package bitManipulations;

public class BitManipulations {
	public int countOneBits(int n) {
		int count = 0;
		while (n != 0) {
			if ((n & 1) == 1)
				++count;

			n >>= 1;
		}

		return count;
	}

	public int countZeroBits(int n) {
		int count = 0;
		while (n != 0) {
			if ((n & 1) != 1)
				++count;

			n >>= 1;
		}

		return count;
	}

	public int findMSB(int n) {
		int position = 0;

		while ((n >>= 1) != 0)
			++position;

		return position;
	}

	public boolean isNthBitSet(int num, int n) {
		return ((num & (1 << n)) == 1) ? true : false;
	}

	public boolean isPowerOfTwo(int n) {
		return ((n != 0) && ((n & (n - 1)) == 0));
	}

	public String getBinaryRepresentation(int n) { return getBinaryRepresentation(n, ""); }
	private String getBinaryRepresentation(int n, String str) {
		if (n > 0) {
			str += getBinaryRepresentation(n >> 1);
			str = str + (n % 2);
			// created a String for testing purposes. Else I could have just outputted (n % 2)
		}

		return str;
	}
/********************/
	public int swapWithoutTemp(int x, int y) {
		x ^= y;
		y ^= x;
		x ^= y;

		return x;
	}

	public boolean isPalindrome(int x) {
	    int y = 0;
	    int z = x;

	    while (z > 0) {
			y = (y << 1) | (z & 1);
			z = z >> 1;
	    }
	    return (y == x) ? true : false;
	}

    public int reverseBits(int n) {
        int b = 0;		// where we're going to store the reversed bits
        while (n != 0) {
			b <<= 1;		// shift b left to the next position to be set
            b |= (n & 1);	// do an '&' on the LSB of the # you want to reverse and then '|' it with b's current position
            n >>= 1;		// shift x right to the next position to '|' with b
        }
        return b;
    }

	public int swapOddEvenBits(int x) {
		return ( ((x & 0xAAAAAAAA) >> 1) | ((x & 0x55555555) << 1) );
	}

	public int bitSwapsRequired(int a, int b) {
		int count = 0;
		for(int c = a ^ b; c != 0; c = c & c - 1)
			++count;

		return count;
	}

	public String findNextBiggestIntWithSameNoOfBits(String n) {
		int x = Integer.parseInt(n, 2);
		int a = x & -x;			// locate lowest set bit
		System.out.println(a);
		int b = x + a;
		int c = b ^ x;
		int d = c / (a << 2);
		int y = d + b;

		return Integer.toString(y, 2);
	}
/********************/
}
