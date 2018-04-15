/****************************************************
*
*UnionFind partition to support union-find operations
*Implemented simply using Discrete Set Trees without 
*compression
*****************************************************/

class UnionFindSets
{
    private int[] treeParent;
    private int N;
    
    public UnionFindSets( int V)
    {
        N = V;
        treeParent = new int[V+1];
        // missing lines
        for (int i = 1; i <= V; i++)
        	treeParent[i] = i;
    }

    public int findSet( int vertex)
    {   
        if (treeParent[vertex] == vertex)
        	return vertex;
    	else
    		return findSet(treeParent[vertex]);
    }
    
    public void union( int set1, int set2)
    {
        int x = findSet(set1);
        int y = findSet(set2);
        treeParent[y] = x;
    }
    
    public void showTrees()
    {
        int i;
        for(i=1; i<=N; ++i)
            System.out.print(toChar(i) + "->" + toChar(treeParent[i]) + "  " );
        System.out.print("\n");
    }
    
    public void showSets()
    {
        int u, root;
        int[] shown = new int[N+1];
        for (u=1; u<=N; ++u)
        {   
            root = findSet(u);
            if(shown[root] != 1) {
                showSet(root);
                shown[root] = 1;
            }            
        }   
        System.out.print("\n");
    }

    private void showSet(int root)
    {
        int v;
        System.out.print("Set{");
        for(v=1; v<=N; ++v)
            if(findSet(v) == root)
                System.out.print(toChar(v) + " ");
        System.out.print("}  ");
    
    }
    
    private char toChar(int u)
    {  
        return (char)(u + 64);
    }
}