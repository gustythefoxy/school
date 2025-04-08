/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package w4lab;

/**
 *
 * @author hp
 */
public class W4LAB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String month[] = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL","AUG","SEP","OCT","NOV","DIS"};
        String day[] = {"sun","mon","teu","wed","thu","fri","sat"};
        int x, y, z;
        x = 0;
        y = 0;
        z = 0;
        
        //month
        for (int i = 1; i < 13; i++) {
            System.out.print("            " + month[i-1] + "            ");
            System.out.print("  ");
            // under month
            if (i%3 == 0) {
                System.out.println("");
                for (int k = 0; k < 3; k++) {
                    for (int j = 0; j < 7; j++) {
                    System.out.print(day[j] + " ");
                    }
                     System.out.print(" ");
                }
                //under day
                System.out.println("");
                for (int j = 0; j < 10; j++) {
                    
                }

                System.out.println("");
//                System.out.println("sun "+"mon "+"teu "+"wed "+"thu "+"fri "+"sat ");
                

            }
            
        }
    }
    
}
