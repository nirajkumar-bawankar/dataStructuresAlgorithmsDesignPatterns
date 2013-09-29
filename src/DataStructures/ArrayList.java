package DataStructures;

import DataStructures.Interfaces.List;

/**
 * @version Sep 13, 2013
 * @param <E>
 */
public class ArrayList<E> implements List<E> {
    private static final int defaultMaxSize = 10;
    private int maxSize;
    private int size;
    private int currentPosition;
    private E[] array;

    /**
     * Create a new ArrayList object.
     *
     * @param maxSize
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int maxSize) {
	this.maxSize = maxSize;
	this.size = this.currentPosition = 0;
	this.array = (E[]) new Object[this.maxSize];
    }

    /**
     * Create a new ArrayList object.
     */
    public ArrayList() {
	this(defaultMaxSize);
    }

    @Override
    public void insert(E item) {
	assert this.size < this.maxSize : "ArrayList capacity exceeded";
	for (int i = this.size; i > this.currentPosition; i--) {
	    // Shift elements up to make room
	    this.array[i] = this.array[i - 1];
	}
	this.array[this.currentPosition] = item;
	this.size++;
    }

    @Override
    public void append(E item) {
	assert this.size < this.maxSize : "ArrayList capacity exceeded";
	this.array[this.size++] = item;
    }

    @Override
    public E remove() {
	if ((this.currentPosition < 0) || (this.currentPosition >= this.size)) {
	    return null;
	}
	E item = this.array[this.currentPosition];
	for (int i = this.currentPosition; i < this.size - 1; i++) {
	    this.array[i] = this.array[i + 1];
	}
	this.size--;
	return item;
    }

    @Override
    public void clear() {
	this.size = this.currentPosition = 0;
    }

    @Override
    public void moveToStart() {
	this.currentPosition = 0;
    }

    @Override
    public void moveToEnd() {
	this.currentPosition = this.size;

    }

    @Override
    public boolean previous() {
	if (this.currentPosition != 0) {
	    this.currentPosition--;
	    return true;
	} else {
	    return false;
	}
    }

    @Override
    public boolean next() {
	if (this.currentPosition < this.size) {
	    this.currentPosition++;
	    return true;
	} else {
	    return false;
	}
    }

    @Override
    public int length() {
	return this.size;
    }

    @Override
    public int currentPosition() {
	return this.currentPosition;
    }

    @Override
    public void moveCurrentToPosition(int position) {
	assert (position >= 0) && (position <= this.size) : "position is out of range";
	this.currentPosition = position;
    }

    @Override
    public E getValue() {
	assert (this.currentPosition >= 0) && (this.currentPosition < this.size) :
	    "No current element";
	return this.array[this.currentPosition];
    }
}
