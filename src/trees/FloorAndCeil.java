/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class FloorAndCeil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeList<Integer> list = new TreeList<Integer>();
		for (int i = 0; i < 20; i++)
			list.add((int) (Math.random() * 100));
		System.out.println();

		list.inOrderTraversal(list.root());
		System.out.println();
		int i = list.ceil(list.root(), 43);
		System.out.println(i);

		i = list.floor(list.root(), 43);

		System.out.println(i);

		boolean b = list.isBST(list.root());
		System.out.println(b);
	}
}