/**
 * 
 */
package stack;

/**
 * @author apple
 * 
 */
public class InToPost {

	private ArrayStack<Character> theStack;
	private String input;
	private String output = "";

	public InToPost(String input) {
		this.input = input;
		theStack = new ArrayStack<Character>(input.length());
	}

	public String doTans() {
		{
			for (int j = 0; j < input.length(); j++) {
				char ch = input.charAt(j);
				switch (ch) {
				case '+': // itÕs + or -
				case '-':
					gotOper(ch, 1); // go pop operators
					break; // (precedence 1)
				case '*': // itÕs * or /
				case '/':
					gotOper(ch, 2); // go pop operators
					break; // (precedence 2)
				case '(': // itÕs a left paren
					theStack.push(ch); // push it
					break;
				case ')': // itÕs a right paren
					gotParen(ch); // go pop operators
					break;
				default: // must be an operand
					output = output + ch; // write it to output
				} // end switch
			} // end for
			while (!theStack.isEmpty())
				output = output + theStack.pop(); // write to output
			return output;
		}
	}

	public void gotOper(char opThis, int prec1) { // got operator from input
		while (!theStack.isEmpty()) {
			char opTop = theStack.pop();
			if (opTop == '(') {
				// if itÕs a Ô(Ô
				theStack.push(opTop); // restore Ô(Ô
				break;
			} else {
				// itÕs an operator
				int prec2; // precedence of new op
				if (opTop == '+' || opTop == '-') // find new op prec
					prec2 = 1;
				else
					prec2 = 2;
				if (prec2 < prec1) {
					// if prec of new op less than prec of old
					theStack.push(opTop); // save newly-popped op
					break;
				} else
					// prec of new not less
					output = output + opTop; // than prec of old
			} // end else (itÕs an operator)
		} // end while
		theStack.push(opThis);
	}

	public void gotParen(char ch) { // got right paren from input
		while (!theStack.isEmpty()) {
			char chx = theStack.pop();
			if (chx == '(') // if popped Ô(Ô
				break; // weÕre done
			else
				// if popped operator
				output = output + chx; // output it
		} // end while
	}
	
	public static void main(String[] args) {
		final String inFlix = "A*(B+C)-D/(E+F)";
		InToPost inToPost = new InToPost(inFlix);
		final String postFix = inToPost.doTans();
		System.out.println(postFix);
	}
}