// reference Paul Kelly chapter 11: A guide to C programming
/* Program to demonstrate passing two arguments by reference. */
#include <stdio.h>
main()
{
  void swap( float *ptr1, float *ptr2 ) ;
  float num1, num2 ;

  printf( "Please enter two numbers: " ) ;
  scanf( "%f", &num1 ) ;
  scanf( "%f", &num2 ) ;

  /* Swap values around so that the smallest is in num1. */
  if ( num1 > num2 )
    swap( &num1, &num2 ) ;
  printf( "The numbers in accending order are %.1f  %.1f\n", num1, num2 );
}


/* Function  : swap
   Purpose   : This function swaps two floating-point values.
   Arguments : pointers to the variables to be swapped. */
void swap( float *ptr1, float *ptr2 )
{
  float temp ;

  temp = *ptr1 ;
  *ptr1 = *ptr2 ;
  *ptr2 = temp ;
}

