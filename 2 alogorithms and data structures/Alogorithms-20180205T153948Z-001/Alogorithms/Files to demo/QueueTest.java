// QueueTest.java
// Allocation of Queue objects in main()

class Queue 
{

	private class Node 
	{
		int data;
		Node next;
	}

	Node z;
	Node head;
	Node tail;

	public Queue() 
	{
		z = new Node(); 
		z.next = z;
		head = z;  
		tail = null;
	}


	public void display() 
	{
		System.out.println("\nThe queue values are:\n");

		Node temp = head;
		while( temp != temp.next) 
		{
			System.out.print( temp.data + "  ");
			temp = temp.next;
		}
		System.out.println("\n");
	}


	public void enQueue( int x) 
	{
		Node temp;

		temp = new Node();
		temp.data = x;
		temp.next = z;

		if(head == z)    // case of empty list
		head = temp;
		else                // case of list not empty
		tail.next = temp;

		tail = temp;        // new node is now at the tail
	}


	// assume the queue is non-empty when this method is called
	public int deQueue() 
	{
		int data = head.data;
		head = head.next;

		return data;
	}


	public boolean isEmpty() 
	{
		if (head == null)
		return true;
		else
		return false;
	}
	
	public boolean isMember(int x)
	{
		Node d = head;
		
		while (d != tail)
		{
			if(d.data == x)
				return true;
			
			d = d.next;
		}
		
		return false;
	}
	
	public int size()
	{
		Node d = head;
		int x = 0;
		
		while (d != tail.next)
		{	
			d = d.next;
			x++;
		}
		
		return x;
	}
} // end of Queue class



class QueueTest 
{

	// try out the ADT Queue using static allocation
	public static void main(String[] arg) 
	{
		int dataSize;
		boolean statement;
		Queue q = new Queue();

		System.out.println("Inserting ints from 9 to 1 into queue gives:\n");
		for (int i = 9; i > 0; --i) 
		{
		q.enQueue( i);
		}

		q.display();
		dataSize = q.size();
		System.out.println( dataSize + "  ");

		if( ! q.isEmpty())
		System.out.println("Deleting value from queue " + q.deQueue() + "\n");

		statement = q.isMember(8);
		System.out.println( statement + "  ");
		System.out.println("Adding value to queue " + 27 + "\n");
		q.enQueue(27);
		statement = q.isMember(100);
		System.out.println(statement + "  ");
		q.display();
		
	}

} //end of Test class

