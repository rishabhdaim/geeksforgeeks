/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class AddGretaerNodes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeList<Integer> list = new TreeList<Integer>();
		for (int i = 0; i < 5; i++)
			list.add((int) (Math.random() * 100));
		System.out.println();
		list.inOrderTraversal(list.root());
		System.out.println();
		list.revInOrderTraversal(list.root());
		list.inOrderTraversal(list.root());

		System.out.println();
		boolean b = list.isBST(list.root());
		System.out.println(b);
		list = new TreeList<Integer>();
		for (int i = 0; i < 20; i++)
			list.add((int) (Math.random() * 100));
		list.add(234);
		list.add(12345);
		list.add(1000);
		list.add(2345);
		System.out.println();
		list.levelOrderTraversal();
		b = list.isSumEquals(list.root(), 12579);
		System.out.println(b);

		b = list.isTripletEquals(list.root(), 12579);
		System.out.println(b);

		list.nodeOnLargestSum(list.root());

		System.out.println();
		int i = list.largestBST(list.root());

		System.out.println(i);

		list.toSumTree(list.root());

		list.levelOrderTraversal();

		b = list.isSumTree(list.root());

		System.out.println(b);

		b = list.isSumTreeTricky(list.root());

		System.out.println(b);
		list = new TreeList<Integer>();
		for (i = 0; i < 20; i++)
			list.add((int) (Math.random() * 100));
		System.out.println();
		list.printValueInRange(24, 68, list.root());
		System.out.println();
		list.levelOrderTraversal();
		System.out.println();
		list.levelOfKey(list.root(), 34);
		System.out.println();
		Integer i1 = list.smallestKthElement(list.root(), 12);
		System.out.println(i1);
		list.inOrderTraversal(list.root());
	}
}