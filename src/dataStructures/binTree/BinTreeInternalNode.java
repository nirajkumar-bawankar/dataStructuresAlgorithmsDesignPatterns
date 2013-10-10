package dataStructures.binTree;

/**
 * The internal nodes of a bin tree only store references to it's left and right
 * child.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 9, 2013
 */
public class BinTreeInternalNode extends BinTreeNode {
    BinTreeNode leftChild;
    BinTreeNode rightChild;

    public BinTreeInternalNode(BinTreeNode leftChild, BinTreeNode rightChild) {
	this.leftChild = leftChild;
	this.rightChild = rightChild;
    }

    public BinTreeNode getLeftChild() {
	return this.leftChild;
    }

    public void setLeftChild(BinTreeNode leftChild) {
	this.leftChild = leftChild;
    }

    public BinTreeNode getRightChild() {
	return this.rightChild;
    }

    public void setRightChild(BinTreeNode rightChild) {
	this.rightChild = rightChild;
    }
}
