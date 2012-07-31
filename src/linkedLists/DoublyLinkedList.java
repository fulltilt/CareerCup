package linkedLists;

public class DoublyLinkedList {
	private Node head = null;
	private Node tail = null;
	private int size;
	
	public static class Node {
		public int value;
		public Node previous = null;
		public Node next = null;
		
		public Node(int value) { this.value = value; }
	}

	public Node getHead() { return head; }
	public Node getTail() { return tail; }
	public int getSize() { return size; }
	public boolean isEmpty() { return size == 0; }
	
	public void displayList() {
		Node currentNode = head;
		while (currentNode != null) {
			System.out.print(currentNode.value + " ");
			currentNode = currentNode.next;
		}
	}
	
	public Node getNodeByIndex(int position) {
		if (position > size - 1)
			return null;
		
		Node currentNode = head;
		for (int i = 0; i < position; i++)
			currentNode = currentNode.next;
		
		return currentNode;
	}
	
	public Node getNodeByValue(int value) {
		Node currentNode = head;
		while (currentNode != null) {
			if (currentNode.value == value)
				return currentNode;
			
			currentNode = currentNode.next;
		}
		
		return null;
	}
	
	public void insertAtHead(int value) {
		Node newNode = new Node(value);
		++size;
		
		if (head == null) {
			head = newNode;
			tail = newNode;
			return;
		}
		
		newNode.next = head;
		head.previous = newNode;
		head = newNode;	// no need to update tail
	}
	
	public void insert(int valueToInsertAfter, int value) {
		Node newNode = new Node(value);
		++size; 
		
		Node nodeToInsertAfter = getNodeByValue(valueToInsertAfter);	
		if (nodeToInsertAfter.next == null) {
			newNode.previous = nodeToInsertAfter;
			nodeToInsertAfter.next = newNode;
			tail = newNode;
			return;
		}
		
		newNode.previous = nodeToInsertAfter;
		newNode.next = nodeToInsertAfter.next;
		nodeToInsertAfter.next.previous = newNode;
		nodeToInsertAfter.next = newNode;
	}
	
	public boolean delete(int value) {
		if (head == null) {
			System.out.println("Unable to delete from empty List.");
			return false;
		}
		
		Node nodeToDelete = getNodeByValue(value);
		if (nodeToDelete == null) {
			System.out.println("Unable to find Node to delete.");
			return false;
		}
		
		return delete(nodeToDelete);
	}
	
	public boolean delete(Node nodeToDelete) {
		if (nodeToDelete == head) {
			if (nodeToDelete.next == null)		// when size of List is 1
				head = tail = null;
			else {								// delete head but List is bigger than 1
				head.next.previous = null;
				head = head.next;	
			}
		} else {								// deleting from middle of List
			nodeToDelete.previous.next = nodeToDelete.next;
			
			if (nodeToDelete.next == null)		// deleting the last element of the List
				tail = nodeToDelete.previous;
			else
				nodeToDelete.next.previous = nodeToDelete.previous;
		}
		
		--size;
		return true;
	}
}
