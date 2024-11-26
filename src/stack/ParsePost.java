/**
 * 
 */
package stack;

/**
 * @author apple
 * 
 */
public class ParsePost {

	/**
	 * @param input
	 */
	public ParsePost(String input) {
		super();
		this.input = input;
		arrayStack = new ArrayStack<>();
	}

	private ArrayStack<Integer> arrayStack;
	private String input;

	public int doParse() {
		char ch;
		int j;
		int num1, num2, interAns;
		for (j = 0; j < input.length(); j++) // for each char,
		{
			ch = input.charAt(j); // read from input
			if (ch >= '0' && ch <= '9') // if it's a number
				arrayStack.push(ch - '0'); // push it
			else // it's an operator
			{
				num2 = arrayStack.pop(); // pop operands
				num1 = arrayStack.pop();
				switch (ch) {
				case '+':
					interAns = num1 + num2;
					break;
				case '-':
					interAns = num1 - num2;
					break;
				case '*':
					interAns = num1 * num2;
					break;
				case '/':
					interAns = num1 / num2;
					break;
				default:
					interAns = 0;
				} // end switch
				arrayStack.push(interAns); // push result
			} // end else
		} // end for
		interAns = arrayStack.pop(); // get answer
		return interAns;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String postfIx = "345+*612+/-";
		ParsePost parsePost = new ParsePost(postfIx);
		int answer = parsePost.doParse();
		System.out.println(answer);
	}

}
