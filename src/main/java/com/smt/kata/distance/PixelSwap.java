package com.smt.kata.distance;

/****************************************************************************
 * <b>Title</b>: PixelSwap.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Pixel Swap Kata
 * 
 * Given a 2-D matrix representing an image, a location of a pixel in the screen 
 * and a color C, replace the color of the given pixel and all adjacent same 
 * colored pixels with C.
 * 
 * For example, given the following matrix, and location pixel of (2, 2), and 'G' for green:
 * 
 * B B W
 * W W W
 * W W W
 * B B B
 * 
 * Becomes
 * 
 * B B G
 * G G G
 * G G G
 * B B B
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Jun 28, 2021
 * @updates:
 ****************************************************************************/
import java.util.*;

public class PixelSwap {
	
	char[][] pixels = new char[][] {
		{ 'B', 'B', 'W' },
		{ 'W', 'W', 'W' },
		{ 'B', 'W', 'B' },
		{ 'B', 'W', 'W' }
	};
	
	public static void main(String[] args) {
		PixelSwap x = new PixelSwap();
		char[][] result = x.swap(x.pixels, new int[] {2, 2}, 'G');
		x.printMatrix(result);
	}
	
	/**
	 * Swaps the pixels at the given coords and contiguous coords with the same original color
	 * @param pixels Matrix of pixels
	 * @param coords Starting coords
	 * @param color New color in those 0ixels
	 * @return Updated pixel map
	 */
	public char[][] swap(char[][] pixels, int[] coords, char color) {
		//determine the original color
		char myChar = pixels[coords[0]][coords[1]];
		//change the color of the selected element
		pixels[coords[0]][coords[1]] = color;
		//loop through all adjacent elements in the matrix
		for (int i = coords[0]-1; i<=coords[0]+1; i++) {
			for (int j = coords[1]-1; j<=coords[1]+1; j++) {
				if (i >= 0 && i < pixels.length && j >= 0 && j<pixels[i].length) {
					//If the element is the same as the original call swap again with updated coordinates
					if (pixels[i][j] == myChar) {
						swap(pixels, new int[] {i, j}, color);
					}
				}
			}
		}
		return pixels;
	}
	
	public void printMatrix(char[][] pixels) {
		for (int i = 0; i<pixels.length; i++) {
			System.out.println();
			for (int j = 0; j<pixels[i].length; j++) {
				System.out.print(pixels[i][j] + "  ");
			}
		}
	}
}
