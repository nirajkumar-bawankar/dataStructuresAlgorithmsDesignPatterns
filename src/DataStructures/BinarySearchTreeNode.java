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

    public Key getKey() {
	return this.key;
    }

    public void setKey(Key key) {
	this.key = key;
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
    public BinarySearchTreeNode<Key, E> getLeftChild() {
	return this.leftChild;
    }

    public void setLeftChild(BinarySearchTreeNode<Key, E> leftChild) {
	this.leftChild = leftChild;
    }

    @Override
    public BinarySearchTreeNode<Key, E> getRightChild() {
	return this.rightChild;
    }

    public void setRightChild(BinarySearchTreeNode<Key, E> rightChild) {
	this.rightChild = rightChild;
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
