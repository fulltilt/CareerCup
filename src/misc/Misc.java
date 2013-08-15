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
    
    /* CI5 - Duplicates in an Array ranging from 0 to n - 2, one duplicate 
       -algorithm: 
    */
    public static int findDuplicates1(int[] array) {    // assumes a valid array is passed in
        if (array == null || array.length < 2)
            return Integer.MIN_VALUE;
        
        int arraySum = 0;
        int actualSum = 0;
        for (int i = 0; i < array.length; i++) 
            arraySum += array[i];
        
        for (int i = 0; i < array.length - 1; i++)
            actualSum += i;
        
        actualSum = ((array.length - 2) * (array.length - 1)) >> 1;
        return arraySum - actualSum;
    } 
    
    /***********************/
   
    /* CI6 - Duplicates in an Array ranging from 0 to n - 1, multiple duplicates 
       -algorithm: 
    */
    public static int findFirstDuplicate(int[] array) {
        HashSet<Integer> hash = new HashSet<Integer>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0 || array[i] > array.length - 1)
                throw new IllegalArgumentException("Number out of bounds!");
            if (!hash.contains(array[i]))
                hash.add(array[i]);
            else {
                return array[i];
            }
        }
        
        return Integer.MIN_VALUE;
    }
    
    /***********************/
    
    /* CI7 - check whether # is in matrix or not 
       -algorithm: 
    */
    public static boolean inMatrix(int[][] array, int value) {
        // find the proper row to search
        int row = 0;
        boolean result = false;
        for (row = 0; row < array.length; row++) { // for multidimensional arrays, length is the # of rows
            int rowLength = array[row].length;
            if (array[row][rowLength - 1] >= value) {
                // use binary search to find value (assumption is that the rows are in order)
                
                // using built-in binary search
                //int index = Arrays.binarySearch(array[row], searchValue);
                //boolean result = index > -1 ? true : false;
                
                // manual binary search
                int start = 0;
                int end = rowLength - 1;
                
                while (start <= end) {
                    int middle = (start + end) / 2;
                    int temp = array[row][middle];
                    if (value == temp) {
                        result = true;
                        break;
                    }
                    
                    if (value < temp) 
                        end = middle - 1;
                    else
                        start = middle + 1;
                }
            }
        }
        
        return result;
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
    
    /* CI45 - Evens before odds 
       -algorithm: 
    */
    public static void evensBeforeOdds(int[] array) {
        // using strategy similar to the partition function for quicksort, swap evens and odds 
        int start = 0;
        int end = array.length - 1;
        
        while (true) {
            while (array[start] % 2 == 0)
                ++start;
                
            while (array[end] % 2 == 1)
                --end;
                
            if (end > start) {    
                int temp = array[end];
                array[end] = array[start];
                array[start] = temp;
            } else {
                break;
            }
        }
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
    
    /* CI71 - finds the intersection of two sorted arrays 
       -algorithm: 
    */
    public static void getIntersection(int[] array1, int[] array2) {        
        for (int i : array1) {
            int start = 0;
            int end = array2.length - 1;
            
            // manual binary search
            while (start <= end) {
                int middle = (start + end) / 2;
                int currentValue = array2[middle];
                if (currentValue == i) {
                    System.out.print(i + " ");
                    break;
                }
                    
                if (i > currentValue)
                    start = middle + 1;
                else
                    end = middle - 1;
            }
            
        }
    }
    
    /***********************/
    
    /* CI72 - greatest sum of consecutive subarrays 
       -algorithm: 
    */
    public static int greatestSumOfSubarrays(int[] array) {       
        // keep on adding #'s but whenever the sum is less than zero, restart at zero
        int max = 0;
        int currentSum = 0;
        
        for (int i : array) {
            currentSum += i;
            if (currentSum > max)
                max = currentSum;
                
            if (currentSum < 0)
                currentSum = 0;
        }
        
        return max;
    }
    
    /***********************/
    
    /* CI87 - is sum of 2 numbers in an array equal to k 
       -algorithm: 
    */    
    public static boolean isSumOf2Numbers(int[] array, int k) {
        int start = 0;
        int end = array.length - 1;
        
        while (start < end) {
            int sum = array[start] + array[end];
            if (sum == k)
                return true;
            else if (sum > k)
                --end;
            else
                ++start;
        }
        
        return false;
    }
    
    /***********************/
    
    /* CI88 - check whether it contains three numbers whose sum equals 0 
       -algorithm:
    */
    public static boolean isSumOf3NumbersZero(int[] array) {        
        if (array.length < 3)
            throw new IllegalArgumentException("not enough numbers!");
            
        for (int i = 0; i < array.length; i++) {
            if (isSumOf2Numbers2(array, -array[i], i))
                return true;
        }
        
        return false;
    }

    /*
       -algorithm:
    */    
    private static boolean isSumOf2Numbers2(int[] array, int k, int index) {
        int start = 0;
        int end = array.length - 1;
        
        while (start < end) {
            int sum = array[start] + array[end];
            if (sum == k)
                return true;
            else if (sum > k) {
                --end;
                if (end == index)
                    --end;
            } else {
                ++start;
                if (start == index)
                    ++start;
            }
        }
        
        return false;
    }       
    
    /***********************/
 
    /* CI90 - Given a positive value s, print all sequences with continuous numbers (with two numbers at least) whose sum is s 
       -algorithm:
    */
    public static void printContinuousSequence(int s) {        
        int startNumber = 1;
        int sum = startNumber;
        int currentNumber = startNumber + 1;;
        while (startNumber < s) {
            sum += currentNumber;
            if (sum < s) {
                ++currentNumber;
            } else if (sum > s) {
                ++startNumber;
                sum = startNumber;
                currentNumber = startNumber + 1;
            } else {
                System.out.println(startNumber + "~" + currentNumber);
                ++startNumber;
                sum = startNumber;
                currentNumber = startNumber + 1;
            }
        }
    }
    
    /***********************/
    
    /***********************/
}
