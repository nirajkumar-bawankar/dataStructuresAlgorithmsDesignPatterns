package designPatterns.observer;

/**
 * Observer Design Pattern
 * 1) Observable is a class & Observer is an interface.
 * 2) Observable class maintains a list of observers.
 * 3) When an Observable object is updated it invokes the update() method of
 * each of its observers to notify that it has changed.
 */
public class ObserverTest extends junit.framework.TestCase {
    private MessageBoard messageBoard; // extends Observable & notifies observers
    				       // when it's state changes

    private Student student1; // implements Observer with an update method that
    			      // is called when messageBoard's state changes
    private Student student2;

    public void setUp() {
	this.messageBoard = new MessageBoard("hello world");
	this.student1 = new Student("Romeo");
	this.student2 = new Student("Juliet");
    }

    public void testSetMessage() {
	this.messageBoard.setMessage("BEFORE ADDING OBSERVERS");
	this.messageBoard.addObserver(this.student1);
	this.messageBoard.addObserver(this.student2);
	this.messageBoard.setMessage("AFTER ADDING OBSERVERS");
    }
}
