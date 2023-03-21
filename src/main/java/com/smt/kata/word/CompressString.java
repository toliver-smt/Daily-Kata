package com.smt.kata.word;

/****************************************************************************
 * <b>Title</b>: CompressString.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Compress a string such that 'AAABCCDDDD' becomes 
 * 'A3BCCD4'. Only compress the string if it saves space.
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Jan 7, 2021
 * @updates:
 ****************************************************************************/
public class CompressString {
	/**
	 * Compress the string by combining a character with the number of characters when concurrent 
	 * characters exceeds 2 characters
	 * @param startNumber
	 * @return
	 */
	public String compress(String val){
		String myString = "";
		int c = 1;
		int j;
		for (int i=0; i<val.length(); i++) {
			j = i + 1;
			if (val.charAt(i) == val.charAt(j)) {
				c++;
			} else if (c > 2) {
				myString = myString + val.charAt(i) + c;
				val = val.substring(j);
				c = 1;
				System.out.println("location 1 " + val + " " + i);
			} else {
				myString = myString + val.charAt(i);
				val = val.substring(j);
				c = 1;
				System.out.println("location 2 " + val + " " + i);
			}
		}
		System.out.println(myString);
		return myString;
	}
}
