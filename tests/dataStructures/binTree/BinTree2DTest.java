package dataStructures.binTree;

import java.awt.Point;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 9, 2013
 */
public class BinTree2DTest extends junit.framework.TestCase {
    private BinTree2D<Point, BinTreeNode> binTree;

    public void setUp() {
	// TODO: make this range generic has a range in the x-axis of 0.0 to
	// 360.0 y-axis from 0.0 to 180.0
	this.binTree = new BinTree2D<Point, BinTreeNode>(0.0, 100.0, 0.0, 100.0);
    }

    public void test_insert() {
	this.binTree
		.insert(new Point(30, 70), new BinTreeLeafNode<String>("A"));
	assertEquals(1, this.binTree.size());

	this.binTree
	 .insert(new Point(10, 45), new BinTreeLeafNode<String>("B"));
	assertEquals(2, this.binTree.size());
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
