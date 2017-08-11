/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author apple
 * 
 */
public class Partition {
	private static long count;

	public static void main(String[] args) {
		int[] is = new int[100];
		for (int i = 0; i < is.length; i++)
			is[i] = (int) (Math.random() * 200);
		System.out.println(Arrays.toString(is));
		int pivot = 120;
		int i = doPartition(pivot, 0, is.length - 1, is);
		System.out.println(i);
		System.out.println(Arrays.toString(is));
		System.out.println(count);
	}

	private static int doPartition(int pivot, int left, int right, int[] is) {
		int leftPtr = left - 1; // right of first element..
		int rightPtr = right + 1; // left of pivot
		while (true) {
			while (leftPtr < right && is[++leftPtr] < pivot)
				;// find bigger element..
			while (rightPtr > left && is[--rightPtr] > pivot)
				; // find smaller element..
			if (leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr, rightPtr, is);
		}
		return leftPtr;
	}

	private static void swap(int leftPtr, int rightPtr, int[] is) {
		count += 3;
		int temp = is[rightPtr];
		is[rightPtr] = is[leftPtr];
		is[leftPtr] = temp;
	}
}
