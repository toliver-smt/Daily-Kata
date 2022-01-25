package com.smt.kata.distance;

import java.util.HashMap;
import java.util.HashSet;
// JDK 11.x
import java.util.List;
import java.util.Map;
import java.util.Set;

// Kata Libs
import com.smt.kata.distance.bean.Rectangle;

/****************************************************************************
 * <b>Title</b>: RangeSumQuery.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Range Sum Query Kata
 * 
 * Given a 2D matrix matrix, handle multiple queries of the following type:
 * 
 * Calculate the sum of the elements of matrix inside the rectangle defined by 
 * its upper left corner (row1, col1) and lower right corner (row2, col2).
 * 
 * Implement the NumMatrix class:
 * 
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the 
 * elements of matrix inside the rectangle defined by its upper 
 * left corner (row1, col1) and lower right corner (row2, col2).
 * 
 * Allow multiple rectangles to be used inside the matrix.  If the rectangles overlap, 
 * those coordinates may be utilized only once when summing the values
 * 
 * ******* Note: Modifications to the CoordinateVO and Rectangle Class may be appropriate
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Aug 31, 2021
 * @updates:
 ****************************************************************************/
public class RangeSumQuery {

	// Members
	protected int[][] matrix;
	
	/**
	 * Initializes the class with a matrix to use to calculate against
	 * @param matrix Matrix to use for calculating sums
	 */
	public RangeSumQuery(int[][] matrix) {
		super();
		this.matrix = matrix;
	}
	
	/**
	 * Sums the values from the provided matrix that are inside the rectangle
	 * @param areas Rectangles to sum the areas
	 * @return Sum of all cells inside the rectangles
	 */
	public int sumRange(List<Rectangle> areas) {
		int sum = 0;
		if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0 || areas == null || areas.isEmpty()) {
			return sum;
		} else {
			Map<Integer, Set<Integer>> points = buildMap();
			for(Rectangle r : areas) {
				if(r.getTopLeft() == null || r.getBottomRight() == null) {
					return 0;
				} if(isValid(r)) {
					sum += calculateSum(points, r);
				}
			}
		}
		return sum;
	}

	private int calculateSum(Map<Integer, Set<Integer>> points, Rectangle r) {
		int sum = 0;
		for(int i = r.getTopLeft().getRow(); i <= r.getBottomRight().getRow() && i < matrix.length; i++) {
			for(int j = r.getTopLeft().getColumn(); j <= r.getBottomRight().getColumn() && j < matrix[i].length; j++) {
				if(points.get(i).contains(j)) {
					sum += matrix[i][j];
					points.get(i).remove(j);
				} else {
					System.out.println("overlap");
				}
			}
		}
		return sum;
	}

	private boolean isValid(Rectangle r) {
		boolean tl = r.getTopLeft().getRow() >= 0 && r.getTopLeft().getRow() < matrix.length && r.getTopLeft().getColumn() >= 0 && r.getTopLeft().getColumn() < matrix[0].length;
		boolean br = r.getBottomRight().getRow() > r.getTopLeft().getRow() && r.getBottomRight().getRow() <= matrix.length && r.getBottomRight().getColumn() > r.getTopLeft().getColumn() && r.getBottomRight().getColumn() <= matrix[0].length;
		return tl && br;
	}

	private Map<Integer, Set<Integer>> buildMap() {
		Map<Integer, Set<Integer>> valid = new HashMap<>();
		for(int i = 0; i < matrix.length; i++) {
			Set<Integer> cols = new HashSet<>();
			for(int j = 0; j < matrix[i].length; j++) {
				cols.add(j);
			}
			valid.put(i, cols);
		}
		return valid;
	}

}
