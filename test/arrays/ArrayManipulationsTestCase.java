package arrays;

import static org.junit.Assert.*;
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
	public void testSearch2DArray() {
		int[][] a1 = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};

		assertTrue(arrManip.search2DArray(a1, 7));
		assertFalse(arrManip.search2DArray(a1, 19));
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
		assertSame(1, result[0]);
		assertSame(2, result[1]);
		assertSame(4, result[2]);
		assertSame(8, result[3]);
		assertSame(9, result[4]);
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
		assertSame(1, result[5]);
		assertSame(2, result[6]);
		assertSame(4, result[7]);
		assertSame(8, result[8]);
		assertSame(9, result[9]);
	}

	@Test
	public void testFindEquilibriumIndex() {
		int a[] = new int[]{1,3,4,6,7,2,5,9,1,11,9,6,5,8,9,1};
		assertSame(9, arrManip.findEquilibriumIndex(a));
	}
}
