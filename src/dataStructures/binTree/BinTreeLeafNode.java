package dataStructures.binTree;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 12, 2013
 * @param <Key>
 *            A class with an x and y field to be used to efficiently store and
 *            find an Element into the BinTree.
 * @param <Element>
 *            The type to be stored in the BinTree.
 */
public class BinTreeLeafNode<Key extends Point, Element> extends
	BinTreeNode<Element> {
    private Key key;
    private Element element;

    /**
     * Create a new BinTreeLeafNode object.
     *
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

    @Override
    public String toString() {
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("\n====Bin Tree Leaf Node====");
	stringBuilder.append("\nkey: ");
	stringBuilder.append(this.key.toString());
	stringBuilder.append("\n   element: ");
	stringBuilder.append(this.element.toString());
	stringBuilder.append("\n==========================");
	String binTreeInformation = stringBuilder.toString();
	return binTreeInformation;
    }
}
