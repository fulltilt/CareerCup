package trees;

import java.util.*;

public class BinarySearchTree {
	private Node root = null;	// Root node pointer. Will be null for an empty tree.

  	private static class Node {
		Node leftChild = null;
		Node rightChild = null;
		int data;

    	Node(int newData) { data = newData; }
	}

	/**
	    Returns true if the given target is in the binary tree.
	    Uses a recursive helper.
	*/
  	public boolean find(int data) {
    	return(find(root, data));
  	}

  	private boolean find(Node node, int data) {
    	if (node == null)
      		return(false);

    	if (data == node.data)
      		return(true);
    	else if (data < node.data)
      		return(find(node.leftChild, data));
    	else
      		return(find(node.rightChild, data));
  	}


	/**
   		Inserts the given data into the binary tree.
   		Uses a recursive helper.
  	*/
  	public void insert(int data) {
    	root = insert(root, data);
  	}

  	private Node insert(Node node, int data) {
    	if (node == null)
      		node = new Node(data);
    	else {
      		if (data <= node.data)
        		node.leftChild = insert(node.leftChild, data);
      		else
        		node.rightChild = insert(node.rightChild, data);
    	}

    	return node; // in any case, return the new pointer to the caller
  	}
	/******************************/

	public void print() {
		print(root);
	}

	private void print(Node node) {
		if (node == null)
			return;

		print(node.leftChild);
		System.out.println(node.data);
		print(node.rightChild);
	}
	/******************************/

	public int getSize() {
		return getSize(root);
	}

	private int getSize(Node node) {
		if (node == null)
			return 0;

		return 1 + getSize(node.leftChild) + getSize(node.rightChild);
	}
	/******************************/

	public int getHeight() {
		return getHeight(root);
	}

	private int getHeight(Node node) {
		if (node == null)
			return 0;

		return 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
	}
	/******************************/
	public boolean hasPathSum(int sum) {
		 return hasPathSum(root, sum);
	}

	private boolean hasPathSum(Node node, int sum) {
		if (node == null)
			return (sum == 0);

		return hasPathSum(node.leftChild, sum - node.data) || hasPathSum(node.rightChild, sum - node.data);
	}
	/******************************/

	public void printPaths() {
		int[] path = new int[1000];
		printPaths(root, path, 0);
	}

	private void printPaths(Node node, int[] path, int pathLength) {
		if (node == null)
			return;

		path[pathLength] = node.data;
		++pathLength;

		// check if node is a leaf
		if (node.leftChild == null && node.rightChild == null)
			printArray(path, pathLength);
		else {
			printPaths(node.leftChild, path, pathLength);
			printPaths(node.rightChild, path, pathLength);
		}
	}

	// used for printPaths
	private void printArray(int[] path, int pathLength) {
		for (int i = 0; i < pathLength; i++)
			System.out.print(path[i] + " ");
		System.out.println();
	}
	/******************************/

	public void mirror() {
		Node newRoot = mirror(root);
		printByLevel(newRoot);
	}

	private Node mirror(Node node) {
		if (node == null)
			return null;

		Node newNode = new Node(node.data);
		newNode.leftChild = mirror(node.rightChild);
		newNode.rightChild = mirror(node.leftChild);

		return newNode;
	}

	/******************************/
	public void printByLevel() {
		printByLevel(root);
	}

	private void printByLevel(Node node) {
		if (node == null)
			return;

		java.util.LinkedList<Node> currentLevel = new java.util.LinkedList<Node>();
		java.util.LinkedList<Node> children = new java.util.LinkedList<Node>();
		currentLevel.add(node);

		while (!currentLevel.isEmpty()) {
			// print out the values of the current level
			for (Node n : currentLevel) {
				System.out.print(n.data + " ");

				if (n.leftChild != null)
					children.add(n.leftChild);
				if (n.rightChild != null)
					children.add(n.rightChild);
			}
			currentLevel.clear();
			System.out.println();

			currentLevel.addAll(children);
			children.clear();
		}
	}
	/******************************/

	public void reversePrintByLevel() {
		reversePrintByLevel(root);
	}

	private void reversePrintByLevel(Node node) {
		if (node == null)
			return;

		LinkedList<Node> currentLevel = new LinkedList<Node>();
		LinkedList<Node> children = new LinkedList<Node>();
		Stack<Node> stack = new Stack<Node>();
		currentLevel.add(node);

		while (!currentLevel.isEmpty()) {
			for (Node n : currentLevel) {
				stack.push(n);	// push values onto a stack

				if (n.rightChild != null)
					children.add(n.rightChild);
				if (n.leftChild != null)
					children.add(n.leftChild);
			}
			currentLevel.clear();
			currentLevel.addAll(children);
			children.clear();

			stack.push(null);	// null value to specify a newline when we eventually print
		}

		while (!stack.isEmpty()) {		// print levels in reverse
			Node tempNode = stack.pop();

			if (tempNode == null)
				System.out.println();
			else
				System.out.print(tempNode.data + " ");
		}
	}
	/******************************/

	public void doubleTree() {
		doubleTree(root);
	}

	private void doubleTree(Node node) {
		if (node == null)
			return;

		Node newNode = new Node(node.data);
		newNode.leftChild = node.leftChild;
		node.leftChild = newNode;

		doubleTree(newNode.leftChild);
		doubleTree(node.rightChild);		// tricky part here is that if we did newNode.rightChild instead, the right children never get doubled
	}
	/******************************/

	public boolean isTreeEqual(Node otherRoot) {
		return isTreeEqual(root, otherRoot);
	}

	private boolean isTreeEqual(Node node, Node otherNode) {
		if (node == null && otherNode == null)
			return true;
		else if (node != null && otherNode != null) {
			return node.data == otherNode.data &&
			       isTreeEqual(node.leftChild, otherNode.leftChild) &&
			       isTreeEqual(node.rightChild, otherNode.rightChild);
		}

		return false;
	}

	// this fxn is a bad idea but is used to test isTreeEqual()
	public Node getRoot() {
		return root;
	}
	/******************************/

	Node lca(Node node1, Node node2) { return lca(root, node1, node2); }
	Node lca(Node root, Node one, Node two) {
	    // Check if one and two are in the root tree.
	    while (root != null) {
	        if (root.data < one.data && root.data < two.data)
	            root = root.rightChild;
	        else if (root.data > one.data && root.data > two.data)
	            root = root.leftChild;
	        else
	            return root;
	    }

	    return null;
	}
	/******************************/
}
