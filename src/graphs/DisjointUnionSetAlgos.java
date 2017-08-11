/**
 * 
 */
package graphs;

/**
 * Disjoint Set Algos, they use Union-Find-Rank algos
 * 
 * @author rishabh.daim
 *
 */
public class DisjointUnionSetAlgos {
	
	public static void main(String[] args) {
		
		GraphList gList = new GraphList(5, false);
		
		for (int i = 0; i < 5; i++) 
			gList.addVertex(i);
		
		gList.addEdge(0, 2);
		gList.addEdge(4, 2);
		gList.addEdge(3, 1);
		
		boolean b = gList.areKnown(4, 0);
		System.out.println(b);
		
		b = gList.areKnown(1, 0);
		System.out.println(b);
	}
	
}
