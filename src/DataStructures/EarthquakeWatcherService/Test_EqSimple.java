package DataStructures.EarthquakeWatcherService;

import java.io.IOException;
import realtimeweb.earthquakeservice.exceptions.EarthquakeException;
import realtimeweb.earthquakewatchers.WatcherParseException;

/**
 * Tests all logic within class EqSimple.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 15, 2013
 */
public class Test_EqSimple extends junit.framework.TestCase {

    public void setUp() {
	new EqSimple();
    }

    /**
     * Place a description of your method here.
     *
     * @throws IOException
     * @throws WatcherParseException
     * @throws EarthquakeException
     */
    public void test_checkForOptionalCommandLineArguments() throws IOException,
	    WatcherParseException, EarthquakeException {
	String[] args1 = { "--all", "watcher.txt", "normal.earthquakes" };
	EqSimple.checkForOptionalCommandLineArguments(args1);
	assertTrue(EqSimple.allParameterGiven);
	assertFalse(EqSimple.liveParameterGiven);

	EqSimple.liveParameterGiven = false;
	EqSimple.allParameterGiven = false;
	String[] args2 = { "--all", "watcher.txt", "live" };
	EqSimple.checkForOptionalCommandLineArguments(args2);
	assertTrue(EqSimple.liveParameterGiven);
	assertTrue(EqSimple.allParameterGiven);

	EqSimple.liveParameterGiven = false;
	EqSimple.allParameterGiven = false;
	String[] args3 = { "watcher.txt", "normal.earthquakes" };
	EqSimple.checkForOptionalCommandLineArguments(args3);
	assertFalse(EqSimple.liveParameterGiven);
	assertFalse(EqSimple.allParameterGiven);

	EqSimple.liveParameterGiven = false;
	EqSimple.allParameterGiven = false;
	String[] args4 = { "watcher.txt", "live" };
	EqSimple.checkForOptionalCommandLineArguments(args4);
	assertTrue(EqSimple.liveParameterGiven);
	assertFalse(EqSimple.allParameterGiven);
    }

    public void test_getEarthquakeFileName() {
	String[] args2 = { "--all", "watcher.txt", "live" };
	String[] args1 = { "--all", "watcher.txt", "normal.earthquakes" };
	String[] args3 = { "watcher.txt", "normal.earthquakes" };
	String[] args4 = { "watcher.txt", "live" };
	// TODO: finish tests with invalid filename
    }

    public void test_getWatcherFileName() {
	String[] args2 = { "--all", "watcher.txt", "live" };
	String[] args1 = { "--all", "watcher.txt", "normal.earthquakes" };
	String[] args3 = { "watcher.txt", "normal.earthquakes" };
	String[] args4 = { "watcher.txt", "live" };
	// TODO: finish tests with invalid filename
    }

    public static void test_processCommands() {

    }

    /**
     * Place a description of your method here.
     */
    public void test_getWatcherName() {
	String command1 = "add	278	216	Riley";
	assertEquals("Riley", EqSimple.getWatcherName(command1));

	String command2 = "delete	Brooklyn";
	assertEquals("Brooklyn", EqSimple.getWatcherName(command2));

	try {
	    String command3 = "query";
	    EqSimple.getWatcherName(command3);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals("In method getWatcherName of class EqSimple"
		    + "you cannot call this method on a query command",
		    expected.getMessage());
	}
    }

    /**
     * Place a description of your method here.
     */
    public void test_getLongitude() {
	String command1 = "add	278	216	Riley";
	assertEquals(278, EqSimple.getLongitude(command1));

	try {
	    String command2 = "delete	Brooklyn";
	    EqSimple.getLongitude(command2);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals("In method getLongitude of class EqSimple"
		    + "the command in the parameter does not have a "
		    + "longitude", expected.getMessage());
	}

	try {
	    String command3 = "query";
	    EqSimple.getLongitude(command3);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals("In method getLongitude of class EqSimple"
		    + "the command in the parameter does not have a "
		    + "longitude", expected.getMessage());
	}
    }

    /**
     * Place a description of your method here.
     */
    public void test_getLatitude() {
	String command1 = "add	278	216	Riley";
	assertEquals(216, EqSimple.getLatitude(command1));

	try {
	    String command2 = "delete	Brooklyn";
	    EqSimple.getLatitude(command2);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals("In method getLatitude of class EqSimple"
		    + "the command in the parameter does not have a "
		    + "latitude", expected.getMessage());
	}

	try {
	    String command3 = "query";
	    EqSimple.getLatitude(command3);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals("In method getLatitude of class EqSimple"
		    + "the command in the parameter does not have a "
		    + "latitude", expected.getMessage());
	}
    }

    public void test_processWatcherAddRequest() {
	Watcher watcher = new Watcher("Sam", 5, 8);
	EqSimple.processWatcherAddRequest(watcher);
	// TODO: how to assert that print statement was
	// printed to standard console
    }

    public void test_processWatcherDeleteRequest() {
	// TODO: test all possibilities
    }

    public void test_printLargestRecentEarthquake() {
	// TODO: implement
    }

    public void test_removeExpiredEarthquakesInQueueAndMaxHeap() {
	// TODO: implement
    }

    public void test_getNewEarthquakes() {
	// TODO: implement
    }

    public void test_isDuplicateInQueueAndHeap() {
	// TODO: implement
    }

    public void test_updateRelevantWatchersOfNewEarthquakes() {
	// TODO: implement
    }
}
