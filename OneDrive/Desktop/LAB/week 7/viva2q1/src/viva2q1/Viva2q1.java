
package viva2q1;

import java.util.Scanner;




public class Viva2q1 {
    
    
    public static String findLongestPalindromicSubstring(String text){
        String rev = "";  
        String answer = "";
        boolean isPalindrome = false;
        int x = 0;
        //lower case the text
        text = text.toLowerCase();
        //reverse the string and put it into "rev"
        for (int i = text.length() - 1; i >= 0; i--) {
            rev += text.charAt(i);
        }
        isPalindrome = rev.equals(text);
        StringBuilder texts = new StringBuilder(text);
        StringBuilder revs = new StringBuilder(rev);
        //IF NOT PALINDROME ON FIRST TRY, DELETE THE FIRST WORD FOR TEXT AND THE LAST WORD FOR REVERSED TEXT THEN REPEAT
        while(isPalindrome == false) {
            // WORD MUST EXCEED 3 LETTER TO BE A PALINDROME
            if (texts.length() >2) {
                texts.deleteCharAt(0);
                revs.deleteCharAt(rev.length()-1);
                text = texts.toString();
                rev = revs.toString();
                isPalindrome = rev.equals(text);
            }
            //NOT THE MOST BREAKTHROUGH WAY TO EXIT A LOOP BUT WHAT CHOICE DO i HAVE
            else{
                isPalindrome = true; x++;
            }
        }
        //X = 0 (PALINDROME)
        if (x == 0) {
            answer = text;
        }
        //X != 0 (NOT)
        else{
            answer = "not one";
        } 
        
        return answer;
    }
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.print("Please input the word:");
        String words = scanner.next();
        String answer = findLongestPalindromicSubstring(words);
        System.out.println(answer);
    }
}
