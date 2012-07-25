package misc;

public class CountingSort {
	 
    // We set the array to 95, as this is the number of ASCII graphic characters
    // that range from 0x20 to 0x7E (32 to 126 decimal).
    private static final int ARRAY_SIZE = 95;
 
    // We use the minimum range of the ASCII graphic characters, in order to 
    // index from 0 the counts for each character.
    private static final int MIN_RANGE = 32;
 
    /**
     * Returns a new String sorted based on the ASCII table ordering.
     */
    public static String sort(String input) {
 
        char[] counts = new char[ARRAY_SIZE];		// counts of each individual ASCII char from 'SPACE' (32) through '~' (126)
        char[] result = new char[input.length()];	// where ordered String is put
 
        // Initialize the counts
        for(int i = 0; i < input.length(); i++)
            counts[input.charAt(i) - MIN_RANGE]++;
 
        // Generate the result array
        for (int i = 0, resultIndex = 0; i < counts.length; i++) {
            if(counts[i] > 0) {
                for (int j = 0; j < counts[i]; j++) {
                    result[resultIndex] = (char)(i + MIN_RANGE);
                    resultIndex++;
                }
            }
        }
 
        // Return the result array as a String
        return new String(result);
    }
 
    /**
     * Test Method
     */
    public static void main( String[ ] args ) {
 
        System.out.println( CountingSort.sort( 
            "*mS1H9EGNWJ!2h!zyFfkU4J;c2b\"FNx    qjYv4DDi!cTfvEZq<DPF^)5\"b)a`!+.4") );
    }
}