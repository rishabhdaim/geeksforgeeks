/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class Sort012 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SingleLinkedList<Integer> linkedList = new SingleLinkedList<Integer>();
		linkedList.add(1);
		linkedList.add(1);
		linkedList.add(1);
		linkedList.add(1);
		linkedList.add(1);
		linkedList.add(1);
		linkedList.add(1);
		linkedList.add(1);
		linkedList.add(1);
		linkedList.add(1);
		linkedList.add(1);
		linkedList.add(1);
		linkedList.add(0);
		linkedList.add(0);
		linkedList.add(0);
		linkedList.add(0);
		linkedList.add(0);
		linkedList.add(0);
		linkedList.add(0);
		linkedList.add(0);
		linkedList.add(0);
		linkedList.add(0);
		linkedList.add(0);
		linkedList.add(0);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);
		linkedList.add(2);

		System.out.println(linkedList);

		int[] count = linkedList.sort012();
		SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
		for (; count[0] > 0; count[0]--)
			list.add(0);
		for (; count[1] > 0; count[1]--)
			list.add(1);
		for (; count[2] > 0; count[2]--)
			list.add(2);

		System.out.println(list);

	}
}