
package viva3q1;

public class Vehichle {
    String vehichleID;
    String type;
    String brand;
    int rentalRate;
    double fuelLevel;
    int day;
    
    public Vehichle(){
        vehichleID = "0000";
        type = "car";
        brand = "Myvi";
        rentalRate = 100;
        fuelLevel = 100.00;
    }
    
    public Vehichle(String vehichleID, String type, String brand, int rentalRate, double fuelLevel){
        this.vehichleID = vehichleID;
        this.type = type;
        this.brand = brand;
        this.rentalRate = rentalRate;
        this.fuelLevel = fuelLevel;
    }
    
    
    public int calculateRentalRate(){
        double modified = 0;
        
        String temp = type.toLowerCase();
        if (temp.equals("van") || temp.equals("vans")) {
            modified = rentalRate* 1.2;
        }
        else if(temp.equals("motorcycle") || temp.equals("motorcycles")){
            modified = rentalRate* 0.75;
        }
        else{
            modified = rentalRate;
        }
        return (int) modified;
    }
    
    
    
    
    public double calculateRentalCost(int day){
        this.day = day;
        double total = calculateRentalRate()*day;
        
        if (day >6 && day < 15) {
            total *= 0.95;
        }
        else if (day >= 15) {
            total *= 0.9;
        }
        return total;
    }
    
    public int calculateFuelCharge(){
        int extra = 0;
        if (fuelLevel <= 50) {
            extra = 50;
        }
        return extra;
    }
    
    
    
    public String toString(){
        
        String a = "Vehicle ID: "+ vehichleID;
        String b = "Type: " + type;
        String c = "Brand: " + brand;
        String d = "Rental Rate: $" + calculateRentalRate();
        String e = "Fuel Level: " + fuelLevel + "%";
        String f = "Rental Days: " + day;
        String g = "Total Cost: RM" + (calculateRentalCost(day) + calculateFuelCharge());
        
        return a + "\n" + b + "\n" + c + "\n" + d + "\n" + e + "\n" + f + "\n" + g;
    }

    public String getVehichleID() {
        return vehichleID;
    }

    public void setVehichleID(String vehichleID) {
        this.vehichleID = vehichleID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(int rentalRate) {
        this.rentalRate = rentalRate;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    

}
