/**
 * 
 */
package graphs;

/**
 * @author aa49442
 * 
 */
public class SCC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DGraph<Integer> graph = new DGraph<Integer>(5);
		graph.addVertex(0);
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);

		System.out.println(graph);

		graph.printAdjMat();

		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(1, 0);
		graph.addEdge(2, 1);
		graph.addEdge(3, 4);

		graph.printAdjMat();

		graph.printScc();

		boolean isSc = graph.isSCC();

		System.out.println(isSc);
	}
}