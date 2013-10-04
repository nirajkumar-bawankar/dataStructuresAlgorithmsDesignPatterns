package designPatterns.flyweight;

import java.util.HashMap;
import java.awt.Color;

/**
 * @version Sept 29, 2013
 */
public class CustomRectangleFactory {
    private static final HashMap<Color, CustomRectangle> rectanglesByColor = new HashMap<Color, CustomRectangle>();

    public static CustomRectangle getCustomRectangle(Color color) {
	CustomRectangle rectangle = (CustomRectangle) rectanglesByColor
		.get(color);

	if (rectangle == null) {
	    rectangle = new CustomRectangle(color);

	    // add new custom rectangle to HashMap
	    rectanglesByColor.put(color, rectangle);
	}
	return rectangle;
    }
}
