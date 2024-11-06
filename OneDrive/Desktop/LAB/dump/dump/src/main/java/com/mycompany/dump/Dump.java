/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.dump;
import java.util.Scanner;
/**
 *
 * @author hp
 */
public class Dump {
    
    
    
    static void questionOne(){
        System.out.println("I like cake");
        System.out.println("SEKOLAH");
        System.out.println("Nnanhira best singer");
    
    }
   
    
    
    public static void main(String[] args) {
        
        
        int x = 0;
        
        int marks;
        
        switch(x){
            case 1:
                System.out.println("tessst");
                break;
            case 2:
                System.out.println("hello");
                break;
            default:
                System.out.println("heyyyo");
                break;
                
        }
        
        int time_s, clock_h;
        double clock_m, clock_s;
        time_s = 123456;
        double copy = time_s;
        int copy_m = 0;
        
        clock_h = time_s/3600;
        clock_m = (copy/3600 - clock_h) * 60;
        copy_m = (int) clock_m;
        clock_s = (clock_m%copy_m * 60);
        
        
        System.out.println("seconds " + (int)clock_s);
        System.out.println("minutes " +copy_m);
        System.out.println("hours " + clock_h);
    }
}
