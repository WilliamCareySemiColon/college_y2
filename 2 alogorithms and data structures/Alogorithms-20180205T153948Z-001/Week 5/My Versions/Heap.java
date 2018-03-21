/*****************************************************************************************************
This is \ max heap. This means that the bigger numbers are near the top while the smaller elements are
near the bottom 
*****************************************************************************************************/
import java.util.*;

class Heap 
{
	//arrays 
  	private int[] a, hPos;
  	//static variables for the programme
	private static int N;
	private static int maxH = 100;

	// two constructors - One calls the other if it is called
	public Heap() 
	{
	   this(maxH);
	}


	public Heap(int size) 
	{
	   N = 0;
	   a = new int[size + 1];
	   hPos = new int[size + 1];
	   a[0] = Integer.MAX_VALUE;
	}

	//Methid to insert into the heap. It then call the 
	//method to move it up until it finds the sufficient location
	public void insert( int x) 
	{
	   a[++N] = x;
	   siftUp( N);
	}


	public void siftUp( int k) 
	{
	   int v = a[k];

	   while( v > a[k/2]) 
	   {
	      a[k] = a[k/2];
		  hPos[a[k]] = k;
	      k = k/2;
	   }
	   a[k] = v;
	   hPos[v] = k;
	}

	//Method to remove an element then calls the siftdown method

	public int remove() 
	{
		int v = a[1];
		hPos[v] = -1;
		a[1] = a[N--];
		siftDown(1);
		return v;
	}

	public void siftDown(int x) 
	{
	 	int v = a[x], j;

	 	while(x <= N/2)
	 	{
	 		j = 2*x;
	 		if (j < N && a[j] < a[j+1])
	 			j++;

	 		if (v > a[j])
	 			break;

	 		a[x] = a[j];
	 		hPos[a[x]] = x;
	 		x = j;
	 	}
	 	a[x] = v;
	 	hPos[v] = x;
	}

	public void display() 
	{
	   System.out.println("\n\nThe tree structure of the heaps is:");
	   System.out.println( a[1] );
	   for(int i = 1; i<= N/2; i = i * 2) 
	   {
	    	for(int j = 2*i; j < 4*i && j <= N; ++j)
	         System.out.print( a[j] + "  ");

	    	System.out.println("\n");
	   }//end outer for loop
	}//end display


	public static void main(String args[]) 
	{

	   Heap h = new Heap();
	   int r; double x;
	   int arrayCount = 20;

	   // insert random numbers between 0 and 99 into heap
	   for(int i = 1; i <= arrayCount; ++i)  
	   {
		  x =  (Math.random()*99.0);
	      r = (int) x; 
	      System.out.println("Inserting " + r);
	      h.insert(r);
		  h.display();

	   }//end for loop

	   for(int i = 1; i <= arrayCount; ++i)  
	   {
	      System.out.println("Removing " + h.remove());
		  h.display();

	   }//end for loop

	}//end main()


} // end of Heap class