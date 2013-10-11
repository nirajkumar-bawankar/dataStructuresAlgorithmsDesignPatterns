package dataStructures.binTree;

import dataStructures.interfaces.DictionaryInterface;
import java.awt.Point;

/**
 * The bin tree is a spatial data structure that can be used to unify search
 * across any arbitrary set of keys. Most commonly it is used to efficiently
 * search through on multi-dimensional coordinates such as 2D or 3D spaces. This
 * bin tree efficiently stores 2 dimensional keys.
 *
 * To learn more about the bin tree please visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/Bintree.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 10, 2013
 * @param <Key>
 *            a 2 dimensional point type in space such as a (x, y) coordinate
 *            that extends the Point class
 * @param <Element>
 *            the item type to be stored in the leaf nodes of the 2D bin tree
 */
public class BinTree2D<Key extends Point, Element> implements
	DictionaryInterface<Key, Element> {
    /**
     * Create a flyweight leaf node to represent a single empty leaf node since
     * on average, half of the leaf nodes in a BinTree are empty.
     */
    private BinTreeNode emptyLeafNodeFlyweight;

    private BinTreeNode<Element> rootNode;

    private double minimumXAxis;
    private double maximumXAxis;
    private double minimumYAxis;
    private double maximumYAxis;

    /**
     * If true, split along x-axis; otherwise split along y-axis.
     */
    private boolean isSplitingXAxis;

    /**
     * Number of leaf nodes with data in this bin tree.
     */
    private int size;

    /**
     * Create a new BinTree object.
     *
     * @param minimumXAxis
     * @param maximumXAxis
     * @param minimumYAxis
     * @param maximumYAxis
     */
    public BinTree2D(double minimumXAxis, double maximumXAxis,
	    double minimumYAxis, double maximumYAxis) {
	this.emptyLeafNodeFlyweight = EmptyBinTreeNode.getInstance();
	this.rootNode = this.emptyLeafNodeFlyweight;

	this.minimumXAxis = minimumXAxis;
	this.maximumXAxis = maximumXAxis;
	this.minimumYAxis = minimumYAxis;
	this.maximumYAxis = maximumYAxis;
	this.isSplitingXAxis = true; // arbitrarily start splitting x-axis of
				     // 2 dimensional world first
	this.size = 0;
    }

    /**
     * Recursive insertion of a new element.
     */
    @Override
    public void insert(Key key, Element element) {
	this.rootNode = this.insertHelp(this.rootNode, this.maximumXAxis
		- this.minimumXAxis, this.maximumYAxis - this.minimumYAxis,
		key, element);
	this.size++;
    }

    /**
     * This insert forces the BinTree to have multi-dimensional keys of lowest
     * value in the leftmost leaf and multi-dimensional keys of greatest value
     * in the rightmost leaf of the BinTree.
     */
    public BinTreeNode<Element> insertHelp(BinTreeNode<Element> node,
	    double xAxis, double yAxis, Key key, Element element) {
	double xAxisMidpoint = xAxis / 2;
	double yAxisMidpoint = yAxis / 2;

	// While traversing the tree the changing rootNode parameter
	// will most often be an BinTreeInternalNode (so this is checked first),
	// then rootNode parameter will on average be the same as
	// BinTreeLeafNode and Empty

	// find a leaf node
	if (node instanceof BinTreeInternalNode) {
	    if (this.isSplitingXAxis) {
		this.isSplitingXAxis = false; // so y-axis can be split next
		// time
		if (key.getX() < xAxisMidpoint) {
		    this.insertHelp(
			    ((BinTreeInternalNode) node).getLeftChild(),
			    xAxisMidpoint, yAxis, key, element);
		} else { // current node should go on right side
		    this.insertHelp(
			    ((BinTreeInternalNode) node).getRightChild(),
			    xAxisMidpoint, yAxis, key, element);
		}
	    } else { // splitting y-axis
		this.isSplitingXAxis = true; // so x-axis can be split next time
		if (key.getY() < yAxisMidpoint) {
		    this.insertHelp(
			    ((BinTreeInternalNode) node).getLeftChild(), xAxis,
			    yAxisMidpoint, key, element);
		} else {
		    this.insertHelp(
			    ((BinTreeInternalNode) node).getRightChild(),
			    xAxis, yAxisMidpoint, key, element);
		}
	    }
	} else if (node instanceof EmptyBinTreeNode) {
	    // return a new leaf node with the element to insert and do not
	    // worry about splitting
	    return new BinTreeLeafNode<Element>(element);
	} else { // this is BinTreeLeafNode that is not empty

	    // one of the children will be set to the tempNode and other
	    // will be set with the element that has been continuous passed
	    // through this recursive method
	    BinTreeNode<Element> tempNode = node; // save A
	    node = new BinTreeInternalNode<Element>();

	    // Call the point key already stored in the BinTree A, and
	    // the new node that we want to insert with key B.
	    // We need to split the node containing key A into 3, replacing
	    // it with a new internal node, 1 leaf child, and 1 empty leaf
	    // child.
	    // Key A is then placed in the appropriate leaf child
	    // and a recursive call is made on the new internal node

	    // return currentRootNode with correct left and right child set
	    return this.insertHelp(node, xAxisMidpoint, yAxisMidpoint, key,
		    element);
	}
	throw new IllegalArgumentException(
		"In class BinTree2D method insertHelp the parameter node"
			+ "must be of type BinTreeInternalNode, "
			+ "EmptyBinTreeNode, or BinTreeLeafNode");
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
    public void clear() {

    }

    @Override
    public int size() {
	// TODO Auto-generated method stub
	return this.size;
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
