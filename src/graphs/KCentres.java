/**
 * 
 */
package graphs;

/**
 * @author Rishabh.Daim
 *
 */
public class KCentres {

	public static void main(String[] args) {
		
		WUnDGraph<Integer> wUnDGraph = new WUnDGraph<>(4);
		
		for (int i = 0; i < 4; i++) {
			wUnDGraph.addVertex(i);
		}
		
		wUnDGraph.addEdge(0, 1, 10);
		wUnDGraph.addEdge(0, 2, 7);
		wUnDGraph.addEdge(0, 3, 6);
		wUnDGraph.addEdge(1, 2, 8);
		wUnDGraph.addEdge(1, 3, 5);
		wUnDGraph.addEdge(2, 3, 12);
		
		wUnDGraph.printAdjMat();
		
		wUnDGraph.kCentreProblem(3, 0);
		wUnDGraph.kCentreProblem(3, 1);
		wUnDGraph.kCentreProblem(3, 2);
		wUnDGraph.kCentreProblem(3, 3);
	}
	
}
