package dataStructures.linkedList.DoublyLinkedList;
/**
 * @author Alexander Norton (ajn123@vt.edu)
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 30, 2013
 *
 * @param <E>
 *            The type of data stored in the Node.
 */
public class DoublyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;

    private int size;

    /**
     * Creates an empty doubly linked list.
     */
    public DoublyLinkedList() {
	this.head = new Node<E>(null);
	this.tail = new Node<E>(null);

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
    public void appendNode(Node<E> nodeToAppend) {
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
     * @param dataToFind
     * @return The removed Node if found; otherwise return null.
     */
    Node<E> findAndRemoveNode(E dataToFind) {
	Node<E> currentNode = this.head.getNextNode();
	while (currentNode != this.tail) {
	    if (currentNode.getData().equals(dataToFind)) {
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
     * Place a description of your method here.
     *
     * @return
     */
    public Node<E> removeFirstNode() {
	if (this.size == 0) {
	    return null;
	} else {
	    Node<E> currentNode = this.head.getNextNode();

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

    public String toString() {

	StringBuffer stringRepresentationOfLinkedList = new StringBuffer();

	stringRepresentationOfLinkedList.append("< ");

	Node<E> currentNode = this.head.getNextNode();
	while (currentNode != this.tail) {
	    stringRepresentationOfLinkedList
		    .append(currentNode.getData() + " ");
	    currentNode = currentNode.getNextNode();
	}

	stringRepresentationOfLinkedList.append(">");
	return stringRepresentationOfLinkedList.toString();
    }
}