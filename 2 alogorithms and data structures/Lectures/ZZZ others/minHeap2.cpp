// minHeap2.cpp
// modified for use in building Huffman Coding Tree
// do not need hPos[] 

#include <iostream.h>
#include <limits.h>
#include <math.h>

#define maxH 200

class MinHeap
{

private:
   int *h;		// heap array
//   int *hPos;	// hPos[h[k]] == k
   int *val;	// val[v] = priority of v

   int N;
   
public:
   MinHeap( int val[]);

   void siftUp( int k);
   void siftDown( int k);
   
   void insert( int x);
   int remove();
   int size();

   void display();
   void displayAsChars();
};




MinHeap::MinHeap(int _val[]) {
   N = 0;
   h = new int[maxH+1];
   val = _val;
   //hPos = _hPos;

}


int MinHeap::size() { return N;}


void MinHeap::siftUp( int k) {
   int v = h[k];

   h[0] = 0;
   val[0] = INT_MIN;

   while( val[v] < val[h[k/2]]) {
      h[k] = h[k/2];
	  //hPos[h[k]] = k;
      k = k/2;
   }
   h[k] = v;
   //hPos[v] = k;
}



void MinHeap::siftDown( int k) {
   int v, j;
   
   v = h[k];  
   while( k <= N/2) {
      j = 2 * k;
      if(j < N && val[h[j]] > val[h[j+1]]) ++j;
      if( val[v] <= val[h[j]]) break;
      h[k] = h[j]; 	  
	  //hPos[h[k]] = k;
	  k = j;
   }
   h[k] = v;
   //hPos[v] = k;
}



void MinHeap::insert( int x) {
   h[++N] = x;
   siftUp( N);
}



int MinHeap::remove() {   
   h[0] = h[1];
   h[1] = h[N--];
   siftDown(1);
   return h[0];
}



void MinHeap::displayAsChars() {  
   
   cout << (char)h[1] << endl;
   for(int i = 1; i<= N/2; i = i * 2) {
      for(int j = 2*i; j < 4*i && j <= N; ++j)
         cout << (char) h[j] << "  ";
       cout << endl;
   }
}


void MinHeap::display() {  
   
   cout << h[1] << endl;
   for(int i = 1; i<= N/2; i = i * 2) {
      for(int j = 2*i; j < 4*i && j <= N; ++j)
         cout << h[j] << "  ";
       cout << endl;
   }
}


