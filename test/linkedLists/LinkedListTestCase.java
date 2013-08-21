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

	public void setUpList1() {
		list = new LinkedList();
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
	public void testgetNodeByIndex() {
		list.insertAtHead(5);
		list.insertAtHead(8);
		list.insertAtHead(8);
		list.insertAtHead(6);
		list.insertAtHead(3);
		list.insertAtHead(1);
		list.insertAtHead(2);
		list.insertAtHead(2);
		list.insertAtHead(9);

		assertEquals(null, list.getNodeByIndex(12));
		assertSame(1, list.getNodeByIndex(3).value);
		assertSame(6, list.getNodeByIndex(5).value);
	}
/*************************************************************/

	@Test
	public void testReversePrint() {
		setUpList1();
		
		System.out.print("\nReverse Print");
		list.displayList();
		System.out.println();
		list.recursiveReversePrint();
		System.out.println("\nEnd Reverse Print");
	}

	@Test
	public void testIsCircular() {
		setUpList1();

		assertFalse(list.isCircular());
		list.getNode(5).next = list.getNode(3);	// make list circular
		assertTrue(list.isCircular());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSwapKthElementsFromBeginningAndEnd() {
		setUpList1(); // 9 2 2 1 3 6 8 8 5

		list.swapKthElementsFromBeginningAndEnd(3);  // 9 2 8 1 3 6 2 8 5
		assertSame(8, list.getNodeByIndex(2).value);
		assertSame(2, list.getNodeByIndex(6).value);
		list.swapKthElementsFromBeginningAndEnd(90); // should throw Exception
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
		assertSame(5, list.getNodeByIndex(0).value);
		assertSame(8, list.getNodeByIndex(1).value);
		assertSame(8, list.getNodeByIndex(2).value);
		assertSame(6, list.getNodeByIndex(3).value);
		assertSame(3, list.getNodeByIndex(4).value);
		assertSame(1, list.getNodeByIndex(5).value);
		assertSame(2, list.getNodeByIndex(6).value);
		assertSame(2, list.getNodeByIndex(7).value);
		assertSame(9, list.getNodeByIndex(8).value);
	}
	
	@Test
	public void testRecursiveReverseList() {
		setUpList1();

		list.recursiveReverseList();
		assertSame(5, list.getNodeByIndex(0).value);
		assertSame(8, list.getNodeByIndex(1).value);
		assertSame(8, list.getNodeByIndex(2).value);
		assertSame(6, list.getNodeByIndex(3).value);
		assertSame(3, list.getNodeByIndex(4).value);
		assertSame(1, list.getNodeByIndex(5).value);
		assertSame(2, list.getNodeByIndex(6).value);
		assertSame(2, list.getNodeByIndex(7).value);
		assertSame(9, list.getNodeByIndex(8).value);
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
	public void testMergeSort() {
		setUpList1();

		Node newHead = list.mergeSort();
		list.setHead(newHead);
		assertSame(1, list.getNodeByIndex(0).value);
		assertSame(2, list.getNodeByIndex(1).value);
		assertSame(2, list.getNodeByIndex(2).value);
		assertSame(3, list.getNodeByIndex(3).value);
		assertSame(5, list.getNodeByIndex(4).value);
		assertSame(6, list.getNodeByIndex(5).value);
		assertSame(8, list.getNodeByIndex(6).value);
		assertSame(8, list.getNodeByIndex(7).value);
		assertSame(9, list.getNodeByIndex(8).value);
	}

	@Test
	public void testBubbleSort() {
		setUpList1();

		list.bubbleSort();
		assertSame(1, list.getNodeByIndex(0).value);
		assertSame(2, list.getNodeByIndex(1).value);
		assertSame(2, list.getNodeByIndex(2).value);
		assertSame(3, list.getNodeByIndex(3).value);
		assertSame(5, list.getNodeByIndex(4).value);
		assertSame(6, list.getNodeByIndex(5).value);
		assertSame(8, list.getNodeByIndex(6).value);
		assertSame(8, list.getNodeByIndex(7).value);
		assertSame(9, list.getNodeByIndex(8).value);

	}

	@Test
	public void testElimateDuplicates() {
		setUpList1();

		list.eliminateDuplicates();
		assertSame(1, list.getNodeByIndex(0).value);
		assertSame(2, list.getNodeByIndex(1).value);
		assertSame(3, list.getNodeByIndex(2).value);
		assertSame(5, list.getNodeByIndex(3).value);
		assertSame(6, list.getNodeByIndex(4).value);
		assertSame(8, list.getNodeByIndex(5).value);
		assertSame(9, list.getNodeByIndex(6).value);
	}
	
	@Test
	public void testMergeAlternating() {
		LinkedList list1 = new LinkedList();
		list1.insertAtHead(9);
		list1.insertAtHead(7);
		list1.insertAtHead(5);
		list1.insertAtHead(3);
		list1.insertAtHead(1);
		
		LinkedList list2 = new LinkedList();
		list2.insertAtHead(8);
		list2.insertAtHead(6);
		list2.insertAtHead(4);
		list2.insertAtHead(2);
		
		LinkedList.mergeAlternating(list1.getHead(), list2.getHead());
		
		assertSame(1, list1.getNodeByIndex(0).value);
		assertSame(2, list1.getNodeByIndex(1).value);
		assertSame(3, list1.getNodeByIndex(2).value);
		assertSame(4, list1.getNodeByIndex(3).value);
		assertSame(5, list1.getNodeByIndex(4).value);
		assertSame(6, list1.getNodeByIndex(5).value);
		assertSame(7, list1.getNodeByIndex(6).value);
		assertSame(8, list1.getNodeByIndex(7).value);
		assertSame(9, list1.getNodeByIndex(8).value);
	}

	@Test
	public void testIsPalindrome() {
		LinkedList list1 = new LinkedList();
		list1.insertAtHead(1);
		list1.insertAtHead(2);
		list1.insertAtHead(3);
		list1.insertAtHead(3);
		list1.insertAtHead(2);
		list1.insertAtHead(1);
		assertTrue(list1.isPalindrome());
			
		LinkedList list2 = new LinkedList();
		list2.insertAtHead(1);
		list2.insertAtHead(2);
		list2.insertAtHead(3);
		list2.insertAtHead(2);
		list2.insertAtHead(1);
		assertTrue(list2.isPalindrome());
		
		LinkedList list3 = new LinkedList();
		list3.insertAtHead(1);
		list3.insertAtHead(2);
		list3.insertAtHead(3);
		list3.insertAtHead(1);
		list3.insertAtHead(2);
		assertFalse(list3.isPalindrome());
		
		LinkedList list4 = new LinkedList();
		list4.insertAtHead(1);
		list4.insertAtHead(2);
		list4.insertAtHead(3);
		list4.insertAtHead(1);
		list4.insertAtHead(1);
		list4.insertAtHead(3);
		assertFalse(list3.isPalindrome());
	}
	
	@Test
	public void testFindThirds() {
		setUpList1();
		
		System.out.println("\nFind Thirds");
		System.out.println("Output should be: 1/3: 2 2/3: 6");
		list.findThirds();
		System.out.println("End Find Thirds");
	}

	@Test 
	public void testSwapEveryTwoElements() {
		LinkedList list1 = new LinkedList();
		list1.insertAtHead(7);
		list1.insertAtHead(6);
		list1.insertAtHead(5);
		list1.insertAtHead(4);
		list1.insertAtHead(3);
		list1.insertAtHead(2);
		list1.insertAtHead(1);
		
		list1.swapEveryTwoElements();
		assertSame(2, list1.getNodeByIndex(0).value);
		assertSame(1, list1.getNodeByIndex(1).value);
		assertSame(4, list1.getNodeByIndex(2).value);
		assertSame(3, list1.getNodeByIndex(3).value);
		assertSame(6, list1.getNodeByIndex(4).value);
		assertSame(5, list1.getNodeByIndex(5).value);		
		assertSame(7, list1.getNodeByIndex(6).value);
	}
	
	@Test
	public void testGetHeadOfLoop() {
		LinkedList list1 = new LinkedList();
		list1.insert(1);
		list1.insert(2);
		list1.insert(3);
		list1.insert(4);
		list1.insert(5);
		list1.insert(6);
		list1.insert(7);
		list1.insert(8);
		list1.getHead().next.next.next.next.next.next.next.next = list1.getHead().next.next;
		assertEquals(3, list1.getHeadOfLoop().value);		
	}
	
	@Test
	public void testGetKthNodeFromTail() {
		setUpList1();
		assertEquals(list.getKthNodeFromTail(9).value, 9);
		assertEquals(list.getKthNodeFromTail(5).value, 3);
		assertEquals(list.getKthNodeFromTail(1).value, 5);	
	}
	
	@Test
	public void testIntersectionOf2Lists() {
		LinkedList list = new LinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(6);
        list.insert(7);

        LinkedList list2 = new LinkedList();
        list2.insert(4);
        list2.insert(5);
        list2.insert(6);
        list2.insert(7);
        
        LinkedList list3 = null; //new LinkedList();

        assertEquals(LinkedList.intersectionOf2Lists(list, list2).value, 6);
        assertEquals(LinkedList.intersectionOf2Lists(list, list3), null);
	}
	
	@Test
	public void testEvensBeforeOdds() {

		

//System.out.println("\nEvensBeforeOdds");
//		list.evensBeforeOdds();
//		list.displayList();
	}
	
/****
	@Test
	public void testConvertToDoublyLinkedList() {
		setUpList1();
	}
*****/
}


