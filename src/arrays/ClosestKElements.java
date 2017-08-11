/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author aa49442
 * 
 */
public class ClosestKElements {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] i = new Integer[20];
		for (int j = 0; j < i.length; j++)
			i[j] = (int) (Math.random() * 100);

		System.out.println(Arrays.toString(i));

		Array<Integer> array = new Array<Integer>(i);
		System.out.println(array);

		array.quickSort();

		System.out.println(array);

		array.printKClosestElements(9, 46);
	}
}