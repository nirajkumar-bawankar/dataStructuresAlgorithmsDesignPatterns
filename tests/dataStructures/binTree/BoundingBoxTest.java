package dataStructures.binTree;

import dataStructures.binTree.Point;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 11, 2013
 */
public class BoundingBoxTest extends junit.framework.TestCase {
    private BoundingBox boundingBox;

    public void setUp() {
	this.boundingBox = new BoundingBox(new Point(0.0, 0.0), 100.0, 100.0);
    }

    public void test_getCurrentXAxis() {
	assertEquals(50.0, this.boundingBox.getCurrentXAxis());
    }

    public void test_getCurrentYAxis() {
	assertEquals(50.0, this.boundingBox.getCurrentYAxis());
    }

    public void test_changeToLeftAndRightHalfBoundingBox() {
	assertEquals(50.0, this.boundingBox.getCurrentXAxis());
	this.boundingBox.changeToLeftHalfBoundingBox();
	assertEquals(25.0, this.boundingBox.getCurrentXAxis());

	this.boundingBox.changeToLeftHalfBoundingBox();
	assertEquals(12.5, this.boundingBox.getCurrentXAxis());

	this.boundingBox.changeToRightHalfBoundingBox();
	assertEquals(18.75, this.boundingBox.getCurrentXAxis());

	this.boundingBox.changeToRightHalfBoundingBox();
	assertEquals(21.875, this.boundingBox.getCurrentXAxis());
    }

    public void test_changeToBottomAndTopHalfBoundingBox() {
	assertEquals(50.0, this.boundingBox.getCurrentYAxis());
	this.boundingBox.changeToBottomHalfBoundingBox();
	assertEquals(25.0, this.boundingBox.getCurrentYAxis());

	this.boundingBox.changeToBottomHalfBoundingBox();
	assertEquals(12.5, this.boundingBox.getCurrentYAxis());

	this.boundingBox.changeToTopHalfBoundingBox();
	assertEquals(18.75, this.boundingBox.getCurrentYAxis());

	this.boundingBox.changeToTopHalfBoundingBox();
	assertEquals(21.875, this.boundingBox.getCurrentYAxis());
    }
}
