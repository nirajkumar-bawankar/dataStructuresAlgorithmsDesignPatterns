package dataStructures.binarySearchTree;

import dataStructures.interfaces.BinNodeInterface;

/**
 * @version Oct 2, 2013
 */
public class BinarySearchTreeNode<Key, Element> implements BinNodeInterface<Element> {
    private Key key;
    private Element value;

    private BinarySearchTreeNode<Key, Element> leftChild;
    private BinarySearchTreeNode<Key, Element> rightChild;

    public BinarySearchTreeNode(Key key, Element value,
	    BinarySearchTreeNode<Key, Element> leftChild,
	    BinarySearchTreeNode<Key, Element> rightChild) {
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
    public Element getValue() {
	return this.value;
    }

    @Override
    public void setValue(Element value) {
	this.value = value;
    }

    @Override
    public BinarySearchTreeNode<Key, Element> getLeftChild() {
	return this.leftChild;
    }

    public void setLeftChild(BinarySearchTreeNode<Key, Element> leftChild) {
	this.leftChild = leftChild;
    }

    @Override
    public BinarySearchTreeNode<Key, Element> getRightChild() {
	return this.rightChild;
    }

    public void setRightChild(BinarySearchTreeNode<Key, Element> rightChild) {
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
