#ifndef BASICC_H
#define BASICC_H
#include <stdbool.h>
#include <stdint.h>

struct _DataTypeName {
	int property1;
	int property2;
};

typedef struct _DataTypeName DataTypeName;

bool function(int integer);

DataTypeName DataTypeName_Construct(int property1, int property2);

void DataTypeName_init(DataTypeName* dataTypeName, int property1, int property2);

uint64_t dynamicArrayFunction(uint16_t n);

void arrayAsParameter(int array[]);

void pointerExample();

void pointersAsParameters(uint32_t* p1, uint32_t* p2);

uint64_t stringlength(const char* string[]);

void stringCopy(char* destination, const char* source);

void findExtrema(const int* const Array, int size, int* const pToMin,
		int* const pToMax);

int* returningAPointerToArray(int arraySize);

#endif
