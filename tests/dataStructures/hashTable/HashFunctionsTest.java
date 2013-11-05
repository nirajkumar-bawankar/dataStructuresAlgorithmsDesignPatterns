package dataStructures.hashTable;

/**
 * Tests logic within class HashTable.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Nov 4, 2013
 */
public class HashFunctionsTest extends junit.framework.TestCase {
    public void setUp() {

    }

    public void test_stringFoldingHashFunction() {
	assertEquals(33, HashFunctions.stringFoldingHashFunction("qvg0hz", 100));
    }
}
