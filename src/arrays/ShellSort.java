/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author apple
 * 
 */
public class ShellSort {

	private static long count;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] is = { 19, 28, 37, 4675, 54, 6234, 71, 748, 85, 87, 98, 112, 243,
				354, 656, 17, 36, 15467, 423, 425, 465, 56, 515, 314, 214, 224,
				1243, 154, 265, 1276, 1476, 715, 643, 534, 543, 573, 62, 726,
				164, 5634, 3446, 456, 4364, 34564, 346, 3475, 747, 5746856, 8,
				585, 74, 756, 746, 856, 84568, 4658, 568, 568, 568, 3458,
				34568, 34258, 58, 64757, 6467, 45, 7646, 347, 437, 537, 56745,
				8578, 7, 45756, 1 };
		System.out.println(is.length);
		System.out.println(Arrays.toString(is));
		selectionSort(is);
		System.out.println(Arrays.toString(is));
		System.out.println("selection sort : " + count);
		count = 0;
		is = new int[10];
		for (int i = 0; i < is.length; i++)
			is[i] = (int) (Math.random() * 10000);
		shellSort(is);
		System.out.println(Arrays.toString(is));
		count = 0;
		is = new int[100];
		for (int i = 0; i < is.length; i++)
			is[i] = (int) (Math.random() * 1000);
		insertionSort(is);
		System.out.println(Arrays.toString(is));
		System.out.println("insertion sort : " + count);
		count = 0;
		is = new int[1000000];
		int[] temp = new int[is.length];
		for (int i = 0; i < is.length; i++)
			is[i] = (int) (Math.random() * 10000);
		mergeSort(is, temp, 0, is.length - 1);
		System.out.println("merge sort : " + count);
		count = 0;
		is = new int[10000];
		for (int i = 0; i < is.length; i++)
			is[i] = (int) (Math.random() * 100000);
		selectionSort(is);
		System.out.println("selection sort : " + count);
	}

	private static void mergeSort(int[] is, int[] temp, int lower, int upper) {
		if (lower >= upper)
			return;
		int mid = (lower + upper) >> 1; // divide by two..
		mergeSort(is, temp, lower, mid);
		mergeSort(is, temp, mid + 1, upper);
		merge(is, temp, lower, mid + 1, upper);
	}

	private static void merge(final int[] is, final int[] temp, int lower, int i, int upper) {
		int j = 0; // to keep track of supporting arrays elements..
		int lowerBound = lower; // to fill src array from dest(supporting)..
		int mid = i - 1; // to mark mid of arrays to be merged..
		int noOfElems = upper - lower + 1;
		while (lower <= mid && i <= upper) {
			if (is[lower] < is[i])
				temp[j++] = is[lower++];
			else
				temp[j++] = is[i++];
			count++;
		}
		while (lower <= mid) {
			temp[j++] = is[lower++];
			count++;
		}
		while (i <= upper) {
			temp[j++] = is[i++];
			count++;
		}

		for (j = 0; j < noOfElems; j++) {
			is[lowerBound + j] = temp[j];
			count++;
		}
	}

	private static void shellSort(int[] is) {
		int i, j; // for outer & inner for loops..
		int temp; // to store current element..
		int h = 1; // interval width..
		while (h <= is.length / 3)
			h = h * 3 + 1;
		while (h > 0) {
			for (i = h; i < is.length; i++) {
				count++;
				temp = is[i]; // store current element..
				for (j = i; j > h - 1 && is[j - h] > temp; j -= h)
					swap(is, j, h);
				if (j != i) {
					is[j] = temp; // swap only if current element was moved..
					count++;
				}
			}
			h = (h - 1) / 3; // decrement h..
		}
	}

	private static void insertionSort(int[] is) {
		int i, j, temp;
		for (i = 1; i < is.length; i++) {
			count++;
			temp = is[i];
			for (j = i; j > 0 && is[j - 1] > temp; j--)
				swap(is, j, 1);
			if (j != i) {
				is[j] = temp;
				count++;
			}
		}
	}

	private static void swap(int[] is, int j, int h) {
		count += 3;
		int temp = is[j];
		is[j] = is[j - h];
		is[j - h] = temp;
	}

	private static void selectionSort(int[] is) {
		// index of smallest element..
		int smallest;
		for (int i = 0; i < is.length; i++) {
			count++;
			smallest = i;
			for (int j = i + 1; j < is.length; j++) {
				if (is[j] < is[smallest])
					smallest = j;
				if (smallest != i) {
					int k = is[smallest];
					is[smallest] = is[i];
					is[i] = k;
				}
			}
		}
	}
}