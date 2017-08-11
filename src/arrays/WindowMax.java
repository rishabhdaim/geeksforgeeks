/**
 * 
 */
package arrays;

/**
 * @author aa49442
 * 
 */
public class WindowMax {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] ints = new Integer[20];
		int length = ints.length;
		for (int j1 = 0; j1 < length; j1++)
			ints[j1] = (int) (Math.random() * 100);

		Array<Integer> arr = new Array<Integer>(ints);

		System.out.println(arr);
		
		arr.printKMax(4);
		
		System.out.println();

		arr.maxOfWindow(4);
	}
}