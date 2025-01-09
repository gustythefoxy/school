
package main.entity;
import java.awt.Rectangle;
import java.util.Random;
import main.GamePanel;


/**
 *
 * @author hp
 */
public class NPC_Oldman extends Entity{
    
    public NPC_Oldman(GamePanel gp){
        super(gp);
        direction = "down";
        speed = 1;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;
        
        dialogueSet = -1;
        
        getImage();
        setDialogue();
    }
    
    public void getImage(){
    up1 = setup("npc/oldman_up_1", gp.tileSize, gp.tileSize);
    up2 = setup("npc/oldman_up_2", gp.tileSize, gp.tileSize);
    down1 = setup("npc/oldman_down_1", gp.tileSize, gp.tileSize);
    down2 = setup("npc/oldman_down_2", gp.tileSize, gp.tileSize);
    right1 = setup("npc/oldman_right_1", gp.tileSize, gp.tileSize);
    right2 = setup("npc/oldman_right_2", gp.tileSize, gp.tileSize);
    left1 = setup("npc/oldman_left_1", gp.tileSize, gp.tileSize);
    left2 = setup("npc/oldman_left_2", gp.tileSize, gp.tileSize);

    }
    
    public void setDialogue(){
        dialogues[0][0] = "Hello.";
        dialogues[0][1] = "How are you?";
        dialogues[0][2] = "Just go South and there will be a shop. \nit sells usefull stuff.";
        dialogues[0][3] = "Lantern... to get through the darkness.";
        
        dialogues[1][0] = "Also you need to buy key to open the door.";
        dialogues[1][1] = "How to use it?";
        dialogues[1][2] = "Just press C on your keyboard in front \nof the door. ";
        
        dialogues[2][0] = "Have a safe trip!";
        
        dialogues[3][0] = "There's a shortcut if you have encountered \nthe boss before!";
        
        
    }
        
    public void setAction(){
        if (onPath == true) {
            int goalCol = 12;
            int goalRow= 9;
//            enable this bellow to make the npc follow the 
//            goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
//            goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
            searchPath(goalCol, goalRow);
        }
        else{
             actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100)+1; // pick up a number
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
        }
       
    }
    
    public void speak(){
        facePlayer();   
        startDialogue(this, dialogueSet);
        
        dialogueSet++;
        
        if (dialogues[dialogueSet][0] == null) {
            dialogueSet = 0;
        }
        
//        if (gp.player.life < gp.player.maxLife/2) {
//            dialogueSet = 1;
//        }
    
    }
    
}

