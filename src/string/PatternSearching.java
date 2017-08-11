/**
 * 
 */
package string;

/**
 * @author aa49442
 * 
 */
public class PatternSearching {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DemoString demoString = new DemoString();

		String txt = "This is a test text";
		String pat = "test";

		demoString.naivePatternSearching(txt, pat);
		demoString.kmpPatSearch(txt, pat);
		demoString.faPatSearch(txt, pat);
		demoString.bmBadPatSearch(txt, pat);
		demoString.zAlgoPatSearch(txt, pat);

		System.out.println("---------------------------");
		txt = "AABAACAADAABAAABAA";
		pat = "AABA";
		demoString.naivePatternSearching(txt, pat);
		demoString.kmpPatSearch(txt, pat);
		demoString.faPatSearch(txt, pat);
		demoString.bmBadPatSearch(txt, pat);

		System.out.println("----------------------");
		txt = "ABABDABACDABABCABAB";
		pat = "ABABCABAB";
		demoString.naivePatternSearching(txt, pat);
		demoString.kmpPatSearch(txt, pat);
		demoString.faPatSearch(txt, pat);
		demoString.bmBadPatSearch(txt, pat);
		
	}
}