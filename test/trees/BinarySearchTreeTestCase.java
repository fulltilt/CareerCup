package trees;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
}