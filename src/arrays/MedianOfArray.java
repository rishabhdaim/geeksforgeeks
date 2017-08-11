/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author aa49442
 * 
 */
public class MedianOfArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] i = new Integer[20];
		for (int j = 0; j < i.length; j++)
			i[j] = (int) (Math.random() * 100);

		Integer[] i1 = new Integer[20];
		for (int j = 0; j < i1.length; j++)
			i1[j] = (int) (Math.random() * 100);

		Arrays.sort(i);
		Arrays.sort(i1);

		System.out.println(Arrays.toString(i));
		System.out.println(Arrays.toString(i1));

		Array<Integer> array = new Array<>(i.length);
		int median = array.getMedian(i, i1, i.length);
		System.out.println(median);

		median = array.getMedRec(i, i1, i.length);
		System.out.println(median);
	}
}