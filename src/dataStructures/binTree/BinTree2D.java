package dataStructures.binTree;

import dataStructures.interfaces.DictionaryInterface;
import dataStructures.binTree.Point;

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
 * @param <K>
 *            a 2 dimensional point type in space such as a (x, y) coordinate
 *            that extends the Point class
 * @param <E>
 *            the item type to be stored in the leaf nodes of the 2D bin tree
 */
public class BinTree2D<K extends Point, E> implements DictionaryInterface<K, E> {
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
    public void insert(K key, E element) {
	// world coordinates
	BoundingBox world = new BoundingBox(new Point(this.minimumXAxis,
		this.minimumYAxis), this.maximumXAxis, this.maximumYAxis);

	this.rootNode = this.insertHelp(this.rootNode, world, key, element,
		true);
	this.size++;
    }

    /**
     * This insert forces the BinTree to have multi-dimensional keys of lowest
     * value in the leftmost leaf and multi-dimensional keys of greatest value
     * in the rightmost leaf of the BinTree.
     *
     * @param node
     * @param currentWorld
     * @param key
     * @param element
     * @param isSplittingXAxis
     * @return a subtree
     */
    public BinTreeNode<E> insertHelp(BinTreeNode<E> node,
	    BoundingBox currentWorld, K key, E element, boolean isSplittingXAxis) {
	// in a bin tree with many elements
	if (node instanceof BinTreeInternalNode<?>) {
	    if (isSplittingXAxis) {
		isSplittingXAxis = false; // so y-axis can be split next time
		if (key.getX() < currentWorld.getCurrentXAxis()) {
		    // current node should go to left subtree
		    currentWorld.changeToLeftHalfBoundingBox();

		    // recursive call on left child until base case of empty
		    // node is reached
		    ((BinTreeInternalNode<E>) node).setLeftChild(this
			    .insertHelp(((BinTreeInternalNode<E>) node)
				    .getLeftChild(), currentWorld, key,
				    element, isSplittingXAxis));
		} else { // current node should go to right subtree
		    currentWorld.changeToRightHalfBoundingBox();

		    // recursive call on right child until base case of empty
		    // node is reached
		    ((BinTreeInternalNode<E>) node).setRightChild(this
			    .insertHelp(((BinTreeInternalNode<E>) node)
				    .getRightChild(), currentWorld, key,
				    element, isSplittingXAxis));
		}
	    } else { // splitting y-axis
		isSplittingXAxis = true; // so x-axis can be split next time
		if (key.getY() < currentWorld.getCurrentYAxis()) {
		    currentWorld.changeToBottomHalfBoundingBox();

		    ((BinTreeInternalNode<E>) node).setLeftChild(this
			    .insertHelp(((BinTreeInternalNode<E>) node)
				    .getLeftChild(), currentWorld, key,
				    element, isSplittingXAxis));
		} else {
		    currentWorld.changeToTopHalfBoundingBox();

		    ((BinTreeInternalNode<E>) node).setRightChild(this
			    .insertHelp(((BinTreeInternalNode<E>) node)
				    .getRightChild(), currentWorld, key,
				    element, isSplittingXAxis));
		}
	    }
	} else if (node instanceof BinTreeEmptyNode) {
	    return new BinTreeLeafNode<K, E>(key, element);
	} else if (node instanceof BinTreeLeafNode<?, ?>) { // this is
	    // BinTreeLeafNode
	    // that is not empty
	    BinTreeLeafNode<K, E> tempNode = (BinTreeLeafNode<K, E>) node;

	    node = new BinTreeInternalNode<E>();

	    this.insertHelp(node, currentWorld, tempNode.getKey(),
		    tempNode.getElement(), isSplittingXAxis);

	    this.insertHelp(node, currentWorld, key, element, isSplittingXAxis);

	    return node;
	}
	return node;
    }

    @Override
    public E remove(K key) {
	// TODO: implement with recursion

	// requires that sibling leaf nodes be merged together if they are
	// empty.
	// can cause many levels of merging
	this.size--;
	return null;
    }

    public void regionSearch() {
	// TODO: with recursion
    }

    @Override
    public E removeRandomElement() {
	// TODO Auto-generated method stub
	this.size--;
	return null;
    }

    @Override
    public E find(K key) {
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
	this.rootNode = this.emptyLeafNodeFlyweight;
	this.size = 0;
    }

    @Override
    public int size() {
	return this.size;
    }

    /**
     * Starting at the rootNode of the bin tree this method prints the
     * following: 1) "I" for internal nodes. 2) "E" for empty leaf nodes. 3) a
     * new line and the key and element of a leaf node and then another new
     * line.
     *
     * @param rootNode
     * @return
     */
    public String preorderTraversal(BinTreeNode<E> rootNode) {
	StringBuilder stringBuilder = new StringBuilder();
	if (rootNode instanceof BinTreeEmptyNode<?>) {
	    return "E\n";
	} else if (rootNode instanceof BinTreeInternalNode<?>) {
	    stringBuilder.append("I\n");
	    stringBuilder.append(this
		    .preorderTraversal(((BinTreeInternalNode<E>) rootNode)
			    .getLeftChild()));
	    stringBuilder.append(this
		    .preorderTraversal(((BinTreeInternalNode<E>) rootNode)
			    .getRightChild()));
	} else if (rootNode instanceof BinTreeLeafNode<?, ?>) {
	    stringBuilder
	    .append((((BinTreeLeafNode<K, E>) rootNode)
			    .getElement()).toString()
		    + " "
		    + ((BinTreeLeafNode<K, E>) rootNode).getKey()
			    .getX()
		    + " "
		    + ((BinTreeLeafNode<K, E>) rootNode).getKey()
				    .getY() + "\n");
	}
	return stringBuilder.toString();
    }

    BinTreeNode<E> getRootNode() {
	return this.rootNode;
    }

    void setRootNode(BinTreeNode<E> rootNode) {
	this.rootNode = rootNode;
    }
}
