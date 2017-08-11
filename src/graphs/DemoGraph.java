/**
 * 
 */
package graphs;

/**
 * @author aa49442
 * 
 */
public class DemoGraph {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final UnDGraph<Integer> graph = new UnDGraph<>(4);

		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);
		graph.addVertex(5);
		graph.addVertex(6);

		System.out.println(graph);

		graph.printAdjMat();

		graph.addEdge(3, 4);

		graph.printAdjMat();
		
		// adjacency list graph
		
		final GraphList dGraphList = new GraphList(4, false);

		dGraphList.addVertex(1);
		dGraphList.addVertex(2);
		dGraphList.addVertex(3);
		dGraphList.addVertex(4);
		dGraphList.addVertex(5);
		dGraphList.addVertex(6);

		System.out.println(dGraphList);

	}
}