/**
 * 
 */
package arrays;

/**
 * @author Rishabh.Daim
 *
 */
public class IntersectionOfArray {

	public static void main(String[] args) {

		final int i = 10;
		int[] integers = new int[i];
		for (int j = 0; j < i; j++)
			integers[j] = (int) (Math.random() * i);

		int[] integers1 = new int[i];
		for (int j = 0; j < i; j++)
			integers1[j] = (int) (Math.random() * i);

		int[] integers11 = new int[i];
		for (int j = 0; j < i; j++)
			integers11[j] = (int) (Math.random() * i);

		Array<Integer> array = new Array<>(i);
		array.commonElements(integers, integers1, integers11);
	}
}