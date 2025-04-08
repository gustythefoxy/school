
package viva3q2;


public class Viva3q2 {

    public static void main(String[] args) {
        //HERO
        Hero Amber = new Hero("Amber", "pyro", 8000); 
        Hero Collei = new Hero("Collei", "hydro", 10); 
        Hero Neuvillete = new Hero("Neuvillete", "hydro", 80); 
        Hero Xiangling = new Hero("Xiangling", "pyro", 120); 
        
        //MONSTER
        Monster bear = new Monster("bear", 10, 50, 50, 50, 100);
        Monster Dvalin = new Monster("Dvalin",0, 50, 50, 50, 1000);
        
        //PARTY
        Hero[] list = {Amber, Collei, Neuvillete, Xiangling};
        HeroParty party = new HeroParty(list);
        
        
        //THE THING THAT VIVA WANTS
        //party.heroPower();
        //System.out.println("");
        //party.battleWinner(bear);
        System.out.println("");
        party.battleBoss(Dvalin);
        
    }
    
}
