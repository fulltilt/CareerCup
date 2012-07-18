package linkedLists;

import java.util.Iterator;

public class LinkedList {
	private Node head = null;
	private int size;

	public static class Node {
		Node(int item) { value = item; }
		int value;
		Node next = null;
	}
	/*
	 * inserts new int to the head of the List. Needed for this implementation as insert inserts only after a given Node
	 * note: can implement a Linked List with a single insert fxn that would take in a null value as a signal to add at
	 *       the head
	 */
	public void insertAtHead(int value) {
		Node newNode = new Node(value);

		// case when List isn't empty
		if (head != null)
			newNode.next = head;

		head = newNode;		// this also handles the case when List is empty

		++size;
	}

	/*
	 * insert() that takes in the value of the Node to insert after. Calls private insert() that takes in the Node to insert
	 * after and the value. Calls getNode to find the Node in question. If Node isn't found, new Node isn't added to List
	 */
	public void insert(int valueOfNodeToInsertAfter, int value) {
		Node tempNode = getNode(valueOfNodeToInsertAfter);

		if (tempNode == null) {
			System.out.println("Unable to find Node to insert after.");
			return;
		}

		insert(tempNode, value);
	}

	/*
	 * insert() fxn that takes in a Node and a value of new Node that will be added after Node that's passed in.
	 * handles cases when Node to be inserted is in the middle or to be the last on the List
	 */
	private void insert(Node node, int value) {
		Node newNode = new Node(value);

		newNode.next = node.next;
		node.next = newNode;

		++size;
	}

	/*
	 * delete the head Node. Return false if the List is empty. (Like insertAtHead(), could probably implement one delete()
	 * function and if I wanted to delete the head, pass in a null value
	 */
	public boolean deleteAtHead() {
		if (size == 0) {
			System.out.println("Unable to delete from an empty List!");
			return false;
		}

		head = head.next;

		--size;
		return false;
	}

	/*
	 * delete that takes in the int value. Calls getNode to find the Node to delete and then calls private delete which takes
	 * in the Node to delete
	 */
	public boolean delete(int value) {
		if (size == 0) {
			System.out.println("Unable to delete from an empty List!");
			return false;
		}

		Node tempNode = getNode(value);
		if (tempNode == null) {
			System.out.println("Unable to find Node to delete.");
			return false;
		}

		return delete(tempNode);
	}

	/*
	 * delete function that takes in a Node to delete
	 */
	private boolean delete(Node node) {
		// first, find the Node previous to node
		Node currentNode = head;
		Node previousNode = head;

		while (currentNode != null) {
			if (currentNode == node)
				break;

			previousNode = currentNode;
			currentNode = currentNode.next;
		}

		previousNode.next = currentNode.next;

		--size;
		return true;
	}

	/*
	 * searches the List for a Node with a given value and returns the Node if the values match
	 */
	public Node getNode(int value) {
		Node currentNode = head;

		while (currentNode != null) {
			if (currentNode.value == value)
				return currentNode;

			currentNode = currentNode.next;
		}

		return null;
	}

	public Node getNodeByPosition(int position) {
		if (position >= size)
			return null;

		Node currentNode = head;
		for (int i = 0; i < position; i++)
			currentNode = currentNode.next;

		return currentNode;
	}

	public void displayList() {
		Node currentNode = head;

		while (currentNode != null) {
			System.out.print(currentNode.value + " ");

			currentNode = currentNode.next;
		}
	}

	public int size() { return size; }
	public boolean isEmpty() { return size == 0; }
	public void setHead(Node node) { head = node; }	// used for Merge Sort

	public Iterator<Integer> iterator() { return new ListIterator(); }

	private class ListIterator implements Iterator<Integer> {
		private Node currentNode = head;

		public boolean hasNext() { return currentNode.next != null; }
		public void remove() { }
		public Integer next() {
			int tempValue = currentNode.value;
			currentNode = currentNode.next;
			return tempValue;
		}
	}
/*************************************************************/

	public boolean isCircular() {
		Node n1 = head;
		Node n2 = head;

		while (n2 != null) {
			n1 = n1.next;

			if (n2.next == null)	// so we don't end up dereferencing a null pointer
				return false;

			n2 = n2.next.next;

			if (n1 == n2)
				return true;
		}

		return false;
	}

	public int findCircularListLength() {
		Node slow = head;
		Node fast = head;
		int lengthToStartOfCycle = 0;

		while (fast != null) {
			slow = slow.next;

			if (fast.next == null)
				break;

			fast = fast.next.next;

			++lengthToStartOfCycle;

			if (slow == fast)
				break;
		}

		Node startOfCycle = slow;
		Node currentNode = slow.next;
		int lengthOfCycle = 0;
		while (currentNode != startOfCycle) {
			currentNode = currentNode.next;
			++lengthOfCycle;
		}

		return lengthToStartOfCycle + lengthOfCycle;
	}

	public void swapKthElements(int position) {
		if (isCircular() || position >= size) {
			System.out.println("LIST IS OF LESSER SIZE!");
			return;
		}

		Node node1 = head;
		Node node2 = head;
		Node helper = head;

		for (int i = 0; i < position; i++) {
			node1 = node1.next;
			helper = helper.next;
		}

		// iterate helper pointer until it's null and keep a count
		int distanceFromNode1ToEnd = 0;
		while (helper != null) {
			helper = helper.next;
			++distanceFromNode1ToEnd;
		}
		--distanceFromNode1ToEnd;	// this accounts for when the loop goes one extra step for the null value

		// iterate node2 to the position that is 'position' elements from end
		for (int i = 0; i < distanceFromNode1ToEnd; i++)
			node2 = node2.next;

		int temp = node1.value;
		node1.value = node2.value;
		node2.value = temp;
	}

	public Node getNthToLastElement(int n) {
		Node NthElement = head;
		Node NthElementFromLast = head;

		for (int i = 0; i <= n; i++)	// did '<=' to account for the extra step taken in the next loop to determine if element is null
			NthElement = NthElement.next;

		while (NthElement != null) {
			NthElement = NthElement.next;
			NthElementFromLast = NthElementFromLast.next;
		}

		return NthElementFromLast;
	}

	public void reverseList() {
		Node previousNode = null;
		Node currentNode = head;
		Node nextNode = head.next;

		while (nextNode != null) {
			currentNode.next = previousNode;
			previousNode = currentNode;
			currentNode = nextNode;
			nextNode = nextNode.next;

			if (nextNode == null)		// this step needed on the very last iteration
				currentNode.next = previousNode;
		}

		head = currentNode;		// make sure head points to the correct element
	}

	public void recursiveReverseList() { recursiveReverseList(null, head); }
	private void recursiveReverseList(Node current1, Node current2) {
		if (current2 == null) {
			head = current1;
			return;
		}

		recursiveReverseList(current2, current2.next);
		current2.next = current1;
	}

	// keep reference to n1 only if you want to get the Node that is in the middle
	public int getMidpoint() {
		if (head == null)		// list is empty
			return -1;

		if (head.next == null)	// list is of size 1
			return 0;

		//Node n1 = head;
		Node n2 = head.next;
		int position = 0;

		while ((n2 != null) && (n2.next != null)) {
			//n1 = n1.next;
			n2 = n2.next.next;
			++position;
		}

		return position;
	}

	public Node getMidpoint(Node head) {
		if (head == null)		// list is empty
			return head;

		Node slow, fast; 
		slow = fast = head;
		
	    while(fast.next != null && fast.next.next != null) {
	        slow = slow.next; 
	        fast = fast.next.next;
	    }
	    return slow;

	}

	public Node mergeSort() { return mergeSort(head); }
	private Node mergeSort(Node head) {
    	if(head == null || head.next == null)
    		return head;

		Node middle = getMidpoint(head);	//get the middle of the list
		
		//split the list into two halfs
		Node secondHalf = middle.next; 
		middle.next = null;   	

		return merge(mergeSort(head), mergeSort(secondHalf));  //recurse on that
	}

	//Merge subroutine to merge two sorted lists
	public Node merge(Node a, Node b) {
	    Node dummyHead = new Node(-1);	// dummy Node that will ultimately point to beginning of sorted List
	    Node curr = dummyHead;

	    // merge both Lists into one in a sorted order
	    while(a != null && b != null) {
	        if (a.value <= b.value) {
				curr.next = a;
				a = a.next;
			} else {
				curr.next = b;
				b = b.next;
			}
	        curr = curr.next;
	    }
	    curr.next = (a == null) ? b : a;
	    return dummyHead.next;
	}
	
	public Node findConvergenceOfTwoLists(LinkedList otherList) {
		int list1Length = this.size();
		int list2Length = otherList.size();	
		Node longerListNode, shorterListNode;
		int positionsToIterate = 0;

		// find how many positions to iterate the longer List to match up to the shorter List
		if (list1Length >= list2Length) {
			longerListNode = head;
			shorterListNode = otherList.getNodeByPosition(0);
			positionsToIterate = list1Length - list2Length;
		} else {
			longerListNode = otherList.getNodeByPosition(0);
			shorterListNode = head;
			positionsToIterate = list2Length - list1Length;
		}

		for (int i = 0; i < positionsToIterate; i++)	// position longer List to match sohrter List
			longerListNode = longerListNode.next;

		while (longerListNode != null) {
			if (longerListNode == shorterListNode)
				return longerListNode;

			longerListNode = longerListNode.next;
			shorterListNode = shorterListNode.next;
		}

		return null;
	}

	public void eliminateDuplicates() {
		head = mergeSort();	// sort List
		
		Node currentNode = head;
		while (currentNode.next != null) {
			if (currentNode.value == currentNode.next.value) 
				currentNode.next = currentNode.next.next;
				
			currentNode = currentNode.next;
		}
	}


/****
	public void convertToDoublyLinkedList() {
	}
****/
}
