package misc;

import java.util.*;

public class Misc {

    /* Recursive binary search on an array
       -algorithm: 
    */    
    public static boolean binarySearch(int[] array, int start, int end, int value) {
        if (start > end)
            return false;
            
        int middle = (start + end) / 2;
        if (array[middle] == value)
            return true;
        
        if (value < array[middle])
            return binarySearch(array, start, middle - 1, value);
        else
            return binarySearch(array, middle + 1, end, value);
    }
    
    /***********************/
    
    /* Non-recursive fxn to find nth fibonacci 
       -algorithm: Fibonacci revolves on the values of the previous 2 #'s so create two variables that refer to them. The very
                   first 2 numbers in the sequence is 0 and 1 so set those variables to each value respectively. In a for loop that
                   starts at 1, the next number in the sequence is the sum of the previous 2 variables. Once this is done, set the 
                   second to last variable to be the last variable and set the last variable to be the sum
    */    
    public static int fibonacci(int n) {    // non-recursive        
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        int previousPrevious = 0;
        int previous = 1;
        int sum = 0;
        for (int i = 1; i < n; i++) {
            sum = previousPrevious + previous;

            previousPrevious = previous;
            previous = sum;
        }
        
        return sum;
    }
    
    /***********************/
    
    /* CI40 - Write a power function 
       -algorithm: 
    */
    public static double Power(double base, int exponent) {     
        if (base == 0 && exponent == 0)
            throw new IllegalArgumentException("undefined");
        
        if (exponent == 0)
            return 1;
            
        int absoluteExponent = exponent;
        
        if (exponent < 0)   // to deal with negative exponents
            absoluteExponent = absoluteExponent * -1;
            
        double product = 1.0;    
        for (int i = 0; i < absoluteExponent; i++)
            product = product * base;
        
        if (exponent < 0)   // x^-2 is equivalent to 1 / x^2
            product = 1.0 / product;
            
        return product;    
    }
    
    /***********************/

    /* CI56 - is corresponding sequence for stack. For example, if the pushing sequence is {1, 2, 3, 4, 5}, 
        the sequence {4, 5, 3, 2, 1} is a corresponding popping sequence, but {4, 3, 5, 1, 2} is not.
       -algorithm: 
    */
    public static boolean isCorrespondingStackSequence(int[] pushingSequence, int[] poppingSequence) {     
        int pushingIndex = 0;
        int poppingIndex = 0;
        Stack<Integer> stack = new Stack<Integer>();

        while (pushingIndex < pushingSequence.length || poppingIndex < poppingSequence.length) {
            if (!stack.empty() && stack.peek() == poppingSequence[poppingIndex]) {
                stack.pop();
                ++poppingIndex;
            } else if (pushingIndex >= pushingSequence.length) { // if it gets here, it means that there's nothing else to push but we can't pop either so sequence is invalid
                return false;
            } else { 
                stack.push(pushingSequence[pushingIndex++]);
            }
        }
        
        return true;
    }
    
    /***********************/
    
    /* Sort a stack in ascending order
     * -algorithm: have one auxiliary stack which will hold the sorted stack. Pop off the top element (temp)
     *             from the original stack and if the aux stack isn't empty or the aux's top element is greater
     *             than the top element, keep popping elements from the aux stack and push it onto 
     *             the original stack. Afterwards, push temp onto the aux stack. That last step happens
     *             regardless. 
     *             -originally I was thinking of needing a 3rd stack but one must remember that when you pop off the
     *              top element and setting it to 'temp', 'temp' sort of acts like a stack that can only contain 1 element max
     */
    public static Stack<Integer> sortStack(Stack<Integer> stack) {
        Stack<Integer> aux = new Stack<Integer>();
        
        while (!stack.isEmpty()) {
            int temp = stack.pop(); // take off top element from stack
            while (!aux.isEmpty() && aux.peek() > temp)
                stack.push(aux.pop());
            
            aux.push(temp);
        }
        
        return aux;
    }
    
    /***********************/

    /* Get permutations of a String
     * -algorithm: 
     */
    public static ArrayList<String> getPermutations(String str) {
        ArrayList<String> permutations = new ArrayList<String>();
        if (str == null) 
            return null;
        else if (str.length() == 0) {   // base case 
            permutations.add("");   // don't know why but I need this line, else it doesn't work
            return permutations;
        }

        char first = str.charAt(0); // get the first character
        String remainder = str.substring(1); // remove the first character
        ArrayList<String> words = getPermutations(remainder);
        for (String word : words) {
            for (int j = 0; j <= word.length(); j++) {
                permutations.add(insertCharAt(word, first, j));
            }
        }
        
        return permutations;
    }
    // used to insert first character into every position of a String
    public static String insertCharAt(String word, char c, int i) {
        String start = word.substring(0, i);
        String end = word.substring(i);
        return start + c + end;
    }
    
    /***********************/
}
