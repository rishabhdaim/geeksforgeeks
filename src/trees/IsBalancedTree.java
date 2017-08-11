/**
 * 
 */
package trees;

/**
 * @author aa49442
 * 
 */
public class IsBalancedTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeList<Integer> list = new TreeList<Integer>();
		for (int i = 0; i < 20; i++)
			list.add((int) (Math.random() * 100));

		int h = list.heightOf(list.root());
		System.out.println();
		System.out.println("the height is : " + h);

		// list.levelOrderTraversal();

		boolean b = list.isBalacnedTree();

		System.out.println("the tree is : " + b);

		b = list.allLeafNodesSameLevel(list.root());

		System.out.println(b);

		int d = list.depthOfDeepestOddNode(list.root(), 1);

		System.out.println(d);
		d = list.depthOfDeepestEvenNode(list.root(), 1);

		System.out.println(d);

		d = list.diameter(list.root());
		System.out.println(d);

		d = list.diameterOpt(list.root());
		System.out.println(d);
		
		if((list.getMaxDepth(list.root().left,1)+ list.getMaxDepth(list.root().right,0))>list.maxHeight) {
		   list.maxHeight=list.getMaxDepth(list.root().left,1)+list.getMaxDepth(list.root().right,0);
	  }
		  System.out.println(list.maxHeight);
	}
}