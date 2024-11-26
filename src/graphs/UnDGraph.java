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
public class UnDGraph<E> {

	private Vertex<E>[] vertexs;
	private boolean[][] adjMat;
	private int noOfVertex;
	private int size;

	/**
	 * @param noOfVertex
	 */
	@SuppressWarnings("unchecked")
	public UnDGraph(int noOfVertex) {
		super();
		this.noOfVertex = noOfVertex;
		this.vertexs = new Vertex[this.noOfVertex];
		adjMat = new boolean[this.noOfVertex][this.noOfVertex];
		this.size = 0;
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

		final boolean[][] newAdjMat = new boolean[noOfVertex * 2][noOfVertex * 2];

		for (int i = 0; i < noOfVertex; i++)
			for (int j = 0; j < noOfVertex; j++)
				newAdjMat[i][j] = adjMat[i][j];

		adjMat = newAdjMat;

		noOfVertex = noOfVertex << 1;
	}

	public void addEdge(int start, int end) {
		if (start > size || end > size)
			throw new IllegalStateException("vertex not present");
		adjMat[start][end] = true;
		adjMat[end][start] = true;

		// to maintain in/out degree
		vertexs[start].outDegree++;
		vertexs[start].inDegree++;

		vertexs[end].outDegree++;
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

	static class Vertex<E> {
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
			return "Vertex [e=" + e + "]";
		}
	}

	/**
	 * This function solves the Hamiltonian Cycle problem using Backtracking. It
	 * mainly uses hamCycleUtil() to solve the problem. It returns false if
	 * there is no Hamiltonian Cycle possible, otherwise return true and prints
	 * the path. Please note that there may be more than one solutions, this
	 * function prints one of the feasible solutions.
	 */
	public void hamiltonianPath() {
		int[] path = new int[noOfVertex];
		for (int i = 0; i < noOfVertex; i++)
			path[i] = -1;

		final boolean[] hamPath = new boolean[noOfVertex];

		// Let us put vertex 0 as the first vertex in the path. If there is a
		// Hamiltonian Cycle, then the path can be started from any point of the
		// cycle as the graph is undirected

		path[0] = 0;
		hamPath[0] = true;
		if (!hamPathUtil(path, 1, hamPath)) {
			System.err.println("no hamiltonian path..");
			return;
		}
		System.out.println(Arrays.toString(path));
	}

	/**
	 * A recursive utility function to solve hamiltonian cycle problem
	 * 
	 * @param path
	 * @param i
	 * @return
	 */
	private boolean hamPathUtil(int[] path, int pos, boolean[] hamPath) {

		// base case: If all vertices are included in Hamiltonian Cycle
		if (pos == noOfVertex) {
			// And if there is an edge from the last included vertex to the
			// first vertex
			if (adjMat[path[pos - 1]][path[0]])
				return true;
			else
				return false;
		}

		// Try different vertices as a next candidate in Hamiltonian Cycle. We
		// don't try for 0 as we included 0 as starting point in in hamCycle()

		for (int v = 1; v < noOfVertex; v++) {

			// Check if this vertex can be added to Hamiltonian Cycle

			if (isSafe(v, path, pos, hamPath)) {
				path[pos] = v;

				// recur to construct rest of the path
				if (hamPathUtil(path, pos + 1, hamPath))
					return true;

				// If adding vertex v doesn't lead to a solution, then remove it
				path[pos] = -1;
			}

		}
		// If no vertex can be added to Hamiltonian Cycle constructed so far,
		// then return false
		return false;
	}

	/**
	 * 
	 * A utility function to check if the vertex v can be added at index 'pos'
	 * in the Hamiltonian Cycle constructed so far (stored in 'path[]')
	 * 
	 * @param v
	 * @param path
	 * @param pos
	 * @param hamPath
	 * @return
	 */
	private boolean isSafe(int v, int[] path, int pos, boolean[] hamPath) {
		/*
		 * Check if this vertex is an adjacent vertex of the previously added
		 * vertex.
		 */
		if (!adjMat[path[pos - 1]][v])
			return false;

		// Check if the vertex has already been included.
		if (hamPath[v])
			return false;
		else {
			hamPath[v] = true;
			return true;
		}
	}
	
	
	/**
	 * To check whether graph has a cycle in it or not.
	 * 
	 * @return true if graph has a cycle else false
	 */
	
	// We use DFS for this.
	public boolean isCyclic() {
		
		for (int i = 0; i < vertexs.length; i++) {
			
			if (!vertexs[i].isVisited && isCyclicUtil(i)) {
				unVisitVertexes();
				return true;
			}
		}
		
		unVisitVertexes();
		return false;
	}
	
	private boolean isCyclicUtil(final int i) {
		
		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<>();
		final boolean[] inStack = new boolean[this.noOfVertex];
		
		stack.addFirst(i);
		inStack[i] = true;
		vertexs[i].isVisited = true;
		int curr = stack.poll();
		int next;
		
		while (!stack.isEmpty()) {
			
			curr = stack.poll();
			while ((next = nextAdjVertex(curr)) != -1) {
				stack.addFirst(next);
				
				if (inStack[next]) return true; // if the next is already present, then we have a cycle
				else inStack[next] = true;
				
				vertexs[next].isVisited = true; // mark next as visited
			}
			
			// make current node as false since it is not cyclic
			inStack[curr] = false;
		}
		
		return false;
	}

	/**
	 * @param curr index of current vertex
	 * @return index of next adjacent vertex, if no adjacent vertex is present then return -1
	 */
	private int nextAdjVertex(final int curr) {
		
		for (int i = 0; i < noOfVertex; i++) {
			if (adjMat[curr][i]) return i;
		}
		
		return -1;
	}

	/**
	 * un-visit the vertexes
	 */
	private void unVisitVertexes() {
		// make all vertexes un-visited
		for (int i = 0; i < noOfVertex; i++)
			vertexs[i].isVisited = false;
	}
	
	
	// bipartite graph
	
	/**
	 * To check if graph is bipartite or not.
	 * 
	 * A Bipartite Graph is a graph whose vertices can be divided into two
	 * independent sets, U and V such that every edge (u, v) either connects a
	 * vertex from U to V or a vertex from V to U. In other words, for every
	 * edge (u, v), either u belongs to U and v to V, or u belongs to V and v to
	 * U. We can also say that there is no edge that connects vertices of same
	 * set.
	 * 
	 * @return true if graph is bipartite else false
	 */
	public boolean isBipartite() {
		
		// Create a color array to store colors assigned to all veritces.
		// Vertex/ number is used as index in this array. The value '-1' of
		// colorArr[i] is used to indicate that no color is assigned to vertex
		// 'i'. The value 1 is used to indicate first color is assigned and
		// value 0 indicates second color is assigned.
		final int[] colorArray = new int[this.noOfVertex];
		Arrays.fill(colorArray, -1);
		
		for (int i = 0; i < this.noOfVertex; i++) {
			if (colorArray[i] == -1) {
				if (!isBipartite(i, colorArray)) return false;
			}
		}
		System.out.println(Arrays.toString(colorArray));
		return true;
	}
	
	// perform BFS to check whether graph is bipartite or not. 0 for red, 1 for blue
	private boolean isBipartite(final int i, final int[] colorArray) {
		
		final DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
		
		colorArray[i] = 1; // source is blue
		
		queue.offer(i);
		
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			
			// check for all its adjacent nodes
			
			for (int j = 0; j < this.noOfVertex; j++) {
				// there exists edge and no color is still applied
				if (adjMat[curr][j] && colorArray[j] == -1) {
					// apply opposite color
					colorArray[j] = 1 - colorArray[curr];
					queue.offer(j);
				}
				
				// same color
				else if (adjMat[curr][j] && colorArray[curr] == colorArray[j]) {
					return false;
				}
			}
		}
		
		// true if reached here
		return true;
	}
	
	
	/**
	 * Backtracking Algorithm : The idea is to assign colors one by one to
	 * different vertices, starting from the vertex 0. Before assigning a color,
	 * we check for safety by considering already assigned colors to the
	 * adjacent vertices. If we find a color assignment which is safe, we mark
	 * the color assignment as part of solution. If we do not a find color due
	 * to clashes then we backtrack and return false.
	 * 
	 * @param m no of different types of colors
	 * @return true if colors can be applied else false
	 */
	
	/*
	 * This function solves the m Coloring problem using Backtracking. It mainly
	 * uses graphColoringUtil() to solve the problem. It returns false if the m
	 * colors cannot be assigned, otherwise return true and prints assignments
	 * of colors to all vertices. Please note that there may be more than one
	 * solutions, this function prints one of the feasible solutions.
	 */
	public boolean graphColoring(final int m) {
		
		final int[] color = new int[this.noOfVertex];
		Arrays.fill(color, 0);
		
		if (!graphColoringUtil(m, color, 0)) {
			System.err.println("No solution");
			return false;
		}
		
		for (int i = 0; i < this.noOfVertex; i++)
            System.out.print(" " + color[i] + " ");
        System.out.println();
		
		return true;
	}

	// uses BFS before coloring the current vertex
	private boolean graphColoringUtil(final int m, final int[] color, final int vertex) {
		
		// all vertexes are colored
		if (vertex == this.noOfVertex) {
			return true;
		}
		
		for (int c = 1; c <= m; c++) {
			// Check if assignment of color c to vertex is fine
			if (isSafe(vertex, color, c)) {
				color[vertex] = c;
				
				// now check for adjacent nodes, whether we can apply other colors or not
				if (graphColoringUtil(m, color, vertex+1)) {
					return true;
				}
				
				// if not applicable, marks this node as un-colored
				color[vertex] = 0;
			}
		}
		
		return false;
	}
	
	
	/*
	 * A utility function to check if the current color assignment is safe for
	 * vertex v. This is checking all neighbors for there colors. Search is BSF
	 */
	private boolean isSafe(final int vertex, final int[] color, final int c) {
		for (int i = 0; i < this.noOfVertex; i++)
			if (adjMat[vertex][i] && c == color[i])
				return false;
		return true;
	}
	
	/**
	 * The idea is to consider the given snake and ladder board as a directed
	 * graph with number of vertices equal to the number of cells in the board.
	 * The problem reduces to finding the shortest path in a graph. Every vertex
	 * of the graph has an edge to next six vertices if next 6 vertices do not
	 * have a snake or ladder. If any of the next six vertices has a snake or
	 * ladder, then the edge from current vertex goes to the top of the ladder
	 * or tail of the snake. Since all edges are of equal weight, we can
	 * efficiently find shortest path using Breadth First Search of the graph.
	 * 
	 * @return
	 */
	// The input is represented by two things, first is 'N' which is number of
	// cells in the given board, second is an array 'move[0..N-1]' of size N. An
	// entry move[i] is -1 if there is no snake and no ladder from i, otherwise
	// move[i] contains index of destination cell for the snake or the ladder at
	// i.
	public int snakeAndLadder(final int[] moves) {
		
		// for snakes and ladders
		final int n = moves.length;
		final boolean[] visited = new boolean[n];
		
		final DoubleLinkedList<QueueEntry> queue = new DoubleLinkedList<>();
		
		// added first element
		queue.offer(new QueueEntry(0, 0)); // first vertex has 0 index and 0 distance from itself
		visited[0] = true; // mark first as visited
		
		QueueEntry curr = null;
		
		while (!queue.isEmpty()) {
			
			curr = queue.poll();
			int v = curr.v;
			
			// If front vertex is the destination vertex, we are done
	        if (v == n-1)
	            break; // break while loop, curr has answer
			
			// run to Vertex + 6 i.e. max number of dice
			for (int i = v + 1; i <= v + 6 && i < n; i++) {
				
				// ignore visited nodes
				if (!visited[i]) {
					
					final QueueEntry next = new QueueEntry();
					next.dist = curr.dist + 1; // only one more step away from current node
					visited[i] = true;
					
					// if it has snake or ladder
					if (moves[i] != -1) {
						next.v = moves[i];
					} else {
						next.v = i; // else next is i vertex
					}
					queue.offer(next); // add to queue
				}
			}
		}
		
		return curr.dist;
	}
}