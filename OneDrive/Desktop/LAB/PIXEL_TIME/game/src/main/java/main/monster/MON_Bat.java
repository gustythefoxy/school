
package main.monster;

import java.util.Random;
import main.GamePanel;
import main.entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_Bat extends Entity{
    GamePanel gp;
    
    public MON_Bat(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        switch(gp.player.difficulty){
            case 0: maxLife = 7; attack = 4; defense = 0; break;
            case 1: maxLife = 13; attack = 5; defense = 1; break;
            case 2: maxLife = 17; attack = 8; defense = 2; break;
        
        }
        
        type = type_monster;
        name = "Bat";
        defaultSpeed = 4;
        speed = defaultSpeed;
        
        life = maxLife;
        
        
        exp = 7;
        projectile = new OBJ_Rock(gp);
        score = 10;
        
        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
    
    public void getImage(){
        up1 = setup("monster/bat_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/bat_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/bat_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/bat_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/bat_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/bat_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/bat_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/bat_down_2", gp.tileSize, gp.tileSize);
    }
    
    public void setAction(){
        
        
        
        if (onPath == true) {
            
//            //check if it stops chasing you
//            checkStopChasingOrNot(gp.player, 18, 100);
//
//            
//            //search the direction to go
////            enable this bellow to make the npc follow the \\\\\\
//            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
            
            //shoot out projectile
//            checkShootOrNot(200, 30);
        }
        else{
//            //check if it start chasing you
//            checkStartChasingOrNot(gp.player, 5, 100);
            
            //get a random direction if not onPath
             getRandomDirection(10);
            }
        }

    
    public void damageReaction(){
        
        actionLockCounter = 0;
        onPath = true;
//        direction = gp.player.direction;
        
    }
    public void checkDrop(){
        gp.player.score += score;
        // cast a die
        int i = new Random().nextInt(100) +1;
        
        //set the monster drop
        if (i < 50) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }  
    
    
}
