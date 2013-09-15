package DataStructures.EarthquakeWatcherService;

import java.util.Date;

import realtimeweb.earthquakeservice.domain.Earthquake;

import java.util.List;

import java.util.ArrayList;
import realtimeweb.earthquakeservice.exceptions.EarthquakeException;
import realtimeweb.earthquakeservice.domain.History;
import realtimeweb.earthquakeservice.domain.Threshold;
import realtimeweb.earthquakeservice.domain.Report;
import realtimeweb.earthquakewatchers.WatcherParseException;
import java.io.IOException;
import java.io.FileInputStream;
import realtimeweb.earthquakewatchers.WatcherService;
import java.io.InputStream;
import realtimeweb.earthquakeservice.regular.EarthquakeService;

/**
 * On my honor:
 *
 * - I have not used source code obtained from another student, or any other
 * unauthorized source, either modified or unmodified.
 *
 * - All source code and documentation used in my program is either my original
 * work, or was derived by me from the source code published in the textbook for
 * this course.
 *
 * - I have not discussed coding details about this project with anyone other
 * than my partner (in the case of a joint submission), instructor, ACM/UPE
 * tutors or the TAs assigned to this course. I understand that I may discuss
 * the concepts of this program with other students, and that another student
 * may help me debug my program so long as neither of us writes anything during
 * the discussion or modifies any computer file during the discussion. I have
 * violated neither the spirit nor letter of this restriction
 *
 * Please visit http://mickey.cs.vt.edu/cs3114-earthquake/ for more
 * documentation on the below code.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 12, 2013
 */
public class EqSimple {
    private static SinglyLinkedList<String> linkedListWatcher;
    private static LinkedQueue<NodeAwareOfHeapIndex> linkedQueueOfRecentEarthquakes;
    private static MaxHeap<NodeAwareOfHeapIndex> maxHeapOfRecentEarthquakes;

    private static EarthquakeService earthquakeService;
    private static WatcherService watcherService;

    /**
     * If true, then a message is output for every earthquake processed on top
     * of everything else. If false, only output notifications messages and
     * messages related to the user request stream.
     */
    private static boolean allParameterGiven = false;

    /**
     * If true, use the real-time data stream. If false, use the given local
     * file to simulate the real-time data stream.
     */
    private static boolean liveParameterGiven = false;

    private static final long secondsInSixHours = 21600;

    private static long unixTimeOfEarliestQuake = -1;

    public static void main(String[] commandLineArguments) throws IOException,
	    WatcherParseException, EarthquakeException {

	checkForOptionalCommandLineArguments(commandLineArguments);

	setUpDataStructures();

	// -----------------------2 Input Streams-----------------------------
	// this can be live or a file with earthquake data
	InputStream normalEarthquakes = new FileInputStream(
		"./src/DataStructures/normal.earthquakes");
	earthquakeService = EarthquakeService.getInstance(normalEarthquakes);

	InputStream watcherCommandFile = new FileInputStream(
		"./src/DataStructures/watcher.txt");
	watcherService = WatcherService.getInstance(watcherCommandFile);
	// -------------------------------------------------------------------

	// Poll the earthquake and watcher service while there are still
	// commands
	while (watcherService.hasCommands()) {
	    // nextCommands contains the watchers at the same time step
	    // as the ArrayList index
	    ArrayList<String> nextCommands = watcherService.getNextCommands();

	    // process next commands ...
	    processCommands(nextCommands);

	    Report latestQuakesInfo = earthquakeService.getEarthquakes(
		    Threshold.ALL, History.HOUR);

	    removeExpiredEarthquakesInQueueAndMaxHeap();

	    // earthquakes that have occurred in the recent hour time step
	    List<Earthquake> latestEarthquakes = latestQuakesInfo
		    .getEarthquakes();
	    List<Earthquake> newEarthquakes = getNewEarthquakes(latestEarthquakes);

	    // list of wrapper classes to hold earthquake indexes within the
	    // heap
	    List<NodeAwareOfHeapIndex> earthquakesWithHeapIndexes;

	    // add new earthquakes to rear of the earthquakeQueue
	    // and maxHeap based on magnitude
	    for (int i = 0; i < newEarthquakes.size(); i++) {
		NodeAwareOfHeapIndex newEarthquakeNode = new NodeAwareOfHeapIndex(
			newEarthquakes.get(i));
		linkedQueueOfRecentEarthquakes.enqueue(newEarthquakeNode);

		NodeAwareOfHeapIndex earthquakeWithHeapIndex = new NodeAwareOfHeapIndex(
			newEarthquakes.get(i));
		maxHeapOfRecentEarthquakes.insert(earthquakeWithHeapIndex);

		// TODO: Update each node in the max heap of it's
		// correct position within the heap

		updateCloseByWatchersOfNewEarthquake(newEarthquakes.get(i));
	    }
	    System.out.println("-------------------------------");
	}
    }

    private static void checkForOptionalCommandLineArguments(
	    String[] commandLineArguments) {
	// args = { --all, watcher.txt, normal.earthquakes } OR
	// args = { watcher.txt, normal.earthquakes }

	if (commandLineArguments.length == 3
		&& commandLineArguments[0] == "--all") {
	    allParameterGiven = true;
	} else if (commandLineArguments[1] == "live"
		|| commandLineArguments[2] == "live") {
	    liveParameterGiven = true;
	}
    }

    private static void setUpDataStructures() {
	// store watchers in the order that they arrive.
	linkedListWatcher = new SinglyLinkedList<String>();

	// store the list of recent earthquake records in order of arrival
	linkedQueueOfRecentEarthquakes = new LinkedQueue<NodeAwareOfHeapIndex>();

	int heapCapacity = 1000; // no testing of this program will require more
				 // than 10000 earthquakes
	NodeAwareOfHeapIndex[] heap = new NodeAwareOfHeapIndex[heapCapacity];

	// also stores the list of recent earthquakes ordered by earthquake
	// magnitude
	maxHeapOfRecentEarthquakes = new MaxHeap<NodeAwareOfHeapIndex>(heap,
		heapCapacity, 0);
    }

    public static void processCommands(ArrayList<String> commands) {
	if (commands.size() == 0) {
	    return; // since no commands to process
	}
	// commands = [add 81 274 Tristan, query]
	for (int i = 0; i < commands.size(); i++) {
	    String command = commands.get(i);
	    if (command.contains("add")) {
		String watcherName = getWatcherName(command);
		processWatcherAddRequest(watcherName);
	    } else if (command.contains("delete")) {
		String watcherName = getWatcherName(command);
		processWatcherDeleteRequest(watcherName);
	    } else if (command.contains("query")) {
		reportEarthquakesToRelevantWatchers();
	    }
	}
    }

    /**
     * As earthquake records arrive or expire, they are added to or removed from
     * both the queue or max heap.
     */
    public static void removeExpiredEarthquakesInQueueAndMaxHeap() {
	// assume this boolean variable is initially the case
	boolean timeRangeBetweenFrontAndRearEarthquakeInQueueGreaterThan6Hours = true;
	while (linkedQueueOfRecentEarthquakes.length() > 0
		&& timeRangeBetweenFrontAndRearEarthquakeInQueueGreaterThan6Hours) {
	    // the front of the queue is holding the oldest earthquakes
	    Earthquake earthquakeToCheckToBeRemoved = linkedQueueOfRecentEarthquakes
		    .frontValue().getEarthquake();
	    // unix time definition can be found at:
	    // http://en.wikipedia.org/wiki/Unix_time
	    long unixTimeOfOldestQuake = earthquakeToCheckToBeRemoved.getTime() / 1000;

	    // if this old earthquake is greater than 6 hours compared to
	    // the earliest quake in the queue then remove the outdated
	    // earthquake
	    if ((unixTimeOfEarliestQuake - unixTimeOfOldestQuake) > secondsInSixHours) {
		int sameQuakeHeapIndex = linkedQueueOfRecentEarthquakes.dequeue()
			.getIndexWithinHeapArray();
		linkedQueueOfRecentEarthquakes.dequeue();
		maxHeapOfRecentEarthquakes.remove(sameQuakeHeapIndex);
	    } else {
		timeRangeBetweenFrontAndRearEarthquakeInQueueGreaterThan6Hours = false;
	    }
	}
    }

    /**
     * When a new earthquake comes in and is added to the MaxHeap, print a line
     * for each watcher that is within the appropriate distance.
     */
    public static void updateCloseByWatchersOfNewEarthquake(
	    Earthquake earthquake) {
	// TODO: implement
    }

    /**
     * All expired earthquakes have already been removed from the queue and
     * maxheap.
     */
    public static List<Earthquake> getNewEarthquakes(
	    List<Earthquake> latestQuakes) {
	List<Earthquake> newQuakes = new ArrayList<Earthquake>();
	// TODO: check latestQuakes and deduce if any are actually new
	// earthquakes

	return newQuakes;
    }

    public static void reportEarthquakesToRelevantWatchers() {
	// TODO: implement
    }

    /**
     * Precondition: command parameter must have a watcherName.
     */
    public static String getWatcherName(String command) {
	String[] splitCommand = command.split("\t");

	// watcherName will always be either in the 1st index or 3rd index
	String watcherName = "";
	if (splitCommand.length == 2) {
	    watcherName = splitCommand[1];
	} else if (splitCommand.length == 4) {
	    watcherName = splitCommand[3];
	}
	return watcherName;
    }

    public static void processWatcherAddRequest(String watcherName) {
	// add watcher to linkedListWatcher to the end of linked list
	linkedListWatcher.append(watcherName);

	System.out.println(watcherName + " is added to the watchers list");
    }

    public static void processWatcherDeleteRequest(String watcherName) {
	// remove watcher from linkedListWatcher
	int valuePosition = linkedListWatcher.findValuePosition(watcherName);
	if (valuePosition == -1) {
	    linkedListWatcher.moveCurrentNodeToPosition(valuePosition);
	    linkedListWatcher.remove();
	}

	System.out.println(watcherName + " is removed from the watchers list");
    }

    public static void getLargestRecentEarthquake() {
	// output a copy of the information for the largest earthquake
	// currently in the root of the max heap.
	System.out.println("Location of largest earthquake in past 6 hours:");
	// earthquake location description

	// if maxheap is empty printout: No record on MaxHeap
    }
}
