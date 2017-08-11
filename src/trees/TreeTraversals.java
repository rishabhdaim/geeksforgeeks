/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class TreeTraversals {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeList<Integer> list = new TreeList<Integer>();
		for (int i = 0; i < 6; i++)
			list.add((int) (Math.random() * 1000));

		System.out.println();
		list.levelOrderTraversal();
		System.out.println("In Order");
		list.inOrderTraversal(list.root());
		System.out.println();
		list.inOrderIterative(list.root());
		list.inOrderTraversalWithStack(list.root());
		list.inOrderMorris(list.root());
		System.out.println("Pre Order");
		list.preOrderTraversal(list.root());
		System.out.println();
		list.preOrderIterative(list.root());
		list.preOrderMorris(list.root());
		System.out.println("Post Order");
		list.postOrderTraversal(list.root());
		System.out.println();
		list.postOrderIterative(list.root());
		//list.postOrderIterativeSingleStack(list.root());
		System.out.println("Size of tree is : " + list.size(list.root()));
		Integer[] arr = { 4, 2, 5, 1, 3 };

		list.printInOrderArray(arr, 0, arr.length - 1);
		System.out.println();
	}
}