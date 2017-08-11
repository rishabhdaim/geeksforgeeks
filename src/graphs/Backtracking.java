/**
 * 
 */
package graphs;


/**
 * @author Rishabh.Daim
 *
 */
public class Backtracking {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DGraph<Integer> dGraph = new DGraph<>(4);
		
		for (int i = 0; i < 4; i++) {
			dGraph.addVertex(i);
		}
		
		dGraph.addEdge(0, 0);
		dGraph.addEdge(1, 0);
		dGraph.addEdge(1, 1);
		dGraph.addEdge(1, 3);
		
		dGraph.addEdge(2, 1);
		dGraph.addEdge(3, 0);
		dGraph.addEdge(3, 1);
		dGraph.addEdge(3, 2);
		dGraph.addEdge(3, 3);
	
		dGraph.printAdjMat();
		dGraph.ratMaze();
		
		dGraph.knightPath();
		
		System.out.println("Min Steps : " + dGraph.knightSteps(0,2));
		System.out.println("Min Steps : " + dGraph.knightSteps(3,1));
		System.out.println("Min Steps : " + dGraph.knightSteps(1,6));
		System.out.println("Min Steps : " + dGraph.knightSteps(0,4));
		System.out.println("Min Steps : " + dGraph.knightSteps(0,2));
	}

}
