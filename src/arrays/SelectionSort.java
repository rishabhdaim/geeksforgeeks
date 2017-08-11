/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author apple
 * 
 */
public class SelectionSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] ls = { 21, 34, 2, 45, 67, 8, 56, 5, 56, 1, 1, 333, 4, 54, 565,
				76, 77, 87, 8, 76, 65, 54, 45, 343, };
		System.out.println(Arrays.toString(ls));
		selectionSort(ls);
		System.out.println(Arrays.toString(ls));
	}

	private static void selectionSort(int[] ls) {
		// index of minimum element..
		int min;
		for (int i = 0, l = ls.length; i < l; i++) {
			// let leftMost element be min element..
			min = i;
			for (int j = i + 1; j < l; j++)
				if (ls[j] < ls[min])
					min = j;
			if (min != i)
				swap(i, min, ls);
		}
	}

	private static void swap(int i, int min, int[] ls) {
		int temp = ls[i];
		ls[i] = ls[min];
		ls[min] = temp;
	}
}