// Heap.java

import java.util.*;

class Heap 
{
  	private int[] a, hPos;
	private static int N;
	private static int maxH = 100;

	// two constructors
	public Heap() 
	{
	   this(maxH);
	}


	public Heap(int size) 
	{
	   N = 0;
	   a = new int[size + 1];
	   a[0] = Integer.MAX_VALUE;
	}



public void insert( int x) 
{
   //a[++N] = x;
   //siftUp( N);
	a[N++] = x;
   siftDown(x);
}


public void siftUp( int k) 
{
   int v = a[k];

   while( v > a[k/2]) 
   {
      a[k] = a[k/2];
	  //hPos[]
      k = k/2;
   }
   a[k] = v;
  // hPos[]
}

public void move(int n)
{
	for (int i = N-1; i > 1; i--)
		a[i] = a[i-1];
}


public void move()
{
	for (int i = 0; i < N-1; i++)
		a[i] = a[i+1];
}


public void siftDown(int x) 
{
 	move(N-1);

 	int k = 1;
	int v = a[k] = x;

	while (v < a[k*2] || v < a[(k*2) + 1])
	{
		if (! (v<a[k*2]) )
			k++;

		a[k] = a[k*2];

		k *= 2;
	}

	a[k] = v;
}


public int remove(int x) 
{
	int b = a[1], i = 1;

	if (b == x)
	{
		move();
	}

	else
	{
		while (a[i] != x && i != N)
			i++;

		if (a[i] == x)
		{
			a[1] = a[i];
			a[i] = b;
			move();
			siftUp(a[i-1]);
		}
	}
	N--;
	return 1;
}



public void display() 
{
   System.out.println("\n\nThe tree structure of the heaps is:");
   System.out.println( a[1] );
   for(int i = 1; i<= N/2; i = i * 2) 
   {
      for(int j = 2*i; j < 4*i && j <= N; ++j)
         System.out.print( a[j] + "  ");
       System.out.println();
   }//end outer for loop
}//end display


public static void main(String args[]) 
{

   Heap h = new Heap();
   int r; double x;
   int arrayCount = 14;

   // insert random numbers between 0 and 99 into heap
   for(int i = 1; i <= arrayCount; ++i)  
   {
	  x =  (Math.random()*99.0);
      r = (int) x; 
      System.out.println("Inserting " + r);
      h.insert(r);
	  h.display();

   }//end for loop
  
  for(int i = N-1; i > 0; --i)  
   {
	   System.out.println("\nInput a value to remove: ");
	   Scanner in = new Scanner(System.in);
	   r = in.nextInt();
	   int temp = h.remove(r);
	   if (temp == 1)
	   	h.display();
   }//end for loop
}//end main()


} // end of Heap class