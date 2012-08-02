package misc;

public class MyStringTokenizer {

	public String[] tokenize(String str, String token) {
		int index = 0;
		int numberOfWords = 0;

		// find the # of words in a String
		for (int i = 0; i < str.length(); i++) {
			index = str.indexOf(token, i);
			if (index == -1) {
				++numberOfWords;
				break;
			}

			i = index;
			++numberOfWords;
		}

		// put words into Array
		String[] tokenizedStrings = new String[numberOfWords];
		int currentWord = 0;
		for (int i = 0; i < str.length(); i++) {
			index = str.indexOf(token, i);

			if (index == -1) {
				tokenizedStrings[currentWord] = str.substring(i, str.length());	// don't know why it isn't str.length() - 1
				break;
			}

			tokenizedStrings[currentWord++] = str.substring(i, index);

			i = index;
		}

		return tokenizedStrings;
	}

	public static void main(String[] args) {
		MyStringTokenizer myTokenizer = new MyStringTokenizer();
		String str = "The human torch was denied a bank loan";
		String[] strings = myTokenizer.tokenize(str, " ");

		for (String str2 : strings)
			System.out.println(str2);
	}
}