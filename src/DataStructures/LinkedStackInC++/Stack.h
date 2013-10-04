#ifndef STACK_H_
#define STACK_H_

class Stack {
public:
	Stack();
	~Stack();
	void push(int);
	int pop();
	int getTopElement() const;
	int size() const;
	bool isEmpty() const { return num_elements == 0; }

private:
	struct node {
		int value;
		node* next;
	};

	node* head;
	int num_elements;
};

#endif // STACK_H_
