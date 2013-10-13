package dataStructures.binTree;

/**
 * The internal nodes of a bin tree only store references to it's left and right
 * child.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 12, 2013
 * @param <Element>
 */
public class BinTreeInternalNode<Element> extends BinTreeNode<Element> {
    /**
     * Current internal node left child.
     */
    BinTreeNode<Element> leftChild;

    /**
     * Current internal node right child.
     */
    BinTreeNode<Element> rightChild;

    /**
     * Create a new BinTreeInternalNode object with 2 empty bin tree leaf nodes.
     */
    @SuppressWarnings("unchecked")
    public BinTreeInternalNode() {
	this.leftChild = BinTreeEmptyNode.getInstance();
	this.rightChild = BinTreeEmptyNode.getInstance();
    }

    /**
     * @return left child node
     */
    public BinTreeNode<Element> getLeftChild() {
	return this.leftChild;
    }

    /**
     * @param leftChild
     *            the new left child node
     */
    public void setLeftChild(BinTreeNode<Element> leftChild) {
	this.leftChild = leftChild;
    }

    /**
     * @return left child node
     */
    public BinTreeNode<Element> getRightChild() {
	return this.rightChild;
    }

    /**
     * @param rightChild
     *            the new right child node
     */
    public void setRightChild(BinTreeNode<Element> rightChild) {
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
