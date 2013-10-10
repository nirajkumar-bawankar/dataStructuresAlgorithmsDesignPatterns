package dataStructures.binTree;

import realtimeweb.earthquakeservice.domain.Coordinate;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 10, 2013
 */
public class CoordinateContainer implements Comparable {
    private Coordinate coordinate;

    public CoordinateContainer(Coordinate coordinate) {
	this.coordinate = coordinate;
    }

    @Override
    public int compareTo(Object o) {
	// TODO Auto-generated method stub
	return 0;
    }
}
