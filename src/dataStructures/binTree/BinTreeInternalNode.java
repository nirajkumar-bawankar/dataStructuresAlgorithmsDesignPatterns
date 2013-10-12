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
	this.leftChild = BinTreeEmptyNode.getInstance();
	this.rightChild = BinTreeEmptyNode.getInstance();
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

    @Override
    public String toString() {
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("\n==Bin Tree Internal Node==");
	stringBuilder.append("\n leftChild: ");
	stringBuilder.append("\n" + this.leftChild.toString() + "\n");
	stringBuilder.append("\nrightChild:");
	stringBuilder.append("\n" + this.rightChild.toString());
	stringBuilder.append("\n==========================");
	String binTreeInformation = stringBuilder.toString();
	return binTreeInformation;
    }
}
