/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class ConstructTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer pre[] = { 376, 38, 129, 42, 141, 218, 836, 640, 516, 418, 623,
				542, 754, 711, 716, 811, 788, 806, 944, 873 };
		// 38 42 129 141 218 376 418 516 542 623 640 711 716 754 788 806 811 836
		// 873 944
		TreeList<Integer> list = new TreeList<Integer>();
		list.setRoot(list.convertTreeFromPreOrder(pre));

		list.inOrderTraversal(list.root());

		list = new TreeList<Integer>();
		System.out.println();
		list.setRoot(list.convertTreeFromPreOrderWithStack(pre));

		list.inOrderTraversal(list.root());
		System.out.println();
		list.postOrderTraversal(list.root());

		Integer post[] = { 42, 218, 141, 129, 38, 418, 542, 623, 516, 716, 711,
				806, 788, 811, 754, 640, 873, 944, 836, 376 };
		list = new TreeList<Integer>();
		System.out.println();

		list.setRoot(list.constructTreeFromPrePostOrder(pre, post));

		list.inOrderTraversal(list.root());

		list = new TreeList<Integer>();
		System.out.println();
		Integer in[] = { 42, 129, 218, 141, 836, 38, 640, 376, 418, 516, 542,
				623, 716, 711, 811, 754, 806, 788, 873, 944 };
		list.setRoot(list.constructSpecialTreeFromInOrder(in, 0, in.length - 1));
		System.out.println();
		list.inOrderTraversal(list.root());

		list = new TreeList<Integer>();
		System.out.println();

		Integer[] preOrder = { 10, 30, 20, 5, 15 };
		Character[] preLN = { 'N', 'N', 'L', 'L', 'L' };

		list.setRoot(list.constructSpecialTreeFromPrePreLNOrder(preOrder,
				preLN, preLN.length));
		list.inOrderTraversal(list.root());
	}
}