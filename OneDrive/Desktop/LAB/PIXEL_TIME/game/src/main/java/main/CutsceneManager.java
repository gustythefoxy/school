/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.entity.PlayerDummy;
import main.monster.MON_SkeletonLord;
import object.OBJ_BlueHeart;
import object.OBJ_Door_Iron;


public class CutsceneManager {
    
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y;
    String[] endCredit = new String[3];
    
    //  scene number
    public final int NA = 0;
    public final int skeletonLord = 1;
    public final int ending = 2;
    
    public CutsceneManager(GamePanel gp){
        this.gp = gp;
        endCredit[0] = "CONGRATULATION CONGARTULAION X20";
        endCredit[1] = "from RYISNOW 2D RPG GAME JAVA tutorial \n" 
                + "Code \n" + "Ryisnow\n" + "thanks you all \n\n\n\n" + "thanks you mom and dad \n";
        
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        switch(sceneNum){
            case skeletonLord: scene_skeletonLord(); break;
            case ending: scene_ending(); break;
        }
        
    }
    public void scene_skeletonLord(){
        if (scenePhase == 0) {
            gp.stopMusic();
            gp.playMusic(23);
            gp.bossBattleOn = true;
            //shut down the iron door
            for (int i = 0; i < gp.obj[1].length; i++) {
                
                if (gp.obj[gp.currentMap][i] == null) {
                    gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize*25;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize*28;
                    gp.obj[gp.currentMap][i].temp = true;
                    gp.playSE(22);
                    break;
                }
            }
            //Search vacant slot
            for (int i = 0; i < gp.npc[1].length; i++) {
                if (gp.npc[gp.currentMap][i] == null) {
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }
            
            gp.player.drawing = false;
            scenePhase++;
        }
        if (scenePhase == 1) {
            gp.player.worldY -=2;
            
            if (gp.player.worldY < gp.tileSize*18) {
                scenePhase++;
            }
        }
        if (scenePhase == 2) {
            
            //Search the boss
            for (int i = 0; i < gp.monster[1].length; i++) {
                
                if (gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].name ==  MON_SkeletonLord.monName) {
                    
                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }
        if (scenePhase == 3) {
            //the boss speak
            gp.ui.drawDialogueScreen();
        }
        if (scenePhase == 4) {
            //return camera to the player
            
            for (int i = 0; i < gp.npc[1].length; i++) {
                if (gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
                    
                    //restore the player position
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                    
                    //delete the dummy
                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }
            
            //start drawing the player again
            gp.player.drawing = true;
            
            // RESET
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;
            
            //change the music
            
        }
        
    }
    public void scene_ending(){
        
        if (scenePhase == 0) {
            gp.stopMusic();
            gp.ui.npc = new OBJ_BlueHeart(gp);
            //saving
            try {             
                gp.data.insertData(gp.player.name, gp.player.getScore(), gp.data.lastIndexNum());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CutsceneManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            scenePhase++;
        }
        if (scenePhase == 1) {
            
            //Display dialogue
            gp.ui.drawDialogueScreen();
            
        }
        if (scenePhase == 2) {
            
            //play the fanfare
            gp.playSE(4);
            scenePhase++;
        }
        if (scenePhase == 3) {
            //wait until the same sound effect
            if (counterReached(300) == true) {
                scenePhase++;
            }
            
        }
        if (scenePhase == 4) {
            //the screen gets darker
            alpha += 0.005f;
            if (alpha > 1f) {
                alpha = 1f;
            }
            drawBlackBackground(alpha);
            
            if (alpha == 1f) {
                alpha = 0;
                scenePhase++;
            }
        }
        if (scenePhase == 5) {
            drawBlackBackground(1f);
            
            alpha += 0.005f;
            if (alpha > 1f) {
                alpha = 1f;
            }
            
            String text = "Congratulation " + gp.player.name + ",\n" + "you are my sunshine.\n" + "live well,\n";
            drawString(alpha, 38f, 200, text, 70);
            
            if (counterReached(600) == true) {
                gp.playMusic(9);
                scenePhase++;
            }
            
        }
        if (scenePhase == 6) {
            drawBlackBackground(1f);
            y =  gp.screenHeight/2;
            drawString(1f, 120f, y,  "Pixel Time", 40);
            y+= gp.tileSize*3;
            drawString(1f, 40f, y,  "Your score is " + gp.player.getScore(), 40);
            if (counterReached(480) == true) {
                scenePhase++;
            }    
        }
        if (scenePhase ==7) {
            drawBlackBackground(1f);
            
            drawString(1f, 38f, gp.screenHeight/2, endCredit[0], 40);
            
            if (counterReached(480) == true) {
                scenePhase++;
            } 
        }
        if (scenePhase == 8) {
            
            //scrolling credit
            drawBlackBackground(1f);
            y--;
            drawString(1f,38f, y, endCredit[1], 40);
            try {
                gp.saveLoad.save();
            } catch (IOException ex) {
                Logger.getLogger(CutsceneManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (counterReached(600) == true) {
                scenePhase++;
            } 
            
        }
        if (scenePhase == 9) {
            System.exit(0);
        }
    }
    public boolean counterReached(int target){
        boolean counterReached = false;
        
        counter++;
        if (counter > target) {
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }
    public void drawBlackBackground(float alpha){
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
    }
    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));
        
        for (String line: text.split("\n")) {
            int x = gp.ui.getXforCenteredText(line);
            g2.drawString(line, x, y);  
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
