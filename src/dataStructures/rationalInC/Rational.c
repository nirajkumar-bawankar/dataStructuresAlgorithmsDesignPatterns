#include <stdio.h>
#include <stdbool.h>
#include <math.h> // searches default library classpaths
#include "Rational.h" // searchs my directory
// let's the compiler know that this is a function
static bool Rational_isPositive(Rational rational);
static double absolute(double number);

/**
 *  Creates and initializes a new Rational object.
 *  Pre:
 *        Denominator != 0
 *  Returns:
 *        A Rational object X such that X.Top == Numerator
 *        and X.Bottom = Denominator.
 */
Rational Rational_Construct(int Numerator, int Denominator) {
  // make all rationals into the equivalent rational with
  // either a postive or negative numerator and never a 
  // negative denominator
  Rational newRational;
  if (Numerator < 0 && Denominator < 0) {
    Numerator = -Numerator;
    Denominator = -Denominator;
  } else if (Numerator >= 0 && Denominator < 0) {
    Numerator = -Numerator;
    Denominator = -Denominator;
  } 

  newRational.Top = Numerator;
  if (Denominator != 0) {
    newRational.Bottom = Denominator;
  } else {
    printf("You have set a denominator = 0");
    newRational.Bottom = 0;
  }
  return newRational;
}

/**
 *   Compute the arithmetic negation of R.
 *   Pre:
 *        R has been properly initialized.
 *   Returns:
 *        A Rational object X such that X + R = 0.
 */
Rational Rational_Negate(const Rational R) {
  Rational negatedR;
  negatedR.Top = -R.Top;
  negatedR.Bottom = R.Bottom;
  return negatedR;
}

/**
 *   Compute the arithmetic floor of R.
 *   Pre:
 *        R has been properly initialized.
 *   Returns:
 *        The largest integer N such that N <= R.
 */
int Rational_Floor(const Rational R) {
  if (Rational_isPositive(R)) {
    return R.Top / R.Bottom;
  } else {
    if (R.Top % R.Bottom == 0) {
      return R.Top / R.Bottom;
    } else {
      return R.Top / R.Bottom - 1;
    }
  }
}

/**
 *   Compute the arithmetic ceiling of R.
 *   Pre:
 *        R has been properly initialized.
 *   Returns:
 *        The smallest integer N such that N >= R.
 */
int Rational_Ceiling(const Rational R) {
  if (Rational_isPositive(R)) {
    if (R.Top % R.Bottom == 0) {
      return R.Top / R.Bottom;  
    } else {
      return R.Top / R.Bottom + 1;
    }
  } else {
    return R.Top / R.Bottom;
  }
}

/**
 *   Round R to the nearest integer.
 *   Pre:
 *        R has been properly initialized.
 *   Returns:
 *        The closest integer N to R.
 */
int Rational_Round(const Rational R) { 
  double decimalFormat = (double) R.Top / (double) R.Bottom;
  double R_ceiling = (double) Rational_Ceiling(R);
  double R_floor = (double) Rational_Floor(R);
  double distanceToR_ceiling = absolute(R_ceiling - decimalFormat);
  double distanceToR_floor = absolute(R_floor - decimalFormat);

  // decimalFormat is closer to the ceiling
  if (distanceToR_ceiling < distanceToR_floor) {
    return (int) R_ceiling;
  } else {
    return (int) R_floor;
  }
}

/**
 *   Compute the sum of Left and Right.
 *   Pre:
 *        Left and Right have been properly initialized.
 *   Returns:
 *        A Rational object X equal to Left + Right.
 */
Rational Rational_Add(const Rational Left, const Rational Right) {
  Rational sum;
  sum.Top = (Left.Top * Right.Bottom) + (Right.Top * Left.Bottom);
  sum.Bottom = Left.Bottom * Right.Bottom;
  return sum;
}

/**
 *   Compute the difference of Left and Right.
 *   Pre:
 *        Left and Right have been properly initialized.
 *   Returns:
 *        A Rational object X equal to Left - Right.
 */
Rational Rational_Subtract(const Rational Left, const Rational Right) {
  Rational sum;
  sum.Top = (Left.Top * Right.Bottom) - (Right.Top * Left.Bottom);
  sum.Bottom = Left.Bottom * Right.Bottom;
  return sum;
}

/**
 *   Compute the product of Left and Right.
 *   Pre:
 *        Left and Right have been properly initialized.
 *   Returns:
 *        A Rational object X equal to Left * Right.
 */
Rational Rational_Multiply(const Rational Left, const Rational Right) {
  Rational product;
  product.Top = Left.Top * Right.Top;
  product.Bottom = Left.Bottom * Right.Bottom;
  return product;
}

/**
 *   Compute the quotient of Left and Right.
 *   Pre:
 *        Left and Right have been properly initialized.
 *        Right != 0.
 *   Returns:
 *        A Rational object X equal to Left / Right.
 */
Rational Rational_Divide(const Rational Left, const Rational Right) {
  Rational quotient;
  quotient.Top = Left.Top * Right.Bottom;
  quotient.Bottom = Left.Bottom * Right.Top;
  return quotient;
}

/**
 *   Determine whether Left and Right are equal.
 *   Pre:
 *        Left and Right have been properly initialized.
 *   Returns:
 *        True if Left == Right, false otherwise.
 */
bool Rational_Equals(const Rational Left, const Rational Right) {
  if (Left.Top * Right.Bottom == Left.Bottom * Right.Top) {
    return true;
  } else {
    return false;
  }
}

/**
 *   Determine whether Left and Right are not equal.
 *   Pre:
 *        Left and Right have been properly initialized.
 *   Returns:
 *        True if Left != Right, false otherwise.
 */
bool Rational_NotEquals(const Rational Left, const Rational Right) {
  if (Rational_Equals(Left, Right)) {
    return false;
  } else {
    return true;
  }
}

/**
 *   Determine whether Left is less than Right.
 *   Pre:
 *        Left and Right have been properly initialized.
 *   Returns:
 *        True if Left < Right, false otherwise.
 */
bool Rational_LessThan(const Rational Left, const Rational Right) {
  // double leftValue = (double) Left.Top / (double) Left.Bottom;
  // double rightValue = (double) Right.Top / (double) Right.Bottom;
  int leftValue = Left.Top * Right.Bottom;
  int rightValue = Left.Bottom * Right.Top;
  if (leftValue < rightValue) {
    return true;
  } else {
    return false;
  }
}

/**
 *   Determine whether Left is less than or equal to Right.
 *   Pre:
 *        Left and Right have been properly initialized.
 *   Returns:
 *        True if Left <= Right, false otherwise.
 */
bool Rational_LessThanOrEqual(const Rational Left, const Rational Right) {
  if( Rational_Equals(Left, Right) | Rational_LessThan(Left, Right)) {
    return true;
  } else {
    return false;
  }
}

/**
 *   Determine whether Left is greater than Right.
 *   Pre:
 *        Left and Right have been properly initialized.
 *   Returns:
 *        True if Left > Right, false otherwise.
 */
bool Rational_GreaterThan(const Rational Left, const Rational Right) {
  if (Rational_LessThanOrEqual(Left, Right)) {
    return false;
  } else {
    return true;
  }
}

/**
 *   Determine whether Left is greater than or equal to Right.
 *   Pre:
 *        Left and Right have been properly initialized.
 *   Returns:
 *        True if Left >= Right, false otherwise.
 */
bool Rational_GreaterThanOrEqual(const Rational Left, const Rational Right) {
  if (Rational_GreaterThan(Left, Right) | Rational_Equals(Left, Right)) {
    return true;
  } else {
    return false;
  }
}

/**
 *   Determines if rational is positive.
 *   Pre: rational has been properly initialized.
 *   Returns: True if rational >=0, false otherwise.
 */
static bool Rational_isPositive(Rational rational) {
  // all rationals are equivalently represented without a negative
  // denominator
  if (rational.Top >= 0) {
    return true;
  } else {
    return false;
  }
}

/**
 *   Computes absolute value of a double number.
 */
static double absolute(double number) {
  if (number < 0) {
    return -number;
  } else {
    return number;
  }
}

