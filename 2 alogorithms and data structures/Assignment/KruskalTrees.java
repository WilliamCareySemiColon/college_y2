// Kruskal's Minimum Spanning Tree Algorithm
// Union-find implemented using disjoint set trees without compression

import java.io.*;    

//Class edge to store all the indiviadual information about each edge and vertices 
class Edge {
    public int u, v, wgt;

    public Edge() {
        this(0,0,0);
    }

    public Edge( int x, int y, int w) {
        u = x;
        v = y;
        wgt = w;
    }
    
    public void show() {
        System.out.print("Edge " + toChar(u) + "--" + wgt + "--" + toChar(v) + "\n") ;
    }
    
    // convert vertex into char for pretty printing
    private char toChar(int u)
    {  
        return (char)(u + 64);
    }
}


//Heap to sort the edges based off their weight in association with each vertices
class Heap
{
	private int[] h;
    int N, Nmax;
    Edge[] edge;


    // Bottom up heap construc
    public Heap(int N, Edge[] edge) {
        int i,j, temp;
        Nmax = this.N = N;
        h = new int[N+1];
        this.edge = edge;
       
        // initially just fill heap array with 
        // indices of edge[] array.
        for (i=0; i <= N; ++i) 
            h[i] = i;
           
        // Then convert h[] into a heap
        // from the bottom up.
        for(i = N/2; i > 0; --i)
        {
        	siftDown(i);
        }

    }

    private void siftDown( int k) {
        int e, j;

        e = h[k];
        while( k <= N/2) {
            j = 2*k;
            if (j < N && edge[h[j]].wgt > edge[h[j+1]].wgt) j++;

            if (edge[e].wgt < edge[h[j]].wgt) break;

            h[k] = h[j];
            k = j;
        }
        h[k] = e;
    }


    public int remove() {
        h[0] = h[1];
        h[1] = h[N--];
        siftDown(1);
        return h[0];
    }
}

/****************************************************
*
*       UnionFind partition to support union-find operations
*       Implemented simply using Discrete Set Trees
*
*****************************************************/

class UnionFindSets
{
    private int[] treeParent;
    private int N;
    
    public UnionFindSets( int V)
    {
        N = V;
        treeParent = new int[V+1];
        // missing lines
        for (int i = 1; i <= V; i++)
        	treeParent[i] = i;
    }

    public int findSet( int vertex)
    {   
        if (treeParent[vertex] == vertex)
        	return vertex;
    	else
    		return findSet(treeParent[vertex]);
    }
    
    public void union( int set1, int set2)
    {
        int x = findSet(set1);
        int y = findSet(set2);
        treeParent[y] = x;
    }
    
    public void showTrees()
    {
        int i;
        for(i=1; i<=N; ++i)
            System.out.print(toChar(i) + "->" + toChar(treeParent[i]) + "  " );
        System.out.print("\n");
    }
    
    public void showSets()
    {
        int u, root;
        int[] shown = new int[N+1];
        for (u=1; u<=N; ++u)
        {   
            root = findSet(u);
            if(shown[root] != 1) {
                showSet(root);
                shown[root] = 1;
            }            
        }   
        System.out.print("\n");
    }

    private void showSet(int root)
    {
        int v;
        System.out.print("Set{");
        for(v=1; v<=N; ++v)
            if(findSet(v) == root)
                System.out.print(toChar(v) + " ");
        System.out.print("}  ");
    
    }
    
    private char toChar(int u)
    {  
        return (char)(u + 64);
    }
}

/**************************************************************************************************************************************************
Class graph to read in a file and use the kruskal to find the MST
**************************************************************************************************************************************************/
class Graph 
{ 
    private int V, E;
    private Edge[] edge;
    private Edge[] mst;        

    public Graph(String graphFile) throws IOException
    {
        int u, v;
        int w, e;

        FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);
	           
        String splits = " +";  // multiple whitespace as delimiter
		String line = reader.readLine();        
        String[] parts = line.split(splits);
        System.out.println("Parts[] = " + parts[0] + " " + parts[1]);
        
        V = Integer.parseInt(parts[0]);
        E = Integer.parseInt(parts[1]);
        
        // create edge array
        edge = new Edge[E+1];   
        
       // read the edges
        System.out.println("Reading edges from text file");
        for(e = 1; e <= E; ++e)
        {
            line = reader.readLine();
            parts = line.split(splits);
            u = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]); 
            w = Integer.parseInt(parts[2]);
            
            System.out.println("Edge " + toChar(u) + "--(" + w + ")--" + toChar(v));                         
             
            // create Edge object
            Edge edgey = new Edge(u,v,w);
            edge[e] = edgey;
        }
    }


/**********************************************************
*
*       Kruskal's minimum spanning tree algorithm
*
**********************************************************/
public Edge[] MST_Kruskal() 
{
    int ei, i = 0;
    Edge e;
    int uSet, vSet;
    UnionFindSets partition;
    
    // create edge array to store MST
    // Initially it has no edges.
    mst = new Edge[V-1];

    // priority queue for indices of array of edges
    Heap h = new Heap(E, edge);

    // create partition of singleton sets for the vertices
    partition = new UnionFindSets(V);

    for(int z = 0; z < E; z++)
    {
    	ei = h.remove();

    	uSet = edge[ei].u;
    	vSet = edge[ei].v;
    	int check1 = partition.findSet(uSet);
    	int check2 = partition.findSet(vSet);

    	if (check1 != check2)
    	{
    		
    		mst[i++] = edge[ei];
    		//System.out.println("\n Vertice 1 = "+ mst[z].u + " Vertice2 = " + mst[z].v + " Edge = "+mst[z].wgt);
    		partition.union(uSet,vSet);
    	}

    	if (i == V-1) break;

    }
    
    
    return mst;
}


    // convert vertex into char for pretty printing
    private char toChar(int u)
    {  
        return (char)(u + 64);
    }

    public void showMST()
    {
        System.out.print("\nMinimum spanning tree build from following edges:\n");
        for(int e = 0; e < V-1; ++e) {
            mst[e].show(); 
        }
        System.out.println();
       
    }

} // end of Graph class

//class Kruskal tree to demonstate the kruskal algorithm in the main function using the heap class, graph class and edge class
class KruskalTrees {
    public static void main(String[] args) throws IOException
    {
        String fname = "wGraph3.txt";
        //System.out.print("\nInput name of file with graph definition: ");
        //fname = Console.ReadLine();

        Graph g = new Graph(fname);

        g.MST_Kruskal();

        g.showMST();
        
    }
}  