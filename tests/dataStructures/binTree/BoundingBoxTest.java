package dataStructures.binTree;

import dataStructures.binTree.Point;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 12, 2013
 */
public class BoundingBoxTest extends junit.framework.TestCase {
    private BoundingBox boundingBox;

    public void setUp() {
	this.boundingBox = new BoundingBox(new Point(0.0, 0.0), 100.0, 100.0);
    }

    /**
     * Assert initial midpoint calculations for bounding box is correct for
     * x-axis.
     */
    public void test_getCurrentMidpointOfBoxAlongXAxis() {
	assertEquals(50.0, this.boundingBox.getCurrentMidpointOfBoxAlongXAxis());
    }

    /**
     * Assert initial midpoint calculations for bounding box is correct for
     * y-axis.
     */
    public void test_getCurrentMidpointOfBoxAlongYAxis() {
	assertEquals(50.0, this.boundingBox.getCurrentMidpointOfBoxAlongYAxis());
    }

    /**
     * Assert that the current bounding box can change to left box 2 times then
     * change to right box 2 times correctly.
     */
    public void test_changeToLeftAndRightHalfBoundingBox() {
	assertEquals(50.0, this.boundingBox.getCurrentMidpointOfBoxAlongXAxis());
	this.boundingBox.changeToLeftHalfBoundingBox();
	assertEquals(25.0, this.boundingBox.getCurrentMidpointOfBoxAlongXAxis());

	this.boundingBox.changeToLeftHalfBoundingBox();
	assertEquals(12.5, this.boundingBox.getCurrentMidpointOfBoxAlongXAxis());

	this.boundingBox.changeToRightHalfBoundingBox();
	assertEquals(18.75, this.boundingBox.getCurrentMidpointOfBoxAlongXAxis());

	this.boundingBox.changeToRightHalfBoundingBox();
	assertEquals(21.875, this.boundingBox.getCurrentMidpointOfBoxAlongXAxis());
    }

    /**
     * Assert that the current bounding box can change to bottom box 2 times
     * then change to top box 2 times correctly.
     */
    public void test_changeToBottomAndTopHalfBoundingBox() {
	assertEquals(50.0, this.boundingBox.getCurrentMidpointOfBoxAlongYAxis());
	this.boundingBox.changeToBottomHalfBoundingBox();
	assertEquals(25.0, this.boundingBox.getCurrentMidpointOfBoxAlongYAxis());

	this.boundingBox.changeToBottomHalfBoundingBox();
	assertEquals(12.5, this.boundingBox.getCurrentMidpointOfBoxAlongYAxis());

	this.boundingBox.changeToTopHalfBoundingBox();
	assertEquals(18.75, this.boundingBox.getCurrentMidpointOfBoxAlongYAxis());

	this.boundingBox.changeToTopHalfBoundingBox();
	assertEquals(21.875, this.boundingBox.getCurrentMidpointOfBoxAlongYAxis());
    }

    /**
     * Assert that the current bounding box can change to all directions left,
     * right, top, and bottom correctly.
     */
    public void test_changeBoundingBoxInAllDirections() {
	assertEquals(50.0, this.boundingBox.getCurrentMidpointOfBoxAlongXAxis());
	assertEquals(50.0, this.boundingBox.getCurrentMidpointOfBoxAlongYAxis());

	this.boundingBox.changeToRightHalfBoundingBox();
	assertEquals(75.0, this.boundingBox.getCurrentMidpointOfBoxAlongXAxis());
	assertEquals(50.0, this.boundingBox.getCurrentMidpointOfBoxAlongYAxis());

	this.boundingBox.changeToTopHalfBoundingBox();
	assertEquals(75.0, this.boundingBox.getCurrentMidpointOfBoxAlongXAxis());
	assertEquals(75.0, this.boundingBox.getCurrentMidpointOfBoxAlongYAxis());

	this.boundingBox.changeToLeftHalfBoundingBox();
	assertEquals(62.5, this.boundingBox.getCurrentMidpointOfBoxAlongXAxis());
	assertEquals(75.0, this.boundingBox.getCurrentMidpointOfBoxAlongYAxis());

	this.boundingBox.changeToBottomHalfBoundingBox();
	assertEquals(62.5, this.boundingBox.getCurrentMidpointOfBoxAlongXAxis());
	assertEquals(62.5, this.boundingBox.getCurrentMidpointOfBoxAlongYAxis());
    }
}
