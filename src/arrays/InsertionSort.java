/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author apple
 * 
 */
public class InsertionSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] ls = { 21, 21, 34, 2, 2, 453, 67, 8, 5, 8, 8, 5, 56, 1, 2, 3, 3,
				3, 3, 333, 4, 565, 76, 77, 87, 8, 65, 54, 45, 343, 343, 343 };
		System.out.println(Arrays.toString(ls));
		insertionSort(ls);
		System.out.println(Arrays.toString(ls));
		System.out.println(totalNoOfDup(ls));
		ls = removeDuplicates(ls);
		System.out.println(Arrays.toString(ls));
		int[] ls2 = { 21, 21, 34, 2, 2, 453, 67, 8, 5, 8, 8, 5, 56, 1, 2, 3, 3,
				3, 3, 333, 4, 565, 76, 77, 87, 8, 65, 54, 45, 343, 343, 343 };
		insertionSort(ls2);
		System.out.println(Arrays.toString(ls2));
		replaceDupWithKey(ls2);
		System.out.println(Arrays.toString(ls2));
		insertionSort(ls2);
		System.out.println(Arrays.toString(ls2));
	}

	private static void replaceDupWithKey(int[] ls) {
		for (int i = 0, l = ls.length; i < l - 1; i++)
			if (ls[i] == ls[i + 1])
				ls[i] = -1;
	}

	private static int totalNoOfDup(int[] ls) {
		int dupCount = 0;
		for (int i = 0, l = ls.length; i < l - 1; i++) {
			if (ls[i] == ls[i + 1])
				dupCount++;
		}
		return dupCount;
	}

	private static int[] removeDuplicates(int[] ls) {
		int i = totalNoOfDup(ls);
		int[] js = new int[ls.length - i];
		for (int j = 0, l = ls.length, k = 0; j < l - 1; j++)
			if (ls[j] != ls[j + 1])
				js[k++] = ls[j];
		js[js.length - 1] = ls[ls.length - 1];
		return ls = js;
	}

	private static void insertionSort(int[] ls) {
		int temp;
		for (int i = 1, l = ls.length; i < l; i++) {
			// store leftMost unsorted element in temp variable..
			temp = ls[i];
			int j;
			for (j = i; j > 0 && ls[j - 1] >= temp; j--)
				ls[j] = ls[j - 1];
			// if ls[j] is largest element, then no need to copy the same..
			if (j != i)
				ls[j] = temp;
		}
	}
}