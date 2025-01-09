/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.monster;

import java.util.Random;
import main.GamePanel;
import main.entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_RedSlime extends Entity{
        GamePanel gp;
    
    public MON_RedSlime(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        switch(gp.player.difficulty){
            case 0: maxLife = 30; attack = 5; defense = 0; break;
            case 1: maxLife = 40; attack = 6; defense = 3; break;
            case 2: maxLife = 80; attack = 12; defense = 7; break;
        }
        type = type_monster;
        name = "Red Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        
        
        life = maxLife;
        
        
        exp = 10;
        projectile = new OBJ_Rock(gp);
        score = 40;
        
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
    
    public void getImage(){
        up1 = setup("monster/redSlime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/redSlime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/redSlime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/redSlime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/redSlime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/redSlime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/redSlime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/redSlime_down_2", gp.tileSize, gp.tileSize);
    }
    
    public void setAction(){
        
        
        
        if (onPath == true) {
            speed = 5;
            //check if it stops chasing you
            checkStopChasingOrNot(gp.player, 18, 100);

            
            //search the direction to go
//            enable this bellow to make the npc follow the \\\\\\
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
            
            //shoot out projectile
            checkShootOrNot(200, 10);
        }
        else{
            //check if it start chasing you
            if (gp.player.difficulty == 1) {
                checkStartChasingOrNot(gp.player, 5, 100);
            }
            else if (gp.player.difficulty == 2) {
                checkStartChasingOrNot(gp.player, 17, 10);
            }
            
            speed = defaultSpeed;
            //get a random direction if not onPath
             getRandomDirection(120);
            }
        }

    
    public void damageReaction(){
        
        actionLockCounter = 0;
        onPath = true;
        
        switch(gp.player.difficulty){
            case 0: defaultSpeed +=1; attack += 2; break;
            case 1:  defaultSpeed +=2; attack += 4; break;
            case 2:   defaultSpeed +=3; attack += 8;break;
        }
//        direction = gp.player.direction;
        
    }
    public void checkDrop(){
        gp.player.score += score;
        // cast a die
        int i = new Random().nextInt(100) +1;
        
        //set the monster drop
        if (i < 80) {
            dropItem(new OBJ_Coin_Bronze(gp));
            dropItem(new OBJ_Coin_Bronze(gp));
            dropItem(new OBJ_Coin_Bronze(gp));
            dropItem(new OBJ_Coin_Bronze(gp));
            dropItem(new OBJ_Coin_Bronze(gp));
            dropItem(new OBJ_Coin_Bronze(gp));
            dropItem(new OBJ_Coin_Bronze(gp));
            dropItem(new OBJ_Coin_Bronze(gp));
            dropItem(new OBJ_Coin_Bronze(gp));
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if (i >= 80 && i < 90) {
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 90 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }  
    
    
}
