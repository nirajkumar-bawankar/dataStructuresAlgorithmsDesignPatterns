package dataStructures.binTree;

import dataStructures.binTree.Point;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 11, 2013
 */
public class BoundingBox {
    private Point bottomLeft;
    private double width;
    private double height;

    public BoundingBox(Point bottomLeft, double width, double height) {
	this.bottomLeft = bottomLeft;
	this.width = width;
	this.height = height;
    }

    /**
     * @return the x midpoint between the smallest x coordinate and greatest x
     *         coordinate of the current state of the box.
     */
    public double getCurrentXAxis() {
	double leftMostXPosition = this.bottomLeft.getX();
	double rightMostXPosition = leftMostXPosition + this.width;
	return (leftMostXPosition + rightMostXPosition) / 2;
    }

    /**
     * @return the y midpoint between the smallest y coordinate and greatest y
     *         coordinate of the current state of the box.
     */
    public double getCurrentYAxis() {
	double bottomMostYPosition = this.bottomLeft.getY();
	double topMostYPosition = bottomMostYPosition + this.height;
	return (bottomMostYPosition + topMostYPosition) / 2;
    }

    public void changeToLeftHalfBoundingBox() {
	this.width = this.width / 2;
    }

    public void changeToRightHalfBoundingBox() {
	this.width = this.width / 2;
	double newXPosition = this.bottomLeft.getX() + this.width;
	this.bottomLeft.setX(newXPosition);
	// do not need to change y
    }

    public void changeToTopHalfBoundingBox() {
	this.height = this.height / 2;
	double newYPosition = this.bottomLeft.getY() + this.height;
	this.bottomLeft.setY(newYPosition);
	// do not need to change x
    }

    public void changeToBottomHalfBoundingBox() {
	this.height = this.height / 2;
    }
}
