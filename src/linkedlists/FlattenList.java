/**
 * 
 */
package linkedlists;


/**
 * @author aa49442
 * 
 */
public class FlattenList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChildLinkedList<String> childLinkedList = new ChildLinkedList<String>();
		childLinkedList.add("a");
		childLinkedList.add("s");
		childLinkedList.add("d");
		childLinkedList.add("f");
		childLinkedList.add("g");
		childLinkedList.add("h");
		childLinkedList.add("j");
		childLinkedList.add("k");

		childLinkedList.addChild("a", "a1");
		childLinkedList.addChild("a", "a2");
		childLinkedList.addChild("a", "a3");
		childLinkedList.addChild("a", "a4");
		childLinkedList.addChild("a1", "a11");
		childLinkedList.addChild("a1", "a12");
		childLinkedList.addChild("s", "s1");
		childLinkedList.addChild("s", "s2");
		childLinkedList.addChild("s", "s3");
		childLinkedList.addChild("s1", "s11");
		childLinkedList.addChild("s1", "s12");
		childLinkedList.addChild("s11", "s111");
		childLinkedList.addChild("k", "k1");
		childLinkedList.addChild("k", "k2");
		childLinkedList.addChild("k", "k3");
		childLinkedList.addChild("k3", "k31");
		childLinkedList.addChild("k2", "k21");
		childLinkedList.addChild("k31", "k311");
		System.out.println(childLinkedList);
		
		childLinkedList.flattenList();

		System.out.println(childLinkedList);
	}
}