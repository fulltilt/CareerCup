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
}
