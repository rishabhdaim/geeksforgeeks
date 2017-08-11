/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class VerticalTraversal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeList<Integer> list = new TreeList<Integer>();
		for (int i = 0; i < 20; i++)
			list.add((int) (Math.random() * 100));

		System.out.println(list);
		list.verticalOrderTraversal();
		System.out.println();

		list.rightViewTraversal();
		System.out.println();
		list.leftViewTraversal();

		int[] inOrder = { 4, 8, 10, 12, 14, 20, 22 };
		int[] levelOrder = { 20, 8, 22, 4, 12, 10, 14 };

		list.buildTreeFromInLevelOrder(inOrder, levelOrder);
		System.out.println();
		System.out.println("******************************");

		list.printAllNodesAtKDistance(2);
		System.out.println();
		list.add(34);
		System.out.println("adding 34..");
		list.printAllNodesAtKDistance(3);

		System.out.println("------------------------");

		list.verticalOrderTraversalWithOutHM();

		System.out.println("-----------------------------");

		list.kDistanceFromLeafNodes(2);
		System.out.println();
		list.add(123);
		list.add(13);

		System.out.println("++++++++++++++++++++++++++++++++++++");
		int d = list.distanceBetweenNodes(123, 13);
		System.out.println(d);

		System.out.println();
		list.levelOrderTraversal();
		System.out.println("********************************************");
		list.printWithOutSiblings(list.root());
		System.out.println();
		
		list.levelOrderTraversalReverse();
	}
}