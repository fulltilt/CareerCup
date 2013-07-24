package trees;

import java.util.*;

import linkedLists.DoublyLinkedList;

public class BinarySearchTree {
	private Node root = null;	// Root node pointer. Will be null for an empty tree.
    
  	public static class Node {
		Node leftChild = null;
		Node rightChild = null;
		int value;

    	Node(int newValue) { value = newValue; }
	}

	/**
	    Returns true if the given target is in the binary tree.
	    Uses a recursive helper.
	*/
  	public boolean find(int value) {
    	return(find(root, value));
  	}

  	private boolean find(Node node, int value) {
    	if (node == null)
      		return(false);

    	if (value == node.value)
      		return(true);
    	else if (value < node.value)
      		return(find(node.leftChild, value));
    	else
      		return(find(node.rightChild, value));
  	}


	/**
   		Inserts the given value into the binary tree.
   		Uses a recursive helper.
  	*/
  	public void insert(int value) {
    	root = insert(root, value);
  	}

  	private Node insert(Node node, int value) {
    	if (node == null)
      		node = new Node(value);
    	else {
      		if (value <= node.value)
        		node.leftChild = insert(node.leftChild, value);
      		else
        		node.rightChild = insert(node.rightChild, value);
    	}

    	return node; // in any case, return the new pointer to the caller
  	}
	/******************************/

	public void print() { print(root); System.out.println(); }
	public void print(Node node) {
		if (node == null)
			return;

		print(node.leftChild);
		System.out.print(node.value + " ");
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

		return hasPathSum(node.leftChild, sum - node.value) || hasPathSum(node.rightChild, sum - node.value);
	}
	/******************************/

	public void printPaths() {
		int[] path = new int[1000];
		printPaths(root, path, 0);
	}

	private void printPaths(Node node, int[] path, int pathLength) {
		if (node == null)
			return;

		path[pathLength] = node.value;
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

	/* CI51 - return mirror of a tree */
	public Node mirror() {
		printByLevel(root);
		Node newRoot = mirror(root);
		printByLevel(newRoot);
		return newRoot;
	}

	private Node mirror(Node node) {
		if (node == null)
			return null;

		Node newNode = new Node(node.value);
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
				System.out.print(n.value + " ");

				if (n.leftChild != null)
					children.add(n.leftChild);
				if (n.rightChild != null)
					children.add(n.rightChild);
			}
			currentLevel.clear();
			currentLevel.addAll(children);
			children.clear();
			System.out.println();
		}
	}
	
    /* CI59 - print by level zig-zag style */
    public void printByLevelZigZag() {        
        printByLevelZigZag(root);
    }

    private void printByLevelZigZag(Node node) {
        if (node == null)
            return;
            
        ArrayList<Node> currentLevel = new ArrayList<Node>();    
        ArrayList<Node> children = new ArrayList<Node>();
        currentLevel.add(node);
    
        int depth = 0;
        while (!currentLevel.isEmpty()) {
            if (depth % 2 == 0) {   // for odd depths, print from left to right
                for (int i = 0; i < currentLevel.size(); i++)
                    System.out.print(currentLevel.get(i).value + " ");
            } else {                // for even depths, print from right to left
                for (int i = currentLevel.size() - 1; i >= 0 ; i--)
                    System.out.print(currentLevel.get(i).value + " ");
            }
          
            // print out the values of the current level
            for (Node n : currentLevel) {
                if (n.leftChild != null)
                    children.add(n.leftChild);
                if (n.rightChild != null)
                    children.add(n.rightChild);
            }
            currentLevel.clear();
            currentLevel.addAll(children);
            children.clear();
            System.out.println();
            ++depth;
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
				System.out.print(tempNode.value + " ");
		}
		System.out.println();
	}
	/******************************/

	// after creating this, found this can be used to print level by level but requires storage
	public void createLinkedListByLevel() { createLinkedListByLevel(root); }
	private void createLinkedListByLevel(Node node) {
		if (node == null)
			return;
		
		LinkedList<Node> currentLevel = new LinkedList<Node>();
		LinkedList<Node> children = new LinkedList<Node>();
		Stack<LinkedList<Node>> tree = new Stack<LinkedList<Node>>();
		currentLevel.addFirst(node);
		tree.push(currentLevel);
		
		while (!currentLevel.isEmpty()) {
			for (Node n : currentLevel) {
				if (n.leftChild != null)
					children.add(n.leftChild);				
				if (n.rightChild != null)
					children.add(n.rightChild);
			}
			currentLevel = children;
			tree.push(currentLevel);
			children = new LinkedList<Node>();
		}
		
		for (LinkedList<Node> ll : tree) {
			for (Node n : ll)
				System.out.print(n.value + " ");
			System.out.println();
		}
	}
	/******************************/
	
	public boolean isBalanced() { return (maxDepth(root) - minDepth(root)) <= 1; }
	private int maxDepth(Node node) {
		if (node == null)
			return 0;
		
		return 1 + Math.max(maxDepth(node.leftChild), maxDepth(node.rightChild)); 
	}	
	private int minDepth(Node node) {
		if (node == null)
			return 0;
		
		return 1 + Math.min(minDepth(node.leftChild), minDepth(node.rightChild)); 
	}	
	/******************************/
	
	public void doubleTree() {
		doubleTree(root);
	}

	private void doubleTree(Node node) {
		if (node == null)
			return;

		Node newNode = new Node(node.value);
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
			return node.value == otherNode.value &&
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

	public Node lca(Node node1, Node node2) { return lca(root, node1, node2); }
	private Node lca(Node root, Node one, Node two) {
	    // Check if one and two are in the root tree.
	    while (root != null) {
	        if (root.value < one.value && root.value < two.value)
	            root = root.rightChild;
	        else if (root.value > one.value && root.value > two.value)
	            root = root.leftChild;
	        else
	            return root;
	    }

	    return null;
	}
	/******************************/
	
	public void printNodesInRange(int min, int max) { printNodesInRange(root, min, max); }
	private void printNodesInRange(Node node, int min, int max) {
		if (node == null)
			return;
		
		printNodesInRange(node.leftChild, min, max);
		if (node.value >= min && node.value <= max)
			System.out.print(node.value + " ");
		printNodesInRange(node.rightChild, min, max);
	}
	/******************************/
	
	public Node trimTreeInRange(int min, int max) { 
		return trimTreeInRange(root, min, max); 
	}
	private Node trimTreeInRange(Node node, int min, int max) {
		if (node == null)
			return null;
		
		if (node.value > max) 
			return trimTreeInRange(node.leftChild, min, max);
		else if (node.value < min) 
			return trimTreeInRange(node.rightChild, min, max);
		else {
			   node.leftChild = trimTreeInRange(node.leftChild, min, max);
			   node.rightChild = trimTreeInRange(node.rightChild, min, max);
		} 
		  
		return node;
	}	
	/******************************/
	
	public linkedLists.LinkedList convertToLinkedList() { 
		linkedLists.LinkedList newList = new linkedLists.LinkedList();
		convertToLinkedList(root, newList);

		return newList;
	}
	private void convertToLinkedList(Node node, linkedLists.LinkedList newList) {  
		if (node == null)
			return; 
		
		convertToLinkedList(node.rightChild, newList);	// by switching the order, we don't have to reverse the List
		newList.insertAtHead(node.value);
		convertToLinkedList(node.leftChild, newList);
	}	
	/******************************/

	public DoublyLinkedList convertToDoublyLinkedList() { 
		DoublyLinkedList newList = new DoublyLinkedList();
		convertToDoublyLinkedList(root, null, newList);
		
		return newList;
	}
	private void convertToDoublyLinkedList(Node node, DoublyLinkedList.Node previousNode, DoublyLinkedList newList) {  
		if (node == null)
			return; 
		
		convertToDoublyLinkedList(node.rightChild, previousNode, newList);
		
		newList.insertAtHead(node.value);
		newList.getHead().previous = previousNode;
		if (previousNode != null)
			previousNode.next = newList.getHead();
		
		convertToDoublyLinkedList(node.leftChild, previousNode, newList);
	}	
	/******************************/
	
	public void reversePointers() { reversePointers(root, null); }
	private void reversePointers(Node currentNode, Node parent) {
		if (currentNode == null)
			return;
		
		// must save these values as these will be modified
		Node leftNode = currentNode.leftChild;
		Node rightNode = currentNode.rightChild;
		
		currentNode.leftChild = parent;
		currentNode.rightChild = parent;
		reversePointers(leftNode, currentNode);
		reversePointers(rightNode, currentNode);
	}
	
	/******************************/
	
	public void valueToSumOfChildrenValues() { valueToSumOfChildrenValues(root); }
	private int valueToSumOfChildrenValues(Node node) {
		if (node == null)
			return 0;
		
		int currentValue = node.value;
		node.value = valueToSumOfChildrenValues(node.leftChild) + valueToSumOfChildrenValues(node.rightChild);
		
		return node.value + currentValue;
	}
	
	/******************************/
	
	public boolean isTreeSymmetric() {return isTreeSymmetric (root.leftChild, root.rightChild); }
	private boolean isTreeSymmetric(Node left, Node right) {
		if (left == null && right == null)
			return true;
		else if (left == null || right == null)
			return false;

		return isTreeSymmetric(left.leftChild, right.rightChild) && 
			   isTreeSymmetric(left.rightChild, right.leftChild);
	}
	/******************************/
	
	public HashMap<Integer, Integer> addColumns() { 
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		addColumns(root, 0, hm); 

		return hm;
	}
	private void addColumns(Node node, int column, HashMap<Integer, Integer> hashMap) {
		if (node == null)
			return;
		
		if (!hashMap.containsKey(column))
			hashMap.put(column, node.value);
		else {
			int tempValue = (int) hashMap.get(column);
			hashMap.put(column, tempValue + node.value);
		}
		// one turn to the left corresponds to n-1 and one turn to the right corresponds to n+1
		addColumns(node.leftChild, column - 1, hashMap);
		addColumns(node.rightChild, column + 1, hashMap);	
	}
	/******************************/
	static int currentCount; 	
	public void findKthLargest(int k) { findKthLargest(root, k); currentCount = 0;}
	private void findKthLargest(Node node, int k) {
		if (node == null)
			return;
		
		findKthLargest(node.rightChild, k);
		
		if (++currentCount == k) {
			System.out.print(node.value + " ");
			return;
		}
		
		findKthLargest(node.leftChild, k);
	}
	/******************************/
	
	public int sumOfAllGreaterAndEqualTo() { return sumOfAllGreaterAndEqualTo(root, 0); }
	private int sumOfAllGreaterAndEqualTo(Node node, int sum) {
		if (node == null)
			return sum;
		else if (node.leftChild == null && node.rightChild == null) {
			node.value += sum;
			return node.value;
		}
		
		int tmp = sumOfAllGreaterAndEqualTo(node.rightChild, sum);
		node.value += tmp;
		tmp = sumOfAllGreaterAndEqualTo(node.leftChild, node.value);
		return tmp;
	}
	/******************************/
	
    /* CI84 - kth node in a binary search tree in an incremental order of values */
    public Node getKthNode(int k) {
        if (k > getSize(root)) 
            return null;    
            
        return getKthNode(root, k); 
    }
    
    private Node getKthNode(Node node, int k) {
        if (node == null)
            return null;
        
        // since we need the current value of k, we can't do a recursive in-order traversal. Simulate this using a stack
        // Complicated going through this at first but noticed when node is not null, add to stack and look at left child
        // but if node is null, pop off stack and node = rightChild but in between check current value of k
        Stack<Node> stack = new Stack<Node>();  
        while (stack.size() > 0 || node != null) {
            if (node != null) {
                stack.add(node);
                node = node.leftChild;
            } else {
                node = stack.pop();
                k--;
                
                if (k == 0) 
                    return node;
                    
                node = node.rightChild;
            }
        }
        
        return null;
    }
    
	/******************************/
    
    /* 18 - get next node in Binary Tree (C++ pseudocode as this assumes that there is a parent link
    BinaryTreeNode* GetNext(BinaryTreeNode* pNode) { 
        if(pNode == NULL) 
            return NULL; 
        
        BinaryTreeNode* pNext = NULL; 
        if(pNode->m_pRight != NULL) { // when node has a right-child, next node is left-most child on right subtree
            BinaryTreeNode* pRight = pNode->m_pRight; 
            while(pRight->m_pLeft != NULL) 
            pRight = pRight->m_pLeft; 
            pNext = pRight; 
        } else if(pNode->m_pParent != NULL) { // when node has no right-subtree, it's next node is it's parent if it's the parents left child. 
                                              // If a node isn't the left child, keep traversing up until you find the node that is a left child. That
                                              // node's parent is the next node
            BinaryTreeNode* pCurrent = pNode; 
            BinaryTreeNode* pParent = pNode->m_pParent; 
            while(pParent != NULL && pCurrent == pParent->m_pRight) { 
                pCurrent = pParent; 
                pParent = pParent->m_pParent; 
            } 
            
            pNext = pParent; 
        } 
        return pNext; 
    } 
        
	/******************************/
	
	/******************************/
	public static void main(String[] args) {
		//BinarySearchTree bt = new BinarySearchTree();
		//System.out.println(bt.printNodesInRange());
	}	
}
