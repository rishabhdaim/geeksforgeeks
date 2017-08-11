/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class TestCLL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<Integer>();

		for (int i = 0; i < 10; i++)
			circularLinkedList.add((int) (Math.random() * 200));

		System.out.println(circularLinkedList);
	}

}
