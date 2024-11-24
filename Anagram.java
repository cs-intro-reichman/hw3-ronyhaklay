import java.util.Arrays;
import java.util.Random;

public class Anagram {
    public static void main(String args[]) {
        System.out.println(isAnagram("silent", "listen"));
        System.out.println(isAnagram("William Shakespeare", "I am a weakish speller"));
        System.out.println(isAnagram("Madam Curie", "Radium came"));
        System.out.println(isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort"));

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
        str1 = preProcess(str1);
        str2 = preProcess(str2);

        if (str1.length() != str2.length())
            return false;

        char[] charsS1 = str1.toCharArray();
        char[] charsS2 = str2.toCharArray();

        Arrays.sort(charsS1);
        Arrays.sort(charsS2);

        return Arrays.equals(charsS1, charsS2);
    }

    public static String preProcess(String str) {
        String lowerCase = str.toLowerCase();
		String newString = lowerCase.replaceAll("[!?]", "");
		return newString;
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
