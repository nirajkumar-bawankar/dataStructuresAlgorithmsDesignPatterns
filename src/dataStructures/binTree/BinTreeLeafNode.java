package dataStructures.binTree;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 9, 2013
 * @param <Key>
 * @param <Element>
 */
public class BinTreeLeafNode<Key, Element> extends BinTreeNode<Element> {
    private Key key;
    private Element element;

    /**
     * Create a new BinTreeLeafNode object.
     * @param key
     * @param element
     */
    public BinTreeLeafNode(Key key, Element element) {
	this.key = key;
	this.element = element;
    }

    /**
     * @return the key stored in this bin tree leaf node
     */
    public Key getKey() {
	return this.key;
    }

    /**
     * @return the element stored in this bin tree leaf node.
     */
    public Element getElement() {
	return this.element;
    }

    /**
     * @param element
     *            the new element being stored in this leaf node.
     */
    public void setElement(Element element) {
	this.element = element;
    }
}
