#include "Stack.h"
#include <iostream>

// Author: Quinn Liu (quinnliu@vt.edu)
// Date:   Oct 3, 2013

// Initializes an empty Stack.
Stack::Stack() {
	this->head = new node();
	this->num_elements = 0; // using nullptr instead of 0
							// to be more accurate and safe with memory addresses

	// set default values for head
	this->head->next = NULL;
	this->head->value = 0;
}

// Cleans up memory before the Stack's destruction.
Stack::~Stack() {
	while (this->head->next != NULL) {
		this->pop();
	}
	delete this->head;
}

// Pushes value onto the top of the stack.
void Stack::push(int value) {
	node* newTopNode = new node();
	newTopNode->value = value;

	// currentTopNode may be null if stack is empty
	node* currentTopNode = this->head->next;

	this->head->next = newTopNode;
	newTopNode->next = currentTopNode;

	this->num_elements++;
}

// Pops the top-most value off the stack and returns the value.
int Stack::pop() {
	if (this->isEmpty()) {
		std::cout
				<< "\nIllegal State Exception: You cannot pop an empty\
	 stack."
				<< std::endl;
		return 0;
	} else {
		node* topNodeToRemove = this->head->next;

		int topNodeToRemoveValue = topNodeToRemove->value;

		this->head->next = this->head->next->next;

		delete topNodeToRemove;
		this->num_elements--;

		return topNodeToRemoveValue;
	}
}

// Returns the value at the top of the stack.  Works similarly to pop(), but
// retains the internal structure of the stack.  That is, it does not remove
// the top-most element.
int Stack::getTopElement() const {
	if (this->isEmpty()) {
		std::cout
				<< "\nIllegal State Exception: You cannot get the top ele\
	ment of an empty stack."
				<< std::endl;
		return 0;
	} else {
		return this->head->next->value;
	}
}

// Returns the number of elements currently in the stack.
int Stack::size() const {
	return this->num_elements;
}
