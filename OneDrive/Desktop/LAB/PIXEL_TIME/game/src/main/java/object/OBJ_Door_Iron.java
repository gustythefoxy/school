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
public class OBJ_Door_Iron extends Entity{
    public static final String objName = "Iron Door";
    
    GamePanel gp;
    public OBJ_Door_Iron(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        type = type_obstacle;
        name = objName;
        down1 = setup("objects/door_iron", gp.tileSize, gp.tileSize);

        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }
    public void setDialogue(){
        dialogues[0][0] = "it won't budge";
    }
    public void interact(){
        startDialogue(this, 0);
        
    }
}
