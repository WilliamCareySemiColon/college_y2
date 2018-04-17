// Simple weighted graph representation 
// Uses an Adjacency Linked Lists, suitable for sparse graphs

package PrimList.myFolder;

import java.io.*;
import java.util.*;

/*********************************************************************************************************************************
Main function
*********************************************************************************************************************************/
public class PrimLists {
    public static void main(String[] args) throws IOException
    {
    	Scanner mstPrim = new Scanner(System.in);
    	System.out.println("What is the name of the file ");
    	String fname = mstPrim.nextLine();
    	System.out.println("What is the starting vertice");
        int s = Integer.parseInt(mstPrim.nextLine());
        //String fname = "../wGraph3.txt";               

        Graph g = new Graph(fname);
       
        g.MST_Prim(s);
    }
    
    
}
