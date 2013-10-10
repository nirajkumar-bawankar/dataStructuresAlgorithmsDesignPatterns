package dataStructures.binTree;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 9, 2013
 */
public class BinTreeLeafNode<E> extends BinTreeNode<E> {
    private E data;
    // TODO: cannot store bounding box coordinates in bin tree nodes
    // cannot store a pointer to this node's parent
    public BinTreeLeafNode(E data) {
	// no children nodes, just data fields
	this.data = data;
    }

    public E getData() {
	return this.data;
    }

    public void setData(E data) {
	this.data = data;
    }
}
