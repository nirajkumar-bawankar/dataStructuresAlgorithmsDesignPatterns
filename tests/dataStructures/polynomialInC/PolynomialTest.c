/* 
 * Tests Polynomial.c method implementations 
 *
 * Instructions for running this test class using the terminal:
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
#include <stdio.h>
#include <stdlib.h>
#include <inttypes.h>
#include "Polynomial.h"

int main() {
	printf("\n--------Test Polynomial_Set---------\n");
	Polynomial tempPolynomial1;
	Polynomial *polynomial1 = &tempPolynomial1;
	int64_t coefficientOfPolynomial[4] = { 0, 1, 2, 3 };
	Polynomial_Set(polynomial1, 3, coefficientOfPolynomial);
	printf("polynomial1.Degree = 3 = %d\n", polynomial1->Degree);
	printf("polynomial1.Coeff = 0 1 2 3 =");
	for (int i = 0; i <= 3; i++) {
		printf(" %d", polynomial1->Coeff[i]);
	}

	//Rational rational1 = Rational_Construct(2, 3);
	//printf("rational1.Top = 2: %d\n", rational1.Top);

	printf("\n\n--------Test Polynomial_Copy--------\n");
	Polynomial tempPolynomial2;
	Polynomial *sourcePolynomial = &tempPolynomial2;
	Polynomial tempPolynomial3;
	Polynomial *targetPolynomial = &tempPolynomial3;
	Polynomial_Set(sourcePolynomial, 3, coefficientOfPolynomial);

	Polynomial_Copy(targetPolynomial, sourcePolynomial);
	printf("targetPolynomial.Degree = 3 = %d\n", targetPolynomial->Degree);
	printf("targetPolynomial.Coeff = 0 1 2 3 =");
	for (int i = 0; i <= targetPolynomial->Degree; i++) {
		printf(" %d", targetPolynomial->Coeff[i]);
	}

	printf("\n\n--------Test Polynomial_Equals------\n");
	printf("assert 1 = %d\n",
			Polynomial_Equals(targetPolynomial, sourcePolynomial));

	Polynomial tempPolynomial4;
	Polynomial *unequalPolynomial = &tempPolynomial4;
	int64_t coefficientOfPolynomial2[4] = { 0, 1, 2, 4 };
	Polynomial_Set(unequalPolynomial, 3, coefficientOfPolynomial2);

	printf("assert 0 = %d",
			Polynomial_Equals(targetPolynomial, unequalPolynomial));

	printf("\n\n-----Test Polynomial_EvaluateAt-----\n");
	Polynomial tempPolynomial5;
	Polynomial *polynomialToEvaluate = &tempPolynomial5;
	int64_t coefficientOfPolynomial3[4] = { 3, 4, 5, 6 };
	Polynomial_Set(polynomialToEvaluate, 3, coefficientOfPolynomial3);

	int64_t result = Polynomial_EvaluateAt(polynomialToEvaluate, 3);
	printf("if X = 3, then 3 + 4X^1 + 5X^2 + "
			"6X^3 = 222 = %d", result);

	printf("\n\n--------Test Polynomial_Scale-------\n");
	Polynomial tempPolynomial6;
	Polynomial *polynomialToScale = &tempPolynomial6;
	// polynomialToEvaluate = 3 + 4X^1 + 5X^2 + 6X^3
	Polynomial_Scale(polynomialToScale, polynomialToEvaluate, 2);
	printf("3 + 4X^1 + 5X^2 + 6X^3 scaled by 2 "
			"\nhas coefficients = 6 8 10 12 = ");
	for (int i = 0; i <= polynomialToScale->Degree; i++) {
		printf(" %d", polynomialToScale->Coeff[i]);
	}

	printf("\n\n---------Test Polynomial_Add--------\n");
	Polynomial tempPolynomial7;
	Polynomial *Sum = &tempPolynomial7;
	Polynomial_Add(Sum, polynomialToEvaluate, polynomialToScale);
	printf("Sum of 3 + 4X^1 + 5X^2 + 6X^3 and "
			"\n6 + 8X^1 + 10X^2 + 12X^3 has \ncoefficients ="
			"\n9 12 15 18 = ");
	for (int i = 0; i <= Sum->Degree; i++) {
		printf(" %d", Sum->Coeff[i]);
	}

	int64_t oppositeCoefficient[4] = {3, 4, -5, -6};
	Polynomial_Set(polynomialToScale, 3, oppositeCoefficient);
	Polynomial_Add(Sum, polynomialToEvaluate, polynomialToScale);
	printf("\n\nSum of 3 + 4X^1 + 5X^2 + 6X^3 and "
			"\n3 + 4X^1 + -5X^2 + -6X^3 has \ncoefficients ="
			"\n6 8 = ");
	for (int i = 0; i <= Sum->Degree; i++) {
		printf(" %d", Sum->Coeff[i]);
	}

	Polynomial tempPolynomial8;
	Polynomial *polynomial8 = &tempPolynomial8;
	int64_t coefficientOfPolynomial4[4] = { 3, 4, 5, 6 };
	Polynomial_Set(polynomial8, 3, coefficientOfPolynomial4);

	Polynomial tempPolynomial9;
	Polynomial *polynomial9 = &tempPolynomial9;
	int64_t coefficientOfPolynomial5[7] = { 2, 4, 8, 10, 11, 12, 13 };
	Polynomial_Set(polynomial9, 6, coefficientOfPolynomial5);

	Polynomial_Add(Sum, polynomial8, polynomial9);
	printf("\n\nSum of 3 + 4X^1 + 5X^2 + 6X^3 and "
			"\n2 + 4X^1 + 8X^2 + 10X^3 + 11X^4 + 12X^5 + 13X^6"
			" has \ncoefficients ="
			"\n5 8 13 16 11 12 13 = ");
	for (int i = 0; i <= Sum->Degree; i++) {
		printf(" %d", Sum->Coeff[i]);
	}

	printf("\n\n-------Test Polynomial_Subtract-----\n");
	Polynomial tempPolynomial10;
	Polynomial *Sum2 = &tempPolynomial10;
	Polynomial_Subtract(Sum2, polynomialToEvaluate, polynomialToScale);
	printf("Sum of 3 + 4X^1 + 5X^2 + 6X^3 and "
			"\n-(6 + 8X^1 + 10X^2 + 12X^3) has \ncoefficients ="
			"\n-3 -4 -5 -6 = ");
	for (int i = 0; i <= Sum2->Degree; i++) {
		printf(" %d", Sum2->Coeff[i]);
	}

	printf("\n\n----Test Polynomial_Differentiate---\n");
	//  1X^0 +   2X^1 +     3X^2 +   4X^3
	//   0   + 2*1X^0 +   2*3X^1 + 3*4X^2
	Polynomial tempPolynomial11;
	Polynomial *polynomial11 = &tempPolynomial11;
	int64_t coefficientOfPolynomial6[4] = { 1, 2, 3, 4 };
	Polynomial_Set(polynomial9, 3, coefficientOfPolynomial6);

	Polynomial tempPolynomial12;
	Polynomial *deferientiatedPolynomial = &tempPolynomial12;
	Polynomial_Differentiate(deferientiatedPolynomial, polynomial9);
	printf("deferientiatedPolynomial->Coeff should = 2 6 12 =");
	for (int i = 0; i <= deferientiatedPolynomial->Degree; i++) {
		printf(" %d", deferientiatedPolynomial->Coeff[i]);
	}
	printf("\ndeferientiatedPolynomial->Degree = 2 = %d",
			deferientiatedPolynomial->Degree);

	printf("\n\n---------Test Polynomial_Zero-------\n");
	Polynomial_Zero(polynomial9);
	printf("polynomial9 = 0 =");
	for (int i = 0; i <= polynomial9->Degree; i++) {
		printf(" %d", polynomial9->Coeff[i]);
	}
	printf("\n");
}
