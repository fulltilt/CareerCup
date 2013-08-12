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

	/* reverse a C-style string. 
	   -algorithm:  iterate through 1/2 of the length of the string and swap each equal but opposite indexes of the string. This algorithm
	                uses XOR's so there is no need for a temp variable (http://stackoverflow.com/a/528946/1202995)
	*/
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
	
	
    /* CI78 - delete characters contained in the second string from the first string 
       -algorithm: put chars in 2nd string into a hash table and set each respective char entry to 'true'
                   Iterate through 1st string using 2 pointers: 1 that advances every step and a trailing pointer
                   that only advances if the current char isn't true in the hash table. Before this is done, set the
                   char at the trailing index to be equal to the current char which effectively overwrites any duplicates 
                   characters
    */
    public static String deleteDuplicateChars(String str1, String str2) {
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();        
        
        boolean[] ascii = new boolean[256];
        for (char c : char2)
            ascii[c] = true;
            
        int trailing = 0;    
        for (int i = 0; i < char1.length; i++) {
            if (ascii[char1[i]] == false) {
                char1[trailing++] = char1[i];
            }
        }
            
        return new String(char1, 0, trailingIndex);
    } 

    // similiar fxn as above but doesn't inefficient shifting after encountering a duplicate
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
    
    /***********************/
    
    /* CI76 - find the first character in a string that only appears once */
    public static char findFirstSingleChar(String str) {        
        int[] ascii = new int[256]; // make ascii hashtable
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            ascii[currentChar]++;
        }

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (ascii[currentChar] == 1) 
                return currentChar;
        }
        
        return ' ';
    }
    
    
    /***********************/

    /* CI79 - delete all duplicated characters in a string and keep only the first occurrence of each character left 
       -algorithm: iterate through string and set respective char values to true in a hash table. Iterate through string using 2 pointers:
                   one pointer that moves forward each step and a trailing index that advances only when the current char is true in the
                   hash table. Before this is done, set the char at the trailing index to the char at the current index. Afterwards, set 
                   the respective char to be false in the hash table or else further duplicates would not be passed over resulting in the 
                   string being untouched since by default, every character in the hash table is true
    */
    public static String deleteDuplicateCharsExceptFirst(String str) {
        char[] array = str.toCharArray();
        boolean[] ascii = new boolean[256];
        for (char c : array)
            ascii[c] = true;
            
        int trailingIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (ascii[array[i]] == true) {
                array[trailingIndex++] = array[i];
                ascii[array[i]] = false;    // was confused as to why but without this here, duplicate letters would not be passed over
            }
        }
        
        return new String(char1, 0, trailingIndex);
    }  

    /***********************/

    /* CI80 - are 2 words anagrams 
       -algorithm: setup an int hash table and iterate through one string and for each char, increment the respective bucket by 1. Iterate
                   through the other string and decrement each respective bucket. If you encounter a bucket that is 0 before decrementing,
                   that means there's an inbalance of chars so return false. This works only if you confirm that the length of both strings
                   are the same.
    */
    public static boolean areAnagrams(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;
        
        int[] ascii = new int[256];
        char[] str1Array = str1.toCharArray();
        for (char c : str1Array) 
            ascii[c]++;
        
        char[] str2Array = str2.toCharArray();
        for (char c : str2Array) {
            if (ascii[c] == 0)  // at least 1 more occurence in str2 than str1
                return false;
                
            ascii[c]--;    
        }
        
        return true;
    }

    /* Assume you have a method isSubstring which checks if one word is a substring of 
       another Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using 
       only one call to isSubstring (i e , “waterbottle” is a rotation of “erbottlewat”)
       -algorithm: first check if the lengths are both equal else return false. Next, append
                   one of the strings to itself and check to see if the other string is a substring of the other
    */
    public static boolean isRotation(String str1, String str2) {
        if (str1.length() != str2.length() && str1.length() != 0)
            return false;
            
        String temp = str2 + str2;
        return isSubstring(temp, str1);
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
