package dataStructures.hashTable;

/**
 * To learn about hash functions please vist:
 * http://algoviz.org/OpenDSA/Books/CS3114PM/html/HashIntro.html
 *
 * Probability of a collision function: 1 - (t!)/((t-n)!(t^n)) where t is the
 * table size and n is the number of records inserted.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Nov 4, 2013
 */
public class HashFunctions {
    public static void integerBinningHashFunction() {
	// TODO: implement
    }


    /**
     * This is a good hash function to use with integer key values. This method
     * squares the key value, and then takes out the middle r bits of the
     * result, giving a value in the range 0 to 2^r - 1. This works well because
     * most of all bits of the key value contribute to the result.
     */
    public static void integerMidSquareHashFunction() {
	// TODO: implement
    }

    /**
     * Sums the ASCII values of the letters in a string. If the hash table size
     * M is small compared to the resulting summations, then this hash function
     * should do a good job of distributing strings evenly among the hash table
     * slots, because it gives equal weight to all characters in the string.
     *
     * @return
     *
     */
    public static int stringSummationHashFunction(String x, int M) {
	char ch[];
	ch = x.toCharArray();
	int xlength = x.length();

	int i, sum;
	for (sum = 0, i = 0; i < x.length(); i++) {
	    sum += ch[i];
	}
	return sum % M;
    }

    /**
     * Processes the string 4 bytes at a time, and interprets each of the 4 byte
     * chunks as a single long integer value. The integer values for the 4 byte
     * chunks are added together. In the end, the resulting sum is converted to
     * the range 0 to (tableSize - 1) using the modulus operator.
     *
     * For example, if the string "aaaabbbb" is passed in, the the 1st 4 bytes
     * "aaaa" will be interpreted as the integer value 1,633,771,873, and the
     * next 4 bytes "bbbb" will be interpreted as the integer value
     * 1,650,614,882. Their sum is 3,284,386,755 (when treated as an unsigned
     * integer). If the table size is 101 then the modulus function will cause
     * this key to hash to slot 75 in the table.
     *
     * Note: for any sufficiently long string, the sum for the integer
     * quantities will typically cause a 32-bit integer to overflow (thus losing
     * some of the high-order bits) because the resulting values are so large.
     * But this causes no problems when the goal is to compute a hash function.
     *
     * @param stringKey
     * @param tableSize
     * @return
     */
    public static long stringFoldingHashFunction(String stringKey, int tableSize) {
	int fourByteChunks = stringKey.length() / 4; // 4 is arbitrary chosen

	long sumOfASCIICharsInString = 0;
	for (int j = 0; j < fourByteChunks; j++) {
	    char c[] = stringKey.substring(j * 4, (j * 4) + 4).toCharArray();
	    long mult = 1;
	    for (int k = 0; k < c.length; k++) {
		sumOfASCIICharsInString += c[k] * mult;
		mult *= 256;
	    }
	}

	char c[] = stringKey.substring(fourByteChunks * 4).toCharArray();
	long mult = 1;
	for (int k = 0; k < c.length; k++) {
	    sumOfASCIICharsInString += c[k] * mult;
	    mult *= 256;
	}
	return (Math.abs(sumOfASCIICharsInString) % tableSize);
    }
}
