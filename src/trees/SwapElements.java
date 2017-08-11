/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class SwapElements {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeList<Integer> list = new TreeList<Integer>();
		for (int i = 0; i < 20; i++)
			list.add((int) (Math.random() * 100));

		System.out.println();
		list.inOrderTraversal(list.root());

		list.swapNodes();

		System.out.println();
		list.inOrderTraversal(list.root());

		list.undoSwap();

		list.inOrderTraversal(list.root());

		System.out.println();

		list.levelOrderTraversal();
		list.boundryTraversal();
	}
}