
package object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.entity.Entity;

public class OBJ_Key extends Entity{
    
    public static final String objName = "Key";
    
    GamePanel gp;
        
        public OBJ_Key(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        type = type_consumable;
        name = objName;
        down1 = setup("objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\n" + "key";  
        price = 10;
        stackable = true;
        setDialogue();
    }
    public void setDialogue(){
        dialogues[0][0] = "you used the " + name + " and opened the door";
        
        dialogues[1][0] = "what the?";
    }
    public boolean use(Entity entity){
        
        
        
        int objIndex = getDetected(entity, gp.obj, "Door");
        if (objIndex != 999) {
            startDialogue(this, 0);
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
            
        }
        else{
            startDialogue(this, 1);
            return false;
        }
    }
    
    
}
