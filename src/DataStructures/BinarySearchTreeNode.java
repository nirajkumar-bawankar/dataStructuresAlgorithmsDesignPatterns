package DataStructures;

import DataStructures.Interfaces.BinNode;

/**
 * @version Oct 2, 2013
 */
public class BinarySearchTreeNode<Key, E> implements BinNode<E> {
    private Key key;
    private E value;

    private BinarySearchTreeNode<Key, E> leftChild;
    private BinarySearchTreeNode<Key, E> rightChild;

    public BinarySearchTreeNode(Key key, E value,
	    BinarySearchTreeNode<Key, E> leftChild,
	    BinarySearchTreeNode<Key, E> rightChild) {
	this.key = key;
	this.value = value;
	this.leftChild = leftChild;
	this.rightChild = rightChild;
    }

    @Override
    public E getValue() {
	return this.value;
    }

    @Override
    public void setValue(E value) {
	this.value = value;
    }

    @Override
    public BinNode<E> getLeftChild() {
	return this.leftChild;
    }

    @Override
    public BinNode<E> getRightChild() {
	return this.rightChild;
    }

    @Override
    public boolean isLeaf() {
	if (this.leftChild == null && this.rightChild == null) {
	    return true;
	} else {
	    return false;
	}
    }
}
