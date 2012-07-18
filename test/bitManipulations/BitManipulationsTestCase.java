package bitManipulations;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BitManipulationsTestCase {
	protected static BitManipulations bm;

	@Before
	public void createBitManipulations() {
		bm = new BitManipulations();
	}

	@Test
	public void testCountOneBits() {
		assertSame(6, bm.countOneBits(159));	//10011111
	}

	@Test
	public void testCountZeroBits() {
		assertSame(2, bm.countZeroBits(159));	//10011111
	}

	@Test
	public void testFindMSB() {
		assertSame(6, bm.findMSB(101));	//1100101
	}

	@Test
	public void testIsNthBitSet() {
		assertTrue(bm.isNthBitSet(156, 4));	// 156 = 10011100
		assertFalse(bm.isNthBitSet(156, 5));	// 156 = 10011100
	}

	@Test
	public void testIsPalindrome() {
		assertTrue(bm.isPalindrome(5));		//101
		assertFalse(bm.isPalindrome(6));	//110
	}

	@Test
	public void testFindNextBiggestIntWithSameNoOfBits() {
		assertEquals("1010001", bm.findNextBiggestIntWithSameNoOfBits("1001100"));	//76
	}

	@Test
	public void testBitSwapsRequired() {
		assertSame(2, bm.bitSwapsRequired(156, 159));	//10011100 to 10011111: needs 2
	}

	@Test
	public void testGetBinaryRepresentation() {
		assertEquals("1100101", bm.getBinaryRepresentation(101));
	}

	@Test
	public void testReverseBits() {
		assertSame(57, bm.reverseBits(156));	//10011100 reversed is 111001 or 57 in decimal
	}

	@Test
	public void testSwapEvenAndOddBits() {
		assertEquals(154, bm.swapOddEvenBits(101));	//01100101 to 10011010
	}

	@Test
	public void testSwapWithoutTemp() {
		assertSame(9, bm.swapWithoutTemp(5, 9));
	}
}

