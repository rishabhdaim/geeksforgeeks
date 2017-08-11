/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class AddLinkedListElements {
	public static void main(String[] args) {
		SingleLinkedList<Integer> first = new SingleLinkedList<Integer>();
		for (int i = 0; i < 13; i++)
			first.add((int) (Math.random() * 10));
		System.out.println(first);
		SingleLinkedList<Integer> second = new SingleLinkedList<Integer>();
		for (int i = 0; i < 13; i++)
			second.add((int) (Math.random() * 10));
		System.out.println(second);
		SingleLinkedList<Integer> result = first.addTwoList(first, second);
		System.out.println(result);
	}
}
