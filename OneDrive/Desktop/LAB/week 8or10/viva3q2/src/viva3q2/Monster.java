
package viva3q2;

public class Monster {
    
    String name;
    int pyroRes;
    int electroRes;
    int hydroRes;
    int cryoRes;
    int hp;
    
    public Monster(String name, int pyroRes, int electroRes,int hydroRes,int cryoRes,int hp){
        this.name = name;
        this.pyroRes = pyroRes;
        this.electroRes = electroRes;
        this.hydroRes = hydroRes;
        this.cryoRes = cryoRes;
        this.hp = hp;
    
    }
    public double resChecker(Hero hero){
        double multiply = 0;
        String e = hero.element.toLowerCase();
        if (e.equals("pyro")) {
            multiply = 100 - pyroRes;
        }
        else if (e.equals("hydro")) {
            multiply = 100 - hydroRes;
        }
        else if (e.equals("cryo")){
            multiply = 100 - cryoRes;
        }
        else if (e.equals("electro")) {
            multiply = 100 - electroRes;
        }
        return multiply/100;
    }
    
}
