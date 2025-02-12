/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.awt.Color;
import main.GamePanel;
import main.entity.Entity;
import main.entity.Projectile;


/**
 *
 * @author hp
 */
public class OBJ_Rock extends Projectile{
    
    public static final String objName = "Rock";
    
        GamePanel gp;
    
    public OBJ_Rock(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        name = objName;
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
       
    }
    
    public void getImage(){
        
        up1 = setup("projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        down1 = setup("projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        left1 = setup("projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        right1 = setup("projectiles/rock_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("projectiles/rock_down_1", gp.tileSize, gp.tileSize);
    }
    public boolean haveResources(Entity user){
        boolean haveResources = false;
        if (user.ammo >= useCost) {
            haveResources = true;
        }
        return haveResources;
    }
    public void subtractResources(Entity user){
        user.ammo -= useCost;
    }
    
    public Color getParticleColor(){
        Color color = new Color(40,50,0);
        return color;
    }
    public int getParticleSize(){
        int size = 10; // 6 pixels
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
    
}
