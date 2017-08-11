/**
 * 
 */
package string;

/**
 * @author aa49442
 * 
 */
public class LongextSubStr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoString demoString = new DemoString("ADFSGZXCVTASB");

		final int lss = demoString.longestSubStr();
		System.out.println(lss);
		
		DemoString demoString2 = new DemoString("ADASKZCCRRRAKB");
		
		final int editDistance = DemoString.editDistance(demoString, demoString2);
		
		System.out.println("Min editing : " + editDistance);
	}
}