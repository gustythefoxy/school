
package tuto8lab8;

public class Car {
    
    String jenama, warna, tempat;
    
    public Car (String a, String b, String c){
        this.jenama = a;
        this.tempat = b;
        this.warna = c;
        
        
    }
    
    public String allCar(){
        String all = this.jenama + this.tempat + this.warna;
        return all;
    }
}
    