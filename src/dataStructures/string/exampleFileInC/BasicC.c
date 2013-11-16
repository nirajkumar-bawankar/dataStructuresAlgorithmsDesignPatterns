#include <stdio.h>
#include <stdbool.h>
#include <stdint.h>
#include "BasicC.h"

#define GLOBAL_FLOAT_CONSTANT 69.69f
#define ARRAY_SIZE 3
static bool helper_function(int integer);

int main(void) {
	int returned = dynamicArrayFunction(5);
	printf("returned: %d\n", returned); // returned: 8
	int array[ARRAY_SIZE];
	arrayAsParameter(array);
	for (int i = 0; i < ARRAY_SIZE; i++) {
		printf("%d, ", array[i]); // 69, 70, 71,
	}
	// pointerExample();

	uint32_t X = 6;
	uint32_t Y = 9;
	pointersAsParameters(&X, &Y); // X = 9 and Y = 6

	char word[5] = "four"; // word[6] = '\0'
	int stringLength = stringlength(word);
	printf("stringLength: %d\n", stringLength); // 10

	int Array[5] = {1, 2, 3, 4, 5};
	int arrayMin = 0, arrayMax = 0;
	findExtrema(Array, 5, &arrayMin, &arrayMax);
	// arrayMin = 1 and arrayMax = 5

	int *pToArray = returningAPointerToArray(2);
	// pToArray[0] = 2 and pToArray[1] = 2
	return 0;
}

// external linkage = shared by several files in the program
int variableWith_ExternalLinkage_StaticStorage_FileScope = 69;
// internal linkage = shared by all functions within this file
static int variableWith_InternalLinkage_StaticStorage_FileScope = 69;

bool function(int integer) {
	// no linkage = restricted to a single function
	int variableWith_NoLinkage_AutoStorage_BlockScope = 69;
	static int variableWith_NoLinkage_StaticStorage_BlockScope = 69;
	return true;
}
DataTypeName DataTypeName_Construct(int property1, int property2) {
	DataTypeName newDataTypeName;
	newDataTypeName.property1 = property1;
	newDataTypeName.property2 = property2;
	return newDataTypeName;
}
void DataTypeName_init(DataTypeName* dataTypeName, int property1, int property2) {
	dataTypeName->property1 = property1; // (*dataTypeName).property1 = property1
	dataTypeName->property2 = property2;
}
uint64_t dynamicArrayFunction(uint16_t n) {
	if (n < 2) {
		return 1;
	}
	uint64_t* dynamicArray = malloc((n + 1) * sizeof(uint64_t)); // why N+1?
	if (dynamicArray == NULL ) {
		return 0;
	}
	dynamicArray[0] = dynamicArray[1] = 1;
	for (int i = 2; i <= n; i++) {
		dynamicArray[i] = dynamicArray[i - 1] + dynamicArray[i - 2];
	}
	uint64_t value = dynamicArray[n];
	free(dynamicArray);
	return value;
}

// array is passed by reference
void arrayAsParameter(int array[]) {
	for (int i = 0; i < ARRAY_SIZE; i++) {
		array[i] = i + 69;
	}
}
void pointerExample() {
	int* p;
	// MOST common mistake is to FORGET to declare pointee!
	p = malloc(sizeof(int)); // malloc dynamically allocates block of
							 // unintialized memory and returns the address
	if (p != NULL ) { // malloc returns NULL if block of memory cannot be allocated
		*p = 5; // this is the only way to change the value of the pointer p
		if (&p == *p) { // example: 0x0010AFB == 5
			printf("memory address is equal to the target");
		} else {
			printf("memory address is NOT equal to the target");
		}
		int sum = *p + *p;
		printf("sum: %d", sum); // sum: 10
		free(p); // free can only be applied to a pointer storing the address
				 // of a target that was allocated by calling malloc()
		*p = NULL; // reset pointer to NULL
	}
	int* p2 = malloc(sizeof(int));
	*p2 = 69;
	printf("*p2 = %d", *p2);
	int** p3 = &p2; // p3 is assigned address of variable p2
	// &p2 the address of p2
	// p2 is an int*
	// &p2 is an int**
	free(p2);

	int *p4 = malloc(sizeof(int)); // p4 has a target
	int *p5 = p4; // p5 has the same target; aliases
	free(p4); // neither p4 or p5 have a target
	// *p5 = 42; // ERROR

    //uint8_t* p6 = 0x01;
    //uint16_t* p7 = *((uint16_t*)p6); // p7 = 0x01 0x02

    int* p8; // both the pointer and target can be changed
    const int* p9; // target cannot be changed, but the pointer can
    int* const p10; // target cannot be changed, but the pointer cannot be changed
    const int* const p11; // both cannot be changed
}
void pointersAsParameters(uint32_t* p1, uint32_t* p2) {
	uint32_t temp = *p1; // temp = 6
	*p1 = *p2; // X = 9
	*p2 = temp; // Y = 6
}
uint64_t stringlength(const char* string[]) {
	uint64_t length = 0;
	while (*string != '\0') {
		string++;
		length++;
	}
	return length;
}
void stringCopy(char* destination, const char* source) {
	int i = 0;
	while (true) {
		destination[i] = source[i];
		if (destination[i] == '\0') {
			break; // done copying
		} else {
			i++;
		}
	}
}
void findExtrema(const int* const Array, int size, int* const pToMin, int* const pToMax) {
	*pToMin = *pToMax = Array[0];
	for (int i = 1; i < size; i++) {
		int current = Array[i];
		if (current < *pToMin) {
			*pToMax = current;
		} else if (current > *pToMax) {
			*pToMax = current;
		}
	}
}
int* returningAPointerToArray(int arraySize) {
	int *pToArray = malloc(arraySize * sizeof(int));
	if (pToArray != NULL) {
		for (int i = 0; i < arraySize; i++) {
			pToArray[i] = 2;
		}
	}
	return pToArray; // ownership goes to caller
}
static bool helper_function(int integer) {
	// This method only has file scope (no external linkage) because
	// static was used in method header.
}
