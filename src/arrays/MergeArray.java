/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author aa49442
 * 
 */
public class MergeArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] i = new int[10];
		int[] j = new int[10];
		int[] k = new int[10];
		int[] l = new int[10];
		int[] m = new int[10];
		int[] n = new int[10];
		int[] o = new int[10];

		for (int p = 0; p < 10; p++)
			i[p] = (int) (Math.random() * 100);
		for (int p = 0; p < 10; p++)
			j[p] = (int) (Math.random() * 100);
		for (int p = 0; p < 10; p++)
			k[p] = (int) (Math.random() * 100);
		for (int p = 0; p < 10; p++)
			l[p] = (int) (Math.random() * 100);
		for (int p = 0; p < 10; p++)
			m[p] = (int) (Math.random() * 100);
		for (int p = 0; p < 10; p++)
			n[p] = (int) (Math.random() * 100);
		for (int p = 0; p < 10; p++)
			o[p] = (int) (Math.random() * 100);

		int[][] arr = new int[7][];

		Arrays.sort(i);
		Arrays.sort(j);
		Arrays.sort(k);
		Arrays.sort(l);
		Arrays.sort(m);
		Arrays.sort(n);
		Arrays.sort(o);

		System.out.println(Arrays.toString(i));
		System.out.println(Arrays.toString(j));
		System.out.println(Arrays.toString(k));
		System.out.println(Arrays.toString(l));
		System.out.println(Arrays.toString(m));
		System.out.println(Arrays.toString(n));
		System.out.println(Arrays.toString(o));

		arr[0] = i;
		arr[1] = j;
		arr[2] = k;
		arr[3] = l;
		arr[4] = m;
		arr[5] = n;
		arr[6] = o;

		Array<Integer> array = new Array<Integer>(0);
		int[] sortedArr = array.mergeKSortedArrays(arr, 10);
		System.out.println(Arrays.toString(sortedArr));

		System.out.println("------------------------");

		i = new int[] { 6, 8 };
		j = new int[] { 1, 3 };
		k = new int[] { 2, 4 };
		l = new int[] { 4, 7 };
		m = new int[] { 4, 12 };
		n = new int[] { 15, 18 };
		o = new int[] { 2, 9 };

		arr = new int[7][];

		arr[0] = i;
		arr[1] = j;
		arr[2] = k;
		arr[3] = l;
		arr[4] = m;
		arr[5] = n;
		arr[6] = o;

		array.mergeOverlappingIntervals(arr);

		System.out.println("------------------");

		i = new int[] { 4, 6 };
		j = new int[] { 6, 5 };
		k = new int[] { 7, 3 };
		l = new int[] { 4, 5 };
		m = new int[] { 11, 12 };
		n = new int[] { 15, 15 };
		o = new int[] { 12, 10 };

		arr = new int[7][];

		arr[0] = i;
		arr[1] = j;
		arr[2] = k;
		arr[3] = l;
		arr[4] = m;
		arr[5] = n;
		arr[6] = o;

		array.findPetrolPump(arr);
	}
}