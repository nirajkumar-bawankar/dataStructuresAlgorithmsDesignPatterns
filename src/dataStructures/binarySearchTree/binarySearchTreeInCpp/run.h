#include <cstdlib>
#include <stdio.h>

namespace run {
	int num_failed = 0;
	int num_tests = 0;
	int mem_blocks = 0;

	#define CHECK(cond) \
		if(!(cond)) \
		{ \
			printf("Test \'%s\' failed (%s:%d)\n", #cond, __FILE__, __LINE__); \
			run::num_failed++; \
		} \
		run::num_tests++;

	struct Grader {
		~Grader() {
			printf("Results:\n");
			printf("Tests passed: %d\n", num_tests - num_failed);
			printf("Tests failed: %d\n", num_failed);
			printf("Overall grade: %.2f%%\n", (1 - ((float)num_failed / (float)num_tests)) * 100);

			if(mem_blocks != 0) {
				printf("%d blocks of memory were allocated, but never deleted\n", mem_blocks);
			} else {
				printf("All blocks of memory allocated with new were successfully deleted\n");
			}
		}
	};
	static Grader g;
}

void* operator new(size_t size) {
	run::mem_blocks++;
	return malloc(size);
}

void operator delete(void* p) {
	run::mem_blocks--;
	free(p);
}

void* operator new[](size_t size) {
	run::mem_blocks++;
	return malloc(size);
}

void operator delete[](void* p) {
	run::mem_blocks--;
	free(p);
}
