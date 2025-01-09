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
public class OBJ_Potion_Red extends Entity{
    
    public static final String objName = "Red Potion";
    
    GamePanel gp;
    
    
    public OBJ_Potion_Red(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        type = type_consumable;
        name = objName;
        value = 5;
        down1 = setup("objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n" + "heals " + value + " life";  
        price = 5;
        stackable = true;
    }
    public void setDialogue(){
        dialogues[0][0] = "you drank the " + name + "!\n" + "heals by " + value + " health";
    }
    public boolean use(Entity entity){
        
        gp.gameState = gp.dialogueState;
        startDialogue(this, 0);
        entity.life += value;
        
        gp.playSE(2);
        return true;
    }
    
}
