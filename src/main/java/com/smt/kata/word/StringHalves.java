package com.smt.kata.word;

/****************************************************************************
 * <b>Title</b>: StringHalves.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> String Halves Kata
 * 
 * Determine if String Halves Are Alike
 * 
 * You are given a string s of even length. Split this string into two halves of 
 * equal lengths, and let a be the first half and b be the second half.
 * 
 * Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 
 * 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.
 * 
 * Return true if a and b are alike. Otherwise, return false.
 * 
 * Example 1:
 * Input: s = "book"
 * Output: true
 * Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
 * 
 * Example 2:
 * Input: s = "textbook"
 * Output: false
 * Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, 
 * they are not alike.
 * Notice that the vowel o is counted twice.
 * 
 * Example 3:
 * Input: s = "MerryChristmas"
 * Output: false
 * 
 * Example 4:
 * Input: s = "AbCdEfGh"
 * Output: true
 * 
 * Constraints:
 * 
 * 2 <= s.length <= 1000
 * s.length is even.
 * s consists of uppercase and lowercase letters.
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Sep 7, 2021
 * @updates:
 ****************************************************************************/
public class StringHalves {

	/**
	 * Calculates whether the number of vowels and consonants are the same after
	 * splitting the source string in half
	 * @param s Source String
	 * @return True if the numbers match.  False otherwise;
	 */
	public boolean calculate(String s) {
		if(s == null || s.isEmpty() || s.length()%2 != 0) return false;
		String a = s.substring(0, s.length()/2).toLowerCase();
		String b = s.substring(s.length()/2).toLowerCase();
		int aCount = 0;
		int bCount = 0;
		for (int i=0; i<s.length()/2; i++) {
			if(a.charAt(i) == 'a' || a.charAt(i) == 'e' || a.charAt(i) == 'i' || a.charAt(i) == 'o' || a.charAt(i) == 'u') {
				aCount++;
			}
			if(b.charAt(i) == 'a' || b.charAt(i) == 'e' || b.charAt(i) == 'i' || b.charAt(i) == 'o' || b.charAt(i) == 'u') {
				bCount++;
			}
		}
		return aCount == bCount;
	}
}
