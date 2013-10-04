package algorithms;

import java.util.HashMap;

/**
 * A class that computes the Fibonacci numbers, using a traditonal recursive
 * approach and a memoized approach to increase performance.
 *
 * @author Tony Allevato (allevato@vt.edu)
 * @version June 18, 2013
 */
public class Fibonacci {
    private HashMap<Integer, Long> cache;

    /**
     * Initializes a new Fibonacci object (creates the cache used by the
     * memoized implementation).
     */
    public Fibonacci() {
	this.cache = new HashMap<Integer, Long>();
    }

    /**
     * Calculates the nth Fibonacci number. Uses a traditional recursive
     * approach that needlessly recomputes a lot of intermediate results. The
     * complexity of this algorithm is O(1.618^n), where 1.618 is the golden
     * ratio (to 3 decimal places).
     *
     * @param n
     *            the index of the desired Fibonacci number
     * @return the nth Fibonacci number
     */
    public long recursiveFibonacci(int n) {
	if (n == 0) {
	    return 0;
	} else if (n == 1) {
	    return 1;
	} else {
	    return recursiveFibonacci(n - 2) + recursiveFibonacci(n - 1);
	}
    }

    /**
     * Calculates the nth Fibonacci number. Uses "memoization", storing previous
     * results so that they do not need to be needlessly recomputed. The
     * complexity of this algorithm is O(n).
     *
     */
    public long memoizedFibonacci(int n) {
	if (n == 0) {
	    return 0;
	} else if (n == 1) {
	    return 1;
	} else {
	    // if the computed value was already in the cache, then just
	    // return it instead of recomputing it
	    if (cache.containsKey(n)) {
		return cache.get(n);
	    } else {
		// otherwise, we need to compute the value and cache it so
		// that a later memoizedFibonacci call can retrieve it
		long result = memoizedFibonacci(n - 2)
			+ memoizedFibonacci(n - 1);
		cache.put(n, result);
		return result;
	    }
	}
    }

    /**
     * A simple runner that benchmarks the runtime of the Fibonacci function.
     *
     * @param args
     *            not used
     */
    public static void main(String[] args) {
	Fibonacci f = new Fibonacci();

	// The naive implementation starts to slow down significantly (to the
	// point that you can see it lag) around 37 or so on my computer. Your
	// mileage may vary.
	final int LAST_NUMBER = 44;

	System.out
		.println("========Using the 'naive' recursive implementation:========");

	for (int i = 0; i <= LAST_NUMBER; i++) {
	    long start = System.currentTimeMillis();
	    int result = (int) f.recursiveFibonacci(i);
	    long end = System.currentTimeMillis();
	    System.out.println("recursiveFibonacci(" + i + ") = " + result
		    + " (" + (end - start) + " milliseconds)");
	}

	System.out
		.println("\n=============Using the memoizing implementation:===========");

	for (int i = 0; i <= LAST_NUMBER; i++) {
	    long start = System.currentTimeMillis();
	    int result = (int) f.memoizedFibonacci(i);
	    long end = System.currentTimeMillis();
	    System.out.println("memoizedFibonacci(" + i + ") = " + result
		    + " (" + (end - start) + " milliseconds)");
	}
    }
}
