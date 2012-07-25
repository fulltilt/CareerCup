package strings;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StringManipulationsTestCase {
	protected static StringManipulations strManip;

	@Before
	public void createList() {
		strManip = new StringManipulations();
	}
	
	@Test
	public void testLongestCommonSubsequence() {
		String a = "computer";
	    String b = "houseboat";
	    
	    String result = strManip.lcs(a, b);
	    assertEquals("out", result);
	}

}
