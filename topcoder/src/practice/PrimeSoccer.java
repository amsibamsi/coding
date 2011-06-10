package practice;

import static java.lang.Math.*;

/**
 * <h1>TopCoder SRM 422 DIV 1 250</h1>
 * 
 * <h2>Problem Statement</h2>
 * You are watching a soccer match, and you wonder what the probability is that
 * at least one of the two teams will score a prime number of goals. The game
 * lasts 90 minutes, and to simplify the analysis, we will split the match into
 * five-minute intervals. The first interval is the first five minutes, the
 * second interval is the next five minutes, and so on. During each interval,
 * there is a <code>skillOfTeamA</code> percent probability that team A will
 * score a goal, and a skillOfTeamB percent probability that teamB will score
 * a goal. Assume that each team will score at most one goal within each
 * interval. Return the probability that at least one team will have a prime
 * number as its final score.
 * 
 * <h2>Definition</h2>
 * <dl>
 * <dt>Class</dt><dd>PrimeSoccer</dd>
 * <dt>Method</dt><dd>getProbability</dd>
 * <dt>Parameters</dt><dd>int, int</dd>
 * <dt>Returns</dt><dd>double</dd>
 * <dt>Method signature</dt><dd>double getProbability(int skillOfTeamA, int
 * skillOfTeamB) (be sure your method is public)</dd>
 * </dl>
 * 
 * <h2>Notes</h2>
 * <ul>
 * <li>The returned value must be accurate to within a relative or absolute
 * value of 1E-9.</li>
 * <li>A prime number is a number that has exactly two divisors, 1 and itself.
 * Note that 0 and 1 are not prime.</li>
 * </ul>
 * 
 * <h2>Constraints</h2>
 * <ul>
 * <li><code>skillOfTeamA</code> will be between 0 and 100, inclusive.</li>
 * <li><code>skillOfTeamB</code> will be between 0 and 100, inclusive.</li>
 * </ul>
 * 
 * @author $Author: anselm $
 * @version $Revision: 283 $ $Date: 2008-11-16 12:44:13 +0100 (Sun, 16 Nov 2008) $
 */
public class PrimeSoccer {

	/**
	 * Number of intervals in one match.
	 */
	private int intervals = 18;

	/**
	 * Tests if the given number is prime.
	 * 
	 * @param n The number to test. (For not natural numbers as input the
	 * behavior is undefined.)
	 * @return If the given number is prime or not.
	 */
	private boolean isPrime(double n) {
		if (n <= 1) return false;
		if (n == 2) return true;
		if (n % 2 == 0) return false;
		double m = (double) round(Math.sqrt(n));
		for (int i = 3; i <= m; i += 2)
			if (n % i == 0)
				return false;
		return true;
	}

	/**
	 * Compute the prime goal probability for one team.
	 * 
	 * @param p The probability to score one goal in one interval.
	 * @return The probability (as floating-point number, not percentage) that
	 * a team scores a prime number of goals in the whole match, assumed that
	 * at each interval it scores one goal with the given probability.
	 */
	private double primeGoalProbability(double p) {
		// probs[i] is the probability that the team has scored i goals.
		double[] probs = new double[this.intervals + 1];
		// The probability for 0 goals is initially 1.
		probs[0] = 1;
		// All others are 0 (no goals have been shot yet).
		for (int i = 1; i <= this.intervals; i++) {
			probs[i] = 0;
		}
		// Play the match, stepping through each interval.
		for (int i = 1; i <= this.intervals; i++) {
			// Update all probabilities for each step.
			for (int j = this.intervals; j >= 0; j--) {
				/* At each interval, the probability for scoring j goals is
				 * the sum of two possible cases:
				 *   1. j goals have been scored and none is scored in this
				 *      interval,
				 *   2. j - 1 goals have been scored and the team scores in this
				 *      interval (except for 0 goals). */
				probs[j] *= 1 - p;
				if (j > 0)
					probs[j] += probs[j - 1] * p;
			}
		}
		double sum = 0;
		// Collect and return all probabilities for a prime number of goals.
		for (int i = 1; i <= this.intervals; i++) {
			if (this.isPrime(i))
				sum += probs[i];
		}
		return sum;
	}

	/**
	 * Compute the final probability.
	 * 
	 * @param skillTeamA Probability that Team A scores one goal in one
	 * interval.
	 * @param skillTeamB Probability that Team B scores one goal in one
	 * interval.
	 * @return The probability that at least one team finishes the match with
	 * a prime number of goals.
	 */
	public double getProbability(int skillTeamA, int skillTeamB) {
		double skillA = (double)skillTeamA / 100;
		double skillB = (double)skillTeamB / 100;
		/* The probability that at least one team finishes with a prime number
		 * of goals is equal to 100% minus the probability that team A and team
		 * B do not finish with a prime number of goals. */
		return 1 -
		  (1 - this.primeGoalProbability(skillA)) *
		  (1 - this.primeGoalProbability(skillB));
	}

}