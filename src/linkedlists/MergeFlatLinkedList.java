/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class MergeFlatLinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FlatLinkedList<Integer> flatLinkedList = new FlatLinkedList<Integer>();
		int[] is = { 7, 35, 39, 43, 45, 46, 52, 54, 70, 94 };
		for (int i = 0; i < is.length; i++)
			flatLinkedList.add(is[i]);

		System.out.println(flatLinkedList);

		flatLinkedList.addChild(12, 15);

		System.out.println(flatLinkedList);

		flatLinkedList.addChild(60, 68);
		System.out.println(flatLinkedList);

		flatLinkedList.addChild(60, 65);
		System.out.println(flatLinkedList);
		flatLinkedList.addChild(7, 10);
		flatLinkedList.addChild(7, 11);
		flatLinkedList.addChild(12, 22);
		flatLinkedList.addChild(12, 18);
		flatLinkedList.addChild(12, 32);
		flatLinkedList.addChild(70, 72);
		flatLinkedList.addChild(70, 77);
		flatLinkedList.addChild(70, 88);
		flatLinkedList.addChild(70, 90);
		flatLinkedList.addChild(70, 86);
		flatLinkedList.addChild(94, 112);

		System.out.println(flatLinkedList);

		flatLinkedList.flatten();

		System.out.println(flatLinkedList);
	}
}