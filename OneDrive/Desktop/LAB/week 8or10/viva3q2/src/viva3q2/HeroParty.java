
package viva3q2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


public class HeroParty {
    Hero[] heroList = new Hero[4];
    
    public HeroParty(Hero[] heroList){
        this.heroList = heroList;
    }
    
    public void heroPower(){
        
        Arrays.sort(heroList, new Comparator<Hero>() {
            @Override
            public int compare(Hero o1, Hero o2) {
                return Integer.compare(o2.power, o1.power);
            }
        });
        System.out.println("List of heroes based on power: ");
        for (int i = 0; i < heroList.length; i++) {
            
            System.out.println(heroList[i].name + ", " + heroList[i].element + ", " + heroList[i].power);
        }
    }
    public void battleWinner(Monster enemy){
        double damage = 0;
        System.out.println("hero that will win againts " + enemy.name);
        for (int i = 0; i < heroList.length; i++) {
            damage = heroList[i].power * enemy.resChecker(heroList[i]);
            if (damage >= enemy.hp) {
                System.out.println(heroList[i].name + " , " + damage);
            }
        }
    }
    
    
    
    public double elementalReaction(Hero a, Hero b, Monster enemy){
        boolean superConduct = false;
        
        String p = "pyro";
        String h = "hydro";
        String c = "cryo";
        String e = "electro";
        
        String aE = a.element.toLowerCase();
        String bE = b.element.toLowerCase();
        
        double aP = a.power;
        double bP = b.power;
        
        double total = 0;
        
        Random r = new Random();
        
        if (aE.equals(p)) {
            if (bE.equals(h)) {
                aP = aP*1.5;
            }
            else if (bE.equals(c)) {
                aP = aP*1.5;
            }
            else if (bE.equals(e)) {
                aP += r.nextInt(50)+1 + 50;
            }
        }
        else if (aE.equals(h)) {
            if (bE.equals(e)) {
                aP += (r.nextInt(20)+1)*5;
            }
        }
        else if (aE.equals(e)) {
            if (bE.equals(c)) {
                superConduct = true;
            }
        }
        
        if (superConduct ==  true) {
            total = (aP * enemy.resChecker(a)) + (bP * (enemy.resChecker(b)+10));
        }
        else{
            total = (aP * enemy.resChecker(a)) + (bP * (enemy.resChecker(b)+0));
        }
        
        return total;
    }
    public void battleBoss(Monster boss){
        String pair1 = "";
        String pair2 = "";
        
        double temp = 0;
        double damage = 0;
        for (int i = 0; i < heroList.length; i++) {
            for (int j = 0; j < heroList.length; j++) {
                if (j != i) {
                    damage = elementalReaction(heroList[i], heroList[j], boss);
                    temp = damage;
                    if (damage >= temp) {
                        temp = damage;
                        pair1 = heroList[i].name;
                        pair2 = heroList[j].name;
                    }
                }
            }
        }
        if (temp >= boss.hp) {
            System.out.println("You beat the boss");
            System.out.println("The pair with the highest damage: " + pair1 + " and " + pair2);
            System.out.println("total dmg dealt: " + temp);
        }
        else{
            System.out.println("You failed");
            System.out.println("Strongest: " + pair1 + "," +pair2);
            System.out.println("total dmg dealt: " + temp);
            System.out.println("boss health left: " + (boss.hp - temp));
        }
        
        
    }
}
