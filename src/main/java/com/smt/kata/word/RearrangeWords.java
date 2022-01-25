package com.smt.kata.word;

// JDK 11.x
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/****************************************************************************
 * <b>Title</b>: RearrangeWords.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Rearrange Words Kata
 * 
 * Given a string with repeated characters, rearrange the string so that no two 
 * adjacent characters are the same. If this is not possible, return None.  Return a 
 * collection of all of the possible variations that can occur with no two
 * characters repeated
 * 
 * If the word passed in already has no repeating characters, return just that word
 * 
 * For example, given "aaabbc", you could return :
 * ababac, ababca, abacab, abacba, abcaba, acabab, acbaba, babaca, bacaba, cababa 
 * 
 * Given "aaab", return an empty collection.
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Aug 30, 2021
 * @updates:
 ****************************************************************************/
public class RearrangeWords {

	/**
	 * Rearranges the characters in a word such that there are no 2 adjacent characters
	 * @param word Word to rearrange
	 * @return All the possible ways the word can be rearranged to not have sequential characters
	 */
	public Collection<String> arrange(String word) {
		Set<String> words = new HashSet<>();
		if(word == null || !StringUtils.isAlpha(word) || word.length() < 2) {
			return words;
		} else {
			boolean noRepeats = true;
			for(int i = 1; i < word.length(); i++) {
				if(word.charAt(i) == word.charAt(i-1)) {
					noRepeats = false;
				}
			}
			if(noRepeats) {
				words.add(word);
			} else {
				permutate(word, "", words);
			}
		}
		return words;
	}

	private void permutate(String pool, String word, Set<String> words) {
		if(StringUtils.isEmpty(pool)) {
			words.add(word);
		}
		Set<Character> used = new HashSet<>();
		if(word.length() != 0) {
			used.add(word.charAt(word.length() - 1));
		}
		for(int i = 0; i < pool.length(); i++) {
			if(used.contains(pool.charAt(i))) {
				continue;
			}
			permutate(pool.substring(0, i) + pool.substring(i+1), word + pool.charAt(i), words);
		}
	}

}
