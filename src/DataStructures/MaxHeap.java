package DataStructures;

import java.lang.Comparable;

/**
 * A heap is defined by the following 2 properties: 1) It is a complete binary
 * tree. 2) The values stored in a heaps nodes are partially ordered.
 *
 * A max-heap has the property that every node stores a value that is greater
 * than or equal to the value of either of its children. This causes the root
 * node to store the maximum value in the complete binary tree.
 *
 * Because the following max-heap is implemented using an array it is important
 * to understand that the variable arrayIndex corresponds to a nodes position
 * within the complete binary tree.
 *
 * To develop a deep understanding of max and min heaps please visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/Heaps.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sept 8, 2013
 */
public class MaxHeap<E extends Comparable> {
    private E[] heap;
    private int capacity; // maximum size of heap
    private int numberOfNodes; // number of nodes in current heap

    public MaxHeap(E[] heap, int capacity, int numberOfNodes) {
	this.heap = heap;
	this.capacity = capacity;
	this.numberOfNodes = numberOfNodes;

	this.buildHeap();
    }

    public void buildHeap() {
	for (int i = (this.numberOfNodes / 2 - 1); i >= 0; i--) {
	    this.correctNodeIndexByShifting(i);
	}
    }

    public void insert(E nodeValue) {
	if (this.capacity <= this.numberOfNodes) {
	    throw new IllegalArgumentException("In method insert of class "
		    + "MaxHeap the value: " + nodeValue
		    + " could not be inserted because the max-heap is full");
	}

	int currentNodePosition = this.numberOfNodes++;
	this.heap[currentNodePosition] = nodeValue;

	// start at the end of most bottom right leaf node and shift up
	// until the nodeValue has a parent with a greater or equal value
	while ((currentNodePosition != 0)
		&& (this.heap[currentNodePosition].compareTo(this.heap[this
			.getParentIndex(currentNodePosition)]) > 0)) {
	    this.swap(currentNodePosition,
		    this.getParentIndex(currentNodePosition));
	    currentNodePosition = this.getParentIndex(currentNodePosition);
	}
    }

    /**
     * Remove the node at arrayIndex within the MaxHeap and return the node
     * value that the removed node is replaced with.
     */
    public E remove(int arrayIndex) {
	if ((arrayIndex < 0) || (arrayIndex >= this.numberOfNodes)) {
	    throw new IllegalArgumentException("In method remove of class "
		    + "MaxHeap the input node postion to be removed is invalid");
	}

	// if the most bottom right node is being removed there is no work to be
	// done
	if (arrayIndex == (this.numberOfNodes - 1)) {
	    this.numberOfNodes--;
	} else {
	    // swap node to be removed with most bottom right node
	    this.swap(arrayIndex, --this.numberOfNodes);

	    // if swapped node is large, shift it up the tree
	    while ((arrayIndex > 0)
		    && (this.heap[arrayIndex].compareTo(this.heap[this
			    .getParentIndex(arrayIndex)]) > 0)) {
		this.swap(arrayIndex, this.getParentIndex(arrayIndex));
		arrayIndex = this.getParentIndex(arrayIndex);
	    }
	    if (this.numberOfNodes != 0) {
		// if swapped node is small, shift it down the tree
		this.correctNodeIndexByShifting(arrayIndex);
	    }
	}
	return this.heap[arrayIndex];
    }



    /**
     * @return maximum node value in max-heap.
     */
    public E removeMaximumValue() {
	if (this.numberOfNodes <= 0) {
	    throw new IllegalStateException(
		    "In method removeMaximumValue of class "
			    + "MaxHeap the value you cannot remove a value from an "
			    + "empty max-heap");
	}
	// swap maximum with last value
	this.swap(0, --this.numberOfNodes);

	if (this.numberOfNodes != 0) {
	    // if not the last element
	    this.correctNodeIndexByShifting(0);
	}
	return this.heap[this.numberOfNodes];
    }

    /**
     * Place given node position in the correct position within the complete
     * binary tree.
     */
    void correctNodeIndexByShifting(int arrayIndex) {
	if ((arrayIndex < 0) || (arrayIndex >= this.numberOfNodes)) {
	    throw new IllegalArgumentException(
		    "In method shiftDown of class "
			    + "MaxHeap the value: "
			    + arrayIndex
			    + " represents a node that does not exist in the current heap");
	}
	while (!this.isLeafNode(arrayIndex)) {
	    int childIndex = this.getLeftChildIndex(arrayIndex);
	    if ((childIndex < (this.numberOfNodes - 1))
		    && (this.heap[childIndex]
			    .compareTo(this.heap[childIndex + 1]) < 0)) {
		childIndex++; // childIndex is not at index of child with
			      // greater node value
	    }
	    if (this.heap[arrayIndex].compareTo(this.heap[childIndex]) >= 0) {
		return;
	    }
	    this.swap(arrayIndex, childIndex);
	    arrayIndex = childIndex; // node shifted down
	}
    }

    void swap(int arrayIndex1, int arrayIndex2) {
	if (arrayIndex1 < 0 || arrayIndex1 > this.numberOfNodes) {
	    throw new IllegalArgumentException(
		    "In method swap of class "
			    + "MaxHeap the input arrayIndex1 is not a valid node position");
	} else if (arrayIndex2 < 0 || arrayIndex2 > this.numberOfNodes) {
	    throw new IllegalArgumentException(
		    "In method swap of class "
			    + "MaxHeap the input arrayIndex2 is not a valid node position");
	}
	E tempNodeValue = this.heap[arrayIndex1];
	this.heap[arrayIndex1] = this.heap[arrayIndex2];
	this.heap[arrayIndex2] = tempNodeValue;
    }

    public int getParentIndex(int arrayIndex) {
	if (arrayIndex <= 0) {
	    throw new IllegalArgumentException(
		    "In method getParentPosition of class "
			    + "MaxHeap your input node position at "
			    + arrayIndex + " must be > 0");
	} else {
	    return (arrayIndex - 1) / 2;
	}
    }

    public int getRightChildIndex(int arrayIndex) {
	if (arrayIndex >= (this.numberOfNodes / 2)) {
	    throw new IllegalArgumentException("In method rightChild of class "
		    + "MaxHeap your input node position at " + arrayIndex
		    + " does not have a right child.");
	} else {
	    return 2 * arrayIndex + 2;
	}
    }

    public int getLeftChildIndex(int arrayIndex) {
	if (arrayIndex >= (this.numberOfNodes / 2)) {
	    throw new IllegalArgumentException("In method leftChild of class "
		    + "MaxHeap your input node position at " + arrayIndex
		    + " does not have a left child.");
	} else {
	    return 2 * arrayIndex + 1;
	}
    }

    public boolean isLeafNode(int arrayIndex) {
	if ((arrayIndex >= (this.numberOfNodes / 2))
		&& (arrayIndex < this.numberOfNodes)) {
	    return true;
	} else {
	    return false;
	}
    }

    public int getNumberOfNodes() {
	return this.numberOfNodes;
    }

    public int getHeapHeight() {
	double approximateHeight = Math.log(this.numberOfNodes) / Math.log(2);
	int actualHeight = (int) (Math.floor(approximateHeight) + 1);
	return actualHeight;
    }

    public String printMaxHeapArray() {
	StringBuilder maxHeapArray = new StringBuilder();
	for (int i = 0; i < this.heap.length; i++) {
	    maxHeapArray.append(this.heap[i] + " ");
	}
	return maxHeapArray.toString();
    }
}
