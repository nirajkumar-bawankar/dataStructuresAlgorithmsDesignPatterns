package DataStructures;

/**
 * To view an interactive web page about doubly linked list node implementation
 * visit: http://algoviz.org/OpenDSA/Books/CS3114PM/html/ListDouble.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 28, 2013
 * @param <E>
 */
class DoublyLinkedListNode<E> {
    private E value;
    private DoublyLinkedListNode<E> nextNode;
    private DoublyLinkedListNode<E> previousNode;

    /**
     * Create a new DoublyLinkedListNode object.
     *
     * @param value
     * @param previousNode
     * @param nextNode
     */
    DoublyLinkedListNode(E value, DoublyLinkedListNode<E> previousNode,
	    DoublyLinkedListNode<E> nextNode) {
	this.value = value;
	this.previousNode = previousNode;
	this.nextNode = nextNode;
    }

    E getValue() {
	return this.value;
    }

    void setValue(E value) {
	this.value = value;
    }

    DoublyLinkedListNode<E> getNextNode() {
	return this.nextNode;
    }

    void setNextNode(DoublyLinkedListNode<E> nextNode) {
	this.nextNode = nextNode;
    }

    DoublyLinkedListNode<E> getPreviousNode() {
	return this.previousNode;
    }

    void setPreviousNode(DoublyLinkedListNode<E> previousNode) {
	this.previousNode = previousNode;
    }
}
