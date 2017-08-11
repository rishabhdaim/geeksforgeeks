/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class PrintPostOrder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// int in[] = { 4, 2, 5, 1, 3, 6 };
		// int pre[] = { 1, 2, 4, 5, 3, 6 };

		final TreeList<Integer> list = new TreeList<>();

		// list.printPostFromPreAndInOrder(in, in.length - 1, pre, 0);

		for (int i = 0; i < 20; i++)
			list.add((int) (Math.random() * 50));
		System.out.println();
		int diff = list.getLevelDiff(list.root());
		System.out.println("diff is : " + diff);

		list.levelOrderTraversalWithQueue(list.root());
		list.printLevelOrder(list.root());
		System.out.println("Level Order Spiral");
		list.levelOrderSpiral(list.root());
		
		System.out.println("------------------------");

		list.add(45);
		System.out.println();
		list.levelOrderTraversal();
		list.printAncestors(45);
		System.out.println();

		list.printAncestorsRecursively(list.root(), 45);
		System.out.println();
		int h = list.heightIterative(list.root());
		System.out.println("height of tree is : " + h);
	}
}