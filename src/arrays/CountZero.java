/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author aa49442
 * 
 */
public class CountZero {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] i = new Integer[23];

		for (int j = 0; j < i.length; j++)
			if (j < 2)
				i[j] = 1;
			else
				i[j] = 0;

		Array<Integer> array = new Array<Integer>(i);

		System.out.println(array);

		int c = array.countZeros(0, i.length);
		System.out.println("no of zeros are : " + (i.length - c));

		i = new Integer[23];

		for (int j = 0; j < i.length; j++)
			i[j] = (int) (Math.random() * 100);
		array = new Array<Integer>(i);

		System.out.println(array);

		i = new Integer[23];

		for (int j = 0; j < i.length; j++)
			i[j] = (int) (Math.random() * 100);

		System.out.println(Arrays.toString(i));
		c = array.countPairWithXY(i);

		System.out.println(c);

		System.out.println("------------------------------------");

		i = new Integer[20];
		for (int j = 0; j < i.length; j++)
			if (j % 2 == 0)
				i[j] = (int) (Math.random() * 100);
			else
				i[j] = 0;
		array = new Array<Integer>(i);

		System.out.println(array);

		array.pushZeroToEnd();

		System.out.println(array);

		i = new Integer[20];
		for (int j = 0; j < i.length; j++)
			i[j] = (int) (Math.random() * 100) % 2;
		array = new Array<Integer>(i);

		System.out.println(array);

		array.maxSubArrWithEqual0And1(i.length);
	}
}