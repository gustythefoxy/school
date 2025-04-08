
package viva3q1;
public class Viva3Q1 {
    public static void main(String[] args) {
        Vehichle vh1 = new Vehichle("0011", "Van", "toyota", 100, 50);
        vh1.calculateRentalCost(14);
        
        
        
        String ans = vh1.toString();
        System.out.println(ans);
        
    }
    
}
