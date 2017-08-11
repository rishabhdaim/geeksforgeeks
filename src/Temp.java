/**
 * 
 */

/**
 * @author aa49442
 * 
 */
public class Temp {
	private static final long multiplier = 0x5DEECE66DL;
	private static final long addend = 0xBL;
	private static final long mask = (1L << 48) - 1;
	private long seed;

	public Temp(int seed) {
		this.seed = seed;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Temp temp = new Temp(26);

		for (int i = 0; i < 40; i++)
			System.out.print(temp.random(32) + " ");
	}

	private int random(int n) {
		if (n <= 0)
			throw new IllegalArgumentException("n must be positive");

		if ((n & -n) == n)
			return (int) (n * (long) next(31) >> 31);
		int bits, value;
		do {
			bits = next(31);
			value = bits % n;
		} while (bits - value + (n - 1) < 0);
		return value;
	}

	private int next(int bits) {
		long seed = this.seed;
		long nextSeed = (seed * multiplier + addend) & mask;
		this.seed = nextSeed;
		return (int) (nextSeed >>> (48 - bits));
	}
}
