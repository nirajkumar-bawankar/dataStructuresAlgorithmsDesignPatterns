package algorithms;

/**
 * Sorting algorithms need to primarily compared by both number of comparisons
 * done and number of swaps done to create a sorted array.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 5, 2013
 */
public class Sorting {

    /**
     * Comparisons: Best Case = O(n) Average Case = O(n^2) Worst Case = O(n^2)
     * Swaps: Best Case = 0 Average Case = O(n^2) Worst Case = O(n^2)
     *
     * Use this sorting algorithm when: 1) Your unsorted array is very small. 2)
     * Your unsorted array is already nearly sorted with a few elements in the
     * wrong place.
     *
     * Note: It may first appear that using a binary search to locate the
     * position within the first i - 1 records of the array into which record i
     * should be inserted, but this will not help because now the algorithm
     * needs to shift the records to make room for the insertion requiring
     * bigTheta(i) time.
     *
     * To learn more about insertion sort visit:
     * http://algoviz.org/OpenDSA/Books/CS3114PM/html/InsertionSort.html
     *
     * @param array
     *            unsorted array to be sorted
     */
    public static void insertionSort(Comparable[] array) {
	for (int i = 1; i < array.length; i++) {
	    for (int j = i; j > 0; j--) {
		if (array[j].compareTo(array[j - 1]) < 0) {
		    swap(array, j, j - 1);
		}
	    }
	}
    }

    /**
     * Comparisons: Best Case = O(n^2) Average Case = O(n^2) Worst Case = O(n^2)
     * Swaps: Best Case = O(n) Average Case = O(n) Worst Case = O(n)
     *
     * Use this sorting algorithm when: 1) The cost of swapping 2 elements is
     * very large such as when the records are long strings.
     *
     * To learn more about insertion sort visit:
     * http://algoviz.org/OpenDSA/Books/CS3114PM/html/SelectionSort.html
     */
    public static void selectionSort(Comparable[] array) {
	for (int i = 0; i < (array.length - 1); i++) {
	    int indexOfLargestValue = 0;

	    // find the index of the largest value
	    for (int j = 1; j < (array.length - i); j++) {
		if (array[j].compareTo(array[indexOfLargestValue]) > 0) {
		    indexOfLargestValue = j;
		}
	    }
	    swap(array, indexOfLargestValue, array.length - (i + 1));
	}
    }

    static void swap(Comparable[] array, int index1, int index2) {
	checkIfValidIndex(index1, array.length);
	checkIfValidIndex(index2, array.length);

	Comparable tempValue1 = array[index1];
	array[index1] = array[index2];
	array[index2] = tempValue1;
    }

    private static void checkIfValidIndex(int index, int arrayLength) {
	if (index < 0 || index > arrayLength) {
	    throw new IllegalArgumentException("In class Sorting method "
		    + "checkIfValidIndex " + index + " must be between 0 and "
		    + arrayLength);
	}
    }
}
