/**
 * 
 */
package string;

/**
 * @author aa49442
 * 
 */
public class RunLength {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoString demoString = new DemoString("GeeksForGeeks");

		String runLenStr = demoString.runLengthStr();
		System.out.println(runLenStr);
	}
}