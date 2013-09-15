/* Tests Rational.c method implementations */
#include <stdio.h>
#include "Rational.h"

// every c program needs a main function
int main(void)
{
  printf("\n--Test Rational_Construct--\n");
  Rational rational1 = Rational_Construct(2, 3);
  printf("rational1.Top = 2: %d\n", rational1.Top);
  printf("rational1.Bottom = 3: %d\n", rational1.Bottom);
  rational1 = Rational_Construct(-2, -3);
  printf("rational1.Top = 2: %d\n", rational1.Top);
  printf("rational1.Bottom = 3: %d\n", rational1.Bottom);
  rational1 = Rational_Construct(2, -3);
  printf("rational1.Top = -2: %d\n", rational1.Top);
  printf("rational1.Bottom = 3: %d\n", rational1.Bottom);
  rational1 = Rational_Construct(-2, 3);
  printf("rational1.Top = -2: %d\n", rational1.Top);
  printf("rational1.Bottom = 3: %d\n", rational1.Bottom);

  printf("\n--Test Rational_Negate--\n");
  Rational rational2 = Rational_Construct(2, 3);
  Rational rational3 = Rational_Negate(rational2);
  printf("rational3.Top = -2: %d\n", rational3.Top);
  printf("rational3.Bottom = -3: %d\n", rational3.Bottom);
  
  printf("\n--Test Rational_Floor--\n");
  Rational rational4 = Rational_Construct(-4, 5);
  printf("Return -1: %d\n", Rational_Floor(rational4));
  rational4 = Rational_Construct(-6, 5);
  printf("Return -2: %d\n", Rational_Floor(rational4));
  rational4 = Rational_Construct(-10, 5);
  printf("Return -2: %d\n", Rational_Floor(rational4));
  rational4 = Rational_Construct(-12, 5);
  printf("Return -3: %d\n", Rational_Floor(rational4));

  Rational rational5 = Rational_Construct(4, 5);
  printf("Return 0: %d\n", Rational_Floor(rational5));
  rational5 = Rational_Construct(6, 5);
  printf("Return 1: %d\n", Rational_Floor(rational5));
  rational5 = Rational_Construct(10, 5);
  printf("Return 2: %d\n", Rational_Floor(rational5));
  rational5 = Rational_Construct(12, 5);
  printf("Return 2: %d\n", Rational_Floor(rational5));

  printf("\n--Test Rational_Ceiling--\n");
  Rational rational6 = Rational_Construct(-4, 5);
  printf("Return 0: %d\n", Rational_Ceiling(rational6));
  rational6 = Rational_Construct(-6, 5);
  printf("Return -1: %d\n", Rational_Ceiling(rational6));
  rational6 = Rational_Construct(-10, 5);
  printf("Return -2: %d\n", Rational_Ceiling(rational6));
  rational6 = Rational_Construct(-12, 5);
  printf("Return -2: %d\n", Rational_Ceiling(rational6));

  Rational rational7 = Rational_Construct(4, 5);
  printf("Return 1: %d\n", Rational_Ceiling(rational7));
  rational7 = Rational_Construct(6, 5);
  printf("Return 2: %d\n", Rational_Ceiling(rational7));
  rational7 = Rational_Construct(10, 5);
  printf("Return 2: %d\n", Rational_Ceiling(rational7));
  rational7 = Rational_Construct(12, 5);
  printf("Return 3: %d\n", Rational_Ceiling(rational7));

  printf("\n--Test Rational_Round--\n");
  Rational rational8 = Rational_Construct(2, 6);
  printf("Return 0: %d\n", Rational_Round(rational8));
  rational8 = Rational_Construct(3, 6);
  printf("Return 0 or 1: %d\n", Rational_Round(rational8));
  rational8 = Rational_Construct(4, 6);
  printf("Return 1: %d\n", Rational_Round(rational8));
  rational8 = Rational_Construct(13, 6);
  printf("Return 2: %d\n", Rational_Round(rational8));

  printf("\n--Test Rational_Add--\n");
  Rational rational9 = Rational_Construct(2, 3);
  Rational rational10 = Rational_Construct(4, 5);
  Rational sum1 = Rational_Add(rational9, rational10);
  printf("Return 22/15: %d/%d\n", sum1.Top, sum1.Bottom);

  Rational rational11 = Rational_Construct(-2, 3);
  Rational rational12 = Rational_Construct(-4, 5);
  sum1 = Rational_Add(rational11, rational12);
  printf("Return -22/15: %d/%d\n", sum1.Top, sum1.Bottom);
  
  /*
  printf("\n--Test Rational_Substract--\n");
  sum1 = Rational_Substract(rational9, rational10);
  printf("Return -2/15: %d/%d\n", sum1.Top, sum1.Bottom);

  sum1 = Rational_Substract(rational11, rational12);
  printf("Return 2/15: %d/%d\n", sum1.Top, sum1.Bottom);
  */
  
  printf("\n--Test Rational_Multiply--\n");
  Rational rational13 = Rational_Construct(2, 3);
  Rational rational14 = Rational_Construct(4, 5);
  Rational product = Rational_Multiply(rational13, rational14);
  printf("Return 8/15: %d/%d\n", product.Top, product.Bottom);

  printf("\n--Test Rational_Divide--\n");
  Rational rational15 = Rational_Construct(2, 3);
  Rational rational16 = Rational_Construct(4, 5);
  Rational quotient = Rational_Divide(rational15, rational16);
  printf("Return 10/12: %d/%d\n", quotient.Top, quotient.Bottom);

  printf("\n--Test Rational_Equals--\n");
  Rational rational17 = Rational_Construct(2, 3);
  Rational rational18 = Rational_Construct(4, 6);
  bool boolean = Rational_Equals(rational17, rational18);
  printf("Return 1: %d\n", boolean);
  boolean = Rational_Equals(rational16, rational18);
  printf("Return 0: %d\n", boolean);

  printf("\n--Test Rational_NotEquals--\n");
  printf("Tested above");

  printf("\n--Test Rational_LessThan--\n");
  // Left = Right
  boolean = Rational_LessThan(rational17, rational18);
  printf("Return 0: %d\n", boolean);
  // 2/3 < 4/5
  boolean = Rational_LessThan(rational15, rational16);
  printf("Return 1: %d\n", boolean);
  // Left > Right
  boolean = Rational_LessThan(rational16, rational15);
  printf("Return 0: %d\n", boolean);

  printf("\n--Additional Tests for Rational_LessThan--\n");
  Rational rational30 = Rational_Construct(19, 11);
  Rational rational31 = Rational_Construct(1180298650, 13688880);
  boolean = Rational_LessThan(rational30, rational31);
  printf("Return 0: %d\n", boolean);

  rational30 = Rational_Construct(15, 14);
  rational31 = Rational_Construct(-2076445764, 18251840);
  boolean = Rational_LessThan(rational30, rational31);
  printf("Return 1: %d\n", boolean);

  rational30 = Rational_Construct(-2076445764, 18251840);
  rational31 = Rational_Construct(15, 14);
  boolean = Rational_LessThan(rational30, rational31);
  printf("Return 0: %d\n", boolean);

  rational30 = Rational_Construct(23, 24);
  rational31 = Rational_Construct(-1038222882, 25096280);
  boolean = Rational_LessThan(rational30, rational31);
  printf("Return 1: %d\n", boolean);

  rational30 = Rational_Construct(-1038222882, 25096280);
  rational31 = Rational_Construct(23, 24);
  boolean = Rational_LessThan(rational30, rational31);
  printf("Return 0: %d\n", boolean);


  printf("\n--Test Rational_LessThanOrEqual--\n");
  // Left = Right
  boolean = Rational_LessThanOrEqual(rational17, rational18);
  printf("Return 1: %d\n", boolean);
  // 2/3 < 4/5
  boolean = Rational_LessThanOrEqual(rational15, rational16);
  printf("Return 1: %d\n", boolean);
  // Left > Right
  boolean = Rational_LessThanOrEqual(rational16, rational15);
  printf("Return 0: %d\n", boolean);
  return 0;
}

