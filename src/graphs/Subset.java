/**
 * 
 */
package graphs;

/**
 * @author Rishabh.Daim
 *
 */
public class Subset {
	
	int parent;
	int rank;
	
	/**
	 * @param parent
	 * @param rank
	 */
	public Subset(int parent, int rank) {
		this.parent = parent;
		this.rank = rank;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Subset [parent=");
		builder.append(parent);
		builder.append(", rank=");
		builder.append(rank);
		builder.append("]");
		return builder.toString();
	}
}
