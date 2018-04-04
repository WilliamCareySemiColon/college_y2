// Simple weighted graph representation 
// Uses an Adjacency Linked Lists, suitable for sparse graphs

import java.io.*;
/*****************************************************************************************************************************************
The class heap that gets data and sorts it out in descending order based upon their distance from each other
****************************************************************************************************************************************/
class Heap
{
    private int[] a;	   // heap array
    private int[] hPos;	   // hPos[h[k]] == k
    private int[] dist;    // dist[v] = priority of v

    private int N;         // heap size
   
    // The heap constructor gets passed from the Graph:
    //    1. maximum heap size
    //    2. reference to the dist[] array
    //    3. reference to the hPos[] array
    public Heap(int maxSize, int[] dist, int[] hPos) 
    {
        N = 0;
        a = new int[maxSize + 1];
        this.dist = dist;
        this.hPos = hPos;
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

        while( dist[v] < dist[a[k/2]]  && k != 0) {
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
        while( k <= N/2) {
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
    
    public int remove() 
    {   
        int v = a[1];
        hPos[v] = 0; // v is no longer in heap
        a[N+1] = 0;  // put null node into empty spot
        
        a[1] = a[N--];
        siftDown(1);

        return v;
    }
    
    // display heap values and their priorities or distances
    public void display() {
        System.out.println("\n\nThe tree structure of the heaps is:");
        System.out.println( a[1] + "(" + dist[a[1]] + ")" );
        for(int i = 1; i<= N/2; i = i * 2) {
            for(int j = 2*i; j < 4*i && j <= N; ++j)
                System.out.print( a[j] + "(" + dist[a[j]] + ")  ");
            System.out.println();
        }
    }

}//end class heap

/*****************************************************************************************************************************************
//class graph to read from the files and sort it into an adjency list
****************************************************************************************************************************************/
class Graph {
    class Node {
        public int vert;
        public int wgt;
        public Node next;
    }
    
    // V = number of vertices
    // E = number of edges
    // adj[] is the adjacency lists array
    private int V, E;
    private Node[] adj;
    private Node z;
    private int[] mst;
    
    
    /// default constructor
    public Graph(String graphFile)  throws IOException
    {
        int v1, v2, ed;
        int e,v;
        Node node, temp;

        FileReader fr = new FileReader(graphFile);
        BufferedReader reader = new BufferedReader(fr);
               
        String splits = " +";  // multiple whitespace as delimiter
        String line = reader.readLine();        
        String[] parts = line.split(splits);
        System.out.println("Parts[] = " + parts[0] + " " + parts[1]);
        
        V = Integer.parseInt(parts[0]);
        E = Integer.parseInt(parts[1]);
        
        // create sentinel node
        z = new Node(); 
        z.next = z;
        
        // create adjacency lists, initialised to sentinel node z
        adj = new Node[V+1];

        for(v = 1; v <= V; ++v)
           adj[v] = z;               
        
        // read the edges
        System.out.println("Reading edges from text file");
        
        
        for(e = 1; e <= E; ++e)
       {
            line = reader.readLine();
            parts = line.split(splits);
            v1 = Integer.parseInt(parts[0]);
            v2 = Integer.parseInt(parts[1]);
            ed = Integer.parseInt(parts[2]);
            
            System.out.println("Edge " + toChar(v1) + "--(" + ed + ")--" + toChar(v2));

            temp = adj[v1];
            adj[v1] = new Node();
            adj[v1].vert = v2;
            adj[v1].wgt = ed;
            adj[v1].next = temp;

            temp = adj[v2];
            adj[v2] = new Node();
            adj[v2].vert = v1;
            adj[v2].wgt = ed;
            adj[v2].next = temp;
       }           
    }
   
    // convert vertex into char for pretty printing
    private char toChar(int u)
    {  
        return (char)(u + 64);
    }
    
    // method to display the graph representation
     public void display() {
        int v;
        Node n;
        
        for(v=1; v<=V; ++v){
            System.out.print("\nadj[" + toChar(v) + "] ->" );
            for(n = adj[v]; n != z; n = n.next) 
                System.out.print(" |" + toChar(n.vert) + " | " + n.wgt + "| ->");    
            System.out.print(" NULL");
        }
        System.out.println("");
    }


    //mst based upon the prim algorithm
	public void MST_Prim(int s)
	{
        int v;
        int wgt_sum = 0;
        int[]  dist, parent, hPos;

        //code here
        dist = new int[V + 1];
        hPos = new int[V + 1];
        parent = new int[V + 1];

        for (int i = 1; i <= V; i++)
            dist[i] = Integer.MAX_VALUE;

        dist[s] = 0;
        parent[s] = 0;

        Heap pq =  new Heap(V, dist, hPos);
        pq.insert(s);
        
        System.out.println("\nSource point is "+toChar(s));

        while ( !(pq.isEmpty()) ) 
        {
            v = pq.remove();
            dist[v] = -dist[v];
            Node u = adj[v];
            while (u != z)
            {
                if (u.wgt < dist[u.vert])
                {
                    dist[u.vert] = u.wgt;
                    parent[u.vert] = v;

                    if (hPos[u.vert] == 0)
                        pq.insert(u.vert);
                    else
                        pq.siftUp(hPos[u.vert]);
                }

                u = u.next;
            }  
        }

        for (int d: dist)
            wgt_sum += Math.abs(d);

        System.out.print("\n\nWeight of MST = " + wgt_sum + "\n");
        
        mst = parent;

        showMST();                   		
	}
    
    public void showMST()
    {
            System.out.print("\n\nMinimum Spanning tree parent array is:\n");
            for(int v = 1; v <= V; ++v)
                System.out.println(toChar(v) + " -> " + toChar(mst[v]));
            System.out.println("");
    }

}//end graph algorithm


/*********************************************************************************************************************************
Main function
*********************************************************************************************************************************/
public class PrimLists {
    public static void main(String[] args) throws IOException
    {
        int s = 2;
        String fname = "../wGraph3.txt";               

        Graph g = new Graph(fname);
       
        g.MST_Prim(4);
    }
    
    
}
