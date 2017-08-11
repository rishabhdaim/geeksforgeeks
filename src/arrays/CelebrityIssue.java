/**
 * 
 */
package arrays;

/**
 * @author aa49442
 * 
 */
public class CelebrityIssue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TwoDArray twoDArray = new TwoDArray(6, 6);
		twoDArray.fillBooleanArray();
		
		int c = twoDArray.getCelebrity();
		System.out.println(c);
	}
}