/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class TestIntervalTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntervalTree<Integer> intervalTree = new IntervalTree<Integer>();
		intervalTree.add(15, 20);
		intervalTree.add(10, 20);
		intervalTree.add(5, 20);
		intervalTree.add(25, 60);
		intervalTree.add(35, 70);
		intervalTree.add(45, 80);
		intervalTree.add(20, 110);
		intervalTree.add(50, 80);
		intervalTree.add(17, 20);
		intervalTree.add(27, 60);
		intervalTree.add(135, 920);

		intervalTree.inOrderTraversal(intervalTree.root());
		System.out.println();
		System.out.println(intervalTree.searchInterval(17, 20));
	}
}