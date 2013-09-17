package DataStructures.EarthquakeWatcherService;

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
    private static NamedSinglyLinkedList<Watcher> linkedListWatcher;
    private static LinkedQueue<EarthquakeNodeAwareOfHeapIndex> linkedQueueOfRecentEarthquakes;
    private static EQMaxHeap<EarthquakeNodeAwareOfHeapIndex> maxHeapOfRecentEarthquakes;

    private static EarthquakeService earthquakeService;
    private static WatcherService watcherService;

    /**
     * If true, then a message is output for every earthquake processed on top
     * of everything else. If false, only output notifications messages and
     * messages related to the user request stream.
     */
    static boolean allParameterGiven = false;

    /**
     * If true, use the real-time data stream. If false, use the given local
     * file to simulate the real-time data stream.
     */
    static boolean liveParameterGiven = false;

    private static final long secondsInSixHours = 21600;

    private static long unixTimeOfEarliestQuake = -1;

    /**
     * TODO: Place a description of your method here.
     *
     * @param commandLineArguments
     *            Commands describing how earthquakes and watchers are retrieved
     *            from and how they should be displayed to the console.
     * @throws IOException
     * @throws WatcherParseException
     * @throws EarthquakeException
     */
    public static void main(String[] commandLineArguments) throws IOException,
	    WatcherParseException, EarthquakeException {
	checkForOptionalCommandLineArguments(commandLineArguments);

	setUpDataStructures();

	String earthquakeFileName = getEarthquakeFileName(commandLineArguments);
	String watcherFileName = getWatcherFileName(commandLineArguments);

	// -----------------------2 Input Streams-----------------------------
	// this can be live or a file with earthquake data
	InputStream normalEarthquakes = new FileInputStream(
		"./src/DataStructures/EarthquakeWatcherService/"
			+ earthquakeFileName);
	earthquakeService = EarthquakeService.getInstance(normalEarthquakes);

	InputStream watcherCommandFile = new FileInputStream(
		"./src/DataStructures/EarthquakeWatcherService/"
			+ watcherFileName);
	watcherService = WatcherService.getInstance(watcherCommandFile);
	// -------------------------------------------------------------------

	// Poll the earthquake and watcher service while there are still
	// commands
	while (watcherService.hasCommands()) {
	    // nextCommands contains the watchers at the same time step
	    // as the ArrayList index
	    ArrayList<String> nextCommands = watcherService.getNextCommands();

	    processCommands(nextCommands);

	    Report latestQuakesInfo = earthquakeService.getEarthquakes(
		    Threshold.ALL, History.HOUR);

	    removeExpiredEarthquakesInQueueAndMaxHeap();

	    // earthquakes that have occurred in the recent hour time step
	    List<Earthquake> latestEarthquakes = latestQuakesInfo
		    .getEarthquakes();
	    List<Earthquake> newEarthquakes = getNewEarthquakes(latestEarthquakes);

	    // add new earthquakes to rear of the earthquakeQueue
	    // and maxHeap based on magnitude
	    for (int i = 0; i < newEarthquakes.size(); i++) {
		EarthquakeNodeAwareOfHeapIndex newEarthquakeNode = new EarthquakeNodeAwareOfHeapIndex(
			newEarthquakes.get(i), -1);
		long unixTimeOfQuake = newEarthquakeNode.getEarthquake()
			.getTime() / 1000;
		if (unixTimeOfQuake > unixTimeOfEarliestQuake) {
		    unixTimeOfEarliestQuake = unixTimeOfQuake;
		}

		linkedQueueOfRecentEarthquakes.enqueue(newEarthquakeNode);

		maxHeapOfRecentEarthquakes.insert(newEarthquakeNode);

		if (allParameterGiven) {
		    System.out.println("Earthquake "
			    + newEarthquakeNode.getEarthquake()
				    .getLocationDescription()
			    + " is inserted into the Heap");
		}

		updateRelevantWatchersOfNewEarthquake(newEarthquakes.get(i));
	    }
	    System.out.println("-------------------------------");
	}
    }

    /**
     * Check if the optional command line arguments "live" and "--all" are
     * given.
     *
     * @param commandLineArguments
     *            The commands to be checked.
     */
    static void checkForOptionalCommandLineArguments(
	    String[] commandLineArgumentss) {
	// check for the following 4 different possible commands
	// args = { --all, watcher.txt, normal.earthquakes } OR
	// args = { --all, watcher.txt, live } OR
	// args = { watcher.txt, normal.earthquakes } OR
	// args = { watcher.txt, live }

	String[] commandLineArguments = { "--all", "watcher.txt", "live" };
	if (commandLineArguments.length == 3
		&& commandLineArguments[0].equals("--all")) {
	    allParameterGiven = true;
	} else if ((commandLineArguments.length == 3 && commandLineArguments[2]
		.equals("live")) || commandLineArguments[1].equals("live")) {
	    liveParameterGiven = true;
	} else {
	    throw new IllegalArgumentException(
		    "In method checkForOptionalCommandLineArguments"
			    + " of class EqSimple the given commands are invalid");
	}
    }

    /**
     * Construct a linked list to store watchers. Construct a linkedQueue to
     * store earthquakes in order of time. Construct a max-heap of earthquakes
     * to efficiently query the largest earthquake.
     */
    static void setUpDataStructures() {
	// store watchers in the order that they arrive.
	linkedListWatcher = new NamedSinglyLinkedList<Watcher>();

	// store the list of recent earthquake records in order of arrival
	linkedQueueOfRecentEarthquakes = new LinkedQueue<EarthquakeNodeAwareOfHeapIndex>();

	int heapCapacity = 1000; // no testing of this program will require more
				 // than 1000 earthquakes
	EarthquakeNodeAwareOfHeapIndex[] heap = new EarthquakeNodeAwareOfHeapIndex[heapCapacity];

	// also stores the list of recent earthquakes ordered by earthquake
	// magnitude
	maxHeapOfRecentEarthquakes = new EQMaxHeap<EarthquakeNodeAwareOfHeapIndex>(
		heap, heapCapacity, 0);
    }

    /**
     * Return earthquake file name if input commands are valid.
     *
     * @param commandLineArguments
     *            Commands holding the earthquake input file name.
     * @return The earthquake file name.
     */
    static String getEarthquakeFileName(String[] commandLineArguments) {
	if (commandLineArguments.length == 3
		&& commandLineArguments[2].equals("live")) {
	    throw new IllegalArgumentException(
		    "In method getEarthquakeFileName of class EqSimple"
			    + "the commands state there is no earthquake file "
			    + "and that the program should instead be run live");
	} else if (commandLineArguments.length == 2) {
	    return commandLineArguments[1];
	} else if (commandLineArguments.length == 3) {
	    return commandLineArguments[2];
	} else {
	    throw new IllegalArgumentException(
		    "In method getEarthquakeFileName of class EqSimple"
			    + "the given commands are invalid");
	}
    }

    /**
     * Return watcher file name if input commands are valid.
     *
     * @param commandLineArguments
     *            Commands holding the watcher input file name.
     * @return The watcher file name.
     */
    static String getWatcherFileName(String[] commandLineArguments) {
	if (commandLineArguments.length == 2) {
	    return commandLineArguments[0];
	} else if (commandLineArguments.length == 3) {
	    return commandLineArguments[1];
	} else {
	    throw new IllegalArgumentException(
		    "In method getWatcherFileName of class EqSimple"
			    + "the given commands are invalid");
	}
    }

    /**
     * Process the input list of commands by calling other methods based on
     * whether the command is for adding deleting or querying.
     *
     * @param commands
     *            List of commands with instructions to be executed.
     */
    public static void processCommands(ArrayList<String> commands) {
	if (commands.size() == 0) {
	    return; // since no commands to process
	}
	// commands = [add 81 274 Tristan, query]
	for (int i = 0; i < commands.size(); i++) {
	    String command = commands.get(i);

	    String watcherName = getWatcherName(command);
	    int longitude = getLongitude(command);
	    int latitude = getLatitude(command);
	    Watcher newWatcher = new Watcher(watcherName, longitude, latitude);

	    if (command.contains("add")) {
		processWatcherAddRequest(newWatcher);
	    } else if (command.contains("delete")) {
		processWatcherDeleteRequest(newWatcher);
	    } else if (command.contains("query")) {
		printLargestRecentEarthquake();
	    }
	}
    }

    /**
     * Get the watcherName of commands with the format: add/delete latitude
     * longitude watcherName
     *
     * @param command
     *            The command with the watcher name.
     * @return The watcher name in the command.
     */
    public static String getWatcherName(String command) {
	String[] splitCommand = command.split("\t");

	// watcherName will always be either in the 1st index or 3rd index
	String watcherName = "";
	if (splitCommand.length == 1) {
	    throw new IllegalArgumentException(
		    "In method getWatcherName of class EqSimple"
			    + "you cannot call this method on a query command");
	} else if (splitCommand.length == 2) {
	    watcherName = splitCommand[1];
	} else if (splitCommand.length == 4) {
	    watcherName = splitCommand[3];
	}
	return watcherName;
    }

    /**
     * Get the longitude of commands with the format: add/delete latitude
     * longitude watcherName
     *
     * @param command
     *            The command with the longitude information.
     * @return The longitude of the command.
     */
    public static int getLongitude(String command) {
	// if the command has 4 elements longitude will always be the 2nd
	// element
	int longitude = 0;
	String[] splitCommand = command.split("\t");
	if (splitCommand.length == 4) {
	    longitude = Integer.parseInt(splitCommand[1]);
	} else {
	    throw new IllegalArgumentException(
		    "In method getLongitude of class EqSimple"
			    + "the command in the parameter does not have a "
			    + "longitude");
	}
	return longitude;
    }

    /**
     * Get the latitude of commands with the format: add/delete latitude
     * longitude watcherName
     *
     * @param command
     *            The command with the latitude information.
     * @return The latitude of the command.
     *
     */
    public static int getLatitude(String command) {
	// if the command has 4 elements latitude will always be the 3rd element
	int latitude = 0;
	String[] splitCommand = command.split("\t");
	if (splitCommand.length == 4) {
	    latitude = Integer.parseInt(splitCommand[2]);
	} else {
	    throw new IllegalArgumentException(
		    "In method getLatitude of class EqSimple"
			    + "the command in the parameter does not have a "
			    + "latitude");
	}
	return latitude;
    }

    /**
     * Print to console message of adding a watcher to the linked list.
     *
     * @param watcher
     *            Watcher to be added.
     *
     */
    public static void processWatcherAddRequest(Watcher watcher) {
	// add watcher to linkedListWatcher to the end of linked list
	linkedListWatcher.append(watcher);

	System.out
		.println(watcher.getName() + " is added to the watchers list");
    }

    /**
     * Print to console message of deleting a watcher from the linked list.
     *
     * @param watcher
     *            Watcher to be deleted.
     */
    public static void processWatcherDeleteRequest(Watcher watcher) {
	// remove watcher from linkedListWatcher
	int valuePosition = linkedListWatcher.findValuePosition(watcher);
	if (valuePosition != -1) {
	    linkedListWatcher.moveCurrentNodeToPosition(valuePosition);
	    linkedListWatcher.remove();
	} else {
	    throw new IllegalArgumentException(
		    "In method processWatcherDeleteRequest of class EqSimple"
			    + "the given watcher is not in the queue or heap");
	}

	System.out.println(watcher.getName()
		+ " is removed from the watchers list");
    }

    /**
     * Print to the console the largest earthquake in the past 6 hours.
     */
    public static void printLargestRecentEarthquake() {
	if (maxHeapOfRecentEarthquakes.getNumberOfNodes() == 0) {
	    System.out.println("No record on MaxHeap");
	} else {
	    // greatest magnitude earthquake in past 6 hours
	    Earthquake biggestEarthquake = maxHeapOfRecentEarthquakes
		    .getMaximumValue().getEarthquake();
	    System.out.println("Largest earthquake in past 6 hours:");
	    System.out.println("Magnitude " + biggestEarthquake.getMagnitude()
		    + " at " + biggestEarthquake.getLocation().toString());
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
		int sameQuakeHeapIndex = linkedQueueOfRecentEarthquakes
			.dequeue().getIndexWithinHeapArray();
		linkedQueueOfRecentEarthquakes.dequeue();
		maxHeapOfRecentEarthquakes.remove(sameQuakeHeapIndex);
	    } else {
		timeRangeBetweenFrontAndRearEarthquakeInQueueGreaterThan6Hours = false;
	    }
	}
    }

    /**
     * All expired earthquakes have already been removed from the queue and
     * max-heap.
     *
     * @param latestQuakes
     * @return The new latest earthquakes with duplicates removed.
     */
    public static List<Earthquake> getNewEarthquakes(
	    List<Earthquake> latestQuakes) {
	List<Earthquake> newQuakes = new ArrayList<Earthquake>();
	// check latestQuakes and deduce if any are actually new
	// earthquakes
	for (Earthquake earthquake : latestQuakes) {
	    if (isDuplicateInQueueAndHeap(earthquake)) {
		// do not add earthquake to newQuakes ArrayList
	    } else {
		newQuakes.add(earthquake);
	    }
	}
	return newQuakes;
    }

    /**
     * Checks if given earthquake is a duplicate.
     *
     * @param newEarthquake
     * @return True if the given earthquake is already in the queue; otherwise
     *         false.
     */
    public static boolean isDuplicateInQueueAndHeap(Earthquake newEarthquake) {
	// not duplicate if queue and heap are empty
	if (linkedQueueOfRecentEarthquakes.length() == 0) {
	    return false;
	}

	// begin checking against earthquakes in the queue since the earthquakes
	// are ordered by time.
	Earthquake frontEarthquake = linkedQueueOfRecentEarthquakes
		.frontValue().getEarthquake();
	if (newEarthquake == frontEarthquake) {
	    return true;
	} else {
	    return false;
	}
    }

    /**
     * Update any watchers that have a distance < 2 * Magnitude of
     * newEarthquake. Here distance is defined as ((longitude_watcher)^2 +
     * (latitude_watcher)^2)^0.5
     *
     * @param newEarthquake
     *            The new earthquake that needs to be known by close by
     *            watchers.
     */
    public static void updateRelevantWatchersOfNewEarthquake(
	    Earthquake newEarthquake) {
	// TODO: implement

	// iterate through all current watchers and see if they are close to the
	// current earthquake
	linkedListWatcher.moveToStart();
	while (linkedListWatcher.next()) {
	    Watcher watcher = linkedListWatcher.getValue();
	    int longitude_watcher = watcher.getLongitude();
	    int latitude_watcher = watcher.getLatitude();
	    double distance = Math.sqrt(Math.pow(longitude_watcher, 2)
		    + Math.pow(latitude_watcher, 2));

	    double earthquake_magnitude = newEarthquake.getMagnitude();

	    if (distance < 2 * earthquake_magnitude) {
		System.out.println("Earthquake "
			+ newEarthquake.getLocationDescription()
			+ " is close to " + watcher.getName());
	    }
	}
    }
}
