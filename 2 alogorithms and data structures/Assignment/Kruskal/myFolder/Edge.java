//Class edge to store all the indiviadual information about each edge and vertices 
class Edge {
    public int u, v, wgt;

    public Edge() {
        this(0,0,0);
    }

    public Edge( int x, int y, int w) {
        u = x;
        v = y;
        wgt = w;
    }
    
    public void show() {
        System.out.print("Edge " + toChar(u) + "--" + wgt + "--" + toChar(v) + "\n") ;
    }
    
    // convert vertex into char for pretty printing
    private char toChar(int u)
    {  
        return (char)(u + 64);
    }
}