/**************************************************************************************************************************************************
Class graph to read in a file and use the kruskal to find the MST
**************************************************************************************************************************************************/

import java.io.*;

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