package com.smt.kata.number;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.siliconmtn.data.text.StringUtil;

/****************************************************************************
 * <b>Title</b>: OriginalDigits.java <b>Project</b>: SMT-Kata <b>Description:
 * </b> Reconstruct Original Digits from English
 * 
 * Given a string s containing an out-of-order English representation of digits
 * 0-9, return the digits in ascending order.
 * 
 * Example 1: Input: s = "owoztneoer" Output: "012"
 * 
 * Example 2: Input: s = "fviefuro" Output: "45"
 * 
 * Constraints: 1 <= s.length <= 105 s[i] is one of the characters
 * ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"].
 * 
 * <b>Copyright:</b> Copyright (c) 2021 <b>Company:</b> Silicon Mountain
 * Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Sep 9, 2021
 * @updates:
 ****************************************************************************/
public class OriginalDigits {
	char [] repl = {'e','g','f','i','h','o','n','s','r','u','t','w','v','x','z'};

	Map<Integer, List<Character>> numLetters = new LinkedHashMap<>();
	/**
	 * Initializes the class
	 */
	public OriginalDigits() {
		super();
		numLetters.put(0, "zero".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
		numLetters.put(1, "one".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
		numLetters.put(2, "two".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
		numLetters.put(3, "three".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
		numLetters.put(4, "four".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
		numLetters.put(5, "five".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
		numLetters.put(6, "six".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
		numLetters.put(7, "seven".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
		numLetters.put(8, "eight".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
		numLetters.put(9, "nine".chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
		
	}

	/**
	 * Calculates the digits in the word
	 * @param source Source to find digits
	 * @return Digits in order
	 */
	public String calculate(String source) {
		if(StringUtil.isEmpty(source)) {
			return "";
		}
		source = source.toLowerCase();
		String t = source.replaceAll("[egfihonsrutwvxz]", "");
		if(StringUtils.isNotEmpty(t)) {
			return "";
		}
		String nums = "";
		boolean foundMatch = false;
		while(StringUtils.isNotEmpty(source)) {
			for(Entry<Integer, List<Character>> e : numLetters.entrySet()) {
				String temp = source;
				int repl = e.getValue().size();
				for(char d : e.getValue()) {
					for(char c : temp.toCharArray()) {
						if(c == d) {
							temp= temp.replaceFirst("" + c, " ");
							repl--;
							break;
						}
					}
				}
				if(repl == 0) {
					source = temp.replace(" ", "");
					nums += e.getKey();
				}
			}
			if(StringUtils.isEmpty(nums)) {
				return "";
			}
		}
		char [] ns = nums.toCharArray();
		Arrays.sort(nums.toCharArray());
		
		return new String(ns);
	}
}
