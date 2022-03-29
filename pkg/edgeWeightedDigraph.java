
public class edgeWeightedDigraph {
	private final int V; // number of vertices
	private int E; // number of edges
	private Bag<DirectedEdge>[] adj; // adjacency lists
	
	
	
	public edgeWeightedDigraph(int V)
	{
		this.V = V;
		this.E = 0;
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for (int v = 0; v < V; v++)
		{
			adj[v] = new Bag<DirectedEdge>();
		}
	}
	
	public edgeWeightedDigraph(In in)
	{
		this(in.readInt()); // Read V and construct this graph.
		int E = in.readInt(); // Read E.
		for (int i = 0; i < E; i++)
		{ // Add an edge.
		int v = in.readInt(); // Read a vertex,
		int w = in.readInt(); // read another vertex,
		addEdge(v, w); // and add edge connecting them.
		}
	}
	
	
	// See Exercise 4.4.2.
	public int V() { return V; }
	public int E() { return E; }
	
	public void addEdge(DirectedEdge e)
	{
		adj[e.from()].add(e);
		E++;
	}
	public Iterable<Edge> adj(int v)
	{ 
		return adj[v]; }
	public Iterable<DirectedEdge> edges()
	{
		Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
		for (int v = 0; v < V; v++)
		{
			for (DirectedEdge e : adj[v])
			{
				bag.add(e);
			}
		}
	return bag;
	}
	

}
