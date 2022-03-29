import java.util.Stack;

public class SP {
	
	SP(edgeWeightedDigraph G, int s);
	public double distTo(int v)
	{
		return distTo[v];
	}

	boolean hasPathTo(int v) 
	{
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	Iterable<DirectedEdge> pathTo(int v) 
	{
		if (!hasPathTo(v)) return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
		path.push(e);
		return path;
	}
	
	public static void main(String[] args)
	{
		edgeWeightedDigraph G;
		G = new edgeWeightedDigraph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		SP sp = new SP(G, s);
		for (int t = 0; t < G.V(); t++)
		{
			System.out.print(s + " to " + t);
			System.out.printf(" (%4.2f): ", sp.distTo(t));
			if (sp.hasPathTo(t))
			{
				for (DirectedEdge e : sp.pathTo(t))
				{
					System.out.print(e + " ");
				}
			}
			System.out.println();
			}
		}
	}

}
