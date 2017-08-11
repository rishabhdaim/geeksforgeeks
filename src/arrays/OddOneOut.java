/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author aa49442
 * 
 */
public class OddOneOut {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = { 3, 2, 4, 5, 9, 7, 8, 6, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11 };

		Array<Integer> array = new Array<Integer>(arr.length);

		array.oddOccurances(arr);

		arr = new int[] { 1, -2, 3, -4, 5, -6, 7, -8, -9, };

		int i = array.firstMissingPositiveNo(arr);
		System.out.println(i);

		arr = new int[50];
		int n = arr.length;
		for (int i1 = 0; i1 < n; i1++)
			arr[i1] = (int) (Math.random() * 49);
		System.out.println(Arrays.toString(arr));
		array.printDuplicates(arr);
	}
}