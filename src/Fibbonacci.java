/**
 * 
 */

/**
 * @author Rishabh.Daim
 *
 */
public class Fibbonacci {

	/**
	 * @param args
	 */
	static long[] arr;

	public static void main(String[] args) {

		final Fibbonacci f = new Fibbonacci();

		final int n = 40;

		long l = System.currentTimeMillis();
		
		System.out.println(f.f1(n));

		System.out.println(System.currentTimeMillis() - l);

		arr = new long[n+1];
		
		l = System.currentTimeMillis();
		System.out.println(f.f2(n));
		System.out.println(System.currentTimeMillis() - l);
		
		l = System.currentTimeMillis();
		System.out.println(f.f3(n));
		System.out.println(System.currentTimeMillis() - l);
	}

	// overlapping fibbonacci
	private long f1(final int n) {
		
		if (n <= 1)
			return n;

		return f1(n - 1) + f1(n - 2);
	}

	// dynamic programming fibbonacci memory
	private long f2(final int n) {

		if (arr[n] == 0) {

			if (n <= 1) arr[n] = n;

			else arr[n] = f2(n - 1) + f2(n - 2);
		}
		
		return arr[n];
	}
	
	// dynamic programming fibbonacci tabuled
	private long f3(final int n) {

		for (int i = 0; i < arr.length; i++) {
			arr[i] = 0;
		}
		
		arr[0] = 1;
		arr[1] = 1;
		
		for (int i = 2; i <= n; i++) {
			arr[i] = arr[i-1] + arr[i-2];
		}
		
		return arr[n-1];
	}

}
