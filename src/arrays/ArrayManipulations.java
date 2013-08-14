package arrays;

import java.util.HashMap;

public class ArrayManipulations {
    
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
            
            if (start + 1 == end)
                return array[end];
            middle = (start + end) / 2;
        }
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

    /*
       -algorithm: 
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

    /* Find replaced element where replaced element is greater than the size of the array
       -algorithm: 
       note: replaced element is replaced with an integer greater than the size of the array
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

    /*
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

    /*
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

    /* Find element in a 2D array
       -algorithm: 
        note: assumes square matrix
    */
    boolean search2DArray(int[][] a, int key) {
        int m = a.length;       // get row count
        int n = a[0].length;    // get column count
        int k = 0;

        while ((k < m) && (n >= 0)) {
            if (key < a[k][n - 1])
                n--;
            else if(key > a[k][n - 1])
                k++;
            else if(key == a[k][n - 1])
                return true;
        }
        return false;
    }

    /*
       -algorithm: 
    */
    public int countNegativeElemsIn2DArray(int[][] a) {
        int columnLength = a[0].length;
        int row = 0;
        int count = 0;
        
        // find the first row that should have at least 1 positive # (assumption is that array is sorted)
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
    
    // An array of integers, only one integer appears odd times, all others appear even times, find it
    public int FindOddCountInteger(int[] array) {
        int oddNumber = 0;

        for (int i : array)
            oddNumber ^= i;

        return oddNumber; 
    }
    
    /*
       -algorithm: 
    */  
    public void generateThirdArray(int[] input, int[] index, int[] result) {
        int product = 1;
        for (int i = 0; i < input.length; i++)
            product *= input[i];

        for (int i = 0; i < result.length; i++)
            result[i] = product / input[index[i]];  
    }

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
    
    /*
       -algorithm: 
    */  
    public void arraysIntersections(int[] arr1, int[] arr2) {
        // setup HashMap for the first array. Create a running count for each value
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr1.length; i++) {
            if (!hashMap.containsKey(arr1[i]))
                hashMap.put(arr1[i], 1);
            else {
                int total = hashMap.get(arr1[i]);
                hashMap.put(arr1[i], ++total);
            }
        }
        
        // check for intersection
        System.out.println("Checking for interesecting elements...");
        for (int i = 0; i < arr2.length; i++) {
            if (hashMap.containsKey(arr2[i])) {
                int total = hashMap.get(arr2[i]) - 1; // subtract 1 from the value of the key
                System.out.print(arr2[i] + " ");

                if (total == 0)
                    hashMap.remove(arr2[i]);
                else
                    hashMap.put(arr2[i], total);
            }
        }       
        System.out.println();
    }
    
    /*
       -algorithm: 
    */  
    public boolean doesArraySumExists(int[] arr, int sum) {
        HashMap<Integer, Integer> ht = new HashMap<Integer, Integer>();
        int tmp = 0, num1 = 0;
        boolean exists = false;

        for (int i = 0; i < arr.length; i++) {
            ht.put(arr[i], arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            num1 = arr[i];
            tmp = sum - num1;
            if((ht.get(tmp) != null) && (num1 != tmp)) {
                exists = true;
                break;
            }
        }       
        
        return exists;
    }

    /*
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
    
    /* 
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
    
    /* Create largest int from array of ints
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
    
    public static void main(String[] args) {
        //ArrayManipulations arrManip = new ArrayManipulations();

    }
}
