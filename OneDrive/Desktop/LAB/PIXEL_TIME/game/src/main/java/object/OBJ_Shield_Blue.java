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
public class OBJ_Shield_Blue extends Entity{
    
    public static final String objName = "Blue Shield";
    
    public OBJ_Shield_Blue (GamePanel gp){
        super(gp);
        
        type = type_shield;
        name = objName;
        down1 = setup("objects/shield_blue",gp.tileSize,gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\n" + "normal defend blue shield";  
        price = 25;
    }
    
}
