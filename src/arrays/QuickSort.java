/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author apple
 * 
 */
public class QuickSort {

	private static long count;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final int[] asd = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] is = new int[100000000];
		for (int i = is.length-1; i > 0; i--)
			is[i] = i;
		long l = System.currentTimeMillis();
		// System.out.println(Arrays.toString(is));
		quickSort(is, 0, is.length - 1);
		System.out.println(System.currentTimeMillis() - l);
		// System.out.println(Arrays.toString(is));
		System.out.println(count);
		count = 0;
		
		is = new int[100000000];
		for (int i = is.length-1; i > 0; i--)
			is[i] = (int)(Math.random() * 10000000);
		l = System.currentTimeMillis();
		// System.out.println(Arrays.toString(is));
		quickSort(is, 0, is.length - 1);
		System.out.println(System.currentTimeMillis() - l);
		// System.out.println(Arrays.toString(is));
		System.out.println(count);
		count = 0;
		
		System.out.println(Arrays.toString(asd));
		quickSort(asd, 0, asd.length - 1);
		System.out.println(Arrays.toString(asd));
		System.out.println(count);
	}

	private static void quickSort(int[] is, int left, int right) {
		// to handle case of already sorted data in inverse proportion..
		int size = right - left + 1;
		if (size <= 3)
			// manualSort(is, left, right, size);
			insertionSort(is, left, right);
		else {
			count++;
			// int pivot = is[right];
			final int pivot = median(is, left, right);
			// System.out.println(pivot);
			final int partition = doPartition(is, left, right, pivot);
			quickSort(is, left, partition - 1);
			quickSort(is, partition + 1, right);
		}
	}

	private static int median(int[] is, int left, int right) {
		
		final int center = (left + right) >>> 1;
		if (is[left] > is[center])
			swap(is, left, center);
		if (is[left] > is[right])
			swap(is, left, right);
		if (is[center] > is[right])
			swap(is, center, right);
		swap(is, center, right - 1); // put pivot on right-1 position..
		return is[right - 1];
	}

	static void manualSort(int[] is, int left, int right, int size) {
		if (size <= 1)
			return;
		if (size == 2) {
			if (is[left] > is[right])
				swap(is, left, right);
			return;
		} else {
			// size is 3, sort them..
			if (is[left] > is[right - 1])
				swap(is, left, right - 1); // left, center
			if (is[left] > is[right])
				swap(is, left, right); // left, right
			if (is[right - 1] > is[right])
				swap(is, right - 1, right);// center, right
		}
	}

	private static int doPartition(int[] is, int left, int right, int pivot) {
		int leftPtr = left; // left is already smaller than pivot
		int rightPtr = right - 1; // right is already greater than pivot and
		count += 2;							// pivot is at right -1..
		while (true) {
			while (is[++leftPtr] < pivot)
				count++; // find bigger value
			while (is[--rightPtr] > pivot)
				// no need to check rightPtr against 0 as we know leftest
				// element is less than right element and same with upper while
				// condition..
			count++	; // find smaller value
			if (leftPtr >= rightPtr)
				break;
			else
				swap(is, leftPtr, rightPtr);
		}
		swap(is, leftPtr, right - 1); // restore pivot to its position..
		return leftPtr;
	}

	private static void swap(int[] is, int leftPtr, int rightPtr) {
		count += 3;
		int temp = is[rightPtr];
		is[rightPtr] = is[leftPtr];
		is[leftPtr] = temp;
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
}