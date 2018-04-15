//Heap to sort the edges based off their weight in association with each vertices
class Heap
{
    private int[] h;
    int N, Nmax;
    Edge[] edge;


    // Bottom up heap construc
    public Heap(int N, Edge[] edge) {
        int i,j, temp;
        Nmax = this.N = N;
        h = new int[N+1];
        this.edge = edge;
       
        // initially just fill heap array with 
        // indices of edge[] array.
        for (i=0; i <= N; ++i) 
            h[i] = i;
           
        // Then convert h[] into a heap
        // from the bottom up.
        for(i = N/2; i > 0; --i)
        {
            siftDown(i);
        }

    }

    private void siftDown( int k) {
        int e, j;

        e = h[k];
        while( k <= N/2) {
            j = 2*k;
            if (j < N && edge[h[j]].wgt > edge[h[j+1]].wgt) j++;

            if (edge[e].wgt < edge[h[j]].wgt) break;

            h[k] = h[j];
            k = j;
        }
        h[k] = e;
    }


    public int remove() {
        h[0] = h[1];
        h[1] = h[N--];
        siftDown(1);
        return h[0];
    }
}