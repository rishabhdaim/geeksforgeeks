/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author aa49442
 * 
 */
public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] is = new int[10];
		for (int j = 0; j < is.length; j++)
			is[j] = (int) (Math.random() * 10);
		System.out.println(Arrays.toString(is));
		quickSort(is, 0, is.length - 1);
		System.out.println(Arrays.toString(is));
		System.out.println("------------------------");
		is = new int[1000000];
		for (int i = is.length-1; i > 0; i--)
			is[i] = i;
		long l = System.currentTimeMillis();
		// System.out.println(Arrays.toString(is));
		quickSort(is, 0, is.length - 1);
		System.out.println(System.currentTimeMillis() - l);
	}

	private static void quickSort(int[] is, int l, int r) {
		if (l >= r)
			return;
		int pivot = is[r];
		int p = partition(is, pivot, l, r);
		quickSort(is, l, p);
		quickSort(is, p + 1, r);
	}

	private static int partition(int[] is, int pivot, int l, int r) {
		int lower = l;
		int upper = r - 1;
		while (true) {
			while (lower < r && is[lower] <= pivot)
				lower++;
			while (upper >= 0 && is[upper] > pivot)
				upper--;
			if (lower < upper)
				swap(is, upper, lower);
			else
				break;
		}
		// swap pivot with last upper
		swap(is, upper + 1, r);
		return upper;
	}

	private static void swap(int[] is, int upper, int lower) {
		int temp = is[upper];
		is[upper] = is[lower];
		is[lower] = temp;
	}
}