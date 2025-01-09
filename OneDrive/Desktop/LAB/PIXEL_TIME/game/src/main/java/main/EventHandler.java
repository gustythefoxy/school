/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import data.Progress;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.entity.Entity;


public class EventHandler {
    
    GamePanel gp;
    EventRect eventRect[][][]; 
    Entity eventMaster;
    
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;
    boolean meetBoss = false;
    
    public EventHandler(GamePanel gp){
        
        this.gp = gp;
        
        eventMaster = new Entity(gp);
        
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        
        int map = 0;
        int col = 0;
        int row = 0;
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){
        
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
                
                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
 
        setDialogue();
    }
    public void setDialogue(){
    
        eventMaster.dialogues[0][0] = "you fall";
        
        eventMaster.dialogues[1][0] = "you healed \nheal all heart and mana";
        eventMaster.dialogues[1][1] = "damn this taste like water\nSaved progress";
        
        eventMaster.dialogues[2][0] = "tp";
    }
    //event is put here
    public void checkEvent(){
        //check  if player char is one time away from event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }
        
        if (canTouchEvent == true) {
            //if (hit(0,27,16,"right") == true) {damagePit(gp.dialogueState);}
            if (hit(0,23,12, "up") == true) {healingPool(gp.dialogueState);}
            else if (meetBoss == true) {
                if (hit(0, 20, 16, "any") == true) {teleport(3,26,41, gp.dungeon);}
            }
            else if (hit(0,21,16, "any") == true) {teleportNormal(gp.dialogueState);}
            else if (hit(0,10,40, "any") == true) {teleport(1, 12, 10, gp.indoor);} // to merchant
            else if (hit(1, 12, 13, "any") == true) {teleport(0,10,40, gp.outside);} // to merchant
            else if (hit(1,12,9,"up") == true) {speak(gp.npc[1][0]);}
            
            else if (hit(0, 12, 9, "any") == true) {teleport(2,9,41, gp.dungeon);} // to the dungeon
            else if (hit(2, 9, 41, "any") == true) {teleport(0,12,9, gp.outside);} // to the outside
            else if (hit(3, 25, 7, "any") == true) {teleport(0,12,9, gp.outside);} // to the outside
            else if (hit(4, 9, 41, "any") == true) {teleport(2,8,7, gp.dungeon);} // to the dungeon f1
            else if (hit(5, 9, 41, "any") == true) {teleport(2,8,7, gp.dungeon);} // to the dungeon f1
            else if (hit(6, 9, 41, "any") == true) {teleport(2,8,7, gp.dungeon);} // to the dungeon f1
            else if (hit(7, 9, 41, "any") == true) {teleport(2,8,7, gp.dungeon);} // to the dungeon f1
            
            //random random dungeon from the first floor to random
            else if (hit(2, 8, 7, "any") == true) {
                int i = new Random().nextInt(100) +1;
                if (i >=0 && i < 25 ) {
                    teleport(4,9,41, gp.dungeon);
                }
                else if (i >= 25 && i < 50) {
                    teleport(5,9,41, gp.dungeon);
                }
                else if (i >= 50 && i < 75) {
                    teleport(6,9,41, gp.dungeon);
                }
                else if (i >= 75 && i < 101) {
                    teleport(7,9,41, gp.dungeon);
                }
            } // to the random dungeon
            
            else if (hit(3, 26, 41, "any") == true) {
                int i = new Random().nextInt(100) +1;
                if (i >=0 && i < 25 ) {
                    teleport(4,8,7, gp.dungeon);
                }
                else if (i >= 25 && i < 50) {
                    teleport(5,38,22, gp.dungeon);
                }
                else if (i >= 50 && i < 75) {
                    teleport(6,22,17, gp.dungeon);
                }
                else if (i >= 75 && i < 101) {
                    teleport(7,25,30, gp.dungeon);
                }
            } // from boss to the random dungeon
            
            
            else if (hit(4, 8, 7, "any") == true) {teleport(3,26,41, gp.dungeon);} // to the boss
            else if (hit(5, 38, 22, "any") == true) {teleport(3,26,41, gp.dungeon);} // to the boss
            else if (hit(6, 22, 17, "any") == true) {teleport(3,26,41, gp.dungeon);} // to the boss
            else if (hit(7, 25, 30, "any") == true) {teleport(3,26,41, gp.dungeon);} // to the boss
            //else if (hit(3, 26, 41, "any") == true) {teleport(2,8,7, gp.dungeon);} // to the b2
            
            //cutscene
            else if (hit(3,25,27, "any") == true) {skeletonLord(); meetBoss = true;} //boss
        }
    }
    
    public boolean hit(int map,int col, int row, String reqDirection){
        boolean hit = false;
        
        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false){
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }
    
    public void damagePit(int gameState){
        gp.gameState = gameState;
        eventMaster.startDialogue(eventMaster, 0);
        gp.player.life -= 1;
        canTouchEvent = false;
//        eventRect[col][row].eventDone = true;
    }
    
    public void healingPool(int gameState){
        if (gp.keyH.enterPressed == true) {
            
            gp.gameState = gameState;
            gp.player.attackCanceled = true; 
            eventMaster.startDialogue(eventMaster, 1);
            gp.playSE(2);
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.aSetter.setMonster();
            try {
                gp.saveLoad.save();
            } catch (IOException ex) {
                Logger.getLogger(EventHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            gp.keyH.enterPressed = false;
            setDialogue();
        }
    }
    
    public void teleportNormal(int gameState){
        if (gp.keyH.enterPressed == true) {
            
            gp.gameState = gameState;
            
            eventMaster.startDialogue(eventMaster, 2);
            gp.player.worldX = gp.tileSize*37;
            gp.player.worldY = gp.tileSize*10;
        }  
    }
    public void teleport(int map, int col, int row, int area){
        
        gp.gameState = gp.transitionState;
        gp.nextArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSE(14);
    }
    public void speak(Entity entity){
        if (gp.keyH.enterPressed == true) {
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
        gp.keyH.enterPressed = false;
    }
    public void skeletonLord(){
        
        if (gp.bossBattleOn == false &&  Progress.skeletonLordDefeated == false) {
            gp.gameState = gp.cutsceneState;
            gp.csManager.sceneNum = gp.csManager.skeletonLord;
        }
    }
}
