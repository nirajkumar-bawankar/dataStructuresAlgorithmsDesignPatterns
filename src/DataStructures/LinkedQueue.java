package DataStructures;

/**
 * To view an interactive web page about linked queue implementation visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/Queue.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 1, 2013
 */
public class LinkedQueue<E> implements QueueInterface<E> {
    private Link<E> front;
    private Link<E> rear;
    private int size;

    public LinkedQueue() {
	this.init();
    }

    private void init() {
	this.front = new Link<E>(null);
	this.rear = new Link<E>(null);
	this.size = 0;
    }

    @Override
    public void enqueue(E item) {
	this.rear.setNextNode(new Link<E>(item, null));
	this.rear = this.rear.getNextNode();
	this.size++;
    }

    @Override
    public E dequeue() {
	if (this.size == 0) {
	    throw new IllegalArgumentException("In method dequeue of class "
		    + "LinkedQueue the linked queue is empty can cannot"
		    + " be dequeued");
	}

	E item = this.front.getNextNode().getValue();
	this.front.setNextNode(this.front.getNextNode().getNextNode());

	if (this.front.getNextNode() == null) {
	    this.rear = this.front;
	}
	this.size--;
	return item;
    }

    @Override
    public E frontValue() {
	if (this.size == 0) {
	    throw new IllegalArgumentException("In method frontValue of class "
		    + "LinkedQueue the linked queue is empty");
	}
	return this.front.getNextNode().getValue();
    }

    @Override
    public int length() {
	return this.size;
    }

    @Override
    public void clear() {
	this.init();
    }

    /**
     * Creates a easy to read String representation of the linked queue's
     * contents.
     *
     * Example: < 1 2 3 4 5 6 >
     */
    public String toString() {
	StringBuffer linkedQueueAsString = new StringBuffer((this.length() + 1) * 4);
	linkedQueueAsString.append("< ");
	for (Link<E> node = this.front.getNextNode(); node != null; node = node.getNextNode()) {
	    linkedQueueAsString.append(node.getValue());
	    linkedQueueAsString.append(" ");
	}
	linkedQueueAsString.append(">");
	return linkedQueueAsString.toString();
    }
}
