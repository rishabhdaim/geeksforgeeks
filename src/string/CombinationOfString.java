/**
 * 
 */
package string;

/**
 * @author aa49442
 * 
 */
public class CombinationOfString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("First Test");
		char set1[] = { 'a', 'b' };
		int k = 3;
		printAllKLength(set1, k);

		System.out.println("\nSecond Test");
		char set2[] = { 'a', 'b', 'c', 'd' };
		k = 1;
		printAllKLength(set2, k);
	}

	private static void printAllKLength(char[] set, int k) {
		int n = set.length;
		printAllKLength(set, k, n, "");
	}

	private static void printAllKLength(char[] set, int k, int n, String result) {
		if (k == 0) {
			System.out.println(result);
			return;
		}

		for (int i = 0; i < n; i++) {
			String newPrefix = result + set[i];
			printAllKLength(set, k - 1, n, newPrefix);
		}
	}
}