/**
 * 
 */
package arrays;

/**
 * @author aa49442
 * 
 */
public class RearrangeArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] i = new Integer[10];
		for (int j = 0; j < i.length; j++)
			i[j] = (int) (Math.random() * 10);

		Array<Integer> arr = new Array<Integer>(i);

		System.out.println(arr);

		arr.rearrangeToArrOfArrIndex();

		System.out.println(arr);

		i = new Integer[] { 1, 1, 1, 1, 1, 1, 11, 2, 3, 4, 5, 6, -1, -2, -3,
				-3, -4, -5, -6, -6, -7 };
		arr = new Array<Integer>(i);

		System.out.println(arr);

		arr.rearrangePositiveAndNegative();

		System.out.println(arr);

		int l = 3, k = 4;
		i = new Integer[l * k];
		for (int j = 0; j < i.length; j++)
			i[j] = (int) (Math.random() * 100);
		arr = new Array<Integer>(i);
		System.out.println(arr);

		arr.transpose(l, k);
		System.out.println(arr);

		l = 2;
		k = 10;
		Character[] ch = new Character[l * k];
		for (int j = 0; j < 10; j++)
			ch[j] = (char) ('a' + j);
		for (int j = 10, m = 0; j < 20; j++, m++)
			ch[j] = (char) ('0' + m);

		Array<Character> array = new Array<Character>(ch);
		System.out.println(array);

		array.transpose(2, 10);

		System.out.println(array);

		System.out.println(arr);
		arr.randomize();

		System.out.println("random : " + arr);

		arr.rotateArrLeft(i.length, 5);

		System.out.println(arr);

		arr.rotateArrLeftItr(i.length, 4);

		System.out.println(arr);

		arr.rotateArrWithReversal(i.length, 5);
		System.out.println(arr);

		arr.reverseArrayRecur(0, i.length - 1);
		System.out.println(arr);
	}
}
