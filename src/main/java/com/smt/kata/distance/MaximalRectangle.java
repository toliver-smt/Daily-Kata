package com.smt.kata.distance;

import org.apache.commons.lang3.ArrayUtils;

/****************************************************************************
 * <b>Title</b>: MaximalRectangle.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Maximal Rectangle
 * 
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * https://assets.leetcode.com/uploads/2020/09/14/maximal.jpg
 * 
 * Example 1:
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 6
 * Explanation: The maximal rectangle is shown in the above picture.
 * 
 * Example 2:
 * Input: matrix = []
 * Output: 0

 * Example 3:
 * Input: matrix = [["0"]]
 * Output: 0
 * 
 * Example 4:
 * Input: matrix = [["1"]]
 * Output: 1
 * 
 * Example 5:
 * Input: matrix = [["0","0"]]
 * Output: 0
 * 
 * Constraints:
 * rows == matrix.length
 * cols == matrix[i].length
 * 0 <= row, cols <= 200
 * matrix[i][j] is '0' or '1'.
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Sep 28, 2021
 * @updates:
 ****************************************************************************/
public class MaximalRectangle {

	/**
	 * Calculates the largest rectangle of all ones
	 * @param matrix Matrix to calculate against
	 * @return Count of all of the "1"s in the rectangle
	 */
	public int calculate(int[][] matrix) {
		int max = 0;

		if(ArrayUtils.isNotEmpty(matrix) && ArrayUtils.isNotEmpty(matrix[0])) {
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix[i].length; j++) {
					if(matrix[i][j] == 1 && (checkBox(matrix, i+1, j) || checkBox(matrix, i, j+1))) {
						int calc = checkRect(matrix, i, j);
						max = Math.max(calc, max);
					} else if(matrix[i][j] == 1 && max == 0) {
						max = 1;
					}
				}
			}
		}
		return max;
	}

	private boolean checkBox(int[][] matrix, int i, int j) {
		return i < matrix.length && j < matrix[i].length && matrix[i][j] == 1;
	}

	private int checkRect(int[][] matrix, int i, int j) {
		int right = 1; int down = 1;
		while(checkBox(matrix, i + down, j)) {
			down++;
		}
		while(checkBox(matrix, i, j + (right))) {
			right++;
		}
		while(checkSum(matrix, i, j, right, down) != right * down) {
			down--;
		}
		return right * down;
	}

	private int checkSum(int[][] matrix, int i, int j, int right, int down) {
		int sum = 0;
		for(int x = i; x < i + down; x++) {
			for(int y = j; y < right+j; y++) {
				if(matrix[x][y] == 1) {
					sum++;
				}
			}
		}
		return sum;
	}
	
}
