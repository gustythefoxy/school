/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dumpsmh;
/**
 *
 * @author hp
 */
import java.util.Random;
public class DumpSmh {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Random r = new Random();
        int num1, num2;
        num1 = r.nextInt(7);
        num2 = r.nextInt(7);
        
        System.out.println("player 1 = " + num1);
        System.out.println("Player 2 = " + num2);
        if (num1 > num2) {
            System.out.println("Player 1 win");
        }
        else if (num1 == num2){
            System.out.println("draw");
        }
        else{
            System.out.println("Player 2 win");
        }

        
    }
    
}
