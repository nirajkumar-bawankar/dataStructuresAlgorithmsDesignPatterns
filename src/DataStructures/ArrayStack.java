package DataStructures;

import DataStructures.Interfaces.StackInterface;

/**
 * To learn more about the class please visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/Stack.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 13, 2013
 * @param <E>
 */
public class ArrayStack<E> implements StackInterface<E> {
    private static final int defaultSize = 10;
    private int maxSize;
    private int top;
    private E[] array;

    /**
     * Create a new ArrayStack object.
     *
     * @param maxSize
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int maxSize) {
	this.maxSize = maxSize;
	this.top = 0;
	this.array = (E[]) new Object[this.maxSize];
    }

    /**
     * Create a new ArrayStack object with default size 10.
     */
    public ArrayStack() {
	this(defaultSize);
    }

    @Override
    public boolean push(E item) {
	if (this.top == this.maxSize) {
	    return false;
	} else {
	    this.array[this.top++] = item;
	    return true;
	}
    }

    @Override
    public E pop() {
	if (this.top == 0) {
	    return null;
	} else {
	    return this.array[--this.top];
	}
    }

    @Override
    public E topValue() {
	if (this.top == 0) {
	    return null;
	} else {
	    return this.array[this.top - 1];
	}
    }

    @Override
    public int length() {
	return this.top;
    }

    @Override
    public void clear() {
	this.top = 0;
    }
}
