import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Anagram {

    public static String preProcess(String str) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
            if (Character.isLetter(c)) {
                result.append(Character.toLowerCase(c));
            }
        }
        
        return result.toString();
    } 

  
    public static boolean isAnagram(String str1, String str2) {
        String p1 = preProcess(str1);
        String p2 = preProcess(str2);
        
        if (p1.length() != p2.length()) {
            return false;
        } 
        
        int[] charCounts = new int[26];
        int len = p1.length();

        // ספירת תווים עבור p1 (+1)
        for (int i = 0; i < len; i++) {
            charCounts[p1.charAt(i) - 'a']++;
        }

        // ספירת תווים עבור p2 (-1)
        for (int i = 0; i < len; i++) {
            charCounts[p2.charAt(i) - 'a']--;
        }

        for (int count : charCounts) {
            if (count != 0) {
                return false;
            }
        }
        
        return true; 
    }


    public static String randomAnagram(String str) {
        List<Character> charList = new ArrayList<>();
        
       
        for (char c : str.toCharArray()) { 
            charList.add(c);
        }

        Collections.shuffle(charList, new Random());

        StringBuilder randomResult = new StringBuilder(charList.size());
        for (char c : charList) {
            randomResult.append(c);
        }
        
        return randomResult.toString();
    }

    
    public static void main(String args[]) {
        // Tests the isAnagram function.
        System.out.println(isAnagram("silent","listen")); // true
        System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
        System.out.println(isAnagram("Madam Curie","Radium came")); // true
        System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

       
        System.out.println(preProcess("What? No way!!!")); 
            
        // Tests the randomAnagram function.
        System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
            
        // Performs a stress test of randomAnagram 
        String str = "1234567";
        Boolean pass = true;
        // 10 can be changed to much larger values, like 1000
        for (int i = 0; i < 10; i++) {
            String randomAnagram = randomAnagram(str);
            System.out.println(randomAnagram);
            pass = pass && isAnagram(str, randomAnagram);
            if (!pass) break;
        }
        System.out.println(pass ? "test passed" : "test Failed");
    }
}