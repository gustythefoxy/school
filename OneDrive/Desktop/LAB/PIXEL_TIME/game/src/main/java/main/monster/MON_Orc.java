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

/**
 *
 * @author hp
 */
public class MON_Orc extends Entity{
    GamePanel gp;
    
    public MON_Orc(GamePanel gp) {
        super(gp);
        
        this.gp = gp;
        
        
        switch(gp.player.difficulty){
            case 0: maxLife = 16; attack = 8; defense = 2;defaultSpeed = 1; break;
            case 1: maxLife = 20; attack = 10; defense = 4;defaultSpeed = 2; break;
            case 2: maxLife = 50; attack = 14; defense = 8;defaultSpeed = 3; break;
        }
        type = type_monster;
        name = "Orc";
        
        speed = defaultSpeed;
        
        life = maxLife;
        
        
        exp = 15;
        knockBackPower = 5;
        score = 15;
        
        
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height= 48;
        motion1_duration = 40;
        motion2_duration = 85;
        
        getImage();
        getAttackImage();
    }
    
    public void getImage(){
        up1 = setup("monster/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/orc_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/orc_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/orc_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/orc_right_2", gp.tileSize, gp.tileSize);
    }
    public void getAttackImage(){
        attackUp1 = setup("monster/orc_attack_up_1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("monster/orc_attack_up_2", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("monster/orc_attack_down_1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("monster/orc_attack_down_2", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("monster/orc_attack_left_1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("monster/orc_attack_left_2", gp.tileSize*2, gp.tileSize);        
        attackRight1 = setup("monster/orc_attack_right_1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("monster/orc_attack_right_2", gp.tileSize*2, gp.tileSize);
    }
    public void setAction(){
        
        
        
        if (onPath == true) {
            
            //check if it stops chasing you
            checkStopChasingOrNot(gp.player, 18, 100);

            
            //search the direction to go
//            enable this bellow to make the npc follow the \\\\\\
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
            
        }
        else{
            //check if it start chasing you
            checkStartChasingOrNot(gp.player, 5, 100);
            
            //get a random direction if not onPath
             getRandomDirection(150);
        }
        //checks if its attack
        if (attacking == false) {
            checkAttackOrNot(30, gp.tileSize*4, gp.tileSize);
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
        if (i < 80) {
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
