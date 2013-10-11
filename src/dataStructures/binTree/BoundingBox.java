package dataStructures.binTree;

import java.awt.Point;

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

    public void changeToLeftBoundingBox() {
	this.width = this.width / 2;
    }

    public void changeToRightBoundingBox() {
	this.width = this.width / 2;
	this.bottomLeft.setLocation(this.bottomLeft.getX() + this.width,
		this.bottomLeft.getY());
    }

    public void changeToUpperBoundingBox() {
	this.height = this.height / 2;
	this.bottomLeft.setLocation(this.bottomLeft.getX(),
		this.bottomLeft.getY() + this.height);
    }

    public void changeToLowerBoundingBox() {
	this.height = this.height / 2;
    }
}
