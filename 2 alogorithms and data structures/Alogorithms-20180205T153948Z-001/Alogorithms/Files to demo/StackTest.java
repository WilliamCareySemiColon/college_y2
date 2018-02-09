// StackTest.java
// Linked list implementation of Stack

class Stack 
{
    
    class Node 
	{
        int data;
        Node next;  
    }
    private Node top;
      
    public Stack()
    { 
        top = null;
    }
        
    public void push(int x) 
	{
        Node  t = new Node();
        t.data = x;
        t.next = top;
        top = t;
    }

    // only to be called if list is non-empty.
    // Otherwise an exception should be thrown.
   public int pop()
   {
		int data = top.data;
		Node  t = top;
		top = t.next;
		
		return data;
   } 

    /*
    public boolean isEmpty(){
       do yourself
    }*/


    public void display() 
	{
        Node t = top;
        //Console.Write("\nStack contents are:  ");
        System.out.println("\nStack contents are:  ");
        
        while (t != null) 
		{
            //Console.Write("{0} ", t.data);
            System.out.print(t.data + " ");
            t = t.next;
        }
        //Console.Write("\n");
        System.out.println("\n");
    }
	
	public boolean isMember(int x)
	{
		Node t = top;
		
		while (t != null) 
		{
            if (x == t.data)
				return true;
			
            t = t.next;
        }
		
		return false;
	}
	
	public void size()
	{
		int number = 0;
		Node t = top;
		
		while(t != null)
		{
			number++;
			t = t.next;
		}
		
		System.out.println("Number of members in stack are " + number);
	}

}


public class StackTest
{
    public static void main( String[] arg)
	{
        Stack s = new Stack();
        //Console.Write("Stack is created\n");
        System.out.println("Stack is created\n");
        
		//testing the push function
        s.push(10); 
		s.push(3); 
		s.push(11); 
		s.push(7);
		
        s.display();
		s.size();
        
       
       int i = s.pop();
       System.out.println("Just popped " + i);
       s.display();
	   s.size();
	   
	   boolean state = s.isMember(10);
	   if(state)
		   System.out.println("10 exists");
	   else
		   System.out.println("10 non-existen " );
	   
	   state = s.isMember(2);
	   if(state)
		   System.out.println("2 exists");
	   else
		   System.out.println("2 non-existen " );
	   
	   
    }
}


