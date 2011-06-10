package practice.test;

import practice.PrimeSoccer;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test some simple cases.
 * 
 * @author $Author: anselm $
 * @version $Revision: 280 $ $Date: 2008-11-16 12:25:20 +0100 (Sun, 16 Nov 2008) $
 */
public class PrimeSoccerTest {
	
	private PrimeSoccer soccer;

	@Before public void setUp() {
		soccer = new PrimeSoccer();
	}

	@Test public void example0() {
		assertTrue(soccer.getProbability(50, 50) == 0.5265618908306351);
	}
	
	/**
	 * Both teams will score a goal in each interval, so the final result will
	 * be 1.0 if the number of intervals is prime, otherwise 0.0.
	 */
	@Test public void example1() {
		assertTrue(soccer.getProbability(100, 100) == 0.0);
	}
	
	@Test public void example2() {
		assertTrue(soccer.getProbability(12, 89) == 0.6772047168840168);
	}
	
}
