package com.smt.kata.tree;

import java.util.ArrayList;
// JDK 11.x
import java.util.List;
import java.util.stream.Collectors;

/****************************************************************************
 * <b>Title</b>: LongestTreePath.java
 * <b>Project</b>: SMT-Kata
 * <b>Description: </b> Longest Tree Path
 * Given a tree where each edge has a weight, compute the length of the longest path in the tree.
 * 
 * For example, given the following tree:
 *
 *	   a
 *	  /|\
 *	 b c d
 *	    / \
 *	   e   f
 *	  / \
 *	 g   h
 * 
 * and the weights: a-b: 3, a-c: 5, a-d: 8, d-e: 2, d-f: 4, e-g: 1, e-h: 1, the 
 * longest path would be c -> a -> d -> f, with a length of 17.
 *
 * The path does not have to pass through the root, and each node can have any amount of children.
 * Each node on the tree can only be used once.  This means that once you hit a leaf, the 
 * path is at the end, as you would have to backtrack and use the same node twice
 * 
 * The root path will always have a weight of 0.
 *
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Jun 24, 2021
 * @updates:
 ****************************************************************************/
public class LongestTreePath {

	
	public int caclculatePath(List<KataNode<Integer>> nodes){
        long startTime = System.currentTimeMillis();
        int result = 0;
        if(nodes != null && !nodes.isEmpty()) {
			List<KataNode<Integer>> roots = nodes.stream().filter(n -> n.getParentId() == null).collect(Collectors.toList());
			nodes = nodes.stream().filter(n -> n.getParentId() != null).collect(Collectors.toList());
			if(!roots.isEmpty()) {
				KataTree<Integer> tree = new KataTree<>(nodes, roots.get(0));
				List<KataNode<Integer>> linkedNodes = loadNodes(tree.getRootNode());
		        for(int x = 0; x < 1000000; x++){
		            result = caclculatePathWrapper(linkedNodes);
				}
			}
        }
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println("Total execution time: " + (endTime) + "ms");
        return result;
        
    }
	
	/**
	 * Calculates the longest path for the assigned weights at each node
	 * @param nodes Collection of nodes to calculate against
	 * @return Sum of the weights from each node in the path
	 */
	public int caclculatePathWrapper(List<KataNode<Integer>> nodes) {
		int longPath = 0;
		for(KataNode<Integer> n : nodes) {
			//longPath = Math.max(calculatePath(n, new ArrayList<KataNode<Integer>>()), longPath);
			longPath = Math.max(calculatePath(n, null), longPath);
		}
		return longPath;
	}

	private int calculatePath(KataNode<Integer> n, KataNode<Integer> p) {
		int pathDepth = n.getData();

		// Do we need to go up the parent
		if(n.getParent()!= null && (p == null || n.getParent().compareTo(p) != 0)) {
			pathDepth = Math.max(pathDepth, n.getData() + calculatePath(n.getParent(),n));
		}

		/// Do we process the children
		for(KataNode<Integer> c : n.getChildren()) {
			if(p == null || c.compareTo(p) != 0) {
				pathDepth = Math.max(pathDepth, n.getData() + calculatePath(c, n));
			}
		}

		return pathDepth;
	}
//	private int calculatePath(KataNode<Integer> n, List<KataNode<Integer>> traversed) {
//		int pathDepth = n.getData();
//		List<KataNode<Integer>> connections = getConnections(n, traversed);
//		if(!connections.isEmpty()) {
//			for(KataNode<Integer> c : connections) {
//				List<KataNode<Integer>> newList = new ArrayList<>(traversed);
//				newList.add(n);
//				pathDepth = Math.max(pathDepth, n.getData() + calculatePath(c, newList));
//			}
//		}
//		return pathDepth;
//	}

	private List<KataNode<Integer>> getConnections(KataNode<Integer> node, List<KataNode<Integer>> traversed) {
		List<KataNode<Integer>> nodes = new ArrayList<>();
		if(node.getParent() != null && !traversed.contains(node.getParent())) {
			nodes.add(node.getParent());
		}
		for(KataNode<Integer> n : node.getChildren()) {
			if(!traversed.contains(n)) {
				nodes.add(n);
			}
		}
		return nodes;
	}

	private List<KataNode<Integer>> loadNodes(KataNode<Integer> node) {
		List<KataNode<Integer>> nodes = new ArrayList<>();
		List<KataNode<Integer>> leaves = new ArrayList<>();
		nodes.add(node);
		for(KataNode<Integer> n : node.getChildren()) {
			nodes.addAll(loadNodes(n));
		}
		for(KataNode<Integer> n : nodes) {
			if(n.isLeaf()) {
				leaves.add(n);
			}
		}
		return leaves;
	}

}
