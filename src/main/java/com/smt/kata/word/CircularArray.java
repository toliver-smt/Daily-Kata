package com.smt.kata.word;

// JDK 11.x
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/****************************************************************************
 * <b>Title</b>: CircularArray.java
 * <b>Project</b>: Daily-Kata
 * <b>Description: </b> Circular Array Kata
 * 
 * Given a list of words, determine whether the words can be chained to form a circle. 
 * A word X can be placed in front of another word Y in a circle if the last 
 * character of X is same as the first character of Y.
 * 
 * For example, the words ['chair', 'height', 'racket', touch', 'tunic'] can 
 * form the following circle: chair --> racket --> touch --> height --> tunic --> chair.

 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Sep 13, 2021
 * @updates:
 ****************************************************************************/
public class CircularArray {

	/**
	 * Creates the circular array from the array of words
	 * @param words Words to reorder in a circular pattern
	 * @return Collection of words reordered.  Empty list if not possible
	 */
	public List<String> create(String[] words) {
		List<String> res = new ArrayList<>();
		if(words != null && words.length > 1) {
			for(int i = 0; i < words.length; i++) {
				String s = words[i];
				List<String >w = new ArrayList<>();
				w.add(s);
				List<String> r = chainWords(cpArray(words, i), w);
				if(r.size() > res.size()) {
					res = r;
				}
			}
			
		}
		return res;
	}

	private String[] cpArray(String[] words, int i) {
		String [] clone = Arrays.copyOf(words, words.length);
		clone[i] = "";
		return clone;
		
	}

	private List<String> chainWords(String[] words, List<String> w) {
		if(w.size() == words.length) {
			w.add(w.get(0));
			return w;
		}
		String lastWord = w.get(w.size() - 1);
		for(int i = 0; i < words.length; i++) {
			String s = words[i];
			if(StringUtils.isNotEmpty(s) && s.charAt(0) == lastWord.charAt(lastWord.length() - 1)) {
				List<String> wNew = new ArrayList<>();
				wNew.addAll(w);
				wNew.add(s);
				return chainWords(cpArray(words, i), wNew);
			}
		}
		return new ArrayList<>();
	}
}
