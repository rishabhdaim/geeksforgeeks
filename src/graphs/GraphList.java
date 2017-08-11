/**
 * 
 */
package graphs;

import java.util.Arrays;

import linkedlists.DoubleLinkedList;

/**
 * This is adjacency list representation of graph. We store graph vertexes in an array and edges are stored in array of {@link DoubleLinkedList}
 * 
 * @author Rishabh.Daim
 *
 */
public class GraphList {
	
	private int[] vertexes;
	private int[] inDegree;
	private DoubleLinkedList<Integer>[] edges;
	private int size;
	private int noOfVertexes;
	private boolean directed;
	
	/**
	 * @param n no. of vertexes in graph
	 * @param directed whether graph is directed or not
	 */
	@SuppressWarnings("unchecked")
	public GraphList(final int n, final boolean directed) {
		this.vertexes = new int[n]; // vertexes array
		this.inDegree = new int[n]; // inDegree array
		this.edges = new DoubleLinkedList[n];
		this.noOfVertexes = n;
		this.size = 0; // initial size is zero
		for (int i = 0; i < edges.length; i++) {
			this.edges[i] = new DoubleLinkedList<>();
		}
		Arrays.fill(vertexes, -1);
		Arrays.fill(inDegree, 0);
		this.directed = directed;
	}

	/**
	 * to add vertex to graph
	 * 
	 * @param e vertex to be added
	 */
	public void addVertex(final int e) {
		
		if (size == noOfVertexes)
			grow(); // increase the size of graph
		
		vertexes[size++] = e; // add vertex and increase size of graph
	}

	/**
	 * to increase the size of graph
	 */
	@SuppressWarnings("unchecked")
	private void grow() {
		
		// copy vertexes
		final int[] newVertexes = new int[noOfVertexes << 1]; // increase size 2 times
		Arrays.fill(newVertexes, -1);
		
		System.arraycopy(vertexes, 0, newVertexes, 0, noOfVertexes); // copy vertex array to new one
		vertexes = newVertexes;
		
		// copy indegree
		final int[] newInDegree = new int[noOfVertexes << 1]; // increase size 2 times
		Arrays.fill(newInDegree, 0);
		
		System.arraycopy(inDegree, 0, newInDegree, 0, noOfVertexes); // copy indegree array to new one
		inDegree = newInDegree;
		
		// copy edges
		final DoubleLinkedList<Integer>[] newEdges = new DoubleLinkedList[noOfVertexes << 1];
		for (int i = 0; i < newEdges.length; i++) {
			newEdges[i] = new DoubleLinkedList<>();
		}
		
		for (int i = 0; i < noOfVertexes; i++) {
			final DoubleLinkedList<Integer> adjList = edges[i];
			if (adjList.size() > 0)
				for (Integer e : adjList) 
					newEdges[i].add(e);
		}
		edges = newEdges;
		
		noOfVertexes = noOfVertexes << 1; // increase no. of vertexes
	}
	
	/**
	 * To create an edge between 2 vertexes
	 * 
	 * @param src source of edge
	 * @param dest destination of edge
	 */
	public void addEdge(final int src, final int dest) {
		
		if (src < 0 || src > noOfVertexes || dest < 0 || dest > noOfVertexes) {
			throw new IllegalArgumentException("vertex doesn't exists");
		}
		
		edges[src].add(dest);
		inDegree[dest]+= 1; // increase indegree of destination
		if (!directed) {
			edges[dest].add(src);
			inDegree[src]+= 1;
		}
	}
	
	
	
	/**
	 * Depth first search of graph
	 */
	public void dfs(final int src) {
		
		if (size == 0 || src < 0 || src > size || src > noOfVertexes || vertexes[src] ==  -1) return;
		
		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<>();
		final boolean[] visited = new boolean[noOfVertexes];
		
		stack.addFirst(src); // add src to stack for dfs starting
		visited[src] = true; // mark this as visited
		
		while (!stack.isEmpty()) {
			
			final int curr = stack.poll();
			System.out.print(curr + " ");
			
			final DoubleLinkedList<Integer> edgeList = edges[curr];
			
			for (Integer element : edgeList) {
				
				// if not visited before
				if (!visited[element]) {
					stack.addFirst(element); // add element to stack
					visited[element] = true; // mark this as visited
				}
			}
		}
		System.out.println();
	}
	
	/**
	 * Breadth first search of graph
	 */
	public void bfs(final int src) {
		
		if (size == 0 || src < 0 || src > size || src > noOfVertexes || vertexes[src] ==  -1) return;
		
		final DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
		final boolean[] visited = new boolean[noOfVertexes];
		
		queue.add(vertexes[src]); // add src to stack for dfs starting
		visited[src] = true; // mark this as visited
		
		while (!queue.isEmpty()) {
			
			final int curr = queue.poll();
			System.out.print(curr + " ");
			
			final DoubleLinkedList<Integer> edgeList = edges[curr];
			
			for (Integer element : edgeList) {
				
				// if not visited before
				if (!visited[element]) {
					queue.add(element); // add element to stack
					visited[element] = true; // mark this as visited
				}
			}
		}
		System.out.println();
	}
	
	
	/**
	 * To print all possible paths from source & destination
	 * 
	 * The idea is to do Depth First Traversal of given directed graph. Start
	 * the traversal from source. Keep storing the visited vertices in an array
	 * say ‘path[]’. If we reach the destination vertex, print contents of
	 * path[]. The important thing is to mark current vertices in path[] as
	 * visited also, so that the traversal doesn’t go in a cycle.
	 * 
	 * @param src
	 *            source of path
	 * @param dest
	 *            destination of path
	 */
	public void printAllPaths(final int src, final int dest) {
		
		if (src < 0 || src > size || dest < 0 || dest > size) return;
		
		if (src == dest) {
			System.out.println("same node");
			return;
		}
		
		final boolean[] visited = new boolean[noOfVertexes];
		final int[] path = new int[noOfVertexes]; // to store path from source to destination
		int pathIndex = 0;
		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<>();
		
		printAllPathsUtil(visited, path, pathIndex, stack, src, dest);
		
		
	}
	
	/**
	 * @param visited visited elements
	 * @param path to path elements in path
	 * @param pathIndex length of path
	 * @param stack stack to store visited elements
	 * @param src source of path
	 * @param dest destination of path
	 */
	private void printAllPathsUtil(final boolean[] visited, final int[] path, int pathIndex, final DoubleLinkedList<Integer> stack, final int src, final int dest) {
		stack.addFirst(src); // add source
		visited[src] = true; // mark as visited
		path[pathIndex++] = src;
		
		// reached destination 
		if (src == dest) {
			System.out.print("Path is : ");
			for (int i = 0; i < pathIndex; i++) {
				System.out.print(path[i]+ " ");
			}
			System.out.println();
		} else {
			final DoubleLinkedList<Integer> adjList = edges[src];
			for (Integer e : adjList) {
				// not visited already
				if (!visited[e]) {
					printAllPathsUtil(visited, path, pathIndex, stack, e, dest);
				}
			}
		}
		// remove curr from path & mark unvisited
		pathIndex--;
		visited[src] = false;
	}

	@Override
	public String toString() {
		return Arrays.toString(vertexes);
	}
	
	public void printAdjList() {
		for (DoubleLinkedList<Integer> doubleLinkedList : edges) {
			System.out.println(doubleLinkedList);
		}
	}

	/**
	 * To check whether destination is reachable from source or not. Idea is to use bfs.
	 * 
	 * @param src
	 * @param dest
	 * @return true if destination is reachable from src
	 */
	public boolean isReachable(final int src, final int dest) {
		
		if (src < 0 || src > size || dest < 0 || dest > size) {
			return false;
		}
		
		final boolean[] visited = new boolean[noOfVertexes];
		
		final DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
		queue.add(src); // add source in queue
		visited[src] = true; // mark source as visited
		
		while (!queue.isEmpty()) {
			
			final int curr = queue.poll();
			// if curr is destination
			if (curr == dest) {
				return true;
			} else {
				final DoubleLinkedList<Integer> adjList = edges[curr];
				for (Integer e : adjList) {
					if (!visited[e]) { // if not visited, add in queue and mark visited
						queue.add(e);
						visited[e] = true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * To find path from src to dest using bi directional search.
	 * 
	 * Bidirectional search is a graph search algorithm which find smallest path form source to goal vertex. It runs two simultaneous search – 
	 * 1) Forward search form source/initial vertex toward goal vertex
	 * 2) Backward search form goal/target vertex toward source vertex
	 * 
	 * @param src source of journey
	 * @param dest destination of journey
	 */
	public void biDirectionalSearch(final int src, final int dest) {
		
		if (src < 0 || src > size || dest < 0 || dest > size) return;
		
		if (src == dest) {
			System.out.println("same node");
			return;
		}
		
		int intersecting = -1;
		
		// to check nodes visited from source in front direction
		final boolean[] srcVisited = new boolean[noOfVertexes];
		final DoubleLinkedList<Integer> srcQueue = new DoubleLinkedList<>();
		final int[] srcParentPath = new int[noOfVertexes];
		Arrays.fill(srcParentPath, -1);
		srcQueue.add(src);
		srcVisited[src] = true;
		srcParentPath[src] = -1; // no parent of source

		// to check nodes visited from dest in back direction
		final boolean[] destVisited = new boolean[noOfVertexes];
		final DoubleLinkedList<Integer> destQueue = new DoubleLinkedList<>();
		final int[] destParentPath = new int[noOfVertexes];
		Arrays.fill(destParentPath, -1);
		destQueue.add(dest);
		destVisited[dest] = true;
		destParentPath[dest] = -1; // no parent of dest
		
		while (!srcQueue.isEmpty() && !destQueue.isEmpty()) {
			bfsUtil(srcQueue, srcVisited, srcParentPath);
			bfsUtil(destQueue, destVisited, destParentPath);
			
			intersecting = isIntersecting(srcVisited, destVisited);
			
			// we have visited common node
			if (intersecting != -1) {
				printPath(src, srcParentPath, dest, destParentPath, intersecting);
				return;
			}
		}
	}
	
	
	/**
	 * To print path from source to destination
	 * 
	 * @param src source node
	 * @param srcParentPath path of parent nodes from nodes visited from source
	 * @param dest destination node
	 * @param destParentPath path of parent nodes from nodes visited from destination
	 * @param intersecting intersecting node index
	 */
	private void printPath(final int src, final int[] srcParentPath, final int dest, final int[] destParentPath, final int intersecting) {
		
		final DoubleLinkedList<Integer> pathList = new DoubleLinkedList<>();
		int parent = intersecting;
		
		// for path from source, populate in list as stack
		while (parent != src) {
			pathList.addFirst(parent);
			parent = srcParentPath[parent];
		}
		
		pathList.addFirst(src); // add source
		
		parent = destParentPath[intersecting];
		// for path to destination, populate in list as queue
		while (parent != dest) {
			pathList.add(parent);
			parent = destParentPath[parent];
		}
		
		pathList.add(dest); // add source
		
		for (Integer e : pathList) {
			System.out.print(e + " ");
		}
		System.out.println();
	}

	/**
	 * Utility to check whether we have visted one common nodes or not
	 * 
	 * @param srcVisited visited nodes from source
	 * @param destVisited visited nodes from destination
	 * @return nodes index if we visited common nodes else -1
	 */
	private int isIntersecting(boolean[] srcVisited, boolean[] destVisited) {
		
		for (int i = 0; i < vertexes.length; i++)
			if (srcVisited[i] && destVisited[i])
				return i;
		
		return -1;
	}

	/**
	 * Utility for BFS
	 * 
	 * @param queue queue for BFS
	 * @param visited visited nodes of graph
	 * @param parentPath parent element of node
	 */
	private void bfsUtil(final DoubleLinkedList<Integer> queue, final boolean[] visited, final int[] parentPath) {
		
		final int curr = queue.poll();
		
		final DoubleLinkedList<Integer> edgeList = edges[curr];
		
		for (Integer e : edgeList) {
			
			if (!visited[e]) {
				visited[e] = true;
				parentPath[e] = curr;
				queue.add(e);
			}
		}
	}

	
	/**
	 * Depth First Traversal can be used to detect cycle in a Graph. DFS for a
	 * connected graph produces a tree. There is a cycle in a graph only if
	 * there is a back edge present in the graph. A back edge is an edge that is
	 * from a node to itself (selfloop) or one of its ancestor in the tree
	 * produced by DFS.
	 * 
	 * To detect a back edge, we can keep track of vertices currently in
	 * recursion stack of function for DFS traversal. If we reach a vertex that
	 * is already in the recursion stack, then there is a cycle in the tree. The
	 * edge that connects current vertex to the vertex in the recursion stack is
	 * back edge. We have used recStack[] array to keep track of vertices in the
	 * recursion stack
	 * 
	 * @return true if graph has cycle else false
	 */
	public boolean isCyclicDirected() {
		
		final boolean[] visited = new boolean[noOfVertexes];
		final boolean[] recStack = new boolean[noOfVertexes];
		
		for (int i = 0; i < vertexes.length; i++) {
			if (!visited[i] && isCyclicUtilDirected(i, visited, recStack))
				return true;
		}
		
		return false;
	}

	/**
	 * @param src source of traversing
	 * @param visited array of already visited nodes
	 * @param recStack elements in this recursion cycle
	 * @return true if we have cycle, else false
	 */
	// this is modified DFS, we visit child's of first child first rather than visiting them all child first and start visiting from last one
	private boolean isCyclicUtilDirected(final int src, final boolean[] visited, final boolean[] recStack) {
		
		visited[src] = true;
		recStack[src] = true;
		for (Integer e : edges[src]) {
			// only visit if not visited already
			
			if (recStack[e]) return true;
			else recStack[e] = true;
			
			if (!visited[e] && isCyclicUtilDirected(e, visited, recStack)) { // if any child is not visited, visit it first
				return true;
			}
		}
		// mark src as not present in recStack since we have visited all elements of this
		recStack[src] = false;
		return false;
	}
	
	/**
	 * The idea is to do DFS of given graph and while doing traversal, assign one of the below three colors to every vertex.
	 * 
	 * WHITE : 
	 * 			Vertex is not processed yet.  Initially all vertices are WHITE.
	 * 
	 * GRAY : 
	 * 			Vertex is being processed (DFS for this vertex has started, but not finished which means that all descendants 
	 * 			(in DFS tree) of this vertex are not processed yet (or this vertex is in function call stack)
	 * 
	 * BLACK : 
	 * 			Vertex and all its descendants are processed.
	 * 
	 * While doing DFS, if we encounter an edge from current vertex to a GRAY vertex, then this edge is back edge and hence there is a cycle.
	 * 
	 * @return true if graph has cycle else false
	 */
	
	private enum Color { WHITE, GRAY, BLACK };
	
	// gray means node is in recursive stack, white means not visited & black means its recursion tree has been done
	public boolean isCyclicColor() {
		
		final Color[] stack = new Color[noOfVertexes];
		Arrays.fill(stack, Color.WHITE); // no nodes visited yet
		
		for (int v : vertexes) {
			if (stack[v] == Color.WHITE && isCyclicColorUtil(v, stack))
				return true;
		}
		
		return false;
	}

	/**
	 * @param src source of current node whose child are to be visited
	 * @param stack Color stack to store recursion stack
	 * @return true if cycle else false
	 */
	private boolean isCyclicColorUtil(final int src, final Color[] stack) {
		
		stack[src] = Color.GRAY; // node in recursion stack
		
		// iterate all elements reachable from source
		for (Integer e : edges[src]) {
			if (stack[e] == Color.WHITE && isCyclicColorUtil(e, stack)) {
				return true;
			} else if (stack[e] == Color.GRAY) { // node in recursion stack
				return true;
			}
		}
		
		stack[src] = Color.BLACK; // no cycle for source, mark it as black i.e. visited and no cycle for this
		return false;
	}

	/**
	 * A disjoint-set data structure is a data structure that keeps track of a set of elements partitioned into a number of disjoint (non-overlapping) subsets. 
	 * A union-find algorithm is an algorithm that performs two useful operations on such a data structure:
	 * Find: Determine which subset a particular element is in. This can be used for determining if two elements are in the same subset.
	 * Union: Join two subsets into a single subset.
	 * Union-Find Algorithm can be used to check whether an undirected graph contains cycle or not. This method assumes that graph doesn’t contain any self-loops.
	 * We can keeps track of the subsets in a 1D array, lets call it parent[]
	 * 
	 * @return true if graph has cycle else false
	 */
	public boolean isCyclicUnionFind() {
		
		final int[] parent = new int[noOfVertexes]; // to store parent of vertex
		Arrays.fill(parent, -1); // no parent has found yet
		
		for (int i = 0; i < edges.length; i++) {
			for (Integer e : edges[i]) {
				int x = parent(parent, i);
				int y = parent(parent, e);
				
				if (x == y) return true; // cycle exists
				
				union(parent, x, y);
			}
		}
		
		return false;
	}

	/**
	 * To join parent of source (x) & destination (y) nodes
	 * 
	 * @param parent array of parent nodes
	 * @param x parent of source node
	 * @param y parent of destination node
	 */
	private void union(final int[] parent, final int x, final int y) {
		
		final int xParent = parent(parent, x);
		final int yParent = parent(parent, y);
		
		parent[xParent] = yParent; // make parent of destination as parent of source
	}

	/**
	 * To get parent of given node
	 * 
	 * @param parent array of parent nodes
	 * @param i index of node whose parent is to be found
	 * @return parent of i index node
	 */
	private int parent(final int[] parent, final int i) {
		
		if (parent[i] == -1) return i; // no parent is present
		
		return parent(parent, parent[i]); // get parent of parent[i]
	}
	
	/**
	 * This graph uses Rank along with Union-Find algo
	 * 
	 * @return true is graph has cycle else false
	 */
	// The normal union() and find() are naive and the worst case time
	// complexity is linear. The trees created to represent subsets can be
	// skewed and can become like a linked list.
	// The idea is to always attach smaller depth tree under the root of the
	// deeper tree. This technique is called union by rank. The term rank is
	// preferred instead of height because if path compression technique (we
	// have discussed it below) is used, then rank is not always equal to
	// height. Also, size (in place of height) of trees can also be used as
	// rank. Using size as rank also yields worst case time complexity as
	// O(Logn)
	// The second optimization to naive method is Path Compression. The idea is
	// to flatten the tree when find() is called. When find() is called for an
	// element x, root of the tree is returned. The find() operation traverses
	// up from x to find root. The idea of path compression is to make the found
	// root as parent of x so that we don’t have to traverse all intermediate
	// nodes again. If x is root of a subtree, then path (to root) from all
	// nodes under x also compresses.
	public boolean isCyclicUnionFindRank() {
		
		final Subset[] subset = new Subset[noOfVertexes];
		for (int i = 0; i < subset.length; i++) 
			subset[i] = new Subset(i, 0); // make every node its parent and gave rank 0
		
		for (int i = 0; i < vertexes.length; i++) {
			for (Integer e : edges[i]) {
				int x = parent(subset, i);
				int y = parent(subset, e);
				
				if (x == y) return true;
				
				union(subset, x, y);
			}
		}
		return false;
	}

	/**
	 * To join to subsets represented by x & y, making subset with higher rank as parent of other
	 * 
	 * @param subset subset of nodes with parent and rank
	 * @param x subset with parent x
	 * @param y subset with parent y
	 */
	private void union(final Subset[] subset, final int x, final int y) {
		final int xParent = parent(subset, x);
		final int yParent = parent(subset, y);
		
		// xParent has higher rank, make yParent as its child
		if (subset[xParent].rank > subset[yParent].rank) {
			subset[yParent].parent = xParent;
		} else if (subset[xParent].rank < subset[yParent].rank) {
			subset[xParent].parent = yParent;
		} else {
			// make xParent as parent of yParent and increase its rank
			subset[yParent].parent = xParent;
			subset[xParent].rank += 1;
		}
	}

	/**
	 * @param subset Array of all nodes with parent and rank
	 * @param i nodes whose parent is to be find
	 * @return parent of i
	 */
	private int parent(final Subset[] subset, final int i) {
		
		// get parent of i. If parent is same as i return, else get parent of parent of i
		if (subset[i].parent == i)
			return subset[i].parent;
		
		return parent(subset, subset[i].parent);
	}

	/**
	 * To check cycle in undirected graph.
	 * 
	 * We do a DFS traversal of the given graph. For every visited vertex ‘v’,
	 * if there is an adjacent ‘u’ such that u is already visited and u is not
	 * parent of v, then there is a cycle in graph. If we don’t find such an
	 * adjacent for any vertex, we say that there is no cycle. The assumption of
	 * this approach is that there are no parallel edges between any two
	 * vertices.
	 * 
	 * @return true for cycle else false
	 */
	public boolean isCyclicUnDirected() {
		
		final boolean[] visited = new boolean[noOfVertexes];
		
		for (int i = 0; i < vertexes.length; i++) {
			
			if (!visited[i] && isCyclicUtilUnDirected(i, -1, visited)) return true;
		}
		
		return false;
	}

	/**
	 * @param src source node
	 * @param parent parent of source
	 * @param visited array of visited nodes
	 * @return true if graph has cycle
	 */
	private boolean isCyclicUtilUnDirected(final int src, final int parent, final boolean[] visited) {
		
		visited[src] = true; // mark source as visited
		
		for (Integer e : edges[src]) {
			
			// If an adjacent is not visited, then recur for that adjacent
			if (!visited[e]) {
				if (isCyclicUtilUnDirected(e, src, visited)) return true;
			}
			// If an adjacent is visited and not parent of current vertex, then there is a cycle
			else if (e != parent) return true;
		}
		
		return false;
	}

	/**
	 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering
	 * of vertices such that for every directed edge uv, vertex u comes before v
	 * in the ordering. Topological Sorting for a graph is not possible if the
	 * graph is not a DAG.
	 */
	public void topologicalSort() {
		
		final DoubleLinkedList<Integer> stack = new DoubleLinkedList<>(); // to store elements for print topological order
		final boolean[] visited = new boolean[noOfVertexes]; // mark visited nodes
		
		for (int i = 0; i < vertexes.length; i++)
			if (!visited[i])
				topologicalSortUtil(stack, visited, i);
		
		while (!stack.isEmpty()) {
			System.out.print(stack.poll() + " ");
		}
		System.out.println();
	}

	private void topologicalSortUtil(final DoubleLinkedList<Integer> stack, final boolean[] visited, final Integer src) {
		
		visited[src] = true; // mark it as visited
		
		for (Integer e : edges[src])
			if (!visited[e])
				topologicalSortUtil(stack, visited, e);
		
		stack.addFirst(src); // add element to stack
	}
	
	/**
	 * print all topological sorts of a graph
	 */
	public void allTopologicalSort() {
		
		final boolean[] visited = new boolean[noOfVertexes];
		final DoubleLinkedList<Integer> result = new DoubleLinkedList<>();

		allTopologicalSortUtil(visited, result);
	}

	private void allTopologicalSortUtil(final boolean[] visited, final DoubleLinkedList<Integer> result) {
		
		boolean flag = false;
		
		for (int i = 0; i < vertexes.length; i++) {
			// only visit if indegree is 0 & not visited yet
			if (inDegree[i] == 0 && !visited[i]) {
				
				for (Integer e : edges[i])
					inDegree[e]-=1; // reduce indegree of all reachable vertexes by 1
				
				result.add(i); // add in result
				visited[i] = true; // mark visited
				allTopologicalSortUtil(visited, result);
				
				// now backtrack
				result.remove(i);
				visited[i] = false;
				for (Integer e : edges[i])
					inDegree[e]+=1; // increase indegree of all reachable vertexes by 1
				flag = true;
			}
		}
		// We reach here if all vertices are visited. So we print the solution here
		if (!flag) {
			for (Integer e : result)
				System.out.print(e + " ");
			System.out.println();
		}
	}
	
	/**
	 * To print MST using Kruskal's algo. This also uses Union-Find-Rank algo
	 */
	public void kruskalMST() {
		
		final int[] parent = new int[noOfVertexes]; // result array to store MST
		
		final Subset[] subset = new Subset[noOfVertexes];
		for (int i = 0; i < subset.length; i++) {
			subset[i] = new Subset(i, 0); // make node its parent and start with zero rank
		}
		
		for (int i = 0; i < vertexes.length; i++) {
			for (Integer e : edges[i]) {
				final int x = parent(subset, i);
				final int y = parent(subset, e);
				
				// to avoid cycle
				if (x != y) {
					parent[e] = i; // make x as parent of y
					union(subset, x, y); // join to sub sets having x & y as heads
				}
			}
		}
		
		// print MST
    	System.out.println("Edge	Weight");
    	for (int i = 1; i < noOfVertexes; i++) {
			System.out.println(parent[i]+" - "+i+"  "+1);
		}
	}

	/**
	 * It uses Union-Find-Rank also to check both persons directly-indirectly know each other or not
	 * 
	 * Partitioning the individuals into different sets according to the groups in which they fall. 
	 * This method is known as disjoint set data structure which maintains collection of disjoint sets and 
	 * each set is represented by its representative which is one of its members.
	 * 
	 * @param a first person
	 * @param b second person
	 * @return true they know each other else false
	 */
	public boolean areKnown(final int a, final int b) {
		
		final Subset[] subset = new Subset[noOfVertexes];
		for (int i = 0; i < subset.length; i++)
			subset[i] = new Subset(i, 0); // every person himself
		
		for (int i = 0; i < vertexes.length; i++) {
			for (Integer e : edges[i]) {
				union(subset, i, e);
			}
		}
		return parent(subset, a) == parent(subset, b);
	}
}
