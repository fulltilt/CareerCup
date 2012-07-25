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
	
	public void test() {
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

}
