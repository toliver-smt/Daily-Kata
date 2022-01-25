package com.smt.kata.code;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/****************************************************************************
 * <b>Title</b>: StringWindow.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> String Window Kata
 * 
 * Given a string, find the length of the smallest window that contains every 
 * distinct character. Characters may appear more than once in the window.
 * 
 * For example, given "jiujitsu", you should return 5, corresponding to the final 
 * five letters.
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Nov 30, 2021
 * @updates:
 ****************************************************************************/
public class StringWindow {

	/**
	 * Find the length of the smallest window
	 * @param word Word to find the window
	 * @return Smallest Window.  0 is returned if invalid data.
	 */
	public int find(String word) {
		int count = 0;
		if(StringUtils.isNotEmpty(word)) {
			count = word.length();
			Set<Integer> wordSet = getSet(word);
			for(int i = 0, j = i+1; i < (word.length() - wordSet.size()) && j < word.length(); j++) {
				if(getSet(word.substring(i, j)).containsAll(wordSet)) {
					count = Math.min(count, j - i);
				}
				if (j == word.length() - 1) {
					i++;
					j = i;
				}
			}
		}
		return count;
	}

	private Set<Integer> getSet(String chars) {
		return chars.toLowerCase().chars().boxed().collect(Collectors.toSet());
	}
}
