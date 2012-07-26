package strings;

import java.util.*;

public class StringManipulations {
	/*
	 * Longest Common Subsequence
	 */
	public String lcs(String a, String b){
	    int aLen = a.length();
	    int bLen = b.length();
	    if (aLen == 0 || bLen == 0){
	        return "";
	    } else if (a.charAt(aLen - 1) == b.charAt(bLen - 1)){
	        return lcs(a.substring(0, aLen - 1),b.substring(0, bLen - 1))
	            + a.charAt(aLen - 1);
	    } else {
	        String x = lcs(a, b.substring(0, bLen - 1));
	        String y = lcs(a.substring(0, aLen - 1), b);
	        return (x.length() > y.length()) ? x : y;
	    }
	}

	public char[] iterativelyReverseString(char[] str) {
	    for (int i = 0; i < str.length / 2; i++) {
	        str[str.length - i - 1] ^= str[i];
	        str[i] ^= str[str.length - i - 1];
	        str[str.length - i - 1] ^= str[i];
	    }
	    return str;
	}

	public String recursivelyReverseString(StringBuffer buffer, String str, int length) {
		if (length == 0)
			return buffer.append("").toString();
		else
			return recursivelyReverseString(buffer.append(str.charAt(length - 1)), str, length - 1).toString();
	}

	public char[] removeDuplicateChars(String s1, String s2) {
		s1 += " ";		// need the space else the last character will be repeated
		
		HashSet<Character> hs = new HashSet<Character>();	// create HashSet for s2
		for (int i = 0; i < s2.length(); i++)
			hs.add(s2.charAt(i));
		
		char[] charArr = s1.toCharArray();		// compare chars in s1 to HashSet
		for (int i = 0; i < charArr.length; i++) {
			if (hs.contains(charArr[i])) {	// if current char is in HashSet, shift all elements to immediate right to the left one time
				for (int j = i; j < charArr.length - 1; j++)
					charArr[j] = charArr[j + 1];
				--i;	// need this because if we shift all characters 1 to the left, we'd skip the first shifted character on the next iteration
			}
		}		
		
		return charArr;
	}

	public boolean isPalindrome(String s) {
		char[] str = s.toLowerCase().replace(" ", "").toCharArray();	// convert to all lowercase and strip spaces
	    for(int i = 0; i < str.length / 2; i++) {
	    	if (str[i] != str[str.length - i - 1])
	    		return false;
	   }
	   return true;
	}

	/* replace every occurrence of s1 with s2 in String str (s1 is assumed to be only a letter)
	 */
	public StringBuilder replaceSubstring(String str, String s1, String s2) {
		StringBuilder newString = new StringBuilder();
		String[] tokens = str.split(s1);
		
		for (int i = 0; i < tokens.length; i++) {	
			if (i != tokens.length - 1)
				newString.append(tokens[i] + s2);
			else
				newString.append(tokens[i]);
		}
		
		// handles situation when last char is equal to 's1' (however, because of this, s1 can only be one char long
		char lastChar = str.charAt(str.length() - 1);
		if (lastChar == s1.charAt(0))
			newString.append(s2);
		
		return newString;
	}
	
	public char[] compress(char[] str) {
		if (str.length == 0 || str.length == 1)
			return str;
		
		int currentData = 0, index = 0;
		char currentChar = str[0];
		
		while (currentData < str.length) {
			int count = 0;
			while ((currentData < str.length) && (str[currentData] == currentChar)) {
				++count;
				++currentData;
			}
			if (count > 1)
				str[++index] = (char) count;
			else
				str[++index] = str[currentData];
		}

		//System.out.println("count: " + count + " j: " + j + " currentChar: " + currentChar);
		
		return str;
	}
	
	
	public static void main(String[] args) {
		StringManipulations strManip = new StringManipulations();

		String s1 = "A quick brown fox jumped over a bridge on a box a";
		//String s2 = "AAAABBBCXYZEEEEPPPPPKKABC";
		//String s2 = "So many dynamos".toLowerCase().replace(" ", "");	// convert to all lowercase and strip spaces
		//char[] result = strManip.compress(s2.toCharArray());
		System.out.println(strManip.replaceSubstring(s1.toLowerCase(), "a", "the"));
		
	}
}
