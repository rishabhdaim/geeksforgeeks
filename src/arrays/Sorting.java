/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author aa49442
 * 
 */
public class Sorting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] i = new Integer[10];
		for (int j = 0; j < i.length; j++)
			i[j] = (int) (Math.random() * 100);

		Array<Integer> arr = new Array<Integer>(i);

		System.out.println(arr);

		arr.countSort();

		System.out.println(arr);

		i = new Integer[10];
		for (int j = 0; j < i.length; j++)
			i[j] = (int) (Math.random() * 100);

		arr = new Array<Integer>(i);

		System.out.println(arr);

		arr.radixSort(10);

		System.out.println(arr);

		i = new Integer[20];
		for (int j = 0; j < i.length; j++)
			i[j] = (int) (Math.random() * 100);

		arr = new Array<Integer>(i);

		System.out.println(arr);

		arr.pancakeSort(i.length - 1);

		System.out.println(arr);

		i = new Integer[] { 0, 1, 2, 2, 1, 1, 1, 1, 0, 2, 1, 0, 2, 1, 0, 2, 2,
				0, 2, 0, 2, 0, 1, 2, 1 };
		arr = new Array<Integer>(i);

		System.out.println(arr);

		//arr.sort012(i.length);
		System.out.println(arr);
		
		int[] pigeonSortArr = new int[]{8, 3, 2, 7, 4, 6, 8};
		arr = new Array<>(0);
		
		arr.pigeonSort(pigeonSortArr);
		
		System.out.println(Arrays.toString(pigeonSortArr));
		
		int[] arr01 = new int[]{1,2,1,2,0,0,0,1,2,1,0,0,2,0,2};
		arr.sort012(arr01);
	}
}