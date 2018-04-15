// Kruskal's Minimum Spanning Tree Algorithm
// Union-find implemented using disjoint set trees without compression

import java.io.*;
import java.util.*;    

//class Kruskal tree to demonstate the kruskal algorithm in the main function using the heap class, graph class and edge class
class KruskalTrees {
    public static void main(String[] args) throws IOException
    {
        //String fname = "../wGraph3.txt";
        Scanner mstPrim = new Scanner(System.in);
        System.out.println("What is the name of the file ");
        String fname = mstPrim.nextLine();
        
        //System.out.print("\nInput name of file with graph definition: ");
        //fname = Console.ReadLine();

        Graph g = new Graph(fname);

        g.MST_Kruskal();

        g.showMST();
        
    }
}  