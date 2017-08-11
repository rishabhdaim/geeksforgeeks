/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author apple
 * 
 */
public class OddEvenSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] ls = { 21, 21, 34, 2, 2, 453, 67, 1, 8, 5, 8, 8, 5, 56, 1, 2, 2,
				3, 3, 3, 3, 333, 4, 565, 76, 77, 87, 8, 65, 54, 45, 343, 343,
				343 };
		System.out.println(Arrays.toString(ls));
		oddEvenSort(ls);
		System.out.println(Arrays.toString(ls));
	}

	private static void oddEvenSort(int[] ls) {
		int i = ls.length >> 1;
		for (int j = 0; j < i; j++) {
			for (int k = 1; k <= i; k++) {
				// sorting all odd elements..
				int l = k * 2 - 1;
				// to handle scenario when no. of elements are even
				if (l < ls.length - 1)
					if (ls[l] > ls[l + 1])
						swap(l, l + 1, ls);
			}
			for (int k = 0; k < i; k++) {
				// sorting all even elements..
				int l = k * 2;
				if (ls[l] > ls[l + 1])
					swap(l, l + 1, ls);
			}
		}
	}

	private static void swap(int i, int min, int[] ls) {
		int temp = ls[i];
		ls[i] = ls[min];
		ls[min] = temp;
	}
}