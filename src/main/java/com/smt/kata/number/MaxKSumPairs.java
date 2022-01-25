package com.smt.kata.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/****************************************************************************
 * <b>Title</b>: MaxKSumPairs.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Max Number of K-Sum Pairs
 * 
 * You are given an integer array nums and an integer k.
 * 
 * In one operation, you can pick two numbers from the array whose sum equals k 
 * and remove them from the array.
 * 
 * Return the maximum number of operations you can perform on the array.
 * 
 * Example 1:
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5, hence a total of 2 operations.
 * 
 * Example 2:
 * Input: nums = [3,1,3,4,3], k = 6
 * Output: 1
 * Explanation: Starting with nums = [3,1,3,4,3]:
 * - Remove the first two 3's, then nums = [1,4,3]
 * There are no more pairs that sum up to 6, hence a total of 1 operation.
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Sep 24, 2021
 * @updates:
 ****************************************************************************/
public class MaxKSumPairs {
	
	/**
	 * In one operation, you can pick two numbers from the array whose sum equals k 
 	 * and remove them from the array.
	 * @param source Source array of numbers
	 * @param target Target number to calculate against
	 * @return Number of operations
	 */
	public int calculate(int[] source, int target) {
		int pairs = 0;
		// Validate the data\
		if(source != null  && target > 0) {
			List<Integer> nums = Arrays.stream(source).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
			List<Integer> matches = new ArrayList<>();
			while(!nums.isEmpty()) {
				int match = -1;
				for(int i = 0; i < nums.size(); i++) {
					int ele = nums.get(i);
					int sum = sum(ele, matches);
					if(ele < target && sum < target) {
						matches.add(ele);
						match = i;
						break;
					} else if (ele == target || sum == target) {
						pairs++;
						matches.clear();
						match = i;
						break;
					}
				}
				if(match > -1) {
					nums.remove(match);
				} else {
					matches.clear();
				}
			}
		}
		return pairs;
	}

	private int sum(int ele, List<Integer> matches) {
		return matches.stream().collect(Collectors.summingInt(Integer::intValue)) + ele;
	}
}
