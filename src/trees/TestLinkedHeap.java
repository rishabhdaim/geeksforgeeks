/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class TestLinkedHeap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedBinaryTree<Integer> binaryTree = new LinkedBinaryTree<Integer>();

		for (int i = 0; i < 100; i++)
			binaryTree.insert((int) (Math.random() * 10000));

		System.out.println();

		binaryTree.levelOrderTraversal();
	}
}