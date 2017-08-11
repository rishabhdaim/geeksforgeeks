/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class RevAltNodesAndAppendEnd {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SingleLinkedList<String> linkedList = new SingleLinkedList<String>();
		linkedList.add("1");
		linkedList.add("2");
		linkedList.add("3");
		linkedList.add("4");
		linkedList.add("5");
		linkedList.add("6");
		linkedList.add("7");
		linkedList.add("8");
		linkedList.add("9");

		System.out.println(linkedList);

		linkedList.rearrange();

		System.out.println(linkedList);

		linkedList.swapList();

		System.out.println(linkedList);

		SingleLinkedList<String> list = new SingleLinkedList<String>();
		list.add("q");
		list.add("w");
		list.add("e");
		list.add("r");
		list.add("t");
		list.add("y");
		list.add("u");
		list.add("i");
		list.add("o");
		list.add("p");
		list.add("l");
		list.add("k");

		System.out.println(list);
		linkedList.merge(list);

		System.out.println(linkedList);

		linkedList.deleteNAfterM(5, 3);

		System.out.println(linkedList);
		// linkedList.quickSort();

		System.out.println(linkedList);

		linkedList.add("10");
		System.out.println(linkedList);
		linkedList.swapList();

		System.out.println(linkedList);

		linkedList.swapKthElements(4);

		System.out.println(linkedList);

		System.out.println(linkedList.pop());
		System.out.println(linkedList);
		linkedList.push("d");
		System.out.println(linkedList);
	}
}