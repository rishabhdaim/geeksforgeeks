/**
 * 
 */
package linkedlists;

/**
 * @author aa49442
 * 
 */
public class UnionAndInterSection {

	public static void main(String[] args) {
		SingleLinkedList<Integer> list1 = new SingleLinkedList<Integer>();
		for (int i = 0; i < 10; i++)
			list1.add(i);

		System.out.println(list1);

		SingleLinkedList<Integer> list2 = new SingleLinkedList<Integer>();
		for (int i = 5; i < 15; i++)
			list2.add(i);

		System.out.println(list2);

		SingleLinkedList<Integer> union = list1.union(list1, list2);

		System.out.println(union);

		SingleLinkedList<Integer> intersection = list1.intersection(list1,
				list2);
		System.out.println(intersection);

		SingleLinkedList<Integer> unsortedList = new SingleLinkedList<Integer>();
		for (int i = 0; i < 15; i++)
			unsortedList.add((int) (Math.random() * 100));

		System.out.println(unsortedList);
		unsortedList.mergeSort();
		System.out.println(unsortedList);
	}
}