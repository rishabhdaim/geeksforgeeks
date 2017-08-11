/**
 * 
 */
package graphs;

/**
 * To check whether graph has cycle or not
 * 
 * @author rishabh.daim 
 * 
 */
public class CycleProb {

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

		boolean b = dGraph.isCyclic();
		System.out.println(b);

		final WUnDGraph<Integer> unDGraph = new WUnDGraph<>(6);

		for (int i = 0; i <=5; i++)
			unDGraph.addVertex(i);

		unDGraph.addEdge(0, 1, 1);
		unDGraph.addEdge(1, 2, 1);
		unDGraph.addEdge(2, 3, 1);
		unDGraph.addEdge(3, 4, 1);
		unDGraph.addEdge(4, 5, 1);
		unDGraph.addEdge(5, 0, 1);

		// b = unDGraph.unionFindAlgoCyclic();

		System.out.println(b);
		
		GraphList graphList = new GraphList(4, true);

		for (int i = 0; i < 4; i++)
			graphList.addVertex(i);

//		unDGraphList.addEdge(0, 1);
		graphList.addEdge(0, 2);
//		unDGraphList.addEdge(1, 2);
		graphList.addEdge(2, 0);
		graphList.addEdge(2, 3);
//		unDGraphList.addEdge(3, 3);

		b = graphList.isCyclicDirected();
		System.out.println(b);
		
		b = graphList.isCyclicColor();
		System.out.println(b);
		
		b = graphList.isCyclicUnionFind();
		System.out.println(b);
		
		b = graphList.isCyclicUnionFindRank();
		System.out.println(b);
		
		// un directed graph
		graphList = new GraphList(4, false);

		for (int i = 0; i < 5; i++)
			graphList.addVertex(i);

		graphList.addEdge(1, 0);
		graphList.addEdge(0, 2);
		graphList.addEdge(2, 0);
		graphList.addEdge(0, 3);
		graphList.addEdge(3, 4);

		b = graphList.isCyclicUnDirected();
		System.out.println(b);
		
		// un directed graph
		graphList = new GraphList(4, false);

		for (int i = 0; i < 5; i++)
			graphList.addVertex(i);

		graphList.addEdge(0, 1);
		graphList.addEdge(1, 2);

		b = graphList.isCyclicUnDirected();
		System.out.println(b);
	}
}