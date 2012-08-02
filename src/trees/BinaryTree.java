package trees;

import java.util.*;

public class BinaryTree {
	private Node root = null;	// Root node pointer. Will be null for an empty tree.
	private int balanceFactor;
	
  	public static class Node {
		Node leftChild = null;
		Node rightChild = null;

		int value;

    	Node(int newValue) { value = newValue; }
	}

	public BinaryTree() {
		root = new Node(1);
		root.leftChild = new Node(2);
		root.rightChild = new Node(3);
		root.rightChild.leftChild = new Node(6);
		root.leftChild.leftChild = new Node(4);
		root.leftChild.rightChild = new Node(5);
		root.leftChild.leftChild.rightChild = new Node(7);
		root.rightChild.rightChild = new Node(5);
		root.rightChild.rightChild.leftChild = new Node(4);
		root.rightChild.rightChild.leftChild.leftChild = new Node(2);
		root.rightChild.rightChild.leftChild.leftChild.leftChild = new Node(1);
		root.rightChild.rightChild.leftChild.leftChild.rightChild = new Node(3);
		root.rightChild.rightChild.rightChild = new Node(8);
		root.rightChild.rightChild.rightChild.leftChild = new Node(7);
		root.rightChild.rightChild.rightChild.rightChild = new Node(9);
		root.rightChild.rightChild.rightChild.rightChild.rightChild = new Node(10);
	}

	public int getHeight(Node node) {
		if (node == null)
			return 0;

		return 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
	}

	public Node getRoot() { return root; }
	
	/******************************/
	public boolean find(int value) { return find(root, value); }
	private boolean find(Node node, int value) {
		if (node == null)
			return false;

		LinkedList<Node> currentLevel = new LinkedList<Node>();
		LinkedList<Node> children = new LinkedList<Node>();
		currentLevel.add(node);
		int level = 1;

		while (!currentLevel.isEmpty()) {
			for (Node n : currentLevel) {
				if (n.value == value) {
					System.out.println("Value " + value + " has been found on level " + level);
					return true;
				}

				if (n.leftChild != null)
					children.add(n.leftChild);
				if (n.rightChild != null)
					children.add(n.rightChild);
			}
			currentLevel.clear();
			currentLevel.addAll(children);
			children.clear();
			++level;
		}

		System.out.println("Value " + value + " was unable to be found");
		return false;
	}
	/******************************/

	public void printByLevel() { printByLevel(root); }
	private void printByLevel(Node node) {
		if (node == null)
			return;

		LinkedList<Node> currentLevel = new LinkedList<Node>();
		LinkedList<Node> children = new LinkedList<Node>();
		currentLevel.add(node);

		while (!currentLevel.isEmpty()) {
			for (Node n : currentLevel) {
				System.out.print(n.value + " ");

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

	public boolean isBST(Node node) { return isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE); }
	private boolean isBST(Node node, int min, int max) {
		if (node == null)	// if we made it this far, so far, the tree is a BST
			return true;

		// if the Node's data falls outside the range, return false
		if (node.value < min || node.value > max)
			return false;

		// check if the left child is within range (min...node.data) else return false
		return isBST(node.leftChild, min, node.value) &&

			   // check if right child should be in range (node.data + 1...max) else return false
		 	   isBST(node.rightChild, node.value + 1, max);
	}
	/******************************/

	public double getBalanceFactor() {
		balanceFactor = 0;
		getBalanceFactor(root, 1);
		
		return balanceFactor;
	}
	private int getBalanceFactor(Node node, int level) {
		if (node == null)
			return 0;
		
		int desc = getBalanceFactor(node.leftChild, level + 1) + 
				   getBalanceFactor(node.rightChild, level + 1);
		balanceFactor += desc * 1.0 / level;
		   
		return desc + 1;
	}
	
	public int getDiameter(Node node) {
		if (node == null)
			return 0;

		int leftDiameter  = getDiameter(node.leftChild);
		int rightDiameter = getDiameter(node.rightChild);
		int rootDiameter  = getHeight(node.leftChild) + getHeight(node.rightChild) + 1;

		return Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));
	}
	
	public int getMaxElement() { return getMaxElement(root); }
	private int getMaxElement(Node node) {
		  if (node == null) 
			  return Integer.MIN_VALUE;
		  
		  return Math.max(node.value, Math.max(getMaxElement(node.leftChild), getMaxElement(node.rightChild)));
	}
				  
	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		System.out.println(bt.getBalanceFactor());
	}
}
