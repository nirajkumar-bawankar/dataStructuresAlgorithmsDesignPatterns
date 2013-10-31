package dataStructures.linkedList.doublyLinkedList;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 30, 2013
 *
 * @param <E>
 *            The type of data stored in the Node.
 */
public class DoublyLinkedList<E> {
    private DoublyLinkedListNode<E> head;
    private DoublyLinkedListNode<E> tail;

    private int size;

    /**
     * Creates an empty doubly linked list.
     */
    public DoublyLinkedList() {
	this.head = new DoublyLinkedListNode<E>(null);
	this.tail = new DoublyLinkedListNode<E>(null);

	this.head.setNextNode(this.tail);
	this.tail.setPreviousNode(this.head);

	this.size = 0;
    }

    /**
     * Adds an Node the end of the linked list.
     *
     * @param nodeToAppend
     *            The Node to be added at the end of this linked list. the item
     *            to add
     */
    public void appendNode(DoublyLinkedListNode<E> nodeToAppend) {
	// If the list is not empty, then we need to create a new
	// node, set the current tail's next reference to point to
	// the new one, and then reassign tail to point to the new
	// node at the end of the list.
	this.tail.getPreviousNode().setNextNode(nodeToAppend);
	nodeToAppend.setPreviousNode(this.tail.getPreviousNode());
	this.tail.setPreviousNode(nodeToAppend);
	nodeToAppend.setNextNode(this.tail);

	this.size++;
    }

    /**
     * Return and remove the node within the linked list with data dataToFind.
     *
     * @param dataOfNodeToFind
     * @return The removed Node if found; otherwise return null.
     */
    DoublyLinkedListNode<E> findAndRemoveNode(E dataOfNodeToFind) {
	DoublyLinkedListNode<E> currentNode = this.head.getNextNode();
	while (currentNode != this.tail) {
	    if (currentNode.getData().equals(dataOfNodeToFind)) {
		currentNode.getPreviousNode().setNextNode(
			currentNode.getNextNode());
		currentNode.getNextNode().setPreviousNode(
			currentNode.getPreviousNode());
		currentNode.setNextNode(null);
		currentNode.setPreviousNode(null);

		this.size--;
		return currentNode;
	    }
	    currentNode = currentNode.getNextNode();
	}
	return null;
    }

    /**
     * Remove the first Node in this linked list (the head Node's next node).
     *
     * @return The removed node.
     */
    public DoublyLinkedListNode<E> removeFirstNode() {
	if (this.size == 0) {
	    return null;
	} else {
	    DoublyLinkedListNode<E> currentNode = this.head.getNextNode();

	    this.head.setNextNode(currentNode.getNextNode());

	    currentNode.getNextNode().setPreviousNode(this.head);
	    currentNode.setNextNode(null);
	    currentNode.setPreviousNode(null);

	    this.size--;
	    return currentNode;
	}
    }

    /**
     * @return The number of nodes in this linked list.
     */
    public int getSize() {
	return this.size;
    }

    /**
     * @return a String representation of this doubly linked list. For example a
     *         linked list starting at the head with elements (head) 0, 1, 2, 3
     *         (tail) will be returned as a String equal to "< 0 1 2 3 >".
     */
    @Override
    public String toString() {

	StringBuffer stringRepresentationOfLinkedList = new StringBuffer();

	stringRepresentationOfLinkedList.append("< ");

	DoublyLinkedListNode<E> currentNode = this.head.getNextNode();
	while (currentNode != this.tail) {
	    stringRepresentationOfLinkedList
		    .append(currentNode.getData() + " ");
	    currentNode = currentNode.getNextNode();
	}

	stringRepresentationOfLinkedList.append(">");
	return stringRepresentationOfLinkedList.toString();
    }
}