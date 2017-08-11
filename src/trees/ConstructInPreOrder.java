/**
 * 
 */
package trees;

import java.util.Arrays;

/**
 * @author aa49442
 * 
 */
public class ConstructInPreOrder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Integer[] in = { 20, 34, 41, 45, 199, 228, 259, 270, 343, 446, 470, 530, 546, 612, 739, 764, 781, 816, 938, 991 };
		final Integer[] pre = { 20, 199, 34, 41, 45, 546, 470, 446, 228, 343, 259, 270, 530, 938, 764, 612, 739, 816, 781, 991 };

		final TreeList<Integer> list = new TreeList<>();

		list.setRoot(list.constructTreeFromInPreOrder(in, pre));

		list.inOrderIterative(list.root());

		System.out.println(Arrays.toString(in));
		
		list.preOrderTraversal(list.root());
		
		list.levelOrderTraversal();
	}
}