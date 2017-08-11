/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class MergeTrees {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeList<Integer> list = new TreeList<Integer>();
		for (int i = 0; i < 5; i++)
			list.add((int) (Math.random() * 100));

		TreeList<Integer> list1 = new TreeList<Integer>();
		for (int i = 0; i < 5; i++)
			list1.add((int) (Math.random() * 100));
		System.out.println();
		list.inOrderTraversal(list.root());
		System.out.println();
		list1.inOrderTraversal(list1.root());

		System.out.println();

		list.mergeTree(list.root(), list1.root());
		System.out.println();
		list = new TreeList<Integer>();
		for (int i = 0; i < 20; i++)
			list.add((int) (Math.random() * 100));
		System.out.println();
		list.inOrderIterative(list.root());
		list.levelOrderTraversal();
		System.out.println(list);

		list.addNextSucc(list.root());
		list.nextRight(list.root());
		System.out.println(list);
	}
}