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
     * Use this sorting algorithm when: 1) The cost of swapping 2 elements in
     * your array is very large such as when the records are long strings.
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

    /**
     * Time complexities: Best Case = O(n * log n) Average Case = O(n * log n)
     * Worst Case = O(n * log n)
     *
     * Use this sorting algorithm when: 1) When space is not an issue as a merge
     * sort of an array takes twice the amount of space as insertionSort and
     * selectionSort. 2) When sorting a singly linked list since merging does
     * not require random access to list elements but instead use a singly
     * linked list based merge sort instead of this array based merge sort.
     *
     * The array to be sorted is repeatedly split in half until subarrays of
     * size 1 are reached.These subarrays of size 1 are merged in order to be of
     * size 2. Subarrays of size 2 are merged in order to become subarrays of
     * size 4, and so on. This means the depth of the recursion is log n for n
     * records. The first level of recursion can be thought of as n subarrays of
     * size 1. This requires O(n) total steps for merging. The next level of
     * recursion has n/2 subarrays of size 2 that again requires O(n) steps of
     * merging, and so on. Overall, at each of the log(n) levels or recursion,
     * O(n) merging is being done. Thus the cost of merge sort is O(n * log n)
     * for worst, average, and best cases.
     *
     * To learn more about merge sort visit:
     * http://algoviz.org/OpenDSA/Books/CS3114PM/html/Mergesort.html
     *
     * @param array
     *            array to be sorted
     * @param temperaryArray
     *            used in recursive implementation to sort larger and larger
     *            subarrays of array
     * @param leftIndex
     *            left most accessible index for subarray of array
     * @param rightIndex
     *            right most accessible index for subarray of array
     */
    public static void mergeSort(Comparable[] array,
	    Comparable[] temperaryArray, int leftIndex, int rightIndex) {
	checkIfValidIndex(leftIndex, array.length);
	checkIfValidIndex(rightIndex, array.length);
	if (leftIndex == rightIndex) {
	    return; // the array has been recursively reduced to array.size()
		    // subarrays
	}
	int middleIndex = (leftIndex + rightIndex) / 2;

	// merge sort first half
	mergeSort(array, temperaryArray, leftIndex, middleIndex);

	// merge sort second half
	mergeSort(array, temperaryArray, middleIndex + 1, rightIndex);

	// copy array/subarray to temperaryArray
	for (int i = leftIndex; i <= rightIndex; i++) {
	    temperaryArray[i] = array[i];
	}

	// do the merge operation back to array by placing the elements
	// previously copied into the temperaryArray into the correct positions
	// back into array
	int currentIndexWithinLeftSubArray = leftIndex;
	int currentIndexWithinRightSubArray = middleIndex + 1;
	for (int currentIndex = leftIndex; currentIndex <= rightIndex; currentIndex++) {
	    // if true then all elements in left subarray have been merged into
	    // the array
	    if (currentIndexWithinLeftSubArray == middleIndex + 1) {
		array[currentIndex] = temperaryArray[currentIndexWithinRightSubArray++];
	    } else if (currentIndexWithinRightSubArray > rightIndex) {
		// if true then all elements in right subarray have been merged
		// into the array
		array[currentIndex] = temperaryArray[currentIndexWithinLeftSubArray++];
	    } else if (temperaryArray[currentIndexWithinLeftSubArray]
		    .compareTo(temperaryArray[currentIndexWithinRightSubArray]) < 0) {
		// get smaller value in left subarray
		array[currentIndex] = temperaryArray[currentIndexWithinLeftSubArray++];
	    } else {
		// get smaller or equal value in right subarray
		array[currentIndex] = temperaryArray[currentIndexWithinRightSubArray++];
	    }
	}
    }

    /**
     * Time complexities:
     *
     * Use this sorting algorithm when: 1)
     *
     * Quick sort steps: 1. selects a value called the pivot
     *
     * To learn more about quick sort visit:
     * http://algoviz.org/OpenDSA/Books/CS3114PM/html/Quicksort.html
     */
    public static void quickSort() {
	// TODO: implement
    }
}
