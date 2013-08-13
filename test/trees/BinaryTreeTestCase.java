package trees;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import trees.BinaryTree.Node;

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
	public void testIsBST2() { // testing 2nd version of isBST
		BinaryTree bst = new BinaryTree();
	    bst.setRoot(5);
	    bst.getRoot().leftChild = new Node(4);
	    bst.getRoot().leftChild.leftChild = new Node(3);      
	    bst.getRoot().rightChild = new Node(9);
	    bst.getRoot().rightChild.leftChild = new Node(8);
	    bst.getRoot().rightChild.rightChild = new Node(10);
	    System.out.println(bst.isBST());
	    assertTrue(bst.isBST());
	    assertFalse(tree.isBST());
	}
	
	@Test
	public void testIsSubtree() {
		BinaryTree bst = new BinaryTree();
	    bst.setRoot(8);
	    bst.getRoot().leftChild = new Node(8);
	    bst.getRoot().leftChild.leftChild = new Node(9);
	    bst.getRoot().leftChild.rightChild = new Node(2);
	    bst.getRoot().leftChild.rightChild.leftChild = new Node(4);
	    bst.getRoot().leftChild.rightChild.rightChild = new Node(7);
	    bst.getRoot().rightChild = new Node(7);
	        
	    BinaryTree bst2 = new BinaryTree();
	    bst2.setRoot(8);
	    bst2.getRoot().leftChild = new Node(9);
	    bst2.getRoot().rightChild = new Node(2);
	    assertTrue(BinaryTree.isSubTree(bst, bst2));
	    assertTrue(BinaryTree.isSubTree(bst2, bst));
	}

	@Test
	public void testGetDiameter() {
		assertSame(9, tree.getDiameter(tree.getRoot()));
		assertSame(7, tree.getDiameter(tree.getRoot().rightChild.rightChild));
	}
	
	@Test
	public void testGetMaxElement() {
		assertSame(10, tree.getMaxElement());
		tree.getRoot().leftChild.leftChild.leftChild = new Node(40);
		assertSame(40, tree.getMaxElement());
	}
	
	@Test
	public void testHasPathSum() {
		System.out.println("\nTesting hasPathSum() and printSumPaths()....");
        BinaryTree bt = new BinaryTree();
        bt.setRoot(10);
        bt.getRoot().leftChild = new Node(5);
        bt.getRoot().rightChild = new Node(12);        
        bt.getRoot().leftChild.leftChild = new Node(4);
        bt.getRoot().leftChild.rightChild = new Node(7);
        assertTrue(bt.hasPathSum(22));
        bt.printSumPaths(22);
        assertTrue(bt.hasPathSum(15));	// test that it stops when the sum is in the middle of the tree
        bt.printSumPaths(15);
        assertFalse(bt.hasPathSum(11));
        bt.printSumPaths(11);
	}
	
	@Test
	public void testPrintPaths() {
		System.out.println("\nTesting printPaths()....");
        BinaryTree bt = new BinaryTree();
        bt.printPaths();
	}
	
	@Test
	public void testIsSymmetrical() {
        BinaryTree bst = new BinaryTree();
        bst.setRoot(8);
        bst.getRoot().leftChild = new Node(6);
        bst.getRoot().leftChild.leftChild = new Node(5);
        bst.getRoot().leftChild.rightChild = new Node(7);
        bst.getRoot().rightChild = new Node(6);
        bst.getRoot().rightChild.leftChild = new Node(7);
        bst.getRoot().rightChild.rightChild = new Node(5);
        assertTrue(bst.isSymmetrical());
        
        BinaryTree bst2 = new BinaryTree();
        bst2.setRoot(8);
        bst2.getRoot().leftChild = new Node(6);
        bst2.getRoot().leftChild.leftChild = new Node(5);
        bst2.getRoot().leftChild.rightChild = new Node(7);
        bst2.getRoot().rightChild = new Node(9);
        bst2.getRoot().rightChild.leftChild = new Node(7);
        bst2.getRoot().rightChild.rightChild = new Node(5);
        assertFalse(bst2.isSymmetrical());
        
        BinaryTree bst3 = new BinaryTree();
        bst3.setRoot(8);
        bst3.getRoot().leftChild = new Node(6);
        bst3.getRoot().leftChild.leftChild = new Node(5);
        bst3.getRoot().leftChild.rightChild = new Node(7);
        bst3.getRoot().rightChild = new Node(6);
        bst3.getRoot().rightChild.leftChild = new Node(7);        
        assertFalse(bst3.isSymmetrical());  
	}

	@Test 
	public void testGetDepth() {
		assertSame(tree.getDepth(), 6);
	}	
	
	@Test
	public void testIsBalanced() {
		assertFalse(tree.isBST());
		
	    BinaryTree bst2 = new BinaryTree();
	    bst2.setRoot(8);
	    bst2.getRoot().leftChild = new Node(9);
	    bst2.getRoot().rightChild = new Node(2);
	    assertTrue(bst2.isBalanced());
	}
	
	@Test
	public void testLowestCommonAncestor() {
		BinaryTree bst = new BinaryTree();
		Node n1 = bst.getRoot().leftChild.leftChild.rightChild;
		Node n2 = bst.getRoot().leftChild.rightChild;
		Node n3 = bst.getRoot().rightChild.rightChild.leftChild.leftChild.leftChild;
		Node n4 = bst.getRoot().rightChild.rightChild.rightChild.rightChild.rightChild;
		assertEquals(2, bst.lowestCommonAncestor(n1, n2).value);
		assertEquals(5, bst.lowestCommonAncestor(n3, n4).value);
		assertEquals(1, bst.lowestCommonAncestor(n1, n4).value);
	}
	
	@Test
	public void testFindNode() {
		BinaryTree bst = new BinaryTree();
		Node n1 = bst.getRoot().leftChild.leftChild.rightChild;
		Node n3 = bst.getRoot().rightChild.rightChild.leftChild.leftChild.leftChild;
		Node n4 = bst.getRoot().rightChild.rightChild.rightChild.rightChild.rightChild;
		assertTrue(bst.findNode(bst.getRoot(), n1));
		assertTrue(bst.findNode(bst.getRoot(), n4));
		assertFalse(bst.findNode(bst.getRoot().leftChild, n3));
	}

	@Test
	public void testMirror() {
		System.out.println("\nmirror()");
        BinaryTree bst = new BinaryTree();
        bst.printByLevel();
        bst.mirror();
	}

	@Test
	public void testPrintByLevelZigZag() {
		System.out.println("\nprint by zigzag()");
        BinaryTree bst = new BinaryTree();
        bst.printByLevel();
        bst.printByLevelZigZag();
	}	
}
