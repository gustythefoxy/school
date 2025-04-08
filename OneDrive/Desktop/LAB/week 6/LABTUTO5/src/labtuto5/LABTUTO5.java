
package labtuto5;

import java.util.Random;


public class LABTUTO5 {

    
    static void Tquestion1(){
        //a
        float[] arr1 = new float[12];
        //b
        char[] char1 = {'A','B','C','D','E'};
        //c
        String[] students = new String[100];
        //d
        int[][] arr2 = new int[6][2];
        //e
        int[][] arr3 = {{6,4},{2,5},{4,6}};
        //f
        arr3[1][1] = 4;
        arr3[2][0] = 3;
        arr3[2][1] = 7;
    }
    
    static void Tquestion2(){
        //String[] code = {'AAA', 'AAB', 'AAC', 'AAD'}; is false, should be:
        String[] code = {"AAA", "AAB", "AAC", "AAD"};
        
        
//        int[] num = new num[10];
//        for(int k=0; k<=num.length(); k++)
//         sum+=num;
        // last line should be sum+=num[k]
        
//        int [][]t = new int[3][];
//            t[1][2] = 5;
//          can't innitialize array like this

            //int i=4;
            //int []score = new int[5];
            //score [1] = 78;
            //score[++i] = 100;
            //the final line is falssse because of syntax error, use i++ instead

    
    }
    
    
    static void Tquestion3(){
    //    int[] marks = new int[5];
    //    int i = 0, j = 1;
    //    marks[i] = 12;
    //    marks[j] = marks[i] + 19;
    //    marks[j-1] = marks[j] * marks [j];
    //    marks[j*3] = marks[i+1];
    //    marks[++j] = marks[i]%5;
    //    marks[2*j] = marks[j-1];
    
    //ANS IS 
    // {961,1,0,31,31}

    }
    
    static void Tquestion4(){
        //use split(//+s)
    }
    
    static void Tquestion5(){
        String name = "Muhammad Ali Bin Zainul Abidin";
        String[] name_inverse = name.split("\\s+");
        int x = name_inverse.length;
        for (int i = 0; i <= x-1; i++) {
            //do it later
        }
    }
    
    static void Tquestion6(){
        Random rd = new Random();
        int num = rd.nextInt(256);
        num--;
        int[] binary = new int[35];
        int id = 0;
        while(num > 0){
            binary[id++] = num % 2;
            num = num / 2;
        }
        
        
    }
    
    public static void main(String[] args) {
        Tquestion6();
    }
    
}
