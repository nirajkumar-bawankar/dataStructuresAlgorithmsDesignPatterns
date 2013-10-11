package dataStructures;

/**
 * This class tests all logic within class BinarySearchTree.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 9, 2013
 */
public class KeyValuePairTest extends junit.framework.TestCase {
    private KeyValuePair<Integer, String> keyValuePair1;
    private KeyValuePair<Integer, String> keyValuePair2;
    private KeyValuePair<Integer, String> keyValuePair3;
    private KeyValuePair<Integer, String> keyValuePair4;

    public void setUp() {
	this.keyValuePair1 = new KeyValuePair<Integer, String>(new Integer(1),
		new String("a"));
	this.keyValuePair2 = new KeyValuePair<Integer, String>(new Integer(1),
		new String("a"));
	this.keyValuePair3 = new KeyValuePair<Integer, String>(new Integer(0),
		new String("0"));
	this.keyValuePair4 = new KeyValuePair<Integer, String>(new Integer(2),
		new String("ab"));
    }

    public void test_compareTo() {
	// the compareTo does not care about the key value pair's element value
	assertEquals(-1, this.keyValuePair1.compareTo(this.keyValuePair4));
	assertEquals(0, this.keyValuePair1.compareTo(this.keyValuePair2));
	assertEquals(1, this.keyValuePair1.compareTo(keyValuePair3));
    }
}
