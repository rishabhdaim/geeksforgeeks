/**
 * 
 */
package trees;

import trees.SPlayTree.Node;

/**
 * @author aa49442
 * 
 */
public class TestSPlay {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SPlayTree<Integer> sPlayTree = new SPlayTree<Integer>();

		for (int i = 0; i < 10; i++)
			sPlayTree.add((int) (Math.random() * 200));

		System.out.println();
		sPlayTree.inOrderTraversal(sPlayTree.root());

		Node<Integer> node = sPlayTree.search(sPlayTree.root(), 30);
		System.out.println();
		System.out.println(sPlayTree.root());
		System.out.println(node);
	}
}