package practice.test;

import practice.Time;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author $Author: anselm $
 * @version $Revision: 284 $ $Date: 2008-11-16 12:44:47 +0100 (Sun, 16 Nov 2008) $
 */
public class TimeTest {
	
	private Time time;
	
	@Before public void setUp() {
		time = new Time();
	}
	
	@Test public void example0() {
		assertTrue(time.whatTime(0).equals("0:0:0"));
	}
	
	@Test public void example1() {
		assertTrue(time.whatTime(3661).equals("1:1:1"));
	}
	
	@Test public void example2() {
		assertTrue(time.whatTime(5436).equals("1:30:36"));
	}
	
	@Test public void example3() {
		assertTrue(time.whatTime(86399).equals("23:59:59"));
	}
}
