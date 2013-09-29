package DataStructures.Interfaces;

/**
 * To learn more about this interface please visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/ListADT.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 15, 2013
 * @param <E>
 */
public interface List<E> {
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
     * Remove the element after the current element and return the value of the
     * removed element.
     *
     * @return The element that was removed.
     */
    public E remove();

    /**
     * Remove all contents from the list.
     */
    public void clear();

    /**
     * Move current position to first element.
     */
    public void moveToStart();

    /**
     * Move current position to last element.
     */
    public void moveToEnd();

    /**
     * Move the current position one element before. No change if already at the
     * beginning.
     *
     * @return True if moved to previous position; otherwise return false.
     */
    public boolean previous();

    /**
     * Move the current position one element after. No change if already at the
     * end.
     *
     * @return True if moved to current position; otherwise return false.
     */
    public boolean next();

    /**
     * @return The number of items in the list.
     */
    public int length();

    /**
     * @return The current position.
     */
    public int currentPosition();

    /**
     * @param position
     *            Position to move current to.
     */
    public void moveCurrentToPosition(int position);

    /**
     * @return The current item in the current position.
     */
    public E getValue();
}
