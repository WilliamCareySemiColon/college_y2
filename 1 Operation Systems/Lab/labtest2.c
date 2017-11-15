/*

Lab Test 2 A semaphore implementation of the producer consumer problem
	
*/	
	
	
	
#include <pthread.h>
#include <stdio.h>
#include <semaphore.h>    

#define BUFF_SIZE 6

char buffer[BUFF_SIZE];   // the bounded buffer - to be implemented as a circular buffer
int Add = 0;    //producer index pointer 
int Remove = 0;   // consumer index pointer


// declare semaphore variables as global variables
sem_t empty_elements;
sem_t full_elements;
sem_t mutex;


//  The producer function 
void Put(char item)
{
  
  // shows the item that was produced
    printf("Producing %c ...\n", item);
	
  // INSERT CODE FOR THE PRODUCER ALGORITHM AS DESCRIBED IN THE CLASS NOTES. ;