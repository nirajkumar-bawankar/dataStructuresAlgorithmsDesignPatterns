package DataStructures.Interfaces;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 21, 2013
 */
public interface Dictionary {
    /**
     * Insert a record.
     *
     * @param key
     * @param element
     */
    public void insert(Comparable<?> key, Object element);

    /**
     * Remove and return a record.
     *
     * @param key
     *            Key of record to be removed.
     */
    public void remove(Comparable<?> key);

    /**
     * @return Remove and return a random record.
     */
    public Object removeRandomElement();

    /**
     * @param key
     * @return An record with key value matching parameter.
     */
    public Object find(Comparable<?> key);

    /**
     * @return Number of records in dictionary.
     */
    public int size();
}
