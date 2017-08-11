/**
 * 
 */
package arrays;

/**
 * @author apple
 * 
 */
public class TicTacToe {

	private static final int X = 1, O = -1, EMPTY = 0;
	private int[][] board = new int[3][3];
	private int player;

	public TicTacToe() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = EMPTY;
		player = X;
	}

	/**
	 * Puts an X or 0 mark at position i,j
	 */

	public void putMark(int i, int j) {
		if (i < 0 || i > 2 || j < 0 || j > 2)
			throw new IllegalArgumentException();
		if (board[i][j] != EMPTY)
			throw new IllegalStateException();
		board[i][j] = player;
		player = -player;
	}

	/**
	 * Checks whether the board configuration is a win for the given player
	 */
	public boolean isWin(int mark) {
		return ((board[0][0] + board[0][1] + board[0][2] == mark * 3)
				|| (board[1][0] + board[1][1] + board[1][2] == mark * 3)
				|| (board[2][0] + board[2][1] + board[2][2] == mark * 3)
				|| (board[0][0] + board[1][0] + board[2][0] == mark * 3)
				|| (board[0][1] + board[1][1] + board[2][1] == mark * 3)
				|| (board[0][2] + board[1][2] + board[2][2] == mark * 3)
				|| (board[0][0] + board[1][1] + board[2][2] == mark * 3) || (board[2][0]
				+ board[1][1] + board[0][2] == mark * 3));
	}

	/**
	 * Returns the winning player or 0 to indicate a tie
	 */

	public int winner() {
		if (isWin(X))
			return X;
		else if (isWin(O))
			return O;
		else
			return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				System.out.print(board[i][j] + " | ");
			System.out.println();
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		System.out.println(game);
		/*
		 * X & O moves:
		 */
		game.putMark(0, 0);
		game.putMark(1, 2);
		game.putMark(2, 2);
		game.putMark(2, 1);
		game.putMark(2, 0);
		game.putMark(1, 0);
		game.putMark(0, 1);
		game.putMark(0, 2);
		game.putMark(1, 1);
		System.out.println(game);
		System.out.println(game.winner());
	}
}