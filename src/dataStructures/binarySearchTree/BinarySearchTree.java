package dataStructures.binarySearchTree;

import dataStructures.interfaces.DictionaryInterface;
import java.lang.Comparable;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Oct 4, 2013
 */
public class BinarySearchTree<Key extends Comparable<? super Key>, Element>
	implements DictionaryInterface<Key, Element> {

    private BinarySearchTreeNode<Key, Element> rootNode;
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
    public void insert(Key key, Element element) {
	this.rootNode = this.insertHelp(this.rootNode, key, element);
	this.numberOfNodes++;
    }

    @Override
    public Element remove(Key key) {
	// first find the node to remove
	Element nodeToRemove = this.findHelp(this.rootNode, key);
	if (nodeToRemove != null) {
	    // now remove the found node
	    this.rootNode = this.removeHelp(this.rootNode, key);
	    this.numberOfNodes--;
	}
	return nodeToRemove;
    }

    @Override
    public Element removeRandomElement() {
	if (this.rootNode == null) {
	    return null;
	}
	Element randomeNodeToRemove = this.rootNode.getValue();
	this.rootNode = this.removeHelp(this.rootNode, this.rootNode.getKey());
	this.numberOfNodes--;
	return randomeNodeToRemove;
    }

    @Override
    public Element find(Key key) {
	return this.findHelp(this.rootNode, key);
    }

    @Override
    public int size() {
	return this.numberOfNodes;
    }

    private Element findHelp(BinarySearchTreeNode<Key, Element> rootNode, Key key) {
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

    private BinarySearchTreeNode<Key, Element> insertHelp(
	    BinarySearchTreeNode<Key, Element> rootNode, Key key, Element element) {
	if (rootNode == null) {
	    return new BinarySearchTreeNode<Key, Element>(key, element, null, null);
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

    private BinarySearchTreeNode<Key, Element> removeHelp(
	    BinarySearchTreeNode<Key, Element> rootNode, Key key) {
	if (rootNode == null) {
	    return null;
	}
	if (rootNode.getKey().compareTo(key) > 0) {
	    rootNode.setLeftChild(this.removeHelp(rootNode.getLeftChild(), key));
	} else if (rootNode.getKey().compareTo(key) < 0) {
	    rootNode.setRightChild(this.removeHelp(rootNode.getRightChild(),
		    key));
	} else { // found node to remove and it is now denoted as rootNode
	    if (rootNode.getLeftChild() == null) {
		return rootNode.getRightChild();
	    } else if (rootNode.getRightChild() == null) {
		return rootNode.getLeftChild();
	    } else { // there are 2 children
		// when deleting a node that has 2 non-empty children, the deleted
		// node is replaced with the greatest valued node in the left
		// subtree
		BinarySearchTreeNode<Key, Element> nodeToRemove = this
			.getNodeWithMinimumValue(rootNode.getRightChild());
		rootNode.setValue(nodeToRemove.getValue());
		rootNode.setKey(nodeToRemove.getKey());
		rootNode.setRightChild(this.deleteNodeWithMinimumValue(rootNode
			.getRightChild()));
	    }
	}
	return rootNode;
    }

    private BinarySearchTreeNode<Key, Element> getNodeWithMinimumValue(
	    BinarySearchTreeNode<Key, Element> rootNode) {
	if (rootNode.getLeftChild() == null) {
	    return rootNode;
	}
	return this.getNodeWithMinimumValue(rootNode.getLeftChild());
    }

    private BinarySearchTreeNode<Key, Element> deleteNodeWithMinimumValue(
	    BinarySearchTreeNode<Key, Element> rootNode) {
	if (rootNode.getLeftChild() == null) {
	    return rootNode.getRightChild();
	}
	rootNode.setLeftChild(this.deleteNodeWithMinimumValue(rootNode
		.getLeftChild()));
	return rootNode;
    }

    // TODO: add traversal functions
}
