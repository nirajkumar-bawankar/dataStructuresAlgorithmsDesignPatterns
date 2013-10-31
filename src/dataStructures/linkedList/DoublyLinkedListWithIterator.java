package dataStructures.linkedList;

import dataStructures.interfaces.ListIteratorInterface;

import dataStructures.interfaces.ListInterface;

/**
 * If you have a good idea of how many elements will be in your list that does
 * not change very much you should instead use an array based linked list. On
 * the other hand, if you have no idea how many elements you will add to your
 * list and removed from your list use a linked list of nodes as implemented in
 * this file.
 *
 * To view an interactive web page about linked list implementation visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/ListDouble.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 28, 2013
 * @param <E>
 */
public class DoublyLinkedListWithIterator<E> implements ListInterface<E>, ListIteratorInterface<E> {
    private DoublyLinkedListNode<E> head;
    private DoublyLinkedListNode<E> tail;
    private DoublyLinkedListNode<E> currentNode;
    private int size;

    /**
     * Create a new empty DoublyLinkedList object.
     */
    public DoublyLinkedListWithIterator() {
	this.currentNode = this.head = new DoublyLinkedListNode<E>(null, null,
		null);
	this.tail = new DoublyLinkedListNode<E>(null, this.head, null);
	this.head.setNextNode(this.tail);
	this.size = 0;
    }

    @Override
    public void insert(E item) {
	this.currentNode.setNextNode(new DoublyLinkedListNode<E>(item,
		this.currentNode, this.currentNode.getNextNode()));
	this.currentNode.getNextNode().getNextNode()
		.setPreviousNode(this.currentNode.getNextNode());
	this.size++;
    }

    @Override
    public void append(E item) {
	this.tail.setPreviousNode(new DoublyLinkedListNode<E>(item, this.tail
		.getPreviousNode(), this.tail));
	this.tail.getPreviousNode().getPreviousNode()
		.setNextNode(this.tail.getPreviousNode());
	this.size++;
    }

    @Override
    public E remove() {
	if (this.currentNode.getNextNode() == this.tail) {
	    return null; // empty linked list
	}
	E item = this.currentNode.getNextNode().getValue(); // remember value to
							    // be deleted
	this.currentNode.getNextNode().getNextNode()
		.setPreviousNode(this.currentNode);

	// remove current node
	this.currentNode.setNextNode(this.currentNode.getNextNode()
		.getNextNode());
	this.size--;
	return item;
    }

    @Override
    public void clear() {
	// drop access to all other nodes
	this.head.setNextNode(null);
	this.currentNode = this.head = new DoublyLinkedListNode<E>(null, null,
		null);
	this.tail = new DoublyLinkedListNode<E>(null, this.head, null);
	this.head.setNextNode(this.tail);
	this.size = 0;
    }

    @Override
    public void moveToStart() {
	this.currentNode = this.head;
    }

    @Override
    public void moveToEnd() {
	this.currentNode = this.tail.getPreviousNode();
    }

    @Override
    public boolean previous() {
	if (this.currentNode != this.head) {
	    this.currentNode = this.currentNode.getPreviousNode();
	    return true;
	} else {
	    return false; // no node before head node
	}
    }

    @Override
    public boolean next() {
	if (this.currentNode != this.tail.getPreviousNode()) {
	    this.currentNode = this.currentNode.getNextNode();
	    return true;
	} else {
	    return false;
	}
    }

    @Override
    public int length() {
	return this.size;
    }

    @Override
    public int currentPosition() {
	DoublyLinkedListNode<E> tempNode = this.head;
	int indexOfCurrentNode;
	for (indexOfCurrentNode = 0; this.currentNode != tempNode; indexOfCurrentNode++) {
	    tempNode = tempNode.getNextNode();
	}
	return indexOfCurrentNode;
    }

    @Override
    public void moveCurrentToPosition(int position) {
	if (position < 0 || position > this.size) {
	    throw new IllegalArgumentException(
		    "In method moveCurrentToPosition of class "
			    + "DoublyLinkedList the input node postion to be "
			    + "removed is out of bounds");
	}
	this.currentNode = this.head;
	for (int i = 0; i < position; i++) {
	    this.currentNode = this.currentNode.getNextNode();
	}
    }

    @Override
    public E getValue() {
	if (this.currentNode.getNextNode() == this.tail) {
	    return null;
	} else {
	    return this.currentNode.getNextNode().getValue();
	}
    }

    /**
     * @param item
     *            Item to be found within linked list.
     * @return Correct position within linked list or -1 if value is not found
     *         within linked list.
     */
    public int findValuePosition(E item) {
	// move the current position of the linked list back to the original
	// position before this method was called
	int currentPosition = this.currentPosition();

	// counter to track where the found value is within the linked list
	int foundValuePosition = 0;

	// begin searching for item within the linked list from the beginning
	this.moveToStart();
	this.next();

	while (this.currentNode != this.tail) {
	    if (this.currentNode.getValue().equals(item)) {
		this.moveCurrentToPosition(currentPosition);
		return foundValuePosition;
	    } else {
		foundValuePosition++;
		this.currentNode = this.currentNode.getNextNode();
	    }
	}

	this.moveCurrentToPosition(currentPosition);
	return -1;
    }

    /**
     * Creates a easy to read String representation of the doubly linked lists
     * contents.
     *
     * Example 1: < 1 2 3 4 | 5 6 >
     *
     * The vertical bar = the link immediately after the current node.
     *
     * @author Clifford A. Shaffer
     */
    public String toString() {
	int oldPosition = this.currentPosition();
	int length = this.length();
	StringBuffer linkedListAsString = new StringBuffer((length() + 1) * 4);

	this.moveToStart();
	linkedListAsString.append("< ");
	for (int i = 0; i < oldPosition; i++) {
	    linkedListAsString.append(this.getValue());
	    linkedListAsString.append(" ");
	    this.next();
	}
	linkedListAsString.append("| ");
	for (int i = oldPosition; i < length; i++) {
	    linkedListAsString.append(this.getValue());
	    linkedListAsString.append(" ");
	    this.next();
	}
	linkedListAsString.append(">");
	this.moveCurrentToPosition(oldPosition);

	return linkedListAsString.toString();
    }
}
