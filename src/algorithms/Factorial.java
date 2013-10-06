package algorithms;

import dataStructures.stack.ArrayStack;

import dataStructures.interfaces.StackInterface;
import java.util.ArrayList;

/**
 * This class has an iterative and recursive function for computing the
 * factorial of an number.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sept 19, 2013
 */
public class Factorial {

    /**
     * Precondition: The largest factorial an long data type can represent is
     * factorial(20).
     *
     * @param numberLessThan21
     * @return The iteratively calculated factorial of parameter
     *         numberLessThan21.
     *
     */
    public static long iterativeFactorial(int numberLessThan21) {
	if (numberLessThan21 < 0) {
	    throw new IllegalArgumentException(
		    "In method iterativeFactorial of class "
			    + "Factorial the factorial of " + numberLessThan21
			    + " is undefined");
	} else if (numberLessThan21 > 20) {
	    throw new IllegalArgumentException(
		    "In method iterativeFactorial of class "
			    + "Factorial the factorial of "
			    + numberLessThan21
			    + " cannot be accuratley calculated. Instead use a "
			    + "integer between 0 and 20");
	}

	StackInterface<Long> stack = new ArrayStack<Long>();

	int number = numberLessThan21;
	while (number > 1) {
	    stack.push((long) number--);
	}

	long result = 1;

	while (stack.length() > 0) {
	    result = result * stack.pop();
	}
	return result;
    }

    /**
     * @param n
     *            The number to compute the factorial of.
     * @return The recursively calculated factorial of parameter n.
     */
    public static long recursiveFactorial(int n) {
	if (n < 0) {
	    throw new IllegalArgumentException(
		    "In method recursiveFactorial of class "
			    + "Factorial the factorial of " + n
			    + " is undefined");
	}
	// base case
	if (n == 0 || n == 1) {
	    return 1;
	} else { // n! = n * (n - 1)!
	    return n * recursiveFactorial(n - 1);
	}
    }
}
