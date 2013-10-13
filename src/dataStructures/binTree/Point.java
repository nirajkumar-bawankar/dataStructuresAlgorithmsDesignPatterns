package dataStructures.binTree;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 12, 2013
 */
public class Point {
    private double x;
    private double y;

    /**
     * Create a new Point object.
     *
     * @param x
     * @param y
     */
    public Point(double x, double y) {
	this.x = x;
	this.y = y;
    }

    /**
     * @return the x coordinate
     */
    public double getX() {
	return this.x;
    }

    /**
     * @return the y coordinate
     */
    public double getY() {
	return this.y;
    }

    /**
     * @param x
     *            the new x coordinate
     */
    public void setX(double x) {
	this.x = x;
    }

    /**
     * @param y
     *            the new y coordinate
     */
    public void setY(double y) {
	this.y = y;
    }

    /**
     * Print out the x and y and in the format "(x, y) = (this.x, this.y)".
     */
    public String toString() {
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("(x, y) = ");
	stringBuilder.append("(" + this.x + ", " + this.y + ")");
	String pointInformation = stringBuilder.toString();
	return pointInformation;
    }
}
