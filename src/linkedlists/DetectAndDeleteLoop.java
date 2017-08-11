/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class DetectAndDeleteLoop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SingleLinkedList<Integer> linkedList = new SingleLinkedList<Integer>();
		linkedList = linkedList.createLoopedLinkedList(new Integer[] { 1, 2, 3,
				4, 5, 6, 7, 8 });

		// System.out.println(linkedList);

		linkedList.deleteLoop();
		System.out.println(linkedList);

		linkedList = new SingleLinkedList<Integer>();
		for (int i = 0; i < 12; i++)
			linkedList.add((int) (Math.random() * 200));

		System.out.println(linkedList);

		// linkedList.deleteLesserNodes();
		// System.out.println(linkedList);

		linkedList.reverseAltKNodes(6);

		System.out.println(linkedList);

		linkedList.reverseList(5);

		System.out.println(linkedList);
	}
}