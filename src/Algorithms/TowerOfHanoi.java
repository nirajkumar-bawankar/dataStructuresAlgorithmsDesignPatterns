package Algorithms;

/**
 * This class prints out how to solve the Tower of Hanoi puzzle. For information
 * about this puzzle visit: http://en.wikipedia.org/wiki/Tower_of_Hanoi
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Aug 31, 2013
 */
public class TowerOfHanoi {
    public static void getSolution(int numberOfDisks, int startPolePosition,
	    int goalPolePosition, int temperaryPolePosition) {
	if (numberOfDisks == 0) {
	    return;
	}
	getSolution(numberOfDisks - 1, startPolePosition,
		temperaryPolePosition, goalPolePosition);
	System.out.println("Move disk from pole " + startPolePosition + " to "
		+ "pole " + goalPolePosition);
	getSolution(numberOfDisks - 1, temperaryPolePosition, goalPolePosition,
		startPolePosition);
    }
}
