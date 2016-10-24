package dev.zero.trainSimulator;
/**
 * 
 */

import dev.zero.trainSimulator.model.TimeCal;

/**
 * @author Priyabrata
 *
 */
public class TestTime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeCal tc= new TimeCal("0:11:34");
		System.out.println("time1 = "+tc.convertDateToString());
		tc.addTime(1,2,62,0);
		System.out.println("time2 = "+tc.convertDateToString());
		
		

	}

}
