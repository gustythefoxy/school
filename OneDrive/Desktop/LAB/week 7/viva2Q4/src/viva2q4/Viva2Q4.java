
package viva2q4;

import java.util.Scanner;
public class Viva2Q4 {
    
    
    
    public static void main(String[] args) {
        int num = 0;
        int width = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("PLEASE INPUT NUMBER: ");
        num = scanner.nextInt();
        System.out.print("PLEASE INPUT WIDTH: ");
        width = scanner.nextInt();
        
        String ans = format(num, width);
        System.out.println(ans);
        
        
    }

    private static String format(int num, int width) {
        //CHECK HOW MANY DIGIT THE NUMBER IS
        int numLength = String.valueOf(num).length();
        //CHANGE THE NUMBER TO STRING SO WE CAN ADD PADDING LATER
        String numS = Integer.toString(num);
        //CALC HOW MUCH PAD IS NEEDED BASED ON WIDTH - DIGIT NUMBER
        int formater = width - numLength;
        String ans = "";
        
        //DO THE PADDING PROCESS, IF WIDTH IS MORE THAN DIGIT THEN WE ADD THEM
        //IF LESS, WE JUST IGNORE THE PADDING
        if (formater > 0) {
            for (int i = 0; i < formater; i++) {
                ans += "0";
            }
            ans +=numS;
        }
        else{
            ans += numS;
        }
        return ans;
    }
}
