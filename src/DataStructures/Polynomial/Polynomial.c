/* 
 * Instructions for running this class using the terminal:
 * 1) Make sure you have gcc installed and the files
 *    "Polynomial.h", "Polynomial.c", and "Test_Polynomial.c"
 *    within a folder
 * 2) Move to that folder using the terminal and run the command:
 *    gcc -o Test_Polynomial -m32 -std=c99 -Wall Test_Polynomial.c
 *    Polynomial.c
 * 3) Now type to the terminal:
 *    ./Test_Polynomial
 * 4) OMG It works! Enjoy!
 */
#include <stdbool.h>
#include <stdlib.h>
#include "Polynomial.h"

static int64_t power(int64_t x, uint64_t y);

/**
 * Initializes *P as described below.
 *
 * Pre:  P points to an uninitialized Polynomial object,
 *       C != NULL,
 *       C[i] initialized for i = 0:D
 * Post: P->Degree == D,
 *       P->Coeff != C (array is duplicated, not linked),
 *       P->Coeff[i] == C[i] for i = 0:D
 * Returns: false if *P cannot be properly initialized, true otherwise
 */bool Polynomial_Set(Polynomial* const P, const uint8_t D,
		const int64_t* const C) {
	if (P == NULL || C == NULL ) {
		return false;
	}
	P->Degree = D;

	P->Coeff = malloc((D + 1) * sizeof(int64_t));

	// malloc returns NULL if block of memory cannot be allocated
	if (P == NULL ) {
		return false;
	} else {
		for (int i = 0; i <= P->Degree; i++) {
			P->Coeff[i] = C[i];
		}
		return true;
	}
}

/**
 * Initializes *Target from *Source as described below.
 *
 * Pre:  Target points to a Polynomial object,
 *       Source points to a properly-initialized Polynomial object
 * Post: Target->Degree == Source->Degree,
 *       Target->Coeff != Source->Coeff,
 *       Target->Coeff[i] == Source->Coeff[i] for i = 0:Source->Degree
 * Returns: false if *Target cannot be properly initialized, true otherwise
 */bool Polynomial_Copy(Polynomial* const Target, const Polynomial* const Source) {
	return Polynomial_Set(Target, Source->Degree, Source->Coeff);
}

/**
 * Compares two polynomials.
 *
 * Pre:  Left points to a properly-initialized Polynomial object,
 *       Right points to a properly-initialized Polynomial object
 * Returns: true if Left and Right have the same coefficients, false otherwise
 */bool Polynomial_Equals(const Polynomial* const Left,
		const Polynomial* const Right) {
	if (Left->Degree != Right->Degree) {
		return false;
	}

	for (int i = 0; i <= Left->Degree; i++) {
		if (Left->Coeff[i] != Right->Coeff[i]) {
			return false;
		}
	}
	return true; // since all coefficients are equal between
	// the two polynomials
}

/**
 * Computes value of polynomial at X.
 *
 * Pre:  P points to a properly-initialized Polynomial object
 * Returns: value of P(X); 0 if cannot be evaluated
 */
int64_t Polynomial_EvaluateAt(const Polynomial* const P, const int64_t X) {
	if (P == NULL ) {
		return 0;
	}
	//TODO: check for overflow, only 2^63 bits can be used to represent the evaluated number
	int64_t result = 0;

	for (int i = 0; i <= P->Degree; i++) {
		int64_t termCoefficient = P->Coeff[i]; // 3
		int64_t termResult = termCoefficient * power(X, i);
		result = result + termResult;
	}
	return result;
}

/**
 * Initializes *Scaled to represent K times *Source
 *
 * Pre:  Scaled points to a Polynomial object,
 *       Source points to a properly-initialized Polynomial object,
 *       Source != Target
 * Post: Scaled->Degree == Source->Degree,
 *       Scaled->Coeff  != Source->Coeff,
 *       Scaled->Coeff[i] == K * Source->Coeff[i] for i = 0:Scaled->Degree
 * Returns: false if *Scaled cannot be properly initialized, true otherwise
 */bool Polynomial_Scale(Polynomial* const Scaled, const Polynomial* const Source,
		const int64_t K) {
	if (Polynomial_Copy(Scaled, Source) == false || Scaled == NULL
			|| Source == NULL ) {
		return false;
	} else {
		for (int i = 0; i <= Scaled->Degree; i++) {
			Scaled->Coeff[i] = K * Scaled->Coeff[i];
		}
		return true;
	}
}

/**
 * Initializes *Sum to equal *Left + *Right.
 *
 * Pre:  Sum points to a Polynomial object,
 *       Left points to a properly-initialized Polynomial object,
 *       Right points to a properly-initialized Polynomial object,
 *       Sum != Left,
 *       Sum != Right
 * Post: Sum->Degree == max(Left->Degree, Right->Degree),
 *       Sum->Coeff[i] == Left->Coeff[i] + Right->Coeff[i] 
 *           with proper allowance for the case that 
 *           Left->Degree != Right->Degree
 * Returns: false if *Sum cannot be properly initialized, true otherwise
 */bool Polynomial_Add(Polynomial* const Sum, const Polynomial* const Left,
		const Polynomial* const Right) {
	if (Sum == NULL || Left == NULL || Right == NULL ) {
		return false;
	}

	if (Left->Degree > Right->Degree) {
		if (Polynomial_Set(Sum, Left->Degree, Left->Coeff)) {
			for (int i = 0; i <= Right->Degree; i++) {
				Sum->Coeff[i] = Sum->Coeff[i] + Right->Coeff[i];
			}
			Sum->Degree = Left->Degree;
		} else {
			return false;
		}
	} else { // Right polynomial > Left polynomial || Right polynomial == Left polynomial
		if (Polynomial_Set(Sum, Right->Degree, Right->Coeff)) {
			for (int i = 0; i <= Left->Degree; i++) {
				Sum->Coeff[i] = Sum->Coeff[i] + Left->Coeff[i];
			}
			Sum->Degree = Right->Degree;
		} else {
			return false;
		}
	}

	// Consider the case where largest degrees has the same coefficients
	// for Left and Right polynomial and cancel out each other lowering
	// the degree by one. ex. Largest term in each polynomial are
	// 2X^7 and -2X^7 means the Sum will have a degree less than 7.
	// The exact same problem can reoccur for the next polynomial term as 
	// well.

	//  1 + 3X^1 + 3X^2 + 4X^3
	// -1 - 2X^1 - 3X^2 - 4X^3
	int sumDegree = Sum->Degree;
	while(Sum->Coeff[sumDegree] == 0 && sumDegree >= 0) {
		Sum->Degree -= 1;
		sumDegree--;
	}
	
	return true;
}

/**
 * Initializes *Diff to equal *Left - *Right.
 *
 * Pre:  Diff points to a Polynomial object,
 *       Left points to a properly-initialized Polynomial object,
 *       Right points to a properly-initialized Polynomial object,
 *       Diff != Left,
 *       Diff != Right
 * Post: Diff->Degree is set correctly,
 *       Diff->Coeff[i] == Left->Coeff[i] - Right->Coeff[i] 
 *           with proper allowance for the case that 
 *           Left->Degree != Right->Degree
 * Returns: false if *Diff cannot be properly initialized, true otherwise
 */bool Polynomial_Subtract(Polynomial* const Diff, const Polynomial* const Left,
		const Polynomial* const Right) {
	if (Diff == NULL || Left == NULL || Right == NULL ) {
		return false;
	}
	for (int i = 0; i <= Right->Degree; i++) {
		Right->Coeff[i] = -Right->Coeff[i];
	}
	return Polynomial_Add(Diff, Left, Right);
}

/**
 * Computes the first derivative of Source.
 *
 * Pre:  Target points to a Polynomial object,
 *       Source points to a properly-initialized Polynomial object,
 *       Target != Source
 * Post: Target->Degree is set correctly
 *       Target->Coeff[i] == iith coefficient of Source'
 *
 * Returns: false if Source' cannot be properly initialized, true otherwise
 */bool Polynomial_Differentiate(Polynomial* const Target,
		const Polynomial* const Source) {
	if (Target == NULL || Source == NULL ) {
		return false;
	}
	// EXAMPLE:
	//Source->Coeff    0         1          2        3
	//               1X^0 +   2X^1 +     3X^2 +   4X^3
	//       0   + 2*1X^0 +   2*3X^1 + 3*4X^2

	//free(Target->Coeff);
	if (Source->Degree == 0) {
		Polynomial_Zero(Source);
		Polynomial_Copy(Target, Source);
		return true;
	}

	Target->Degree = Source->Degree - 1;
	// add 1 to Target->Degree because one additional space is need to
	// hold a int64_t at Target->Coeff[0]
	Target->Coeff = malloc((Target->Degree + 1) * sizeof(int64_t));
	for (int degree = 1; degree <= Source->Degree; degree++) {
		//Target->Coeff[0]      =   1    *           2          = 2
		//Target->Coeff[1]      =   2    *           3          = 6
		//Target->Coeff[2]      =   3    *           4          = 12
		Target->Coeff[degree-1] = degree * Source->Coeff[degree];
	}
	return true;
}

/**
 * Reset P to represent zero polynomial.
 *
 * Pre:  P points to a Polynomial object
 * Post: P->Degree == 0
 *       P->Coeff is set appropriately
 */bool Polynomial_Zero(Polynomial* const P) {
	if (P == NULL ) {
		return false;
	} else {
		free(P->Coeff);
		P->Degree = 0;

		P->Coeff = malloc((P->Degree + 1) * sizeof(int64_t));
		P->Coeff[0] = 0;

		return true;
	}
}

/**
 * power function that calculated x raised to the
 * power y in O(log N).
 */
static int64_t power(int64_t x, uint64_t y) {
	int temp;
	if (y == 0) {
		return 1;
	}
	temp = power(x, y / 2);
	if (y % 2 == 0) {
		return temp * temp;
	} else {
		return x * temp * temp;
	}
}
