package DataStructures;

/**
 * To view an interactive web page about linked list implementation visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/ListArray.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 1, 2013
 * @param <E>
 */
class SinglyLinkedListNode<E> {
    private E value; // for this node
    private SinglyLinkedListNode<E> nextNode;

    /**
     * Create a new Link object with a value and reference to next node.
     *
     * @param value
     * @param nextNode
     */
    SinglyLinkedListNode(E value, SinglyLinkedListNode<E> nextNode) {
	this.value = value;
	this.nextNode = nextNode;
    }

    /**
     * @return The value for this node.
     */
    E getValue() {
	return this.value;
    }

    /**
     * @param value
     *            The new value for this node.
     */
    void setValue(E value) {
	this.value = value;
    }

    /**
     * @return The node after this node.
     */
    SinglyLinkedListNode<E> getNextNode() {
	return this.nextNode;
    }

    /**
     * @param nextNode
     *            The new next node for this current node.
     */
    void setNextNode(SinglyLinkedListNode<E> nextNode) {
	this.nextNode = nextNode;
    }
}
