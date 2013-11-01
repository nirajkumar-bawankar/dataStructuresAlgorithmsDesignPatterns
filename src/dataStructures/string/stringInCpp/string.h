#ifndef STRING_H_
#define STRING_H_

#include <cstdlib>

class string {
 public:
 	// Initializes an empty string.
	string();

	// Initializes a string based on the given C string.
	string(const char*);

	// Initializes a string from an already existing string.  The contents
	// of the existing string should be copied over to this string.
	string(const string&);

	// Sets this string equal to another.  The contents of the string should
	// be copied over to this string.
	string& operator=(const string&);

	// Frees up memory for this string.
	~string();

	// Returns true if the two strings have the same contents.
	bool operator==(const string&) const;

	// Concatenates a string to this string.
	string& operator+=(const string&);

	// Returns the ith character of this string.
	const char& operator[](int) const;

	// Returns a C-style string representing this string.
	const char* c_str() const;

	// Returns the length of this string.
	int length() const;

 private:
 	char* str;
	int len;
};

class out_of_range { };

// Copies the C string in the second argument into the first argument,
// including the null terminator.
char* strcpy(char*, const char*);

// Appends the second string to the first.  The null terminator of the
// first string is overwritten by the first character of the second string,
// and a new null terminator is inserted at the end of the result.
char* strcat(char*, const char*);

// Compares the two arguments.  If they are equal, 0 is returned.  If they
// are not equal, the difference of the first unequal pair of characters
// is returned.
int strcmp(const char*, const char*);

// Returns a pointer to the first occurence of the given character in the
// given string.
const char* strchr(const char*, char);

// Returns a pointer to the first character in the first argument that
// appears in the second argument.
const char* strpbrk(const char*, const char*);

// Scans the first string for the occurence of any character in the second
// string.  The number of characters scanned prior to this occurence is
// returned.
int strcspn(const char*, const char*);

// Returns the number of initial characters in the first argument that
// only contain letters in the second argument.
int strspn(const char*, const char*);

// Returns a pointer to the first occurence of the second argument in
// the first, and null otherwise.
const char* strstr(const char*, const char*);

// Returns the length of the string, excluding the null terminator.
int strlen(const char*);

#endif /* STRING_H_ */
