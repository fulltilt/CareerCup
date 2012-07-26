package arrays;

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
	boolean search2DArray(int a[][], int key) {
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

	public static void main(String[] args) {
		ArrayManipulations arrManip = new ArrayManipulations();
		int[] a1 = new int[]{0, 0, 1, 2, 0, 4, 0, 0 ,8 ,9};
		int[] result = arrManip.pushZeroesToBeginning(a1);

		//int[] a2 = new int[]{5,6,7,8};


		//for (int i = 0; i < result.length; i++)
			//System.out.print(result[i] + " ");

		for (int i : result)
			System.out.print(i + " ");
	}
}
