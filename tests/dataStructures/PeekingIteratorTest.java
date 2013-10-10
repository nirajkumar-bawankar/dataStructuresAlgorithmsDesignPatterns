package dataStructures;

import dataStructures.PeekingIterator;

import java.util.NoSuchElementException;

import java.util.LinkedList;
import java.util.Collection;

/**
 * Tests methods within class PeekingIterator.
 */
public class PeekingIteratorTest extends junit.framework.TestCase {
    private Collection<Integer> linkedList = new LinkedList<Integer>();
    private PeekingIterator<Integer> peekingIterator;

    public void setUp() {
	for (int i = 0; i < 5; i++) {
	    this.linkedList.add(new Integer(i));
	}

	this.peekingIterator = new PeekingIterator<Integer>(this.linkedList);
    }

    public void iterateThroughLinkedListUsingHasNextGetNextAndPeek() {
	assertTrue(this.peekingIterator.hasNext());
	assertEquals(1, this.peekingIterator.getNext().intValue());
	assertEquals(2, this.peekingIterator.getNext().intValue());
	assertEquals(3, this.peekingIterator.peek().intValue());
	assertEquals(3, this.peekingIterator.peek().intValue());
	assertEquals(4, this.peekingIterator.getNext().intValue());
	assertEquals(5, this.peekingIterator.peek().intValue());
	assertEquals(5, this.peekingIterator.peek().intValue());
	assertTrue(this.peekingIterator.hasNext());
	assertEquals(5, this.peekingIterator.peek().intValue());
	assertFalse(this.peekingIterator.hasNext());
    }

    public void testHasNext() {
	assertTrue(this.peekingIterator.hasNext());
	for (int i = 0; i < 5; i++) {
	    this.peekingIterator.getNext();
	}
	assertFalse(this.peekingIterator.hasNext());
    }

    public void testGetNext() {
	// iterate over all elements
	for (int i = 0; i < 5; i++) {
	    assertEquals(i, this.peekingIterator.getNext().intValue());
	}

	try {
	    this.peekingIterator.getNext();
	    fail("should've thrown an exception!");
	} catch (NoSuchElementException expected) {
	    assertEquals("In class PeekingIterator method getNext, there is no"
		    + "next element", expected.getMessage());
	}
    }

    public void testPeek() {
	// iterate over all elements
	assertEquals(0, this.peekingIterator.peek().intValue());
	for (int i = 0; i < 5; i++) {
	    assertEquals(i, this.peekingIterator.getNext().intValue());
	}

	try {
	    this.peekingIterator.peek();
	    fail("should've thrown an exception!");
	} catch (NoSuchElementException expected) {
	    assertNull(expected.getMessage());
	}
    }
}
