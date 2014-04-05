package dataStructures.hashTable;

/**
 * This hashtable is implemented using open addressing. This hastable is
 * represented as an array of Entry objects.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version April 5, 2014
 */
public class Hashtable<Key, Value> implements
	KeyValueHashMapInterface<Key, Value> {

    /**
     * A hashtable with a prime number size has emprically been shown to have
     * less collisions when probing.
     */
    private static final int INITIAL_CAPACITY = 101;

    /**
     * Load factor = # of filled cells divided by hashtable size. Load factor =
     * 0.75 = average # of probes is 1.38 using chaining.
     */
    private double MAXIMUM_LOAD_FACTOR = 0.75;

    private Entry<Key, Value>[] hashtable;
    private int numberOfKeys;
    private int numberOfDeletes;

    /**
     * Used to indicate that the Entry at a particular element in the
     * hashtable array has been deleted.
     */
    private final Entry<Key, Value> DELETED_ENTRY = new Entry<Key, Value>(null,
	    null);

    public Hashtable() {
	this.hashtable = new Entry[this.INITIAL_CAPACITY];
    }

    @Override
    public Value get(Object key) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean isEmpty() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public Value put(Key key, Value value) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void remove(Object key) {
	// TODO Auto-generated method stub

    }

    @Override
    public int size() {
	// TODO Auto-generated method stub
	return 0;
    }
}
