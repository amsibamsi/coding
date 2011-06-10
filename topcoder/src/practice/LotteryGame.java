package practice;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

public class LotteryGame
implements Comparable<LotteryGame> {

	private String name;
	private int choices, blanks;
	private boolean sorted, unique;
	private long odds;
	
	public void set(
		String name,
		int choices,
		int blanks,
		boolean sorted,
		boolean unique
	) {
		this.name = name;
		this.choices = choices;
		this.blanks = blanks;
		this.sorted = sorted;
		this.unique = unique;
		this.odds = -1;
	}
	
	public LotteryGame(String spec) {
		String[] game, rules;
		int choices, blanks;
		boolean sorted = false, unique = false;
		game = spec.split(":");
		rules = game[1].split(" ");
		choices = parseInt(rules[1]);
		blanks = parseInt(rules[2]);
		if (rules[3].equals("T"))
			sorted = true;
		if (rules[4].equals("T"))
			unique = true;
		this.set(game[0], choices, blanks, sorted, unique);
	}
	
	public String getName() {
		return this.name;
	}
	
	private long fact(int start, int count) {
		long result = start;
		for (int i = 1; i < count; i++)
			result *= start - i;
		return result;
	}
	
	private long fact(int n) {
		return this.fact(n, n);
	}
	
	private long binomCoef(int n, int k) {
		return this.fact(n, k) / this.fact(k);
	}
	
	private long sortedOdds(int blanks, int choices) {
		long[][] odds = new long[blanks][choices];
		for (int i = 0; i < blanks; i++) {
			for (int j = 0; j < choices; j++) {
				if (i == 0)
					odds[i][j] = j + 1;
				else if (j == 0)
					odds[i][j] = 1;
				else
					odds[i][j] = odds[i-1][j] + odds[i][j-1];
			}
		}
		return odds[blanks-1][choices-1];
	}
	
	private void computeOdds() {
		if (!this.sorted && !this.unique)
			this.odds = (long) pow(this.choices, this.blanks);
		if (!this.sorted && this.unique)
			this.odds = this.fact(this.choices, this.blanks);
		if (this.sorted && !this.unique)
			this.odds = this.sortedOdds(this.blanks, this.choices);
		if (this.sorted && this.unique)
			this.odds = this.binomCoef(this.choices, this.blanks);
	}
	
	public long getOdds() {
		if (this.odds < 0)
			this.computeOdds();
		return this.odds;
	}

	public int compareTo(LotteryGame game) {
		long diff = this.getOdds() - game.getOdds();
		if (diff < 0)
			return -1;
		else if (diff > 0)
			return 1;
		else
			return this.getName().compareToIgnoreCase(game.getName());
	}
	
}
