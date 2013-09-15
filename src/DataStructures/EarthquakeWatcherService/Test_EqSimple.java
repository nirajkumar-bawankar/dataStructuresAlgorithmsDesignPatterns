package DataStructures.EarthquakeWatcherService;

public class Test_EqSimple extends junit.framework.TestCase {
    private EqSimple eq;

    public void setUp() {
	this.eq = new EqSimple();
    }

    public void test_processCommands() {

    }

    public void test_reportEarthquakesToRelevantWatchers() {

    }

    public void test_getWatcherName() {
	String command1 = "add	278	216	Riley";
	assertEquals("Riley", eq.getWatcherName(command1));

	String command2 = "delete	Brooklyn";
	assertEquals("Brooklyn", eq.getWatcherName(command2));

	String command3 = "query";
	assertEquals("", eq.getWatcherName(command3));
    }

    public void test_processWatcherAddRequest() {

    }

    public void test_processWatcherDeleteRequest() {

    }

    public void test_getLargestRecentEarthquake() {

    }
}
