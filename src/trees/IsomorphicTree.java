/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class IsomorphicTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeList<Integer> list = new TreeList<Integer>();
		for (int i = 0; i < 20; i++)
			list.add((int) (Math.random() * 100));

		TreeList<Integer> list2 = new TreeList<Integer>();
		for (int i = 0; i < 20; i++)
			list2.add((int) (Math.random() * 100));
		System.out.println();

		list.levelOrderTraversal();

		boolean b = list.isIsomorphic(list.root(), list.root());

		System.out.println(b);
	}
}