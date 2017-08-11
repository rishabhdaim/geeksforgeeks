/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class InsectionOfLL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SingleLinkedList<Integer> linkedList = new SingleLinkedList<Integer>();
		for (int i = 0; i < 20; i++)
			linkedList.add(i);

		SingleLinkedList<Integer> linkedList2 = new SingleLinkedList<Integer>();
		for (int i = 0; i < 20; i++)
			linkedList2.add(i);

		System.out.println(linkedList2);
		linkedList2.intersectWith(linkedList);

		System.out.println(linkedList);
		System.out.println(linkedList2);

		System.out.println(linkedList2.intersectingNode(linkedList));

	}
}