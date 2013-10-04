package DataStructures;

import java.util.Random;
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * A bag or "multiset" is collection that can hold duplicate items but has no
 * notion of order among them. In other words, you can't access the items by a
 * numeric position.
 *
 * @param <E>
 *            the type of item stored in the bag
 *
 * @author Tony Allevato (allevato@vt.edu)
 * @version June 4, 2013
 */
public class Bag<E> {
    /**
     * The ArrayList that will hold the elements in the bag. This is an example
     * of the adapter design pattern; rather than manage our own low-level array
     * to hold the elements, we reuse the ArrayList and most of the methods
     * below just delegate to the same method in ArrayList.
     */
    private ArrayList<E> contents;

    /**
     * Create a new Bag object.
     */
    public Bag() {
	this.contents = new ArrayList<E>();
    }

    /**
     * Adds the specified item to the bag.
     *
     * @param item the time to add to the bag
     */
    public void add(E item) {
	this.contents.add(item);
    }

    /**
     * Gets a value indicating whether the specified item is in the bag.
     *
     * @param item the item to look for
     * @return true if the item is in the bag, otherwise false
     */
    public boolean contains(E item)
    {
        return contents.contains(item);
    }

    /**
     * Gets a value indicating whether the bag is empty.
     *
     * @return true if the bag is empty, otherwise false
     */
    public boolean isEmpty()
    {
        return contents.isEmpty();
    }

    /**
     * Gets the number of elements in the bag.
     *
     * @return the number of elements in the bag
     */
    public int size()
    {
        return contents.size();
    }

    /**
     * Removes all the elements from the bag.
     */
    public void clear()
    {
        contents.clear();
    }

    /**
     * Removes a random element from the bag.
     *
     * @return the item that was removed from the bag
     * @throws NoSuchElementException if the bag is empty
     */
    public E remove()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException("The bag is empty.");
        }

        Random r = new Random();
        int index = r.nextInt(size());
        return contents.remove(index);
    }
}
