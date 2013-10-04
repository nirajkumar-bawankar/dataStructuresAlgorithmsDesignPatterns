package dataStructures;

import dataStructures.MaxHeap;

/**
 * Tests all logic within class SinglyLinkedList.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 8, 2013
 */
public class MaxHeapTest extends junit.framework.TestCase {
    private MaxHeap<Integer> maxHeap;

    public void setUp() {
	int numberOfNodes = 10;
	int capacity = 11;
	Integer[] heap = new Integer[capacity];
	for (int i = 0; i < numberOfNodes; i++) {
	    heap[i] = new Integer(i);
	}
	this.maxHeap = new MaxHeap<Integer>(heap, capacity, numberOfNodes);
    }

    /**
     * Assert new elements can be added to max-heap up to capacity and then the
     * correct exception is thrown when the max-heap is full.
     */
    public void test_insert() {
	this.maxHeap.insert(10);
	assertEquals("10 9 6 7 8 5 2 0 3 1 4 ",
		this.maxHeap.printMaxHeapArray());
	try {
	    this.maxHeap.insert(new Integer(11));
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals("In method insert of class "
		    + "MaxHeap the element: 11"
		    + " could not be inserted because the max-heap is full",
		    expected.getMessage());
	}
    }

    /**
     * Assert remove a node at a specific index works correctly when the index
     * is valid and the correct exception is thrown when the index is invalid.
     */
    public void test_remove() {
	assertEquals("9 8 6 7 4 5 2 0 3 1 null ",
		this.maxHeap.printMaxHeapArray());
	assertEquals(1, (int) this.maxHeap.remove(9));
	assertEquals("9 8 6 7 4 5 2 0 3 1 null ",
		this.maxHeap.printMaxHeapArray());
	assertEquals(3, (int) this.maxHeap.remove(3));
	assertEquals("9 8 6 3 4 5 2 0 7 1 null ",
		this.maxHeap.printMaxHeapArray());

	try {
	    this.maxHeap.remove(-1);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals(
		    "In method remove of class "
			    + "MaxHeap the input node postion to be removed is invalid",
		    expected.getMessage());
	}
    }

    /**
     * Assert remove the maximum a node works for non-empty and empty max-heaps.
     */
    public void test_removeMaximumValue() {
	assertEquals("9 8 6 7 4 5 2 0 3 1 null ",
		this.maxHeap.printMaxHeapArray());
	assertEquals(9, (int) this.maxHeap.removeMaximumValue());
	assertEquals("8 7 6 3 4 5 2 0 1 9 null ",
		this.maxHeap.printMaxHeapArray());
	for (int i = 0; i < 9; i++) {
	    this.maxHeap.removeMaximumValue();
	}
	// maxHeap now has size of 0
	try {
	    this.maxHeap.removeMaximumValue();
	    fail("should've thrown an exception!");
	} catch (IllegalStateException expected) {
	    assertEquals("In method removeMaximumValue of class "
		    + "MaxHeap the value you cannot remove a value from an "
		    + "empty max-heap", expected.getMessage());
	}
    }

    /**
     * Test a node can be shifted back into the correct index within the array
     * based max-heap after being swapped into the wrong position. Also assert
     * the correct exception is thrown when an invalid index is given.
     */
    public void test_correctNodeIndexByShifting() {
	assertEquals("9 8 6 7 4 5 2 0 3 1 null ",
		this.maxHeap.printMaxHeapArray());
	this.maxHeap.swap(0, 2);
	assertEquals("6 8 9 7 4 5 2 0 3 1 null ",
		this.maxHeap.printMaxHeapArray());
	this.maxHeap.correctNodeIndexByShifting(0);
	assertEquals("9 8 6 7 4 5 2 0 3 1 null ",
		this.maxHeap.printMaxHeapArray());

	try {
	    this.maxHeap.correctNodeIndexByShifting(-1);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals(
		    "In method shiftDown of class "
			    + "MaxHeap the value: -1"
			    + " represents a node that does not exist in the current heap",
		    expected.getMessage());
	}
    }

    /**
     * Assert the swap method can correctly switch the location of two nodes.
     * Also assert the swap method throws the correct exception when either of
     * it's input indexes are invalid.
     */
    public void test_swap() {
	assertEquals("9 8 6 7 4 5 2 0 3 1 null ",
		this.maxHeap.printMaxHeapArray());
	this.maxHeap.swap(0, 1);
	assertEquals("8 9 6 7 4 5 2 0 3 1 null ",
		this.maxHeap.printMaxHeapArray());

	try {
	    this.maxHeap.swap(12, 1);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals(
		    "In method swap of class "
			    + "MaxHeap the input arrayIndex1 is not a valid node position",
		    expected.getMessage());
	}
	try {
	    this.maxHeap.swap(1, 12);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals(
		    "In method swap of class "
			    + "MaxHeap the input arrayIndex2 is not a valid node position",
		    expected.getMessage());
	}
    }

    /**
     * Assert the getParentIndex() is correct for valid indexes and the correct
     * exception is thrown for invalid input indexes.
     */
    public void test_getParentIndex() {
	assertEquals(2, this.maxHeap.getParentIndex(5));
	try {
	    this.maxHeap.getParentIndex(-1);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals(
		    "In method getParentPosition of class "
			    + "MaxHeap your input node position at -1 "
			    + "must be > 0", expected.getMessage());
	}
    }

    /**
     * Assert the getRightChildIndex() is correct for valid indexes and the
     * correct exception is thrown for invalid input indexes.
     */
    public void test_getRightChildIndex() {
	assertEquals(10, this.maxHeap.getRightChildIndex(4));
	try {
	    this.maxHeap.getRightChildIndex(6);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals("In method rightChild of class "
		    + "MaxHeap your input node position at 6 "
		    + "does not have a right child.", expected.getMessage());
	}
    }

    /**
     * Assert the getLeftChildIndex() is correct for valid indexes and the
     * correct exception is thrown for invalid input indexes.
     */
    public void test_getLeftChildIndex() {
	assertEquals(9, this.maxHeap.getLeftChildIndex(4));
	try {
	    this.maxHeap.getLeftChildIndex(6);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals("In method leftChild of class "
		    + "MaxHeap your input node position at 6 "
		    + "does not have a left child.", expected.getMessage());
	}
    }

    /**
     * Assert the isLeafNode() method returns correct results.
     */
    public void test_isLeafNode() {
	assertTrue(this.maxHeap.isLeafNode(5));
	assertFalse(this.maxHeap.isLeafNode(4));
	assertFalse(this.maxHeap.isLeafNode(12));
    }

    /**
     * Assert getNumberOfNodes() works when elements are removed and inserted
     * from and into the max-heap.
     */
    public void test_getNumberOfNodes() {
	assertEquals(10, this.maxHeap.getNumberOfNodes());
	this.maxHeap.removeMaximumValue();
	assertEquals(9, this.maxHeap.getNumberOfNodes());
	this.maxHeap.insert(new Integer(10));
	assertEquals(10, this.maxHeap.getNumberOfNodes());
    }

    /**
     * Assert the heap height was calculated correclty.
     */
    public void test_getHeapHeight() {
	assertEquals(4, this.maxHeap.getHeapHeight());
    }

    /**
     * Assert the toString prints out the properly formatted String
     * representation of the array based max-heap.
     */
    public void test_printMaxHeapArray() {
	assertEquals("9 8 6 7 4 5 2 0 3 1 null ",
		this.maxHeap.printMaxHeapArray());
    }
}
