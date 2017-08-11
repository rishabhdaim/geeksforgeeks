/**
 * 
 */
package graphs;

/**
 * @author aa49442
 * 
 */
public class PossiblePaths {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final DGraph<Integer> graph = new DGraph<>(4);

		graph.addVertex(0);
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);

		System.out.println(graph);

		graph.printAdjMat();

		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);

		graph.printAdjMat();

		int count = graph.countWalks(0, 3, 2);

		System.out.println(count);

		count = graph.countWalksOpt(0, 3, 2);
		System.out.println(count);
	}
}