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

	/* Get index of the middle Node
	   -algorithm: have 2 pointers: one that moves one step at a time and the other that moves 2 steps
	               at a time. Both move at the same time. Once the faster node's next node or next.next node
	               is null, wherever the slow node is currently is the middle Node
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
			return null;

		Node slow, fast;
		slow = fast = head;

	    while(fast.next != null && fast.next.next != null) {
	        slow = slow.next;
	        fast = fast.next.next;
	    }
	    return slow;
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

	/* Find out where the loop starts in the list
	   -algorithm: http://umairsaeed.com/2011/06/23/finding-the-start-of-a-loop-in-a-circular-linked-list/
	               Still don't have my head around why it works but to find the head of the loop, find out where the fast and slow
	               pointer meet and have one of the pointers point to the head of the list. Iterate each one a Node at a time and
	               wherever they meet is the head of the loop 
	*/
	public Node getHeadOfLoop() {
		Node slow = head;
		Node fast = head;
		
		// check that there's a loop
		if (!isCircular())
			return null;
		
		// find out where the Node's meet
		while (true) {
			slow = slow.next;
			
			if (fast.next != null)
				fast = fast.next.next;
			
			if (slow == fast)
				break;
		}
		
		// set one of the Node's to head. Iterate each one and wherever they meet is the head
		slow = head;
		while (true) {
			slow = slow.next;
			fast = fast.next;
			
			if (slow == fast)
				return slow;
		}
	}
	
	/*************************************************************/
	
    /* 47 - kth node from tail 
       -algorithm: have 2 pointers: 1st pointer will iterate k steps from head. Once this is reached, have a 2nd pointer
                   that starts from the head and have both pointers advance at the same time until the leading node is null.
				   The trailing node will be k elements from the end
		Note: when k == 1, what should return is the tail. k cannot be zero
    */
	public Node getKthNodeFromTail(int k) {
	    if (k > size())
	        throw new IllegalArgumentException("k cannot be greater than the list length!");
	    if (k == 0)
	        throw new IllegalArgumentException("k cannot be zero");
	        
	    int steps = k - 1;	// the 'minus one' is to account for k not being zero indexed
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
	    
	    return trailingNode;
	}

	// similar to above but uses a for loop when advancing the first pointer (apparently this version IS zero-indexed
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

    /* Swaps the kth elements from the beginning and the end
       -algorithm: call kthElementNodeFromTail to get the kth-to-last Node and iterate k - 1 steps to get
                   the kthElementFromStart. Swap values
       note: just like kthElementFromTheEnd, indices are NOT zero-indexed 
       note: a good but annoying exercise would be to swap the actual Node's as you would have to 
             keep track of the previous Node's, have to handle the condition when the Node is head/tail,
             Node's end up being the same
    */	
	public void swapKthElementsFromBeginningAndEnd(int position) {
		if (position > size) 
			throw new IllegalArgumentException("k cannot be greater than the size of the List!");
		
		Node kthElementFromStart = head;
		
		// get the kth element from the start
		for (int i = 1; i < position; i++)  // start i at 1 since k is not zero-indexed
			kthElementFromStart = kthElementFromStart.next;
		
		// get the kth element from the end
		Node kthElementFromEnd = getKthNodeFromTail(position);

		// not: use this if we're allowed to swap only the values and not the Node's themselves
		int temp = kthElementFromEnd.value;
		kthElementFromEnd.value = kthElementFromStart.value;
		kthElementFromStart.value = temp;
	}

	/*************************************************************/
	
    /* CI82 - intersection of 2 lists 
       -algorithm: Get the each List's respective lengths (O(n + m)). Subtract the shorter List's length
                   from the longer List's length and iterate the longer List by the difference. Now we
                   can iterate both List's in lockstep and once we found the intersection once both
                   of the current List's Nodes are equal to one another
    */
    public static Node intersectionOf2Lists(LinkedList list, LinkedList list2) {           
        if (list == null || list2 == null || list.size() == 0 || list2.size() == 0)
            return null;
            
        int list1Length = list.size();
        int list2Length = list2.size();
        Node list1Node = list.getHead();
        Node list2Node = list2.getHead();
       
        // advance the longer List by the difference of the lengths so the each List's length will technically be the same
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
	   -algorithm: have 2 pointers: one that points to the previous pointer (initially set to null) and
	               a current pointer (initially set to head). Iterate through the list a set currents next
	               to previous. Update each respective pointer. Do this until current is null. You will
	               need a temporary pointer to current's next since you will be updating this to previous.
	               After the loop, the previous node points to the new head so set head to this
	               Note: try doing this with stacks as I believe the solution below may have problems with lists of length < 3
	*/
	public void reverseList() {
		Node previous = null;
		Node current = head;
		
		while (current != null) {
			Node temp = current.next;
			current.next = previous;
			previous = current;
			current = temp;
		}
		
		head = previous;
	}
	
	/* Reverse a List passed to it starting at the Node given. This can reverse in the middle of the List which
	 * effectively splits the List. Because of this the head pointer isn't taken into account
	 * -algorithm: same as reverseList() above
	 */
	public static Node reverseList(Node node) {
		Node previousNode = null;
		Node currentNode = node;
		
		while (currentNode != null) {
			Node temp = currentNode.next;
			currentNode.next = previousNode;
			previousNode = currentNode;
			currentNode = temp;
		}
		
		return previousNode;
	}
		
	/* Recursively reverse a List
	 * -algorithm: the trick is to start from the end of the list by recursively calling the fxn 
	 *             before setting the currentNode's next to the previous Node. This allows us to
	 *             avoid creating a temporary variable as above
	 */
	public void recursiveReverseList() { recursiveReverseList(null, head); }
	private void recursiveReverseList(Node previousNode, Node currentNode) {
		if (currentNode == null) {
			head = previousNode;
			return;
		}

		recursiveReverseList(currentNode, currentNode.next);
		currentNode.next = previousNode;
	}

	/*************************************************************/

    /* Recursively print a List in reverse
       -algorithm: to print in reverse, start printing at the end by recursively calling the fxn
                   before printing current Node's value. Trick is similar to above
    */
	public void recursiveReversePrint() { recursiveReversePrint(head); }
	private void recursiveReversePrint(Node node) {
		if (node != null) {
			recursiveReversePrint(node.next);
			System.out.print(node.value + " ");
		}
	}

	/*************************************************************/

    /* Eliminate duplicates in a List (no external buffer)
       -algorithm: In order to do this w/o a buffer, we need to sort the List first. From there, 
                   iterate one-by-one and if the next Node's value is the same, skip over the next
                   Node. If we did have a buffer (hash table), we can iterate one by one and if the
                   next Node's value is in the hash table, skip over the next Node. The first
                   algorithm is O(n log n) without an external buffer(although implementation below
                   uses merge sort which does use an external buffer) and the latter algorithm is
                   O(n) but uses an external buffer
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

	/*************************************************************/
	
    /* Determine if a List is a palindrome
       -algorithm: split the list into two and compare each Node's value to see they are the same
       note: this works for even and odd length lists
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

	/*************************************************************/
	
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

	/*************************************************************/

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

	/*************************************************************/

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

	/*************************************************************/

    /*
       -algorithm: 
    */	
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

	/*************************************************************/

    /*
       -algorithm: 
    */	
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
	
	/*************************************************************/

    /*
       -algorithm: 
    */		
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
