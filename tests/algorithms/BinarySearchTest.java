package algorithms;

/**
 * Tests all logic within class BinarySearch.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 9, 2013
 */
public class BinarySearchTest extends junit.framework.TestCase {
    private int[] sortedArray;

    public void setUp() {
	this.sortedArray = new int[10];
	for (int i = 0; i < this.sortedArray.length; i++) {
	    // sorted array contains numbers 0 through 9 inclusive
	    this.sortedArray[i] = i;
	}
    }

    public void test_search() {
	// value 10 is not in the sorted array
	assertEquals(-1, BinarySearch.search(this.sortedArray, 10));

	assertEquals(3, BinarySearch.search(this.sortedArray, 3));
    }
}
