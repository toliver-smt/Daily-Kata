package com.smt.kata.math;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;


/****************************************************************************
 * <b>Title</b>: AddBinary.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Add Binary Kata
 * 
 * Given two binary strings a and b, return their sum as a binary string.
 * 
 * Example 1:
 * Input: a = "11", b = "1"
 * Output: "100"
 * 
 * Example 2:
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 * 
 * Constraints:
 * 
 * 1 <= a.length, b.length <= 104
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 * 
 * ****** Try and perform this kata in a single line of code
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Nov 15, 2021
 * @updates:
 ****************************************************************************/
public class AddBinary {

	/**
	 * Adds two strings representing binary numbers 
	 * @param a First binary number
	 * @param b Second Binary Number
	 * @return Sum of the 2 binary numbers added together 
	 */
	public String addBinary(String a, String b) {		
		return Integer.toBinaryString(
			Arrays
			///Convert to 2D array because I need an array to stream, but I need the a and b to remain linked through the stream since validation depends on both values.
			.stream(new String [][]{new String []{a,b}})
			//.stream(new String[] {a,b})  fails because it individually processes elements not the pairs so you reach the end with only one of the elements and the sum is not correct.
			.filter(s -> !StringUtils.isEmpty(s[0]) && !StringUtils.isEmpty(s[1]))  //check both for empty
			.filter(s -> s[0].matches("[01]+") && s[1].matches("[01]+")) //check both match
			.map(s -> new Integer[] {Integer.parseInt(s[0], 2), Integer.parseInt(s[1], 2)}) //convert both to ints
			.map(s -> s[0] + s[1]) //sum both and convert to a single int
			.reduce(0, Integer::sum) //fast exit to a single element with default of 0
		);
    }

}
