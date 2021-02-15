package com.smt.kata.data;

// JDK 11
import java.util.ArrayList;
import java.util.List;

/****************************************************************************
 * <b>Title</b>: PatternDecoder.java 
 * <b>Project</b>: SMT-Kata 
 * <b>Description:</b> 
 * Given the mapping a = 1, b = 2, ... z = 26, and an encoded message,
 * count the number of ways it can be decoded. For example, the message '111'
 * would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'. You can
 * assume that the messages are decodable. For example, '001' is not allowed.
 * <b>Copyright:</b> Copyright (c) 2021 
 * <b>Company:</b> Silicon Mountain
 * Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Jan 5, 2021
 * @updates:
 ****************************************************************************/
public class PatternDecoder {
	public static String [] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

	/**
	 * Decodes the given pattern
	 * 
	 * @param pattern Valid pattern to compare
	 * @return Collection of the matching patterns
	 */
	public List<String> decodePattern(String pattern) {
		System.out.println("");
		List<String> patterns = new ArrayList<>();

		//Remove bad test cases.
		if(pattern == null || pattern.indexOf("0") > -1 || pattern.matches("\\w*[a-zA-Z]\\w*")) {
			return patterns;
		}

		///If a single character, parse and return
		if(pattern.length() == 1) {
			patterns.add(letters[Integer.parseInt(pattern) - 1]);
		} else {
			///Parse and test character 1 in the pattern
			char [] chars = pattern.toCharArray();
			String startletter = letters[Integer.parseInt(chars[0] + "") - 1];
			///Calculate the remainder after the start char.
			String remainder = pattern.substring(1, pattern.length());

			//If we have a valid remainder, recursively call
			if(remainder != null && remainder.length() > 0) {
				patterns.addAll(checkPattern(startletter, remainder));
			}

			///Check for 2 character split.
			startletter = pattern.substring(0, 2);

			//Check if the 2 character is a valid letter.
			if(Integer.parseInt(startletter) < 27) {
				startletter = letters[Integer.parseInt(startletter) - 1];
				remainder = pattern.substring(2);

				///Check and test remainders again recursively.
				if(remainder != null && remainder.length() > 0) {
					patterns.addAll(checkPattern(startletter, remainder));
				}
			}
		}

		for(String s : patterns) {
			System.out.println(s);
		}
		return patterns;
	}

	/**
	 * Recursive method to call
	 * @param startString
	 * @param pattern
	 * @return
	 */
	public List<String> checkPattern(String startString, String pattern) {
		List<String> patterns = new ArrayList<>();

		///If pattern is a single character, join start and pattern add to patterns.
		if(pattern.length() == 1) {
			patterns.add(startString + letters[Integer.parseInt(pattern) - 1]);
		} else {
			char [] chars = pattern.toCharArray();
			String startletter = startString + letters[Integer.parseInt(chars[0] + "") -1];
			String remainder = pattern.substring(1, pattern.length());
			if(remainder != null && remainder.length() > 0) {
				patterns.addAll(checkPattern(startletter, remainder));
			}
			//Check for 2 character split.
			if(pattern.length() >= 2) {
				startletter = pattern.substring(0, 2);
				
				if(Integer.parseInt(startletter) < 27) {
					startletter = letters[Integer.parseInt(startletter) - 1];
					remainder = pattern.substring(2);
					if(remainder != null && remainder.length() > 0) {
						patterns.addAll(checkPattern(startString + startletter, remainder));
					} else {
						patterns.add(startString + startletter);
					}
				}
			}
		}
		return patterns;
	}
}
