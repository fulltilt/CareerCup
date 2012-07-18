package trees;

import java.util.*;

public class BinaryTree {
	private Node root = null;	// Root node pointer. Will be null for an empty tree.

  	public static class Node {
		Node leftChild = null;
		Node rightChild = null;

		int data;

    	Node(int newData) { data = newData; }
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

	/******************************/
	public boolean find(int data) { return find(root, data); }
	private boolean find(Node node, int data) {
		if (node == null)
			return false;

		LinkedList<Node> currentLevel = new LinkedList<Node>();
		LinkedList<Node> children = new LinkedList<Node>();
		currentLevel.add(node);
		int level = 1;

		while (!currentLevel.isEmpty()) {
			for (Node n : currentLevel) {
				if (n.data == data) {
					System.out.println("Data " + data + " has been found on level " + level);
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

		System.out.println("Data " + data + " was unable to be found");
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

	public boolean isBST(Node node) { return isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE); }
	private boolean isBST(Node node, int min, int max) {
		if (node == null)	// if we made it this far, so far, the tree is a BST
			return true;

		// if the Node's data falls outside the range, return false
		if (node.data < min || node.data > max)
			return false;

		// check if the left child is within range (min...node.data) else return false
		return isBST(node.leftChild, min, node.data) &&

			   // check if right child should be in range (node.data + 1...max) else return false
		 	   isBST(node.rightChild, node.data + 1, max);
	}
	/******************************/

	public int getDiameter(Node node) {
		if (node == null)
			return 0;

		int leftDiameter  = getDiameter(node.leftChild);
		int rightDiameter = getDiameter(node.rightChild);
		int rootDiameter  = getHeight(node.leftChild) + getHeight(node.rightChild) + 1;

		return Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));
	}

	public int getHeight(Node node) {
		if (node == null)
			return 0;

		return 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
	}

	public Node getRoot() { return root; }
}
