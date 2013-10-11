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
 * @param <E>
 *            the item type to be stored in the leaf nodes of the 2D bin tree
 */
public class BinTree2D<Key extends Point, E> implements
	DictionaryInterface<Key, E> {
    /**
     * Create a flyweight leaf node to represent a single empty leaf node since
     * on average, half of the leaf nodes in a BinTree are empty.
     */
    private BinTreeNode<E> emptyLeafNodeFlyweight;

    private BinTreeNode<E> rootNode;

    private double minimumXAxis;
    private double maximumXAxis;
    private double minimumYAxis;
    private double maximumYAxis;

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
	// TODO: make sure xaxis and yaxis are all positive and max > min
	// for axis x and y
	this.emptyLeafNodeFlyweight = BinTreeEmptyNode.getInstance();
	this.rootNode = this.emptyLeafNodeFlyweight;

	this.minimumXAxis = minimumXAxis;
	this.maximumXAxis = maximumXAxis;
	this.minimumYAxis = minimumYAxis;
	this.maximumYAxis = maximumYAxis;
	this.size = 0;
    }

    /**
     * Recursive insertion of a new element.
     */
    @Override
    public void insert(Key key, E element) {
	this.rootNode = this.insertHelp(this.rootNode, this.maximumXAxis
		- this.minimumXAxis, this.maximumYAxis - this.minimumYAxis,
		key, element, true);
	this.size++;
    }

    /**
     * This insert forces the BinTree to have multi-dimensional keys of lowest
     * value in the leftmost leaf and multi-dimensional keys of greatest value
     * in the rightmost leaf of the BinTree.
     *
     * @param node
     * @param xAxis
     * @param yAxis
     * @param key
     * @param element
     * @return a subtree
     */
    public BinTreeNode<E> insertHelp(BinTreeNode<E> node, double xAxis,
	    double yAxis, Key key, E element, boolean isSplittingXAxis) {
	// While traversing the tree the changing rootNode parameter
	// will most often be an BinTreeInternalNode (so this is checked first),
	// then rootNode parameter will on average be the same as
	// BinTreeLeafNode and Empty

	// find a leaf node
	if (node instanceof BinTreeInternalNode) {
	    double xAxisMidpoint = xAxis / 2;
	    double yAxisMidpoint = yAxis / 2;

	    if (isSplittingXAxis) {
		isSplittingXAxis = false; // so y-axis can be split next
		// time
		if (key.getX() < xAxisMidpoint) { // current node should go to
						  // left subtree
		    // TODO: determine if left child should be an internal node
		    ((BinTreeInternalNode<E>) node).setLeftChild(this
			    .insertHelp(((BinTreeInternalNode<E>) node)
				    .getLeftChild(), xAxisMidpoint, yAxis, key,
				    element, isSplittingXAxis));
		} else { // current node should go to right subtree
		    // TODO: determine if right child should be an internal node
		    ((BinTreeInternalNode<E>) node).setRightChild(this
			    .insertHelp(((BinTreeInternalNode<E>) node)
				    .getRightChild(), xAxisMidpoint, yAxis,
				    key, element, isSplittingXAxis));
		}
	    } else { // splitting y-axis
		isSplittingXAxis = true; // so x-axis can be split next time
		if (key.getY() < yAxisMidpoint) { // current node should go to
						  // left subtree
		    // TODO: determine if left child should be an internal node
		    ((BinTreeInternalNode<E>) node).setLeftChild(this
			    .insertHelp(((BinTreeInternalNode<E>) node)
				    .getLeftChild(), xAxis, yAxisMidpoint, key,
				    element, isSplittingXAxis));
		} else { // current node should go to right subtree
			 // TODO: determine if right child should be an internal
			 // node
		    ((BinTreeInternalNode<E>) node).setRightChild(this
			    .insertHelp(((BinTreeInternalNode<E>) node)
				    .getRightChild(), xAxis, yAxisMidpoint,
				    key, element, isSplittingXAxis));
		}
	    }
	} else if (node instanceof BinTreeEmptyNode) {
	    // make sure that no other internal nodes need to be created by
	    // comparing key x and y point to current xAxisMidpoint and
	    // yAxisMidpoint

	    // return a new leaf node with the element to insert and do not
	    // worry about splitting
	    return new BinTreeLeafNode<E>(element);
	} else { // this is BinTreeLeafNode that is not empty

	    // one of the children will be set to the tempNode and other
	    // will be set with the element that has been continuous passed
	    // through this recursive method
	    BinTreeLeafNode<E> tempNode = (BinTreeLeafNode<E>) node; // save A
	    node = new BinTreeInternalNode<E>();

	    // Call the point key already stored in the BinTree A, and
	    // the new node that we want to insert with key B.
	    // We need to split the node containing key A into 3, replacing
	    // it with a new internal node, 1 leaf child, and 1 empty leaf
	    // child.
	    // Key A is then placed in the appropriate leaf child
	    // and a recursive call is made on the new internal node

	    // return currentRootNode with correct left and right child set
	    // element = B

	    // insert element previously in the bin tree A in the correct place
	    // in the new bin tree structure
	    this.insertHelp(node, xAxis, yAxis, key, tempNode.getData(), !isSplittingXAxis);

	    // finally insert the new element B
	    this.insertHelp(node, xAxis, yAxis, key, element, !isSplittingXAxis);

	    return node;
	}
	throw new IllegalArgumentException(
		"In class BinTree2D method insertHelp the parameter node"
			+ "must be of type BinTreeInternalNode, "
			+ "EmptyBinTreeNode, or BinTreeLeafNode");
    }

    @Override
    public E remove(Key key) {
	// TODO: implement with recursion

	// requires that sibling leaf nodes be merged together if they are
	// empty.
	// can cause many levels of merging
	this.size--;
	return null;
    }

    @Override
    public E removeRandomElement() {
	// TODO Auto-generated method stub
	this.size--;
	return null;
    }

    @Override
    public E find(Key key) {
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
