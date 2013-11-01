#include <stdio.h>
#include "String.h"


int main(void) {
	String s1, s2;

	String_Init(&s1, "This is a test");
	String_Init(&s2, "Another test");

	s2 = s1;
	// This compiles and does make a copy of s1, but it is a
	// shallow copy. This works fine for structures that
	// contain only values but doesn't work so well
	// with pointers or arrays.

	// s2 receives a copy of the values in s1. So copying the value
	// "14" from s1.length to s2.length works fine, but
	// copying s1.sentence into s2.sentence only copies the
	// pointer address contained within s1.sentence.
	// Let's say s1.sentence is an address like 0xFFFF5641,
	// s2.sentence will end up with a copy of that value,
	// rather than a copy of the array of chars. They both
	// point to the same region of memory and changes to one will
	// result in changes to the other.

	// Further in the process of making the shallow copy the
	// original malloc'd address is lost. This causes a
	// memory leak.
	return 0;
}

String_Init(String* const pointerToString, const char* const sentence) {
	pointerToString->length = strlen(sentence);
	pointerToString->size = pointerToString->length + 1;

	pointerToString->sentence = (char*) malloc(
			sizeof(char) * (pointerToString->size));
	strncpy(pointerToString->sentence, sentence, pointerToString->size);
}

/**
 * Copies the values of a String struct from src into dest.
 *
 * Returns: NULL if the copy function encountered an error,
 *          A pointer to destination if successful.
 */
String* StringCopy(String* destination, const String* const source) {
	// copies the statically allocated variables.
	destination->size = source->size;
	destination->length = source->length;

	// if you don't free is already in destination->sentence then there is a
	// memory leak
	free(destination->sentence); // destination was dynamically allocated

	// make space for the copied string in destination
	strncpy(destination->sentence, source->sentence, source->size);

	return destination;
}
