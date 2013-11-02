#include <cstdio>
#include <cstdlib>

namespace test {
int numberOfTestsFailed = 0;
int numberOfTotalTests = 0;
int memoryBlocksAllocatedDuringTesting = 0;

#define CHECK(condition) \
		if(!(condition)) \
		{ \
			printf("Test \'%s\' Failed (%s:%d)\n", #condition, __FILE__, __LINE__); \
			test::numberOfTestsFailed++; \
		} \
		test::numberOfTotalTests++;

struct Tester {
	~Tester() {
		printf(
				"=======================String Test Results=======================\n");
		printf("Tests Passed: %d\n", numberOfTotalTests - numberOfTestsFailed);
		printf("Tests Failed: %d\n", numberOfTestsFailed);
		printf("Overall Percentage of Tests Passed: %.2f%%\n",
				(1 - ((float) numberOfTestsFailed / (float) numberOfTotalTests))
						* 100);

		if (memoryBlocksAllocatedDuringTesting != 0) {
			printf("%d blocks of memory were allocated, but never deleted\n",
					memoryBlocksAllocatedDuringTesting);
		} else {
			printf(
					"All blocks of memory allocated with new were successfully deleted\n");
		}
		printf(
				"=================================================================\n");
	}
};

static Tester tester;
}

void* operator new(size_t size) {
	test::memoryBlocksAllocatedDuringTesting++;
	return malloc(size);
}

void operator delete(void* p) {
	test::memoryBlocksAllocatedDuringTesting--;
	free(p);
}

void* operator new[](size_t size) {
	test::memoryBlocksAllocatedDuringTesting++;
	return malloc(size);
}

void operator delete[](void* p) {
	test::memoryBlocksAllocatedDuringTesting--;
	free(p);
}
