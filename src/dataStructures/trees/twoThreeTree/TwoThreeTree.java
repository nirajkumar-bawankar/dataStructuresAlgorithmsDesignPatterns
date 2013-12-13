package dataStructures.trees.twoThreeTree;

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
}
