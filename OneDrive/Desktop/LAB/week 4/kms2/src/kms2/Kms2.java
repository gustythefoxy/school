package kms2;
import java.util.Scanner;
public class Kms2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str, strm;
        int x, z;
        int y = 0;
        System.out.print("name : ");
        str = scanner.nextLine();
        strm = str.toLowerCase().trim();
        x = strm.length();
        
        
        
        
        //setting up stuff
        int[] flag = new int[x];
        for (int i = 0; i < x; i++) {
            flag[i] = 0;
        }
        //counter to check distinct char
        for (int i = 0; i < x; i++) {
            if (flag[i] == 0) {
                for (int j = i; j < x; j++) {
                    if (strm.charAt(i) == strm.charAt(j)) {
                        flag[j] = 1;
                    }
                }
                y++;
            }
        }
        //unnecessary step
        //if statement
        if(y % 2 == 0) {
            System.out.println("ALLY DETECTED");
        }
        else{
            System.out.println("ENEMY DETECTED");
        }      
    }  
}
