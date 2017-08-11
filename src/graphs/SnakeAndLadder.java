/**
 * 
 */
package graphs;

import java.util.Arrays;

/**
 * @author Rishabh.Daim
 *
 */
public class SnakeAndLadder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		UnDGraph<Integer> unDGraph = new UnDGraph<>(0);
		final int n = 30;
		final int[] moves = new int[n];
		Arrays.fill(moves, -1); // no ladder and snake
		
		// ladders
		moves[2] = 21;
		moves[4] = 7;
		moves[10] = 25;
		moves[19] = 28;
		
		// snakes
		moves[26] = 0;
		moves[20] = 8;
	    moves[16] = 3;
	    moves[18] = 6;
	    
	    System.out.println(unDGraph.snakeAndLadder(moves));
	}

}
