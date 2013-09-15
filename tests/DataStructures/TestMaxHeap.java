package DataStructures;

/**
 * Tests all methods within class SinglyLinkedList.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 8, 2013
 */
public class TestMaxHeap extends junit.framework.TestCase {
    private MaxHeap maxHeap;

    public void setUp() {
	int numberOfNodes = 10;
	int capacity = 11;
	Integer[] heap = new Integer[capacity];
	for (int i = 0; i < numberOfNodes; i++) {
	    heap[i] = i;
	}
	this.maxHeap = new MaxHeap(heap, capacity, numberOfNodes);
    }

    public void test_insert() {
	this.maxHeap.insert(11);
	assertEquals("11 9 6 7 8 5 2 0 3 1 4 ",
		this.maxHeap.printMaxHeapArray());
	try {
	    this.maxHeap.insert(12);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals("In method insert of class " + "MaxHeap the value: 12"
		    + " could not be inserted because the max-heap is full",
		    expected.getMessage());
	}
    }

    public void test_remove() {
	assertEquals("9 8 6 7 4 5 2 0 3 1 null ", this.maxHeap.printMaxHeapArray());
	assertEquals(1, this.maxHeap.remove(9));
	assertEquals("9 8 6 7 4 5 2 0 3 1 null ", this.maxHeap.printMaxHeapArray());
	assertEquals(3, this.maxHeap.remove(3));
	assertEquals("9 8 6 3 4 5 2 0 7 1 null ", this.maxHeap.printMaxHeapArray());

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

    public void test_removeMaximumValue() {
	assertEquals("9 8 6 7 4 5 2 0 3 1 null ", this.maxHeap.printMaxHeapArray());
	assertEquals(9, this.maxHeap.removeMaximumValue());
	assertEquals("8 7 6 3 4 5 2 0 1 9 null ", this.maxHeap.printMaxHeapArray());
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

    public void test_correctNodeIndexByShifting() {
	assertEquals("9 8 6 7 4 5 2 0 3 1 null ", this.maxHeap.printMaxHeapArray());
	this.maxHeap.swap(0, 2);
	assertEquals("6 8 9 7 4 5 2 0 3 1 null ", this.maxHeap.printMaxHeapArray());
	this.maxHeap.correctNodeIndexByShifting(0);
	assertEquals("9 8 6 7 4 5 2 0 3 1 null ", this.maxHeap.printMaxHeapArray());

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

    public void test_swap() {
	assertEquals("9 8 6 7 4 5 2 0 3 1 null ", this.maxHeap.printMaxHeapArray());
	this.maxHeap.swap(0, 1);
	assertEquals("8 9 6 7 4 5 2 0 3 1 null ", this.maxHeap.printMaxHeapArray());

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

    public void test_isLeafNode() {
	assertTrue(this.maxHeap.isLeafNode(5));
	assertFalse(this.maxHeap.isLeafNode(4));
	assertFalse(this.maxHeap.isLeafNode(12));
    }

    public void test_getNumberOfNodes() {
	assertEquals(10, this.maxHeap.getNumberOfNodes());
    }

    public void test_getHeapHeight() {
	assertEquals(4, this.maxHeap.getHeapHeight());
    }

    public void test_printMaxHeapArray() {
	assertEquals("9 8 6 7 4 5 2 0 3 1 null ", this.maxHeap.printMaxHeapArray());
    }
}
