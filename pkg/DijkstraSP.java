import java.util.ArrayList;

//This class was taken from the Sedgewick and Wayne textbook and has been altered to fit the project
public class DijkstraSP
	{
	private double[] distTo;          
    private DirectedEdge[] edgeTo;    
    private IndexMinPQ<Double> pq; 
    public ArrayList<busStops> shortestRoute;
    public ArrayList<busStops> stops;

    public DijkstraSP(edgeWeightedDigraph G, int startNode) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];


        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[startNode] = 0.0;

        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(startNode, distTo[startNode]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : G.adj(v))
                relax(e);
        }

        assert check(G, startNode);
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }

    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }
    
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v, ArrayList<busStops> stopList) { 
    	ArrayList <Integer> stopID = new ArrayList<Integer>();
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        ArrayList<DirectedEdge> path = new ArrayList<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.add(e);
            stopID.add(e.from());
        }
        for (int i = 0; i < stopList.size(); i ++)
        {
        	for (int j = 0; j < stopID.size(); j ++)
        	{
        		if (stopList.get(i).stopID == stopID.get(j))
        		{
        			System.out.println("Stop ID: " + stopList.get(i).stopID);
        			System.out.println("Stop Name: " + stopList.get(i).stopName);
        			System.out.println("Stop Description: " + stopList.get(i).stopDesc);
        			System.out.println("");
        		}
        	}
        }
        return path;
    }

    private boolean check(edgeWeightedDigraph G, int s) {

        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            if (v == s) continue;
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        for (int v = 0; v < G.V(); v++) {
            for (DirectedEdge e : G.adj(v)) {
                int w = e.to();
                if (distTo[v] + e.weight() < distTo[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        for (int w = 0; w < G.V(); w++) {
            if (edgeTo[w] == null) continue;
            DirectedEdge e = edgeTo[w];
            int v = e.from();
            if (w != e.to()) return false;
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }

    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }    
}
