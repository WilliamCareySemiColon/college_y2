#include <iostream.h>
#include <stdlib.h>
#include "minHeap2.cpp"  // modified version of minHeap.cpp


class HuffmanTree {

  private:
      int * dad;        // store the Huffman tree
      int * count;      // stores freq or prob of each char
      int * code;       // store codeword for each char 
      int * len;        // stores number of bits in each codeword
      int m;            // m = alphabet size
      int n;            // n >= total number of nodes in Huffman tree
          
  public:
    HuffmanTree( int count_[], int asize, int size) 
    {
        count = count_;
        m = asize;
        n = size;

        // h is a priority queue initially empty
        MinHeap h(count);  
        dad = new int[n+1];

        int i, t1, t2;

        //insert alphabet into heap
        // heap will use count[] for prioritising
        for(i = 1; i <= m; ++i)
            if( count[i] != 0) h.insert( i);

        while( h.size() > 1) {
            t1 = h.remove();
            t2 = h.remove();                                
            count[i] = count[t1] + count[t2]; 
            dad[t1] = i;  dad[t2] = -i;
            h.insert( i); ++i;
        }
        dad[--i] = 0;
    }


    // Store codewords as a decimal number in an array code[]
    // When number converted to binary, you get the codeword
    // Also store the number of bits for each codeword in len[]
    void buildCodewords() 
    {
        int x, j, i, t;
        code = new int[m+1];
        len = new int[m+1];

        for(int k=1; k<=m; ++k) 
        {                    
            x = 0; j = 1; i = 0;
            t = dad[k];            
            while( t != 0) {
                if(t < 0) { t = -t; x = x + j;}            
                j = 2 * j;  ++i;  
                t = dad[t];
            }
            len[k] = i;
            code[k] = x;
            
        }
    }



    void displayCodewords()
    {
        cout << "\nCode words for alphabet are:\n";
        for(int k=1; k<=m; ++k) {
            cout << "code[" << char (k+64) << "] is " << codewordToStr(k) << "\n";
        }
        cout << endl;
    }



    char * codewordToStr( int k)
    {
        int w, l;
        char * str;

        w = code[k]; l = len[k]; str = new char[l+1];
        for(int i = l-1; i >= 0; --i) {
            str[i]  =  w % 2 == 0? '0' : '1';
            w = w / 2;
        }
        str[l] = '\0';
        return str;
    }

}; // end of class



void main() 
{
    int count[20];
    for(int i=0; i< 20; ++i) count[i] = 0;

    count[1] = 35; count[2] = 10; count[3] = 20; count[4] = 20; count[5] = 15;
        
    HuffmanTree ht( count, 5, 15);
  
    ht.buildCodewords();
    
    ht.displayCodewords();
}
