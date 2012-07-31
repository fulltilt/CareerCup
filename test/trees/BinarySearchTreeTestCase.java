package trees;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import trees.BinarySearchTree.Node;

public class BinarySearchTreeTestCase {
    private BinarySearchTree tree;

	@Before
    public void setUp() throws Exception {
		tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(4);
        tree.insert(8);
        tree.insert(2);
        tree.insert(1);
        tree.insert(3);
        tree.insert(7);
        tree.insert(9);
        tree.insert(10);
    }

	@Test
    public void testSearch() {
		assertEquals(true, tree.find(1));
        assertEquals(true, tree.find(2));
        assertEquals(true, tree.find(3));
        assertEquals(true, tree.find(4));
        assertEquals(true, tree.find(5));
        assertEquals(true, tree.find(9));
        assertEquals(true, tree.find(7));
        assertEquals(true, tree.find(8));
        assertEquals(true, tree.find(10));
        assertEquals(false, tree.find(809));
    }	
	
	@Test
	public void testPrintPaths() {
		System.out.println("\nprintPaths()");
		tree.printPaths();
	}
	
	@Test
	public void testPrintByLevel() {
		System.out.println("\nprintByLevel()");
		tree.printByLevel();
	}	
	
	@Test
	public void testReversePrintByLevel() {
		System.out.print("\nreversePrintByLevel()");
		tree.reversePrintByLevel();
	}

	@Test
	public void testMirror() {
		System.out.println("\nmirror()");
		tree.mirror();
	}
	
	@Test
	public void testSize() {
		assertEquals(9, tree.getSize());
	}

	@Test
	public void testHeight() {
		assertEquals(4, tree.getHeight());
	}

	@Test
	public void testHasPathSum() {
		assertTrue(tree.hasPathSum(14));
		assertFalse(tree.hasPathSum(140));
		assertTrue(tree.hasPathSum(32));
	}
	
	@Test
	public void testIsTreeEqual() {
		BinarySearchTree tree2 = new BinarySearchTree();
        tree2.insert(5);
        tree2.insert(4);
        tree2.insert(8);
        tree2.insert(2);
        tree2.insert(1);
        tree2.insert(3);
        tree2.insert(7);
        tree2.insert(9);
        tree2.insert(10);
        
        assertTrue(tree.isTreeEqual(tree2.getRoot()));
	}	
	
	@Test
	public void testPrintNodesInRange() {
		System.out.println("\nBegin testPrintNodesInRange()");
		tree.printNodesInRange(3, 8);
		System.out.println("\nEnd testPrintNodesInRange()");
	}
	
	@Test
	public void testTrimTreeInRange() {
		System.out.println("\nBegin testTrimTreeInRange()");
		Node newNode = tree.trimTreeInRange(3, 8);
		tree.print(newNode);
		System.out.println("\nEnd testTrimTreeInRange()");
	}	

	@Test
	public void testConvertToLinkedList() {
		linkedLists.LinkedList newList = tree.convertToLinkedList();

		assertSame(1, newList.getNodeByIndex(0).value);
		assertSame(2, newList.getNodeByIndex(1).value);
		assertSame(3, newList.getNodeByIndex(2).value);
		assertSame(4, newList.getNodeByIndex(3).value);
		assertSame(5, newList.getNodeByIndex(4).value);
		assertSame(7, newList.getNodeByIndex(5).value);
		assertSame(8, newList.getNodeByIndex(6).value);
		assertSame(9, newList.getNodeByIndex(7).value);
		assertSame(10, newList.getNodeByIndex(8).value);
	}
	
	@Test
	public void testConvertToDoublyLinkedList() {
		linkedLists.DoublyLinkedList newList = tree.convertToDoublyLinkedList();

		linkedLists.DoublyLinkedList.Node currentNode = newList.getTail();
		assertSame(10, currentNode.value); currentNode = currentNode.previous;
		assertSame(9, currentNode.value); currentNode = currentNode.previous;
		assertSame(8, currentNode.value); currentNode = currentNode.previous;
		assertSame(7, currentNode.value); currentNode = currentNode.previous;
		assertSame(5, currentNode.value); currentNode = currentNode.previous;
		assertSame(4, currentNode.value); currentNode = currentNode.previous;
		assertSame(3, currentNode.value); currentNode = currentNode.previous;
		assertSame(2, currentNode.value); currentNode = currentNode.previous;
		assertSame(1, currentNode.value); currentNode = currentNode.previous;
		assertSame(null, currentNode); 
	}	
	
	@Test
	public void testValueToSumOfChildrenValues() {
		System.out.println("\nBegin testValueToSumOfChildrenValues()");
		tree.valueToSumOfChildrenValues();
		tree.print();
		System.out.println("End testValueToSumOfChildrenValues()");		
	}
	
	@Test
	public void testIsTreeSymmetric() {
		assertFalse(tree.isTreeSymmetric());
		
		BinarySearchTree tree2 = new BinarySearchTree();
		tree2.insert(5);
		tree2.insert(3);
		tree2.insert(4);
		tree2.insert(9);
		tree2.insert(8);
		assertTrue(tree2.isTreeSymmetric());
	}
	
	@Test
	public void testAddColumns() {
		Collection<Integer> al = tree.addColumns();
		
	}
}