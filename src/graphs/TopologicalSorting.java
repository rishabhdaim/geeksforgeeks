/**
 * 
 */
package graphs;

/**
 * @author aa49442
 * 
 */
public class TopologicalSorting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final DGraph<Integer> dGraph = new DGraph<>(6);

		for (int i = 0; i < 6; i++)
			dGraph.addVertex(i);

		dGraph.addEdge(2, 3);
		dGraph.addEdge(3, 1);
		dGraph.addEdge(4, 0);
		dGraph.addEdge(4, 1);
		dGraph.addEdge(5, 0);
		dGraph.addEdge(5, 2);

		dGraph.topologicalSort();
		dGraph.dfs();
		
		// adj list graph
		System.out.println("List Graph");
		final GraphList graphList = new GraphList(6, true);

		for (int i = 0; i < 6; i++)
			graphList.addVertex(i);

		graphList.addEdge(2, 3);
		graphList.addEdge(3, 1);
		graphList.addEdge(4, 0);
		graphList.addEdge(4, 1);
		graphList.addEdge(5, 0);
		graphList.addEdge(5, 2);

		graphList.topologicalSort();
		graphList.allTopologicalSort();
	}
}