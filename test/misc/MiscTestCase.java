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
	public void testPower() {
		assertEquals(Misc.Power(1,1), 1.0, .01);
		assertEquals(Misc.Power(2,2), 4.0, .01);
		assertEquals(Misc.Power(3,3), 27.0, .01);
		assertEquals(Misc.Power(4,-4), .00390625, .01);
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
}