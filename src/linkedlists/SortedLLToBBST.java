/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class SortedLLToBBST {
	public static void main(String[] args) {
		DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<Integer>();
		for (int i = 0; i < 20; i++)
			doubleLinkedList.add(i);

		System.out.println(doubleLinkedList);

		doubleLinkedList.toTree();
		System.out.println(doubleLinkedList);

		SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<Integer>();
		for (int i = 0; i < 15; i++)
			singleLinkedList.add(i);

		System.out.println(singleLinkedList);
		singleLinkedList.toTree();
		System.out.println(singleLinkedList);
	}
}