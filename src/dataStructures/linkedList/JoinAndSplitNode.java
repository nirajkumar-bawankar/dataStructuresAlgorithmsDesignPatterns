package dataStructures.linkedList;

/**
 * A "node" represents a single element in a sequence. Think of it like a like
 * in a chain. The node contains an element of data and reference to the next
 * element in the sequence.
 *
 * This node is "doubly linked". It has references to the next node in the chain
 * and the previous node in the chain. The join and split methods manage both
 * connections simultaneously to ensure that the consistency of links in the
 * chain is preserved at all times.
 *
 * @param<T> the element type stored in the node
 *
 * @author Tony Allevato (allevato@vt.edu)
 * @version Oct 22, 2012
 */
public class JoinAndSplitNode<T> {
    private T data;
    private JoinAndSplitNode<T> next;
    private JoinAndSplitNode<T> previous;

    /**
     * Create a new Node object with the specified data.
     *
     * @param data
     *            the data for the node
     */
    public JoinAndSplitNode(T data) {
	this.data = data;
    }

    public T getData() {
	return this.data;
    }

    public void setData(T data) {
	this.data = data;
    }

    public JoinAndSplitNode<T> next() {
	return this.next;
    }

    public void setNext(JoinAndSplitNode<T> next) {
	this.next = next;
    }

    public JoinAndSplitNode<T> previous() {
	return this.previous;
    }

    public void setPrevious(JoinAndSplitNode<T> previous) {
	this.previous = previous;
    }

    /**
     * Joins this node to the specified node so that one passed as a parameter
     * follows this node. In other words, writing {@code A.join(B)} would create
     * the linkage A <-> B.
     *
     * An exception will be thrown if this node already has another node
     * following it, or if {@code newNext} already has another node preceding
     * it. In this case, you must <@link #split()} the nodes before you can join
     * it to something else.
     *
     * @param newNext
     *            the node that should follow this one
     * @return this node, so that you can chain methods like
     *         {@code A.join(B.join(C))}
     *
     * @throws IllegalStateException
     *             if there is already a node following this node or if there is
     *             already a node preceding {@code newNext}
     */
    public JoinAndSplitNode<T> join(JoinAndSplitNode<T> newNext) {
	if (this.next != null) {
	    throw new IllegalStateException(
		    "A node is already following this node");
	} else if (newNext != null && newNext.previous != null) {
	    throw new IllegalStateException(
		    "A node is already preceding the one passed to this method.");
	} else {
	    this.next = newNext;

	    if (newNext != null) {
		newNext.previous = this;
	    }
	    return this;
	}
    }

    /**
     * Splits this node from its follower and then returns the follower.
     *
     * @return the node that used to follow this node before they were split.
     */
    public JoinAndSplitNode<T> split() {
	JoinAndSplitNode<T> oldNext = this.next;

	if (this.next != null) {
	    this.next.previous = null;
	}
	this.next = null;
	return oldNext;
    }
}
