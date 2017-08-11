/**
 * 
 */
package arrays;

import string.DemoString;

/**
 * @author aa49442
 * 
 */
public class SuffixArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoString demoString = new DemoString("appel");
		demoString.suffixArray();

		demoString.patternSearch("el");

		demoString.suffixArrayOpt("apple");

		// demoString.suffixArrRadixSort("apple");
	}
}