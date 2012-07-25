package misc;

import java.util.ArrayList;
import java.util.List;
 
public class Trie {
   private TrieNode root;
   
   /**
    * Constructor
    */
   public Trie() {
      root = new TrieNode();
   }
   
   /**
    * Adds a word to the Trie
    * @param word
    */
   public void addWord(String word) {
      root.addWord(word.toLowerCase());
   }
   
   /**
    * Get the words in the Trie with the given
    * prefix
    * @param prefix
    * @return a List containing String objects containing the words in
    *         the Trie with the given prefix.
    */
   public List<String> getWords(String prefix) {
      //Find the node which represents the last letter of the prefix
      TrieNode lastNode = root;
      for (int i = 0; i < prefix.length(); i++) {
    	  lastNode = lastNode.getNode(prefix.charAt(i));
	 
		 //If no node matches, then no words exist, return empty list
		 if (lastNode == null) 
			 return new ArrayList<String>();	 
      }
      
      //Return the words which emanate from the last node
      return lastNode.getWords();
   }
   
   public static void main(String[] args) {
	   Trie trie = new Trie();
	   trie.addWord("A");
	   trie.addWord("to");
	   trie.addWord("tea");
	   trie.addWord("ted");
	   trie.addWord("inn");
	   ArrayList<String> l = (ArrayList<String>)trie.getWords("t");
	   for (Object s : l)
		   System.out.println(s);
   }
}
