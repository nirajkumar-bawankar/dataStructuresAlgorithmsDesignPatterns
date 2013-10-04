package dataStructures;

/**
 * Tests all logic within class LinkedQueue.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 1, 2013
 */
public class LinkedQueueTest extends junit.framework.TestCase {
    private LinkedQueue<Integer> queue;

    public void setUp() {
	this.queue = new LinkedQueue<Integer>();
	this.queue.enqueue(1);
	this.queue.enqueue(2);
	this.queue.enqueue(3);
    }

    /**
     * Assert all 3 elements were dequeued correctly from the queue and the
     * correct exception is thrown when dequeing an emtpy queue.
     */
    public void test_dequeue() {
	assertEquals(1, this.queue.frontValue().intValue());
	this.queue.dequeue();
	assertEquals(2, this.queue.frontValue().intValue());
	this.queue.dequeue();
	assertEquals(3, this.queue.frontValue().intValue());
	this.queue.dequeue();
	try {
	    this.queue.dequeue();
	    fail("should've thrown an exception!");
	} catch (IllegalStateException expected) {
	    assertEquals("In method dequeue of class "
		    + "LinkedQueue the linked queue is empty can cannot"
		    + " be dequeued", expected.getMessage());
	}
    }

    /**
     * Assert the front value method returns the correct front value on a
     * non-empty queue and throws the correct exception when called on an empty
     * queue.
     */
    public void test_frontValue() {
	assertEquals(1, this.queue.frontValue().intValue());
	this.queue.dequeue();
	this.queue.dequeue();
	this.queue.dequeue();
	try {
	    this.queue.frontValue();
	    fail("should've thrown an exception!");
	} catch (IllegalStateException expected) {
	    assertEquals("In method frontValue of class "
		    + "LinkedQueue the linked queue is empty",
		    expected.getMessage());
	}
    }

    /**
     * Assert length works correctly after dequeue and enqueue calls.
     */
    public void test_length() {
	assertEquals(3, this.queue.length());
	this.queue.dequeue();
	assertEquals(2, this.queue.length());
	this.queue.enqueue(4);
	assertEquals(3, this.queue.length());
    }

    /**
     * Assert correct String representation was generated on a queue with
     * elements.
     */
    public void test_toString() {
	this.queue.toString();
	assertEquals("< 1 2 3 >", this.queue.toString());
    }
}
