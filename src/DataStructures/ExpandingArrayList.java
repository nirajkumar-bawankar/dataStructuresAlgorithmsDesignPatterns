package DataStructures;

/**
 * A stripped-down version of Java's ArrayList class.
 *
 * @param <E>
 *            the type of elements stored in the array list
 *
 * @author Tony Allevato (allevato@vt.edu)
 * @version June 3, 2013
 */
public class ExpandingArrayList<E> {

    // the array that holds the elements in the list. This will need
    // to be resized (replaced by layer arrays) from time to time as elements
    // are added
    private E[] contents;

    // the number of elements that are actually currently in the list (will
    // always be less than or equal to contents.length
    private int size;

    /**
     * Creates a new ExandingArrayList with an initial capacity of 10 (that is,
     * it can hold 10 elements before it needs to be resized). Even though the
     * capacity is 10, the new list has a size of 0 (it is empty).
     */
    @SuppressWarnings("unchecked")
    public ExpandingArrayList() {
	this.contents = (E[]) new Object[10];
    }

    /**
     * Adds an item to the end of the list.
     *
     * @param item
     *            the item to add to the end of the list
     *
     */
    public void add(E item) {
	// if the number of elements in the list is equal to the capacity of
	// the array, then it's full and needs to be expanded to hold
	// the new item.
	if (this.size == this.contents.length) {
	    this.expandCapacity();
	}

	this.contents[this.size] = item;
	this.size++;
    }

    /**
     * Gets the element at the specified position in the list.
     *
     * @param index
     *            the index of the item to retrieve
     * @return the item at the specified position
     * @throws IndexOutOfBoundsException
     *             if the index is less than 0 or greater than or equal to
     *             size()
     *
     */
    public E get(int index) {
	this.checkBounds(index);
	return this.contents[index];
    }

    /**
     * Sets the element at the specified position in the list.
     *
     * @param index
     *            the index of the item to replace
     * @param item
     *            the item to put at the specified position
     * @throws IndexOutOfBoundsException
     *             if the index is less than 0 or greater than or equal to
     *             size()
     *
     */
    public void set(int index, E item) {
	this.checkBounds(index);
	this.contents[index] = item;
    }

    /**
     * Gets the number of elements that are currently in the list.
     *
     * @return the number of elements that are currently in the list
     */
    public int size() {
	return this.size;
    }

    /**
     * Expands the array. The steps this methods implement are:
     *
     * 1) Create a new array that is twice the size of the original.
     * 2) Copy everything from the old array to the new array.
     * 3) Reassign the field that holds a reference to the array so that it points to the new one.
     * This will cause the old one to be garbage-collected.
     */
    private void expandCapacity() {
	@SuppressWarnings("unchecked")
	E[] newContents = (E[]) new Object[this.size * 2];
	System.arraycopy(this.contents, 0, newContents, 0, this.size);
	this.contents = newContents;
    }

    /**
     * Checks that the specified index is a valid index within the bounds of the
     * list. If it is, nothing happens (the program continues to execute
     * normally). If it is not, an IndexOutOfBoundsException will be thrown.
     *
     * @throws IndexOutOfBoundsException
     *             if index is less than 0 or greater than or equal to size()
     */
    private void checkBounds(int index) {
	if (!(0 <= index && index < this.size)) {
	    throw new IndexOutOfBoundsException("The index " + index
		    + " is out of bounds: it must be between 0 and "
		    + this.size);
	}
    }
}
