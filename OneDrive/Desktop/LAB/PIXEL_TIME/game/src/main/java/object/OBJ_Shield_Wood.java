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
public class OBJ_Shield_Wood extends Entity{
    
    public static final String objName = "Wooden Shield";
    
    public OBJ_Shield_Wood (GamePanel gp){
        super(gp);
        
        type = type_shield;
        name = objName;
        down1 = setup("objects/shield_wood",gp.tileSize,gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\n" + "normal defend wood";  
        price = 15;
        
    } 
}
