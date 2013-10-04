package dataStructures;

import dataStructures.SinglyLinkedList;

/**
 * Tests all logic within class SinglyLinkedList.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 8, 2013
 */
public class SinglyLinkedListTest extends junit.framework.TestCase {
    private SinglyLinkedList<Integer> linkedList;

    public void setUp() {
	this.linkedList = new SinglyLinkedList<Integer>();
    }

    /**
     * Assert insert works with multiple numbers.
     */
    public void test_insert() {
	this.linkedList.insert(0);
	assertEquals("< | 0 >", this.linkedList.toString());
	this.linkedList.insert(1);
	assertEquals("< | 1 0 >", this.linkedList.toString());
    }

    /**
     * Assert append adds to the end of the list.
     */
    public void test_append() {
	this.linkedList.insert(0);
	this.linkedList.append(1);
	assertEquals("< | 0 1 >", this.linkedList.toString());
    }

    /**
     * Assert remove works on non-empty and empty lists.
     */
    public void test_remove() {
	assertNull(this.linkedList.remove());

	this.linkedList.append(0);
	this.linkedList.append(1);
	this.linkedList.append(2);
	this.linkedList.append(3);
	assertEquals("< | 0 1 2 3 >", this.linkedList.toString());
	this.linkedList.remove();
	assertEquals("< | 0 2 3 >", this.linkedList.toString());

	this.linkedList.remove();
	this.linkedList.remove();
	this.linkedList.remove();
	assertNull(this.linkedList.remove());
    }

    /**
     * Assert clear removes all elements in list.
     */
    public void test_clear() {
	this.linkedList.append(0);
	this.linkedList.append(1);
	assertEquals(2, this.linkedList.length());

	this.linkedList.clear();
	assertEquals(0, this.linkedList.length());
    }

    /**
     * Assert moveToStart and moveToEnd moves current position to null header
     * and tail node respectively.
     */
    public void test_moveToStartAndEnd() {
	this.linkedList.insert(1);
	this.linkedList.insert(2);
	this.linkedList.insert(3);
	assertEquals(3, (int) this.linkedList.getValue());
	this.linkedList.moveToEnd();
	assertEquals(null, this.linkedList.getValue());
	this.linkedList.moveToStart();
	assertEquals(3, (int) this.linkedList.getValue());
    }

    /**
     * Assert previous and next work for when their is a next and previous node
     * and when there is not a next or previous node.
     */
    public void test_previousAndNext() {
	assertFalse(this.linkedList.previous());
	assertFalse(this.linkedList.next());
	this.linkedList.append(0);
	assertTrue(this.linkedList.next());
	assertTrue(this.linkedList.previous());
    }

    /**
     * Assert length works after inserting and removing elements into and from
     * list.
     */
    public void test_length() {
	this.linkedList.insert(1);
	this.linkedList.insert(2);
	this.linkedList.insert(3);
	assertEquals(3, this.linkedList.length());
	this.linkedList.remove();
	assertEquals(2, this.linkedList.length());
    }

    /**
     * Assert current position is correctly changed.
     */
    public void test_currentPosition() {
	this.linkedList.insert(1);
	this.linkedList.insert(2);
	assertEquals(0, this.linkedList.currentPosition());
	this.linkedList.next();
	assertEquals(1, this.linkedList.currentPosition());
	this.linkedList.next();
	assertEquals(2, this.linkedList.currentPosition());
	this.linkedList.previous();
	assertEquals(1, this.linkedList.currentPosition());
    }

    /**
     * Assert the current node can be moved to any valid position and the
     * correct exception is thrown when the current node is attempted to be
     * moved to an invalid position.
     */
    public void test_moveCurrentNodeToPosition() {
	this.linkedList.insert(0); // position 3
	this.linkedList.insert(1); // position 2
	this.linkedList.insert(2); // position 1
	this.linkedList.insert(3); // position 0
	try {
	    this.linkedList.moveCurrentToPosition(5);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals("In method moveCurrentToPosition of class "
		    + "SinglyLinkedList the input node postion to be "
		    + "removed is out of bounds", expected.getMessage());
	}

	this.linkedList.moveCurrentToPosition(3);
	assertEquals(0, (int) this.linkedList.getValue());

	this.linkedList.moveCurrentToPosition(1);
	assertEquals(2, (int) this.linkedList.getValue());
    }

    /**
     * Assert getValue returns the value of the node at the current position.
     */
    public void test_getValue() {
	assertNull(this.linkedList.getValue());
	this.linkedList.insert(2);
	assertEquals(2, (int) this.linkedList.getValue());
    }

    /**
     * Assert the correct position is returned for values that exist in the
     * list. And -1 is returned for values that do not exist in the list.
     */
    public void test_findValuePosition() {
	this.linkedList.insert(1); // position 2
	assertEquals(-1, this.linkedList.findValuePosition(2));
	this.linkedList.insert(2); // position 1
	this.linkedList.insert(3); // position 0
	assertEquals(2, this.linkedList.findValuePosition(1));
	assertEquals(1, this.linkedList.findValuePosition(2));
	assertEquals(0, this.linkedList.findValuePosition(3));
    }

    /**
     * Assert the current position and node values are correctly represented by
     * toString.
     */
    public void test_toString() {
	this.linkedList.append(1);
	this.linkedList.append(2);
	this.linkedList.append(3);
	this.linkedList.append(4);
	this.linkedList.append(5);
	this.linkedList.append(6);
	this.linkedList.moveCurrentToPosition(4);
	assertEquals("< 1 2 3 4 | 5 6 >", this.linkedList.toString());
    }
}
