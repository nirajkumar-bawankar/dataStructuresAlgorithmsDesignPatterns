#include <iostream>
#include <cstdlib>

namespace Run {
int failedTests = 0;
int totalTests = 0;
int blocksOfMemory = 0;

#define CHECK(cond) \
                if(!(cond)) \
                { \
                        std::cout << "Test \'" << #cond << "\' failed (" << __FILE__ << ":" << __LINE__ << ")" << std::endl; \
                        Run::failedTests++; \
                } \
                Run::totalTests++;

struct Grader {
	~Grader() {
		std::cout << "_______________________________________\n";
		std::cout << "\nResults:\n" << std::endl;
		std::cout << "Tests passed: " << (totalTests - failedTests)
				<< std::endl;
		std::cout << "Tests failed: " << failedTests << std::endl;
		std::cout << "Correctness: "
				<< (1 - ((double) failedTests / (double) totalTests)) * 100
				<< "%" << std::endl;

		if (blocksOfMemory != 0) {
			std::cout << blocksOfMemory
					<< " blocks of memory were allocated, but never deleted\n"
					<< std::endl;
		} else {
			std::cout
					<< "All blocks of memory allocated with new were successfully deleted\n"
					<< std::endl;
		}
	}
};
static Grader g;
}

void* operator new(size_t size) {
	Run::blocksOfMemory++;
	return malloc(size);
}

void operator delete(void* p) {
	Run::blocksOfMemory--;
	free(p);
}
