package dataStructures.binTree;

import dataStructures.binTree.Point;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 12, 2013
 */
public class BoundingBox {
    private Point bottomLeft;
    private double width;
    private double height;

    /**
     * Create a new BoundingBox object.
     *
     * @param bottomLeft
     * @param width
     * @param height
     */
    public BoundingBox(Point bottomLeft, double width, double height) {
	this.bottomLeft = bottomLeft;
	this.width = width;
	this.height = height;
    }

    /**
     * @return the x midpoint between the smallest x coordinate and greatest x
     *         coordinate of the current state of the box.
     */
    public double getCurrentMidpointOfBoxAlongXAxis() {
	double leftMostXPosition = this.bottomLeft.getX();
	double rightMostXPosition = leftMostXPosition + this.width;
	return (leftMostXPosition + rightMostXPosition) / 2;
    }

    /**
     * @return the y midpoint between the smallest y coordinate and greatest y
     *         coordinate of the current state of the box.
     */
    public double getCurrentMidpointOfBoxAlongYAxis() {
	double bottomMostYPosition = this.bottomLeft.getY();
	double topMostYPosition = bottomMostYPosition + this.height;
	return (bottomMostYPosition + topMostYPosition) / 2;
    }

    /**
     * Divide the current bounding box by half along the x-axis and change the
     * current bounding box to the left half.
     */
    public void changeToLeftHalfBoundingBox() {
	this.width = this.width / 2;
    }

    /**
     * Divide the current bounding box by half along the x-axis and change the
     * current bounding box to the right half.
     */
    public void changeToRightHalfBoundingBox() {
	this.width = this.width / 2;
	double newXPosition = this.bottomLeft.getX() + this.width;
	this.bottomLeft.setX(newXPosition);
	// do not need to change y
    }

    /**
     * Divide the current bounding box by half along the y-axis and change the
     * current bounding box to the top half.
     */
    public void changeToTopHalfBoundingBox() {
	this.height = this.height / 2;
	double newYPosition = this.bottomLeft.getY() + this.height;
	this.bottomLeft.setY(newYPosition);
	// do not need to change x
    }

    /**
     * Divide the current bounding box by half along the y-axis and change the
     * current bounding box to the bottom half.
     */
    public void changeToBottomHalfBoundingBox() {
	this.height = this.height / 2;
    }

    Point getBottomLeftPoint() {
	return this.bottomLeft;
    }

    double getWidth() {
	return this.width;
    }

    double getHeight() {
	return this.height;
    }
}
