/**
 * 
 */
package graphs;

/**
 * @author aa49442
 * 
 */
public class MST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final WUnDGraph<Integer> wUnDGraph = new WUnDGraph<>(9);

		for (int i = 0; i < 9; i++)
			wUnDGraph.addVertex(i);

		wUnDGraph.addEdge(0, 1, 4);
		wUnDGraph.addEdge(0, 7, 8);
		wUnDGraph.addEdge(1, 2, 8);
		wUnDGraph.addEdge(1, 7, 11);
		wUnDGraph.addEdge(2, 3, 7);
		wUnDGraph.addEdge(2, 5, 4);
		wUnDGraph.addEdge(2, 8, 2);
		wUnDGraph.addEdge(3, 4, 9);
		wUnDGraph.addEdge(3, 5, 14);
		wUnDGraph.addEdge(4, 5, 10);
		wUnDGraph.addEdge(5, 6, 2);

		wUnDGraph.addEdge(6, 7, 1);
		wUnDGraph.addEdge(6, 8, 6);
		wUnDGraph.addEdge(7, 8, 7);

		wUnDGraph.kruskalMST();
		wUnDGraph.primMST();
		wUnDGraph.dijkstraAlgo(6);
		
		final WGraphList wGraphList = new WGraphList(9, false);

		for (int i = 0; i < 9; i++)
			wGraphList.addVertex(i);

		wGraphList.addEdge(0, 1, 4);
		wGraphList.addEdge(0, 7, 8);
		wGraphList.addEdge(1, 2, 8);
		wGraphList.addEdge(1, 7, 11);
		wGraphList.addEdge(2, 3, 7);
		wGraphList.addEdge(2, 5, 4);
		wGraphList.addEdge(2, 8, 2);
		wGraphList.addEdge(3, 4, 9);
		wGraphList.addEdge(3, 5, 14);
		wGraphList.addEdge(4, 5, 10);
		wGraphList.addEdge(5, 6, 2);
		wGraphList.addEdge(6, 7, 1);
		wGraphList.addEdge(6, 8, 6);
		wGraphList.addEdge(7, 8, 7);

		wGraphList.kruskalMST();
		wGraphList.primMST(0);
		
		final GraphList graphList = new GraphList(9, true);

		for (int i = 0; i < 9; i++)
			graphList.addVertex(i);

		graphList.addEdge(0, 1);
		graphList.addEdge(0, 7);
		graphList.addEdge(1, 2);
		graphList.addEdge(1, 7);
		graphList.addEdge(2, 3);
		graphList.addEdge(2, 5);
		graphList.addEdge(2, 8);
		graphList.addEdge(3, 4);
		graphList.addEdge(3, 5);
		graphList.addEdge(4, 5);
		graphList.addEdge(5, 6);

		graphList.addEdge(6, 7);
		graphList.addEdge(6, 8);
		graphList.addEdge(7, 8);

		graphList.kruskalMST();
		
	}
}