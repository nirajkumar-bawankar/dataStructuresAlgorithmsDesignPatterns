package dataStructures.linkedList.LRULinkedList;

/**
 * Tests all logic within class LRULinkedList.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 26, 2013
 */
public class LRULinkedListTest extends junit.framework.TestCase {
    private LRULinkedList<Integer> LRUList;

    public void setUp() {
        this.LRUList = new LRULinkedList<Integer>(5);
    }

    /**
     *
     * 3 Cases:
     * 1) if element is new & list is not full append element to list.
     * 2) if element is new & list is full append element to list and remove
     * element after head.
     * 3) if element is duplicate, find element within list,
     * remove it, and append it to the list.
     *
     * 3 Cases return:
     * 1) if element is new & list is not full return null;
     * 2) if element is new & list is full return removed element(element after
     * head)
     * 3) if element is duplicate, return null.
     */
    public void test_Add7NumbersWithDuplicates() {
        assertNull(this.LRUList
                .insertNewElementOrPromoteIfElementIsAlreadyInside(new Integer(
                        1)));
        assertEquals(1, this.LRUList.getSize());
        assertEquals("< 1 > maxSize = 5", this.LRUList.toString());

        assertNull(this.LRUList
                .insertNewElementOrPromoteIfElementIsAlreadyInside(new Integer(
                        2)));
        assertEquals(2, this.LRUList.getSize());
        assertEquals("< 1 2 > maxSize = 5", this.LRUList.toString());

        assertNull(this.LRUList
                .insertNewElementOrPromoteIfElementIsAlreadyInside(new Integer(
                        3)));
        assertEquals(3, this.LRUList.getSize());
        assertEquals("< 1 2 3 > maxSize = 5", this.LRUList.toString());

        assertNull(this.LRUList
                .insertNewElementOrPromoteIfElementIsAlreadyInside(new Integer(
                        4)));
        assertEquals(4, this.LRUList.getSize());
        assertEquals("< 1 2 3 4 > maxSize = 5", this.LRUList.toString());

        assertNull(this.LRUList
                .insertNewElementOrPromoteIfElementIsAlreadyInside(new Integer(
                        5)));
        assertEquals(5, this.LRUList.getSize());
        assertEquals("< 1 2 3 4 5 > maxSize = 5", this.LRUList.toString());

        // now that the least recently used linked list is full, when a new
        // element in added to the linked list, the least recently used element
        // at the head of the linked list should be removed and returned.
        assertEquals(
                1,
                this.LRUList.insertNewElementOrPromoteIfElementIsAlreadyInside(
                        new Integer(6)).intValue());
        assertEquals(5, this.LRUList.getSize());
        assertEquals("< 2 3 4 5 6 > maxSize = 5", this.LRUList.toString());

        assertEquals(
                2,
                this.LRUList.insertNewElementOrPromoteIfElementIsAlreadyInside(
                        new Integer(7)).intValue());
        assertEquals(5, this.LRUList.getSize());
        assertEquals("< 3 4 5 6 7 > maxSize = 5", this.LRUList.toString());

        // now add a duplicate element and make sure it was moved to the head
        // of the list
        assertNull(this.LRUList
                .insertNewElementOrPromoteIfElementIsAlreadyInside(new Integer(
                        3)));
        assertEquals("< 4 5 6 7 3 > maxSize = 5", this.LRUList.toString());

        // try to add a duplicate element in the middle of the linked list
        assertNull(this.LRUList
                .insertNewElementOrPromoteIfElementIsAlreadyInside(new Integer(
                        6)));
        assertEquals("< 4 5 7 3 6 > maxSize = 5", this.LRUList.toString());

        // try to add a duplicate element in the middle of the linked list
        assertNull(this.LRUList
                .insertNewElementOrPromoteIfElementIsAlreadyInside(new Integer(
                        5)));
        assertEquals("< 4 7 3 6 5 > maxSize = 5", this.LRUList.toString());
    }

    public void test_removeLeastRecentlyUsedElement() {
	this.test_Add7NumbersWithDuplicates();
	assertEquals("< 4 7 3 6 5 > maxSize = 5", this.LRUList.toString());

	assertEquals(4, this.LRUList.removeLeastRecentlyUsedElement().intValue());
	assertEquals("< 7 3 6 5 > maxSize = 5", this.LRUList.toString());

	assertEquals(7, this.LRUList.removeLeastRecentlyUsedElement().intValue());
	assertEquals("< 3 6 5 > maxSize = 5", this.LRUList.toString());

	assertEquals(3, this.LRUList.removeLeastRecentlyUsedElement().intValue());
	assertEquals("< 6 5 > maxSize = 5", this.LRUList.toString());

	assertEquals(6, this.LRUList.removeLeastRecentlyUsedElement().intValue());
	assertEquals("< 5 > maxSize = 5", this.LRUList.toString());

	assertEquals(5, this.LRUList.removeLeastRecentlyUsedElement().intValue());
	assertEquals("< > maxSize = 5", this.LRUList.toString());

	assertNull(this.LRUList.removeLeastRecentlyUsedElement());
    }
}
