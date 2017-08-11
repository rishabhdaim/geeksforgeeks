/**
 * 
 */
package arrays;

import java.util.Arrays;

/**
 * @author apple
 * 
 */
public class CaesarCipher {
	private static final int ALPHASIZE = 26;
	private static final char[] alpha = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z' };
	private final char[] encript = new char[ALPHASIZE];
	private final char[] decript = new char[ALPHASIZE];

	public CaesarCipher() {
		System.out.println(Arrays.toString(alpha));
		for (int i = 0; i < ALPHASIZE; i++)
			encript[i] = alpha[(i + 3) % ALPHASIZE];
		System.out.println(Arrays.toString(encript));
		for (int i = 0; i < ALPHASIZE; i++)
			decript[encript[i] - 'A'] = alpha[i];
		System.out.println(Arrays.toString(decript));
	}

	private String encrypt(String msg) {
		char[] mess = msg.toCharArray();
		for (int i = 0; i < mess.length; i++)
			if (Character.isUpperCase(mess[i]))
				mess[i] = encript[mess[i] - 'A'];
		return new String(mess);
	}

	private String decrypt(String secret) {
		char[] mess = secret.toCharArray();
		for (int i = 0; i < mess.length; i++)
			if (Character.isUpperCase(mess[i]))
				mess[i] = decript[mess[i] - 'A'];
		return new String(mess);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String msg = "My name is rishabh daim";
		msg = msg.toUpperCase();
		CaesarCipher caesarCipher = new CaesarCipher();
		System.out.println(msg);
		String secret = caesarCipher.encrypt(msg);
		System.out.println(secret);
		msg = caesarCipher.decrypt(secret);
		System.out.println(msg);

	}
}