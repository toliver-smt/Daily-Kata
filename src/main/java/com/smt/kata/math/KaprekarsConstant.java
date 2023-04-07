package com.smt.kata.math;

import java.util.*;

/****************************************************************************
 * <b>Title</b>: KaprekarsConstant.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Kaprekar's Constant Kata
 * 
 * The number 6174 is known as Kaprekar's constant, after the mathematician who 
 * discovered an associated property: for all four-digit numbers with at least 
 * two distinct digits, repeatedly applying a simple procedure eventually results 
 * in this value. The procedure is as follows:
 * 
 * For a given input x, create two new numbers that consist of the digits in x in 
 * ascending and descending order.
 * 
 * Subtract the smaller number from the larger number.
 * For example, this algorithm terminates in three steps when starting from 1234:
 * 
 * 4321 - 1234 = 3087
 * 8730 - 0378 = 8352
 * 8532 - 2358 = 6174
 * 
 * Write a function that returns how many steps this will take for a given input N.
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Oct 25, 2021
 * @updates:
 ****************************************************************************/
public class KaprekarsConstant {

	/**
	 * Calculates the number of steps until Kaprekar's Constant is achieved
	 * @param num Number to evaluate
	 * @return the number of steps to achieve the number
	 */
	public int calculateSteps(int num) {
		//validate for range and repeat numbers
		if (num <= 999 || num >= 10000 || (num+"").chars().distinct().count() < 2) return 0;
		int count = 0;
		while (true) {
			//create a char array and sort it for ascending order
			char[] digits = String.valueOf(num).toCharArray();
			Arrays.sort(digits);
			//append ascending char array to a stringbuilder so I can reverse it
			StringBuilder sb = new StringBuilder();
			for (char c : digits) {
				sb.append(Character.getNumericValue(c));
			}
			//reverse sb and parse to int
			int asc = Integer.parseInt(sb.toString());
			int des = Integer.parseInt(sb.reverse().toString());
			int myVal;
			if (asc > des) {
				myVal = asc - des;
			} else {
				myVal = des - asc;
			}
			if (myVal != 6174) {
				calculateSteps(myVal);
				count++;
			} else {
				count++;
				break;
			}
		}
		return count;
	}
}
