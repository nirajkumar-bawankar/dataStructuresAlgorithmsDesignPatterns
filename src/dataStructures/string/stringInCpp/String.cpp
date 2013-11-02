#include "String.h"
#include <cstdio>
/////////////////////////////////////////////////////////////////
// String class member functions
/////////////////////////////////////////////////////////////////
// @author: Quinn Liu (quinnliu@vt.edu)

// Default constructor for this class. Initializes an empty string.
String::String() {
	this->charArray = new char[1]; // make room for the null terminating char
	this->charArray[0] = '\0';
	this->numberOfChars = 0;
}

// Constructor for String class. Initializes a string based on the given C string.
String::String(const char* initialCharArray) {
	this->numberOfChars = strlen(initialCharArray);
	this->charArray = new char[this->numberOfChars + 1];
	if (this->charArray != NULL) {
		strcpy(this->charArray, initialCharArray);
	}
}

// Copy constructor for String class. 
// Initializes a string from an already existing string.  
// The contents of the existing string should be copied over to this string.
String::String(const String& initialCharArray) {
	this->numberOfChars = strlen(initialCharArray.charArray);
	this->charArray = new char[this->numberOfChars + 1];
	if (this->charArray != NULL) {
		strcpy(this->charArray, initialCharArray.charArray);
	}
}

// Sets this string equal to another.
//The contents of the string should be copied over to this string.
String& String::operator=(const String& stringToBeCopiedOver) {
	// this->charArray has already been allocated, so just use strcpy
	strcpy(this->charArray, stringToBeCopiedOver.charArray);
	this->numberOfChars = strlen(this->charArray);
	return *this;
}

// Destructor for String class. Frees up memory for this string.
String::~String() {
	// Free allocated memory
	delete[] this->charArray;
	this->numberOfChars = 0;
}

// Returns true if the two strings have the same contents.
bool String::operator==(const String& inputString) const {
	if (strcmp(this->charArray, inputString.charArray) == 0) {
		return true;
	}
	return false;
}

// Concatenates a string to this string.
String& String::operator+=(const String& inputString) {
	this->charArray = strcat(this->charArray, inputString.charArray);
	this->numberOfChars = strlen(this->charArray);
	return *this;
}

// Returns the ith character of this string.
const char& String::operator[](int index) const {
	if (index >= 0 && index < this->numberOfChars) {
		return this->charArray[index];
	} else {
		throw out_of_range();
	}
}

// Returns a C-style string representing this string.
// It should returns char * which points to the beginning of this string
const char* String::c_str() const {
	return this->charArray;
}

// Returns the length of this string.
int String::length() const {
	return this->numberOfChars;
}

/////////////////////////////////////////////////////////////////
// Non class member functions
/////////////////////////////////////////////////////////////////

// Copies the C string in the second argument into the first argument,
// including the null terminator.
char* strcpy(char* destinationCharArray, const char* sourceCharArray) {
	int i = 0;
	while (sourceCharArray[i] != '\0') {
		destinationCharArray[i] = sourceCharArray[i];
		i++;
	}
	destinationCharArray[i] = '\0';
	return destinationCharArray;
}

// Appends the second string to the first.  The null terminator of the
// first string is overwritten by the first character of the second string,
// and a new null terminator is inserted at the end of the result.
char* strcat(char* destinationCharArray, const char* charArrayToAppend) {
	int nullTerminatorIndex = strlen(destinationCharArray);

	int numberOfCharsToAppend = strlen(charArrayToAppend);
	for (int i = 0; i < numberOfCharsToAppend; i++) {
		// first iteration will replace the null terminator
		destinationCharArray[nullTerminatorIndex] = charArrayToAppend[i];
		nullTerminatorIndex++;
	}
	// increment one more time to add the new null terminator in the concatenated string
	nullTerminatorIndex++;
	destinationCharArray[nullTerminatorIndex] = '\0';
	return destinationCharArray;
}

// Compares the two arguments.  If they are equal, 0 is returned.  If they
// are not equal, the difference of the first unequal pair of characters
// is returned.
int strcmp(const char* charArray1, const char* charArray2) {
	int i = 0;
	while (charArray1[i] != '\0' || charArray2[i] != '\0') {
		// while traversing both strings, the moment corresponding chars don't equal
		// return the difference
		if (charArray1[i] != charArray2[i]) {
			int differenceOfChars = charArray1[i] - charArray2[i];
			return differenceOfChars;
		} else {
			i++;
		}
	}
	return 0;
}

// Returns a pointer to the first occurrence of the given character in the
// given string.
const char* strchr(const char* sourceCharArray, char charToFind) {
	int i = 0;
	while (sourceCharArray[i] != '\0') {
		if (sourceCharArray[i] == charToFind) {
			return &sourceCharArray[i];
		}
		i++;
	}
	return NULL;
}

// Returns a pointer to the first character in the first argument that
// appears in the second argument.
const char* strpbrk(const char* charArray1, const char* charArray2) {
	int i = 0;
	while (charArray1[i] != '\0') {
		int j = 0;
		while (charArray2[j] != '\0') {
			// while traversing both strings, the first occurrence where the characters
			// match return the index in the first string of where the chars matched
			if (charArray1[i] == charArray2[j]) {
				return &charArray1[i];
			}
			j++;
		}
		i++;
	}
	return NULL;
}

// Scans the first string for the occurrence of any character in the second
// string.  The number of characters scanned prior to this occurrence is returned.
int strcspn(const char* s1, const char* s2) {
	int numberCharScanned = -1;
	int i = 0;
	while (s1[i] != '\0') {
		int j = 0;
		// Increment counter for number of characters scanned.
		// You must start at -1 since first match means no characters
		// were scanned.
		numberCharScanned++;
		while (s2[j] != '\0') {
			if (s1[i] == s2[j]) {
				return numberCharScanned;
			}
			j++;
		}
		i++;
	}
	return numberCharScanned;

}

// Returns the number of initial characters in the first argument that
// only contain letters in the second argument.
int strspn(const char* charArray1, const char* charArray2) {
	int numberOfCharsMatched = 0;
	int i = 0;
	while (charArray2[i] != '\0') {
		if (strchr(charArray1, charArray2[i]) != NULL) {
			numberOfCharsMatched++;
		}
		i++;
	}
	return numberOfCharsMatched;
}

// Returns a pointer to the first occurrence of the second argument in
// the first, and null otherwise.
const char* strstr(const char* charArray1, const char* charArray2) {
	int i = 0;
	while (charArray1[i] != '\0') {
		int j = 0;
		int startingIndexOfFoundMatchInCharArray1 = i;
		int currentMatchCounterForCharArray1 = i;

		if (charArray1[currentMatchCounterForCharArray1] == charArray2[j]) {
			// iterate both strings while there is a match
			while (charArray1[currentMatchCounterForCharArray1] == charArray2[j]) {
				currentMatchCounterForCharArray1++;
				j++;
			}
			// if the number of iterations is greater than the numberOfChars
			// of charArray2 return the location within charArray1 of the starting point of the match
			if (j >= strlen(charArray2)) {
				return &charArray1[startingIndexOfFoundMatchInCharArray1];
			}
		}
		i++;
	}
	return NULL;
}

// Returns the length of the string, excluding the null terminator.
int strlen(const char* charArray) {
	int i = 0;
	while (*charArray) {
		charArray++;
		i++;
	}
	return i;
}
