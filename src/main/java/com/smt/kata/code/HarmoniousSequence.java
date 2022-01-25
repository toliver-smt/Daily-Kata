package com.smt.kata.code;

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

/****************************************************************************
 * <b>Title</b>: HarmoniousSequence.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Longest Harmonious Sequence Kata
 * 
 * We define a harmonious array as an array where the difference between its 
 * maximum value and its minimum value is exactly 1.
 * 
 * Given an integer array nums, return the length of its longest harmonious 
 * subsequence among all its possible subsequences.
 * 
 * A subsequence of array is a sequence that can be derived from the array by 
 * deleting some or no elements without changing the order of the remaining elements.
 * 
 * Example 1:
 * Input: nums = [1,3,2,2,5,2,3,7]
 * Output: 5
 * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 * 
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: 2
 * 
 * Example 3:
 * Input: nums = [1,1,1,1]
 * Output: 0
 * 
 * Constraints:
 * 1 <= nums.length <= 2 * 104
 * -109 <= nums[i] <= 109
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Sep 16, 2021
 * @updates:
 ****************************************************************************/
public class HarmoniousSequence {

	/**
	 * Calculates the Longest Harmonious Sequence
	 * @param sequence Numbers to use to calculate
	 * @return total items in the harmonious sequence
	 */
//	public int getLongest(int[] sequence) {
//		int len = 0;
//		if(!ArrayUtils.isEmpty(sequence) && sequence.length >= 2) {
//			for(int i = 0; i < sequence.length; i++) {
//				int start = sequence[i];
//				for(int j = i + 1; j < sequence.length; j++) {
//					int diff = sequence[j];
//					if(Math.abs(start - diff) == 1) {
//						int tLen = 2;
//						for(int k = j + 1; k < sequence.length; k++) {
//							if(sequence[k] == start || sequence[k] == diff) {
//								tLen++;
//							}
//						}
//						len = Math.max(tLen, len);
//					}
//				}
//			}
//		}
//		return len;
//	}
	public int getLongest(int[] sequence) {
		int len = 0;
		if(!ArrayUtils.isEmpty(sequence) && sequence.length >= 2) {
			TreeMap<Integer, Long> counts = new TreeMap<>(Arrays.stream(sequence).boxed().collect(Collectors.groupingBy(e -> e, Collectors.counting())));
			Integer i = null;
			for(Entry<Integer, Long> e : counts.entrySet()) {
				if(i != null && Math.abs(i - e.getKey()) == 1) {
					len = Math.max(len, (int)(counts.get(i) + e.getValue()));
				}
				i = e.getKey();
			}
		}
		return len;
	}
}
