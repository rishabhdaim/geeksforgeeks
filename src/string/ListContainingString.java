/**
 * 
 */
package string;

import java.util.Arrays;
import java.util.List;

/**
 * @author aa49442
 * 
 */
public class ListContainingString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> strings = Arrays.asList("sunday", "utensils", "sudan",
				"madam", "funny", "spain", "nainas", "friday", "mundays",
				"weekdays");
		DemoString demoString = new DemoString("sun");

		demoString.printListStr(strings);
	}
}