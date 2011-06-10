package practice;

import static java.lang.Integer.*;

/**
 * <h1>TopCoder SRM 144 DIV 1 300</h1>
 * 
 * <h2>Problem Statement</h2>
 * Let's say you have a binary string such as the following: 011100011. One way
 * to encrypt this string is to add to each digit the sum of its adjacent
 * digits. For example, the above string would become: 123210122. In particular,
 * if P is the original string, and Q is the encrypted string, then Q[i] =
 * P[i-1] + P[i] + P[i+1] for all digit positions i. Characters off the left
 * and right edges of the string are treated as zeroes. An encrypted string
 * given to you in this format can be decoded as follows
 * (using 123210122 as an example):
 * <ol>
 * <li>Assume P[0] = 0.</li>
 * <li>Because Q[0] = P[0] + P[1] = 0 + P[1] = 1, we know that P[1] = 1.</li>
 * <li>Because Q[1] = P[0] + P[1] + P[2] = 0 + 1 + P[2] = 2, we know that P[2] =
 * 1.</li>
 * <li>Because Q[2] = P[1] + P[2] + P[3] = 1 + 1 + P[3] = 3, we know that P[3] =
 * </li>1.
 * <li>Repeating these steps gives us P[4] = 0, P[5] = 0, P[6] = 0, P[7] = 1,
 * and P[8] = 1.</li>
 * <li>We check our work by noting that Q[8] = P[7] + P[8] = 1 + 1 = 2. Since
 * this equation works out, we are finished, and we have recovered one possible
 * original string.</li>
 * </ol>
 * Now we repeat the process, assuming the opposite about P[0]:
 * <ol>
 * <li>Assume P[0] = 1.</li>
 * <li>Because Q[0] = P[0] + P[1] = 1 + P[1] = 0, we know that P[1] = 0.</li>
 * <li>Because Q[1] = P[0] + P[1] + P[2] = 1 + 0 + P[2] = 2, we know that P[2] =
 * 1.</li>
 * <li>Now note that Q[2] = P[1] + P[2] + P[3] = 0 + 1 + P[3] = 3, which leads
 * us to the conclusion that P[3] = 2. However, this violates the fact that each
 * character in the original string must be '0' or '1'. Therefore, there exists
 * no such original string P where the first digit is '1'.</li>
 * </ol>
 * Note that this algorithm produces at most two decodings for any given
 * encrypted string. There can never be more than one possible way to decode a
 * string once the first binary digit is set. Given a String message, containing
 * the encrypted string, return a String[] with exactly two elements. The first
 * element should contain the decrypted string assuming the first character is
 * '0'; the second element should assume the first character is '1'. If one of
 * the tests fails, return the string "NONE" in its place. For the above
 * example, you should return {"011100011", "NONE"}.
 *
 * <h2>Definition</h2>
 * <dl>
 * <dt>Class</dt><dd>BinaryCode</dd>
 * <dt>Method</dt><dd>decode</dd>
 * <dt>Parameter</dt><dd>String</dd>
 * <dt>Returns</dt><dd>String[]</dd>
 * <dt>Method signature</dt><dd>String[] decode(String message)
 * (be sure your method is public)</dd>
 * </dl>
 * 
 * <h2>Constraints</h2>
 * <ul>
 * <li><code>message</code> will contain between 1 and 50 characters, inclusive.
 * </li>
 * <li>Each character in message will be either '0', '1', '2', or '3'.</li>
 * </ul>
 * 
 * @author $Author: anselm $
 * @version $Revision: 285 $ $Date: 2008-11-16 12:52:12 +0100 (Sun, 16 Nov 2008) $
 */
public class BinaryCode {

	/**
	 * Decode a binary string assuming it starts with the given start symbol.
	 * 
	 * @param start The assumed first symbol of the decoded string.
	 * @param message The encoded message.
	 * @return The decoded message, or the string "NONE" if decryption fails.
	 */
	private String decode(String start, String message) {
		if (message.length() <= 1) {
			if (message == start)
				return start;
			else
				return "NONE";
		}
		String decoded = start;
		int left = 0;
		int middle = Integer.parseInt(start);
		int right;
		int next;
		for (int i = 0; i < message.length() - 1; i++) {
			/* Yes, converting one character from a string to an integer is
			 * indeed complicated in Java. */
			next = parseInt((new Character(message.charAt(i))).toString());
			right = next - middle - left;
			if ((right != 0) && (right != 1))
				return "NONE";
			decoded += right;
			left = middle;
			middle = right;
		}
		next = parseInt((new Character(message.charAt(message.length() - 1))).toString());
		if (next != (left + middle))
			return "NONE";
		return decoded;
	}

	/**
	 * Decode a message.
	 * 
	 * @param message The encrypted message.
	 * @return An array of two strings. The first is the decryption of the
	 * message assuming it starts with '0', the second string assumes it starts
	 * with '1'. Each string can be "NONE" if decryption is not possible.
	 */
	public String[] decode(String message) {
		String decode0 = this.decode("0", message);
		String decode1 = this.decode("1", message);
		return new String[]{decode0, decode1};
	}

}
