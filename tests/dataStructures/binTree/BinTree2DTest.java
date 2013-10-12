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

    public void test_insert() {
	this.binTree.insert(new Point(30, 70),
		new BinTreeLeafNode<Point, String>(new Point(30, 70), "A"));

	this.binTree.insert(new Point(10, 45),
		new BinTreeLeafNode<Point, String>(new Point(10, 45), "B"));

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
