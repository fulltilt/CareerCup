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

	@Test
	public void testIterativelyReverseString() {
		char[] str = new char[]{'H','e','l','l','o',' ','W','o','r','l','d','!'};
		char[] result = strManip.iterativelyReverseString(str);

		assertSame('!', result[0]);
		assertSame('d', result[1]);
		assertSame('l', result[2]);
		assertSame('r', result[3]);
		assertSame('o', result[4]);
		assertSame('W', result[5]);
		assertSame(' ', result[6]);
		assertSame('o', result[7]);
		assertSame('l', result[8]);
		assertSame('l', result[9]);
		assertSame('e', result[10]);
		assertSame('H', result[11]);
	}

	@Test
	public void testRecursivelyReverseString() {
		String str = "Hello World!";
		String result = strManip.recursivelyReverseString(new StringBuffer(), str, str.length());

		assertSame('!', result.charAt(0));
		assertSame('d', result.charAt(1));
		assertSame('l', result.charAt(2));
		assertSame('r', result.charAt(3));
		assertSame('o', result.charAt(4));
		assertSame('W', result.charAt(5));
		assertSame(' ', result.charAt(6));
		assertSame('o', result.charAt(7));
		assertSame('l', result.charAt(8));
		assertSame('l', result.charAt(9));
		assertSame('e', result.charAt(10));
		assertSame('H', result.charAt(11));
	}
	
	@Test
	public void testDeleteDuplicateChars() {
		String s1 = "The human torch was denied a bank loan";
		String s2 = "aeiou";

		char[] result = StringManipulations.deleteDuplicateChars(s1, s2);
		assertSame('T', result[0]);
		assertSame('h', result[1]);
		assertSame(' ', result[2]);
		assertSame('h', result[3]);
		assertSame('m', result[4]);
		assertSame('n', result[5]);
		assertSame(' ', result[6]);
		assertSame('t', result[7]);
		assertSame('r', result[8]);
		assertSame('c', result[9]);
		assertSame('h', result[10]);
		assertSame(' ', result[11]);
		assertSame('w', result[12]);
		assertSame('s', result[13]);
		assertSame(' ', result[14]);
		assertSame('d', result[15]);
		assertSame('n', result[16]);
		assertSame('d', result[17]);
		assertSame(' ', result[18]);
		assertSame(' ', result[19]);
		assertSame('b', result[20]);
		assertSame('n', result[21]);
		assertSame('k', result[22]);
		assertSame(' ', result[23]);
		assertSame('l', result[24]);
		assertSame('n', result[25]);
	}
	
	@Test
	public void testIsPalindrome() {
		assertTrue(strManip.isPalindrome("So many dynamos"));
		assertFalse(strManip.isPalindrome("So many dynamos!"));
	}

	@Test
	public void testReplaceSubstring() {
		String s1 = "A quick brown fox jumped over a bridge on a box";
		StringBuilder result = strManip.replaceSubstring(s1.toLowerCase(), "a", "the");
		assertEquals("the quick brown fox jumped over the bridge on the box", result.toString());	// call toString else it will fails since result is a StringBuilder object
	}
	
	@Test
	public void testCompress() {
		char[] test = new char[]{'a','a','a','a','b','c','c','a','a','d','e','e','e','e'};
		StringManipulations.compress(test); // should result in ['a','b','c','a','d','e']
		assertEquals('a', test[0]);
		assertEquals('b', test[1]);
		assertEquals('c', test[2]);
		assertEquals('a', test[3]);
		assertEquals('d', test[4]);
		assertEquals('e', test[5]);
	}
}
