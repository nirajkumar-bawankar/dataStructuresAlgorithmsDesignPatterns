package DataStructures;

/**
 * To view an interactive web page about array-based list implementation visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/ListArray.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 1, 2013
 * @param <E>
 */
class Link<E> {
    private E value; // for this node
    private Link<E> nextNode;

    /**
     * Create a new Link object with a value and reference to next node.
     *
     * @param value
     * @param nextNode
     */
    Link(E value, Link<E> nextNode) {
	this.value = value;
	this.nextNode = nextNode;
    }

    /**
     * Create a new Link object without a value and reference to next node.
     *
     * @param nextNode
     */
    Link(Link<E> nextNode) {
	this.value = null;
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
    Link<E> getNextNode() {
	return this.nextNode;
    }

    /**
     * @param nextNode
     *            The new next node for this current node.
     */
    void setNextNode(Link<E> nextNode) {
	this.nextNode = nextNode;
    }
}
