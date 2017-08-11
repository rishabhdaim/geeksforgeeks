/**
 * 
 */
package graphs;

/**
 * @author aa49442
 * 
 */
public class FloydWarshallAlgo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WGraph<Integer> wGraph = new WGraph<Integer>(4);
		
		wGraph.printAdjMat();
		
		for (int i = 0; i < 4; i++)
			wGraph.addVertex(i);
		
		wGraph.addEdge(0, 1, 5);
		wGraph.addEdge(0, 3, 10);
		wGraph.addEdge(1, 2, 3);
		wGraph.addEdge(2, 3, 1);

		wGraph.printAdjMat();

		wGraph.floydWarshallAlgo();
	}
}