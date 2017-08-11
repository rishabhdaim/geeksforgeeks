/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class ChildTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeList<Integer> list = new TreeList<Integer>();
		for (int i = 0; i < 15; i++)
			list.add((int) (Math.random() * 100));
		list.add(24);
		list.add(46);
		list.add(56);
		System.out.println();

		list.levelOrderTraversal();
		Integer i = list.lca(list.root(), 24, 46);
		System.out.println("lca is : " + i);
		i = list.lcaIterative(list.root(), 56, 46);
		System.out.println("lca is : " + i);
		boolean b = list.isBSTUsingInOrder();
		System.out.println(b);
		list.childSumTree(list.root());

		b = list.isBSTUsingInOrder();

		System.out.println(b);

		System.out.println();
		list.levelOrderTraversal();
	}
}