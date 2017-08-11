/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class IdenticalList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SingleLinkedList<Integer> linkedList = new SingleLinkedList<Integer>();
		for (int i = 0; i < 20; i++)
			linkedList.add((int) (Math.random() * 20));

		System.out.println(linkedList);
		SingleLinkedList<Integer> linkedList2 = new SingleLinkedList<Integer>();
		for (int i = 0; i < 20; i++)
			linkedList2.add(i);

		boolean t = linkedList.areIdentical(linkedList.getFirst(),
				linkedList2.getFirst());
		System.out.println(t);

		SingleLinkedList<Integer> list = linkedList.splitAlt();

		System.out.println(linkedList);

		System.out.println(list);

		linkedList2.deleteAlt(linkedList2.getFirst());

		System.out.println(linkedList2);
		linkedList2.addFirst(234);
		linkedList2.pairWiseSwap(linkedList2.getFirst());
		System.out.println(linkedList2);
		linkedList2.setLastLink();
		System.out.println(linkedList2);
		
		linkedList2.moveLastToFirst();
		System.out.println(linkedList2);
		
		linkedList2.printReverse(linkedList2.getFirst());
		System.out.println();
	}

}
