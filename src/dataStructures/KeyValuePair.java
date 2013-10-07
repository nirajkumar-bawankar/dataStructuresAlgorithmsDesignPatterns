package dataStructures;

/**
 * @version Sep 13, 2013
 * @param <Key>
 * @param <Value>
 */
public class KeyValuePair<Key extends Comparable<Key>, Value> implements
	Comparable<KeyValuePair<Key, Value>> {

    private final Key key;
    private final Value value;

    /**
     * Create a new KeyValuePair object.
     *
     * @param key
     * @param value
     */
    public KeyValuePair(Key key, Value value) {
	this.key = key;
	this.value = value;
    }

    /**
     * Values are disregarded for the purposes of comparison.
     */
    @Override
    public int compareTo(KeyValuePair<Key, Value> item) {
	if (this.key == null) {
	    if (item.key == null) {
		return 0;
	    } else {
		// arbitrarily decides to sort null keys first.
		return -1;
	    }
	} else {
	    return this.key.compareTo(item.key);
	}
    }

    /**
     * @return The key object.
     */
    public Key getKey() {
	return this.key;
    }

    /**
     * @return The value object.
     */
    public Value getValue() {
	return this.value;
    }

    @Override
    public boolean equals(Object other) {
	if (!this.getClass().equals(other.getClass())) {
	    return false;
	}
	@SuppressWarnings("rawtypes")
	KeyValuePair item = (KeyValuePair) other;
	return ((this.key == null && item.key == null) || this.key
		.equals(item.key))
		&& ((this.value == null && item.value == null) || this.value
			.equals(item.value));
    }

    @Override
    public int hashCode() {
	return ((this.key == null) ? 0 : this.key.hashCode())
		^ ((this.value == null) ? 0 : this.value.hashCode());
    }
}
