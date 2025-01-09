/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import main.GamePanel;
import main.entity.Entity;

public class OBJ_Lantern extends Entity{
    
    public static final String objName = "Lantern";
    
    public OBJ_Lantern(GamePanel gp) {
        super(gp);
        
        type = type_light;
        name = objName;;
        down1 = setup("objects/lantern", gp.tileSize, gp.tileSize);
        description = "[Lantern] \nIlluminate your life";
        price = 10;
        lightRadius = 200;
    }
    
    
    
}
