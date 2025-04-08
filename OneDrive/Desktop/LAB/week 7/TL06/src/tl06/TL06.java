
package tl06;


public class TL06 {

    
    public static void main(String[] args) {
        System.out.println(max(7,3,1));
        isString("suck21 dick ass 44");
        
    }
    
    public static void isString(String x){
        String xx = x.toLowerCase().trim();
        String xxx = xx.replaceAll("\\d", "");
        String xx1 = xx.replaceAll("[^\\d.]", "");
        System.out.println("character : " + xxx.length());
        System.out.println("number : " + xx1.length());
    }
    
    
    
    public static int max(int x, int y, int z){
        if (x > y) {
            if (x > z){
                return x;
            }
            else{
                return z;
            }
            
        }
        else{
            if(y > z){
            return y;
            }
            else{
            return z;
            }
            
        }
    }
}
