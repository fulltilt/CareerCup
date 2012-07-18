package trees;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class BinaryTreeTestCase {
    private BinaryTree tree;

	@Before
    public void setUp() throws Exception {
		tree = new BinaryTree();
		// nodes are setup in BinaryTree constructor
    }

	@Test
    public void testPrintByLevel() {
		System.out.println("\nprintByLevel()");
		tree.printByLevel();
    }
	
	@Test
    public void testSearch() {
		assertEquals(true, tree.find(10));
        assertEquals(true, tree.find(9));
        assertEquals(true, tree.find(8));
        assertEquals(true, tree.find(7));
        assertEquals(true, tree.find(6));
        assertEquals(true, tree.find(5));
        assertEquals(true, tree.find(4));
        assertEquals(true, tree.find(3));
        assertEquals(true, tree.find(2));
        assertEquals(false, tree.find(111));
    }

	@Test
	public void testHeight() {
		assertEquals(6, tree.getHeight(tree.getRoot()));
	}
	
	@Test
	public void testFind() {
		assertTrue(tree.find(5));
		assertFalse(tree.find(11));
		assertTrue(tree.find(7));
		assertTrue(tree.find(1));
	}
	
	@Test
	public void testIsBST() {	// you'll have to recreate the tree to understand this
		assertFalse(tree.isBST(tree.getRoot()));
		assertTrue(tree.isBST(tree.getRoot().rightChild.rightChild));
		assertTrue(tree.isBST(tree.getRoot().rightChild.rightChild.rightChild));
	}
	
	@Test
	public void testGetDiameter() {
		assertSame(9, tree.getDiameter(tree.getRoot()));
		assertSame(7, tree.getDiameter(tree.getRoot().rightChild.rightChild));
	}
}
