/**
 * 
 */
package arrays;

/**
 * @author aa49442
 * 
 */
public class Stocks {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Integer[] i = new Integer[20];
		for (int j = 0; j < i.length; j++)
			i[j] = (int) (Math.random() * 100);

		Array<Integer> arr = new Array<Integer>(i);

		System.out.println(arr);

		arr.sellAndBuyStocks();
		
		arr.maxSumInRotation();
		
		arr.decodeMsg(new int[] {1,8,3,6,5,6,7,8,9});
	}
}