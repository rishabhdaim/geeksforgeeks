/**
 * 
 */
package string;

/**
 * @author aa49442
 * 
 */
public class PatternMatching {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoString demoString = new DemoString("asdfghjklm");

		String str = "as*ghj?lm";

		boolean b = demoString.wildCardMatch(str, '*', '?');
		System.out.println(b);
	}
}