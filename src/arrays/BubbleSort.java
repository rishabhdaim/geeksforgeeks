/**
 * 
 */
package arrays;


/**
 * @author apple
 * 
 */
public class BubbleSort {

	private static long count;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] ls = new int[1000];
		for (int i = 0; i < ls.length; i++)
			ls[i] = (int) (Math.random() * 1000000);
		bubbleSort(ls);
		System.out.println(count);
	}

	private static void bubbleSort(int[] ls) {
		for (int out = ls.length; out > 1; out--)
			for (int in = 0; in < out - 1; in++)
				if (ls[in] > ls[in + 1])
					swap(in, in + 1, ls);
	}

	private static void swap(int one, int two, int[] ls) {
		count += 3;
		int temp = ls[one];
		ls[one] = ls[two];
		ls[two] = temp;
	}

}
