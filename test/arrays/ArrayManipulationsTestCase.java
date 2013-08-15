package arrays;

import static org.junit.Assert.*;

import java.util.Random;

import misc.Misc;

import org.junit.Before;
import org.junit.Test;

public class ArrayManipulationsTestCase {
	protected static ArrayManipulations arrManip;

	@Before
	public void createList() {
		arrManip = new ArrayManipulations();
	}

	@Test
	public void testDutchNationalFlag() {
		int[] arr = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};

		int[] result = arrManip.dutchNationalFlag(arr);
		assertSame(0, result[0]);
		assertSame(0, result[1]);
		assertSame(0, result[2]);
		assertSame(0, result[3]);
		assertSame(0, result[4]);
		assertSame(1, result[5]);
		assertSame(1, result[6]);
		assertSame(1, result[7]);
		assertSame(1, result[8]);
		assertSame(1, result[9]);
		assertSame(2, result[10]);
		assertSame(2, result[11]);
	}

	
	@Test
	public void testMergeSortedArray() {
		int[] array = ArrayManipulations.mergeSortedArrays(new int[]{1,3,5,9,10}, new int[]{2,4,6,7,8});
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
		assertEquals(ArrayManipulations.getMinimumOfRotatedArray(new int[]{3, 4, 5, 1, 2}), 1);
	}
	
	@Test
	public void testGetTurningPointIndex() {
		assertEquals(ArrayManipulations.getTurningPointIndex(new int[]{1, 2, 3, 4, 5, 10, 9, 8, 7, 6}), 5);
	}
	
	@Test
	public void testMergeTwoSortedArrays() {
		int[] x = new int[]{1,3,6,7,8,9,11};
		int[] y = new int[]{4,5,6,10};
		int[] result = arrManip.mergeTwoSortedArrays(x, y);
		assertSame(1, result[0]);
		assertSame(3, result[1]);
		assertSame(4, result[2]);
		assertSame(5, result[3]);
		assertSame(6, result[4]);
		assertSame(6, result[5]);
		assertSame(7, result[6]);
		assertSame(8, result[7]);
		assertSame(9, result[8]);
		assertSame(10, result[9]);
		assertSame(11, result[10]);

		int[] x2 = new int[]{5,6,7,9,11};
		int[] y2 = new int[]{1,2,3};
		result = arrManip.mergeTwoSortedArrays(x2, y2);
		assertSame(1, result[0]);
		assertSame(2, result[1]);
		assertSame(3, result[2]);
		assertSame(5, result[3]);
		assertSame(6, result[4]);
		assertSame(7, result[5]);
		assertSame(9, result[6]);
		assertSame(11, result[7]);
	}

	@Test
	public void testInterleave() {
		String[] result = arrManip.interleave(new String[]{"a1", "a2", "a3", "a4", "b1", "b2", "b3", "b4"});
		assertSame("a1", result[0]);
		assertSame("b1", result[1]);
		assertSame("a2", result[2]);
		assertSame("b2", result[3]);
		assertSame("a3", result[4]);
		assertSame("b3", result[5]);
		assertSame("a4", result[6]);
		assertSame("b4", result[7]);
	}

	@Test
	public void testFindFirstRepeat() {
		int result = arrManip.findFirstRepeat(new int[]{7,5,9,8,5,1,2,6,3,4});
		assertSame(5, result);
	}

	@Test
	public void testAddArray() {
		int[] a1 = new int[]{9,2,3,4};
		int[] a2 = new int[]{5,6,7,8};
		int[] result = arrManip.addArray(a1, a2);
		assertSame(1, result[0]);
		assertSame(4, result[1]);
		assertSame(9, result[2]);
		assertSame(1, result[3]);
		assertSame(2, result[4]);

		a1 = new int[]{2,7,8,9};
		a2 = new int[]{1};
		result = arrManip.addArray(a1, a2);
		assertSame(0, result[0]);
		assertSame(2, result[1]);
		assertSame(7, result[2]);
		assertSame(9, result[3]);
		assertSame(0, result[4]);
	}

	@Test
	public void testFindReplacedElement() {
		int[] a1 = new int[1000];

		// initialize the array
		for (int i = 0; i < 1000; i++)
			a1[i] = i + 1;

		assertEquals(568, arrManip.findReplacedElement(a1));	// for some reason, assertSame fails
	}

	@Test
	public void testPushZeroesToEnd() {
		int[] a1 = new int[]{0, 0, 1, 2, 0, 4, 0, 0 ,8 ,9};
		int[] result = arrManip.pushZeroesToEnd(a1);
		assertSame(9, result[0]);
		assertSame(8, result[1]);
		assertSame(1, result[2]);
		assertSame(2, result[3]);
		assertSame(4, result[4]);
		assertSame(0, result[5]);
		assertSame(0, result[6]);
		assertSame(0, result[7]);
		assertSame(0, result[8]);
		assertSame(0, result[9]);
	}

	@Test
	public void testPushZeroesToBeginning() {
		int[] a1 = new int[]{0, 0, 1, 2, 0, 4, 0, 0 ,8 ,9};
		int[] result = arrManip.pushZeroesToBeginning(a1);
		assertSame(0, result[0]);
		assertSame(0, result[1]);
		assertSame(0, result[2]);
		assertSame(0, result[3]);
		assertSame(0, result[4]);
		assertSame(4, result[5]);
		assertSame(2, result[6]);
		assertSame(1, result[7]);
		assertSame(8, result[8]);
		assertSame(9, result[9]);
	}

	@Test
	public void testFindEquilibriumIndex() {
		int a[] = new int[]{1,3,4,6,7,2,5,9,1,11,9,6,5,8,9,1};
		assertSame(9, arrManip.findEquilibriumIndex(a));
	}
	
	@Test
	public void testFindOddCountInteger() {
		int[] array = new int[]{1,1,4,2,2,3,3,4,4,5,5,0,0};
		assertSame(4, arrManip.FindOddCountInteger(array));
	}
	
	@Test
	public void testGenerateThirdArray() {
		int[] input = new int[]{3,8,1,3,9};
		int[] index = new int[]{0,1,1,2,2};
		int[] result = new int[5];
		
		arrManip.generateThirdArray(input, index, result);
		assertEquals(216, result[0]);
		assertEquals(81, result[1]);
		assertEquals(81, result[2]);
		assertEquals(648, result[3]);
		assertEquals(648, result[4]);
	}

	@Test
	public void testSearch2DArray() {
		int[][] a1 = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};

		assertTrue(arrManip.search2DArray(a1, 7));
		assertFalse(arrManip.search2DArray(a1, 19));
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
	public void testCountNegativeElemsIn2DArray() {
		int[][] a1 = new int[][]{{-12,-11,-10,-9,-8},{-7,-6,-5,-4,-3},{-2,-1,0,1,2},{3,4,5,6,7},{8,9,10,11,12}};

		assertSame(12, arrManip.countNegativeElemsIn2DArray(a1));
	}

	@Test
	public void testAreArraysEqual() {
		int[] arr1 = new int[]{1,2,3,4,5};
		int[] arr2 = new int[]{1,2,3,4,5};
		int[] arr3 = new int[]{1,2,3,4,3,2};
		int[] arr4 = new int[]{0,5,-5};
		int[] arr5 = new int[]{0};

		assertTrue(arrManip.areArraysEqual(arr1, arr2));
		assertFalse(arrManip.areArraysEqual(arr1, arr3));
		assertFalse(arrManip.areArraysEqual(arr4, arr5));		
	}
	
	@Test
	public void testArraysIntersection() {
		int[] arr1 = new int[10];
		int[] arr2 = new int[10];
		Random intGenerator = new Random();
		for (int i = 0; i < 10; i++) {
		  	arr1[i] = intGenerator.nextInt(10) + 1;
		  	arr2[i] = intGenerator.nextInt(10) + 1;
		  	System.out.println(arr1[i] + " " + arr2[i]);
		}		
		
		arrManip.arraysIntersections(arr1, arr2);
	}
	
	@Test
	public void testDoesArraySumExists() {
		int[] arr = new int[]{1,2,3,4,5};
		int sum = 9;
		
		assertTrue(arrManip.doesArraySumExists(arr, sum));
		assertFalse(arrManip.doesArraySumExists(arr, 90));
	}
	
	@Test
	public void testFindBeginningOfRotatedArray() {
		int[] arr = new int[]{6,7,8,9,10,11,12,13,1,2,3,4,5};
		assertSame(8, arrManip.findBeginningOfRotatedArray(arr, 0, arr.length - 1));
	}
	
	@Test
	public void testFindConsecutiveIndex() {
		int[] arr1 = new int[]{4,1,6,2,8,9,5,3,2,9,8,4,6};
		int[] arr2 = new int[]{6,1,2,9,8};
		arrManip.findConsecutiveIndex(arr1, arr2);
	}
	
	@Test
	public void testCreateLargestInt() {
		int[] arr = new int[]{2,3,9,78};
		assertEquals("93278", arrManip.createLargestInt(arr));
		arr = new int[]{9, 1, 97};
		assertEquals("9791", arrManip.createLargestInt(arr));
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
		
	/*
	@Test
	public void testFindElementInRotatedArray() {
		int[] arr = new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
		assertEquals(8, ArrayManipulations.findElementInRotatedArray(arr, 5));
		assertEquals(4, ArrayManipulations.findElementInRotatedArray(arr, 25));
		assertEquals(1, ArrayManipulations.findElementInRotatedArray(arr, 16));
	}
	*/
}
