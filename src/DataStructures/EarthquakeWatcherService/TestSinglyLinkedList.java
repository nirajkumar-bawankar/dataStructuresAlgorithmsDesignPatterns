package DataStructures.EarthquakeWatcherService;

/**
 * Tests all methods within class SinglyLinkedList.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 8, 2013
 */
public class TestSinglyLinkedList extends junit.framework.TestCase {
    private SinglyLinkedList<Integer> linkedList;

    public void setUp() {
	this.linkedList = new SinglyLinkedList<Integer>();
    }

    public void test_insert() {
	this.linkedList.insert(0);
	assertEquals("< | 0 >", this.linkedList.toString());
	this.linkedList.insert(1);
	assertEquals("< | 1 0 >", this.linkedList.toString());
    }

    public void test_append() {
	this.linkedList.insert(0);
	this.linkedList.append(1);
	assertEquals("< | 0 1 >", this.linkedList.toString());
    }

    public void test_remove() {
	assertNull(this.linkedList.remove());

	this.linkedList.append(0);
	this.linkedList.append(1);
	this.linkedList.append(2);
	this.linkedList.append(3);
	assertEquals("< | 0 1 2 3 >", this.linkedList.toString());
	this.linkedList.remove();
	assertEquals("< | 0 2 3 >", this.linkedList.toString());
    }

    public void test_clear() {
	this.linkedList.append(0);
	this.linkedList.append(1);
	assertEquals(2, this.linkedList.length());

	this.linkedList.clear();
	assertEquals(0, this.linkedList.length());
    }

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

    public void test_previousAndNext() {
	assertFalse(this.linkedList.previous());
	assertFalse(this.linkedList.next());
	this.linkedList.append(0);
	assertTrue(this.linkedList.next());
	assertTrue(this.linkedList.previous());
    }

    public void test_length() {
	this.linkedList.insert(1);
	this.linkedList.insert(2);
	this.linkedList.insert(3);
	assertEquals(3, this.linkedList.length());
    }

    public void test_currentPosition() {
	this.linkedList.insert(1);
	this.linkedList.insert(2);
	assertEquals(0, this.linkedList.currentPosition());
	this.linkedList.next();
	assertEquals(1, this.linkedList.currentPosition());
	this.linkedList.next();
	assertEquals(2, this.linkedList.currentPosition());
    }

    public void test_moveCurrentNodeToPosition() {
	this.linkedList.insert(0); // position 3
	this.linkedList.insert(1); // position 2
	this.linkedList.insert(2); // position 1
	this.linkedList.insert(3); // position 0
	try {
	    this.linkedList.moveCurrentNodeToPosition(5);
	    fail("should've thrown an exception!");
	} catch (IllegalArgumentException expected) {
	    assertEquals("In method moveToPosition of class "
		    + "SinglyLinkedList the input node postion to be "
		    + "removed is out of bounds", expected.getMessage());
	}

	this.linkedList.moveCurrentNodeToPosition(3);
	assertEquals(0, (int) this.linkedList.getValue());

	this.linkedList.moveCurrentNodeToPosition(1);
	assertEquals(2, (int) this.linkedList.getValue());
    }

    public void test_getValue() {
	assertNull(this.linkedList.getValue());
	this.linkedList.insert(2);
	assertEquals(2, (int) this.linkedList.getValue());
    }

    public void test_findValuePosition() {
	this.linkedList.insert(1); // position 2
	assertEquals(-1, this.linkedList.findValuePosition(2));
	this.linkedList.insert(2); // position 1
	this.linkedList.insert(3); // position 0
	assertEquals(1, this.linkedList.findValuePosition(2));
    }

    public void test_toString() {
	this.linkedList.append(1);
	this.linkedList.append(2);
	this.linkedList.append(3);
	this.linkedList.append(4);
	this.linkedList.append(5);
	this.linkedList.append(6);
	this.linkedList.moveCurrentNodeToPosition(4);
	assertEquals("< 1 2 3 4 | 5 6 >", this.linkedList.toString());
    }
}
