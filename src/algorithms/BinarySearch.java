package algorithms;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sept 28, 2013
 */
public class BinarySearch {

    /**
     * @param sortedArray
     * @param valueToFind
     * @return Index of valueToFind within sortedArray; otherwise return -1.
     */
    public static int search(int[] sortedArray, int valueToFind) {
	int lowerBound = -1;
	int upperBound = sortedArray.length;

	while (lowerBound + 1 != upperBound) { // stop when lowerBound meets
					       // upperBound
	    int middleIndex = (lowerBound + upperBound) / 2;
	    if (valueToFind < sortedArray[middleIndex]) {
		upperBound = middleIndex; // valueToFind may be in left half
	    }
	    if (valueToFind == sortedArray[middleIndex]) {
		return middleIndex; // valueToFind has been found
	    }
	    if (valueToFind > sortedArray[middleIndex]) {
		lowerBound = middleIndex; // valueToFind may be in right half
	    }
	}
	return -1; // value was not found
    }
}
