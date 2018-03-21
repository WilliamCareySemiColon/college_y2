// Simple weighted graph representation 
// Uses an Adjacency Linked Lists, suitable for sparse graphs

import java.io.*;

class Heap
{
    private int[] a;	   // heap array
    private int[] hPos;	   // hPos[a[k]] == k
    private int[] dist;    // dist[v] = priority of v

    private int N;         // heap size
   
    // The heap constructor gets passed from the Graph:
    //    1. maximum heap size
    //    2. reference to the dist[] array
    //    3. reference to the hPos[] array
    public Heap(int maxSize, int[] _dist, int[] _hPos) 
    {
        N = 0;
        a = new int[maxSize + 1];
        dist = _dist;
        hPos = _hPos;
        a[0] = 0;
        dist[0] = 0;
    }


    public boolean isEmpty() 
    {
        return (N == 0 ? true : false);
    }

	public void insert( int x)
    {
        a[++N] = x;
        siftUp( N);
    }

    public void siftUp( int k) 
    {
        int v = a[k];

        //a[0] = 0;
        //dist[0] = 0;

        while( dist[v] > dist[a[k/2]]  && k != 0) {
            a[k] = a[k/2];
			hPos[a[k]] = k;
            k = k/2;
        }
        a[k] = v;
		hPos[v] = k;
        
    }


    public void siftDown( int k) 
    {
        int v, j;
       
        v = a[k];  
        while( dist[k] <= dist[N/2]) {
            j = 2 * k;
            if(j < N && dist[a[j]] < dist[a[j+1]]) ++j;
            if( dist[v] >= dist[a[j]]) break;
            a[k] = a[j]; 	  
            hPos[a[k]] = k;
            k = j;
        }
        a[k] = v;
        hPos[v] = k;
    }
	
    public void remove() 
    {   
        int v = a[1];
        hPos[v] = 0; // v is no longer in heap
        a[N+1] = 0;  // put null node into empty spot
        
        a[1] = a[N--];
        siftDown(1);
    }
    
    // display heap values and their priorities or distances
    void display() {
        System.out.println("\n\nThe tree structure of the heaps is:");
        System.out.println( a[1] + "(" + dist[a[1]] + ")" );
        for(int i = 1; i<= N/2; i = i * 2) {
            for(int j = 2*i; j < 4*i && j <= N; ++j)
                System.out.print( a[j] + "(" + dist[a[j]] + ")  ");
            System.out.println();
        }
    }

}


   

public class HeapTest {
    public static void main(String[] args) throws IOException
    {
        int d[] = {0, 100, 70, 120, 20, 60 , 50, 130, 90, 60, 11, 154, 43, 114, 52, 76};
        int i, u;
        double x;
        int heapPos[] = new int[16];
        
        Heap h = new Heap(15, d, heapPos);
		h.display();
        h.insert(1);
        for(i = 0; i < 10; ++i) {
            // pick a heap random value between 1 and 15 and 
            // insert into heap if not already there
            x = Math.random()*15.0;
            u = (int) x + 1;
            if(heapPos[u] == 0)
                h.insert(u);
        }
        
        h.display();
        d[1] = 3; h.siftUp(heapPos[1]); h.display();
        
        h.remove(); 
		h.display();
            
    }
    
    
}
