package DataStructures;

/**
 * Queue abstract data type.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 1, 2013
 */
public interface QueueInterface<E> {
    public void enqueue(E item);

    public E dequeue();

    public E frontValue();

    public int length();

    public void clear();
}
