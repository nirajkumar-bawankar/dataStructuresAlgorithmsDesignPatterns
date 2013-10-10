package dataStructures;

import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Collection;

/**
 * @param <E>
 *            The type of elements to be iterated over.
 *
 * @author Quinn Liu
 * @version Oct 9, 2013
 */
public class PeekingIterator<E> {

    ListIterator<E> iterator;

    /**
     * Create a new PeekingIterator object.
     *
     * @param collection
     */
    public PeekingIterator(Collection<E> collection) {
	this.iterator = (ListIterator<E>) collection.iterator();
    }

    /**
     * @return true if there is a next element; otherwise false.
     */
    public boolean hasNext() {
	return this.iterator.hasNext();
    }

    /**
     * @return the next element in the ieteration.
     */
    public E getNext() {
	if (!hasNext()) {
	    throw new NoSuchElementException(
		    "In class PeekingIterator method getNext, there is no"
			    + "next element");
	} else {
	    return this.iterator.next();
	}
    }

    /**
     * @return the next element in the iteration without advancing the
     *         iteration.
     */
    public E peek() throws NoSuchElementException {
	// get next value
	E nextObject = this.iterator.next();
	this.iterator.previous();
	return nextObject;
    }
}
