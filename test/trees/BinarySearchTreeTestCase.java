package trees;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import trees.BinarySearchTree.Node;
import java.util.HashMap;

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
	public void testPrintByLevel() {
		System.out.println("\nprintByLevel()");
		tree.printByLevel();
	}	
	
	@Test
	public void testReversePrintByLevel() {
		System.out.println("\nreversePrintByLevel()");
		tree.reversePrintByLevel();
	}
	
	@Test
	public void testPrintByLevelZigZag() {
		System.out.println("\nPrint by zigzag()");
        //BinarySearchTree bst = new BinarySearchTree();
        tree.printByLevel();
        tree.printByLevelZigZag();
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
	
	//@Test void testLowestCommonAncestor() {
		//assertEquals(lowestCommonAncestor)
	//}
	
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
		System.out.println("\nBeginning testValueToSumOfChildrenValues()...");
		tree.printByLevel();
		tree.valueToSumOfChildrenValues();
		tree.printByLevel();
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
		HashMap<Integer, Integer> hm = tree.addColumns();
		// need to hard code the indexes since calling [HashMap].values() doesn't return the values in sorted order 
		assertSame(1, hm.get(-3));
		assertSame(2, hm.get(-2));
		assertSame(7, hm.get(-1));
		assertSame(12, hm.get(0));
		assertSame(8, hm.get(1));
		assertSame(9, hm.get(2));
		assertSame(10, hm.get(3));		
	}
	
	@Test 
	public void testSumOfAllGreaterAndEqualTo() {
		System.out.println("\nBegin testSumOfAllGreaterAndEqualTo(): Output should be: 49 48 46 43 39 34 27 19 10");
		tree.sumOfAllGreaterAndEqualTo();
		tree.print();
		System.out.println("End testSumOfAllGreaterAndEqualTo()");
	}
	
	@Test
	public void testIsBalanced() {
		assertFalse(tree.isBalanced());
		
		BinarySearchTree bst2 = new BinarySearchTree();
		bst2.insert(1);
		bst2.insert(0);
		bst2.insert(2);
		assertTrue(bst2.isBalanced());
	}
	
	@Test
	public void testCreateLinkedListByLevel() {
		System.out.println("\nBegin testCreateLinkedListByLevel()");
		tree.createLinkedListByLevel();
		System.out.println("End testCreateLinkedListByLevel()");
	}
	
	@Test
	public void testGetKthNode() {
		assertSame(tree.getKthNode(1).value, 1);
		assertSame(tree.getKthNode(4).value, 4);
		assertSame(tree.getKthNode(9).value, 10);
	}
}