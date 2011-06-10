package practice.test;

import practice.BinaryCode;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test some simple cases.
 * 
 * @author $Author: anselm $
 * @version $Revision: 282 $ $Date: 2008-11-16 12:27:10 +0100 (Sun, 16 Nov 2008) $
 */
public class BinaryCodeTest {

	private BinaryCode code;
	private String[] decrypt;
	
	private void exampleTest(String message, String decrypt1, String decrypt2) {
		decrypt = code.decode(message);
		assertTrue(decrypt[0].equals(decrypt1));
		assertTrue(decrypt[1].equals(decrypt2));
	}
	
	@Before public void setUp() {
		code = new BinaryCode();
	}
	
	@Test public void example0() {
		this.exampleTest("123210122", "011100011", "NONE");
	}

	@Test public void example1() {
		this.exampleTest("11", "01", "10");
	}

	@Test public void example2() {
		this.exampleTest("22111", "NONE", "11001");
	}

	@Test public void example3() {
		this.exampleTest("123210120", "NONE", "NONE");
	}

	@Test public void example4() {
		this.exampleTest("3", "NONE", "NONE");
	}

	@Test public void example5() {
		this.exampleTest(
			"12221112222221112221111111112221111",
			"01101001101101001101001001001101001",
			"10110010110110010110010010010110010"
		);
	}
}
