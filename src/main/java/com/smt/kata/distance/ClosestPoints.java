package com.smt.kata.distance;

import org.apache.commons.lang3.ArrayUtils;

/****************************************************************************
 * <b>Title</b>: ClosestPoints.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Closest points Kata
 * 
 * Given a set of points (x, y) on a 2D cartesian plane, find the two closest points. 
 * For example, given the points [
 * 		(1, 1), 
 * 		(-1, -1), 
 * 		(3, 4), 
 * 		(6, 1), 
 * 		(-1, -6), 
 * 		(-4, -3)
 * ], 
 * 
 * return [(-1, -1), (1, 1)]
 * 
 * Constraints:
 * points length > 1
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Dec 16, 2021
 * @updates:
 ****************************************************************************/
public class ClosestPoints {

	/**
	 * Finds the two closest points and returns them
	 * @param points Points to calculate distance against
	 * @return Closest points
	 */
	public Integer[][] calculateClosestPoints(Integer[][] points) {
		Integer [][] res = new Integer[0][0];
		if(ArrayUtils.isNotEmpty(points)) {
			double distance = Double.MAX_VALUE;
			for(int i = 0; i < points.length; i++) {
				for(int j = i+1; j < points.length; j++) {
					double d = Math.sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
					if(d < distance) {
						distance = d;
						res = new Integer[2][];
						res[0] = new Integer[] {points[i][0], points[i][1]};
						res[1] = new Integer[] {points[j][0], points[j][1]};
					}
				}
			}
		}
		return res;
	}
}
