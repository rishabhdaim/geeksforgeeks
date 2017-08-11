/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class RemoveNodes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeList<Integer> list = new TreeList<Integer>();
		for (int i = 0; i < 20; i++)
			list.add((int) (Math.random() * 1000));

		System.out.println();

		list.levelOrderTraversal();
		System.out.println();
		list.levelOrderTraversalReverse();
		System.out.println();
		list.levelOrderTraversalReverseWithQueue(list.root());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		list.inOrderTraversal(list.root());
		System.out.println();
		list.removeOutsideRange(list.root(), 200, 800);

		list.inOrderTraversal(list.root());
	}
}