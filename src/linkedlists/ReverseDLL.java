/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class ReverseDLL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<Integer>();

		for (int i = 0; i < 20; i++)
			doubleLinkedList.add((int) (Math.random() * 200));

		System.out.println(doubleLinkedList);

		doubleLinkedList.reverse();

		System.out.println(doubleLinkedList);
	}
}