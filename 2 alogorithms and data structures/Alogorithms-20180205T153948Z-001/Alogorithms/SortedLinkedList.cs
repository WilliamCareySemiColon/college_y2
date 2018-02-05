// Sorted linked list with a sentinel node
// Skeleton code
using System;

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
        head = z = new Node();
    }

    // this is the crucial method
    public void insert(int x)
    {
        Node cur = head;
        int y = x;

        // write code to insert x into sorted list
        while (curr.next != null && y > curr.data)
        {
            cur = cur.next;
        }

        z.next = cur

        z.data = y;
        cur = z;
    }

    public void display()
    {
        Node t = head;
        Console.Write("\nHead -> ");
        while (t != z)
        {
            Console.Write("{0} -> ", t.data);
            t = t.next;
        }
        Console.Write("Z\n");
    }

    public static void Main()
    {
        SortedLL list = new SortedLL();
        list.display();

        int i, x;
        Random r = new Random();

        for (i = 0; i < 10; ++i)
        {
            x = r.Next(20);
            list.insert(x);
            Console.Write("\nInserting {0}", x);
            list.display();
        }
        Console.ReadKey();
    }
}