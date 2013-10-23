#ifndef STRING_H
#define STRING_H

struct _String {
	int length;
	int size;
	char *sentence;
};

typedef struct _String String;

/**
 * Initializes a String with the value of sentence.
 */
String_Init(String* const pointerToString, const char* const sentence);

String* StringCopy(String* destination, const String* const source);

#endif
