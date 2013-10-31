#include <stdio.h>
#include <stdint.h>
#include "Fibonacci.h"

int main(void) {
	int nFactorial = Fibonacci(10);
	printf("----------Test Fibonacci(uint16_t N)----------\n");
	printf("Fibonacci(10) = 89 = %d\n", nFactorial);
	return 0;
}

/**
 * Computes Fibonacci_N, iteratively, using a dynamically-
 * allocated array to store Fibonacci_0 through Fibonacci_N
 * as they are calculated.
 *
 * Returns: if no overflow, the N-th Fibonacci number;
 * 			if overflow, probably the incorrect value.
 */
uint64_t Fibonacci(uint16_t N) {
	if (N < 2) {
		return 1;
	}
	uint64_t* FibonacciList = malloc( (N + 1) * sizeof(uint64_t) );
	if (FibonacciList == NULL) {
		return 0;
	}

	FibonacciList[0] = FibonacciList[1] = 1;

	for (int i = 2; i <= N; i++) {
		FibonacciList[i] = FibonacciList[i-1] + FibonacciList[i-2];
	}

	uint64_t FibonacciNumberN = FibonacciList[N];

	free(FibonacciList);

	return FibonacciNumberN;
}
