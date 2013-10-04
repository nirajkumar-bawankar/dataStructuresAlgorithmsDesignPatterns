package dataStructures;

/**
 * If you need to sort a lot of elements, you can store them in an array where
 * every array element contains a key value and a pointer to the value itself.
 * Although this may seem like a waste of space, we can store pointers to the
 * values in another array with keys are their fields for other purposes.
 *
 * @version Sep 13, 2013
 */
public class KeyValuePair implements Comparable {

    private Comparable key;
    private Object value;

    public KeyValuePair(Comparable key, Object value) {
	this.key = key;
	this.value = value;
    }

    @Override
    public int compareTo(Object item) throws ClassCastException {
	if (!(item instanceof KeyValuePair)) {
	    throw new ClassCastException("A KeyValuePair object was expected");
	}
	return this.key.compareTo(((KeyValuePair) item).key);
    }

    public Comparable getKey() {
	return this.key;
    }

    public Object getValue() {
	return this.value;
    }
}
