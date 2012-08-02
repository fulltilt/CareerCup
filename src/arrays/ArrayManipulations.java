package arrays;

import java.util.HashMap;

public class ArrayManipulations {
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

	/* assumes that array 'a' is equal to or longer than array 'b'
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
		if (i != -1) {	// if array a has leftovers
			while(i >= 0)
		 	   c[k--] = a[i--];
		} else {		// else if array b has leftovers
			while(j >= 0)
		 	   c[k--] = b[j--];
		}

		return c;
	}

	public int[] addArray(int[] arr1, int arr2[]) {
        int counter1 = arr1.length - 1;
        int counter2 = arr2.length - 1;
        int counter3 = (counter1 > counter2 ? counter1 : counter2) + 1; // counter3 equivalent to longer array
																		// +1 is to account for potential carry
        int[] arr3 = new int[counter3 + 1];
        int carry = 0;

        while(counter3 > -1) {
			if (counter1 >= 0)
				carry += arr1[counter1--];

          	if (counter2 >= 0)
          		carry += arr2[counter2--];

          	arr3[counter3--] = carry % 10;
          	carry /= 10;
        }

       	return arr3;
	}

	public int findFirstRepeat(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[array[i]] > 0)
				array[array[i]] = -1;
			else
				return array[i];
		}
		return -1;
	}

	/* replaced element is replaced with an integer greater than the size of the array
	*/
	public int findReplacedElement(int[] arr) {
		arr[567] = 3245;	// set a random index to be a random # over 1000
		int result = 0;

		// XOR the array with the values 1 through 1000
		for (int i = 0; i < 1000; i++)
			result = result ^ arr[i] ^ (i + 1);

		// XOR the resulting number with the random # over 1000 to get the index;
		return (result ^ 3245);
	}

	public int[] pushZeroesToEnd(int[] arr) {
		int position = 0;

		for (int i = 0; i <= arr.length - 1; i++) {
			if (arr[i] != 0) {
				arr[position] = arr[i];
				position++;
			}
		}

		for(int i = arr.length - 1; i >= position; i--)
			arr[i] = 0;

		return arr;
	}

	public int[] pushZeroesToBeginning(int[] arr) {
		int i = arr.length - 1, zero = arr.length - 1;

		while (i >= 0) {
			if(arr[i] == 0)
		       i--;
		    else {
				int t = arr[i];
		        arr[i] = 0;
		        arr[zero] = t;
				zero--;
				i--;
			}
	    }

		return arr;
	}

	public int findEquilibriumIndex(int[] arr) {
		int sum = 0, leftSum = 0;

	    for (int i = 0; i < arr.length; i++)	// get total sum of all elements
			sum += arr[i];

		for (int i = 0; i < arr.length; i++) {
			sum -= arr[i];

			if(leftSum == sum)
				return i;

		    leftSum += arr[i];
		}

        return -1;
	}

	public String[] interleave(String[] strArray) {
		int n = strArray.length / 2;
		String temp;

		for (int i = 1; i < n; i++) { 		// step no.
		    for (int j = 0; j < i; j++) {   // no. of swaps
				temp = strArray[n - i + 2 * j];
				strArray[n - i + 2 * j] = strArray[n - i + 2 * j + 1];
				strArray[n - i + 2 * j + 1] = temp;
		    }
  		}
  		return strArray;
	}

	/* assumes square matrix
	*/
	boolean search2DArray(int[][] a, int key) {
        int m = a.length;		// get row count
        int n = a[0].length;	// get column count
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
	
	// Given an n X n array with rows sorted and cols sorted, find the number of negative elements in most efficient way
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
	
	public void generateThirdArray(int[] input, int[] index, int[] result) {
		int product = 1;
		for (int i = 0; i < input.length; i++)
			product *= input[i];

		for (int i = 0; i < result.length; i++)
			result[i] = product / input[index[i]];	
	}

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

	public int findBeginningOfRotatedArray(int[] arr, int start, int end) {
		int index = (start + end) / 2; // split the array into 2 halves

		if (((end - start) < 5) && (arr[start] > arr[index]) && (arr[index] < arr[end]))
			return index;
		else if(arr[start] > arr[index]) // if the rotation is in the left half
			return findBeginningOfRotatedArray(arr, start, index);
		else if(arr[index] > arr[end])	// if the rotation is in the right half
			return findBeginningOfRotatedArray(arr, index, end);

		return 0;		
	}
	
	//doesn't work for duplicate values in array2 since I use a HashSet
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
	
	// maybe cleaner and more efficient to use a Comparable fxn
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
