#include "run.h"
#include "Stack.h"

int main(int argc, char** argv) {
	Stack stack;

	if (argc > 1) {
		int numOfElements;
		numOfElements = atoi(argv[1]);

		if (numOfElements > 0) {
			for (int i = 0; i < numOfElements; ++i) {
				stack.push(i);
				CHECK(stack.getTopElement() == i);
			}

			CHECK(stack.size() == numOfElements);

			for (int i = numOfElements; i >= 0; --i) {
				CHECK(stack.pop() == i);
			}

			CHECK(stack.size() == 0);
		}
	} else {
		for (int i = 0; i < 10; ++i) {
			stack.push(i);
			CHECK(stack.getTopElement() == i);
		}

		CHECK(stack.size() == 10);

		for (int i = 9; i >= 0; --i) {
			CHECK(stack.pop() == i);
		}

		CHECK(stack.size() == 0);
	}

	return 0;
}
