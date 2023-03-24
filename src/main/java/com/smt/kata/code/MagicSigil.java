package com.smt.kata.code;

/****************************************************************************
 * <b>Title</b>: MagicSigil.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> A magic sigil is a glyph which represents a desire one wishes 
 * to manifest in their lives. There are many ways to create a sigil, but the 
 * most common is to write out a specific desire (e.g. "I HAVE WONDERFUL FRIENDS WHO LOVE ME"), 
 * remove all vowels and spaces, remove any duplicate letters (keeping the last occurence), 
 * and then design a glyph from what remains.  Using the sentence above as an example, 
 * we would remove duplicate letters: 
 * 		AUFRINDSWHLOVME 
 * And then remove all vowels, leaving us with: 
 * 		FRNDSWHLVM 
 * Create a method that takes a string and removes its vowels and duplicate letters. 
 * The returned string should not contain any spaces and be in uppercase.
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Jan 5, 2021
 * @updates:
 ****************************************************************************/
public class MagicSigil {

	/**
	 * Sigilize the word
	 * @param word
	 * @return
	 */
	public String sigilize(String word) {
		if(word == null || word.isEmpty()) return "";
		word = word.toUpperCase();
		word = word.replace(" ", "");
		word = word.replace("A", "");
		word = word.replace("E", "");
		word = word.replace("I", "");
		word = word.replace("O", "");
		word = word.replace("U", "");
		System.out.println(word);
		StringBuilder sb = new StringBuilder(word);
		System.out.println(sb);
		for (int i=0; i<sb.length(); i++) {
			for (int j=i+1; j<sb.length(); j++) {
				if (sb.charAt(i) == sb.charAt(j)) {
					sb.deleteCharAt(i);
				}
			}
		}
		String result = sb.toString();
		return result;
	}

}
