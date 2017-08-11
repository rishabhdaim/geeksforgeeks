/**
 * 
 */
package arrays;

/**
 * @author aa49442
 * 
 */
public class GreaterRightElements {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Integer[] ints = new Integer[20];
		int length = ints.length;
		for (int j = 0; j < length; j++)
			ints[j] = (int) (Math.random() * 100);

		Array<Integer> arr = new Array<Integer>(ints);

		System.out.println(arr);

		arr.greaterNextElement();
		
		arr.maxIndexDiff();
	}
}