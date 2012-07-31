package linkedLists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import linkedLists.DoublyLinkedList.Node;

import org.junit.Before;
import org.junit.Test;

public class DoublyLinkedListTestCase {
	protected static DoublyLinkedList list;

	@Before
	public void createList() {
		list = new DoublyLinkedList();
	}

	@Test
	public void testInsertIntoEmptyList() {
		assertEquals(0, list.getSize());
		assertTrue(list.isEmpty());

		list.insertAtHead(1);

		assertEquals(1, list.getSize());
		assertFalse(list.isEmpty());
	}

	@Test
	public void testInsertBeforeFirstElement() {
		list.insertAtHead(1);
		list.insertAtHead(2);

		assertEquals(2, list.getSize());
		assertEquals(2, list.getNodeByIndex(0).value);
		assertEquals(1, list.getNodeByIndex(1).value);
	}
	
	@Test
	public void testInsertBetweenElements() {
		list.insertAtHead(1);
		list.insert(1, 2);
		list.insert(1, 3);

		assertEquals(3, list.getSize());

		assertEquals(1, list.getNodeByIndex(0).value);
		assertEquals(3, list.getNodeByIndex(1).value);
		assertEquals(2, list.getNodeByIndex(2).value);
	}

	@Test
	public void testInsertAfterLastElement() {
		list.insertAtHead(1);
		list.insert(1, 2);

		assertEquals(2, list.getSize());
		assertEquals(1, list.getNodeByIndex(0).value);
		assertEquals(2, list.getNodeByIndex(1).value);
	}

	@Test
	public void testDeleteOnlyElement() {
		list.insertAtHead(1);

		assertEquals(1, list.getSize());
		assertSame(1, list.getNodeByIndex(0).value);

		list.delete(1);
		assertEquals(0, list.getSize());
	}

	@Test 
	public void testDeleteFromEmptyList() {
		assertFalse(list.delete(1));
	}
	
	@Test
	public void testDeleteFirstElement() {
		list.insertAtHead(1);
		list.insert(1, 2);
		list.insert(2, 3);
		
		assertEquals(3, list.getSize());
		assertSame(1, list.getNodeByIndex(0).value);
		assertSame(2, list.getNodeByIndex(1).value);
		assertSame(3, list.getNodeByIndex(2).value);

		list.delete(1);

		assertEquals(2, list.getSize());
		assertSame(2, list.getNodeByIndex(0).value);
		assertSame(3, list.getNodeByIndex(1).value);
	}

	@Test
	public void testDeleteLastElement() {
		list.insertAtHead(1);
		list.insert(1, 2);
		list.insert(2, 3);

		assertEquals(3, list.getSize());
		assertSame(1, list.getNodeByIndex(0).value);
		assertSame(2, list.getNodeByIndex(1).value);
		assertSame(3, list.getNodeByIndex(2).value);
		
		list.delete(3);

		assertEquals(2, list.getSize());
		assertSame(1, list.getNodeByIndex(0).value);
		assertSame(2, list.getNodeByIndex(1).value);
	}

	@Test
	public void testDeleteMiddleElement() {
		list.insertAtHead(1);
		list.insert(1, 3);
		list.insert(3, 2);

		assertEquals(3, list.getSize());
		assertSame(1, list.getNodeByIndex(0).value);
		assertSame(3, list.getNodeByIndex(1).value);
		assertSame(2, list.getNodeByIndex(2).value);

		list.delete(3);

		assertEquals(2, list.getSize());
		assertSame(1, list.getNodeByIndex(0).value);
		assertSame(2, list.getNodeByIndex(1).value);	}

	@Test
	public void iterateBackwards() {
		list.insertAtHead(1);
		list.insert(1, 2);
		list.insert(2, 3);

		assertEquals(3, list.getSize());
		assertSame(1, list.getNodeByIndex(0).value);
		assertSame(2, list.getNodeByIndex(1).value);
		assertSame(3, list.getNodeByIndex(2).value);

		Node currentNode = list.getTail();
		assertEquals(3, currentNode.value);
		assertEquals(2, currentNode.previous.value);
		assertEquals(1, currentNode.previous.previous.value);
		assertEquals(null, currentNode.previous.previous.previous);
	}
}