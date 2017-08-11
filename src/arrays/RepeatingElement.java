/**
 * 
 */
package arrays;

/**
 * @author Rishabh.Daim
 *
 */
public class RepeatingElement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] integers = new Integer[30];

		for (int i = 0; i < 30; i++)
			integers[i] = (int) (Math.random() * 300);

		Array<Integer> array = new Array<Integer>(integers);

		System.out.println(array);

		array.firstRepeatingElement();
	}
}