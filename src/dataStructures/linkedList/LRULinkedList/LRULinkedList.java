package dataStructures.linkedList.LRULinkedList;

import dataStructures.linkedList.doublyLinkedList.DoublyLinkedListNode;

import dataStructures.linkedList.doublyLinkedList.DoublyLinkedList;

/**
 * @author Alexander Norton (ajn123@vt.edu)
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 26, 2013
 * @param <E>
 *            The type of element to be stored in this LRU linked list.
 */
public class LRULinkedList<E> {
    private int maximumNumberOfNodes;
    private DoublyLinkedList<E> leastRecentlyUsedList;

    /**
     * Create a new LRULinkedList object.
     *
     * @param maximumNumberOfNodes
     */
    public LRULinkedList(int maximumNumberOfNodes) {
	// add one to account for null terminating node
	this.maximumNumberOfNodes = maximumNumberOfNodes;
	this.leastRecentlyUsedList = new DoublyLinkedList<E>();

    }

    /**
     * 3 Cases: 1) if element is new & list is not full append element to list.
     * 2) if element is new & list is full append element to list and remove
     * element after head. 3) if element is duplicate, find element within list,
     * remove it, and append it to the list.
     *
     * @param element
     *            to be added or promoted within linked list.
     *
     * @return 3 cases: 1) if element is new & list is not full return null; 2)
     *         if element is new & list is full return removed element(element
     *         after head) 3) if element is duplicate, return null.
     */
    public E insertNewElementOrPromoteIfElementIsAlreadyInside(E element) {
	// if element is in the LRU linked list, then remove it and move it
	// to the tail to represent it was most recently used.
	DoublyLinkedListNode<E> nodeToPromoteIfAlreadyInside = this.leastRecentlyUsedList
		.findAndRemoveNode(element);
	if (nodeToPromoteIfAlreadyInside != null) {
	    // nodeRemovePoisiton now holds a node already in the linked list

	    // move the removed node to the last node position within the linked
	    // list
	    this.leastRecentlyUsedList.appendNode(nodeToPromoteIfAlreadyInside);

	    return null;
	} else { // element is new
		 // if element is new add it to the LRU linked list
	    this.leastRecentlyUsedList.appendNode(new DoublyLinkedListNode<E>(
		    element));

	    // remove the element after the head if the current LRU linked list
	    // size is equal to the maximum size
	    if (this.leastRecentlyUsedList.getSize() >= (this.maximumNumberOfNodes + 1)) {
		return this.leastRecentlyUsedList.removeFirstNode().getData();
	    } else {
		return null;
	    }
	}
    }

    /**
     * @return The data of the least recently used node if it exists; otherwise
     *         return null.
     */
    public E removeLeastRecentlyUsedElement() {
	DoublyLinkedListNode<E> leastRecentlyUsedNode = this.leastRecentlyUsedList
		.removeFirstNode();
	if (leastRecentlyUsedNode != null) {
	    return leastRecentlyUsedNode.getData();
	} else {
	    return null;
	}
    }

    /**
     * @return The number of elements in this linked list.
     */
    public int getSize() {
	return this.leastRecentlyUsedList.getSize();
    }

    @Override
    public String toString() {
	return this.leastRecentlyUsedList.toString() + " maxSize = "
		+ this.maximumNumberOfNodes;
    }
}