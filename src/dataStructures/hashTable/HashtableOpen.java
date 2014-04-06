package dataStructures.hashTable;

/**
 * This hashtable is implemented using open addressing. This hastable is
 * represented as an array of Entry objects.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version April 5, 2014
 * @param <Key>
 *            Unique key.
 * @param <Value>
 *            Value associated with key.
 */
public class HashtableOpen<Key, Value> implements
	KeyValueHashMapInterface<Key, Value> {

    /**
     * A hashtable with a prime number size has emprically been shown to have
     * less collisions when probing.
     */
    private static final int INITIAL_CAPACITY = 101;

    /**
     * Load factor = # of filled cells(including deleted entries) divided by
     * hashtable size.
     */
    private double MAXIMUM_LOAD_FACTOR = 0.75;

    private Entry<Key, Value>[] hashtable;
    private int numberOfKeys;
    private int numberOfDeletes;

    /**
     * Used to indicate that the Entry at a particular element in the hashtable
     * array has been deleted.
     */
    private final Entry<Key, Value> DELETED_ENTRY = new Entry<Key, Value>(null,
	    null);

    /**
     * Create a new HashtableOpen object.
     */
    @SuppressWarnings("unchecked")
    public HashtableOpen() {
	this.hashtable = new Entry[HashtableOpen.INITIAL_CAPACITY];
    }

    @Override
    public Value get(Object key) {
	int emptyIndexOrIndexContainingKey = this.find(key);

	if (this.hashtable[emptyIndexOrIndexContainingKey] != null) {

	    int indexContainingKey = emptyIndexOrIndexContainingKey;

	    return this.hashtable[indexContainingKey].getValue();
	} else {
	    return null; // key was not found within hashtable
	}
    }

    @Override
    public boolean isEmpty() {
	return this.numberOfKeys == 0;
    }

    @Override
    public Value put(Key key, Value value) {
	int emptyIndexOrIndexContainingKey = this.find(key);

	if (this.hashtable[emptyIndexOrIndexContainingKey] == null) {
	    // empty element was found so insert new entry
	    int emptyIndex = emptyIndexOrIndexContainingKey;
	    this.hashtable[emptyIndex] = new Entry<Key, Value>(key, value);
	    this.numberOfKeys++;

	    // check if our hashtable is getting too full and needs to be
	    // rehashed
	    double currentLoadFactor = (double) (this.numberOfKeys + this.numberOfDeletes)
		    / this.hashtable.length;

	    if (currentLoadFactor > this.MAXIMUM_LOAD_FACTOR) {
		this.rehash();
	    }
	    return null;
	} else {
	    int indexContainingKey = emptyIndexOrIndexContainingKey;

	    Value oldValue = this.hashtable[indexContainingKey].getValue();
	    this.hashtable[indexContainingKey].setValue(value);
	    return oldValue;
	}
    }

    @Override
    public Value remove(Object key) {
	int emptyIndexOrIndexContainingKey = this.find(key);

	if (this.hashtable[emptyIndexOrIndexContainingKey] == null) {
	    return null;
	} else {
	    int indexContainingKey = emptyIndexOrIndexContainingKey;
	    Value valueOfEntryToDelete = this.hashtable[indexContainingKey]
		    .getValue();
	    this.hashtable[indexContainingKey] = this.DELETED_ENTRY;
	    this.numberOfDeletes++;
	    this.numberOfKeys--;
	    return valueOfEntryToDelete;
	}
    }

    @Override
    public int size() {
	return this.hashtable.length;
    }

    /**
     * Finds either the target key or the first empty slot in the search chain
     * using linear probing.
     *
     * Precondition: The hashtable is not full.
     *
     * @param key
     *            The key of the target object.
     * @return The index of the specified key within the hashtable(Entry array).
     *         Otherwise return the index of the first available slot.
     */
    private int find(Object key) {
	int currentIndex = key.hashCode() % this.hashtable.length;

	if (currentIndex < 0) {
	    currentIndex += this.hashtable.length;
	}

	// Increment index until an empty slot is reached or the key is found.
	while ((this.hashtable[currentIndex] != null)
		&& (!key.equals(this.hashtable[currentIndex].getKey()))) {
	    currentIndex++;

	    if (currentIndex >= this.hashtable.length) {
		currentIndex = 0;
	    }
	}
	return currentIndex;
    }

    /**
     * Doubles the capacity of the table and permanently removes all deleted
     * entries.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
	Entry<Key, Value>[] oldHashtable = this.hashtable;

	this.hashtable = new Entry[2 * this.hashtable.length + 1];

	// reinsert all items in oldHashtable into expanded hashtable
	this.numberOfKeys = 0;
	this.numberOfDeletes = 0;
	for (int i = 0; i < oldHashtable.length; i++) {
	    if ((oldHashtable[i] != null)
		    && (oldHashtable[i] != this.DELETED_ENTRY)) {
		this.put(oldHashtable[i].getKey(), oldHashtable[i].getValue());
	    }
	}
    }
}
