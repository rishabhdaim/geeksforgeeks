/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class Liss {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeList<Integer> list = new TreeList<>();
		for (int i = 0; i < 200; i++)
			list.add((int) (Math.random() * 1000));

		System.out.println();

		list.levelOrderTraversal();

		int liss = list.liss(list.root());

		System.out.println(liss);
	}
}