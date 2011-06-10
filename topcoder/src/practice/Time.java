package practice;

/**
 * TopCoder SRM 144 DIV 2 200
 * 
 * @author $Author: anselm $
 * @version $Revision: 287 $ $Date: 2008-11-17 22:38:11 +0100 (Mon, 17 Nov 2008) $
 */
public class Time {

	public String whatTime(int seconds) {
		int hours, minutes;
		hours = seconds / 3600;
		seconds -= hours * 3600;
		minutes = seconds / 60;
		seconds -= minutes * 60;
		return "" + hours + ":" + minutes + ":" + seconds; 
	}
	
}
