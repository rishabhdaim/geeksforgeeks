/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class RightNode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeList<Integer> list = new TreeList<Integer>();
		for (int i = 0; i < 20; i++)
			list.add((int) (Math.random() * 100));
		System.out.println();
		System.out.println(list);
		list.add(55);

		System.out.println("-----------------------");
		list.levelOrderTraversal();
		System.out.println("+++++++++++++++++++++");
		Integer i = list.rightNode(55);
		System.out.println(i);

		i = list.leftNode(55);
		System.out.println(i);
		list.deepestLeftNode(list.root());
		list.deepestRightNode(list.root());

		// list.generateLeafDll();

		list.deleteLessSum(list.root(), 1000);
		list.levelOrderTraversal();

	}
}