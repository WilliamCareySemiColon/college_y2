/*Program to maintain a database of employee records in memory */


#include <stdio.h>
#define MAX_PERSONS 100
#define REGISTERS 8

/* Structure definitions */
//structure to include date of birth
struct date
{
  int day;
  int month;
  int year;
};
typedef struct date DATE;

//structure to store the personal information of the workers
struct personnel
{
  int number;
  char surname[26];
  char first_name[11];
  DATE dob;
  int dept;
  DATE joined;
};
typedef struct personnel EMPLOYEE;

//structure to take the process state of the the programme 
struct process_state
{
	int programme_counter;
	int register_counters [REGISTERS];
	int *process_address;
};
typedef struct process_state PROCESS;

//struct to take the state of the control block
struct process_control_block
{
	int Process_id;
	char Process_status[REGISTERS];
	PROCESS STATE;
	int Process_Prority;
};
typedef struct process_control_block PROCESS_CONTROL;

/* Function prototypes */
//Adding the employee to database
void add_an_employee(EMPLOYEE []);
//deleting an employee from data base
void delete_an_employee(EMPLOYEE []);
//dsiplay an employee to database
void display_an_employee(EMPLOYEE []);
//display the details about employee to database
void display_employee_details(EMPLOYEE *);
//intialise the database to have space for user input
void init_database(EMPLOYEE []);
//searching the database for an employee
int search_database(EMPLOYEE [], int);
//display opening menu
int menu(void);


void main()
{
	//have space for people who work in force
	EMPLOYEE persons[MAX_PERSONS];
	//for switch case later
	int menu_choice;

  
	//intialise the programme at this stage
	init_database(persons);

	//keep the programme going for as long as the programme host has power
	do
	{
		//displaying opening message through another function and return the value for the switch case
		menu_choice = menu();

		//checking which option to execute, based upon input. If '0' has been chosen, the programme will exit
		switch ( menu_choice )
		{
			case 1 :
						add_an_employee( persons );
						break;
			case 2 :
						delete_an_employee( persons );
						break;
			case 3 :
						display_an_employee( persons );
						break;
		}
	}
	while ( menu_choice != 0) ;
}



//this function will add another person to the programme
void add_an_employee( EMPLOYEE person_array[] )
{
	int i=0;

		while ( person_array[i].number != 0 && i < MAX_PERSONS )
	  i++;

	if ( i == MAX_PERSONS )
	  printf("\nSorry, the database is full\n");
	else {  				   
	  printf( "\n\nEmployee Number (1 to 3 digits, except 0) : " );
	  do
		 scanf( "%3d",&person_array[i].number );
	  while ( person_array[i].number <= 0 );

	  printf( "\nEmployee  Surname (Maximum 25 characters) : " );
	  scanf( "%25s",person_array[i].surname );
	  printf( "\n       First Name (Maximum 10 characters) : " );
	  scanf( "%10s",person_array[i].first_name );
	  printf( "\nDate of Birth\n" );
	  printf( "     Day (1 or 2 digits) : " );
	  scanf( "%2d",&person_array[i].dob.day );
	  printf( "   Month (1 or 2 digits) : " );
	  scanf( "%2d",&person_array[i].dob.month );
	  printf( "    Year (1 or 2 digits) : " );
	  scanf( "%2d",&person_array[i].dob.year );
	  printf( "\nDepartment Code (1 to 4 digits): " );
	  scanf( "%4d",&person_array[i].dept );
	  printf( "\nDate Joined\n" );
	  printf( "     Day (1 or 2 digits) : " );
	  scanf( "%2d",&person_array[i].joined.day );
	  printf( "   Month (1 or 2 digits) : " );
	  scanf( "%2d",&person_array[i].joined.month );
	  printf( "    Year (1 or 2 digits) : " );
	  scanf( "%2d",&person_array[i].joined.year );
	}
}


void delete_an_employee( EMPLOYEE person_array[] )

{
	
	int employee_number;
	int pos;


	printf("Employee Number to Delete (1 to 3 digits, except 0) :");
	do
	  scanf( "%3d",&employee_number );
	while ( employee_number <= 0 ) ;


	pos = search_database( person_array, employee_number );


	if ( pos == MAX_PERSONS )  
	  printf( "This employee is not in the database\n" );
	else                       
	  {
		 printf("Employee %3d deleted", employee_number);
		 person_array[pos].number = 0;
	  }
}


void display_an_employee( EMPLOYEE person_array[] )
{
	int employee_number;
	int pos;

	
	printf("Employee Number to Display (1 to 3 digits, except 0):" );
	do
	  scanf( "%3d",&employee_number );
	while ( employee_number <= 0 );


		pos = search_database( person_array, employee_number );

	
	if ( pos == MAX_PERSONS )  
	  printf( "This employee is not in the database\n" );
	else                       
	  display_employee_details( &person_array[pos] );
}


void display_employee_details( EMPLOYEE *ptr )
{
	printf("\n\n");
	printf("Employee Number: %d\n",ptr->number);
	printf("Surname        : %s\n",ptr->surname);
	printf("Initial        : %s\n",ptr->first_name);
	printf("Date of Birth  : %2d/%2d/%2d\n",
									 ptr->dob.day,ptr->dob.month,ptr->dob.year);
	printf("Department     : %d\n", ptr->dept);
	printf("Date Joined    : %2d/%2d/%2d\n",
						  ptr->joined.day,ptr->joined.month,ptr->joined.year);
}



void init_database( EMPLOYEE person_array[] )
{
	int i;

	for ( i=0; i < MAX_PERSONS; i++ )
	  person_array[i].number = 0;
}



int menu(void)
{
	int choice;

	/* Display the menu. */
	printf("\n\n 1. Add     an Employee\n\n");
	printf(" 2. Delete  an Employee\n\n");
	printf(" 3. Display an Employee\n\n");
	printf(" 0. Quit\n\n");
	printf( "Please enter your choice (0 to 3) " );

	/* Get the option. */
	do
		scanf( "%d", &choice );
	while ( choice <0 || choice > 3 );

	return (choice);
}


int search_database( EMPLOYEE person_array[], int emp_number )
{
	int i = 0;


	while ( i < MAX_PERSONS && person_array[i].number != emp_number )
		i++;

	 return (i);
}