package dataStructures.binTree;

import dataStructures.interfaces.DictionaryInterface;

/**
 * The bin tree is a spatial data structure that can be used to unify search
 * across any arbitrary set of keys. Most commonly it is used to efficiently
 * search through on multi-dimensional coordinates such as 2D or 3D spaces.
 *
 * To learn more about the bin tree please visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/Bintree.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 9, 2013
 * @param <Key>
 * @param <Element>
 */
public class BinTree<Key extends Comparable, Element> implements
	DictionaryInterface<Key, Element> {

    private double minimumXAxis;
    private double maximumXAxis;
    private double minimumYAxis;
    private double maximumYAxis;

    private BinTreeNode emptyLeafNode; // flyweight
    private int size;

    /**
     * Create a new BinTree object.
     *
     * @param minimumXAxis
     * @param maximumXAxis
     * @param minimumYAxis
     * @param maximumYAxis
     */
    public BinTree(double minimumXAxis, double maximumXAxis,
	    double minimumYAxis, double maximumYAxis) {
	this.minimumXAxis = minimumXAxis;
	this.maximumXAxis = maximumXAxis;
	this.minimumYAxis = minimumYAxis;
	this.maximumYAxis = maximumYAxis;

	// create a flyweight leaf node here to represent a single
	// empty leaf node since on average, half of the leaf nodes in a
	// Bintree are empty
	this.emptyLeafNode = new EmptyBinTreeNode();
	this.size = 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public void insert(Key key, Element element) {
	// TODO: when splitting an X coordinate of 180.0, the coordinate goes
	// to the right side of the dividing line, not the left

	// TODO: implement with recursion

	// spliting takes place whenever a point is to be inserted into a leaf
	// node that already contains a point

	// similar to BST insert
	// the bintree search is followed until a leaf node is found
	// if the leaf node is empty, then it can store the new point.

	// if the leaf node already contains a point, then additional work needs
	// to be done.

	// call the point already stored in the Bintree A, and the new node that
	// we want to insert B. We must split the node containing A into two,
	// replacing it with a new internal node and 2 leaf children.
	// Record A is then placed in the appropriate child, and we restart the
	// insertion from the new internal node.

	// if B falls within in the newly created leaf node, then it can be
	// inserted
	// there. But if B falls within the newly created leaf node that just
	// received A, then the splitting process must repeat.

	// Depending on how far apart A and B are, it is possible that many
	// splits
	// are required.
	this.size++;
    }

    @Override
    public Element remove(Key key) {
	// TODO: implement with recursion

	// requires that sibling leaf nodes be merged together if they are
	// empty.
	// can cause many levels of merging
	this.size--;
	return null;
    }

    @Override
    public Element removeRandomElement() {
	// TODO Auto-generated method stub
	this.size--;
	return null;
    }

    @Override
    public Element find(Key key) {
	// TODO: implement with recursion

	// pass in the coordinates when the recursive call is made

	// TODO: bintree search operation should visit no more nodes than
	// neccessary

	// is like searching a BST, except that each level of the BinTree is
	// associated with a particular discriminator.
	// if the process reaches a null pointer, then that point is not
	// contained in the tree.


	return null;
    }

    @Override
    public int size() {
	// TODO Auto-generated method stub
	return 0;
    }

    public String preorderTraversal() {
	// TODO: internal nodes = "I"
	// empty leaf nodes = "E"

	// leaf nodes that contain a data point output a new line
	// the name and coordinates of the point's position, and then another
	// new line
	return "";
    }
}
