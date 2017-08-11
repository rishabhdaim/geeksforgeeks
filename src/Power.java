/**
 * 
 */


/**
 * @author apple
 * 
 */
public class Power {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long l = power(2, 13);
		System.out.println(l);
	}

	private static long power(int x, int y) {
		System.out.println("Enterring x = " + x + " y = " + y);
		if (y == 1)
			return x;
		else {
			long l = power(x * x, y / 2);
			System.out.println("Returning x & y are = " + x + " " + y);
			if (y % 2 != 0)
				l = l * x;
			return l;
		}
	}

}
