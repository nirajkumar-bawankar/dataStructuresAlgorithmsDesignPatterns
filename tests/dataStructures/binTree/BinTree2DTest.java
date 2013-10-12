package dataStructures.binTree;

import dataStructures.binTree.Point;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 9, 2013
 */
public class BinTree2DTest extends junit.framework.TestCase {
    @SuppressWarnings("rawtypes")
    private BinTree2D<Point, BinTreeNode> binTree;

    @SuppressWarnings("rawtypes")
    public void setUp() {
	// TODO: make this range generic has a range in the x-axis of 0.0 to
	// 360.0 y-axis from 0.0 to 180.0
	this.binTree = new BinTree2D<Point, BinTreeNode>(0.0, 100.0, 0.0, 100.0);
    }

    /**
     * Please refer to the images at the 2 following links while trying to
     * understand the bin tree insert method as it is very confusing: Figure 1 @:
     * Figure 2 @:
     */
    public void test_insert() {
	this.binTree.insert(new Point(30, 70),
		new BinTreeLeafNode<Point, String>(new Point(30, 70), "A"));

	this.binTree.insert(new Point(10, 45),
		new BinTreeLeafNode<Point, String>(new Point(10, 45), "B"));

	assertTrue(this.binTree.getRootNode() instanceof BinTreeInternalNode<?>);
	assertTrue(((BinTreeInternalNode<?>) this.binTree.getRootNode())
		.getLeftChild() instanceof BinTreeInternalNode<?>);
	assertTrue(((BinTreeInternalNode<?>) this.binTree.getRootNode())
		.getRightChild() instanceof BinTreeEmptyNode<?>);

	// assert element B was inserted at the correct leaf node position
	assertEquals(
		"\n====Bin Tree Leaf Node====\nkey: (x, y) = (10.0, 45.0)\n   element: B"
			+ "\n==========================",
		((BinTreeLeafNode<Point, ?>) ((BinTreeInternalNode<?>) ((BinTreeInternalNode<?>) this.binTree
			.getRootNode()).getLeftChild()).getLeftChild())
			.getElement().toString());

	// assert element A was moved to the correct leaf node position
	assertEquals(
		"\n====Bin Tree Leaf Node====\nkey: (x, y) = (30.0, 70.0)\n   element: A"
			+ "\n==========================",
		((BinTreeLeafNode<Point, ?>) ((BinTreeInternalNode<?>) ((BinTreeInternalNode<?>) this.binTree
			.getRootNode()).getLeftChild()).getRightChild())
			.getElement().toString());

	this.binTree.insert(new Point(55, 90),
		new BinTreeLeafNode<Point, String>(new Point(55, 90), "C"));

	// assert element C was inserted at the correct leaf node positions
	assertEquals(
		"\n====Bin Tree Leaf Node====\nkey: (x, y) = (55.0, 90.0)\n   element: C"
			+ "\n==========================",
		((BinTreeLeafNode<Point, ?>) ((BinTreeInternalNode<?>) this.binTree
			.getRootNode()).getRightChild()).getElement()
			.toString());

	// assert element A & B did not move from their correct leaf node
	// positions
	assertEquals(
		"\n====Bin Tree Leaf Node====\nkey: (x, y) = (10.0, 45.0)\n   element: B"
			+ "\n==========================",
		((BinTreeLeafNode<Point, ?>) ((BinTreeInternalNode<?>) ((BinTreeInternalNode<?>) this.binTree
			.getRootNode()).getLeftChild()).getLeftChild())
			.getElement().toString());
	assertEquals(
		"\n====Bin Tree Leaf Node====\nkey: (x, y) = (30.0, 70.0)\n   element: A"
			+ "\n==========================",
		((BinTreeLeafNode<Point, ?>) ((BinTreeInternalNode<?>) ((BinTreeInternalNode<?>) this.binTree
			.getRootNode()).getLeftChild()).getRightChild())
			.getElement().toString());

	// now we are going to add last element D that will dramatically change
	// rootNodes right subtree since it's (x, y) position is very close to
	// element C's (x, y) position
	this.binTree.insert(new Point(52, 65),
		new BinTreeLeafNode<Point, String>(new Point(52, 65), "D"));
	System.out.println(this.binTree.getRootNode().toString());
    }

    public void test_remove() {

    }

    public void test_removeRandomElement() {

    }

    public void test_find() {

    }

    public void test_clear() {

    }

    public void test_size() {

    }

    public void test_preorderTraversal() {

    }
}
