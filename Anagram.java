import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		str1 = preProcess(str1);
		str2 = preProcess(str2);
		char[] charsS1 = new char[str1.length()];
		char[] charsS2 = new char[str2.length()];

		if(str1.length() != str2.length())
			return false;
		else
		{
			int num = str1.length();
			for(int i = 0; i < num ; i++)
			{
				charsS1[i] = str1.charAt(i);
				charsS2[i] = str2.charAt(i);
			}
			return Arrays.equals(charsS1,charsS2);
		}

	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		// str = str.replaceAll("^a-z]);
		str = str.toLowerCase();
		return str;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		str = preProcess(str);
		char[] charsStr = new char[str.length()];
		for(int i = 0; i < str.length() ; i++)
				charsStr[i] = str.charAt(i);
		Random rnd = new Random();
		for(int i = str.length() - 1 ; i > 0 ; i--)
		{
			int index = rnd.nextInt(i + 1);
			char current = charsStr[i];
			charsStr[index] = charsStr[i];
			charsStr[index] = current;
		}
		return new String(charsStr);
	}
}
