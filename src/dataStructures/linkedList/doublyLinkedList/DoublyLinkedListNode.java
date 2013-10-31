package dataStructures.linkedList.doublyLinkedList;

/**
 * A generic doubly linked list Node class.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 30, 2013
 *
 * @param <E>
 *            The type of data stored in the Node.
 */
public class DoublyLinkedListNode<E> {

    private E data;
    private DoublyLinkedListNode<E> nextNode;
    private DoublyLinkedListNode<E> previousNode;

    /**
     * Create a new Node object.
     *
     * @param data
     */
    public DoublyLinkedListNode(E data) {
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
    public DoublyLinkedListNode<E> getNextNode() {
	return this.nextNode;
    }

    /**
     * @param nextNode This Node's reference to next Node.
     */
    public void setNextNode(DoublyLinkedListNode<E> nextNode) {
	this.nextNode = nextNode;
    }

    /**
     * @return This Node's previous Node.
     */
    public DoublyLinkedListNode<E> getPreviousNode() {
	return this.previousNode;
    }

    /**
     * @param previousNode This Node's reference to previous Node.
     */
    public void setPreviousNode(DoublyLinkedListNode<E> previousNode) {
	this.previousNode = previousNode;
    }
}
