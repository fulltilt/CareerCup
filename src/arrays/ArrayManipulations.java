package arrays;

public class ArrayManipulations {
    
    /* Write a method that when given a list, ensures that all consecutive repeated elements of the list are removed. The order of the elements should remain the same.

       simplify(['a','a','a','a','b','c','c','a','a','d','e','e','e','e'])
       returns ['a','b','c','a','d','e']
       The tricky part is if you look at the 2 sets of a's 
       Note: with JavaScripts splice() fxns, this fxn would probably be easier to write 
       -algorithm: have 2 pointers. One that is ahead by the other by at least 1. Have a loop that runs as long as fast is less
                   than the length of the array. If slow and fast are equal or fast == ' ', set fast to ' ' and advance fast. Else
                   advance slow and set slow to fast and set fast to be one greater than slow. By default, this will blank out the
                   array all the way to the end if need be 
    */
    public static void simplify(char[] array) {
        int slow = 0;
        int fast = 1;
        int temp = 0;
        while (fast < array.length) {
            if (array[slow] == array[fast] || array[fast] == ' ') {
                array[fast] = ' ';
                ++fast;
            } else {
                array[++slow] = array[fast];
                array[fast] = ' ';
                fast = slow + 1;        
            }   
        }
    }

    /* JavaScript version of above
    function simplify(array) {
        var slow = 0;
        var fast = 1;
        while (fast < array.length) {
            if (array[slow] === array[fast]) {
                var temparr1 = array.slice(0, fast);
                var temparr2 = array.slice(fast + 1);
                array = temparr1.concat(temparr2); // when I used '+', I got weird results
            } else {
                ++slow;
                fast = slow + 1;
            }
        }
        console.log(array);
    } 
    */   

    /***********************/

    /* CI10 - merging sorted arrays (following deals with arrays of all types of lengths) 
     * -algorithm: create a 3rd array that is the length of the first 2 combined. Create a separate index for each array starting
                   at 0. Enter a while loop that exits once one of the first 2 indexes equals it's respective arrays length. Inside
                   loop, compare the first 2 arrays at their current indices and enter the lesser of the 2 onto the 3rd array and
                   increment the respective indices. Once outside the loop, one array should be empty so put the rest of the non-empty
                   array onto the 3rd array. This can also be done backwards which is useful if you can't use a 3rd array and are
                   forced to add to the first array which has space to accomodate both array values  
     */
    public static int[] mergeSortedArrays(int[] array1, int[] array2) {
        int[] array3 = new int[array1.length + array2.length];
        
        int index1 = 0;
        int index2 = 0;
        int indexMerged = 0;

        while(index1 < array1.length && index2 < array2.length) { 
            if(array1[index1] <= array2[index2]) 
                array3[indexMerged++] = array1[index1++]; 
            else 
                array3[indexMerged++] = array2[index2++]; 
        } 
         
        // deal with reminder once one of the arrays is empty
        while (index1 < array1.length)
            array3[indexMerged++] = array1[index1++];
        while (index2 < array2.length)
            array3[indexMerged++] = array2[index2++];
        
        return array3;
        
        /* from right to left (good when you have to append to the first array so you don't have
         * to worry about shifting right all the time
        int[] array1 = {1,3,5,7,9};
        int[] array2 = {2,4,6,8,10};
        int[] array3 = new int[array1.length + array2.length];
        
        int index1 = array1.length - 1;
        int index2 = array2.length - 1;
        int indexMerged = array3.length - 1;

        while(index1 >= 0 && index2 >= 0) { // important that you use '>=' else you will break out of the while statement with at least 1 element in each array
            if(array1[index1] >= array2[index2]) 
                array3[indexMerged--] = array1[index1--]; 
            else 
                array3[indexMerged--] = array2[index2--]; 
        } 
         
        // deal with reminder once one of the arrays is empty
        while (index1 >= 0)
            array3[indexMerged--] = array1[index1--];
        while (index2 >= 0)
            array3[indexMerged--] = array2[index2--];
            
        for (int i = 0; i < array3.length; i++)
            System.out.println(array3[i]);        
        */
    }
    
    /* note: assumes that array 'a' is equal to or longer than array 'b'
    */
    public int[] mergeTwoSortedArrays(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = a.length - 1;
        int j = b.length - 1;
        int k = c.length - 1;

        while(i >= 0 && j >= 0) {
            if(a[i] > b[j])
                c[k--] = a[i--];
            else
                c[k--] = b[j--];
        }

        // deals with any leftovers for any of the arrays (no need to have separate pointers to account for the longer and shorter arrays
        if (i != -1) {  // if array a has leftovers
            while(i >= 0)
               c[k--] = a[i--];
        } else {        // else if array b has leftovers
            while(j >= 0)
               c[k--] = b[j--];
        }

        return c;
    }

    /***********************/
  
    /* CI27 - Minimum of rotated array 
       -algorithm: Have two pointers, one at the start and the other at the end. Get the middle element. If it's bigger than 'start', 
                   start-middle is in the bigger part of the array so we can discard that. If it's smaller than end, middle-end is in 
                   the lower half so discard that end. Keep repeating this process until both pointers are next to each other.
       NOTE: this doesn't work 100% of the time when there are duplicates. If that's the case, we resort to a O(n) sequential search
    */
    public static int getMinimumOfRotatedArray(int[] array) {
       int start = 0;
       int end = array.length - 1;
       int middle = (start + end) / 2;
       
        while (true) {
            if (array[middle] > array[start]) {
                start = middle;
            } else {
                end = middle;
            }
            
            if (start + 1 == end)  // determines if both pointers are next to one another. If so, we found the minimum
                return array[end];
            middle = (start + end) / 2;
        }
    }

    /* recursive version of above. I see that there is a magic number 5 so this needs refactoring
       -algorithm: 
    */
    public int findBeginningOfRotatedArray(int[] arr, int start, int end) {
        int index = (start + end) / 2; // split the array into 2 halves

        if (((end - start) < 5) && (arr[start] > arr[index]) && (arr[index] < arr[end]))
            return index;
        else if(arr[start] > arr[index]) // if the rotation is in the left half
            return findBeginningOfRotatedArray(arr, start, index);
        else if(arr[index] > arr[end])  // if the rotation is in the right half
            return findBeginningOfRotatedArray(arr, index, end);

        return 0;       
    }

    /***********************/
  
    /* CI28 - get turning point 
       -algorithm: We set a pointer to the start and end of the array. We get the middle element. If the element is greater than the 
                   previous index and less than the next index, we have the turning point. Else if the element is greater than the 
                   previous but smaller than the next, discard everything to the left of middle. Else if the element is less than the 
                   prevous but greater than next, discard everything to the right of middle.
        NOTE: elements are unique so it's not like 1,2,3,4,5,4,3,2,1
    */
    public static int getTurningPointIndex(int[] array) {
       if (array.length == 0)
           throw new IllegalArgumentException("Array length is zero!");
       if (array.length == 1 || array.length == 2)
           return 0;
           
       int start = 0;
       int end = array.length - 1;
       int middle = (start + end) / 2;
       
       while (true) {
           if (array[middle - 1] < array[middle] && array[middle] > array[middle + 1])  // condition to find if we have the turning point
               return middle;
           else if (array[middle - 1] < array[middle] && array[middle] < array[middle + 1]) // we're in ascending part of array
               start = middle;
           else // we're in descending part of array
               end = middle;
           
           middle = (start + end) / 2;
        }
    }

    /***********************/

    /* Find an element in an array that is rotated
     * -algorithm: use binary search. Get the value in the first and middle and last index of the array.
     *             If the value is any of these indexes, return true. Else, compare if the value is
     *             between the values first and middle index or between the values of middle and last index.
     *             If it is, search in that section and repeat the process 
     
    public static int findElementInRotatedArray(int[] array, int value) {
        int firstIndex = 0;
        int lastIndex = array.length - 1;
        int middleIndex = array.length / 2;
        
        while (true) {
            middleIndex = (firstIndex + lastIndex) / 2;
            
            if (value == array[firstIndex])
                return firstIndex;
            else if (value == array[lastIndex])
                return lastIndex;
            else if (value == array[middleIndex])
                return middleIndex;
            
            if (array[firstIndex] < value && value < array[middleIndex]) { // value in 1st half
                lastIndex = middleIndex - 1; 
            } else if (array[middleIndex] < value && value < array[lastIndex]) {    // value in 2nd half
                firstIndex = middleIndex + 1;
            }
        }
    }
    */

    /***********************/

    /* Add two numbers that are in array form
       -algorithm: create a 3rd array and get the last indices for all 3 arrays. Have a variable for the carryover. Add the last
                   indices of the 1st 2 arrays and set the current last index for the 3rd array to be the mod 10 of that sum. 
                   Carryover will equal sum /= 10. Have checks inside the loop so that we don't try to add invalid indexes.
                   Loop ends when 3rd array last index < 0. This will ensure that any extra carry is accounted for.
    */
    public int[] addArray(int[] arr1, int arr2[]) {
        int counter1 = arr1.length - 1;
        int counter2 = arr2.length - 1;
        int counter3 = (counter1 > counter2 ? counter1 : counter2) + 1; // counter3 equivalent to longer array
                                                                        // +1 is to account for potential carry
        int[] arr3 = new int[counter3 + 1];
        int carry = 0;

        // note: 
        while(counter3 >= 0) {
            if (counter1 >= 0)
                carry += arr1[counter1--];

            if (counter2 >= 0)
                carry += arr2[counter2--];

            arr3[counter3--] = carry % 10;
            carry /= 10;
        }

        return arr3;
    }

    /***********************/

    /* Find the first repeat. This is assuming that the numbers are < the length of the array
       -algorithm: very clever algorithm that is hard to explain. Walk through the code
    */
    public int findFirstRepeat(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[array[i]] > 0)
                array[array[i]] = -1;
            else
                return array[i];
        }
        return -1;
    }

    /***********************/

    /* You have an array that has 1000 elements with each element equals to it's index + 1. A random element has been replaced with 
       a number that is greater than the size of the array. Find the index of that number
       -algorithm: XOR each element of the array with its index + 1. XOR a number with itself results in zero so the end result would
                   be the index in question XOR'd with the number greater than 1000. If you were to XOR that number with the number
                   greater than 1000, you would get index. 
                   Note: this is assuming we have access to the value greater than 1000 
    */
    public int findReplacedElement(int[] arr) {
        arr[567] = 3245;    // set a random index to be a random # over 1000
        int result = 0;

        // XOR the array with the values 1 through 1000
        for (int i = 0; i < 1000; i++)
            result = result ^ arr[i] ^ (i + 1);

        // XOR the resulting number with the random # over 1000 to get the index;
        return (result ^ 3245);
    }

    /***********************/

    /* Push all zeroes in an array to the end
       -algorithm: logic is similar to the quicksort partition algorithm. The only trick is that you have to do a second check for
                slow being less than fast in the while loop.
    */    
    public int[] pushZeroesToEnd(int[] arr) {
        int slow = 0;
        int fast = arr.length - 1;
        
        while (slow < fast) {
            while (arr[slow] != 0)
                ++slow;
            while (arr[fast] == 0)
                --fast;
            
            if (slow < fast) {
                int temp = arr[fast];
                arr[fast] = arr[slow];
                arr[slow] = temp;
            } else {
                break;
            }
        }

        return arr;
    }

    /***********************/
    
    /* Push all zeroes in an array to the beginning
       -algorithm: logic is similar to the quicksort partition algorithm. The only trick is that you have to do a second check for
                slow being less than fast in the while loop.
    */    
    public int[] pushZeroesToBeginning(int[] arr) {
        int slow = 0;
        int fast = arr.length - 1;
        
        while (slow < fast) {
            while (arr[slow] == 0)
                ++slow;
            while (arr[fast] != 0)
                --fast;
            
            if (slow < fast) {
                int temp = arr[fast];
                arr[fast] = arr[slow];
                arr[slow] = temp;
            } else {
                break;
            }
        }
        
        return arr;
    }

    /***********************/

    /* Find element in a 2D array
       -algorithm: get the size of the rows and columns. Have a while loop while current index which starts at
                   0 is less than the # of rows and the current column is >= 0. Start from upper-right
                   corner of the matrix and work way backwards until you hit a column whose value is
                   >= key. Afterwards, keep advancing down the rows (sticking to the same column) until
                   you hit a row whose value is >= key. If at any of those spots you hit an index that
                   equals value, return true else after the past 2 steps, return false.
        note: assumes square matrix whose columns and rows are sorted; also, we could have gone column by column 
              and row by row but this algorithm is more efficient
    */
    boolean search2DArray(int[][] matrix, int key) {
        int m = matrix.length;       // get row count
        int n = matrix[0].length;    // get column count
        int k = 0;
        
        while ((k < m) && (n >= 0)) {
            if (key < matrix[k][n - 1])
                n--;
            else if(key > matrix[k][n - 1])
                k++;
            else if(key == matrix[k][n - 1])
                return true;
        }
        
        return false;
    }

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

    /* Find the # of negative elements in a sorted array (from negative to positive).
       -algorithm: Keep iterating through each row while the current rows last index is negative and add to the count the length of the
                   column. The last row you hit will have at least one positive number. From here, iterate through each column in the
                   current row until you hit a positive all the while incrementing the count for each negative you encounter
    */
    public int countNegativeElemsIn2DArray(int[][] a) {
        int columnLength = a[0].length;
        int row = 0;
        int count = 0;
        
        // find the first row that should have at least 1 positive #
        while (a[row][columnLength - 1] < 0) {
            ++row;
            count += columnLength; 
        }
        
        // iterate through the first row that has at least 1 positive #
        for (int i = 0; i < columnLength - 1; i++) {
            if (a[row][i] < 0)
                ++count;
        }
        
        return count;
    }
    
    /***********************/

    /* An array of integers, only one integer appears odd times, all others appear even times, find it.
       -algorithm: if you XOR a number with itself or an even number of times, you get zero. If you XOR each value of the array,
                   the result will be the one number that appears an odd # of times
    */
    public int FindOddCountInteger(int[] array) {
        int oddNumber = 0;

        for (int i : array)
            oddNumber ^= i;

        return oddNumber; 
    }
    
    /***********************/

    /*
       -algorithm: 
    */
    public boolean areArraysEqual(int[] arr1, int[] arr2) {
        // check to see if the # of elements are the same
        if (arr1.length != arr2.length)
            return false;

        // check if the sums are equal
        if (getArraySum(arr1) != getArraySum(arr2))
            return false;

        // check to see if the products are equal
        if (getArrayProduct(arr1) != getArrayProduct(arr2))
            return false;

        // XOR both arrays and check that the result is zero
        int XORResult = 0;
        for (int i : arr1)
            XORResult ^= i;
        for (int i : arr2)
            XORResult ^= i;
        if (XORResult != 0)
            return false;

        return true;
    }
    private int getArraySum(int[] arr) {
        int sum = 0;

        for (int i = 0; i < arr.length; i++)
            sum += arr[i];

        return sum;
    }
    private int getArrayProduct(int[] arr) {
        int product = 1;

        for (int i = 0; i < arr.length; i++)
            product *= arr[i];

        return product;
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
        
        actualSum = ((array.length - 2) * (array.length - 1)) >> 1; // ((n - 2)(n - 1)) / 2
        return arraySum - actualSum;
    } 
    
    /***********************/
   
    /* CI6 - Duplicates in an Array ranging from 0 to n - 1, multiple duplicates 
       -algorithm: iterate through array and for each element, set its respective bucket is true. If
                   you find that the bucket is already true, we found the first duplicate
    */
    public static int findFirstDuplicate(int[] array) {
        boolean[] hashTable = new boolean[256];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0 || array[i] > array.length - 1)
                throw new IllegalArgumentException("Number out of bounds!");
            if (hashTable[array[i]] == false)
                hashTable[array[i]] = true;
            else {
                return array[i];
            }
        }
        
        return Integer.MIN_VALUE;
    }
    
    /***********************/ 
    
    /* CI45 - Place even numbers before odd numbers. Order of numbers doesn't matter 
       -algorithm: Using strategy similar to the partition function for quicksort, swap evens and odds 
    */
    public static void evensBeforeOdds(int[] array) {
        int start = 0;
        int end = array.length - 1;
        
        while (true) {
            while (array[start] % 2 == 0)
                ++start;
                
            while (array[end] % 2 == 1)
                --end;
                
            if (end > start) {    // check in the middle to determine when to break out of the loop
                int temp = array[end];
                array[end] = array[start];
                array[start] = temp;
            } else {
                break;
            }
        }
    }
    
    /***********************/
    
    /* CI71 - finds the intersection of two sorted arrays 
       -algorithm: iterate through first array and for each element, do a binary search in second array
       -note: O(n log n) but no extra space needed
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
  
    /* Find what elements intersect between two arrays
    -algorithm: iterate through one of the arrays and enter each element into an int hash table
                and increment for each instance. Iterate through the second array and if the bucket
                is greater than 0, print out the value and then decrement the bucket
                note: O(n) but requires extra space
     */  
     public void arraysIntersections(int[] arr1, int[] arr2) {
         // setup HashMap for the first array. Create a running count for each value
        int[] hashTable = new int[256];
         for (int i = 0; i < arr1.length; i++)
             ++hashTable[arr1[i]];
         
         // check for intersection
         System.out.println("Checking for interesecting elements...");
         for (int i = 0; i < arr2.length; i++) {
             if (hashTable[arr2[i]] > 0) {
                 System.out.print(arr2[i] + " ");
                 --hashTable[arr2[i]];
             }
         }       
     }
     
     /***********************/    
    
    /* CI72 - greatest sum of consecutive subarrays 
       -algorithm: keep on adding #'s but whenever the sum is less than zero, restart at zero. The logic
                   being that if you are in the negative, if you start all over, you'll be at zero
                   which is always greater than any negative value. Do this all while keeping track
                   of the max sum
    */
    public static int greatestSumOfSubarrays(int[] array) {       
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
       -algorithm: First the array should be sorted. Next, add the first and last indices. If it's 
                   greater than k, keep decrementing the last index since each index should be less
                   than the current last. Keep repeating until we get a value <= k. If the sum was 
                   less than k, we should keep incrementing the start index until we get a value 
                   >= k as each index will be greater than the last. Keep repeating this process
                   until we found 2 values that equal k or start >= end.
                   note: O(n) and doesn't need any extra space
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
  
    /* Find if there exists 2 integers in an array (can't be the same index) that equals a value
    -algorithm: put all ints into a hash table. Iterate through array again and subtract each number from value and check if
                the difference exists in the hash table. If so, return true
                O(n) but needs extra space unlike the solution above
     */  
     public boolean doesArraySumExists(int[] arr, int sum) {
         boolean[] hashTable = new boolean[256];
         int tmp = 0, num1 = 0;
         boolean exists = false;
    
         for (int i = 0; i < arr.length; i++) {
            hashTable[i] = true;
         }
    
         for (int i = 0; i < arr.length; i++) {
             num1 = arr[i];
             tmp = sum - num1;
             if((hashTable[tmp] == true) && (num1 != tmp)) {
                 exists = true;
                 break;
             }
         }       
         
         return exists;
     }
 
     /***********************/
    
    /* CI88 - check whether array contains three numbers whose sum equals 0 
       -algorithm: logic is similar to above but with tweaks. We pass in the array and k will be the
                   negative value of the current index. We also pass a new third parameter which is
                   the index in question so the modified isSumof2Numbers skips that index when finding
                   two values that equals the negative of the current index
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
    // exactly like isSumOf2Numbers but takes into account an index that is skipped from the search
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
 
    /* CI90 - Given a positive value s, print all sequences with continuous numbers (with two numbers at least) 
     *        whose sum is s. The numbers are less than s. 
       -algorithm: Since we need at least 2 numbers, we only have to iterate through half of the numbers 
                   (since (s / 2) + ((s / 2) + 1) is always greater than s). Have two pointers: one that points
                   to the start of the potential list and a second pointer that starts right after start.
                   Keep iterating and comparing the cumulative sums to s. While it's less than s, keep incrementing
                   the fast #. If it's greater than s, increment start and set fast to be start + 1. Else,
                   we have a range so print start and fast and increment start by 1, fast to start + 1 and
                   set the cumulative sum to be equal to start  
    */
    public static void printContinuousSequence(int s) {        
        int startNumber = 1;
        int sum = startNumber;
        int currentNumber = startNumber + 1;
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

    /* Create largest int from array of ints (THIS IS LIKELY WRONG)
       -algorithm:  
        note: maybe cleaner and more efficient to use a Comparable fxn
    */
    public String createLargestInt(int[] arr) {
        String tempStr1, tempStr2, biggestInt = "";
        
        for (int i = 0; i < arr.length - 1; i++) {
            int tempInt1 = arr[i], tempInt2 = arr[i + 1];
            tempStr1 = Integer.toString(tempInt1) + Integer.toString(tempInt2);
            tempStr2 = Integer.toString(tempInt2) + Integer.toString(tempInt1);

            if (Integer.parseInt(tempStr1) > Integer.parseInt(tempStr2)) {
                biggestInt = tempStr1;
                arr[i + 1] = Integer.parseInt(tempStr1);
            } else {
                biggestInt = tempStr2;
                arr[i + 1] = Integer.parseInt(tempStr2);
            }
        }
        
        return biggestInt;
    }

    /***********************/

    /* You have any array whose values are 0, 1 or 2. Arrange the array in order
       -algorithm: Have 3 indexes hi, mid an hi. hi and mid start at zero and hi starts at the end. The main index is mid 
                   and loop through array until the mid index is >= hi index. If 0, swap mid and lo and advance both indices.
                   If 1, advance mid. If 2, swap mid and hi and decrement hi
    */
    public int[] dutchNationalFlag(int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        int mid = 0;
        int temp;

        while(mid <= hi) {
            switch(a[mid]) {
                case 0:
                    temp = a[lo];
                    a[lo] = a[mid];
                    a[mid] = temp;
                    lo++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    temp = a[hi];
                    a[hi] = a[mid];
                    a[mid] = temp;
                    hi--;
                    break;
            }
        }

        return a;
    }

    /***********************/
    
    /* ???
       -algorithm: 
    */
    public int findEquilibriumIndex(int[] arr) {
        int sum = 0, leftSum = 0;

        for (int i = 0; i < arr.length; i++)    // get total sum of all elements
            sum += arr[i];

        for (int i = 0; i < arr.length; i++) {
            sum -= arr[i];

            if(leftSum == sum)
                return i;

            leftSum += arr[i];
        }

        return -1;
    }

    /***********************/

    /* ???
       -algorithm: 
    */
    public String[] interleave(String[] strArray) {
        int n = strArray.length / 2;
        String temp;

        for (int i = 1; i < n; i++) {       // step no.
            for (int j = 0; j < i; j++) {   // no. of swaps
                temp = strArray[n - i + 2 * j];
                strArray[n - i + 2 * j] = strArray[n - i + 2 * j + 1];
                strArray[n - i + 2 * j + 1] = temp;
            }
        }
        return strArray;
    }

    /***********************/

    /* Given an n X n array with rows sorted and cols sorted, find the number of negative elements in most efficient way
       -algorithm: 
    */
    public void findNegativeBits() {
        int v = -1;      // we want to find the sign of v
        int sign;   // the result goes here
        int result;

        // convert true and false values to 1 and 0 respectively
        boolean temp = (v < 0);
        if (temp)
            result = 1;
        else
            result = 0;

        sign = -result;  // if v < 0 then -1, else 0.

        if (sign == -1)
            System.out.println("negative");
        else
            System.out.println("positive");
    }
    
    /***********************/

    /* ???
       note: doesn't work for duplicate values in array2 since I use a HashSet
    */
    public void findConsecutiveIndex(int[] arr1, int[] arr2) {
        int array2Length = arr2.length;
        int startIndex = -1, endIndex = -1;
        int XORValue = 0;

        // put arr2 into a HashSet (because of this, duplicates in array2 wouldn't work for this algorithm) and find the XOR'd value
        java.util.HashSet<Integer> hashSet = new java.util.HashSet<Integer>();
        for (int i : arr2) {
            hashSet.add(i);
            XORValue ^= i;
        }

//note: it seems I may have hardcoded array indices. Will have to change that if that is the case
        for (int i = 0; i < arr1.length; i++) {
            if (hashSet.contains(arr1[i])) {
                if (i + array2Length < arr1.length) {
                    if (((arr1[i] ^ arr1[i + 1] ^ arr1[i + 2] ^ arr1[i + 3] ^ arr1[i + 4]) ^ XORValue) == 0) {
                        startIndex = i;
                        endIndex = i + 4;
                        break;
                    }
                }
            }
        }

        System.out.println("Start index: " + startIndex + "\nEnd index: " + endIndex);      
    }
    
    /***********************/

    /* ???
       -algorithm: 
    */  
    public void generateThirdArray(int[] input, int[] index, int[] result) {
        int product = 1;
        for (int i = 0; i < input.length; i++)
            product *= input[i];

        for (int i = 0; i < result.length; i++)
            result[i] = product / input[index[i]];  
    }

    /***********************/

    public static void main(String[] args) {
        //ArrayManipulations arrManip = new ArrayManipulations();

    }
}
