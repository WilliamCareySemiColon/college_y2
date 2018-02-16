// Sorted linked list with a sentinel node
// Skeleton code

import java.util.*;

class SortedLL
{
    // internal data structure and
    // constructor code to be added here
    class Node
    {
        int data;
        Node next;
    }

    Node head, z;
	
	SortedLL()
    {
		z = new Node();
		z.next = z;

        head = z;
    }
    // this is the crucial method
    public void insert(int x)
    {
        Node prev = null, curr = head, t = new Node(); 
		t.data = z.data = x;
        // write code to insert x into sorted list
        if (head == z)
		{
			t.next = z;
			head = t;
		}
		else
		{
			while(t.data > curr.data)
			{
				prev = curr;
				curr = curr.next;
			}
			
			t.next = curr;
			
			if (prev == null)
				head = t;
			else
				prev.next = t;
		}
    }    
    
    public boolean remove(int x) 
	{
        Node prev = null, curr = head, t = new Node(); 
		t.data = z.data = x;
		t.next = null;
		
		if (head == z)
		{
			return false;
		}
		
		else
		{
			while (t.data != curr.data)
			{	
				prev = curr;
				curr = curr.next;
			}
			if (z == curr)
				return false;

			if (prev == null)
				head = curr.next;
			else
				prev.next = curr.next;	
			return true;
		}
	}

	public boolean isEmpty()
	{
		return (head == z ? true : false);
	}

    public void display()
    {
        Node t = head;
        System.out.print("\nHead -> ");
        while( t != z) {
            System.out.print(t.data + " -> ");
            t = t.next;
        }
        System.out.println("Z\n");
    }
    
   /* public static void main(String args[])   
    {
        SortedLL list = new SortedLL();
        list.display();
        
        int x;
		int [] y = new int[10];
        //Random r = new Random();
        
        for(int i = 1; i <= 10; ++i)  {
           x =  (int) (Math.random()*100.0);
           y[i-1] = x;
           System.out.println("Inserting " + x);
           list.insert(x);
           list.display();            
        }
		
		for(int i = 1; i <= 10; ++i)  
		{
           System.out.println("removing " + y[i-1]);
           list.remove(y[i-1]);
           list.display();            
        }
        
    	}*/
	    public static void main(String args[])   
	    {
		    SortedLL list = new SortedLL();
		    list.display();
		    
		    double x;
		    int i, r;
		    
		   for(i = 1; i <= 5; ++i)
		   {
			   x =  (Math.random()*100.0);
			   r = (int) x; 
			   System.out.println("Inserting " + r);
			   list.insert(r);
			   list.display();            
		   }
		    
		    
		    
		    while(!list.isEmpty())  
		    {
			    System.out.println("\nInput a value to remove: ");
			    Scanner in = new Scanner(System.in);
			    r = in.nextInt();
			    if(list.remove(r)) 
			    {
			        System.out.println("\nSuccessfully removed: " + r);
			    	list.display(); 
				}
			    else 
			    	System.out.println("\nValue not in list: " + r);                        
	    	}
	    }
}