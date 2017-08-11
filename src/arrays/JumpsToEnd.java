/**
 * 
 */
package arrays;

/**
 * @author aa49442
 * 
 */
public class JumpsToEnd {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] ints = new Integer[50];
		int length = ints.length;
		for (int j = 0; j < length; j++)
			ints[j] = (int) (Math.random() * 10);

		Array<Integer> arr = new Array<Integer>(ints);

		System.out.println(arr);

		int i = arr.minJumps();
		System.out.println(i);
	}
}