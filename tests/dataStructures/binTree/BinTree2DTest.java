package dataStructures.binTree;

import dataStructures.binTree.Point;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 12, 2013
 */
public class BinTree2DTest extends junit.framework.TestCase {
    private BinTree2D<Point, String> binTree;

    public void setUp() {
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

	this.binTree.insert(new Point(10.0, 45.0), "A");
	assertEquals("A 10.0 45.0",
		this.binTree.preorderTraversal(this.binTree.getRootNode())
			.trim());

	this.binTree.insert(new Point(30.0, 70.0), "B");
	assertEquals("I\nI\nA 10.0 45.0\nB 30.0 70.0\nE",
		this.binTree.preorderTraversal(this.binTree.getRootNode())
			.trim());

	this.binTree.insert(new Point(52.0, 65.0), "C");
	assertEquals("I\nI\nA 10.0 45.0\nB 30.0 70.0\nC 52.0 65.0",
		this.binTree.preorderTraversal(this.binTree.getRootNode())
			.trim());

	// now we are going to add last element D that will dramatically change
	// rootNodes right subtree since it's (x, y) position is very close to
	// element C's (x, y) position
//	this.binTree.insert(new Point(55.0, 90.0), "D");
//	assertEquals(
//		"I\nI\nA 10.0 45.0\nB 30.0 70.0\nI\nE\nI\nI\nC 52.0 65.0\nD 55.0 90.0\nE",
//		this.binTree.preorderTraversal(this.binTree.getRootNode())
//			.trim());
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
			30.0, 70.0), "B"));

	assertEquals("I\nI\nA 10.0 45.0\nB 30.0 70.0\nE", this.binTree
		.preorderTraversal(this.binTree.getRootNode()).trim());

	// Set C & D correctly in right subtree of rootNode by first creating
	// the correct internal nodes to get to C & D
	internalRootNode.setRightChild(new BinTreeInternalNode<String>());

	BinTreeInternalNode<String> internalRootNodeRightChild = (BinTreeInternalNode<String>) internalRootNode
		.getRightChild();

	assertEquals("I\nI\nA 10.0 45.0\nB 30.0 70.0\nI\nE\nE", this.binTree
		.preorderTraversal(this.binTree.getRootNode()).trim());

	internalRootNodeRightChild
		.setRightChild(new BinTreeInternalNode<String>());

	assertEquals("I\nI\nA 10.0 45.0\nB 30.0 70.0\nI\nE\nI\nE\nE",
		this.binTree.preorderTraversal(this.binTree.getRootNode())
			.trim());

	BinTreeInternalNode<String> internalRootNodeRightRightChild = (BinTreeInternalNode<String>) internalRootNodeRightChild
		.getRightChild();

	internalRootNodeRightRightChild
		.setLeftChild(new BinTreeInternalNode<String>());

	assertEquals("I\nI\nA 10.0 45.0\nB 30.0 70.0\nI\nE\nI\nI\nE\nE\nE",
		this.binTree.preorderTraversal(this.binTree.getRootNode())
			.trim());

	BinTreeInternalNode<String> internalRootNodeRightRightLeftChild = (BinTreeInternalNode<String>) internalRootNodeRightRightChild
		.getLeftChild();

	internalRootNodeRightRightLeftChild
		.setLeftChild(new BinTreeLeafNode<Point, String>(new Point(
			52.0, 65.0), "C"));

	internalRootNodeRightRightLeftChild
		.setRightChild(new BinTreeLeafNode<Point, String>(new Point(
			55.0, 90.0), "D"));

	assertEquals(
		"I\nI\nA 10.0 45.0\nB 30.0 70.0\nI\nE\nI\nI\nC 52.0 65.0\nD 55.0 90.0\nE",
		this.binTree.preorderTraversal(this.binTree.getRootNode())
			.trim());
    }
}
