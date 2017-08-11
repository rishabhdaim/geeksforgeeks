/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author apple
 * 
 */
public class MergeSort {

	private static int count;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = { 1, 89, 4, 51, 7, 2, 5, 667, 34, 132, 3, 154, 567, 67, 64,
				54, 534, 342, 32, 312, 321, 435, 345, 46, };
		System.out.println(Arrays.toString(a));
		int[] temp = new int[a.length];
		mergeSort(a, temp, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
		System.out.println(count);
		
		count = 0;
		int[] ls = new int[100000000];
		for (int i = ls.length - 1; i > 0; i--)
			ls[i] = i;
		temp = new int[ls.length];
		long l = System.currentTimeMillis();
		mergeSort(ls, temp, 0, ls.length - 1);
		System.out.println(System.currentTimeMillis() - l);
		System.out.println(count);
		
		count = 0;
		ls = new int[100000000];
		for (int i = ls.length - 1; i > 0; i--)
			ls[i] = (int)(Math.random() * 10000000);
		temp = new int[ls.length];
		l = System.currentTimeMillis();
		mergeSort(ls, temp, 0, ls.length - 1);
		System.out.println(System.currentTimeMillis() - l);
		System.out.println(count);
	}

	private static void mergeSort(int[] src, int[] dest, int i, int j) {
		if ((j - i + 1) <= 7) {
			insertionSort(src, i, j);
			return;
		} else {
			int m = (i + j) >>> 1; // divide be 2
			mergeSort(src, dest, i, m);
			mergeSort(src, dest, m + 1, j);
			merge(src, dest, i, m + 1, j);
		}
	}

	private static void merge(int[] src, int[] dest, int destLow, int mid, int destHigh) {
		int j = 0;
		int lowerBound = destLow;
		int midEnd = mid - 1;
		int noOfElems = destHigh - destLow + 1;

		if (src[midEnd] <= src[mid])
			return; // to coping is required..

		while (destLow <= midEnd && mid <= destHigh)
			if (src[destLow] < src[mid])
				dest[j++] = src[destLow++]; // start coping from zeroth index in
											// destination array..
			else
				dest[j++] = src[mid++];
		while (destLow <= midEnd)
			dest[j++] = src[destLow++];
		while (mid <= destHigh)
			dest[j++] = src[mid++];

		count += j;

		for (j = 0; j < noOfElems; j++)
			src[lowerBound + j] = dest[j]; // get back elements from dest to src,
										// starting from destLow..
		count += j;
	}

	private static void insertionSort(int[] is, int left, int right) {
		int i, j, temp;

		for (i = left + 1; i <= right; i++) {
			count++;
			temp = is[i];
			for (j = i; j > left && is[j - 1] >= is[j]; j--)
				swap(is, j, j - 1);
			if (j != i) {
				count++;
				is[j] = temp;
			}
		}
	}

	private static void swap(int[] is, int leftPtr, int rightPtr) {
		count += 3;
		int temp = is[rightPtr];
		is[rightPtr] = is[leftPtr];
		is[leftPtr] = temp;
	}
}