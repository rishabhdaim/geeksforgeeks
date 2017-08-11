/**
 * 
 */
package graphs;

/**
 * @author Rishabh.Daim
 *
 */
class QueueEntry implements Comparable<QueueEntry>{
	int v; // vertex
	int dist; // distance from source
	
	public QueueEntry() {
		this(0, 0);
	}
	
	/**
	 * @param v
	 * @param dist
	 */
	QueueEntry(int v, int dist) {
		this.v = v;
		this.dist = dist;
	}

	// just added to use this in DLL
	@Override
	public int compareTo(QueueEntry o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
