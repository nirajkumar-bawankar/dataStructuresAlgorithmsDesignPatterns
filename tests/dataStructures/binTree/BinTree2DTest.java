package dataStructures.binTree;

import dataStructures.binTree.Point;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 9, 2013
 */
public class BinTree2DTest extends junit.framework.TestCase {
    @SuppressWarnings("rawtypes")
    private BinTree2D<Point, String> binTree;

    @SuppressWarnings("rawtypes")
    public void setUp() {
	// TODO: make this range generic has a range in the x-axis of 0.0 to
	// 360.0 y-axis from 0.0 to 180.0
	this.binTree = new BinTree2D<Point, String>(0.0, 100.0, 0.0, 100.0);
    }

    /**
     * Please refer to the images at the 2 following links while trying to
     * understand the bin tree insert method as it is very confusing: Figure 1 @:
     * Figure 2 @:
     */
    public void test_insert() {
	assertEquals("E",
		this.binTree.preorderTraversal(this.binTree.getRootNode())
			.trim());

	this.binTree.insert(new Point(10, 45), "A");
	assertEquals("A 10.0 45.0",
		this.binTree.preorderTraversal(this.binTree.getRootNode())
			.trim());

	this.binTree.insert(new Point(30, 70), "B");

	this.binTree.insert(new Point(52, 65), "C");

	// now we are going to add last element D that will dramatically change
	// rootNodes right subtree since it's (x, y) position is very close to
	// element C's (x, y) position
	this.binTree.insert(new Point(55, 90), "D");

	System.out.println(this.binTree.preorderTraversal(
		this.binTree.getRootNode()).toString());
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

    /**
     * Before asserting the preorder traversal printout is correct, the bin tree
     * is first correclty constructed WITHOUT using the bin tree's insert method
     * to prevent the possibility that an incorreclty implemented BinTree insert
     * method will create an incorrect preorder print out.
     */
    public void test_preorderTraversal() {
	assertEquals("E",
		this.binTree.preorderTraversal(this.binTree.getRootNode())
			.trim());

	BinTreeNode<String> rootNode = new BinTreeInternalNode<String>();
	this.binTree.setRootNode(rootNode);

	assertEquals("I\nE\nE",
		this.binTree.preorderTraversal(this.binTree.getRootNode())
			.trim());

	BinTreeInternalNode<String> internalRootNode = ((BinTreeInternalNode<String>) rootNode);
	// Set A & B correctly in left subtree of rootNode
	internalRootNode.setLeftChild(new BinTreeInternalNode<String>());

	BinTreeInternalNode<String> internalRootNodeLeftChild = (BinTreeInternalNode<String>) internalRootNode
		.getLeftChild();

	assertEquals("I\nI\nE\nE\nE",
		this.binTree.preorderTraversal(this.binTree.getRootNode())
			.trim());

	internalRootNodeLeftChild
		.setLeftChild(new BinTreeLeafNode<Point, String>(new Point(
			10.0, 45.0), "A"));
	internalRootNodeLeftChild
		.setRightChild(new BinTreeLeafNode<Point, String>(new Point(
			10.0, 45.0), "B"));

	// Set C & D correctly in right subtree of rootNode
	internalRootNode.setRightChild(new BinTreeInternalNode<String>());

	BinTreeInternalNode<String> rootNodeRightChild = (BinTreeInternalNode<String>) internalRootNode
		.getRightChild();
    }
}
