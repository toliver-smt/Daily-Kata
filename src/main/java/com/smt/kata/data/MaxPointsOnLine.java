package com.smt.kata.data;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import com.smt.kata.distance.bean.CoordinateVO;

/****************************************************************************
 * <b>Title</b>: MaxPointsOnLine.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Max Points on a Line Kata
 * 
 * Given an array of points where points[i] = [xi, yi] represents a point on the 
 * X-Y plane, return the maximum number of points that lie on the same straight line.
 * this check assumes vertical/horizontal and diagonal (45 degrees) as the lines
 * to evaluate
 * 
 * Example 1:
 * Input: points = [[1,1],[2,2],[3,3]]
 * https://assets.leetcode.com/uploads/2021/02/25/plane1.jpg
 * Output: 3
 * 
 * Example 2:
 * Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * https://assets.leetcode.com/uploads/2021/02/25/plane2.jpg
 * Output: 4
 * 
 * Constraints:
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * Each point is processed only once, even if it is duplicated
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Nov 12, 2021
 * @updates:
 ****************************************************************************/
public class MaxPointsOnLine {
	
	/**
	 * Calculates the number of points on a straight line
	 * @param points Points on the graph
	 * @return Max points in any straight line (vertical, horizontal and diagonal)
	 */
	public int findMax(int[][] points) {
		int maxPoints = 0;
		if(!ArrayUtils.isEmpty(points)) {
			Set<CoordinateVO> coords = convertToSet(points);
			if(coords.isEmpty()) {
				maxPoints = 0;
			} else {
				for(CoordinateVO c : coords) {
					maxPoints = Math.max(checkVert(c, coords), maxPoints);
					maxPoints = Math.max(checkHoriz(c, coords), maxPoints);
					maxPoints = Math.max(checkDiag(c, coords, 1), maxPoints);
					maxPoints = Math.max(checkDiag(c, coords, -1), maxPoints);
				}
			}
		}
		return maxPoints;
	}

	private int checkDiag(CoordinateVO origin, Set<CoordinateVO> coords, int slope) {
		int dCount = 0;
		for(CoordinateVO c : coords) {
			if(!c.equals(origin) && origin.getColumn() != c.getColumn() && origin.getRow() != c.getRow() && (double)(origin.getColumn() - c.getColumn()) / (double)(origin.getRow() - c.getRow()) == slope) {
				if(dCount == 0) dCount++;
				dCount++;
			}
		}
		return dCount;
	}

	private int checkVert(CoordinateVO origin, Set<CoordinateVO> coords) {
		int vertCount = 0;
		for(CoordinateVO c : coords) {
			if(!c.equals(origin) && origin.getColumn() == c.getColumn()) {
				if(vertCount == 0) vertCount++;
				vertCount++;
			}
		}
		return vertCount;
	}

	private int checkHoriz(CoordinateVO origin, Set<CoordinateVO> coords) {
		int hCount = 0;
		for(CoordinateVO c : coords) {
			if(hCount == 0) hCount++;

			if(!c.equals(origin) && origin.getRow() == c.getRow()) {
				hCount++;
			}
		}
		return hCount;
	}

	private Set<CoordinateVO> convertToSet(int[][] points) {
		Set<CoordinateVO> coords = new HashSet<>();
		for(int[] line : points) {
			if(line != null && line.length == 2) {
				coords.add(new CoordinateVO(line[0], line[1]));
			}
		}
		return coords;
	}
}
