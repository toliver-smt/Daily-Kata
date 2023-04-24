package com.smt.kata.code;

// JDK 11.x
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/****************************************************************************
 * <b>Title</b>: StartingAnagramIndexes.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Starting Anagram Indexes Kata
 * 
 * Given a word W and a string S, find all starting indices in S which are anagrams of W.
 * 
 * For example, given that W is "ab", and S is "abxaba", return 0, 3, and 4.
 * 
 * <b>Copyright:</b> Copyright (c) 2022
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Apr 17, 2022
 * @updates:
 ****************************************************************************/
public class StartingAnagramIndexes {

	/**
	 * Finds the indexes of all of the matching permutations or anagrams of the word
	 * in the phrase
	 * @param word Word to find And its anagrams) in the phrase
	 * @param phrase Phrase to match against
	 * @return Collection of the matching indexes
	 */
	public List<Integer> find(String word, String phrase) {
		//Create ArrayList result
		List<Integer> result = new ArrayList<>();
		
		//Validate
		if (word == null || phrase == null || word.isEmpty()) return result;
		
		//Convert both arguments to lower case
		word = word.toLowerCase();
		phrase = phrase.toLowerCase();
		
		//Sort word and assign it to a new variable
		char[] sortedWordArray = word.toCharArray();
		Arrays.sort(sortedWordArray);
		String sortedWord = new String(sortedWordArray);
		
		for (int i=0; i<phrase.length()-word.length()+1; i++) {
			//Create a temporary phrase from the overall phrase to compare
			char[] tempPhrase = phrase.substring(i, i+word.length()).toCharArray();
			//Sort tempPhrase
			Arrays.sort(tempPhrase);
			//If tempPhrase is equal to sortedWord add the index to result
			if (sortedWord.equals(new String(tempPhrase))) {
				result.add(i);
			}
		}
		return result;		
	}
}
