package com.smt.kata.game;

import java.io.BufferedReader;
// JDK 11.x
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/****************************************************************************
 * <b>Title</b>: VoteCounting.java
 * <b>Project</b>: Daily-Kata
 * <b>Description: </b> Vote Counting
 * 
 * On election day, a voting machine writes data in the form ot a tab delimiter 
 * (voter_id \t candidate_id) to a text file. Write a program that reads this 
 * file as a stream and returns the top 3 candidates at any given time. If you 
 * find a voter voting more than once, report this as fraud.
 * 
 * The voting files are located in the resources/data folder
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Nov 4, 2021
 * @updates:
 ****************************************************************************/
public class VoteCounting {
	/**
	 * Location of the dictionary
	 */
	public static final String RESOURCE = "/data/voting_%d.txt";
	
	/**
	 * Candidates whom votes will be counted
	 */
	public enum Candidates {
		BILL, DAVE, BECKY, JOSE, CHRIS, DANIEL, JOSEPH, MARY
	}
	
	// Members
	private List<Integer> possibleFraudVotes = new ArrayList<>();

	/**
	 * Counts the votes for a given version
	 * @param version Version of the voter file to parse.  
	 * Example value of 1 -> voting_1.txt
	 * @return Top 3 vote getters
	 * @throws IOException If votes can't be loaded
	 */
	public List<Candidates> count(int version) throws IOException {
		List<Integer> voters = new ArrayList<>();
		Map<Candidates, Integer> votes = new HashMap<>();
		new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(String.format(RESOURCE, version))))
			.lines()
			.map(s -> s.trim().split("\\t"))
			.map(sArr -> new Object[]{Integer.parseInt(sArr[0]), Candidates.valueOf(sArr[1])})
			.forEach((data) -> {
				if(voters.contains(data[0])) {
						possibleFraudVotes.add((Integer) data[0]);
				} else {
					voters.add((Integer) data[0]);
					if(votes.containsKey((Candidates) data[1])) {
						votes.put((Candidates) data[1], votes.get((Candidates) data[1]) + 1);
					} else {
						votes.put((Candidates) data[1], 1);
					}
				}
			});
		return votes
				.entrySet()
				.stream()
				.sorted(Entry.comparingByValue(Comparator.reverseOrder()))
				.map(e -> e.getKey())
				.limit(3)
				.collect(Collectors.toList());
	}
	

	/**
	 * @return the possibleFraudVotes
	 */
	public List<Integer> getPossibleFraudVotes() {
		return possibleFraudVotes;
	}
	
	/**
	 * Method to check whether fraud may exist in the votes
	 * @return
	 */
	public boolean hasPotentialFraud() {
		return !possibleFraudVotes.isEmpty();
	}
}
