/**
 * 
 */
package graphs;

/**
 * @author aa49442
 * 
 */
public class HamiltonianPath {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UnDGraph<Integer> dGraph = new UnDGraph<Integer>(5);

		for (int i = 0; i < 5; i++)
			dGraph.addVertex(i);

		dGraph.addEdge(0, 1);
		dGraph.addEdge(0, 3);
		dGraph.addEdge(1, 2);
		dGraph.addEdge(1, 3);
		dGraph.addEdge(1, 4);
		dGraph.addEdge(2, 4);
		dGraph.addEdge(3, 4);

		dGraph.hamiltonianPath();
	}
}