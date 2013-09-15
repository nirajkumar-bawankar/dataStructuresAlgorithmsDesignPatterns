package DataStructures.EarthquakeWatcherService;

import realtimeweb.earthquakeservice.domain.Earthquake;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 9, 2013
 */
public class NodeAwareOfHeapIndex implements Comparable<Earthquake> {
    private int indexWithinHeapArray;
    private Earthquake earthquake;

    public NodeAwareOfHeapIndex(Earthquake earthquake) {
	this.indexWithinHeapArray = -1; // uninitialized index within heap array state
	this.earthquake = earthquake;
    }

    public void setIndexWithinHeapArray(int indexWithinHeapArray) {
	this.indexWithinHeapArray = indexWithinHeapArray;
    }

    public int getIndexWithinHeapArray() {
	return this.indexWithinHeapArray;
    }

    public Earthquake getEarthquake() {
	return this.earthquake;
    }

    @Override
    public int compareTo(Earthquake otherEarthquake) {
	if (this.earthquake.getMagnitude() < otherEarthquake.getMagnitude()) {
	    return -1;
	} else if (this.earthquake.getMagnitude() == otherEarthquake.getMagnitude()) {
	    return 0;
	} else { // earthquake.getMagnitude() > otherEarthquake.getMagnitude()
	    return 1;
	}
    }
}
