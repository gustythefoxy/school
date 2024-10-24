/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package l1q3;

/**
 *
 * @author hp
 */
public class L1Q3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for (int i = 0; i < 17; i++) {
            if (i%2 != 0) {
                System.out.println("");
            }
            else{
                //first square
                if (i == 0 || i == 16) {
                    System.out.print("*********");
                }
                else{
                    System.out.print("*       *");
                }
                // second circle
                System.out.print("  "); 
                
                if (i == 0 || i == 16) {
                    System.out.print("   ***   ");
                }
                else if (i == 2 || i == 14) {
                    System.out.print(" *     * "); 
                }
                else{
                    System.out.print("*       *");
                }
                
                //third arrow
                System.out.print("  ");
                if(i == 0 ){
                    System.out.print("    *    ");
                }
                else if (i == 2) {
                    System.out.print("   ***   ");
                }
                else if (i == 4){
                    System.out.print("  *****  ");
                }
                else{
                    System.out.print("    *    ");
                }
                
                //fourth shape
                System.out.print("  ");
                if(i == 0 || i == 16){
                    System.out.print("    *    ");
                }
                else if (i == 2 || i == 14){
                    System.out.print("   * *    ");
                }
                else if (i == 4 || i == 12){
                    System.out.print("  *   *  ");
                }
                else if ( i == 6 || i == 10){
                    System.out.print(" *     * ");
                }
                else{
                    System.out.print("*       *");
                }
            }
        }
        
    }
    
}
