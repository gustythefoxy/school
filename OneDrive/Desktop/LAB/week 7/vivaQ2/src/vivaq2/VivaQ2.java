
package vivaq2;

import java.util.Arrays;
import java.util.Scanner;




public class VivaQ2 {
    


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String num1 = "";
        String num2 = "";
        
        System.out.println("GUIDE TO INPUT A NUM");
        System.out.println("INPUT YOUR NUMBER AND USE COMMA WITH SPACE FOR NEXT NUMBER");
        System.out.println("EG: 2, 3, 4");
        System.out.print("Array 1:");
        num1 = scan.nextLine();
        System.out.print("Array 2:");
        num2 = scan.nextLine();
        int[] ans =  unlimitedVoid(num1, num2);
        System.out.print("[");
        for (int i = 0; i < ans.length ; i++) {
            
            if (i <  ans.length - 1) {
                System.out.print(ans[i] + ", ");
            }
            
            if (i+1 == ans.length) {
                System.out.print(ans[i]);
            }       
        }
        System.out.print("]");
    }

    public static int[] unlimitedVoid(String num1, String num2){
        //COMBINE NUM1 AND NUM2
        String king = num1 + ", " + num2;
        //CHANGING FORMS..... BECOME STRING ARRAY!
        String numbers[] = king.split("[ ,]+");
        int[] gojo = new int[numbers.length]; 
        int x = 0;
        //FROM THE STRING, TO THE RING, TO THE PEN, TO THE INT
        for (int i = 0; i < numbers.length; i++) {
            
            int num = Integer.parseInt(numbers[i]);
            gojo[i] = num;
        }
        //HENCE, I ALONE AM THE SORTED DECENDING ORDER ONE
        Arrays.sort(gojo);
        //AN OPPONENT CAME? CHECK ALL GOJO's ELEMENT EQUAL TO TRUE WITH BOOLEAN
        boolean[] sukuna = new boolean[gojo.length];
        for (int i = 0; i < sukuna.length; i++) {
            sukuna[i] = true;
        }
        //GOJO USED HIS CURSED TECHNIQUE TO MANIPULATE ALL THE SAME ELEMENT AND MARK THEM AS FALSE IF THEY'RE DUPLICATE
        for (int i = 0; i < gojo.length; i++) {
            //LAST ELEMENT IS AN EXCEPTION
            if (i <= gojo.length-1) {
                for (int j = i+1; j < gojo.length; j++) {
                    if (gojo[i] == gojo[j]) {
                        sukuna[i] = false;
                    }
                }
            }
        }
        //SUKUNA CUT GOJO IN HALF, NOW VAR X IS USED TO DETERMINE WHO IS THE NEXT CURSED USER SIZES
        for (int i = 0; i < sukuna.length; i++) {
            if (sukuna[i] == true) {
                x++;
            }
        }
        //YUJI, THE NEW HOLDER OF CURSED FINGERS. AN ARRAY WITH SIZE OF X REPLACE GOJO OLD ELEMENT WITH A NEW ONE
        //YUJI IMPROVISE IT BY ONLY COPYING THE TRUE ELEMENT!
        int[] yuji = new int[x];
        x = 0;
        for (int i = 0; i < gojo.length; i++) {
            if (sukuna[i] == true) {
                yuji[x] = gojo[i];
                x++;
            }
        }
        return yuji;
    }
    
}
