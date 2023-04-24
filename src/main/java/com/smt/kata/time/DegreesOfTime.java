package com.smt.kata.time;

/****************************************************************************
 * <b>Title</b>: DegreesOfTime.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Degrees of Time
 * 
 * Given a clock time in hh:mm format, determine, to the nearest degree, the angle 
 * in degrees, between the hour and the minute hands.  The hour will be in 24 hour format.
 * 
 * Example One:
 * Input : 01:05
 * Output: 0 degrees as the hands are in the same location
 * 
 * Example two
 * Input: 00:15
 * Output: 90 degrees as the hour hand is vertical and the minute had is at 90 gegrees
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Nov 10, 2021
 * @updates:
 ****************************************************************************/
public class DegreesOfTime {

	/**
	 * Calculate the difference in degrees for the provided time
	 * @param time Time to calculate in hh:mm 24 hour format
	 * @return difference in degrees between the minute and hour hand.  0 if invalid data
	 */
	public int calculate(String time) {
		//validate
        if (time == null || time.isEmpty() || !time.matches("[0-9]?[0-9]:[0-9][0-9]")) return 0;
		
		//Convert time to String array
		String[] timeArr = time.split(":");
		
		//Declare and initialize hour
		int hour = Integer.parseInt(timeArr[0]);
		//If hour is greater than 12, subtract 12 from it
		if (hour > 12) {
			hour = hour - 12;
		}
		//Determine the angle from hour to 12
		int hourAngle = hour * 30;
		//Declare and initialize min
		int min = Integer.parseInt(timeArr[1]);
		//Determine the angle from min to 12
		int minAngle = min * 6;
		//Return the difference
		return Math.abs(hourAngle - minAngle);
	}

}
