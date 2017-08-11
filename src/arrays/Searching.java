/**
 * 
 */
package arrays;

/**
 * @author aa49442
 * 
 */
public class Searching {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] i = new Integer[] { 45, 56, 56, 67, 1, 1, 23, 23, 25, 25, 25,
				34, 37, 40, 44 };
		Array<Integer> arr = new Array<Integer>(i);

		System.out.println(arr);

		int min = arr.findMinInRotatedArray(0, i.length - 1);
		System.out.println(min);

		min = arr.inputWithPositiveOutput();
		System.out.println(min);

		i = new Integer[7];
		for (int j = 0; j < i.length; j++)
			i[j] = (int) (Math.random() * 3);

		arr = new Array<Integer>(i);

		System.out.println(arr);

		arr.moreThanK(31);

		int j = arr.peakElement(0, i.length - 1);
		System.out.println("peak element at : " + j);

		arr.printCombination(4);

		i = new Integer[10];
		for (int j1 = 0; j1 < i.length; j1++)
			i[j1] = (int) (Math.random() * 2);

		arr = new Array<Integer>(i);

		System.out.println(arr);

		j = arr.maxDiff();
		System.out.println(j);

		arr.elementsWithMaxCount(10);

		System.out.println();

		arr.majorElement(i.length);
	}
}