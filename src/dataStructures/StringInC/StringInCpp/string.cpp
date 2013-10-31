#include "string.h"
#include <cmath>
#include <cstring>

/////////////////////////////////////////////////////////////////
// String class member functions
/////////////////////////////////////////////////////////////////

// Default constructor for this class. Initializes an empty string.
string::string() {
	this->numberOfChars = 0;
	this->charArray = new char[this->numberOfChars + 1]; // make room for terminating char '\0'
	this->charArray[this->numberOfChars] = '\0';
}

// Constructor for String class. Initializes a string based on the given C string.
string::string(const char* pointerToCharArray) {
	this->numberOfChars = strlen(pointerToCharArray);
	this->charArray = new char[this->numberOfChars + 1]; // make room for terminating char '\0'

	// Instead of using a for loop where you check i once per character and
	// write once per character, by using memcpy this can be done 8 times at
	// a time.
	memcpy(this->charArray, pointerToCharArray, this->numberOfChars + 1);
}

// Copy constructor for String class. 
// Initializes a string from an already existing string.  
// The contents of the existing string should be copied over to this string.
string::string(const string& string) {
	this->numberOfChars = string.numberOfChars;
	this->charArray = new char[this->numberOfChars + 1];
	for (int i = 0; i < (this->numberOfChars); i++) {
		this->charArray[i] = string.charArray[i];
	}

	if (this->charArray[this->numberOfChars + 1] != '\0') {
		this->charArray[this->numberOfChars + 1] = '\0';
	}
}

// Sets this string equal to another.
//The contents of the string should be copied over to this string.
string& string::operator=(const string& string) {
	this->string(string);
	return *this;
}

// Destructor for String class. Frees up memory for this string.
string::~string() {
	delete[] this->charArray;
}

// Returns true if the two strings have the same contents.
bool string::operator==(const string& string) const {
	if (this->numberOfChars == string.numberOfChars) {
		for (int i = 0; i < (this->numberOfChars + 1); i++) {
			if (this->charArray[i] != string.charArray[i]) {
				return false;
			}
		}
		return true;
	} else {
		return false;
	}
}

// Concatenates a string to this string.
string& string::operator+=(const string& string) {
	// TODO: implement
	return *this;
}

// Returns the ith character of this string.
const char& string::operator[](int index) const {
	// check if index is between 0 and the null terminating character
	if (index >= 0 && index < this->numberOfChars) {
		return this->charArray[index];
	} else {
		// return out_of_range; TODO: fix
		return 'z';
	}
}

// Returns a C-style string representing this string.
// It should returns char * which points to the beginning of this string
const char* string::c_str() const {
	return charArray;
}

// Returns the length of this string.
int string::length() const {
	return this->numberOfChars;
}

/////////////////////////////////////////////////////////////////
// Non class member functions
/////////////////////////////////////////////////////////////////

// Copies the C string in the second argument into the first argument,
// including the null terminator.
char* strcpy(char* copiedCharArray, const char* sourceCharArray) {
	int sourceCharArrayLengthWithTerminatingChar = strlen(sourceCharArray) + 1;
	copiedCharArray = new char[sourceCharArrayLengthWithTerminatingChar];
	for (int i = 0; i < sourceCharArrayLengthWithTerminatingChar; i++) {
		copiedCharArray[i] = sourceCharArray[i];
	}
	return copiedCharArray;
}

// Appends the second string to the first.  The null terminator of the
// first string is overwritten by the first character of the second string,
// and a new null terminator is inserted at the end of the result.
char* strcat(char* string1, const char* string2) {
	int concatinatedStringLength = strlen(string1) + strlen(string2);
	int concatinatedStringLengthWithTerminatingChar = concatinatedStringLength
			+ 1;
	char* concatinatedString =
			new char[concatinatedStringLengthWithTerminatingChar];
	for (int i = 0; i < strlen(string1); i++) {
		concatinatedString[i] = string1[i];
	}

	// overwrite the null terminator of the first string with the beginning of the
	// second string
	int j = strlen(string1) + 1; // initially j is where to begin writing the second string
	int i = 0; // begin index for the second string
	while (j < concatinatedStringLength) {
		concatinatedString[j] = string2[i];
		j++;
		i++;
	}
	return concatinatedString;
}

// Compares the two arguments.  If they are equal, 0 is returned.  If they
// are not equal, the difference of the first unequal pair of characters
// is returned.
int strcmp(const char* charPointerToArray1, const char* charPointerToArray2) {
	int i = 0;
	// we need to check if both arrays have reached their terminating character
	// because consider the case where array1 = { '\0' } and array2 = { '1', '\0' }
	while (charPointerToArray1[i] != '\0' || charPointerToArray2[i] != '\0') {
		// while iterating through both char arrays,
		// if 1 char difference is found, the 2 char arrays are not equal
		if (charPointerToArray1[i] != charPointerToArray2[i]) {
			int differenceOfFirstUnequalChars = charPointerToArray1[i]
					- charPointerToArray2[i];
			return differenceOfFirstUnequalChars;
		} else {
			i++;
		}
	}
	return 0; // charPointerToArray1 == charPointerToArray2
}

// Returns a pointer to the first occurrence of the given character in the
// given string.
const char* strchr(const char* pointerToCharArray, char charToFind) {
	int i = 0;
	while (pointerToCharArray[i] != '\0') {
		if (pointerToCharArray[i] == charToFind) {
			return &pointerToCharArray[i]; // or string + i;
		}
	}
	// if not found return an empty string
	static const char *empty = "";
	return empty;
}

// Returns a pointer to the first character in the first argument that
// appears in the second argument.
const char* strpbrk(const char* pointerToCharArray1,
		const char* pointerToCharArray2) {
	int i = 0;
	while (pointerToCharArray1[i] != '\0') {
		int j = 0;
		while (pointerToCharArray2[j] != '\0') {
			if (pointerToCharArray1[i] == pointerToCharArray2[j]) {
				return &pointerToCharArray1[i];
			}
			j++;
		}
		i++;
	}
	// if not found return an empty string
	static const char *empty = "";
	return empty;
}

// Scans the first string for the occurrence of any character in the second
// string.  The number of characters scanned prior to this occurrence is returned.
// Return -1 if two strings have no characters that are the same.
int strcspn(const char* pointerToCharArray1, const char* pointerToCharArray2) {
	int numberOfCharactersChecked = 0;
	int i = 0;
	while (pointerToCharArray1[i] != '\0') {
		int j = 0;
		numberOfCharactersChecked++;
		while (pointerToCharArray2[j] != '\0') {
			numberOfCharactersChecked++;
			if (pointerToCharArray1[i] == pointerToCharArray2[j]) {
				return numberOfCharactersChecked;
			}
			j++;
		}
		i++;
	}
	return -1;
}

// Returns the number of initial characters in the first argument that
// only contain letters in the second argument.
int strspn(const char* pointerToCharArray1, const char* pointerToCharArray2) {
	int numberOfInitialCharactersInString1ThatAreAlsoInString2 = 0;
	int i = 0;
	while (pointerToCharArray1[i] != '\0') {
		// if the char in string1 exists within string 2 increment our
		// counter to be returned
		if (strchr(pointerToCharArray2, pointerToCharArray1[i])) {
			numberOfInitialCharactersInString1ThatAreAlsoInString2++;
			i++;
		} else {
			break;
		}
	}
	return numberOfInitialCharactersInString1ThatAreAlsoInString2;
}

// Returns a pointer to the first occurrence of the second argument in
// the first, and null otherwise.
const char* strstr(const char* charPointerToArray1,
		const char* charPointerToArray2) {
	int i = 0;
	while (charPointerToArray1[i] != '\0') {
		if (charPointerToArray1[i] == charPointerToArray2[0]) {
			// found a char in charPointerToArray1 that begins with the first char of charPointerToArray2
			char* pointerToFirstOccurenceOfSecondStringInFirstString = nullptr;
			bool foundMatch = true; // assume they are a match
			int j = 0;
			while (charPointerToArray2[j] != '\0') {
				// now check if the next (strlen(charPointerToArray2)) chars are the same as well
				if (string[i] != charPointerToArray2[j]) {
					foundMatch = false;
					break; // move on to next char in charPointerToArray1 that matches first char in
					// charPointerToArray2
				}
				j++;
			}
			if (foundMatch) {
				return pointerToFirstOccurenceOfSecondStringInFirstString;
			}
		}
		i++;
	}
	return nullptr; // no match was found
}

// Returns the length of the string, excluding the null terminator.
int strlen(const char* charPointerToArray) {
	int i = 0;
	while (charPointerToArray[i] != '\0') {
		i++;
	}
	return i;
}
