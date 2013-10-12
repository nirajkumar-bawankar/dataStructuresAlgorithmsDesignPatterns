package dataStructures.binTree;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 11, 2013
 */
public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
	this.x = x;
	this.y = y;
    }

    public double getX() {
	return this.x;
    }

    public double getY() {
	return this.y;
    }

    public void setX(double x) {
	this.x = x;
    }

    public void setY(double y) {
	this.y = y;
    }

    public String toString() {
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("(x, y) = ");
	stringBuilder.append("(" + this.x + ", " + this.y + ")");
	String pointInformation = stringBuilder.toString();
	return pointInformation;
    }
}
