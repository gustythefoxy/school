
package object;

import main.GamePanel;
import main.entity.Entity;


public class OBJ_Tent extends Entity{
    public static final String objName = "Tent";
    
    GamePanel gp;

    public OBJ_Tent(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = type_consumable;
        name = objName;
        down1 = setup("objects/tent", gp.tileSize, gp.tileSize);
        description = "[Tent] \nSkip the night";
        price = 6;
        stackable = true;
    }
    public boolean use(Entity entity){
        gp.gameState =  gp.sleepState;
        gp.playSE(15);
        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        gp.player.getSleepingImage(down1);
        //make return true if want to use this item once only
        return true;
    }
    
    
}
