package com.smt.kata.number;

import java.util.ArrayList;
import java.util.List;

/****************************************************************************
 * <b>Title</b>: LargestGap.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Given an array of integers, return the largest gap between 
 * the sorted elements of the array.  For example, consider the array: 
 * [9, 4, 26, 26, 0, 0, 5, 20, 6, 25, 5] 
 * After sorting, the array becomes: [0, 0, 4, 5, 5, 6, 9, 20, 25, 26, 26] 
 * We now see that the largest gap in the array is between 9 and 20 which is 11.  
 * You must sort the arrays manually!  
 * You may not use Arrays.sort!
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Jan 5, 2021
 * @updates:
 ****************************************************************************/
public class LargestGap {
	
	/**
	 * Calls out to get the array sorted and then finds the largest gap
	 * @param arr
	 * @return
	 */
	public int largestGap(int[] arr) {
		for (int i=0; i<arr.length - 1; i++) {
			for (int j=i+1; j<arr.length; j++) {
				if (arr[i] > arr[j]) {
					int first = arr[i];
					int second = arr[j];
					arr[i] = second;
					arr[j] = first;
				}
			}
		}
		
		int longestGap = 0;
		for (int i=0; i<arr.length - 1; i++) {
			int j = i+1;
			int max = Math.max(Math.abs(arr[i]), Math.abs(arr[j]));
			int min = Math.min(Math.abs(arr[i]), Math.abs(arr[j]));
			if ((max-min) > longestGap) {
				longestGap = (max-min);
			}
		}
		
		return longestGap;
	}

}
