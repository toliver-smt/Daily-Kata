package com.smt.kata.data;

import java.util.*;

/****************************************************************************
 * <b>Title</b>: MissingNumber.java 
 * <b>Project</b>: SMT-Kata 
 * <b>Description: </b> Create a method that reverses letters in a string but 
 * keeps digits in their current order.
 * <b>Copyright:</b> Copyright (c) 2020 
 * <b>Company:</b> SiliconMountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Dec 21, 2020
 * @updates:
 ****************************************************************************/
public class ReverseLettersKeepNumbers {

	/**
	 * Reverses a string keeping the numbers in place
	 * @param cWord
	 * @return
	 */
	public String reverse(char[] cWord) {
		//validate
		if (cWord == null || cWord.length == 0) return "";
		
		//Create StringBuilder and lists
		StringBuilder sb = new StringBuilder();
		List<Character> digitList = new ArrayList<>();
		List<Integer> indexList = new ArrayList<>();
		
		//Populate indexList and digitList
		int c = 0;
		for (char element : cWord) {
			if (Character.toString(element).matches("[0-9]")) {
				digitList.add(element);
				indexList.add(c);
			}
			c++;
		}
		
		//Populate StringBuilder
		for (char element : cWord) {
			if (!Character.toString(element).matches("[0-9]")) {
				sb.append(element);
			}
		}
		
		//Reverse StringBuilder
		sb.reverse();
		
		//Insert digits in StringBuilder
		for (int i=0; i<digitList.size(); i++) {
			sb.insert(indexList.get(i), digitList.get(i).toString());
		}
		
		return sb.toString();
	}
}