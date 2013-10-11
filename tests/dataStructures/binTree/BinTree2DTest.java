package dataStructures.binTree;

import java.awt.Point;
import realtimeweb.earthquakeservice.domain.Coordinate;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 9, 2013
 */
public class BinTree2DTest extends junit.framework.TestCase {
    private BinTree2D<Point, String> binTree;

    public void setUp() {
	// TODO: make this range generic has a range in the x-axis of 0.0 to
	// 360.0 y-axis from 0.0 to 180.0
	this.binTree = new BinTree2D<Point, String>(0.0, 100.0,
		0.0, 100.0);
    }

    public void test_insert() {

    }

    public void test_remove() {

    }

    public void test_removeRandomElement() {

    }

    public void test_find() {

    }

    public void test_clear() {

    }


    public void test_size() {

    }

    public void test_preorderTraversal() {

    }
}
