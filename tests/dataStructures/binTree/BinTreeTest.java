package dataStructures.binTree;

import realtimeweb.earthquakeservice.domain.Coordinate;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 9, 2013
 */
public class BinTreeTest extends junit.framework.TestCase {
    private BinTree<CoordinateContainer, String> binTree;

    public void setUp() {
	// TODO: make this range generic has a range in the x-axis of 0.0 to
	// 360.0 y-axis from 0.0 to 180.0
	this.binTree = new BinTree<CoordinateContainer, String>(0.0, 360.0,
		0.0, 180.0);
    }
}
