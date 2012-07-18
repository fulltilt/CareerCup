package linkedLists;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import linkedLists.LinkedList.Node;

public class LinkedListTestCase {
	protected static final int VALUE_A = 1;
	protected static final int VALUE_B = 2;
	protected static final int VALUE_C = 3;
	protected static LinkedList list;

	@Before
	public void createList() {
		list = new LinkedList();
	}

	@Test
	public void testInsertIntoEmptyList() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

		list.insertAtHead(VALUE_A);

		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
	}

	@Test
	public void testInsertBetweenElements() {
		list.insertAtHead(VALUE_A);
		list.insert(VALUE_A, VALUE_B);
		list.insert(VALUE_A, VALUE_C);

		assertEquals(3, list.size());

		Iterator<Integer> iter = list.iterator();
		assertSame(VALUE_A, iter.next());
		assertSame(VALUE_C, iter.next());
		assertSame(VALUE_B, iter.next());
	}

	@Test
	public void testInsertBeforeFirstElement() {
		list.insertAtHead(VALUE_A);
		list.insertAtHead(VALUE_B);

		assertEquals(2, list.size());
		Iterator<Integer> iter = list.iterator();
		assertSame(VALUE_B, iter.next());
		assertSame(VALUE_A, iter.next());
	}

	@Test
	public void testInsertAfterLastElement() {
		list.insertAtHead(VALUE_A);
		list.insert(VALUE_A, VALUE_B);

		assertEquals(2, list.size());
		Iterator<Integer> iter = list.iterator();
		assertSame(VALUE_A, iter.next());
		assertSame(VALUE_B, iter.next());
	}

	@Test
	public void testDeleteOnlyElement() {
		list.insertAtHead(VALUE_A);

		assertEquals(1, list.size());
		Iterator<Integer> iter = list.iterator();
		assertSame(VALUE_A, iter.next());
		list.deleteAtHead();
		assertEquals(0, list.size());
	}

	@Test
	public void testDeleteFromEmptyList() {
		assertFalse(list.deleteAtHead());
	}

	@Test
	public void testDeleteFirstElement() {
		list.insertAtHead(VALUE_A);
		list.insert(VALUE_A, VALUE_B);
		list.insert(VALUE_B, VALUE_C);

		assertEquals(3, list.size());
		Iterator<Integer> iter = list.iterator();
		assertSame(VALUE_A, iter.next());
		assertSame(VALUE_B, iter.next());
		assertSame(VALUE_C, iter.next());

		list.deleteAtHead();

		assertEquals(2, list.size());
		iter = list.iterator();
		assertSame(VALUE_B, iter.next());
		assertSame(VALUE_C, iter.next());
	}

	@Test
	public void testDeleteLastElement() {
		list.insertAtHead(VALUE_A);
		list.insert(VALUE_A, VALUE_B);
		list.insert(VALUE_B, VALUE_C);

		assertEquals(3, list.size());
		Iterator<Integer> iter = list.iterator();
		assertSame(VALUE_A, iter.next());
		assertSame(VALUE_B, iter.next());
		assertSame(VALUE_C, iter.next());

		list.delete(VALUE_C);

		assertEquals(2, list.size());
		iter = list.iterator();
		assertSame(VALUE_A, iter.next());
		assertSame(VALUE_B, iter.next());
	}

	@Test
	public void testDeleteMiddleElement() {
		list.insertAtHead(VALUE_A);
		list.insert(VALUE_A, VALUE_B);
		list.insert(VALUE_B, VALUE_C);

		assertEquals(3, list.size());
		Iterator<Integer> iter = list.iterator();
		assertSame(VALUE_A, iter.next());
		assertSame(VALUE_B, iter.next());
		assertSame(VALUE_C, iter.next());

		list.delete(VALUE_B);

		assertEquals(2, list.size());
		iter = list.iterator();
		assertSame(VALUE_A, iter.next());
		assertSame(VALUE_C, iter.next());
	}

	@Test
	public void testGetNodeByPosition() {
		list.insertAtHead(5);
		list.insertAtHead(8);
		list.insertAtHead(8);
		list.insertAtHead(6);
		list.insertAtHead(3);
		list.insertAtHead(1);
		list.insertAtHead(2);
		list.insertAtHead(2);
		list.insertAtHead(9);

		assertEquals(null, list.getNodeByPosition(12));
		assertSame(1, list.getNodeByPosition(3).value);
		assertSame(6, list.getNodeByPosition(5).value);
	}
/*************************************************************/
	public void setUpList1() {
		list.insertAtHead(5);
		list.insertAtHead(8);
		list.insertAtHead(8);
		list.insertAtHead(6);
		list.insertAtHead(3);
		list.insertAtHead(1);
		list.insertAtHead(2);
		list.insertAtHead(2);
		list.insertAtHead(9);
	}

	@Test
	public void testIsCircular() {
		setUpList1();

		assertFalse(list.isCircular());
		list.getNode(5).next = list.getNode(3);	// make list circular
		assertTrue(list.isCircular());
	}

	@Test
	public void testFindCircularListLength() {
		setUpList1();
		
		list.getNode(5).next = list.getNode(3);	// make list circular
		assertSame(9, list.findCircularListLength());
	}
	
	@Test
	public void testSwapKthElements() {
		setUpList1();

		list.swapKthElements(3);
		assertSame(6, list.getNodeByPosition(3).value);
		assertSame(1, list.getNodeByPosition(5).value);
	}

	@Test
	public void testGetNthToLastElement() {
		setUpList1();

		assertSame(6, list.getNthToLastElement(3).value);
	}

	@Test
	public void testReverseList() {
		setUpList1();

		list.reverseList();
		assertSame(5, list.getNodeByPosition(0).value);
		assertSame(8, list.getNodeByPosition(1).value);
		assertSame(8, list.getNodeByPosition(2).value);
		assertSame(6, list.getNodeByPosition(3).value);
		assertSame(3, list.getNodeByPosition(4).value);
		assertSame(1, list.getNodeByPosition(5).value);
		assertSame(2, list.getNodeByPosition(6).value);
		assertSame(2, list.getNodeByPosition(7).value);
		assertSame(9, list.getNodeByPosition(8).value);
	}

	@Test
	public void testRecursiveReverseList() {
		setUpList1();

		list.recursiveReverseList();
		assertSame(5, list.getNodeByPosition(0).value);
		assertSame(8, list.getNodeByPosition(1).value);
		assertSame(8, list.getNodeByPosition(2).value);
		assertSame(6, list.getNodeByPosition(3).value);
		assertSame(3, list.getNodeByPosition(4).value);
		assertSame(1, list.getNodeByPosition(5).value);
		assertSame(2, list.getNodeByPosition(6).value);
		assertSame(2, list.getNodeByPosition(7).value);
		assertSame(9, list.getNodeByPosition(8).value);
	}

	@Test
	public void testGetMidpoint() {
		setUpList1();
		assertSame(4, list.getMidpoint());

		//test for even # of elements
		list.insertAtHead(0);
		assertSame(4, list.getMidpoint());

		LinkedList newList = new LinkedList();
		assertSame(-1, newList.getMidpoint());
		newList.insertAtHead(1);
		assertSame(0, newList.getMidpoint());
	}

	@Test
	public void testFindConvergenceOfTwoLists() {
		setUpList1();
		LinkedList list2 = new LinkedList();
		list2.insertAtHead(1);
		list2.getNodeByPosition(0).next = list.getNodeByPosition(5);
// faulty because even though we link the 2nd list to the first, the size variable of the 2nd list isn't changed
		Node node = list.findConvergenceOfTwoLists(list2);

		assertSame(6, node.value);
	}

	@Test
	public void testMergeSort() {
		setUpList1();

		Node newHead = list.mergeSort(); 
		list.setHead(newHead);
		assertSame(1, list.getNodeByPosition(0).value);
		assertSame(2, list.getNodeByPosition(1).value);
		assertSame(2, list.getNodeByPosition(2).value);
		assertSame(3, list.getNodeByPosition(3).value);
		assertSame(5, list.getNodeByPosition(4).value);
		assertSame(6, list.getNodeByPosition(5).value);
		assertSame(8, list.getNodeByPosition(6).value);
		assertSame(8, list.getNodeByPosition(7).value);
		assertSame(9, list.getNodeByPosition(8).value);
	}

	@Test
	public void testElimateDuplicates() {
		setUpList1();

		list.eliminateDuplicates();
		assertSame(1, list.getNodeByPosition(0).value);
		assertSame(2, list.getNodeByPosition(1).value);
		assertSame(3, list.getNodeByPosition(2).value);
		assertSame(5, list.getNodeByPosition(3).value);
		assertSame(6, list.getNodeByPosition(4).value);
		assertSame(8, list.getNodeByPosition(5).value);
		assertSame(9, list.getNodeByPosition(6).value);
	}


/****
	@Test
	public void testConvertToDoublyLinkedList() {
		setUpList1();
	}
*****/
}


