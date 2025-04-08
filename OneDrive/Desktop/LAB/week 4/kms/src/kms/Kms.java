/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package kms;
import java.util.Scanner;
import java.util.Arrays;
public class Kms {

    public static void main(String[] args) {
        // take num
        Scanner scanner = new Scanner(System.in);
        int x, y, z;
        y =0;
        z = 0;
        String xs;
        System.out.print("pls input num: ");
        x = scanner.nextInt();
        //change to string
        xs = Integer.toString(x);
        int[] num = new int[xs.length()];
        int[] test = new int[xs.length()];
        //put num into array
        for (int i = 0; i < xs.length(); i++) {
            num[i] = xs.charAt(i) - '0';
        }
        //set all to false
        for (int i = 0; i < xs.length(); i++) {
            test[i] = 0;
        }
        //check the num grouping
        for (int i = 0; i < xs.length(); i++) {
            if (test[i] == 0) {
                for (int j = i ; j < xs.length(); j++) {
                    if (num[i] == num[j]) {
                        test[j] = 1;
                    }
                }
                y++;
            }
        }
        
        //extra work
        int c = 0;
        int c1 = 0;
        int capture = 0;
        for (int i = 0; i < xs.length()-1; i++) {
            
            if (num[i] == num[i+1]) {
                c1++;
            }
            else if (num[i] != num[i+1]) {
                if (c1 > c) {
                    c = c1;
                    capture = num[i];
                }
                c1 = 0;
            }
        }
        c++;
        
        //reset condition
        int[] gp = new int[y];
        for (int i = 0; i < xs.length(); i++) {
            test[i] = 0;
        }
        //find largest occuring times
        for (int i = 0; i < xs.length(); i++) {
            if (test[i] == 0) {
                for (int j = i; j < xs.length(); j++) {
                    if (num[i] == num[j]) {
                        gp[z]++;
                        test[j] = 1;
                    }
                }
                
                z++;
            }
        }
        int max = gp[0];
        for (int i = 1; i < gp.length; i++) {
            if (gp[i] > max) {
                max = gp[i];
            }
        }
        //finding out who is it have the highest occ
        int max_count = 0;
        int max_freq = 0;
        
        for (int i = 0; i < num.length; i++) {
            int count = 0;
            for (int j = 0; j < num.length; j++) {
                if (num[i] == num[j]) {
                    count++;
                }
            }
            
            if(count > max_count){
                max_count = count;
                max_freq = num[i];
            }
        }
        //cacl sum of groups size
        int sum = 0;
        for (int i = 0; i < gp.length; i++) {
            sum = sum + gp[i];
        }
        
        //finding out same repeating element
        int group = 1;
        for (int i = 0; i < num.length; i++) {
            if (num.length - i > 1) {
                if (num[i] != num[i+1]) {
                    group++;
                }
            }
        }
        //polishing group (why Didn't I do this earlier...)
        Arrays.sort(num);
        //printout resut
        System.out.println("there are " + group + " groups");
        System.out.println("the largest group is " + capture + " which have " + (c) + " total numbers");
        System.out.println("total sum of groups digit are " + sum);      
        
        
        
        System.out.print("sort: ");
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i] + "");
        }
        System.out.println("");
        //extra 
        System.out.println("there are " + y + " groups after sorting it");
        System.out.println("the largest group is " + max_freq + " which have " + max_count + " total numbers");

        
    }
}
    