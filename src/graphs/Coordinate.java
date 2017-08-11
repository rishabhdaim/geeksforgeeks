/**
 * 
 */
package graphs;

/**
 * @author Rishabh.Daim
 *
 */
class Coordinate implements Comparable<Coordinate>{
	
	int x;
	int y;
	int moves;
	/**
	 * @param x
	 * @param y
	 */
	Coordinate(int x, int y, int moves) {
		this.x = x;
		this.y = y;
		this.moves = moves;
	}
	@Override
	public int compareTo(Coordinate o) {
		return 0;
	}
	
}
