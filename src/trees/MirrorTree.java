/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class MirrorTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeList<Integer> list = new TreeList<Integer>();
		for (int i = 0; i < 5; i++)
			list.add((int) (Math.random() * 30));

		System.out.println();

		list.inOrderIterative(list.root());

		list.levelOrderTraversal();

		list.mirrorTree(list.root());

		list.levelOrderTraversal();
		list.inOrderIterative(list.root());

		System.out.println();

		boolean b = list.foldableTree(list.root());
		System.out.println(b);

		b = list.foldableTreeUtil(list.root());

		System.out.println(b);

		int width = list.getMaxWidth(list.root());
		System.out.println(width);

		width = list.getMaxWidthWithPreOrder(list.root());
		System.out.println(width);

		list.doubleTree(list.root());
		list.doubleTreeRight(list.root());
		list.levelOrderTraversal();

		list = new TreeList<>();
		for (int i = 0; i < 15; i++)
			list.add((int) (Math.random() * 100));

		System.out.println();
		list.printPathLength(list.root());
	}
}