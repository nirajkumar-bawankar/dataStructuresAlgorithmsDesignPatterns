package dataStructures.hashTable;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version April 5, 2014
 */
public class Entry<Key, Value> {
    private Key key;
    private Value value;

    public Entry(Key key, Value value) {
	this.key = key;
	this.value = value;
    }

    public Key getKey() {
	return this.key;
    }

    public Value getValue() {
	return this.value;
    }

    public Value setValue(Value value) {
	Value oldValue = this.value;
	this.value = value;
	return oldValue;
    }
}
