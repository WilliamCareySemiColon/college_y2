// Sorted linked list without a sentinel node
// Skeleton code

import java.util.*;

class SortedLList
{
    // internal data structure and
    // constructor code to be added here
    class Node
    {
        int data;
        Node next;
    }

    Node head;
	
	SortedLList()
    {
		head = null;
    }
    // this is the crucial method
    public void insert(int x)
    {
        Node prev = null, curr = head, t = new Node(); 
		t.data = x;
        // write code to insert x into sorted list
        if (head == null)
		{
			t.next = null;
			head = t;
		}
		else
		{
			while(t.data > curr.data && curr.next != null)
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
    
    public void remove(int x) 
	{
        Node prev = null, curr = head, t = new Node(); 
		t.data  = x;
		t.next = null;
		
		if (head == null)
		{
			System.out.println("There is no nodes currently");
		}
		
		else
		{
			while (t.data != curr.data && curr.next != null)
			{	
				prev = curr;
				curr = curr.next;
			}
			
			if (prev == null)
				head = curr.next;
			else
				prev.next = curr.next;	
		}
	}

    public void display()
    {
        Node t = head;
        System.out.print("\nHead -> ");
        while( t != null) {
            System.out.print(t.data + " -> ");
            t = t.next;
        }
        System.out.println("Null\n");
    }
    
    public static void main(String args[])   
    {
        SortedLList list = new SortedLList();
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
        
    }
}