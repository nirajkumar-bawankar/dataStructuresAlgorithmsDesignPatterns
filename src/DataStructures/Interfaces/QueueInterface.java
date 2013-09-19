package DataStructures.Interfaces;

/**
 * To learn more about this interface please visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/Queue.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 1, 2013
 * @param <E>
 */
public interface QueueInterface<E> {
    /**
     * Add an item to the rear of the queue.
     *
     * @param item
     *            Item to be added.
     */
    public void enqueue(E item);

    /**
     * Remove and return the item at the front of the queue.
     *
     * @return The item that was removed.
     */
    public E dequeue();

    /**
     * @return The item at the front of the queue.
     */
    public E frontValue();

    /**
     * @return How many elements are in the queue.
     */
    public int length();

    /**
     * Remove all the items in the queue.
     */
    public void clear();
}
