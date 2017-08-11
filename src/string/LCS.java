/**
 * 
 */
package string;

/**
 * @author aa49442
 * 
 */
public class LCS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoString demoString = new DemoString("asdfghjk");

		String s = "daasdfzxcvbn";

		int lcs = demoString.lcs(s);
		System.out.println(lcs);

		lcs = demoString.lcsOpt(s);
		System.out.println(lcs);

		demoString.printLcs(s);

		int lcSubStr = demoString.lcSubStrOpt(s);

		System.out.println(lcSubStr);

		demoString.printLcSubStrOpt(s);
	}
}