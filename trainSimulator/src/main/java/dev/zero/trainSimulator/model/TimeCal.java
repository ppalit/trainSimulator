package dev.zero.trainSimulator.model;
/**
 * This class is used used as a substitute of Date class 
 * It is used to store date in the format of string in the form day:hour:min
 * It provides all the necessary functions for date and time for the application
 */

/**
 * @author Priyabrata
 * 
 */
public class TimeCal {
	/** The hour field */
	public int hour;
	/** The minuite field */
	public int min;
	/** The day field representing the present day no. of journey of train */
	public int day;
	/** The sec field only for calculations*/
	public int sec;

	int extra;

	TimeCal tc;
	/**
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @return the sec
	 */
	public int getSec() {
		return sec;
	}

	/**
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * @param sec the sec to set
	 */
	public void setSec(int sec) {
		this.sec = sec;
	}

	/**
	 * Default constructor
	 */
	public TimeCal() {
		this.day=0;
		this.hour=0;
		this.sec=0;
		this.min =0;
		this.extra=0;
		
	}

	/**
	 * @param hour
	 * @param min
	 * @param day
	 */
	public TimeCal(int hour, int min, int day) {
		this.hour = hour;
		this.min = min;
		this.day = day;
		this.sec=0;
		extra = 0;

	}

	public TimeCal(int hour, int min, int sec, int day) {
		this.hour = hour;
		this.min = min;
		this.day = day;
		this.sec= sec;
		extra = 0;

	}
	/**
	 * @param date
	 *            String to be taken from database or manually and creates a
	 *            TimeCal object
	 */
	public TimeCal(String date) {
		String str[] = new String[2];
		str = date.split(":");
		// System.out.println("Hour=" + str[0] + " Min= "+str[2]+" day= " +
		// str[0]);
		day = Integer.parseInt(str[0]);
		hour = Integer.parseInt(str[1]);
		min = Integer.parseInt(str[2]);
		// System.out.println("Hour=" + hour + "Min" + min + "day" + day);
	}

	/**
	 * @param hr
	 * @param mn
	 * 
	 */
	public TimeCal addTime(int hr, int mn) {

		tc = new TimeCal();
		tc.min = this.min + mn;
		tc.extra = tc.min / 60;
		tc.min = tc.min % 60;

		tc.hour = this.hour + hr + tc.extra;
		tc.extra = tc.hour / 24;
		tc.hour = tc.hour % 24;

		tc.day = this.day + tc.extra;

		return tc;
	}
	
	public void addTime(int hr, int mn, int sc , int dy) {

		//tc = new TimeCal();
		//System.out.println("Hour=" + hour + "Min" + min + "day" + dy +" sec="+ sec);
		//System.out.println("Hour=" + hr + "Min" + mn + "dy" + dy +" sec="+ sc);
		this.sec = this.sec + sc;
		extra = this.sec/60;
		this.sec = this.sec % 60;
		
		
		this.min = this.min + mn + extra;
		extra = this.min / 60;
		this.min = this.min % 60 ;

		this.hour = this.hour + hr + extra;
		extra = this.hour / 24;
		this.hour = this.hour % 24;
		this.day = this.day + dy + extra;
	}
	/**
	 * @param hr
	 *            hours to be added
	 * @param mn
	 *            min to be added
	 * @param dy
	 *            day to be added
	 */
	public void addTime(int hr, int mn, int dy) {
		this.min = this.min + mn;
		extra = this.min / 60;
		this.min = this.min % 60;

		this.hour = this.hour + hr + extra;
		extra = this.hour / 24;
		this.hour = this.hour % 24;
		this.day = this.day + dy + extra;

	}
	/**
	 * @param tc
	 *            takes a TimeCal object as argument and adds it to the current
	 *            object
	 */
	public void addTime(TimeCal tc) {
		min = min + tc.min;
		extra = min / 60;
		min = min % 60;

		hour = hour + tc.hour + extra;
		extra = hour / 24;
		hour = hour % 24;

		day = day + tc.day + extra;

	}

	public long valueOfDate()
	{
		long total=0;
		
		total= this.sec;
		total= total + this.min* 60;
		total = total + this.hour * 60 * 60;
		total= total + this.day * 24 * 3600;
		return total;
		
	}
	/**
	 * @return 1 argument time is greater 
	 * 			0 argument time is equal to called time 
	 * 			-1 argument time is less than called time
	 */
	public int compare(TimeCal tc) {
		if (tc.day > this.day)
			return 1;
		else if (tc.day < this.day)
			return -1;
		else if (tc.hour > this.hour)
			return 1;
		else if (tc.hour < this.hour)
			return -1;
		else if (tc.min > this.min)
			return 1;
		else if (tc.min < this.min)
			return 1;
		else if(tc.sec < this.sec)
			return -1;
		return 0;
	}
	/**
	 * Converts date format to string format to be stored in database or for
	 * display
	 * 
	 * @return date
	 * 
	 */
	public String convertDateToString() {
		String date;
		date = day + ":" + hour + ":" + min ;

		return date;
	}

}
