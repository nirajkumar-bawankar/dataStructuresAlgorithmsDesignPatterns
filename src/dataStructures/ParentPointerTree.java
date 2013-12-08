package dataStructures;

/**
 * To view an interactive web page about parent pointer tree implementation
 * visit: http://algoviz.org/OpenDSA/Books/CS3114PM/html/UnionFind.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Dec 8, 2013
 */
public class ParentPointerTree {
    private int[] allNodes; // represents all nodes of multiple trees

    public ParentPointerTree(int maximumNumberOfNodes) {
	this.allNodes = new int[maximumNumberOfNodes];

	for (int i = 0; i < maximumNumberOfNodes; i++) {
	    // each node is initially it's own root
	    this.allNodes[i] = -1;
	}
    }

    /**
     * @param current
     *            current index within tree
     * @return the root index of current's tree
     */
    public Integer FIND(Integer current) {
	if (this.allNodes[current] == -1) {
	    return current; // already at root
	}
	Integer currentIndex = current;
	while (this.allNodes[currentIndex] != -1) {
	    currentIndex = this.allNodes[currentIndex];
	}
	return currentIndex;
    }

    /**
     * Merge 2 subtrees if they are different
     */
    public void UNION(int nodeAtIndexX, int nodeAtIndexY) {
	Integer rootOfTreeContainingNodeX = FIND(nodeAtIndexX);
	Integer rootOfTreeContainingNodeY = FIND(nodeAtIndexY);

	if (rootOfTreeContainingNodeX != rootOfTreeContainingNodeY) {
	    this.allNodes[rootOfTreeContainingNodeX] = rootOfTreeContainingNodeY;
	}
    }
}
