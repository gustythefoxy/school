package javaapplication1;

import java.util.Scanner;






public class JavaApplication1{
    static boolean isPrime(int n)
    {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to n/2
        for (int i = 2; i <= n / 2; i++)
            if (n % i == 0)
                return false;

        return true;
    }
 public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    // if num multi by 3 pint lulu
    // if num multi by 5 print lala
    //if both print lululala lmao... not funny I  know...
    
    int num,num2;
    System.out.print("pls enter num1: ");
    
    
    num = scanner.nextInt();
     System.out.print("pls enter num2: ");
    num2 = scanner.nextInt();
    
    
    int y = 0;
    //ALL THE PROCESS DOWN BELLOW 
    
    if(num <= 0 || num >= 101){
        System.out.println("why......");
    }
    else if (num2 <= 0 || num2 >= 101){
        System.out.println(".... don't");
    }
    else if (num2 <= num){
        System.out.println("............ how?");
    }
    
    else{
        for (int i = 0; i < num; i++) {
            if(int i==0){
                
            }
            
        }
{
            
                
            
            System.out.println(num);
            
        }
        
     }
    }   
 }