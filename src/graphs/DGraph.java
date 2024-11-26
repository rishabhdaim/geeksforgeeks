/**
 * 
 */
package graphs;

import java.util.Arrays;

import linkedlists.DoubleLinkedList;

/**
 * Matrix based implementation of directed graph
 * 
 * @author Rishabh.Daim
 *
 * @param <E>
 */
public class DGraph<E extends Comparable<? super E>> {

	/**
	 * Vertexes of graph i.e. elements of graph
	 */
	private Vertex<E>[] vertexs;
	/**
	 * 2D array representation of graph showing which nodes are connected
	 */
	private boolean[][] adjMat;
	private int noOfVertex;
	private int size;

	/**
	 * @param noOfVertex
	 */
	@SuppressWarnings("unchecked")
	public DGraph(int noOfVertex) {
		super();
		this.noOfVertex = noOfVertex;
		this.vertexs = new Vertex[this.noOfVertex];
		adjMat = new boolean[this.noOfVertex][this.noOfVertex];
		this.size = 0;
	}

	public void addVertex(E e) {
		if (size == noOfVertex)
			grow();
		vertexs[size++] = new Vertex<>(e);
	}

	private void grow() {
		@SuppressWarnings("unchecked")
		final Vertex<E>[] newVertex = new Vertex[noOfVertex * 2];
		for (int i = 0; i < noOfVertex; i++)
			newVertex[i] = vertexs[i];
		vertexs = newVertex;

		final boolean[][] newAdjMat = new boolean[noOfVertex * 2][noOfVertex * 2];

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
	public void addEdge(final int start, final int end) {
		if (start > size || end > size)
			throw new IllegalStateException("vertex not present");
		adjMat[start][end] = true;
		// adjMat[end][start] = true;

		// to maintain in/out degree
		vertexs[start].outDegree++;
		vertexs[end].inDegree++;
	}

	public void printAdjMat() {
		for (int i = 0; i < noOfVertex; i++) {
			for (int j = 0; j < noOfVertex; j++)
				System.out.print(adjMat[i][j] + " | ");
			System.out.println();
		}
		System.out.println("-------------------------------------");
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

	static class Vertex<E extends Comparable<? super E>> implements Comparable<Vertex<E>> {
		private final E e;
		private boolean isVisited;
		
		/**
		 * total numbers of incoming paths to this node
		 */
		private int inDegree;
		/**
		 * total numbers of outgoing paths from this node
		 */
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
		if (k == 1 && adjMat[u][v])
			return 1;

		// initialize count
		int count = 0;

		// traverse all adj nodes
		for (int i = 0; i < noOfVertex; i++)
			if (adjMat[u][i]) // adj to u
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
					if (e == 1 && adjMat[s][d])
						count[s][d][e] = 1;

					// go to adjacent only when number of edges is more than 1.
					// this is done for optimization

					if (e > 1)
						for (int i = 0; i < noOfVertex; i++)
							// adj of source s
							if (adjMat[s][i])
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
		System.out.println();
	}

	/**
	 * @param root
	 *            whose DFS is to found
	 */
	private void dfsUtil(final int root) {
		
		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<>();
		stack.addFirst(root);
		vertexs[root].isVisited = true; // mark it is visited
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
			if (adjMat[root][i] && !vertexs[i].isVisited)
				fillOrderUtil(i, stack);

		stack.addFirst(root);
	}

	/**
	 * @param index index of vertex in adjacency matrix
	 * @return get next adjacent vertex if exists else return -1
	 */
	private int hasAdjVertex(final int index) {

		for (int i = 0; i < noOfVertex; i++)
			if (adjMat[index][i] && !vertexs[i].isVisited)
				return i;
		return -1;
	}

	/**
	 * to find and prints all strongly connected components
	 */
	public void printScc() {
		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<>();

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
	 * it reverse (or transpose) this graph
	 */
	private void getTranspose() {

		final boolean[][] newAdjMat = new boolean[noOfVertex][noOfVertex];
		for (int i = 0; i < noOfVertex; i++)
			for (int j = 0; j < noOfVertex; j++)
				newAdjMat[j][i] = this.adjMat[i][j];

		this.adjMat = newAdjMat;
	}

	/**
	 * this method is used to check whether graph is strongly connected or not
	 * 
	 * @return true if strongly connected else false
	 */
	public boolean isSCC() {
		
		final boolean isSc = isScUtil();
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

		// If DFS traversal doesn't visit all vertices, then return false.
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
		if (!isSCC())
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
					arr[i].charAt(arr[i].length() - 1) - 'a');

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
					addEdge(s1.charAt(j) - 'a', s2.charAt(j) - 'a');
					break;
				}
			}
		}

		printAdjMat();

		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<>();
		// Call the recursive helper function to store Topological Sort starting
		// from all vertices one by one
		for (int i = 0; i < noOfVertex; i++)
			if ((vertexs[i].inDegree > 0 || vertexs[i].outDegree > 0)
					&& !vertexs[i].isVisited)
				topologicalSortUtil(i, stack);

		// Print contents of stack
		while (!stack.isEmpty())
			System.out.print((char) ('a' + stack.poll()) + " ");

		unVisitVertexes();
	}

	/**
	 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering
	 * of vertices such that for every directed edge {u, v}, vertex u comes
	 * before v in the ordering. Topological Sorting for a graph is not possible
	 * if the graph is not a DAG.
	 * 
	 * 
	 * In topological sorting, we use a temporary stack. We don't print the
	 * vertex immediately, we first recursively call topological sorting for all
	 * its adjacent vertices, then push it to a stack. Finally, print contents
	 * of stack. Note that a vertex is pushed to stack only when all of its
	 * adjacent vertices (and their adjacent vertices and so on) are already in
	 * stack.
	 */
	public void topologicalSort() {
		
		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<>();

		// Call the recursive helper function to store Topological Sort starting
		// from all vertices one by one
		for (int i = 0; i < noOfVertex; i++)
			if (!vertexs[i].isVisited)
				topologicalSortUtil(i, stack);

		// Print contents of stack
		while (!stack.isEmpty())
			System.out.print(stack.poll() + " ");
		
		unVisitVertexes();
		System.out.println();
	}

	/**
	 * A recursive function used by topologicalSort
	 * 
	 * @param i index of vertex
	 * @param stack
	 */
	private void topologicalSortUtil(final int i, final DoubleLinkedList<Integer> stack) {
		// Mark the current node as visited.
		vertexs[i].isVisited = true;

		for (int j = 0; j < noOfVertex; j++)
			if (adjMat[i][j] && !vertexs[j].isVisited)
				topologicalSortUtil(j, stack);
		
		// nothing to visit, add this node and back track
		stack.addFirst(i);
	}

	/**
	 * Depth First Traversal can be used to detect cycle in a Graph. DFS for a
	 * connected graph produces a tree. There is a cycle in a graph only if
	 * there is a back edge present in the graph. A back edge is an edge that is
	 * from a node to itself (selfloop) or one of its ancestor in the tree
	 * produced by DFS.
	 * 
	 * @return true if has cycle else false
	 */
	public boolean isCyclic() {
		for (int i = 0; i < noOfVertex; i++)
			if (!vertexs[i].isVisited && isCyclicUtil(i)) {
				unVisitVertexes();
				return true;
			}
		unVisitVertexes();
		return false;
	}

	/**
	 * This function is a variation of DFSUtil()
	 * 
	 * @param i index of Node to be traversed
	 * @return true if graph has cycle else false
	 */
	private boolean isCyclicUtil(final int i) {
		
		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<>();
		final boolean[] inStack = new boolean[noOfVertex];
		stack.addFirst(i);
		inStack[i] = true;

		vertexs[i].isVisited = true;
		int curr;
		int next;
		while (!stack.isEmpty()) {
			// this loop should only execute once
			curr = stack.poll();
			while ((next = nextAdjVertex(curr)) != -1) {
				stack.addFirst(next);
				if (inStack[next])
					return true;
				else
					inStack[next] = true;
				vertexs[next].isVisited = true;
			}
			
			// make current node as false since it is not cyclic, move to other nodes
			inStack[curr] = false;
		}
		return false;
	}

	private int nextAdjVertex(int curr) {
		for (int i = 0; i < noOfVertex; i++)
			if (adjMat[curr][i])
				return i;
		return -1;
	}

	/**
	 * To check if there exists at least one path between source and destination
	 * 
	 * @param src
	 *            source vertex
	 * @param dest
	 *            destination vertex
	 * @return true if there is at least one path from src to dest
	 */
	public boolean isReachable(final int src, final int dest) {
		// this function is also an example of DFS
		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<>();
		stack.addFirst(src);
		vertexs[src].isVisited = true;
		int curr;
		int next;

		while (!stack.isEmpty()) {

			curr = stack.poll();
			while ((next = hasAdjVertex(curr)) != -1) {
				stack.addFirst(next);
				if (next == dest)
					return true;
				else
					vertexs[next].isVisited = true;
			}
		}

		unVisitVertexes();
		return false;
	}
	
	/**
	 * If destination is reached print the solution matrix Else 
	 * a) Mark current cell in solution matrix as 1. 
	 * b) Move forward in horizontal direction and recursively check if this move leads to a solution. 
	 * c) If the move chosen in the above step doesn't lead to a solution then move down and check if this move leads to a solution. 
	 * d) If none of the above solutions work then unmark this cell as 0 (BACKTRACK) and return false.
	 * @return 
	 */
	public boolean ratMaze() {
		
		final boolean[][] sol = new boolean[this.noOfVertex][this.noOfVertex];
		for (int i = 0; i < sol.length; i++) {
			for (int j = 0; j < sol.length; j++) {
				sol[i][j] = adjMat[i][j];
			}
		}
		
		sol[0][0] = true; // start point and mark it visited
		
		if (!ratMazeUtil(sol, 0, 0)) {
			System.err.println("No solution");
			return false;
		}
		
		System.out.println("Solution exists");
		for (int i = 0; i < sol.length; i++) {
			for (int j = 0; j < sol.length; j++) {
				System.out.print(" " + sol[i][j] + " ");
			}
			System.out.println();
		}
		
		return true;
		
	}
	
	
	private boolean ratMazeUtil(final boolean[][] sol, final int x, final int y) {
		
		if(x == this.noOfVertex -1 && y == this.noOfVertex -1 ) {
			sol[x][y] = true; // solution exists
			return true;
		}
		
		if (isSafeRatPath(x, y)) {
			sol[x][y] = true; // mark this move in solution
			
			// now move in X
			if (ratMazeUtil(sol, x+1, y)) return true; //
			
			// moving left doesn't work, now move in Y
			if (ratMazeUtil(sol, x, y+1)) return true;
			
			// none of them work
			sol[x][y] = false;// back tracking from current column
			return false;
		}
		
		return false;
	}

	private boolean isSafeRatPath(final int x, final int y) {
		
		// x and y should be valid values and there must exists an path between x & y
		return x >= 0 && x < this.noOfVertex && y >= 0 && y < this.noOfVertex && adjMat[x][y];
	}
	
	final int[] xMove = {2,1,-1,-2,-2,-1,1,2};
	final int[] yMove = {1,2,2,1,-1,-2,-2,-1};

	/**
	 * If all squares are visited print the solution Else 
	 * a) Add one of the next moves to solution vector and recursively check if this move leads to a
	 * solution. (A Knight can make maximum eight moves. We choose one of the 8 moves in this step). 
	 * b) If the move chosen in the above step doesn't lead to a solution then remove this move from the solution vector and try other alternative moves. 
	 * c) If none of the alternatives work then return false (Returning false will remove the previously added item in recursion
	 * and if false is returned by the initial call of recursion then "no solution exists" )
	 */
	public boolean knightPath() {
		
		final int n = 8;
		
		final int[][] sol = new int[8][8];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sol[i][j] = -1; // mark them all unvisited
			}
		}
		
		sol[0][0] = 0; // first path
		
		if (!knightPathUtil(sol, 0, 0, 1)) {
			System.err.println("No path exists");
			return false;
		}
		
		System.out.println("Solution exists");
		for (int i = 0; i < sol.length; i++) {
			for (int j = 0; j < sol.length; j++) {
				System.out.print(" " + sol[i][j] + " ");
			}
			System.out.println();
		}
		
		return true;
		
	}

	private boolean knightPathUtil(final int[][] sol, int x, final int y, final int moves) {
		
		if (moves == 64) {
			return true;
		}
		
		for (int i = 0; i < 8; i++) {
			final int nextX = x + xMove[i];
			final int nextY = y + yMove[i];
			
			if (isSafeKnightPath(nextX, nextY, sol)) {
				sol[nextX][nextY] = moves;
				// now try next step
				if (knightPathUtil(sol, nextX, nextY, moves+1)) return true;
				else sol[nextX][nextY] = -1; // back track here
			}
		}
		
		return false;
	}

	private boolean isSafeKnightPath(final int x, final int y, final int[][] sol) {
		// x & y in limits as next should be unvisited
		return x >= 0 && x < 8 && y >= 0 && y < 8 && sol[x][y] == -1;
	}

	
	public int knightSteps(final int destX, final int destY) {
		
		if (destX == 0 && destY == 0) return 0;
		
		final int n = 8;
		
		final int[][] sol = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sol[i][j] = -1; // mark them all unvisited
			}
		}
		sol[0][0] = 1; // first path
		return knightStepsUtil(destX, destY, 0, 0, sol, 1);
	}
	

	// BFS search algo
	private int knightStepsUtil(final int destX, final int destY, final int srcX, final int srcY, final int[][] sol, final int steps) {
		
		final DoubleLinkedList<Coordinate> queue = new DoubleLinkedList<>();
		queue.offer(new Coordinate(srcX, srcY, 0));
		sol[srcX][srcY] = 1;
		
		while (!queue.isEmpty()) {
			
			final Coordinate curr = queue.poll();
			for (int i = 0; i < 8; i++) {
				
				final int nextX = curr.x + xMove[i];
				final int nextY = curr.y + yMove[i];
				
				if (isSafeKnightPath(nextX, nextY, sol)) {
					sol[nextX][nextY] = 1; // mark it visited
					
					if (nextX == destX && nextY == destY) {
						return curr.moves+1;
					}
					queue.offer(new Coordinate(nextX, nextY, curr.moves + 1));
				}
			}
		}
		return -1;
	}
}