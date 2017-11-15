/* program to print each character of the string “this is a new message” on a new line	using a pointer variable */

#include <stdio.h>

int  main()
{
  char *p = "this is a new message";	   /* p points to first character*/

  /* The next while loop is performed until p points to the null
	  character ‘\0’ at the end of the string */

  while ( *p != '\0' )
  {
	  printf("%c\t the value of p the varialbe pointer is %p \n", *p, p);		/* print each character in string */
	  p++;			            /* p now points to next character */
  }
  

  return 0;}

