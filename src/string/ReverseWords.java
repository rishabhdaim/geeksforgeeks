/**
 * 
 */
package string;

/**
 * @author aa49442
 * 
 */
public class ReverseWords {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoString demoString = new DemoString("much very program this like i");
		String str = demoString.reverseWords();
		System.out.println(str);

		demoString = new DemoString("asdfghjkl");
		demoString.reverseStr();
	}
}