package misc;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class MiscTestCase {

	@Test
	public void test() {
       int[] array = {1,20,45,78,99,102,189,7777};
       assertTrue(Misc.binarySearch(array, 0, array.length - 1, 7777));
       assertFalse(Misc.binarySearch(array, 0, array.length - 1, 2));
	}

	@Test 
	public void testFindDuplicates1() {
		int[] array = {0,3,1,3,2};
		assertEquals(Misc.findDuplicates1(array), 3);
	}

	@Test 
	public void testFindFirstDuplicates() {
		int[] array = {2,3,1,0,2,5,3};
		assertEquals(Misc.findFirstDuplicate(array), 2);
	}	
	
	@Test
	public void testInMatrix() {
        int[][] array = {{1,3,5},
                         {7,9,11},
                         {13,15,17}};
        assertTrue(Misc.inMatrix(array, 17));
        assertFalse(Misc.inMatrix(array, 12));
	}
	
	@Test
	public void testMergeSortedArray() {
		int[] array = Misc.mergeSortedArrays(new int[]{1,3,5,9,10}, new int[]{2,4,6,7,8});
		assertEquals(array[0], 1);
		assertEquals(array[1], 2);
		assertEquals(array[2], 3);
		assertEquals(array[3], 4);
		assertEquals(array[4], 5);
		assertEquals(array[5], 6);
		assertEquals(array[6], 7);
		assertEquals(array[7], 8);
		assertEquals(array[8], 9);
		assertEquals(array[9], 10);
	}
	
	@Test
	public void testGetMinimumOfRotatedArray() {
		assertEquals(Misc.getMinimumOfRotatedArray(new int[]{3, 4, 5, 1, 2}), 1);
	}
	
	@Test
	public void testGetTurningPointIndex() {
		assertEquals(Misc.getTurningPointIndex(new int[]{1, 2, 3, 4, 5, 10, 9, 8, 7, 6}), 5);
	}	

	@Test
	public void testPower() {
		assertEquals(Misc.Power(1,1), 1.0, .01);
		assertEquals(Misc.Power(2,2), 4.0, .01);
		assertEquals(Misc.Power(3,3), 27.0, .01);
		assertEquals(Misc.Power(4,-4), .00390625, .01);
	}	
	
	@Test
	public void testEvensBeforeOdds() {
		int[] array = new int[]{1, 5, 3, 4, 2};
		Misc.evensBeforeOdds(array);
		assertEquals(array[0], 2);
		assertEquals(array[1], 4);
		assertEquals(array[2], 3);
		assertEquals(array[3], 5);
		assertEquals(array[4], 1);
	}
	
	@Test
	public void testIsCorrespondingStackSequence() {
	    int[] pushingSequence = {1, 2, 3, 4, 5};
	    int[] poppingSequence1 = {4, 5, 3, 2, 1};
	    int[] poppingSequence2 = {4, 3, 5, 1, 2};
	    assertTrue(Misc.isCorrespondingStackSequence(pushingSequence, poppingSequence1));
	    assertFalse(Misc.isCorrespondingStackSequence(pushingSequence, poppingSequence2));		
	}
	
	@Test
	public void testSortStack() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(2);
		stack.push(4);
		stack.push(1);
		stack.push(3);
		stack.push(5);
		stack = Misc.sortStack(stack);
		assertEquals(stack.pop().intValue(), 5);
		assertEquals(stack.pop().intValue(), 4);
		assertEquals(stack.pop().intValue(), 3);
		assertEquals(stack.pop().intValue(), 2);
		assertEquals(stack.pop().intValue(), 1);
	}
	
	@Test
	public void testGetIntersection() {
	    int[] a = {1, 4, 7, 10, 13};
	    int[] b = {1, 3, 5, 7, 9};
	    int[] c = {1, 4, 7, 10, 13};
	    Misc.getIntersection(a, b); System.out.println();
	    Misc.getIntersection(a, c); System.out.println();
	}
	
	@Test
	public void testGreatestSumOfSubarrays() {
	    int[] array =  {1, -2, 3, 10, -4, 7, 2,-5};
	    assertEquals(Misc.greatestSumOfSubarrays(array), 18);		
	}
	
	@Test 
	public void testIsSumOf2Numbers() {
	    int[] array =  {1, 2, 4, 7, 11, 15};
	    assertTrue(Misc.isSumOf2Numbers(array, 15));
	    assertTrue(Misc.isSumOf2Numbers(array, 5));
	    assertTrue(Misc.isSumOf2Numbers(array, 3));
	    assertFalse(Misc.isSumOf2Numbers(array, 14));
	}
	
	@Test
	public void testIsSumOf3NumbersZero() {
		assertFalse(Misc.isSumOf3NumbersZero(new int[]{1, 2, 4, 7, 11, 15}));
		assertTrue(Misc.isSumOf3NumbersZero(new int[]{1, 2, 4, -6, 11, 15}));		
	}
	
	@Test
	public void testPrintContinuousSequence() {
	    Misc.printContinuousSequence(15);        	
	}
}
