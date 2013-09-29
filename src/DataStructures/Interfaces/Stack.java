package DataStructures.Interfaces;

/**
 * To learn more about this interface please visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/Stack.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 19, 2013
 * @param <E>
 */
public interface Stack<E> {

    /**
     * Push item onto the top of the stack.
     *
     * @param item
     *            Item to be pushed.
     * @return True if item is pushed onto stack; otherwise return false.
     */
    public boolean push(E item);

    /**
     * Remove and return the element at the top of the stack.
     *
     * @return The top element that was removed.
     */
    public E pop();

    /**
     * @return A copy of the top value.
     */
    public E topValue();

    /**
     * @return The number of elements in the stack.
     */
    public int length();

    /**
     * Place a description of your method here.
     */
    public void clear();
}
