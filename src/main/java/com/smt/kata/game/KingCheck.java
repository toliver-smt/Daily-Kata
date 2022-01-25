package com.smt.kata.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.smt.kata.distance.bean.CoordinateVO;

/****************************************************************************
 * <b>Title</b>: KingCheck.java <b>Project</b>: SMT-Kata <b>Description: </b>
 * King Check
 * 
 * You are presented with an 8 by 8 matrix representing the positions of pieces
 * on a chess board. The only pieces on the board are the black king and various
 * white pieces. Given this matrix, determine whether the king is in check.
 * 
 * For details on how each piece moves, see here.
 * 
 * For example, given the following matrix:
 * 
 * { 'O','O','O','K','O','O','O','O' }, { 'O','O','O','O','O','O','O','O' }, {
 * 'O','B','O','O','O','O','O','O' }, { 'O','O','O','O','O','O','P','O' }, {
 * 'O','O','O','O','O','O','O','R' }, { 'O','O','N','O','O','O','O','O' }, {
 * 'O','O','O','O','O','O','O','O' }, { 'O','O','O','O','O','Q','O','O' }
 * 
 * You should return True, since the bishop is attacking the king diagonally.
 * 
 * 'K' = King 'Q' = Queen 'P' = Pawn 'B' = Bishop 'R' = Rook 'N' = Knight
 * 
 * <b>Copyright:</b> Copyright (c) 2021 <b>Company:</b> Silicon Mountain
 * Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Oct 6, 2021
 * @updates:
 ****************************************************************************/
public class KingCheck {
	char[][] board;

	/**
	 * Assigns the chess board to the class
	 * 
	 * @param board Chess board
	 */
	public KingCheck(char[][] board) {
		this.board = board;

	}

	/**
	 * 
	 * @param board
	 * @return
	 */
	public boolean isKingCheck() {
		if (ArrayUtils.isEmpty(board) || ArrayUtils.isEmpty(board[0])) {
			return false;
		}

		if (ArrayUtils.isEmpty(board) || ArrayUtils.isEmpty(board[0]) || board.length != 8 || board[0].length != 8)
			return false;
		CoordinateVO K;
		Map<Character, List<CoordinateVO>> pieces = parsePieces();

		if (pieces.get('K') == null) {
			return false;
		} else {
			K = pieces.get('K').get(0);
		}

		for (Character key : pieces.keySet()) {
			switch (key) {
			case 'Q':
				for (CoordinateVO q : pieces.get(key)) {
					if (checkDiag(q, K) || checkHoriz(q, K)) {
						return true;
					}
				}
				break;
			case 'B':
				for (CoordinateVO q : pieces.get(key)) {
					if (checkDiag(q, K)) {
						return true;
					}
				}
				break;
			case 'R':
				for (CoordinateVO q : pieces.get(key)) {
					if (checkHoriz(q, K)) {
						return true;
					}
				}
				break;
			case 'P':
				for (CoordinateVO q : pieces.get(key)) {
					if (checkPawn(q, K)) {
						return true;
					}
				}
				break;
			case 'N':
				for (CoordinateVO q : pieces.get(key)) {
					if (checkKnight(q, K)) {
						return true;
					}
				}
				break;
			}
		}
		return false;
	}

	public boolean checkDiag(CoordinateVO atk, CoordinateVO k) {

		if (atk.getRow() == k.getRow() || atk.getColumn() == k.getColumn()) {
			return false;
		}
		int slope = (atk.getRow() - k.getRow()) / (atk.getColumn() - k.getColumn());
		if (slope == 1 || slope == -1) {
			int yDiff = atk.getRow() > k.getRow() ? -1 : 1;
			int xDiff = atk.getColumn() > k.getColumn() ? -1 : 1;

			for (int i = atk.getRow() + yDiff; i != k.getRow(); i += yDiff) {
				for (int j = atk.getColumn() + xDiff; j != k.getColumn(); j += xDiff) {
					if (board[i][j] == 'O') {
						continue;
					} else {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	public boolean checkHoriz(CoordinateVO atk, CoordinateVO k) {

		if (atk.getColumn() == k.getColumn()) {
			for (int i = Math.min(atk.getRow(), k.getRow()) + 1; i < Math.max(atk.getRow(), k.getRow()) - 1; i++) {
				if (board[i][k.getColumn()] == 'O') {
					continue;
				} else {
					return false;
				}
			}
			return true;
		} else if (atk.getRow() == k.getRow()) {
			for (int i = Math.min(atk.getRow(), k.getRow()) + 1; i < Math.max(atk.getRow(), k.getRow()) - 1; i++) {
				if (board[k.getRow()][i] == 'O') {
					continue;
				} else {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public boolean checkPawn(CoordinateVO atk, CoordinateVO k) {
		if (atk.getRow() - 1 == k.getRow()) {
			if (atk.getColumn() + 1 == k.getColumn() || atk.getColumn() - 1 == k.getColumn()) {
				return true;
			}
		}
		return false;
	}

	public boolean checkKnight(CoordinateVO atk, CoordinateVO k) {

		if (atk.getRow() == k.getRow() || atk.getColumn() == k.getColumn()) {
			return false;
		}
		double slope = ((double) atk.getRow() - (double) k.getRow())
				/ ((double) atk.getColumn() - (double) k.getColumn());

		return Math.abs(slope) == 2 || Math.abs(slope) == 0.5;
	}

	public Map<Character, List<CoordinateVO>> parsePieces() {
		Map<Character, List<CoordinateVO>> places = new HashMap<>();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				char curr = board[row][col];
				if (curr != 'O') {
					places.putIfAbsent(curr, new ArrayList<CoordinateVO>());
					CoordinateVO temp = new CoordinateVO(row, col);
					places.get(curr).add(temp);
				}
			}
		}
		return places;
	}

}
