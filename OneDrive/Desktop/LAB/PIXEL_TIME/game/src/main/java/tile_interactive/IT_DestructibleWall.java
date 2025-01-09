
package tile_interactive;

import java.awt.Color;
import java.util.Random;
import main.GamePanel;
import main.entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;


public class IT_DestructibleWall extends InteractiveTile{
    
    
    
    GamePanel gp;
    
    public IT_DestructibleWall(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        
        down1 = setup("interactives/destructiblewall", gp.tileSize, gp.tileSize);
        destructible = true;
        life = 1;
    }
    
    public boolean isCorrectWeapon(Entity entity){
        boolean isCorrectItem = false;
        if (entity.currentWeapon.type == type_pickaxe) {
            isCorrectItem = true;
        }
        
        return isCorrectItem;
    }
    public void playSE(){
        gp.playSE(21);
    }
    public InteractiveTile getDestroyedForm(){
        InteractiveTile tile = null;
        return tile;
    }
    
    public Color getParticleColor(){
        Color color = new Color(65,65,65);
        return color;
    }
    public int getParticleSize(){
        int size = 6; // 6 pixels
        return size;
    }
    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }
    
    public int getParticleMaxLife (){
        int maxLife = 20;
        return maxLife;
    }
    public void checkDrop(){
        
        // cast a die
        int i = new Random().nextInt(100) +1;
        
        //set the monster drop
        if (i < 15) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
    }  
}
