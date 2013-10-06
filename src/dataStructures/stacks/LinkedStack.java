package dataStructures.stacks;

import dataStructures.linkedList.SinglyLinkedListNode;

import dataStructures.interfaces.StackInterface;

/**
 * To learn more about the class please visit:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/Stack.html
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Sep 13, 2013
 * @param <E>
 */
public class LinkedStack<E> implements StackInterface<E> {
    private SinglyLinkedListNode<E> top;
    private int length;

    /**
     * Create a new LinkedStack object.
     */
    public LinkedStack() {
	this.top = null;
	this.length = 0;
    }

    @Override
    public boolean push(E item) {
	this.top = new SinglyLinkedListNode<E>(item, this.top);
	this.length++;
	return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() {
	if (this.top == null) {
	    return null;
	} else {
	    Object item = this.top.getValue();
	    this.top = this.top.getNextNode();
	    this.length--;
	    return (E) item;
	}
    }

    @Override
    public E topValue() {
	if (this.top == null) {
	    return null;
	} else {
	    return this.top.getValue();
	}
    }

    @Override
    public int length() {
	return this.length;
    }

    @Override
    public void clear() {
	this.top = null;
	this.length = 0;
    }
}
