
package object;

import main.GamePanel;
import main.entity.Entity;


public class OBJ_BlueHeart extends Entity {
    
    
    GamePanel gp;
    public static final String objName = "Blue Heart";
    public OBJ_BlueHeart(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        type = type_pickupOnly;
        name = objName;
        down1 = setup("objects/blueheart", gp.tileSize, gp.tileSize);
        setDialogue();
        
    }
    public void setDialogue(){
        dialogues[0][0] = "you picked up the blue gem";
        dialogues[0][1] = "you are the blue";
        
    }
    public boolean use(Entity entity){
        
        gp.gameState = gp.cutsceneState;
        gp.csManager.sceneNum = gp.csManager.ending;
        return true;
    }
    
}
