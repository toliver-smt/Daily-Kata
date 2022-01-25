package com.smt.kata.word;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.commons.lang3.ArrayUtils;

/****************************************************************************
 * <b>Title</b>: AlienDictionary.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Alien Dictionary Kata
 * 
 * In an alien language, surprisingly, they also use English lowercase letters, 
 * but possibly in a different order. The order of the alphabet is some permutation 
 * of lowercase letters.
 * 
 * Given a sequence of words written in the alien language, and the order of the alphabet, 
 * return true if and only if the given words are sorted lexicographically in this alien language.
 * 
 * Example 1:
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * 
 * Example 2:
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], 
 * hence the sequence is unsorted.
 * 
 * Example 3:
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is 
 * shorter (in size.) According to lexicographical rules "apple" > "app", 
 * because 'l' > '∅', where '∅' is defined as the blank character which is less 
 * than any other character (More info).
 * 
 * Constraints:
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * 
 * All characters in words[i] and order are English lowercase letters.
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Sep 7, 2021
 * @updates:
 ****************************************************************************/
public class AlienDictionary {

	Map<Character, Integer> letterVals = new HashMap<>();
	String order;
	/**
	 * Initializes the class with the new alphabet order
	 * @param order Order of the letters in the new alphabet
	 * @throws DatatypeConfigurationException If the order of letters is invalid
	 */
	public AlienDictionary(String order) throws DatatypeConfigurationException {
		super();
		this.order = order;
		if(order != null && order.length() == 26) {
			letterVals.put(' ', 0);
			for(int i = 0; i < order.length(); i++) {
				letterVals.put(order.charAt(i), i+1);
			}
		} else {
			throw new DatatypeConfigurationException("Don't do this");
		}
	}

	/**
	 * Determines if the provided words are in order
	 * @param words Words to check
	 * @return True if the words are in order and false otherwise
	 */
	public boolean isSorted(String[] words) {
		boolean sorted = true;
		if(words == null || words.length < 2) return false;
		words = sanitizeWords(words);
		Set<Character> checkedChars;
		for(int i = 1; i < 100; i++) {
			checkedChars = new HashSet<>();
			boolean done = true;
			for(int j = 1; j < words.length; j++) {
				String curr = words[j];
				String prev = words[j-1];
				if(curr.length() > i && prev.length() > i) {
					if(letterVals.get(curr.charAt(i)) < letterVals.get(prev.charAt(i-1))) {
						return false;
					} else if (prev.length() > curr.length() && prev.startsWith(curr)) {
						return false;
					}
					if(checkedChars.contains(curr.charAt(i))) {
						done = false;
					} else {
						checkedChars.add(curr.charAt(i));
					}
				}
			}
			if(done) {
				return true;
			}
		}
		return sorted;
	}
	public String [] sanitizeWords(String [] words) {
		for(int i = 0; i < words.length; i++) {
			if(words[i] == null) {
				words = ArrayUtils.remove(words, i--);
			} else {
				words[i] = words[i].toLowerCase();
			}
		}

		return words;
	}
}
