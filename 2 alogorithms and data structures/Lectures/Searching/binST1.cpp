//  BinST1.cpp
// insert() and inTravers() implemented only.
#include <iostream.h>


// Here a BinST has a dummy node and a null node z
// which acts as a terminal node.  The nodes with data
// are internal nodes. The dummy node at the start to which
// head points, makes the coding easier.

class BinST 
{
	private:

    // a tree node may have data and key value and pointers
    // to the left and right subtrees. A node has 2
    // constructors.
    struct Node 
    {
        int key;
        Node * l;
        Node * r;

        Node() 
        {
            key = -1;
            l = r = NULL;
        }

        Node( int x, Node * ll, Node * rr) 
        {
            key = x;
            l = ll;
            r = rr;
        }
    };


    Node * head, * z;
    void inOrder( Node *);

	public:
	    BinST();
	    void insert( int x);
	    void inOrder();
};


// Binary Tree constructor which creates the terminator or
// null node and a dummy node before the root of the tree.
BinST::BinST() 
{
   z = new Node(); 
   z->l = z; 
   z->r = z;
   head = new Node(-1, z, z);
}


void BinST::insert( int x) 
{
    Node * t = new Node(x, z, z);
    Node * p = head;
    Node * c = head->r;

    // find the path down through the tree to a terminal
    // node where the new node t can be inserted.
    while(c != z) 
    {
       p = c;
       if(x < c->key)
           c = c->l;
       else
           c = c->r;
    }

    // insert the node by making a link from the previous
    // node to it.
    if(x < p->key)
        p->l = t;
    else
        p->r = t;
}



// method for inOrder traversal of the binary tree which
// calls am auxilliary recursive method of the same name.
void BinST::inOrder() 
{
    inOrder( head->r);
}


// auxialliary private methode which recursively
// traverses the tree in inOrder fashion.
void BinST::inOrder( Node * t) 
{
    if(t == z) return;

    inOrder( t->l);
    cout << "Just visited node with key " << t->key << endl;
    inOrder( t->r);
}


// create a binary tree, insert some values and traverse it.
void main() 
{
    BinST t;
    t.insert(10); t.insert(5); t.insert(14);  t.insert(6);  t.insert(3);
    t.insert(6);  t.insert(12);  t.insert(20);

    t.inOrder();
}


