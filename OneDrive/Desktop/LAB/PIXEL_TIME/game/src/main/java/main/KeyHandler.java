/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class KeyHandler implements KeyListener{
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed, rKeyPressed, spacePressed;
    //debug
    boolean showDebugText = false;
    public boolean godModeOn = false;
    public boolean nameInput = false;
    public double time = 0;
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    //state driver
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        //TITLE STATE
        if (gp.gameState == gp.titleState) {
            titleState(code);
        }
        //PLAY STATE
        else if (gp.gameState == gp.playState) {
            //timer
            time++;
            
            try {
                playState(code);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(KeyHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        //DIALOGUE SYAYE
        else if (gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState) {
            dialogueState(code);
        }
        //CHARACTER STATE   
        else if (gp.gameState == gp.characterState) {
            characterState(code);
        }
        //OPTIOM STATE
        else if (gp.gameState == gp.optionState) {
            optionState(code);
        }
         //GAME OVER STATE
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
         //TRADE STATE
        else if (gp.gameState == gp.tradeState) {
            tradeState(code);
        }
        //MAP STATE
        else if (gp.gameState == gp.mapState) {
            mapState(code);
        }
        
    }
    
    public void titleState(int code){
        if (gp.ui.titleScreenState == 0) {
                
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        gp.ui.titleScreenState = 1;
                        
                        
                    }
                    if (gp.ui.commandNum == 1) {
//                        gp.ui.titleScreenState = 3;
                        gp.saveLoad.load();
                        gp.gameState = gp.playState;
                        gp.stopMusic();
                        gp.playMusic(0);
                    }
                    if (gp.ui.commandNum == 2) {
                        gp.ui.titleScreenState = 6;
                        gp.ui.commandNum = 0;
                    }
                    if (gp.ui.commandNum == 3) {
                        System.exit(0);
                    }
                }
            }
            
            else if (gp.ui.titleScreenState == 1) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        System.out.println("do some fighting");
                        gp.player.characterClass = "fighter";
                        gp.ui.titleScreenState = 2;                       
                    }
                    if (gp.ui.commandNum == 1) {
                        System.out.println("stay at home, dies");
                        gp.player.characterClass = "mage";
                        gp.ui.titleScreenState = 2;      
                    }
                    if (gp.ui.commandNum == 2) {
                        System.out.println("cool alchemist");
                        gp.player.characterClass = "alchemist";
                        gp.ui.titleScreenState = 2;      
                    }
                    if (gp.ui.commandNum == 3) {
                        gp.ui.titleScreenState = 0;
                    }
                    if (gp.ui.titleScreenState == 2) {
                        gp.ui.commandNum = 0;
                    }
                    
                    
                }
            }
            //phase 2
            else if (gp.ui.titleScreenState == 2) {
                
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 1;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 1) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                    gp.ui.titleScreenState = 3;
                    }
                    if (gp.ui.commandNum == 1) {
                        gp.ui.titleScreenState = 1;
                        gp.ui.commandNum = 0;
                    }
                   
                }
        }
            //phase 3
            else if (gp.ui.titleScreenState == 3) {
                
                    if (nameInput == false) {
                    gp.player.name = gp.getName();
                    nameInput = true;
                    gp.ui.commandNum = 2;
                        
                
                
            }
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 1;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 1) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        
                        //gp.resetGame(true);
                        
                        
                        //gp.gameState = gp.playState;
                        //gp.playMusic(0);
                        gp.ui.commandNum = 0;
                        gp.ui.titleScreenState = 4;
                    }
                    if (gp.ui.commandNum == 1) {
                        gp.ui.titleScreenState = 2;
                        gp.player.name = null;
                        nameInput = false;
                    }
            }
        }
            //phase 4
            else if (gp.ui.titleScreenState == 4) {
            if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        gp.player.difficulty = gp.player.easy;
                        gp.ui.titleScreenState = 5;
                    }
                    if (gp.ui.commandNum == 1) {
                        gp.player.difficulty = gp.player.medium;
                        gp.ui.titleScreenState = 5;
                    }
                    if (gp.ui.commandNum == 2) {
                        gp.player.difficulty = gp.player.hard;
                        gp.ui.titleScreenState = 5;
                    }
                    if (gp.ui.commandNum == 3) {
                        gp.ui.titleScreenState = 3;
                        gp.ui.commandNum = 2;
                    }
                    if (gp.ui.titleScreenState == 5) {
                        gp.ui.commandNum = 0;
                    }
            }
        }
            else if (gp.ui.titleScreenState == 5) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 1;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 1) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        gp.resetGame(true);                     
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                        gp.ui.titleScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                    if (gp.ui.commandNum == 1) {
                        gp.ui.titleScreenState = 4;
                        gp.ui.commandNum = 0;
                    }
            }
        }
            else if (gp.ui.titleScreenState == 6) {
            if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {                    
                        gp.ui.titleScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
            }
        }
 {
            
        }
    }
    public void playState (int code) throws FileNotFoundException{
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionState;
        }
        if (code == KeyEvent.VK_M) {
            gp.gameState = gp.mapState;
        }
        if (code == KeyEvent.VK_X) {
            if (gp.map.miniMapOn == false) {
                gp.map.miniMapOn = true;
            }
            else{
                gp.map.miniMapOn = false;
            }
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }


        //debug
        if (code == KeyEvent.VK_T) {
            if (showDebugText == false) {
                showDebugText = true;
            }
            else if (showDebugText == true) {
                showDebugText = false;
            }
        }
        if (code == KeyEvent.VK_G) {
            if (godModeOn == false) {
                godModeOn = true;
            }
            else if (godModeOn == true) {
                godModeOn = false;
            }
        }
        
        //entering new map bug
        
        if (code == KeyEvent.VK_R) {
            rKeyPressed = true;
            if (rKeyPressed == true) {
                switch(gp.currentMap){
                case 0:gp.tileM.loadMap("src/maps/worldmap.txt", 0);break;
                case 1:gp.tileM.loadMap("src/maps/indoor01.txt", 1);break;
                
                }
                rKeyPressed = false;
            }
            
                
        }
    }
    public void pauseState (int code){
        if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
                
        }
    }
    public void dialogueState (int code){
        if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
    }
    public void characterState (int code){
        if (code == KeyEvent.VK_C || code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
        playerInventory(code);
    }
    public void optionState (int code){
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        int maxCommandNum = 0;
        switch(gp.ui.subState){
            case 0: maxCommandNum = 5;break;
            case 3: maxCommandNum = 1;break;
        }
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            gp.playSE(10);
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            gp.playSE(10);
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(10);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    gp.playSE(10);
                }
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(10);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++ ;
                    gp.playSE(10);
                }
            }
        }
    }
    public void gameOverState (int code){
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum <  0) {
                gp.ui.commandNum = 1;
            }
            gp.playSE(10);
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum >  1) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(10);
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.resetGame(false);
                
            }
            else if (gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }
        
    }
    public void tradeState(int code){
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (gp.ui.subState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
                gp.playSE(10);
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
                gp.playSE(10);
            }
        }
        if (gp.ui.subState == 1) {
            npcInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
            }
        }
        if (gp.ui.subState == 2) {
            playerInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
            }
        }
    }
    public void mapState(int code){
        if (code == KeyEvent.VK_M) {
            gp.gameState = gp.playState;
        }
    }
    public void playerInventory(int code){
        
        if (code == KeyEvent.VK_W) {
            if (gp.ui.playerSlotRow !=  0) {
                gp.ui.playerSlotRow--;
                gp.playSE(10);
            }
            
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.playerSlotCol != 0) {
                gp.ui.playerSlotCol--;
                gp.playSE(10); 
            }
            
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.playerSlotRow != 3) {
                gp.ui.playerSlotRow++;
                gp.playSE(10); 
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.playerSlotCol != 4) {
                gp.ui.playerSlotCol++;
                gp.playSE(10); 
            }
        }
    }
    public void npcInventory(int code){
        
        if (code == KeyEvent.VK_W) {
            if (gp.ui.npcSlotRow !=  0) {
                gp.ui.npcSlotRow--;
                gp.playSE(10);
            }
            
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.npcSlotCol != 0) {
                gp.ui.npcSlotCol--;
                gp.playSE(10); 
            }
            
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.npcSlotRow != 3) {
                gp.ui.npcSlotRow++;
                gp.playSE(10); 
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.npcSlotCol != 4) {
                gp.ui.npcSlotCol++;
                gp.playSE(10); 
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }
    
}
