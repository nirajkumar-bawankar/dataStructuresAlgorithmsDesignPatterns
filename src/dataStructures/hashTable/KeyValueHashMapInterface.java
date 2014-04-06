package dataStructures.hashTable;

/**
 * To learn about hash tables please vist:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/HashIntro.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version April 5, 2014
 */
public interface KeyValueHashMapInterface<Key, Value> {

    /**
     * @return The value associated with the specified key. Returns null if the
     *         key is not present.
     */
    public abstract Value get(Object key);

    /**
     * @return true if this table contains no key-value mappings.
     */
    public abstract boolean isEmpty();

    /**
     * Associates the specified value with the specified key.
     *
     * @return The previous value asociated with the specified key if key is
     *         already in the hashtable. Otherwise return null if this is a new
     *         key and had no previous mapping for the key.
     */
    public abstract Value put(Key key, Value value);

    /**
     * Removes the mapping for this key from this hashtable if it is present.
     *
     * @return The previous value associated with the specified key, or null if
     *         there was no previous mapping.
     */
    public abstract Value remove(Object key);

    /**
     * @return The size & not the capacity of this hashtable.
     */
    public abstract int size();
}
