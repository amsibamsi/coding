package practice;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;

/**
 * TopCoder SRM 144 DIV 1 550
 * 
 * @author $Author: anselm $
 * @version $Revision: 290 $ $Date: 2008-11-20 12:46:50 +0100 (Thu, 20 Nov 2008) $
 */
public class Lottery {

	public String[] sortByOdds(String[] rules) {
		List<LotteryGame> games;
		List<String> names;
		games = new ArrayList<LotteryGame>();
		names = new ArrayList<String>();
		for (String rule : rules)
			games.add(new LotteryGame(rule));
		for (LotteryGame game : games)
			System.out.println(game.getOdds());
		sort(games);
		for (LotteryGame game : games)
			names.add(game.getName());
		return names.toArray(new String[names.size()]);
	}
	
}
