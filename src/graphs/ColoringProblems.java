/**
 * 
 */
package graphs;

/**
 * @author Rishabh.Daim
 *
 */
public class ColoringProblems {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		UnDGraph<Integer> unDGraph = new UnDGraph<>(4);
		
		for (int i = 0; i < 4; i++) {
			unDGraph.addVertex(i);
		}
		
		unDGraph.addEdge(0, 1);
		unDGraph.addEdge(0, 2);
		unDGraph.addEdge(0, 3);
		unDGraph.addEdge(1, 2);
		unDGraph.addEdge(2, 3);
		
		unDGraph.printAdjMat();
		
		unDGraph.graphColoring(3);
		
		System.out.println("Is bipartite : " + unDGraph.isBipartite());
		
		unDGraph = new UnDGraph<>(4);
		for (int i = 0; i < 4; i++) {
			unDGraph.addVertex(i);
		}
		
		unDGraph.addEdge(0, 1);
		unDGraph.addEdge(0, 3);
		unDGraph.addEdge(1, 2);
		unDGraph.addEdge(2, 3);
		
		unDGraph.printAdjMat();
		
		System.out.println("Is bipartite : " + unDGraph.isBipartite());
		
	}

}
