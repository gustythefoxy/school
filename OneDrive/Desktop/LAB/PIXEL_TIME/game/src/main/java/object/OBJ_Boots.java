
package object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.entity.Entity;

public class OBJ_Boots extends Entity {
    
    public static final String objName = "Boots";
    
    GamePanel gp;
    public OBJ_Boots(GamePanel gp){
        super(gp);
        
        type = type_boots;
        name = objName;
        price = 11;
        speed = 1;
        description = "[" + name + "]\n" + "move faster";
        down1 = setup("objects/boots", gp.tileSize, gp.tileSize);
    }
}
