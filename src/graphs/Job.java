/**
 * 
 */
package graphs;

/**
 * @author rishabh.daim
 *
 */
public class Job implements Comparable<Job> {
	
	private final String id;
	private final int deadline;
	private final int profit;
	
	/**
	 * @param id
	 * @param deadline
	 * @param profit
	 */
	public Job(String id, int deadline, int profit) {
		this.id = id;
		this.deadline = deadline;
		this.profit = profit;
	}
	
	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @return the deadline
	 */
	public final int getDeadline() {
		return deadline;
	}

	/**
	 * @return the profit
	 */
	public final int getProfit() {
		return profit;
	}

	@Override
	public int compareTo(Job o) {
		return Integer.compare(o.profit, this.profit);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Job [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		builder.append("deadline=");
		builder.append(deadline);
		builder.append(", profit=");
		builder.append(profit);
		builder.append("]");
		return builder.toString();
	}
}
