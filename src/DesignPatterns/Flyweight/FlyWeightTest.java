package DesignPatterns.Flyweight;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;

/**
 * @version Sept 29, 2013
 */
public class FlyWeightTest extends JFrame {
    JButton startDrawing;

    int windowWidth = 1750;
    int windowHeight = 150;

    Color[] colors = { Color.orange, Color.red, Color.yellow, Color.blue,
	    Color.pink, Color.cyan, Color.magenta, Color.black, Color.gray };

    public static void main(String[] args) {
	new FlyWeightTest();
    }

    public FlyWeightTest() {
	this.setSize(windowWidth, windowHeight);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle("Flyweight Test");

	JPanel contentPane = new JPanel();
	contentPane.setLayout(new BorderLayout());

	final JPanel drawingPanel = new JPanel();
	this.startDrawing = new JButton("Draw Stuff");

	contentPane.add(drawingPanel, BorderLayout.CENTER);
	contentPane.add(this.startDrawing, BorderLayout.SOUTH);

	this.startDrawing.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent event) {
		Graphics g = drawingPanel.getGraphics();

		long startTime = System.currentTimeMillis();

		for (int i = 0; i < 100000; ++i) {
		    // uses custom rectangles stored in the HashMap to
		    // speed up the program
		    // with flyweight design pattern
		    CustomRectangle rectangle = CustomRectangleFactory
			    .getCustomRectangle(getRandColor());
		    rectangle.draw(g, getRandX(), getRandY(), getRandX(),
			    getRandY());

		    // without flyweight design pattern
//		    CustomRectangle rectangle = new CustomRectangle(
//		    getRandColor(), getRandX(), getRandY(), getRandX(),
//		    getRandY()); rectangle.draw(g);

		    // slowest

		}

		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime)
			+ " milliseconds");
	    }
	});

	this.add(contentPane);
	this.setVisible(true);
    }

    // Picks random x & y coordinates

    private int getRandX() {
	return (int) (Math.random() * this.windowWidth);
    }

    private int getRandY() {
	return (int) (Math.random() * this.windowHeight);
    }

    // Picks a random Color from the 9 available
    private Color getRandColor() {
	Random randomGenerator = new Random();

	int randInt = randomGenerator.nextInt(9);

	return this.colors[randInt];
    }
}
