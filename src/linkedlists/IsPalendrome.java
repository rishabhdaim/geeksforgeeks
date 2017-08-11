/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class IsPalendrome {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SingleLinkedList<String> sll = new SingleLinkedList<>();
		String[] strings = { "1", "S", "D", "F", "D", "S", "A", "A" };
		for (String string : strings)
			sll.add(string);

		System.out.println(sll);

		boolean b = sll.isPalendromeUsingStack();
		System.out.println(b);

		b = sll.isPalendromeUsingReverse();

		System.out.println(b);

		System.out.println(sll);

		b = sll.isPalendromeUsingRecur();
		System.out.println(b);

		sll.reverseRecur();

		System.out.println(sll);
		
		sll.rotate(4);
		System.out.println(sll);
		
		sll.deleteLast("A");
		System.out.println(sll);
		
		sll.deleteLast("D");
		System.out.println(sll);
	}
}