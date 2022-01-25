package com.smt.kata.word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.siliconmtn.data.text.StringUtil;
import com.smt.kata.distance.bean.CoordinateVO;

/****************************************************************************
 * <b>Title</b>: MatrixWordSearch.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Word Search
 * 
 * Given an m x n grid of characters board and a string word, return true if word 
 * exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cells, 
 * where adjacent cells are horizontally or vertically neighboring. The same letter 
 * cell may not be used more than once.
 * 
 * Example 1:
 * https://assets.leetcode.com/uploads/2020/11/04/word2.jpg
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * 
 * Example 2:
 * https://assets.leetcode.com/uploads/2020/11/04/word-1.jpg
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * 
 * Example 3:
 * https://assets.leetcode.com/uploads/2020/10/15/word3.jpg
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 * 
 * Constraints:
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Sep 28, 2021
 * @updates:
 ****************************************************************************/
public class MatrixWordSearch {
	
	/**
	 * Finds the word on the board with sequential boxes on the board
	 * @param board Word board to search
	 * @param word Word to find on the board
	 * @return True if found.  False otherwise
	 */
	public boolean find(String[][] board, String word) {
		if(!ArrayUtils.isEmpty(board) && !StringUtil.isEmpty(word) && !ArrayUtils.isEmpty(board[0])) {
			for(int i = 0; i < board.length; i++) {
				for(int j = 0; j < board[i].length; j++) {
					if(board[i][j].charAt(0) == word.charAt(0)) {
						List<String> coords = new ArrayList<>();
						coords.add(String.format("%d,%d", i, j));
						if(recurse(i, j, board, word.substring(1), coords)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private boolean recurse(int row, int col, String[][] board, String word, List<String> coords) {
		if(StringUtils.isEmpty(word)) {
			return true;
		}
		List<CoordinateVO> candidates = new ArrayList<>();
		candidates.add(new CoordinateVO(row + 1, col));
		candidates.add(new CoordinateVO(row - 1, col));
		candidates.add(new CoordinateVO(row, col + 1));
		candidates.add(new CoordinateVO(row, col - 1));
		char c = word.charAt(0);

		for(CoordinateVO cvo : candidates) {
			int x = cvo.getRow();
			int y = cvo.getColumn();
			if(isValid(x, y, board) && board[x][y].charAt(0) == c && !coords.contains(String.format("%d,%d", x, y))) {
				List<String> cCopy = new ArrayList<>();
				Collections.copy(coords, cCopy);
				coords.add(String.format("%d,%d", x, y));
				if(recurse(x, y, board, word.substring(1), coords)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isValid(int i, int col, String[][] board) {
		return i >= 0 && i < board.length && col >= 0 && col < board[i].length;
	}
}
