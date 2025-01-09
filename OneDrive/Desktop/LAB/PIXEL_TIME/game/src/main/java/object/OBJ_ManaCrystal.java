/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import main.GamePanel;
import main.entity.Entity;

/**
 *
 * @author hp
 */
public class OBJ_ManaCrystal extends Entity {
    
    public static final String objName = "Mana Crystal";
    
    GamePanel gp;
    
    public OBJ_ManaCrystal(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        type = type_pickupOnly;
        value = 1;
        name =  objName;
        down1 = setup("objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image = setup("objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("objects/manacrystal_blank", gp.tileSize, gp.tileSize);
    }
    
    public boolean use(Entity entity){
        gp.playSE(1);
        gp.ui.addMessage("Mana + " + value);
        entity.mana += value;
        return true;
    }
}
