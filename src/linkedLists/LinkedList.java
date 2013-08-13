package linkedLists;

import java.util.Iterator;

public class LinkedList {
	private Node head = null;
	private int size;

	public static class Node {
		Node(int item) { value = item; }
		public int value;
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

    /* general purpose insert fxn that inserts at the end of the list by default */
    public void insert(int value) {
        if (head == null) {
            insertAtHead(value);
            return;
        }
        
        Node newNode = new Node(value);
        Node currentNode = head;
        
        while (true) {
            if (currentNode.next == null) {
                currentNode.next = newNode;
                break;
            }
            currentNode = currentNode.next;
        }
        
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
		return true;
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

	public Node getNodeByIndex(int index) {
		//if (position >= size)	// commented this line out due to mergeAlternating which doesn't update the size of the list
		//	return null;

		Node currentNode = head;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;

			if (currentNode == null)
				break;
		}

		return currentNode;
	}

	public void displayList() {
		Node currentNode = head;

		System.out.println();
		while (currentNode != null) {
			System.out.print(currentNode.value + " ");

			currentNode = currentNode.next;
		}
	}

	// special getSize fxn for findConvergenceOfTwoLists
	public void setSize() {
		Node currentNode = head;
		int tempSize = 0;

		while (currentNode != null) {
			currentNode = currentNode.next;
			++tempSize;
		}
		this.size = tempSize;
	}

	public int size() { return size; }
	public boolean isEmpty() { return size == 0; }
	public void setHead(Node node) { head = node; }	// used for Merge Sort
	public Node getHead() { return head; }

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

	/* Determine if a list has a loop
	   -algorithm: have 2 pointers: 1 that iterates one a time and another that iterates 2 steps at a time. If there is a loop,
	               the fast pointer will meet up with the slow pointer. If not, each pointer will hit a null value which means
	               that there isn't a loop

	*/
	public boolean isCircular() {
		Node slow = head;
		Node fast = head;

		while (fast != null) {
			slow = slow.next;

			if (fast.next == null)	// so we don't end up dereferencing a null pointer
				return false;

			fast = fast.next.next;

			if (slow == fast)
				return true;
		}

		return false;
	}

	/* Determine the length of the loop
       -algorithm: (note: called after isCircular() is true). First find out the node where the fast and slow meets. From
                   there, have another pointer that starts from that node and iterates until it meets back where it began
	*/
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
		int lengthOfCycle = 0;	// should this be 1?
		while (currentNode != startOfCycle) {
			currentNode = currentNode.next;
			++lengthOfCycle;
		}

		return lengthToStartOfCycle + lengthOfCycle;
	}

	/* Find out where the loop starts in the list
	   -algorithm:  
	*/
	public Node getHeadOfLoop() {	

	}
	
	/*************************************************************/
	
    /* 47 - kth node from tail 
       -algorithm: have 2 pointers: 1st pointer will iterate k steps from head. Once this is reached, have a 2nd pointer
                   that starts from the head and have both pointers advance at the same time until the leading node is null.
				   The trailing node will be k elements from the end
    */
	public Node getKthNodeFromTail(int k) {
	    if (k > size())
	        throw new IllegalArgumentException("k cannot be greater than the list length!");
	    if (k == 0)
	        throw new IllegalArgumentException("k cannot be zero");
	        
	    int steps = k - 1;
	    Node leadingNode = head;
	    while (steps != 0) {
	        leadingNode = leadingNode.next;
	        --steps;
	    }
	    
	    Node trailingNode = head;
	    while (leadingNode.next != null) {
	        trailingNode = trailingNode.next;
	        leadingNode = leadingNode.next;
	    }
	    
	    System.out.println(trailingNode.value);
	    
	    return trailingNode;
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

    /*************************************************************/

    /*
       -algorithm: 
    */	
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
									// note: might be able to take this line out by making the while conditional: (helper.next != null)

		// iterate node2 to the position that is 'position' elements from end
		for (int i = 0; i < distanceFromNode1ToEnd; i++)
			node2 = node2.next;

		int temp = node1.value;
		node1.value = node2.value;
		node2.value = temp;
	}

	/*************************************************************/
	
    /* CI82 - intersection of 2 lists 

    */
    public static Node intersectionOf2Lists(LinkedList list, LinkedList list2) {           
        if (list == null || list2 == null || list.size() == 0 || list2.size() == 0)
            return null;
            
        int list1Length = list.size();
        int list2Length = list2.size();
        Node list1Node = list.getHead();
        Node list2Node = list2.getHead();
       
        if (list1Length >= list2Length) {
            for (int i = 0; i < list1Length - list2Length; i++)
                list1Node = list1Node.next;
        } else {
            for (int i = 0; i < list2Length - list1Length; i++)
                list2Node = list2Node.next;           
        }
       
        while (list1Node != null) {
            if (list1Node.value == list2Node.value) 
                return list1Node;
            
            list1Node = list1Node.next;
            list2Node = list2Node.next;
        }    
        
        return null;
    }
    
    /*************************************************************/

	/* Reverse a List
	   -algorithm: 
	*/
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
		// ** because of the above line, it probably explains the weirdness that is happening

/* version 2
        ListNode<Integer> previousNode = list.getHead();
        ListNode<Integer> currentNode = previousNode.next;
        ListNode<Integer> helperNode = currentNode.next;
        previousNode.next = null; // step we have to do for the initial 2 nodes since the first node's next is null
        
        while (helperNode != null) {
            currentNode.next = previousNode;
            
            previousNode = currentNode;
            currentNode = helperNode;
            helperNode = currentNode.next;
        }
        currentNode.next = previousNode;
        list.head = currentNode;
        list.print();		
 */
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

	public void recursiveReversePrint() { recursiveReversePrint(head); }
	private void recursiveReversePrint(Node node) {
		if (node != null) {
			recursiveReversePrint(node.next);
			System.out.print(node.value + " ");
		}
	}

	/* keep reference to n1 only if you want to get the Node that is in the middle
	*/
	public int getMidpoint() {
		if (head == null)		// list is empty
			return -1;

		Node slow, fast;
		slow = fast = head;
		int position = 0;

		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;

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

    /* Sort a linked list use Merge Sort
       -algorithm: 
    */
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

    /* 
       -algorithm: 
    */
	public Node findConvergenceOfTwoLists(LinkedList otherList) {
		int list1Length = this.size();
		int list2Length = otherList.size();
		Node longerListNode, shorterListNode;
		int positionsToIterate = 0;

		// find how many positions to iterate the longer List to match up to the shorter List
		if (list1Length >= list2Length) {
			longerListNode = head;
			shorterListNode = otherList.getNodeByIndex(0);
			positionsToIterate = list1Length - list2Length;
		} else {
			longerListNode = otherList.getNodeByIndex(0);
			shorterListNode = head;
			positionsToIterate = list2Length - list1Length;
		}

		for (int i = 0; i < positionsToIterate; i++)	// position longer List to match shorter List
			longerListNode = longerListNode.next;

		while (longerListNode != null) {
			if (longerListNode == shorterListNode)
				return longerListNode;

			longerListNode = longerListNode.next;
			shorterListNode = shorterListNode.next;
		}

		return null;
	}

	public void bubbleSort() {
		boolean swap = true;
		Node currentNode;

		if (size == 1)
			return;

		while (swap) {
			swap = false;
			currentNode = head;
			while (currentNode.next != null) {
				if (currentNode.value > currentNode.next.value) {
					int temp = currentNode.value ;
					currentNode.value = currentNode.next.value;
					currentNode.next.value = temp;

					swap = true;
				}

				currentNode = currentNode.next;
			}
		}
	}

    /* Eliminate duplicates in a List (no external buffer)
       -algorithm: 
    */
	public void eliminateDuplicates() {
		head = mergeSort();	// sort List

		Node currentNode = head;
		while (currentNode.next != null) {
			while (currentNode.value == currentNode.next.value)	// using 'while' in case there's more than 2 duplicates in a row
				currentNode.next = currentNode.next.next;

			currentNode = currentNode.next;
		}
	}

    /*
       -algorithm: 
    */
	public static Node mergeAlternating(Node head1, Node head2) {
		Node head3 = head1;

		while (head1 != null && head2 != null) {
			Node temp = head1.next;
			Node temp2 = head2.next;
			head1.next = head2;
			head2.next = temp;

			if (head1.next == null)
				head1 = head2;
			else
				head1 = temp;
			head2 = temp2;
		}

		if (head2 != null)	// called when the 2nd list is longer than the 1st one
			head1.next = head2;

		return head3;
	}

    /*
       -algorithm: 
    */
	public boolean isPalindrome() {
		Node midNode = this.getMidpoint(head);
		Node secondHalfNode = midNode.next;
		midNode.next = null;		// split List into 2 halves a la MergeSort

		Node secondHalfCurrent = reverseList(secondHalfNode);	// reverse 2nd half of the List
		
		// compare the 1st half of the List to the reverse of the 2nd half of the List
		Node currentNode = head;
		while (secondHalfCurrent != null)	{ // for odds, middle node will always be in 1st half. By doing this conditional, we don't have to worry about the middle element
			if (currentNode.value != secondHalfCurrent.value)
				return false;

			currentNode = currentNode.next;
			secondHalfCurrent = secondHalfCurrent.next;
		}

		return true;
	}

	public void findThirds() {
		if (size == 1) {
			System.out.println("1/3: " + head.value + " 2/3: " + head.value);
			return;
		} 
		
		Node pointer1 = head, pointer2 = head.next, pointer3 = head.next.next;
		while (pointer3 != null && pointer3.next != null && pointer3.next.next != null) {
			pointer1 = pointer1.next;
			pointer2 = pointer2.next.next;
			pointer3 = pointer3.next.next.next;
		}
		
		System.out.println("1/3: " + pointer1.value + " 2/3: " + pointer2.value);
	}

	public void swapEveryTwoElements() {
		Node pointer1 = head;
		Node pointer2 = head.next;
		
		while (pointer1 != null && pointer2 != null) {
			int temp = pointer2.value;
			pointer2.value = pointer1.value;
			pointer1.value = temp;
			
			pointer1 = pointer1.next.next;
			
			if (pointer2.next == null)
				break;
			else
				pointer2 = pointer2.next.next;
		}
	}
	
	public void evensBeforeOdds() {
		Node currentNode = head;
		Node currentEven, currentOdd;
		currentEven = currentOdd = null;

//int count = 0;
		while (currentNode != null) {
			if (currentNode.value % 2 == 0/* && currentEven == null*/)
				currentEven = currentNode;
			else if (currentNode.value % 2 == 1 && currentOdd == null)
			    currentOdd = currentNode;

			if (currentEven != null && currentOdd != null) {
				int temp = currentOdd.value;
				currentOdd.value = currentEven.value;
				currentEven.value = temp;

				currentNode = currentEven;	// after a swap, even Node always before odd Node
				currentEven = currentOdd = null;
//System.out.print("currentNode value: " + currentNode.value);
				continue;
			}

			currentNode = currentNode.next;
//System.out.print("\niteration: " + count++ + " currentOdd value: " + currentOdd.value
//		 + " currentEven value: " + currentEven.value);
//displayList();
		}
	}

/****
	public void convertToDoublyLinkedList() {
	}
****/
	public static void main(String[] args) {
		LinkedList list1 = new LinkedList();
		list1.insertAtHead(1);
		list1.insertAtHead(2);
		list1.insertAtHead(3);
		list1.insertAtHead(4);
		list1.getHead().next.next.next = list1.getHead().next;
		System.out.println(list1.getHeadOfLoop().value);
		//list1.insertAtHead(5);
		//list1.insertAtHead(6);

		//list1.findThirds();

	}
}
