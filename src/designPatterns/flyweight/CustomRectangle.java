package designPatterns.flyweight;

import java.awt.*;

/**
 * @version Sept 29, 2013
 */
public class CustomRectangle {
    private Color color = Color.black;
    private int x, y, x2, y2;

    public CustomRectangle(Color color) {
	this.color = color;
    }

    public void draw(Graphics g, int upperX, int upperY, int lowerX, int lowerY) {
	g.setColor(this.color);
	g.fillRect(upperX, upperY, lowerX, lowerY);
    }

    // original forces creation of a rectangle every time
    public CustomRectangle(Color color, int upperX, int upperY, int lowerX,
	    int lowerY) {
	this.color = color;
	this.x = upperX;
	this.y = upperY;
	this.x2 = lowerX;
	this.y2 = lowerY;
    }

//    public void draw(Graphics g) {
//    	g.setColor(color);
//    	g.fillRect(x, y, x2, y2);
//    }
}
