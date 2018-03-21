// Simple weighted graph representation 
// Uses an Adjacency Linked Lists, suitable for sparse graphs

import java.io.*;

class GraphLists {
    
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
    
    // used for traversing graph
    private int[] visited;
    private int id;
    
    
    // default constructor
    public GraphLists(String graphFile)  throws IOException
    {
        int u, v;
        int e, wgt;
        Node t;

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
        visited = new int[V+1];
        adj = new Node[E+1];

        for(v = 1; v <= V; ++v)
           adj[v] = z;               
        
        // read the edges
        System.out.println("Reading edges from text file");
        
        // missing for loop here
        for(e = 1; e <= E; ++e)
       {
            line = reader.readLine();
            parts = line.split(splits);
            Node node = new Node();
            node.vert = Integer.parseInt(parts[0]);
            node.wgt = Integer.parseInt(parts[2]); 
            node.next = adj[Integer.parseInt(parts[1])];
            v = Integer.parseInt(parts[1]); 
            
            System.out.println("Edge " + toChar(node.vert) + "--(" + node.wgt + ")--" + toChar(v));    
            adj[e] = node; 
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
        }
        System.out.println("");
    }


    // method to initialise Depth First Traversal of Graph
    public void DF( int s) 
    {
        id = 0;

        for(int v = 1; v <= V; v++)
            visited[v] = 0;

        dfVisit(0,s);
    }


    // Recursive Depth First Traversal for adjacency matrix
    private void dfVisit( int prev, int v)
    {
        visited[v] = ++id;

        System.out.println("visited vertex " + toChar(v) + " Alongside edge " + adj[v].wgt);
        /*for(int u : adj[v])
        {
            if (visited[u] == 0 && u != 0)
                dfVisit(v,adj[id][u]);
            
           //System.out.println(u);
        }*/

        for (int u = 1; u <= V; u++)
        {
            if (visited[u] == 0 && adj[u].wgt != 0)
                dfVisit(v,u);
        }      
    }


    public static void main(String[] args) throws IOException
    {
        int s = 4;
        String fname = "wGraph3.txt";               

        GraphLists g = new GraphLists(fname);
       
        g.display();
        
        //g.DF(s);
    }

}

