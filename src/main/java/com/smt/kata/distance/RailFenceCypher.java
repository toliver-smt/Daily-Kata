package com.smt.kata.distance;

/****************************************************************************
 * <b>Title:</b> RailFenceCypher.java
 * <b>Project:</b> SMT-Kata
 * <b>Description:</b> Rail Fence Cypher
 * Implement encoding and decoding for the rail fence cipher.
 * 
 * The Rail Fence cipher is a form of transposition cipher that gets its name from 
 * the way in which it's encoded. It was already used by the ancient Greeks.
 * 
 * In the Rail Fence cipher, the message is written downwards on successive "rails" 
 * of an imaginary fence, then moving up when we get to the bottom (like a zig-zag). 
 * Finally the message is then read off in rows.
 * 
 * For example, using three "rails" and the message "WE ARE DISCOVERED FLEE AT ONCE", 
 * the cipherer writes out:
 * 
 * W . . . E . . . C . . . R . . . L . . . T . . . E
 * . E . R . D . S . O . E . E . F . E . A . O . C .
 * . . A . . . I . . . V . . . D . . . E . . . N . .
 * Then reads off:
 * 
 * WECRLTEERDSOEEFEAOCAIVDEN
 * To decrypt a message you take the zig-zag shape and fill the ciphertext along the rows.
 * 
 * ? . . . ? . . . ? . . . ? . . . ? . . . ? . . . ?
 * . ? . ? . ? . ? . ? . ? . ? . ? . ? . ? . ? . ? .
 * . . ? . . . ? . . . ? . . . ? . . . ? . . . ? . .
 * The first row has seven spots that can be filled with "WECRLTE".
 * 
 * W . . . E . . . C . . . R . . . L . . . T . . . E
 * . ? . ? . ? . ? . ? . ? . ? . ? . ? . ? . ? . ? .
 * . . ? . . . ? . . . ? . . . ? . . . ? . . . ? . .
 * Now the 2nd row takes "ERDSOEEFEAOC".
 * 
 * W . . . E . . . C . . . R . . . L . . . T . . . E
 * . E . R . D . S . O . E . E . F . E . A . O . C .
 * . . ? . . . ? . . . ? . . . ? . . . ? . . . ? . .
 * Leaving "AIVDEN" for the last row.
 * 
 * W . . . E . . . C . . . R . . . L . . . T . . . E
 * . E . R . D . S . O . E . E . F . E . A . O . C .
 * . . A . . . I . . . V . . . D . . . E . . . N . .
 * 
 * If you now read along the zig-zag shape you can read the original message.
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Mar 22, 2021
 * <b>updates:</b>
 * 
 ****************************************************************************/
import java.util.*;
public class RailFenceCypher {
	
	/**
	 * 
	 */
	public RailFenceCypher() {
		super();
	}

	public String encodePhrase(String phrase) {
		//validate
		if (phrase == null || phrase.isBlank()) return "";
		
		//create a string of our message, coded
		String firstRow = "";
		String secondRow = "";
		String thirdRow = "";
		phrase = phrase.replaceAll(" ", "").toUpperCase();
		for (int i=0; i<phrase.length(); i++) {
			if (i%4 == 0 || i == 0) {
				firstRow = firstRow + phrase.charAt(i);
			} else if (i%2 == 0) {
				thirdRow = thirdRow + phrase.charAt(i);
			} else {
				secondRow = secondRow + phrase.charAt(i);
			}
		}
		String codedPhrase = firstRow + secondRow + thirdRow;
		return codedPhrase;
    }
    
    /**
     * Returns the matrix of the phrase
     * @param phrase Phrase to encode into the matrix
     * @return Matrix of the cypher
     */
    public char[][] getMatrixFromPhrase(String phrase) {
		//validate
		if (phrase == null || phrase.isBlank()) return new char[0][0];
		
		//create phrase
        String myPhrase = encodePhrase(phrase);
        
        //create matrix
        char[][] matrix = new char[3][myPhrase.length()];
        Arrays.fill(matrix[0], '.');
        Arrays.fill(matrix[1], '.');
        Arrays.fill(matrix[2], '.');
        //populate matrix
        int j = 0;
        for (int i=0; i<myPhrase.length(); i = i + 4) {
        	matrix[0][i] = myPhrase.charAt(j);
        	j++;
        }
        for (int i=1; i<myPhrase.length(); i = i + 2) {
        	matrix[1][i] = myPhrase.charAt(j);
        	j++;
        }
        for (int i=2; i<myPhrase.length(); i = i + 4) {
        	matrix[2][i] = myPhrase.charAt(j);
        	j++;
        }
        return matrix;
    }
}
