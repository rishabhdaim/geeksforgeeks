/**
 * 
 */
package graphs;

import java.util.Arrays;

import linkedlists.DoubleLinkedList;

/**
 * @author aa49442
 * 
 */
public class WGraph<E extends Comparable<? super E>> {

	private Vertex<E>[] vertexs;
	private int[][] adjMat;
	private int noOfVertex;
	private int size;

	/**
	 * @param noOfVertex
	 */
	@SuppressWarnings("unchecked")
	public WGraph(int noOfVertex) {
		super();
		this.noOfVertex = noOfVertex;
		this.vertexs = new Vertex[this.noOfVertex];
		adjMat = new int[this.noOfVertex][this.noOfVertex];
		fillWeightedGraph();
		this.size = 0;
	}

	/**
	 * To fill weighted graph
	 */
	private void fillWeightedGraph() {
		for (int i = 0; i < noOfVertex; i++)
			for (int j = 0; j < noOfVertex; j++)
				if (i != j)
					adjMat[i][j] = Integer.MAX_VALUE;
	}

	public void addVertex(E e) {
		if (size == noOfVertex)
			grow();
		vertexs[size++] = new Vertex<E>(e);
	}

	private void grow() {
		@SuppressWarnings("unchecked")
		final Vertex<E>[] newVertex = new Vertex[noOfVertex * 2];
		for (int i = 0; i < noOfVertex; i++)
			newVertex[i] = vertexs[i];
		vertexs = newVertex;

		final int[][] newAdjMat = new int[noOfVertex * 2][noOfVertex * 2];

		for (int i = 0; i < noOfVertex; i++)
			for (int j = 0; j < noOfVertex; j++)
				newAdjMat[i][j] = adjMat[i][j];

		adjMat = newAdjMat;

		noOfVertex = noOfVertex << 1;
	}

	/**
	 * @param start
	 *            index of start vertex
	 * @param end
	 *            index of end vertex
	 */
	public void addEdge(int start, int end, int weight) {
		if (start > size || end > size)
			throw new IllegalStateException("vertex not present");
		adjMat[start][end] = weight;
		// adjMat[end][start] = true;

		// to maintain in/out degree
		vertexs[start].outDegree++;
		vertexs[end].inDegree++;
	}

	public void printAdjMat() {
		printAdjMat(adjMat, noOfVertex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UnGraph [vertexs=" + Arrays.toString(vertexs) + "]";
	}

	/**
	 * Vertex for this graph
	 * 
	 * @author aa49442
	 * 
	 * @param <E>
	 *            type of vertex
	 */
	static class Vertex<E extends Comparable<? super E>> implements Comparable<Vertex<E>> {
		private final E e;
		private boolean isVisited;
		private int inDegree;
		private int outDegree;

		/**
		 * @return the inDegree
		 */
		public int getInDegree() {
			return inDegree;
		}

		/**
		 * @return the outDegree
		 */
		public int getOutDegree() {
			return outDegree;
		}

		/**
		 * @param e
		 * @param isVisited
		 */
		public Vertex(E e) {
			super();
			this.e = e;
			this.isVisited = false;
			this.inDegree = 0;
			this.outDegree = 0;
		}

		/**
		 * @return the isVisited
		 */
		public boolean isVisited() {
			return isVisited;
		}

		/**
		 * @param isVisited
		 *            the isVisited to set
		 */
		public void setVisited(boolean isVisited) {
			this.isVisited = isVisited;
		}

		/**
		 * @return the e
		 */
		public E getE() {
			return e;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Vertex [e=" + e + ", isVisited=" + isVisited
					+ ", inDegree=" + inDegree + ", outDegree=" + outDegree
					+ "]";
		}

		@Override
		public int compareTo(Vertex<E> o) {
			return this.e.compareTo(o.e);
		}
	}

	/**
	 * @param u
	 *            starting vertex
	 * @param v
	 *            end vertex
	 * @param k
	 *            no of steps
	 * @return no of paths from u to v with k steps
	 */
	public int countWalks(int u, int v, int k) {
		// base cases
		if (k == 0 && u == v)
			return 1;
		// distance is one and there is path from u to v
		if (k == 1 && adjMat[u][v] != 0)
			return 1;

		// initialize count
		int count = 0;

		// traverse all adj nodes
		for (int i = 0; i < noOfVertex; i++)
			if (adjMat[u][i] != 0) // adj to u
				count += countWalks(i, v, k - 1);
		return count;
	}

	/**
	 * The idea is to build a 3D table where first dimension is source, second
	 * dimension is destination, third dimension is number of edges from source
	 * to destination, and the value is count of walks.
	 * 
	 * @param u
	 *            source
	 * @param v
	 *            destination
	 * @param k
	 *            steps
	 * @return no of paths with k steps
	 */
	public int countWalksOpt(int u, int v, int k) {

		// Table to be filled up using DP. The value count[i][j][e] will store
		// count of possible walks from i to j with exactly k edges

		int[][][] count = new int[noOfVertex][noOfVertex][k + 1];

		// Loop for number of edges from 0 to k
		for (int e = 0; e <= k; e++) {
			// for source
			for (int s = 0; s < noOfVertex; s++) {
				// for destination
				for (int d = 0; d < noOfVertex; d++) {
					// from base cases
					if (e == 0 && s == d)
						count[s][d][e] = 1;
					if (e == 1 && adjMat[s][d] != 0)
						count[s][d][e] = 1;

					// go to adjacent only when number of edges is more than 1.
					// this is done for optimization

					if (e > 1)
						for (int i = 0; i < noOfVertex; i++)
							// adj of source s
							if (adjMat[s][i] != 0)
								count[s][d][e] += count[i][d][e - 1];

				}
			}
		}
		return count[u][v][k];
	}

	/**
	 * Depth first traversal
	 */
	public void dfs() {
		for (int i = 0; i < noOfVertex; i++)
			if (!vertexs[i].isVisited)
				dfsUtil(i);
		unVisitVertexes();
	}

	/**
	 * @param root
	 *            whose DFS is to found
	 */
	private void dfsUtil(int root) {
		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<Integer>();
		stack.addFirst(root);
		vertexs[root].isVisited = true;
		int curr;
		int next;
		while (!stack.isEmpty()) {
			curr = stack.poll();
			System.out.print(vertexs[curr].e + " ");

			while ((next = hasAdjVertex(curr)) != -1) {
				stack.addFirst(next);
				vertexs[next].isVisited = true;
			}
		}
	}

	/**
	 * un-visit the vertexes
	 */
	private void unVisitVertexes() {
		// make all vertexes un-visited
		for (int i = 0; i < noOfVertex; i++)
			vertexs[i].isVisited = false;
	}

	/**
	 * Breadth first traversal
	 */
	public void bfs() {
		for (int i = 0; i < noOfVertex; i++)
			if (!vertexs[i].isVisited)
				bfsUtil(i);
		unVisitVertexes();
	}

	/**
	 * @param i
	 *            vertex from where breadth first traversal is to done
	 */
	private void bfsUtil(int i) {
		final DoubleLinkedList<Integer> queue = new DoubleLinkedList<Integer>();
		queue.offer(i);
		vertexs[i].isVisited = true;

		int curr;
		int next;
		while (!queue.isEmpty()) {
			curr = queue.poll();
			System.out.print(vertexs[curr].e + " ");

			while ((next = hasAdjVertex(curr)) != -1) {
				queue.offer(next);
				vertexs[next].isVisited = true;
			}
		}
	}

	/**
	 * Fills Stack with vertices (in increasing order of finishing times) The
	 * top element of stack has the maximum finishing time
	 * 
	 * @param root
	 */
	public void fillOrder(int root) {
		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<Integer>();
		fillOrderUtil(root, stack);
	}

	private void fillOrderUtil(int root, DoubleLinkedList<Integer> stack) {
		vertexs[root].isVisited = true;

		for (int i = 0; i < noOfVertex; i++)
			if (adjMat[root][i] != 0 && !vertexs[i].isVisited)
				fillOrderUtil(i, stack);

		stack.addFirst(root);
	}

	private int hasAdjVertex(int index) {

		for (int i = 0; i < noOfVertex; i++)
			if (adjMat[index][i] != 0 && !vertexs[i].isVisited)
				return i;
		return -1;
	}

	/**
	 * to find and prints all strongly connected components
	 */
	public void printScc() {
		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<Integer>();

		// Fill vertices in stack according to their finishing times
		for (int i = 0; i < noOfVertex; i++)
			if (!vertexs[i].isVisited)
				fillOrderUtil(i, stack);

		// Create a reversed graph
		getTranspose();

		// Mark all the vertices as not visited (For second DFS)
		unVisitVertexes();

		// Now process all vertices in order defined by Stack

		while (!stack.isEmpty()) {
			int i = stack.removeFirst();

			// Print Strongly connected component of the popped vertex
			if (!vertexs[i].isVisited)
				dfsUtil(i);
			System.out.println();
		}
		// undo reversed graph
		getTranspose();

		// Mark all the vertices as not visited (For second DFS)
		unVisitVertexes();
	}

	/**
	 * it returns reverse (or transpose) of this graph
	 * 
	 * @return
	 */
	private void getTranspose() {

		final int[][] newAdjMat = new int[noOfVertex][noOfVertex];
		for (int i = 0; i < noOfVertex; i++)
			for (int j = 0; j < noOfVertex; j++)
				newAdjMat[j][i] = this.adjMat[i][j];

		this.adjMat = newAdjMat;
	}

	/**
	 * this method is used to check whether graph is strongly connected or not
	 * 
	 * @return
	 */
	public boolean isSc() {
		boolean isSc = isScUtil();

		unVisitVertexes();

		return isSc;
	}

	private boolean isScUtil() {

		// Find the first vertex with non-zero degree
		int n = 0;
		for (; n < noOfVertex; n++)
			if (vertexs[n].inDegree > 0)
				break;

		// Do DFS traversal starting from first vertex.

		dfsUtil(n);

		// If DFS traversal doesn’t visit all vertices, then return false.

		for (int i = 0; i < noOfVertex; i++)
			if (vertexs[i].inDegree > 0 && !vertexs[i].isVisited)
				return false;

		// Create a reversed graph

		getTranspose();

		// Mark all the vertices as not visited (For second DFS)

		unVisitVertexes();

		// Do DFS for reversed graph starting from first vertex. Staring Vertex
		// must be same starting point of first DFS

		dfsUtil(n);

		// If all vertices are not visited in second DFS, then return false

		for (int i = 0; i < noOfVertex; i++)
			if (vertexs[i].inDegree > 0 && !vertexs[i].isVisited)
				return false;
		return true;
	}

	/**
	 * Eulerian Path is a path in graph that visits every edge exactly once.
	 * Eulerian Circuit is an Eulerian Path which starts and ends on the same
	 * vertex.
	 * 
	 * A graph is said to be eulerian if it has eulerian cycle. We have
	 * discussed eulerian circuit for an undirected graph. In this post, same is
	 * discussed for a directed graph.
	 * 
	 * 
	 * A directed graph has an eulerian cycle if following conditions are true
	 * 1) All vertices with nonzero degree belong to a single strongly connected
	 * component. 2) In degree and out degree of every vertex is same.
	 * 
	 * This function returns true if the directed graph has an eulerian cycle,
	 * otherwise returns false
	 * 
	 * @return
	 */
	public boolean isEC() {
		if (!isSc())
			return false;

		// Check if in degree and out degree of every vertex is same
		for (int i = 0; i < noOfVertex; i++)
			if (vertexs[i].inDegree != vertexs[i].outDegree)
				return false;
		return true;
	}

	/**
	 * This function takes an of strings and returns true if the given array of
	 * strings can be chained to form cycle
	 * 
	 * @param n
	 * @param arr
	 * @return
	 */
	public boolean canBeChained(int n, String[] arr) {

		for (int i = 0; i < n; i++)
			this.addEdge(arr[i].charAt(0) - 'a',
					arr[i].charAt(arr[i].length() - 1) - 'a', 1);

		printAdjMat();

		return isEC();
	}

	/**
	 * // This function finds and prints order of character from a sorted array
	 * of words. n is size of words[]. alpha is set of possible alphabets. For
	 * simplicity, this function is written in a way that only first 'alpha'
	 * characters can be there in words array. For example if alpha is 7, then
	 * words[] should have only 'a', 'b','c' 'd', 'e', 'f', 'g'
	 * 
	 * @param n
	 * @param words
	 */
	public void alienLang(int n, String[] words) {
		// Process all adjacent pairs of words and create a graph
		for (int i = 0; i < n - 1; i++) {
			String s1 = words[i];
			String s2 = words[i + 1];

			for (int j = 0; j < Math.min(s1.length(), s2.length()); j++) {
				// If we find a mismatching character, then add an edge from
				// character of s1 to that of s2

				if (s1.charAt(j) != s2.charAt(j)) {
					this.addEdge(s1.charAt(j) - 'a', s2.charAt(j) - 'a', 1);
					break;
				}
			}
		}

		printAdjMat();

		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<Integer>();
		// Call the recursive helper function to store Topological Sort starting
		// from all vertices one by one
		for (int i = 0; i < noOfVertex; i++)
			if ((vertexs[i].inDegree > 0 || vertexs[i].outDegree > 0)
					&& !vertexs[i].isVisited)
				topologicalSortUtil(i, stack);

		// Print contents of stack
		while (!stack.isEmpty())
			System.out.print(new Character((char) ('a' + stack.poll())) + " ");

		unVisitVertexes();
	}

	/**
	 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering
	 * of vertices such that for every directed edge uv, vertex u comes before v
	 * in the ordering. Topological Sorting for a graph is not possible if the
	 * graph is not a DAG.
	 * 
	 * 
	 * In topological sorting, we use a temporary stack. We don’t print the
	 * vertex immediately, we first recursively call topological sorting for all
	 * its adjacent vertices, then push it to a stack. Finally, print contents
	 * of stack. Note that a vertex is pushed to stack only when all of its
	 * adjacent vertices (and their adjacent vertices and so on) are already in
	 * stack.
	 */
	public void topologicalSort() {
		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<Integer>();

		// Call the recursive helper function to store Topological Sort starting
		// from all vertices one by one
		for (int i = 0; i < noOfVertex; i++)
			if (!vertexs[i].isVisited)
				topologicalSortUtil(i, stack);

		// Print contents of stack
		while (!stack.isEmpty())
			System.out.print(stack.poll() + " ");
	}

	/**
	 * A recursive function used by topologicalSort
	 * 
	 * @param i
	 * @param stack
	 */
	private void topologicalSortUtil(int i, DoubleLinkedList<Integer> stack) {
		// Mark the current node as visited.
		vertexs[i].isVisited = true;

		for (int j = 0; j < noOfVertex; j++)
			if ((adjMat[i][j] != 0) && !vertexs[j].isVisited)
				topologicalSortUtil(j, stack);

		stack.addFirst(i);
	}

	/**
	 * Solves the all-pairs shortest path problem using Floyd Warshall algorithm
	 */
	public void floydWarshallAlgo() {

		/*
		 * dist[][] will be the output matrix that will finally have the
		 * shortest distances between every pair of vertices
		 */

		int[][] dist = new int[noOfVertex][noOfVertex];

		for (int i = 0; i < noOfVertex; i++)
			for (int j = 0; j < noOfVertex; j++)
				dist[i][j] = adjMat[i][j];

		/*
		 * Add all vertices one by one to the set of intermediate vertices. --->
		 * Before start of a iteration, we have shortest distances between all
		 * pairs of vertices such that the shortest distances consider only the
		 * vertices in set {0, 1, 2, .. k-1} as intermediate vertices. ---->
		 * After the end of a iteration, vertex no. k is added to the set of
		 * intermediate vertices and the set becomes {0, 1, 2, .. k}
		 */

		for (int k = 0; k < noOfVertex; k++)
			// Pick all vertices as source one by one
			for (int i = 0; i < noOfVertex; i++)
				// Pick all vertices as destination for the above picked source
				for (int j = 0; j < noOfVertex; j++)
					// If vertex k is on the shortest path from i to j, then
					// update the value of dist[i][j]
					if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && (dist[i][k] + dist[k][j] < dist[i][j]))
						dist[i][j] = dist[i][k] + dist[k][j];

		printAdjMat(dist, noOfVertex);
	}

	private void printAdjMat(int[][] adjMat, int noOfVertex) {

		for (int i = 0; i < noOfVertex; i++) {
			for (int j = 0; j < noOfVertex; j++)
				System.out.print(adjMat[i][j] + " | ");
			System.out.println();
		}
		System.out.println("-------------------------------------");

	}
}