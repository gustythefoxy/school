/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UltilityTool;

/**
 *
 * @author hp
 */
public class Entity {
    
    GamePanel gp;
    
    
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackRight1, attackRight2, attackLeft1, attackLeft2, guardUp, guardDown, guardRight, guardLeft;
    public BufferedImage image, image2, image3;    
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;    
    public String dialogues[][] = new String[20][20];
    public Entity attacker;
    public Entity linkedEntity;
    public boolean temp = false;
    public int difficultyMultiplier = 1;
    
    
    
    //STATE
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;   
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public boolean guarding = false;
    public boolean transparent = false;
    public String knockBackDirection;
    public boolean offBalance = false;
    public Entity loot;
    public boolean opened = false;
    public boolean inRage = false;
    public boolean sleep = false;
    public boolean drawing = true;
    public int difficulty;
    public final int easy = 0; // difficulty\\\\\\\\\\\\\\\\\\\\\\
    public final int medium = 1;
    public final int hard = 2;
    
    //COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int shotAvailableCounter = 0;
    int knockBackCounter = 0;
    public int guardCounter = 0;
    int offBalanceCounter = 0;
    
    //CHARACTER ATTRIBUTES
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int speed;
    public String name;
    public int defaultSpeed;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int motion1_duration;
    public int motion2_duration;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentBoots;
    public Entity currentLight;
    public Projectile projectile;
    public boolean boss;
    public String characterClass = "";
    public double extraStatAtk = 1;
    public double extraStatDef = 1;
    public int extraHp = 0;
    public double extraProjectileDmg = 1;
    public int extraMana = 0;
    public int score = 0;
    
    //ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;
    
    //TYPE
    public int type; //0 is player, 1 is npc, 2 is moster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;
    public final int type_pickaxe = 10;
    public final int type_boots = 11;
    
    
    
    public Entity(GamePanel gp){
        this.gp = gp;
    }
    
    //blank function is overrided due to inheritence in other subclasses
    public int getXdistance(Entity target){
        int xDistance = Math.abs(getCenterX() - target.getCenterX());
        return xDistance;
    }
    public int getYdistance(Entity target){
        int yDistance = Math.abs(getCenterY() - target.getCenterY());
        return yDistance;
    }
    public int getTileDistance(Entity target){
        int tileDistance = (getXdistance(target) + getYdistance(target))/gp.tileSize;
        return tileDistance;
    }
    public int getGoalCol(Entity target){
        int goalCol = (target.worldX + target.solidArea.x)/gp.tileSize;
        return goalCol;
    }
    public int getGoalRow(Entity target){
        int goalRow = (target.worldY + target.solidArea.y)/gp.tileSize;
        return goalRow;
    }
    public void resetCounter(){
        //COUNTER
        spriteCounter = 0;
        actionLockCounter = 0;
        invincibleCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        shotAvailableCounter = 0;
        knockBackCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }
    public void setLoot(Entity loot){}
    public void setAction(){}
    public void move(String direction){}
    public void damageReaction(){}
    public void speak(){   }
    public void facePlayer(){
        switch(gp.player.direction){
        case "up": direction = "down"; break;
        case "down": direction = "up"; break;
        case "left": direction = "right"; break;
        case "right": direction = "left"; break;       
        }
    }
    public void startDialogue(Entity entity, int setNum){
        
        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
        
    }
    public int getScreenX(){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        return screenX;
    }
    public int getScreenY(){
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        return screenY;
    }
    public int getLeftX(){
        return worldX + solidArea.x;
    }
    public int getRightX(){
        return worldX + solidArea.x + solidArea.width;
    }
    public int getTopY(){
        return worldY + solidArea.y;
    }
    public int getBottomY(){
        return worldY + solidArea.y + solidArea.height;
    }
    public int getCol(){
        return (worldX +  solidArea.x)/gp.tileSize;
    }
    public int getRow(){
        return (worldY +  solidArea.y)/gp.tileSize;
    }
    public int getCenterX(){
        int centerX = worldX + up1.getWidth()/2;
        return centerX;
    }
    public int getCenterY(){
        int centerY = worldY + up1.getHeight()/2;
        return centerY;
    }   
    public void interact(){
        
    }
    public boolean use(Entity entity){return false;}
    public void checkDrop(){}
    public void dropItem(Entity droppedItem){
        for (int i = 0; i < gp.obj[1].length; i++) {
            
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX; //dead monster position
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
    public Color getParticleColor(){
        Color color = null;
        return color;
    }
    public int getParticleSize(){
        int size = 0; //  pixels
        return size;
    }
    public int getParticleSpeed(){
        int speed = 0;
        return speed;
    }
    public int getParticleMaxLife (){
        int maxLife = 0;
        return maxLife;
    }
    public void generateParticle(Entity generator, Entity target){
        
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();
        
        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 1, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -1, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }
    public void checkCollision(){
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        
        if (this.type == type_monster && contactPlayer == true) {
            damagePlayer(attack);
        } 
    }
    public void update(){
        
        if (sleep == false) {
            if (knockBack == true) {
                checkCollision();


                if (collisionOn == true) {
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }
                else if (collisionOn == false) {
                    switch(knockBackDirection){
                        case "up": worldY -=  speed; break;
                        case "down": worldY += speed; break;
                        case "left": worldX -= speed; break;
                        case "right": worldX += speed; break;
                    }
                }
                knockBackCounter++;
                if (knockBackCounter == 10) {
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }
            }
            else if (attacking == true) {
                attacking();
            }
            else{
                setAction();
                checkCollision();

                //IF COLLISION IS FALSE, PLAYER CAN MOVE
                if (collisionOn == false) {
                    switch(direction){
                        case "up": worldY -=  speed; break;
                        case "down": worldY += speed; break;
                        case "left": worldX -= speed; break;
                        case "right": worldX += speed; break;
                    }
                }
                spriteCounter++;
                if (spriteCounter > 24) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    }
                    else if(spriteNum == 2){
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }

            if (invincible == true) {
                invincibleCounter++;
                if (invincibleCounter > 40) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
            if (shotAvailableCounter < 30) {
                shotAvailableCounter++;
            }
            if (offBalance == true) {
                offBalanceCounter++;
                if (offBalanceCounter > 60) {
                    offBalance = false;
                    offBalanceCounter = 0;
                }
            }
        }
        
        
    }
    
    public void checkAttackOrNot(int rate, int straight, int horizontal){
        
        boolean targetInRange = false;
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);
        
        switch(direction){
            case "up":
                if (gp.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "down":
                if (gp.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "left":
                if (gp.player.getCenterX() < getCenterX() && xDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;
            case "right":
                if (gp.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal) {
                    targetInRange = true;
                }
                break;
        }
        
        if (targetInRange == true) {
            //check if it initiate an attack
            int i = new Random().nextInt(rate);
            if (i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }
    public void checkShootOrNot(int rate, int shotInterval){
        int i = new Random().nextInt(rate); 
            if (i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval) {
                projectile.set(worldX, worldY, direction, true, this);

                // CHECHK VACANCY
                for (int ii = 0; ii < gp.projectile[1].length; ii++) {
                    if (gp.projectile[gp.currentMap][ii] == null) {
                            gp.projectile[gp.currentMap][ii] = projectile;
                        break;
                    }
                }
                shotAvailableCounter = 0;
            }
    }
    public void checkStartChasingOrNot(Entity target, int distance, int rate){
        if (getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = true;
            }
        }
    }
    public void checkStopChasingOrNot(Entity target, int distance, int rate){
        if (getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = false;
            }
        }
    }
    public void getRandomDirection(int interval){
        actionLockCounter++;

        if (actionLockCounter > interval) {
            Random random = new Random();
            int i = random.nextInt(100)+1; // pick up a number
            if (i <= 25) {direction = "up";}
            if (i > 25 && i <= 50) {direction = "down";}
            if (i > 50 && i <= 75) {direction = "left";}
            if (i > 75 && i <= 100) {direction = "right";}
            actionLockCounter = 0;
        }
    }
    public void setKnockBack(Entity target,Entity attacker, int knocBackPower){
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }
    public void moveTowardPlayer(int interval){
        actionLockCounter++;
        
        if (actionLockCounter > interval) {
            if (getXdistance(gp.player) > getYdistance(gp.player)) {
                if (gp.player.getCenterX() < getCenterX()) {
                    direction = "left";
                }
                else{
                    direction = "right";
                }
            }
            else if (getXdistance(gp.player) < getYdistance(gp.player)) {
                if (gp.player.getCenterY() < getCenterY()) {
                    direction = "up";
                }
                else{
                    direction = "down";
                }
            }
            actionLockCounter = 0;
        }
    }
    public String getOppositeDirection(String direction){
        String oppositeDirection ="";
        
        switch(direction){
            case "up": oppositeDirection = "down"; break;
            case "down": oppositeDirection = "up"; break;
            case "left": oppositeDirection = "right"; break;
            case "right": oppositeDirection = "left"; break;
        }
        return oppositeDirection;
    }
    public void attacking(){
        spriteCounter++;
        
        if (spriteCounter <= motion1_duration) {
            spriteNum =1;
        }
        if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
            spriteNum = 2;
            
            //save the current worldx.wprldY,solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            
            //adjust the attack area
            switch(direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += gp.tileSize; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += gp.tileSize; break;
            }
            //attack area become solid Area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            
            if (type == type_monster ) {
                if (gp.cChecker.checkPlayer(this) == true) {
                    damagePlayer(attack);
                }
            }
            else{ // player
                // check monster collision with the updated worldx, worldY, solidArea
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex,this,  attack, currentWeapon.knockBackPower);

                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }
            
            
            // then, return the solidArea to original
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
            
        }
        if (spriteCounter > motion2_duration) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void damagePlayer(int attack){
        if (gp.player.invincible == false)
        {
            //we can give dmage
            
            int damage = attack - gp.player.defense;
            
            //get an opposite directiion of this attack
            String canGuardDirection = getOppositeDirection(direction);
            //start guarding
            if (gp.player.guarding == true && gp.player.direction.equals(canGuardDirection)) {
                //parry
                if (gp.player.guardCounter < 10) {//
                    damage = 0;
                    gp.playSE(16);
                    setKnockBack(this, gp.player, knockBackPower);
                    offBalance = true;
                    spriteCounter =-60;
                }
                //normal guard
                else{
                    damage /= 3;
                    gp.playSE(16);
                }
                
            }
            else{
                gp.playSE(6);
                if (damage <1) {

                damage = 1;
                }
            }
            if (damage !=0) {
                gp.player.transparent = true;
                setKnockBack(gp.player, this, knockBackPower);
            }
            gp.player.life -=damage;
            gp.player.invincible = true;
            
        }
    }
    public boolean inCamera(){
        boolean inCamera = false;
        if (worldX + gp.tileSize*5 > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize*5> gp.player.worldY - gp.player.screenY && worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {
            inCamera = true;
        }
        return inCamera;
    }
    public void draw(Graphics2D g2){
        BufferedImage image  = null;
        
        int tempScreenX = getScreenX();
        int tempScreenY = getScreenY();
            
        if (inCamera() == true) {
            
        
                switch(direction){
            case "up":
                if (attacking == false) {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
                if (attacking == true) {
                    tempScreenY = getScreenY() - up1.getHeight();
                    if (spriteNum == 1) {image = attackUp1;}
                    if (spriteNum == 2) {image = attackUp2;}
                }
                break;
            case "down":
                if (attacking == false) {
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                }
                if (attacking ==true) {
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                }
                break;
            case "left":
                if (attacking == false) {
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;} 
                }
                if (attacking == true) {
                    tempScreenX= getScreenX() - left1.getHeight();
                    if (spriteNum == 1) {image = attackLeft1;}
                    if (spriteNum == 2) {image = attackLeft2;}
                }
                
                break;
            case "right":
                if (attacking == false) {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                }
                if (attacking == true) {
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}
                }
                
                break;
                }
                
                
            
                
                if (invincible == true) {
                    hpBarOn = true;
                    hpBarCounter = 0;
                    changeAlpha(g2, 0.4f); 
                }
                    if (dying == true) {
                        dyingAnimation(g2);
                    }

                    g2.drawImage(image, tempScreenX, tempScreenY, null);
                    changeAlpha(g2, 1f); 
                    
            }
    }
    public void dyingAnimation(Graphics2D g2){
        
        
        dyingCounter++;
        
        int i = 5;  
        if (dyingCounter <= i) {changeAlpha(g2, 0f);}
        if (dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2, 1f);} 
        if (dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*8) {
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height){
        UltilityTool uTool = new UltilityTool();
        BufferedImage image = null;
        
        try{
            File f1 = new File("./src/"+ imagePath +".png");
            image = ImageIO.read(f1);
            image = uTool.scaleImage(image, width, height);

            
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
        
    }
    public void searchPath(int goalCol, int goalRow){
        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;
        
        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow );
        
        if (gp.pFinder.search() == true) {
            
            //NEXT worldX & worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
             
            
            //entity solid area pos
            int enLeftX = worldX + solidArea.x; //true
            int enRightX =  worldX + solidArea.x + solidArea.width; //true
            int enTopY = worldY + solidArea.y; //true
            int enBottomY =  worldY + solidArea.y + solidArea.height; //true
            
            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
               
            }
            else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
                
            }
            else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize ) {
                //left or right
                if (enLeftX > nextX) {
                    direction = "left";
                    
                }
                if (enLeftX < nextX) {
                    direction = "right";
                    
                }
            }
            else if (enTopY > nextY && enLeftX > nextX ) {
                //up or left
                direction = "up";
                
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                    
                }
            }
            else if (enTopY > nextY && enLeftX < nextX) { //true
                //up or rigjt
                direction = "up";
               
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                    
                }
            }
            else if (enTopY < nextY && enLeftX > nextX) { //true
                //down or left
                direction = "down";
                
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                    
                }
            }//
            else if (enTopY < nextY && enLeftX < nextX) { //true
                //down or rigth
                direction = "down";
               
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                    
                }
            }
            //if reaches goal, stop or disable this when you want them to follow you
            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            if (nextCol == goalCol && nextRow == goalRow) {
                onPath = false;
            }
        }
    }
    public int getDetected(Entity user, Entity target[][], String targetName){
        int index = 999;
        
        //check the surrounding
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();
        
        switch(user.direction){
            case "up": nextWorldY = user.getTopY()-gp.player.speed; break;
            case "down": nextWorldY = user.getBottomY()+gp.player.speed; break;
            case "left": nextWorldX = user.getLeftX()-gp.player.speed; break;
            case "right": nextWorldY = user.getRightX()+gp.player.speed; break;
        }
        int col = nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;
        
        
        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {
                if (target[gp.currentMap][i].getCol() == col && target[gp.currentMap][i].getRow() == row && target[gp.currentMap][i].name.equals(targetName)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
}

