/**
 * 
 */
package graphs;

/**
 * @author aa49442
 * 
 */
public class Traversals {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final DGraph<Integer> dGraph = new DGraph<>(4);

		for (int i = 0; i < 4; i++)
			dGraph.addVertex(i);

		dGraph.addEdge(0, 1);
		dGraph.addEdge(0, 2);
		dGraph.addEdge(1, 2);
		dGraph.addEdge(2, 0);
		dGraph.addEdge(2, 3);
		dGraph.addEdge(3, 3);

		dGraph.dfs();

		System.out.println();
		dGraph.bfs();

		System.out.println();

		boolean b = dGraph.isReachable(2, 0);
		System.out.println(b);
		
		
		GraphList graphList = new GraphList(4, false);

		for (int i = 0; i < 4; i++)
			graphList.addVertex(i);

		graphList.addEdge(0, 1);
		graphList.addEdge(0, 2);
		graphList.addEdge(1, 2);
		graphList.addEdge(2, 0);
		graphList.addEdge(2, 3);
		graphList.addEdge(3, 3);

		graphList.dfs(0);
		graphList.dfs(3);

		graphList.bfs(0);
		graphList.bfs(3);

		b = graphList.isReachable(2, 0);
		System.out.println("Is Reacable : " + b);
		graphList.printAllPaths(0, 3);
		
		graphList = new GraphList(15, false);
		for (int i = 0; i < 15; i++)
			graphList.addVertex(i);
		
		graphList.addEdge(0, 4);
	    graphList.addEdge(1, 4);
	    graphList.addEdge(2, 5);
	    graphList.addEdge(3, 5);
	    graphList.addEdge(4, 6);
	    graphList.addEdge(5, 6);
	    graphList.addEdge(6, 7);
	    graphList.addEdge(7, 8);
	    graphList.addEdge(8, 9);
	    graphList.addEdge(8, 10);
	    graphList.addEdge(9, 11);
	    graphList.addEdge(9, 12);
	    graphList.addEdge(10, 13);
	    graphList.addEdge(10, 14);
	    
	    graphList.biDirectionalSearch(0, 14);
	}
}