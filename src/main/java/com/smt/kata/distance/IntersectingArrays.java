package com.smt.kata.distance;

// JDK 11.x
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

/****************************************************************************
 * <b>Title</b>: IntersectingArrays.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Intersecting Arrays Kata
 * 
 * Given two integer arrays nums1 and nums2, return an array of their intersection. 
 * Each element in the result must appear as many times as it shows in both arrays 
 * and you may return the result in any order.
 * 
 * This kata must be solved in 2 DISTINCT ways.  The intersectNoCollections method
 * must be solved using only std java with NO imports (No collections or other helpers)
 * 
 * The intersectWithCollections must be solved using collections
 * 
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * 
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 * 
 * Constraints:
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 * elements inside the Integer array may NOT be null.  Return an empty array/collection if nulls
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Oct 26, 2021
 * @updates:
 ****************************************************************************/
public class IntersectingArrays {

	/**
	 * Intersects the 2 arrays with just the common values
	 * @param one First array to intersect
	 * @param two Second array to intersect
	 * @return Array of the intersected values
	 */
	public Integer[] intersectNoCollections(Integer[] one, Integer[] two) {
		if(ArrayUtils.isEmpty(one) || ArrayUtils.isEmpty(two)) {
			return new Integer[0];
		}
		Integer[] matches = new Integer [Math.min(one.length, two.length)];
		int pos = 0;
		for(int i = 0; i < one.length; i++) {
			for(int j = 0; j < two.length; j++) {
				if(one[i] == null || two[j] == null) {
					return new Integer[0];
				}
				if(one[i].equals(two[j])) {
					matches[pos++] = two[j];
					two[j] = Integer.MAX_VALUE;
					break;
				}
			}
		}
		Integer[] res = new Integer[pos];
		pos = 0;
		for(Integer i : matches) {
			if(i != null) {
				res[pos++] = i;
			}
		}
		return res;
	}
	
	/**
	 * Intersects 2 arrays using collections
	 * @param one First array to intersect
	 * @param two Second array to intersect
	 * @return Collection of the intersected values
	 */
	public List<Integer> intersectWithCollections(Integer[] one, Integer[] two) {
		List<Integer> union = new ArrayList<>();
		if(ArrayUtils.isEmpty(one) || ArrayUtils.isEmpty(two)) {
			return union;
		}

		List<Integer> oneList = Arrays.asList(one);
		List<Integer> twoList = new ArrayList<>();
		twoList.addAll(Arrays.asList(two));
		if(twoList.contains(null)) {
			return union;
		}
		for(Integer i : oneList) {
			if(i == null) {
				return new ArrayList<>();
			}
			if(twoList.contains(i)) {
				union.add(i);
				twoList.remove(i);
			}
		}
		return union;
	}
}
