package practice.test;

import practice.Lottery;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author $Author: anselm $
 * @version $Revision: 288 $ $Date: 2008-11-19 17:23:46 +0100 (Wed, 19 Nov 2008) $
 */
public class LotteryTest {
	
	private Lottery lottery;
	
	@Before public void setUp() {
		this.lottery = new Lottery();
	}

	public void exampleTest(String[] rules, String[] names) {
		String[] sort = this.lottery.sortByOdds(rules);
		assertArrayEquals(names, sort);
	}
	
	@Test public void example0() {
		String rules[] = {
			"PICK ANY TWO: 10 2 F F",
			"PICK TWO IN ORDER: 10 2 T F",
			"PICK TWO DIFFERENT: 10 2 F T",
			"PICK TWO LIMITED: 10 2 T T"
		};
		String names[] = {
			"PICK TWO LIMITED",
			"PICK TWO IN ORDER",
			"PICK TWO DIFFERENT",
			"PICK ANY TWO"
		};
		this.exampleTest(rules, names);
	}
	
	@Test public void example1() {
		String rules[] = {
			"INDIGO: 93 8 T F",
			"ORANGE: 29 8 F T",
			"VIOLET: 76 6 F F",
			"BLUE: 100 8 T T",
			"RED: 99 8 T T",
			"GREEN: 78 6 F T",
			"YELLOW: 75 6 F F"
		};
		String names[] = {
			"RED",
			"ORANGE",
			"YELLOW",
			"GREEN",
			"BLUE",
			"INDIGO",
			"VIOLET"
		};
		this.exampleTest(rules, names);
	}
	
	@Test public void example2() {
		String rules[] = {};
		String names[] = {};
		this.exampleTest(rules, names);
	}
	
}
