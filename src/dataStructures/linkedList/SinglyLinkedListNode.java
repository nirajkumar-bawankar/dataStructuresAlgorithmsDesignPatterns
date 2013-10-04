package dataStructures.linkedList;

/**
 * To view an interactive web page about linked list implementation visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/ListArray.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 1, 2013
 * @param <E>
 */
public class SinglyLinkedListNode<E> {
    private E value; // for this node
    private SinglyLinkedListNode<E> nextNode;

    /**
     * Create a new Link object with a value and reference to next node.
     *
     * @param value
     * @param nextNode
     */
    public SinglyLinkedListNode(E value, SinglyLinkedListNode<E> nextNode) {
	this.value = value;
	this.nextNode = nextNode;
    }

    /**
     * @return The value for this node.
     */
    public E getValue() {
	return this.value;
    }

    /**
     * @param value
     *            The new value for this node.
     */
    public void setValue(E value) {
	this.value = value;
    }

    /**
     * @return The node after this node.
     */
    public SinglyLinkedListNode<E> getNextNode() {
	return this.nextNode;
    }

    /**
     * @param nextNode
     *            The new next node for this current node.
     */
    public void setNextNode(SinglyLinkedListNode<E> nextNode) {
	this.nextNode = nextNode;
    }
}
