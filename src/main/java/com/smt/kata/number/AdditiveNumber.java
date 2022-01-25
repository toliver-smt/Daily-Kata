package com.smt.kata.number;

import org.apache.commons.lang3.StringUtils;

/****************************************************************************
 * <b>Title</b>: AdditiveNumber.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Additive Number Kata
 * 
 * Additive number is a string whose digits can form additive sequence.
 * 
 * A valid additive sequence should contain at least three numbers. Except for the 
 * first two numbers, each subsequent number in the sequence must be the sum of 
 * the preceding two.
 * 
 * Given a string containing only digits '0'-'9', write a function to determine 
 * if it's an additive number.
 * 
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 
 * 1, 2, 03 or 1, 02, 3 is invalid.
 * 
 * Example 1:
 * Input: "112358"
 * Output: true
 * Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8. 
 *              1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * 
 * Example 2:
 * Input: "199100199"
 * Output: true
 * Explanation: The additive sequence is: 1, 99, 100, 199. 
 *              1 + 99 = 100, 99 + 100 = 199
 * 
 * Constraints:
 * num consists only of digits '0'-'9'.
 * 1 <= num.length <= 35
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Nov 18, 2021
 * @updates:
 ****************************************************************************/
public class AdditiveNumber {
	/**
	 * Determines if the provided sequence is additive
	 * @param sequence Numeric sequence to evaluate
	 * @return True if additive, false otherwise
	 */
	public boolean isAdditive(String sequence) {
		boolean isAdditive = false;
		if(!StringUtils.isEmpty(sequence) && sequence.matches("[0123456789]+")) {
			for(int i = 1; i< sequence.length() / 2 + 1 && !isAdditive; i++) {
				 String seq1 = sequence.substring(0, i);
				 for(int j = i + 1; j < sequence.length(); j++) {
					 String seq2 = sequence.substring(i, j);
					 if(isValid(seq1) && isValid(seq2)) {
						 int sum = Integer.parseInt(seq1) + Integer.parseInt(seq2);
						 String rem = sequence.substring(j);
						 if(rem.equals(Integer.toString(sum)) || (rem.startsWith(Integer.toString(sum)) && isAdditive(rem, Integer.parseInt(seq2)))) {
							 isAdditive = true;
							 break;
						 }
					 }
				 }
			}
		}
		return isAdditive;
	}

	private boolean isAdditive(String sequence, int sum) {
		boolean isAdditive = false;
		for(int i = 1; i< sequence.length() / 2 + 1 && !isAdditive; i++) {
			 String seq1 = sequence.substring(0, i);
			 int s1 = Integer.parseInt(seq1);
			 String rem = sequence.substring(i);
			 if(rem.equals(Integer.toString(sum + s1))) {
				 isAdditive = true;
			 } else if(isValid(seq1) && rem.startsWith(Integer.toString(s1 + sum))) {
				 isAdditive = isAdditive(rem, (s1));
			 }
		}
		return isAdditive;
	}

	private boolean isValid(String seq) {
		return !seq.startsWith("0");
	}
}
