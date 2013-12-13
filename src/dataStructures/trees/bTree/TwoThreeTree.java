package dataStructures.trees.bTree;

/**
 * A 2-3 Tree has the following properties: 1) A node contains 1 or 2 keys. 2)
 * Every internal node has (1 key && 2 children) || (2 keys && 3 chilren). 3)
 * All leaves are at the same level in the 2-3 tree creating a height balanced
 * tree. 4) For every node, the values of all descendants in the left subtree
 * are < than the value of the 1st key. Values in the center subtree are >= the
 * value of the 1st key. If has 2 keys, values of all descendants in the center
 * subtree are less than the value of 2nd key. Values in the right subtree are
 * >= the value of the 2nd key.
 *
 * To learn more about the 2-3 tree data structure please visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/TwoThreeTree.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Dec 12, 2013
 * @param <K>
 * @param <E>
 */
public class TwoThreeTree<K extends Comparable<? super K>, E> {

    private K leftKey;
    private E leftValue;

    private K rightKey;
    private E rightValue;

    private TwoThreeTree<K, E> leftChild;
    private TwoThreeTree<K, E> centerChild;
    private TwoThreeTree<K, E> rightChild;

    public TwoThreeTree() {
	this.centerChild = this.leftChild = this.rightChild = null;
    }

    public TwoThreeTree(K leftKey, E leftValue, K rightKey, E rightValue,
	    TwoThreeTree<K, E> leftChild, TwoThreeTree<K, E> centerChild,
	    TwoThreeTree<K, E> rightChild) {
	this.leftKey = leftKey;
	this.leftValue = leftValue;

	this.rightKey = rightKey;
	this.rightValue = rightValue;

	this.leftChild = leftChild;
	this.centerChild = centerChild;
	this.rightChild = rightChild;
    }

    // TODO: insert(), remove()

    public E remove() {
	// TODO:
	// Case 1) remove a record from a leaf node, just remove the record
	// 	   and no other nodes are affected

	// Case 2) when the only record in a leaf node is to be removed

	// Case 3) when a record is to be removed from an internal node
	return null;
    }

    private E findhelp(TwoThreeTree<K, E> root, K k) {
	if (root == null) {
	    return null; // value not found
	}
	if (k.compareTo(root.getLeftKey()) == 0) {
	    return root.getLeftValue();
	}
	if ((root.getRightKey() != null)
		&& (k.compareTo(root.getRightKey()) == 0)) {
	    return root.getRightValue();
	}

	if (k.compareTo(root.getLeftKey()) < 0) {// search left
	    return findhelp(root.getLeftChild(), k);
	} else if (root.getRightKey() == null) { // search center
	    return findhelp(root.getCenterChild(), k);
	} else if (k.compareTo(root.getRightKey()) < 0) { // search center
	    return findhelp(root.getCenterChild(), k);
	} else {
	    return findhelp(root.getRightChild(), k); // search right
	}
    }

    public boolean isLeaf() {
	return this.leftChild == null;
    }

    public K getLeftKey() {
	return this.leftKey;
    }

    public void setLeftKey(K leftKey) {
	this.leftKey = leftKey;
    }

    public E getLeftValue() {
	return this.leftValue;
    }

    public void setLeftValue(E leftValue) {
	this.leftValue = leftValue;
    }

    public K getRightKey() {
	return this.rightKey;
    }

    public void setRightKey(K rightKey) {
	this.rightKey = rightKey;
    }

    public E getRightValue() {
	return this.rightValue;
    }

    public void setRightValue(E rightValue) {
	this.rightValue = rightValue;
    }

    public TwoThreeTree<K, E> getLeftChild() {
	return this.leftChild;
    }

    public void setLeftChild(TwoThreeTree<K, E> leftChild) {
	this.leftChild = leftChild;
    }

    public TwoThreeTree<K, E> getCenterChild() {
	return this.centerChild;
    }

    public void setCenterChild(TwoThreeTree<K, E> centerChild) {
	this.centerChild = centerChild;
    }

    public TwoThreeTree<K, E> getRightChild() {
	return this.rightChild;
    }

    public void setRightChild(TwoThreeTree<K, E> rightChild) {
	this.rightChild = rightChild;
    }
}
