package com.smt.kata.code;

import java.util.HashMap;
import java.util.Map;

/****************************************************************************
 * <b>Title</b>: SoundexConverter.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Soundex Converter Kata
 * Soundex is an algorithm used to categorize phonetically, such that two names 
 * that sound alike but are spelled differently have the same representation.
 * 
 * Soundex maps every name to a string consisting of one letter and three numbers, like M460.
 * 
 * One version of the algorithm is as follows:
 * 
 * Remove consecutive consonants with the same sound (for example, change ck -> c).
 * Keep the first letter. The remaining steps only apply to the rest of the string.
 * Remove all vowels, including y, w, and h.
 * Replace all consonants with the following digits:
 * 
 * b, f, p, v → 1
 * c, g, j, k, q, s, x, z → 2
 * d, t → 3
 * l → 4
 * m, n → 5
 * r → 6
 * 
 * If you don't have three numbers yet, append zeros until you do. Keep the first three numbers.
 * Using this scheme, Jackson and Jaxen both map to J250.
 * 
 * <b>Copyright:</b> Copyright (c) 2022
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Jan 3, 2022
 * @updates:
 ****************************************************************************/
public class SoundexConverter {
	
	/**
	 * Converts the word into a soundex word
	 * @param word Word to convert
	 * @return Soundex word.  Blank word if word is empty or contains numbers
	 */
	public String convert(String word) {
		//validations
		if(word == null || word.isBlank() || word.matches(".*[^a-zA-Z].*")) return "";
		//create stringbuilder
		StringBuilder sb = new StringBuilder();
		//convert to upper
		word = word.toUpperCase();
		//append first letter
		sb.append(word.charAt(0));
		//replace all vowels and y,w,h
		String myWord = word.substring(1)
				.replaceAll("[A,E,I,O,U,Y,W,H]", "")
				.replaceAll("[B, F, P, V]", "1")
				.replaceAll("[C, G, J, K, Q, S, X, Z]", "2")
				.replaceAll("[D, T]", "3")
				.replaceAll("[L]", "4")
				.replaceAll("[M, N]", "5")
				.replaceAll("[R]", "6");
		
		sb.append(myWord);
		System.out.println("1: " + word + ": " + sb + " length: " + sb.length());
		
		for (int i=0; i<sb.length(); i++) {
			if (sb.charAt(i) == sb.charAt(i+1)) {
				sb.deleteCharAt(i+1);
			}
		}
		
		System.out.println("2: " + word + ": " + sb);
		while (sb.length() < 4) {
			sb.append("0");
		}
		
		String result = sb.toString().substring(0, 4);
		
		System.out.println("3: " + word + ": " + result);
		return result;
	}
}
