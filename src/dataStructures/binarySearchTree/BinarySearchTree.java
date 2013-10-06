package dataStructures.binarySearchTree;

import dataStructures.interfaces.Dictionary;
import java.lang.Comparable;

/**
 * This binary search tree will is implemented in the following way:
 *
 * 1) When you delete a node that has 2 non-empty children, the deleted node
 * is replaced with the greatest valued node in the left subtree.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 4, 2013
 */
public class BinarySearchTree<Key extends Comparable<? super Key>, E>
	implements Dictionary<Key, E> {

    private BinarySearchTreeNode<Key, E> rootNode;
    private int numberOfNodes;

    public BinarySearchTree() {
	this.rootNode = null;
	this.numberOfNodes = 0;
    }

    @Override
    public void clear() {
	this.rootNode = null;
	this.numberOfNodes = 0;
    }

    @Override
    public void insert(Key key, E element) {
	this.rootNode = this.insertHelp(this.rootNode, key, element);
	this.numberOfNodes++;
    }

    @Override
    public E remove(Key key) {
	// first find the node to remove
	E nodeToRemove = this.findHelp(this.rootNode, key);
	if (nodeToRemove != null) {
	    // now remove the found node
	    this.rootNode = this.removeHelp(this.rootNode, key);
	    this.numberOfNodes--;
	}
	return nodeToRemove;
    }

    @Override
    public E removeRandomElement() {
	if (this.rootNode == null) {
	    return null;
	}
	E randomeNodeToRemove = this.rootNode.getValue();
	this.rootNode = this.removeHelp(this.rootNode, this.rootNode.getKey());
	this.numberOfNodes--;
	return randomeNodeToRemove;
    }

    @Override
    public E find(Key key) {
	return this.findHelp(this.rootNode, key);
    }

    @Override
    public int size() {
	return this.numberOfNodes;
    }

    private E findHelp(BinarySearchTreeNode<Key, E> rootNode, Key key) {
	if (rootNode == null) {
	    return null;
	}
	if (rootNode.getKey().compareTo(key) > 0) {
	    return this.findHelp(rootNode.getLeftChild(), key);
	} else if (rootNode.getKey().compareTo(key) == 0) {
	    return rootNode.getValue();
	} else {
	    return this.findHelp(rootNode.getRightChild(), key);
	}
    }

    private BinarySearchTreeNode<Key, E> insertHelp(
	    BinarySearchTreeNode<Key, E> rootNode, Key key, E element) {
	if (rootNode == null) {
	    return new BinarySearchTreeNode<Key, E>(key, element, null, null);
	}
	if (rootNode.getKey().compareTo(key) > 0) {
	    rootNode.setLeftChild(this.insertHelp(rootNode.getLeftChild(), key,
		    element));
	} else {
	    rootNode.setRightChild(this.insertHelp(rootNode.getRightChild(),
		    key, element));
	}
	return rootNode;
    }

    private BinarySearchTreeNode<Key, E> removeHelp(
	    BinarySearchTreeNode<Key, E> rootNode, Key key) {
	if (rootNode == null) {
	    return null;
	}
	if (rootNode.getKey().compareTo(key) > 0) {
	    rootNode.setLeftChild(this.removeHelp(rootNode.getLeftChild(), key));
	} else if (rootNode.getKey().compareTo(key) < 0) {
	    rootNode.setRightChild(this.removeHelp(rootNode.getRightChild(),
		    key));
	} else { // found node to remove
	    if (rootNode.getLeftChild() == null) {
		return rootNode.getRightChild();
	    } else if (rootNode.getRightChild() == null) {
		return rootNode.getLeftChild();
	    } else { // there are 2 children
		BinarySearchTreeNode<Key, E> nodeToRemove = this
			.getNodeWithMinimumValue(rootNode.getRightChild());
		rootNode.setValue(nodeToRemove.getValue());
		rootNode.setKey(nodeToRemove.getKey());
		rootNode.setRightChild(this.deleteNodeWithMinimumValue(rootNode.getRightChild()));
	    }
	}
	return rootNode;
    }

    private BinarySearchTreeNode<Key, E> getNodeWithMinimumValue(
	    BinarySearchTreeNode<Key, E> rootNode) {
	if (rootNode.getLeftChild() == null) {
	    return rootNode;
	}
	return this.getNodeWithMinimumValue(rootNode.getLeftChild());
    }

    private BinarySearchTreeNode<Key, E> deleteNodeWithMinimumValue(
	    BinarySearchTreeNode<Key, E> rootNode) {
	if (rootNode.getLeftChild() == null) {
	    return rootNode.getRightChild();
	}
	rootNode.setLeftChild(this.deleteNodeWithMinimumValue(rootNode.getLeftChild()));
	return rootNode;
    }

    // TODO: add traversal functions
}
