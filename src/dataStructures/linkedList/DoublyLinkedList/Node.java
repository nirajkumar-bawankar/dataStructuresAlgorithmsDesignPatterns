package dataStructures.linkedList.DoublyLinkedList;

/**
 * A generic doubly linked list Node class.
 *
 * @author Alexander Norton (ajn123@vt.edu)
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 30, 2013
 *
 * @param <E>
 *            the type of data stored in the Node.
 */
public class Node<E> {

    private E data;
    private Node<E> nextNode;
    private Node<E> previousNode;

    /**
     * Create a new Node object.
     *
     * @param data
     */
    public Node(E data) {
	this.data = data;
	this.nextNode = null;
	this.previousNode = null;
    }

    /**
     * @return The data in this Node.
     */
    public E getData() {
	return this.data;
    }

    /**
     * @param data This Node's new data element.
     */
    public void setData(E data) {
	this.data = data;
    }

    /**
     * @return This Node's next Node.
     */
    public Node<E> getNextNode() {
	return this.nextNode;
    }

    /**
     * @param nextNode This Node's reference to next Node.
     */
    public void setNextNode(Node<E> nextNode) {
	this.nextNode = nextNode;
    }

    /**
     * @return This Node's previous Node.
     */
    public Node<E> getPreviousNode() {
	return this.previousNode;
    }

    /**
     * @param previousNode This Node's reference to previous Node.
     */
    public void setPreviousNode(Node<E> previousNode) {
	this.previousNode = previousNode;
    }
}
