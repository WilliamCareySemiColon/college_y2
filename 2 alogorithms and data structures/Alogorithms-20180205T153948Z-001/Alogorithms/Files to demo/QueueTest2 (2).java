// Exercise to separate ADT Queue from its implementation
// and to provide 2 implementations. Also exception handling.

class QueueException extends Exception 
{
	public QueueException(String s) 
	{
		super(s);
	}
}    

// In Java an interface can often be the best way to 
// describe an Abstract Data Type (ADT) such as Queue or Stack
interface Queue 
{
    public void enQueue(int x) throws QueueException;
    public int deQueue() throws QueueException;
    public boolean isEmpty();   
}


class QueueLL implements Queue 
{
	
	class linkedList
	{
		int data;
		linkedList next;
	}
	
	linkedList head, tail;
	int sizeOfLinkedList;

 
    public QueueLL() 
	{
        head = tail = new linkedList();
		size = 0;
    }

	
	public void enQueue( int x) throws QueueException  
	{
		try
		{ 
			linkedList test = head;
			
			while (test != tail.next)
			{
				test = test.next;
			}
			
			if (isEmpty())
				head.next = test;
			else
				tail.next = test;
			
			test.data = x;
			test.next = tail;
			tail.next = test;
			size++;
		}
		catch (QueueException e) 
		{
            System.out.println("Exception thrown: " + e); 
        } 
    }
	
    // assume the queue is non-empty when this method is called, otherwise thro exception
    public int deQueue() throws QueueException 
	{
		try
		{
			if (!(isEmpty())
			{
				head = head.next; 
				size--;
			}				
		}
		
		catch (QueueException e) 
		{
            System.out.println("Exception thrown: " + e); 
        } 
    }
	
	public boolean isEmpty()
	{
		return (size == 0 ? true : false);
	}

} // end of QueueLL class



class QueueCB implements Queue 
{
    private int q[], back, front;
    private int qmax, size;

 
    public QueueCB() 
	{
        qmax = 4;
        size = front = back = 0;
        q = new int[qmax];
    }

    public void enQueue( int x) throws QueueException  
	{
        q[back] = x;
		back = (back + 1) % qmax;
		size++;
    }
  
    public int deQueue()  throws QueueException 
    {
        int value = q[front];
		front = (front + 1) % qmax;
		size--;
	}

    public boolean isEmpty() 
	{
        return (size == 0 ? true : false);
    }
}


// here we test both implementations
class QueueTest2 
{
    public static void main(String[] arg) 
	{
        Queue q1, q2;
        q1 = new QueueLL();
        q2 = new QueueCB();
        
        for(int i = 1; i<6; ++i)
			try 
			{ 
				q1.enQueue(i);            
			} 
			
			catch (QueueException e) 
			{
				System.out.println("Exception thrown: " + e); 
			}
        
        // more test code
    }   
	
	for(int i = 1; i<3; ++i)
        try 
		{ 
            q1.deQueue(i);            
        } 
		
		catch (QueueException e) 
		{
            System.out.println("Exception thrown: " + e); 
        }
		
		for(int i = 1; i<6; ++i)
			try 
			{ 
				q2.enQueue(i);            
			} 
			
			catch (QueueException e) 
			{
				System.out.println("Exception thrown: " + e); 
			}
        
        // more test code
    }   
	
	for(int i = 1; i<3; ++i)
        try 
		{ 
            q2.deQueue(i);            
        } 
		
		catch (QueueException e) 
		{
            System.out.println("Exception thrown: " + e); 
        }
}

