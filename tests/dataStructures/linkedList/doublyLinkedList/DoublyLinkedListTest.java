package dataStructures.linkedList.doublyLinkedList;

import dataStructures.linkedList.doublyLinkedList.DoublyLinkedList;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 30, 2013
 */
public class DoublyLinkedListTest extends junit.framework.TestCase {

    private DoublyLinkedList<Integer> doublyLinkedList;

    public void setUp() {
	this.doublyLinkedList = new DoublyLinkedList<Integer>();
    }

    public void test_append4NodesToLinkedList() {
	this.doublyLinkedList.appendNode(new DoublyLinkedListNode<Integer>(0));
	assertEquals("< 0 >", this.doublyLinkedList.toString());
	this.doublyLinkedList.appendNode(new DoublyLinkedListNode<Integer>(1));
	assertEquals("< 0 1 >", this.doublyLinkedList.toString());
	this.doublyLinkedList.appendNode(new DoublyLinkedListNode<Integer>(2));
	assertEquals("< 0 1 2 >", this.doublyLinkedList.toString());
	this.doublyLinkedList.appendNode(new DoublyLinkedListNode<Integer>(3));
	assertEquals("< 0 1 2 3 >", this.doublyLinkedList.toString());
    }

    public void test_findAndRemoveNodeByRemovingAnExistingNodeAndNonexistantNode() {
	this.test_append4NodesToLinkedList();
	assertEquals("< 0 1 2 3 >", this.doublyLinkedList.toString());
	assertEquals(2, this.doublyLinkedList.findAndRemoveNode(2).getData()
		.intValue());

	// there is no Node within the linked list with a data element of 4
	assertNull(this.doublyLinkedList.findAndRemoveNode(4));
    }

    public void test_removeFirstNodeByAdding4NodesAndRemovingAllOfThem() {
	this.test_append4NodesToLinkedList();
	assertEquals("< 0 1 2 3 >", this.doublyLinkedList.toString());

	assertEquals(0, this.doublyLinkedList.removeFirstNode().getData()
		.intValue());
	assertEquals("< 1 2 3 >", this.doublyLinkedList.toString());

	assertEquals(1, this.doublyLinkedList.removeFirstNode().getData()
		.intValue());
	assertEquals("< 2 3 >", this.doublyLinkedList.toString());

	assertEquals(2, this.doublyLinkedList.removeFirstNode().getData()
		.intValue());
	assertEquals("< 3 >", this.doublyLinkedList.toString());

	assertEquals(3, this.doublyLinkedList.removeFirstNode().getData()
		.intValue());
	assertEquals("< >", this.doublyLinkedList.toString());

	assertNull(this.doublyLinkedList.removeFirstNode());
    }

    public void test_getSizeByAdding4NodesAndRemovingAllThemAtTheHead() {
	this.test_append4NodesToLinkedList();
	assertEquals("< 0 1 2 3 >", this.doublyLinkedList.toString());
	assertEquals(4, this.doublyLinkedList.getSize());

	this.doublyLinkedList.removeFirstNode();
	assertEquals(3, this.doublyLinkedList.getSize());

	this.doublyLinkedList.removeFirstNode();
	assertEquals(2, this.doublyLinkedList.getSize());

	this.doublyLinkedList.removeFirstNode();
	assertEquals(1, this.doublyLinkedList.getSize());

	this.doublyLinkedList.removeFirstNode();
	assertEquals(0, this.doublyLinkedList.getSize());

	this.doublyLinkedList.removeFirstNode();
	assertEquals(0, this.doublyLinkedList.getSize());
    }
}
