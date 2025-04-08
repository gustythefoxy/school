
package dump3;

import java.util.Random;



public class Dump3 {

    
    public static void main(String[] args) {
        
        
        
        int[] num = random(12, 1, 8);
        getOdd(num);
        
    }
    
    public static int[] random( int N, int A, int B){
        Random random = new Random();
        int[] num = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = random.nextInt(A, B);
        }
        return num;
    }

    public static void getOdd(int[] num){
        int count2 = 0;
        int count = 0;
        for (int i = 0; i < num.length; i++) {
            if (num[i]%2 != 0) {
                count++;
            }
        }
        for (int i = 0; i < num.length; i++) {
            if (num[i]%2 == 0) {
                count++;
            }
        }
        int[] odd = new int[count];
        int z = 0;
                for (int i = 0; i < num.length; i++){
            if (num[i]%2 != 0) {
                odd[z] = num[i];
                z++;
            }
                }
            int[] even = new int[count];
            int w = 0;
                for (int i = 0; i < num.length; i++){
            if (num[i]%2 == 0) {
                odd[w] = num[i];
                w++;
            }
            
        }
                System.out.println("odd:");
          for (int i = 0; i < z; i++) {
              System.out.print(odd[i] + " ");
        }
          System.out.println("");
          System.out.println("even:");
          for (int i = 0; i < w; i++) {
              System.out.print(even[i] + " ");
        }
    }
        
    
    
    
    
}
