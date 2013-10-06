package dataStructures.interfaces;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 4, 2013
 * @param <E>
 */
public interface ListIteratorInterface<E> {
    /**
     * Remove the element after the current element and return the value of the
     * removed element.
     *
     * @return The element that was removed.
     */
    public E remove();

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
     * @return The current position.
     */
    public int currentPosition();

    /**
     * @return The current item in the current position.
     */
    public E getValue();
}
