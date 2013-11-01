#include "string.h"
#include <cstdio>
/////////////////////////////////////////////////////////////////
// String class member functions
/////////////////////////////////////////////////////////////////
// William Vuong; PID: wnvuong
// Quinn Liu; PID: quinnliu


// Default constructor for this class. Initializes an empty string.
string::string() { 
    // Make char array of size one with only null terminator
    str = new char[1];
    str[0] = '\0';
    len = 0;
}

// Constructor for String class. Initializes a string based on the given C string.
string::string(const char* inputString) {
    len = strlen(inputString);
    str = new char[len + 1];
    // Make char array of the inputString length + 1 (for null terminator)
    // then use strcpy)
    if (str != NULL) {
        strcpy(str, inputString);
    }
}

// Copy constructor for String class. 
// Initializes a string from an already existing string.  
// The contents of the existing string should be copied over to this string.
string::string(const string& inputString) {
    len = strlen(inputString.str);
    str = new char[len + 1];
    // Same Logic as the above constructor
    if (str != NULL) {
        strcpy(str, inputString.str);
    }
}

// Sets this string equal to another.
//The contents of the string should be copied over to this string.
string& string::operator=(const string& inputString) {
    // str has already been allocated, so juse use strcpy
    strcpy(str, inputString.str);
    len = strlen(str);
    return *this;
}

// Destructor for String class. Frees up memory for this string.
string::~string() {
    // Free allocated memory
    delete[] str;
    len = 0;
}

// Returns true if the two strings have the same contents.
bool string::operator==(const string& inputString) const {
	if (strcmp(str, inputString.str) == 0) {
        return true;
    }       
    return false;
}

// Concatenates a string to this string.
string& string::operator+=(const string& inputString) {
	str = strcat(str, inputString.str);
    len = strlen(str);
    return *this;
}

// Returns the ith character of this string.
const char& string::operator[](int index) const {
	if (index >= 0 && index < len) {
        return str[index];
    }
    else {
        throw out_of_range();
    }
}

// Returns a C-style string representing this string.
// It should returns char * which points to the beginning of this string
const char* string::c_str() const {
	return str;
}

// Returns the length of this string.
int string::length() const {
	return len;
}

/////////////////////////////////////////////////////////////////
// Non class member functions
/////////////////////////////////////////////////////////////////

// Copies the C string in the second argument into the first argument,
// including the null terminator.
char* strcpy(char* dest, const char* source) {
	int i = 0;
    // Traverse source array copying characters
    while (source[i] != '\0') {
        dest[i] = source[i];
        i++;
    }
    // Add null terminator
    dest[i] = '\0';
    return dest;
}

// Appends the second string to the first.  The null terminator of the
// first string is overwritten by the first character of the second string,
// and a new null terminator is inserted at the end of the result.
char* strcat(char* dest, const char* append) {
    // End is the locatin of the null terminator of the destination string
    int end = strlen(dest);
    // Size of the string to be appended
    int appendSize = strlen(append);
    for (int i = 0; i < appendSize; i++) {
        // First iteration will replace the null terminator
        dest[end] = append[i];
        end++;
    }
    // Increment one more time to add the null terminator
    end++;
    dest[end] = '\0';
    return dest;
}

// Compares the two arguments.  If they are equal, 0 is returned.  If they
// are not equal, the difference of the first unequal pair of characters
// is returned.
int strcmp(const char* string1, const char* string2) {
    int i = 0;
    while (string1[i] != '\0' || string2[i] != '\0') {
        // Traverse both strings and at first time they dont equal
        // return the difference
        if (string1[i] != string2[i]) {
            int difference = string1[i] - string2[i];
            return difference;
        }
        else {
            i++;
        }
    }
    return 0;
}

// Returns a pointer to the first occurrence of the given character in the
// given string.
const char* strchr(const char* source, char find) {
    int i = 0;
    while (source[i] != '\0') {
        // Traverse source array, and whne character to find
        // is matched return its location
        if (source[i] == find) {
            return &source[i];
        }
        i++;
    }
    return NULL;
}

// Returns a pointer to the first character in the first argument that
// appears in the second argument.
const char* strpbrk(const char* s1, const char* s2) {
    int i = 0;
    while (s1[i] != '\0') {
        int j = 0;
        while (s2[j] != '\0') {
            // Traverse both arrays and upon first occurence where the characters
            // match return the location in the first string of the match
            if (s1[i] == s2[j]) {
                return &s1[i];
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
    int numCharScanned = -1;
    int i = 0;
    while (s1[i] != '\0') {
        int j = 0;
        // Increment counter for number of characters
        // scanned, start at -1 since first match means no characters
        // were scanned.
        numCharScanned++;
        while (s2[j] != '\0') {
            if (s1[i] == s2[j]) { 
                return numCharScanned;
            }
            j++;
        }
        i++;
    }
    return numCharScanned;

}

// Returns the number of initial characters in the first argument that
// only contain letters in the second argument.
int strspn(const char* s1, const char* s2) {
	int numMatched = 0;
    int i = 0;
    while (s2[i] != '\0') {
        // Compare each character of 2nd string to see
        // if it is in the first one, if so
        // increment the number matched
        if (strchr(s1, s2[i]) != NULL) {
            numMatched++;    
        }
        i++;
    }
    return numMatched;
}

// Returns a pointer to the first occurrence of the second argument in
// the first, and null otherwise.
const char* strstr(const char* s1, const char* s2) {
    int i = 0;
    // Iterate first string..
    while (s1[i] != '\0') {
        int j = 0;
        int start = i;
        int matchIterator = i;
        // Until first character of first string
        // matches first character of second string
        if (s1[matchIterator] == s2[j]) {
            // Iterate both strings while these match
            while (s1[matchIterator] == s2[j]) {
                matchIterator++;
                j++;
            }
            // If the number of iterations is greater than the length
            // of the second string return the location in the first
            // string of the starting point of the match.
            if (j >= strlen(s2)) {
                return &s1[start];
            }
        }
        i++;
    }
    return NULL;
}

// Returns the length of the string, excluding the null terminator.
int strlen(const char* inputString) {
    int i = 0;
    while (*inputString) {
        inputString++;
        i++;
    }
    return i;
}
