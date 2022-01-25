package com.smt.kata.tree;

import java.util.HashSet;
import java.util.Set;

/****************************************************************************
 * <b>Title</b>: WallsAndGates.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Walls and Gates Kata
 * 
 * You are given a m x n 2D grid initialized with these three possible values.
 * 
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to r
 *       epresent INF as you may assume that the distance to a gate is less than 2147483647.
 * 
 * Fill each empty room with the distance to its nearest gate. If it is impossible 
 * to reach a gate, it should be filled with INF.
 * 
 * Example: 
 * 
 * Given the 2D grid:
 * 
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 *   
 *   After running your function, the 2D grid should be:
 * 
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 *   
 *  **** Hint: Try using a depth first search algorithm
 *   
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Aug 29, 2021
 * @updates:
 ****************************************************************************/
public class WallsAndGates {
	Set<String> visited;
	/**
	 * Assigns the distance from each room to a gate
	 * @param rooms Matrix of rooms, gates and walls
	 */
	public int[][] assign(int[][] rooms) {
		
		if(rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
			return new int[0][];
		}
		int[][] res = new int[rooms.length][rooms[0].length];
		for(int i = 0; i < rooms.length; i++) {
			for(int j = 0; j < rooms[i].length; j++) {
				if(rooms[i][j] == -1 || rooms[i][j] == 0) {
					res[i][j] = rooms[i][j];
				}
				else {
					visited = new HashSet<>();
					res[i][j] = calcDistance(i, j, rooms, 0);
				}
			}
		}
		return res;
	}

	private int calcDistance(int x, int y, int[][] rooms, int distance) {
		String key = x + "," + y;
		if(x < 0 || x > rooms.length-1 || y < 0 || y > rooms[x].length -1 || visited.contains(key) || rooms[x][y] == -1) {
			return 99999999;
		}
		if(rooms[x][y] == 0) {
			return distance;
		} else {
			visited.add(key);
		}
		int left = calcDistance(x - 1, y, rooms, distance + 1);
		int right = calcDistance(x + 1, y, rooms, distance + 1);
		int up = calcDistance(x, y + 1, rooms, distance + 1);
		int down = calcDistance(x, y - 1, rooms, distance + 1);
		
		return Math.min(Math.min(Math.min(left, right), up), down);
	}
}
