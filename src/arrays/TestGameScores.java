/**
 * 
 */
package arrays;

/**
 * @author apple
 * 
 */
public class TestGameScores {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scores scores = new Scores();

		scores.add(new GameEntry("rishu", 123));
		scores.add(new GameEntry("rishu", 1123));
		scores.add(new GameEntry("rishu", 1));
		scores.add(new GameEntry("rishu", 1213));
		scores.add(new GameEntry("rishu", 1231));
		scores.add(new GameEntry("rishu", 12311));
		System.out.println(scores);
	}
}