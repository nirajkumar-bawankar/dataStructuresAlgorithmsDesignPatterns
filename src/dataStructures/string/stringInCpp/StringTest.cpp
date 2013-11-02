#include <cstdio>
#include "String.h"
#include "StringTest.h"

/**
 * Test logic within class String.cpp.
 */
int main(int argc, char* argv[]) {
	{
		char str[100];
		CHECK(strcmp(strcpy(str, "foo"), "foo") == 0);
		CHECK(strcmp(str, "foobar") != 0);
		CHECK(strcmp("fo", str) != 0);

		CHECK(strlen(str) == 3);
		CHECK(strcmp(strcat(str, "bar"), "foobar") == 0);
		CHECK(strlen(str) == 6);

		CHECK(strchr(str, 'f') - str == 0);
		CHECK(strchr(str, 'b') - str == 3);
		CHECK(strchr(str, 'x') == 0);

		strcat(str, "123");
		CHECK(strpbrk(str, "1234567890") - str == 6);
		CHECK(strpbrk(str, "qxjz") == 0);

		strcpy(str, "abcdef12345");
		CHECK(strcspn(str, "1234567890") == 6);
		CHECK(strspn(str, "abcdefghijklmnop") == 6);

		strcpy(str, "foobar");
		CHECK(strstr(str, "foo") - str == 0);
		CHECK(strstr(str, "bar") - str == 3);
		CHECK(strstr(str, "bare") == 0);

		CHECK(strlen(str) == 6);
		CHECK(strlen(strcpy(str, "1234567890")) == 10);
	}

	{
		String str = "foo";
		CHECK(str == "foo");
		CHECK(!(str == "foobar"));
		CHECK(str.length() == 3);
		str += "bar";
		CHECK(str == "foobar");
		CHECK(str.length() == 6);

		String* str2 = new String();
		*str2 = str;
		CHECK(str.c_str() != str2->c_str());
		CHECK(str == *str2);
		CHECK(strcmp(str.c_str(), str2->c_str()) == 0);
		delete str2;

		try {
			CHECK(str[0] == 'f');
		} catch (out_of_range &) {
			CHECK(false);
		}

		try {
			CHECK(str[3] == 'b');
		} catch (out_of_range &) {
			CHECK(false);
		}

		try {
			CHECK(str[-1] == ' ');
		} catch (out_of_range &) {
			CHECK(true);
		}

		try {
			CHECK(str[8] == 'x');
		} catch (out_of_range &) {
			CHECK(true);
		}
	}
	return 0;
}
