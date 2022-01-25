package com.smt.kata.game;

import java.util.HashSet;
// JDK 11.x
import java.util.List;
import java.util.Map;
import java.util.Set;

/****************************************************************************
 * <b>Title</b>: LazyBartender.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Lazy Bartender Kata
 * 
 * At a popular bar, each customer has a set of favorite drinks, and will happily 
 * accept any drink among this set. For example, in the following situation, customer 
 * 0 will be satisfied with drinks 0, 1, 3, or 6.
 * 
 * preferences = {
 *     0: [0, 1, 3, 6],
 *     1: [1, 4, 7],
 *     2: [2, 4, 7, 5],
 *     3: [3, 2, 5],
 *     4: [5, 8]
 * }
 * 
 * A lazy bartender working at this bar is trying to reduce his effort by limiting 
 * the drink recipes he must memorize. Given a dictionary input such as the one 
 * above, return the fewest number of drinks he must learn in order to satisfy 
 * all customers.
 * 
 * For the input above, the answer would be 2, as drinks 1 and 5 will satisfy everyone.
 * 
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Nov 1, 2021
 * @updates:
 ****************************************************************************/
public class LazyBartender {

	/**
	 * Finds the minimum number of drinks the bartender has to memorize to satisfy all customers
	 * @param custDrinks Map of customers and their drink preferences
	 * @return Minimum number of drinks to memorize.
	 */
	public int minimumTypes(Map<Integer, List<Integer>> custDrinks) {
		return custDrinks == null ? 0 : countDrinks(custDrinks, 0, new HashSet<Integer>());
	}

	int countDrinks(Map<Integer, List<Integer>> custDrinks, int cust, Set<Integer> drinks) {
		//If customer doesn't exist, return drinks size.
		if(!custDrinks.containsKey(cust)) {
			return drinks.size();
		}

		// Set numDrinks to max
		int numDrinks = Integer.MAX_VALUE;

		// For each drink of a given customer, determine how many customers want that drink.
		for(int drink : custDrinks.get(cust)) {

			// Clone Drinks
			Set<Integer> newDrinks = new HashSet<>(drinks);

            // Add drink (set doesn't allow duplicates)
			newDrinks.add(drink);

			//Check next customer for drink preference.  We want the minimum number returned here.
			numDrinks= Math.min(numDrinks, countDrinks(custDrinks, cust + 1, newDrinks));
		}

		return numDrinks;
	}
}
