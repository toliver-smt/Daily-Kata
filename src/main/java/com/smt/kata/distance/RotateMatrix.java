package com.smt.kata.distance;

/****************************************************************************
 * <b>Title</b>: RotateMatrix.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Rotate Matrix
 * Given an N by N matrix, rotate it by 90 degrees clockwise. * 
 * For example, given the following matrix:
 * 
 * [[1, 2, 3],
 *  [4, 5, 6],
 *  [7, 8, 9]]
 * 
 * you should return:
 * 
 * [[7, 4, 1],
 *  [8, 5, 2],
 *  [9, 6, 3]]
 * 
 * We should also rotate it counter-clockwise
 * 
 * [[1, 2, 3],
 *  [4, 5, 6],
 *  [7, 8, 9]]
 * 
 * you should return:
 * 
 * [[3, 6, 9],
 *  [2, 5, 8],
 *  [1, 4, 7]]
 * 
 * We can assume the matrix will have the same number of rows and columns for 
 * this kata
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Jun 28, 2021
 * @updates:
 ****************************************************************************/
public class RotateMatrix {

	/**
	 * Rotates a matrix in the clockwise direction
	 * @param matrix Matrix to rotate clockwise
	 * @return Rotated matrix.  Empty matrix of input is null
	 */
	public int[][] rotateClockwise(int[][] matrix) {
		if(matrix == null) return new int[0][0];
		printMatrix(matrix);

		int size = matrix.length;
		for(int x = 0; x < size / 2; x++) {
			for(int y = x; y < size - x - 1; y++) {
				int temp = matrix[x][y];
				
				matrix[x][y] = matrix[size - 1 - y][x];	//0,0
				matrix[size - 1 - y][x] = matrix[size - 1 - x][size - 1 - y]; // 0, 1;
				matrix[size - 1 - x][size - 1 - y] = matrix[y][size - 1 - x]; //1, 1
				matrix[y][size - 1 - x] = temp; // 1, 0
				
				printMatrix(matrix);
			}
		}
		return matrix;
	}
	
	/**
	 * Rotates a matrix in the counter-clockwise direction
	 * @param matrix Matrix to rotate clockwise
	 * @return Rotated matrix.  Empty matrix of input is null
	 */
	public int[][] rotateCounterClockwise(int[][] matrix) {
		if(matrix == null) return new int[0][0];
		printMatrix(matrix);
		int size = matrix.length;
		for(int x = 0; x < size / 2; x++) {
			for(int y = x; y < size - x - 1; y++) {
				int temp = matrix[x][y];
				matrix[x][y] = matrix[y][size - 1 - x];
				matrix[y][size - 1 - x] = matrix[size - 1 - x][size - 1 - y];
				matrix[size - 1 - x][size - 1 - y] = matrix[size - 1 - y][x];
				matrix[size - 1 - y][x] = temp;
				

				printMatrix(matrix);
			}
		}
		return matrix;
	}

	public void printMatrix(int[][] matrix) {
		for(int x = 0; x < matrix.length; x++) {
			System.out.print('[');
			for(int y = 0; y < matrix[x].length; y++) {
				System.out.print(matrix[x][y] + ",");
			}
			System.out.println(']');
		}
		System.out.println("");
	}
}
