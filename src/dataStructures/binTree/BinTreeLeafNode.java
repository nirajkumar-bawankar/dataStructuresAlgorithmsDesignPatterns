package dataStructures.binTree;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 9, 2013
 * @param <Element>
 */
public class BinTreeLeafNode<Element> extends BinTreeNode<Element> {
    private Element data;

    /**
     * Create a new BinTreeLeafNode object.
     *
     * @param data
     */
    public BinTreeLeafNode(Element data) {
	this.data = data;
    }

    /**
     * @return the data stored in this bin tree leaf node.
     */
    public Element getData() {
	return this.data;
    }

    /**
     * @param data
     *            the new data being stored in this leaf node.
     */
    public void setData(Element data) {
	this.data = data;
    }
}
