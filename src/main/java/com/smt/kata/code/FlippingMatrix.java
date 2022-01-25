package com.smt.kata.code;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;

/****************************************************************************
 * <b>Title</b>: FlippingMatrix.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Score After Flipping Matrix Kata
 * 
 * You are given an m x n binary matrix grid.
 * 
 * A move consists of choosing any row or column and toggling each value in that 
 * row or column (i.e., changing all 0's to 1's, and all 1's to 0's).
 * 
 * Every row of the matrix is interpreted as a binary number, and the score of 
 * the matrix is the sum of these numbers.
 * 
 * Return the highest possible score after making any number of moves (including zero moves).
 * 
 * https://assets.leetcode.com/uploads/2021/07/23/lc-toogle1.jpg
 * 
 * Example 1:
 * Input: grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * Output: 39
 * Explanation: 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 * 
 * Example 2:
 * Input: grid = [[0]]
 * Output: 1
 * 
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 20
 * grid[i][j] is either 0 or 1.
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Nov 30, 2021
 * @updates:
 ****************************************************************************/
public class FlippingMatrix {

	int [][] matrix;
	/**
	 * Calculates the sum of the binaries for the matrix with as many moves as needed
	 * @param matrix Matrix to calculate against
	 * @return Sum of the binary values for each row
	 */
	public int calculate(int[][] matrix) {
		return ArrayUtils.isNotEmpty(this.matrix = matrix) && ArrayUtils.isNotEmpty(this.matrix[0]) && checkValid() ? makeMax() : 0;
	}

	private int makeMax() {
		///Flip Rows
		IntStream.range(0, matrix.length).filter(i -> matrix[i][0] == 0).forEach(i -> invertRow(i));

		///Flip Columns
		IntStream.range(1, matrix[0].length).filter(i -> IntStream.range(0, matrix.length).map(j -> matrix[j][i]).sum() < matrix[0].length / 2).forEach(i -> invertCol(i));		

		///Return Binary sum. 
		return Arrays.stream(matrix).mapToInt(r -> Integer.parseInt(Arrays.stream(r).boxed().map(i -> i.toString()).collect(Collectors.joining("")), 2)).sum();
	}

	private void invertCol(int colNum) {
		IntStream.range(0, matrix.length).forEach(i -> matrix[i][colNum] = matrix[i][colNum] == 0 ? 1 : 0);
	}

	private void invertRow(int rowNum) {
		IntStream.range(0, matrix[rowNum].length).forEach(i -> matrix[rowNum][i] = matrix[rowNum][i] == 0 ? 1 : 0);
	}

	private boolean checkValid() {
		return Arrays.stream(matrix).filter(r -> r == null || r.length != matrix[0].length).count() == 0 && Arrays.stream(matrix).flatMapToInt(x -> Arrays.stream(x)).filter(i -> i != 0 && i != 1).count() == 0;  
	}
}
