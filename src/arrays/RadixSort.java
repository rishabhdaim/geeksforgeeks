/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author apple
 * 
 */
public class RadixSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] is = new int[100];
		for (int i = 0; i < is.length; i++)
			is[i] = (int) (Math.random() * 10000);
		int max = is[0];
		for (int i = 1; i < is.length; i++)
			if (is[i] > max)
				max = is[i];
		System.out.println(max);
		int l = (max + "").length();
		for (int i = 1; i <= l; i++)
			radixSort(is, (int) Math.pow(10, i - 1));
		System.out.println(Arrays.toString(is));
	}

	private static void radixSort(int[] is, int l) {
		Node[] nodes = new Node[10];
		int rem;
		int quotionent;
		for (int i : is) {
			quotionent = i / l;
			rem = quotionent % 10;
			Node node = new Node(i, null);
			if (nodes[rem] == null) // this index is empty..
				nodes[rem] = node;
			else {
				Node curr = nodes[rem];
				while (curr.next != null)
					curr = curr.next;
				curr.next = node;
			}
		}
		int i = 0;
		for (Node node : nodes) {
			while (node != null) {
				is[i++] = node.i;
				node = node.next;
			}
		}
	}

	private static class Node {
		int i;
		Node next;

		/**
		 * @param i
		 * @param next
		 */
		public Node(int i, Node next) {
			super();
			this.i = i;
			this.next = next;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Node [i=" + i + "]";
		}
	}
}