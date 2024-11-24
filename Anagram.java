import java.util.Arrays;
import java.util.Random;

public class Anagram {
    public static void main(String args[]) {
        System.out.println(isAnagram("silent", "listen"));  // true
        System.out.println(isAnagram("William Shakespeare", "I am a weakish speller")); // true
        System.out.println(isAnagram("Madam Curie", "Radium came")); // true
        System.out.println(isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort")); // true

        System.out.println(preProcess("What? No way!!!"));

        System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");

        String str = "1234567";
        Boolean pass = true;
        for (int i = 0; i < 10; i++) {
            String randomAnagram = randomAnagram(str);
            System.out.println(randomAnagram);
            pass = pass && isAnagram(str, randomAnagram);
            if (!pass) break;
        }
        System.out.println(pass ? "test passed" : "test failed");
    }

    public static boolean isAnagram(String str1, String str2) {
        int counter = 0;
		str1 = preProcessExtra(str1);
		str2 = preProcessExtra(str2);
          for(int i = 0; i < str1.length(); i++){
            for(int j = 0; j < str1.length(); j++){
				if(str1.charAt(i) == str2.charAt(j)){
					counter++;
					break;
				}
			}
		  }
		if(counter != str1.length())
			return false;
		else
			return true;
    }  

    public static String preProcess(String str) {
        return str.toLowerCase().replaceAll("[^a-z ]", ""); // שמירת רווחים
    }

    public static String preProcessExtra(String str) {
        String totalString = ""; 
        str = str.toLowerCase(); 
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i); 
            if (current >= 'a' && current <= 'z') { 
                totalString += current; 
            }
        }
        return totalString; 
    }

    public static String randomAnagram(String str) {
        str = preProcess(str);
        char[] charsStr = str.toCharArray();
        Random rnd = new Random();

        for (int i = charsStr.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            char temp = charsStr[i];
            charsStr[i] = charsStr[index];
            charsStr[index] = temp;
        }
        return new String(charsStr);
    }
}