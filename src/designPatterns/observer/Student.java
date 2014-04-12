package designPatterns.observer;

import java.util.Observable;

import java.util.Observer;

/**
 * Observable is a class & Observer is an interface
 */
public class Student implements Observer {
    private String name;

    public Student(String name) {
	this.name = name;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
	System.out.println("message board changed to '" + arg1
		+ "' by student " + this.name);
    }
}
