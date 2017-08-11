/**
 * 
 */

/**
 * @author aa49442
 * 
 */
public class SquareRoot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 37;
		i = (int) squareRoot(i);
		System.out.println(i);
	}

	private static double squareRoot(double i) {
		double k = 1;
		while (k * k - i > 0.000000001 || i - k * k > 0.000000001)
			k = (k + i / k) / 2;
		return k;
	}
}