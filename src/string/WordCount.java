/**
 * 
 */
package string;

/**
 * @author aa49442
 * 
 */
public class WordCount {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoString demoString = new DemoString(
				"thhis us fjdf fsdijf fsd \n bfd \n   very long \t\n   string ;");

		int wc = demoString.countWords();
		System.out.println(wc);
	}
}