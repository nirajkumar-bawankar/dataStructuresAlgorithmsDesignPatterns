package dataStructures.binTree;

/**
 * The internal nodes of a bin tree only store references to it's left and right
 * child.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 9, 2013
 */
public class BinTreeInternalNode<Element> extends BinTreeNode<Element> {
    BinTreeNode<Element> leftChild;
    BinTreeNode<Element> rightChild;

    /**
     * Create a new BinTreeInternalNode object with 2 empty bin tree leaf nodes.
     */
    public BinTreeInternalNode() {
	this.leftChild = EmptyBinTreeNode.getInstance();
	this.rightChild = EmptyBinTreeNode.getInstance();
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
