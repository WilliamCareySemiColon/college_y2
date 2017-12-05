/*

Lab Test 2 A semaphore implementation of the producer consumer problem

 Only an implementation using the skeleton code given below will be acceptable to implement
 the producer/consumer bounded buffer problem.

if any other code version is used it will result in zero marks  
	
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
	
  // INSERT CODE FOR THE PRODUCER ALGORITHM AS DESCRIBED IN THE CLASS NOTES.
  if (Add != Remove || Add < BUFF_SIZE)
  {
	  sem_wait(&mutex);
	  sem_wait(&empty_elements);
	 
	  
	  buffer[Add] = item;
	  
	  sem_post(&mutex);
	  sem_post(&full_elements);
	  
	  Add = Add + 1;
	  
	  if (Add > BUFF_SIZE)
		  Add=0;
	  
  }
}
 

 //The producer thread used to call a producer function 10 times. 
  void * Producer()
{
    int i;

    
	for(i = 0; i < 10; i++)
    {
      Put((char)('A'+ i % 26));   // Call the Put (producer algorithm) function passing a to it an alphabetical character 
    }
   	
}




//  insert code for the consumer function: code to run the consumer algorithm as described in the notes
void Get () 

{

    // INSERT CODE FOR P FUNCTIONS FOR THE FULL AND MUTEX SEMAPHORES     
	if (Remove != Add)
  {
	  sem_wait(&mutex);
	  sem_wait(&full_elements);
	  
	  
	  
  
    /* enter the critical region of the bounded buffer (removing item from buffer!!!)   */  
		item = buffer[Remove];
	    
		//  INSERT CODE TO MOVE POINTER FOR CONSMUER POINTER TO ITS NEXT POSITION IN THE BOUNDED BUFFER
        // in this case the buffer is implemented as a circular buffer		
		 Remove++;
		 
		 if(Remove > BUFF_SIZE)
			 Remove=0;
  
  
	  // INSERT CODE  FOR THE V FUNCTIONS OF THE MUTEX AND EMPTY SEMAPHORES}
	  sem_post(&mutex);
	  sem_post(&empty_elements);
	  	  
  
	  printf("consuming %c ...\n", item);
	}


}




// the consumer thread: this calls the consumer algorithm function (Get) 10 times 
void * Consumer()
{
  sleep(1);
    
   int i;
       
    for(i = 0; i < 10; i++)
   {
       Get();//calling the get function
       sleep(1);  // to ensure that all chars are not consumed just as they are produced.  	 
   }
}



int main()
{
	
	   pthread_t idProduce, idConsume;       // thread ID variables
      
       int rc1, rc2;
	
	
	// INSERT CODE TO INITALISE ALL THREE SEMAPHORE
	sem_init( &empty_elements, 0, 1 );
    sem_init( &full_elements, 0, 0 );
	sem_init( &mutex, 0, 1 );
		
	
	// create thread for producer
	
	if( (rc1=pthread_create( &idProduce, NULL, Producer, NULL)) )
   {
      printf("Thread creation failed: %d\n", rc1);
   }
	
	
	//INSERT CODE TO CREATE THREAD FOR CONSUMER 
	
	if( (rc2=pthread_create( &idConsume, NULL, Consumer, NULL)) )
   {
      printf("Thread creation failed: %d\n", rc1);
   }
   
   
   
     
   //INSERT CODE THAT ensures the CHILD THREADS finish before the MASTER THREAD (the main process)
	pthread_join( rc1, NULL );
    pthread_join( rc2, NULL );		


   
   // INSERT CODE TO DESTROY ALL THE THREE SEMAPHORES
	sem_destroy( &empty_elements );
    sem_destroy( &full_elements );
	sem_destroy( &mutex );
                
	return 0;
	
	
	
}