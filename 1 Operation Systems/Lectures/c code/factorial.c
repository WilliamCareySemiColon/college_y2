#include <stdio.h>

/* A program to prompt the user for a series of non-negative integers.
	For each number entered, the program will calculate and display the
	factorial of the number */

int factorial (int); /* function prototypes */

void main()
{
  int n;

  /* Prompt the user for the first number */
  printf("Enter a non-negative number (-1 to end): ");
  scanf("%d", &n);
  fflush(stdin);

  while (n != -1)
  {	 printf("Factorial of %d is %d\n\n", n, factorial(n));  // call the function 

	 /* Prompt the user for the next number */
	 printf("Enter a non-negative number (-1 to end): ");
	 scanf("%d", &n);
	 fflush(stdin);
  }
}



/* Function definition */

int factorial (int num)   // function heading 
{
  int fact = 1;

  while (num > 1)
  {
	 fact = fact * num;
	 num --;
  }

  return fact;  // return value (note must be of same data type)
}


