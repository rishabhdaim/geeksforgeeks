/**
 * 
 */
package string;

import java.util.Arrays;

/**
 * @author aa49442
 * 
 */
public class Anagrams {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoString demoString = new DemoString("");

		String[] st = { "asd", "qwe", "zxc", "sda", "weq", "xcz", "ads", "qew",
				"zcx", "dsa" };

		System.out.println(Arrays.toString(st));
		demoString.anagram(st);
	}
}