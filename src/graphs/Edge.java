/**
 * 
 */
package graphs;

/**
 * @author aa49442
 * 
 */
public class Edge implements Comparable<Edge> {

	final int src;
	final int dest;
	int weight;

	/**
	 * @param src
	 * @param dest
	 * @param weight
	 */
	public Edge(int src, int dest, int weight) {
		super();
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the src
	 */
	public int getSrc() {
		return src;
	}

	/**
	 * @return the dest
	 */
	public int getDest() {
		return dest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Edge [src=" + src + ", dest=" + dest + ", weight=" + weight+ "]";
	}
	
	public Edge copyOf() {
		return new Edge(src, dest, weight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dest;
		result = prime * result + src;
		result = prime * result + weight;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (dest != other.dest)
			return false;
		if (src != other.src)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Edge o) {
		return Integer.valueOf(this.weight).compareTo(o.weight);
	}
}