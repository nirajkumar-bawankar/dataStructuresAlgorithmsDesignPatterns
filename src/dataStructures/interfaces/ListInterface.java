package dataStructures.interfaces;

/**
 * To learn more about this interface please visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/ListADT.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 4, 2013
 * @param <E>
 */
public interface ListInterface<E> {
    /**
     * Insert an element behind the current position. Must check that the linked
     * list's capacity is not exceeded.
     *
     * @param item
     *            Item to be inserted.
     */
    public void insert(E item);

    /**
     * Insert an element after the last element in the list.
     *
     * @param item
     *            Item to be appended.
     */
    public void append(E item);

    /**
     * Remove all contents from the list.
     */
    public void clear();



    /**
     * @return The number of items in the list.
     */
    public int length();

    /**
     * @param position
     *            Position to move current to.
     */
    public void moveCurrentToPosition(int position);
}
