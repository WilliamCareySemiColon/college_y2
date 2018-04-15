/*****************************************************************************************************************************************
//class graph to read from the files and sort it into an adjency list
****************************************************************************************************************************************/

import java.io.*;

class Graph {
    
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
            {
            	if ( toChar(mst[v]) > 64 && toChar(mst[v]) < 91)
                	System.out.println(toChar(v) + " -> " + toChar(mst[v]));
                else
                	System.out.println(toChar(v) + " -> Source Point");
            }
            System.out.println("");
    }

}//end graph algorithm