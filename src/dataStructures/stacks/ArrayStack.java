package dataStructures.stacks;

import dataStructures.interfaces.StackInterface;

/**
 * To learn more about the class please visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/Stack.html
 *
 * @version Sep 13, 2013
 * @param <E>
 */
public class ArrayStack<E> implements StackInterface<E> {
    private static final int defaultSize = 10;
    private int maxSize;
    private int length;
    private E[] array;

    /**
     * Create a new ArrayStack object.
     *
     * @param maxSize
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int maxSize) {
	this.maxSize = maxSize;
	this.length = 0;
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
	if (this.length == this.maxSize) {
	    return false;
	} else {
	    this.array[this.length++] = item;
	    return true;
	}
    }

    @Override
    public E pop() {
	if (this.length == 0) {
	    return null;
	} else {
	    return this.array[--this.length];
	}
    }

    @Override
    public E topValue() {
	if (this.length == 0) {
	    return null;
	} else {
	    return this.array[this.length - 1];
	}
    }

    @Override
    public int length() {
	return this.length;
    }

    @Override
    public void clear() {
	this.length = 0;
    }
}
