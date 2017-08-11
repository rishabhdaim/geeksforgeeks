/**
 * 
 */
package trees;

import linkedlists.SingleLinkedList;

/**
 * @author aa49442
 * 
 */
public class TreeToList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TreeList<Integer> list = new TreeList<>();
		for (int i = 0; i < 20; i++)
			list.add((int) (Math.random() * 100));
		
		System.out.println();
		System.out.println(list);

		list.treeToCLL();
		
		list = new TreeList<Integer>();
		for (int i = 0; i < 10; i++)
			list.add((int) (Math.random() * 100));
		System.out.println();
		list.inOrderTraversal(list.root());

		System.out.println();

		list.toDll();
		
		System.out.println();
		System.out.println("+++++++++++++++++++++++++++++++++");
		list = new TreeList<Integer>();
		for (int i = 0; i < 10; i++)
			list.add((int) (Math.random() * 10));
		int sum = list.sumOfPaths(list.root());

		System.out.println(sum);

		SingleLinkedList<Integer> linkedList = new SingleLinkedList<Integer>();
		for (int i = 0; i < 20; i++)
			linkedList.add((int) (Math.random() * 100));

		System.out.println(linkedList);

		list = new TreeList<Integer>();

		list.setRoot(list.convertListToTree(linkedList.getFirst(), list.root()));

		list.inOrderTraversal(list.root());
		
		System.out.println();
		System.out.println("Leaf Node count is : " + list.countLeafNodes(list.root()));
	}
}