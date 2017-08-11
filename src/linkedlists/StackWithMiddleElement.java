/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class StackWithMiddleElement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DoubleLinkedList<String> doubleLinkedList = new DoubleLinkedList<String>();
		doubleLinkedList.push("1");
		doubleLinkedList.push("2");
		doubleLinkedList.push("3");
		doubleLinkedList.push("4");
		doubleLinkedList.push("5");
		doubleLinkedList.push("6");
		doubleLinkedList.push("7");
		doubleLinkedList.push("8");
		doubleLinkedList.push("9");

		System.out.println(doubleLinkedList);

		doubleLinkedList.quickSort();
		System.out.println(doubleLinkedList);

		DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<Integer>();
		for (int i = 0; i < 20; i++)
			linkedList.add((int) (Math.random() * 100));

		System.out.println(linkedList);
		linkedList.quickSort();
		System.out.println(linkedList);
	}
}