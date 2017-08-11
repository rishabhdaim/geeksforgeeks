/**
 * 
 */
package arrays;

/**
 * @author aa49442
 * 
 */
public class NoOfTriangles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] i = new Integer[10];
		for (int j = 0; j < i.length; j++)
			i[j] = (int) (Math.random() * 20);

		Array<Integer> arr = new Array<Integer>(i);

		System.out.println(arr);

		arr.quickSort();

		System.out.println(arr);

		arr.noOfTriangles();
	}
}
