/*\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
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
public class OBJ_Axe extends Entity{
    
    public static final String objName = "Woodcutter's Axe";
    
    public OBJ_Axe(GamePanel gp){
        super(gp);
        
        type = type_axe;
        name = objName;
        down1= setup("/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\n" + "Cut down your enemies\ntrees too";
        price = 25;
        knockBackPower = 10;
        motion1_duration = 20;
        motion2_duration = 40;
        
    }
}
