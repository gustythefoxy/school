
package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.entity.Entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;




public class UI {
    
    // 
    GamePanel gp;
    Graphics2D g2;
    public Font maruMonica;
    Font arial_40, arial_80B , purisaB;
    BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
    public boolean messageOn = false;
//    public String message = "";
//    int messageCounter = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum= 0;
    public int titleScreenState = 0; //0 for screen, 1 for 2nd screen etc
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    int subState = 0;
    int counter = 0;
    public Entity npc;
    int charIndex = 0;
    String combinedText = "";
    
    public UI(GamePanel gp){
        this.gp = gp;
        
        FileInputStream fis1;
        try {
            File file1 = new File("src/font/MaruMonica.ttf");
            fis1 = new FileInputStream(file1);
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, fis1);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FontFormatException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            File file1 = new File("src/font/PurisaBold.ttf");
            fis1 = new FileInputStream(file1);
            purisaB = Font.createFont(Font.TRUETYPE_FONT, fis1);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FontFormatException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //CREATE HUD OBJECT
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        Entity crystal = new OBJ_ManaCrystal(gp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;
        Entity bronzeCoin = new OBJ_Coin_Bronze(gp);
        coin = bronzeCoin.down1;
        
        
    }
    
    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }
    //Setup 
    public void draw(Graphics2D g2) throws FileNotFoundException{
        
        this.g2 = g2;
        
        g2.setFont(maruMonica);
//        g2.setFont(purisaB);
//        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);
        
        
        //TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
              
        }
        
        //PLAY STATE 
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawMonsterLife();
            drawMessage();
        }
        //PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
        //CHARACTER STATE
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory(gp.player, true);
            drawPlayerClass();
        }
        //OPTION STATE
        if (gp.gameState == gp.optionState) {
            drawOptionScreen();
        }
        //GAME OVER STATE
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        //TRANSITION STATE
        if (gp.gameState == gp.transitionState) {
            drawTransition();
        }
        //TRADE STATE
        if (gp.gameState == gp.tradeState) {
            drawTradeScreen();
        }
        //SLEEP STATE
        if (gp.gameState == gp.sleepState) {
            drawSleepScreen();
        }
    }
    
    //STATE METHOD IS DOWN HERE
   
    public void drawPlayerLife(){
        
//        gp.player.life = 16;
        
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        int iconSize = 32;
        int manaStartX = (gp.tileSize/2) - 5;
        int manaStartY = 0;
        
        //DRAW BLANK_HEART
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, iconSize, iconSize, null);
            i++;
            x += iconSize;
            
            if (i % 8 == 0) {
                x = gp.tileSize/2;
                y += iconSize;
                
            }
            
        }
        manaStartY = y + iconSize;
        
        //RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        
        //DRAW CURRENT LIFE
        while(i< gp.player.life){
            g2.drawImage(heart_half, x, y, iconSize, iconSize, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, iconSize, iconSize, null);
                i++;
            }
            
            x += iconSize;
            if (i % 16 == 0) {
                x = gp.tileSize/2;
                y += iconSize;
            }
        }
        
       
        
        //DRAW MAX MANA
        x = manaStartX;
        y = manaStartY;
        i = 0;
        while(i < gp.player.maxMana){
            g2.drawImage(crystal_blank, x, manaStartY, iconSize, iconSize, null);
            i++;
            x += iconSize;;
        }
        
        //DRAW MANA
        x = (gp.tileSize/2) - 5;
        y = manaStartY;
        i = 0;
        while(i < gp.player.mana){
            g2.drawImage(crystal_full, x, manaStartY, iconSize, iconSize, null);
            i++;
            x += iconSize;;
        }
        
    }
    public void drawMonsterLife(){
        
        for (int i = 0; i < gp.monster[1].length; i++) {
            
            Entity monster = gp.monster[gp.currentMap][i];
            
            if (monster != null && monster.inCamera() == true) {
                
                if (monster.hpBarOn == true && monster.boss == false) {

                    //hp bar length
                    double oneScale = (double)gp.tileSize/monster.maxLife;
                    double hpBarValue = oneScale*monster.life;

                    g2.setColor(new Color(35,35,35));
                    g2.fillRect(monster.getScreenX()-1, monster.getScreenY()-16, gp.tileSize+2, 12);

                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect(monster.getScreenX(), monster.getScreenY() - 15, (int)hpBarValue, 10);

                    monster.hpBarCounter++;

                    if (monster.hpBarCounter > 600) {
                        monster.hpBarCounter = 0;
                        monster.hpBarOn = false;
                    }
                }
                else if (monster.boss == true) {
                    double oneScale = (double)gp.tileSize*8/monster.maxLife;
                    double hpBarValue = oneScale*monster.life;
                    
                    int x = gp.screenWidth/2 - gp.tileSize*4;
                    int y = gp.tileSize*11;

                    g2.setColor(new Color(35,35,35));
                    g2.fillRect(x-1,y-1, gp.tileSize*8 + 2, 22);

                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect(x, y, (int)hpBarValue, 20);
                    
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
                    g2.setColor(Color.white);
                    g2.drawString(monster.name, x+4, y-10);
                }
            }
        }
    }
    public void drawMessage(){
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        
        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                
                
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);
                
                int counter = messageCounter.get(i)+1; //messageCounter ++ (because this is a list)
                messageCounter.set(i, counter); //set the counter to the array
                messageY += 50;
                
                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i); 
                }
            }
        }
        
    }
    public void drawTitleScreen(){
        
        if (titleScreenState == 0) {
            //BG COLOR (current is black)
            g2.setColor(new Color(0,0,0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
            String text = "PIXEL TIME";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;

            //SHADOW 
            g2.setColor(Color.green);
            g2.drawString(text, x+5, y+5);
            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //BLUE BOY IMAGE    
            x = gp.screenWidth/2 - ((gp.tileSize*2)/2);
            y += gp.tileSize*2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize*3.5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "LEADERBOARD";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
        else if (titleScreenState == 1) {
            
            //CLASS SELECTION SCREEN
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            
            String text = "Select your class";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);
            
            text = "Fighter";
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            
            text = "Mage";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            
            text = "Alchemy";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            
            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
        else if (titleScreenState == 2) {
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(28F));
            
            //fighter
            if (gp.player.characterClass.equals("fighter")) {
                String text = "Health +1";
                int x = gp.tileSize*8;
                int y = gp.tileSize*4;
                g2.drawString(text, x, y);
                text = "this character have higher";
                y += gp.tileSize;
                g2.drawString(text, x, y);
                text = "attack and defend growth";
                y += gp.tileSize;
                g2.drawString(text, x, y);
                text = "And also a bit cooler";
                y += gp.tileSize;
                g2.drawString(text, x, y);
                
                
                
                g2.setFont(g2.getFont().deriveFont(34F));
                text = "Proceed";
                x = gp.tileSize+25;
                y = gp.tileSize*5;
                g2.drawString(text, x, y);
                if (commandNum == 0) {
                    g2.drawString(">", x - gp.tileSize, y);
                }
                
                text = "Back";
                x = gp.tileSize+25;
                y += gp.tileSize*1;
                g2.drawString(text, x, y);
                if (commandNum == 1) {
                    g2.drawString(">", x - gp.tileSize, y);
                }
            }
            
            //mage
            if (gp.player.characterClass.equals("mage")) {
                String text = "Mana +2";
                int x = gp.tileSize*8;
                int y = gp.tileSize*4;
                g2.drawString(text, x, y);
                text = "Health +1";
                y += gp.tileSize;
                g2.drawString(text, x, y);
                text = "This character has the highest";
                y += gp.tileSize;
                g2.drawString(text, x, y);
                text = "magic power growth";
                y += gp.tileSize;
                g2.drawString(text, x, y);
                
                
                
                g2.setFont(g2.getFont().deriveFont(34F));
                text = "Proceed";
                x = gp.tileSize+25;
                y = gp.tileSize*5;
                g2.drawString(text, x, y);
                if (commandNum == 0) {
                    g2.drawString(">", x - gp.tileSize, y);
                }
                
                text = "Back";
                x = gp.tileSize+25;
                y += gp.tileSize*1;
                g2.drawString(text, x, y);
                if (commandNum == 1) {
                    g2.drawString(">", x - gp.tileSize, y);
                }
            }
            
            //alchemy
            if (gp.player.characterClass.equals("alchemist")) {
                String text = "mana +1";
                int x = gp.tileSize*8;
                int y = gp.tileSize*4;
                g2.drawString(text, x, y);
                text = "This character has ballance for";
                y += gp.tileSize;
                g2.drawString(text, x, y);
                text = "multi growth";
                y += gp.tileSize;
                g2.drawString(text, x, y);
                text = "And also a bit cooler";
                y += gp.tileSize;
                g2.drawString(text, x, y);
                
                
                
                g2.setFont(g2.getFont().deriveFont(34F));
                text = "Proceed";
                x = gp.tileSize+25;
                y = gp.tileSize*5;
                g2.drawString(text, x, y);
                if (commandNum == 0) {
                    g2.drawString(">", x - gp.tileSize, y);
                }
                
                text = "Back";
                x = gp.tileSize+25;
                y += gp.tileSize*1;
                g2.drawString(text, x, y);
                if (commandNum == 1) {
                    g2.drawString(">", x - gp.tileSize, y);
                }
            }
            
            
        }
        else if (titleScreenState == 3) {
            
            //NAME SELECTION SCREEN
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            
            String text = "INPUT YOUR NAME";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);
            
            text = gp.player.name;
            x = getXforCenteredText(text);
            y = gp.tileSize*5;
            g2.drawString(text, x, y);
            
            text = "PROCEED";
            x = getXforCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            
            text = "BACK";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            
        }
        else if (titleScreenState == 4) {
             //DIFFICULTY SELECTION SCREEN
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            
            String text = "CHOOSE YOUR DIFFICULTY";
            int x = gp.tileSize+25;
            int y = gp.tileSize*2;
            g2.drawString(text, x, y);
            
            text = "Easy";
            x = gp.tileSize+25;
            y = gp.tileSize*5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            y += gp.tileSize;
            
            text = "Medium";
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            y += gp.tileSize;
            
            text = "Hard";
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            y += gp.tileSize;
            
            text = "Back";
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            y += gp.tileSize;
            
            
            //Description of each difficulty
            x = gp.tileSize*6;
            y = gp.tileSize*5;
            g2.setFont(g2.getFont().deriveFont(32F));
            if (commandNum == 0) {
                
                text = "Easy mode, easy leveling up";
                g2.drawString(text, x, y);
                y += gp.tileSize - 8;
                text = "Monster weaker, score is lower";
                g2.drawString(text, x, y);
                y += gp.tileSize - 8;
                text = "Have fun :D";
                g2.drawString(text, x, y);
                y += gp.tileSize - 8;
                text = "Good for begginer";
                g2.drawString(text, x, y);
                y += gp.tileSize - 8;
                
            }
            if (commandNum == 1) {
                
                text = "Medium mode, normal leveling up";
                g2.drawString(text, x, y);
                y += gp.tileSize - 8;
                text = "Monster not so weak, score is normal";
                g2.drawString(text, x, y);
                y += gp.tileSize - 8;
                text = "Test your skill";
                g2.drawString(text, x, y);
                y += gp.tileSize - 8;
                text = "Not easy";
                g2.drawString(text, x, y);
                y += gp.tileSize - 8;
                
            }
            if (commandNum == 2) {
                x += gp.tileSize;
                g2.setFont(g2.getFont().deriveFont(42F));
                g2.setColor(Color.red);
                text = "HELL";
                g2.drawString(text, x, y);
                y += gp.tileSize;
                text = "YOU ARE COURTING DEATH";
                g2.drawString(text, x, y);
                y += gp.tileSize;
                text = "PREPARE YOURSELF";
                g2.drawString(text, x, y);
                y += gp.tileSize;
                text = "DIE!";
                g2.drawString(text, x, y);
                y += gp.tileSize;
                
            }
            
            
        }
        else if (titleScreenState == 5) {
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            
            
            String tempDif = "";
            switch(gp.player.difficulty){
                case 0: g2.setColor(Color.green); tempDif = "easy"; break;
                case 1: g2.setColor(Color.yellow); tempDif = "medium"; break;
                case 2: g2.setColor(Color.red); tempDif = "HELL"; break;
            }
            String text = "DIFFICULTY: " + tempDif;
            int x = getXforCenteredText(text);
            int y = gp.tileSize*2;
            g2.drawString(text, x, y);
            
            g2.setColor(Color.white);
            if (gp.player.difficulty == gp.player.hard) {
                g2.setColor(Color.red);
            }
            text = "YES";
            x = getXforCenteredText(text);
            y = gp.tileSize*6;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            y += gp.tileSize;
            
            text = "NO";
            x = getXforCenteredText(text);
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            y += gp.tileSize;
        }
        //leaderboard
        
        
        
        
        // a bit of processing 
        else if (titleScreenState == 6) {
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            
            
            String text = "SCORE";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*1;
            g2.drawString(text, x, y);
            
            //top scorer
            y = gp.tileSize*2;
            
            for (int i = 0; i < 5; i++) {
                //name
                x = gp.tileSize*2;
                text = gp.data.player[i];
                g2.drawString((i+1)+". ", x-10, y);
                g2.drawString(text, x+12, y);
                
                
                //score
                x = gp.tileSize*9;
                String text2 = gp.data.scoreText[i];
                g2.drawString(text2, x, y);
                y += gp.tileSize*1;
            }
            
            
            
            
            g2.setColor(Color.white);
            if (gp.player.difficulty == gp.player.hard) {
                g2.setColor(Color.red);
            }
            text = "BACK";
            x = getXforCenteredText(text);
            y = gp.tileSize*11;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            
        }

        
    }
    public void drawPauseScreen(){
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        
        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen(){
    
        //DIALOGUE WINDOW or subwindoww 
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);
        
        //font
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;
        
        if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
            
//            currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];

            char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
            
            if (charIndex < characters.length) {
                
                gp.playSE(18);
                String s = String.valueOf(characters[charIndex]);
                combinedText = combinedText + s;
                currentDialogue = combinedText;
                
                charIndex++;
            }
            
            if (gp.keyH.enterPressed == true) {
                charIndex = 0;
                combinedText = "";
                
                
                if (gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState ) {
                    npc.dialogueIndex++;
                    gp.keyH.enterPressed = false;
                }
            }
        }
        else{ // no text to display, convo over
            npc.dialogueIndex = 0;
            if (gp.gameState == gp.dialogueState) {
                gp.gameState = gp.playState;
            }
            if (gp.gameState == gp.cutsceneState) {
                gp.csManager.scenePhase++;
            }
        }
        
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawCharacterScreen(){
        
        //CREATE A FRAME
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        
        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;
        
        //NAMES
        g2.drawString("Level", textX, textY); textY += lineHeight;
        g2.drawString("Life", textX, textY); textY += lineHeight;
        g2.drawString("Mana", textX, textY); textY += lineHeight;
        g2.drawString("Strength", textX, textY); textY += lineHeight;
        g2.drawString("Dexterity", textX, textY); textY += lineHeight;
        g2.drawString("Attack", textX, textY); textY += lineHeight;
        g2.drawString("Defence", textX, textY); textY += lineHeight;
        g2.drawString("EXP", textX, textY); textY += lineHeight;
        g2.drawString("Next Level", textX, textY); textY += lineHeight;
        g2.drawString("Coin", textX, textY); textY += lineHeight + 10;
        g2.drawString("Weapon", textX, textY); textY += lineHeight + 15;
        g2.drawString("Shield", textX, textY);
        
        
        //VALUES    
        int tailX = (frameX + frameWidth) - 30;
        //reset textY
        textY = frameY + gp.tileSize;
        String value;
        
        value = String.valueOf(gp.player.level);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight; 
        
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight; 
        
        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.strength);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight; 
        
        value = String.valueOf(gp.player.dexterity);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight; 
        
        value = String.valueOf(gp.player.attack);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight; 
        
        value = String.valueOf(gp.player.defense);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight; 
        
        value = String.valueOf(gp.player.exp);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight; 
        
        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight; 
        
        value = String.valueOf(gp.player.coin);
        textX = getXforAllignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight; 
        
        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 24, null);
        textY += gp.tileSize;
        
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 24,null);
        
        
        
    }
    public void drawInventory(Entity entity, boolean cursor){
        
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeigth = 0; 
        int slotCol = 0;
        int slotRow = 0;
        
        if (entity == gp.player) {
            frameX = gp.tileSize*8;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeigth = gp.tileSize*5;  
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }
        else{
            frameX = gp.tileSize*2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeigth = gp.tileSize*5;  
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }
        //FRAME 
        
        drawSubWindow(frameX, frameY, frameWidth, frameHeigth);
        
        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+3;
        
        //DRAW PLAYER ITEM
        for (int i = 0; i < entity.inventory.size(); i++) {
            
            // EQUIP CURSOR
            if (entity.inventory.get(i) == entity.currentWeapon || entity.inventory.get(i) == entity.currentShield || entity.inventory.get(i) == entity.currentLight || entity.inventory.get(i) == entity.currentBoots) {
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            
            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
            
            //DISPLAY AMOUNT
            if (entity == gp.player &&  entity.inventory.get(i).amount > 1) {
                g2.setFont(g2.getFont().deriveFont(32F));
                int amountX;
                int amountY;
                
                String s = "" + entity.inventory.get(i).amount;
                amountX = getXforAllignToRightText(s, slotX + 44);   
                amountY = slotY + gp.tileSize;
                
                //SHADOW
                g2.setColor(new Color(60, 60, 60));
                g2.drawString(s, amountX, amountY);
                //NUMBER
                g2.setColor(Color.white);
                g2.drawString(s, amountX-3, amountY-3);
            }
            
            slotX += slotSize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }
        
        //CURSOR
        if (cursor) {
            int cursorX = slotXstart + (slotSize*slotCol);
            int cursorY = slotYstart + (slotSize*slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeigth = gp.tileSize;

            //DRAW CURSOR
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeigth, 10, 10);

            //Description Frame
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeigth;
            int dFrameWidth = frameWidth;
            int dFrameHeigth = gp.tileSize*3;

            //DRAW DESCRIPTION TEXT
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(28F));

            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

            if (itemIndex < entity.inventory.size()) {

                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeigth);
                for (String line: entity.inventory.get(itemIndex).description.split("\n")) {

                    g2.drawString(line, textX, textY);
                    textY+= 32;
                }

            }
        }
        
        
    }
    public void drawPlayerClass(){
        //CREATE A FRAME
        final int frameX = gp.tileSize*8;
        final int frameY = gp.tileSize*9;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*2 + 12;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(26F));
        
        int textX = frameX + 30;
        int textY = frameY + 34 ;
        final int lineHeight = 35;
        
        //the class
        g2.drawString("class: " + gp.player.characterClass, textX, textY);
        textY += lineHeight - 8;
        
        //the name
        g2.drawString("Name: " + gp.player.name, textX, textY);
        textY += lineHeight - 8;
        
        //the difficulty
        String tempDif = "";
            switch(gp.player.difficulty){
                case 0: g2.setColor(Color.green); tempDif = "easy"; break;
                case 1: g2.setColor(Color.yellow); tempDif = "medium"; break;
                case 2: g2.setColor(Color.red); tempDif = "HELL"; break;
            }
        g2.drawString("Difficulty: " + tempDif, textX, textY);
    }
    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
        
        text = "Game Over";
        
        //shadow text
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        
        //main text
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);
        
        //retry
        g2.setFont(g2.getFont().deriveFont(50F));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-40, y);
        }
        
        //back to title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-40, y);
        }
        
        
    }
    public void drawOptionScreen(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        
        //SUB WINDOW
        int frameX = gp.tileSize*4;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        switch(subState){
            case 0: option_top(frameX, frameY); break;
            case 1: option_fullScreenNotification(frameX, frameY);break;
            case 2: option_control(frameX, frameY); break;
            case 3: option_endGameConfirmation(frameX, frameY); break;
        }    
        
        gp.keyH.enterPressed = false;
    }
    public void option_top(int frameX, int frameY){
        int textX;
        int textY;
        
        //TITLE
        String text = "Option";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        textY += gp.tileSize;
        
        //FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Full Screen", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed == true) {
                if (gp.fullScreenOn == false) {
                    gp.fullScreenOn = true;
                }
                else if (gp.fullScreenOn == true) {
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
            
        }
        
        //MUSIC
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX-25, textY);
        }
        //SE
        textY += gp.tileSize;
        g2.drawString("Sound Effect", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX-25, textY);
        }
        //CONTROL
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 2;
                commandNum = 0;
            }
        }
        //END GAME
        textY += gp.tileSize;
        g2.drawString("Quit", textX, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 3;
                commandNum = 0;
            }
        }
        //BACK
        textY += gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 5) {
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 0;
                gp.gameState = gp.playState;
            }
        }
        
        //FULL SCREEN CHECKBOX
        textX = frameX + (int) (gp.tileSize*4.5);
        textY = frameY + gp.tileSize*2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if (gp.fullScreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);
        }
        
        //MUSIC VOLUME
        textY +=  gp.tileSize;
        g2.drawRect(textX, textY, 120, 24); //120/5  =24
        int volumeWidth = 24 *gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);
        
        //SE VOLUME
        textY +=  gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 *gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);
        
        gp.config.saveConfig();
    }
    public void option_fullScreenNotification(int frameX, int frameY){
        
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;
        
        currentDialogue = "Change will take effect \nafter restarting";
        
        for (String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;    
        }
        
        //BACK 
        textY = frameY + gp.tileSize*6;
        g2.drawString("back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
            }
        }
    }
    public void option_control(int frameX, int frameY){
        int textX;
        int textY;
        
        //TITLE
        String text = "control";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Confirm/Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Shoot/Cast", textX, textY); textY += gp.tileSize;
        g2.drawString("Character Screem", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Option", textX, textY); 
        
        
        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
        g2.drawString("F", textX, textY); textY += gp.tileSize;
        g2.drawString("C", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY); 
        
        //BACK
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize* 9;
        g2.drawString("BACK", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 3;
            }
        }
        
    }
    public void option_endGameConfirmation(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;
        
        currentDialogue = "Quit the gamae and \nreturn to title screen?"; 
        for (String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        
        //YES
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                gp.gameState = gp.titleState;
                gp.resetGame(true);
                gp.stopMusic();
                gp.playMusic(9);
            }
        }
        
        //NO
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 4;
            }
        }
    }
    public void drawTransition() throws FileNotFoundException{
        counter++;
        g2.setColor(new Color(0,0,0,counter*10));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        if (counter == 25) { //transition here
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
            gp.changeArea();
        }
        gp.keyH.rKeyPressed = true;
            if (gp.keyH.rKeyPressed == true) {
                switch(gp.currentMap){
                case 0:gp.tileM.loadMap("src/maps/worldmap.txt", 0);break;
                case 1:gp.tileM.loadMap("src/maps/indoor01.txt", 1);break;
                }
                gp.keyH.rKeyPressed = false;
            }
    }
    public void drawTradeScreen(){
        switch(subState){
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.enterPressed = false;
    }
    public void trade_select(){
        
        npc.dialogueSet = 0;
        
        drawDialogueScreen();
        
        //DRAW WINDOW
        int x = gp.tileSize *11;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = (int)(gp.tileSize * 3.5);  
        drawSubWindow(x,y,width,height);
        
        //DRAW TEXT
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-24, y);
            if (gp.keyH.enterPressed == true) {
                subState = 1;
            }
        }
        y+= gp.tileSize;
        g2.drawString("Sell", x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-24, y);
            if (gp.keyH.enterPressed == true) {
                subState = 2;
            }
        }
        y+= gp.tileSize;
        g2.drawString("Leave", x, y);
        if (commandNum == 2) {
            g2.drawString(">", x-24, y);
            if (gp.keyH.enterPressed == true) {
                commandNum = 0;
                npc.startDialogue(npc, 1);              
            }
        }
        y+= gp.tileSize;
        
    }
    public void trade_buy(){
        // DRAW PLAYER INVENTORY
        drawInventory(gp.player, false);
        
        // DRAW NPC INVENTORY
        drawInventory(npc, true);
        
        // DRAW HINT WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Back", x+24, y+60);
        
        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize*8;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your Coin: " + gp.player.coin, x+24, y+60);
        
        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if (itemIndex < npc.inventory.size()) {
            
            x = (int) (gp.tileSize*5.5);
            y = (int) (gp.tileSize*5.5);
            width = (int) (gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+8, 32, 32, null);
            
            int price = npc.inventory.get(itemIndex).price;
            String text = ""+price;
            x = getXforAllignToRightText(text , gp.tileSize*8-20);
            g2.drawString(text, x, y+32);
            
            //BUY ITEM
            if (gp.keyH.enterPressed == true) {
                if (npc.inventory.get(itemIndex).price > gp.player.coin) {
                    subState = 0;
                    npc.startDialogue(npc, 2);
                    
                }
                else{
                    if (gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {
                        gp.player.coin -= npc.inventory.get(itemIndex).price;
                    }
                    else{
                        subState = 0;
                        npc.startDialogue(npc, 3);
                    }
                }
            }
        }
    }
    public void trade_sell(){
        
        //DRAW PLAYER INVENTORY
        drawInventory(gp.player, true);
        
        
        // DRAW HINT WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Back", x+24, y+60);
        
        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize*8;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your Coin: " + gp.player.coin, x+24, y+60);
        
        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if (itemIndex < gp.player.inventory.size()) {
            
            x = (int) (gp.tileSize*11.5);
            y = (int) (gp.tileSize*5.5);
            width = (int) (gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+8, 32, 32, null);
            
            int price = (int)(gp.player.inventory.get(itemIndex).price /1.5);
            String text = ""+price;
            x = getXforAllignToRightText(text , gp.tileSize*14-20);
            g2.drawString(text, x, y+32);
            
            //SELL  ITEM 
            if (gp.keyH.enterPressed == true) {
                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon || gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
                    commandNum = 0;
                    subState = 0;
                    npc.startDialogue(npc, 4);
                }
                else {
                    //make sure to add a confirmation dialogue box later
                    if (gp.player.inventory.get(itemIndex).amount > 1) {
                        gp.player.inventory.get(itemIndex).amount--;
                    }
                    else{
                        gp.player.inventory.remove(itemIndex);
                    }
                    
                    gp.player.coin += price;
                }
            }
        }
        
    }
    public void drawSleepScreen(){
        
        counter++;
        
        if (counter < 120) {
            gp.eManager.lighting.filterAlpha += 0.01f; 
            if (gp.eManager.lighting.filterAlpha > 1f) {
                gp.eManager.lighting.filterAlpha = 1f;
            }
        }
        if (counter >= 120) {
            gp.eManager.lighting.filterAlpha -= 0.01f;
            if (gp.eManager.lighting.filterAlpha <= 0) {
                gp.eManager.lighting.filterAlpha = 0f;
                counter = 0;
                gp.eManager.lighting.dayState = gp.eManager.lighting.day; 
                gp.eManager.lighting.dayCounter = 0;
                gp.gameState = gp.playState;
                gp.player.getImage();
            }
        }
    }
    public int getItemIndexOnSlot(int slotCol, int slotRow){
        int itemIndex = slotCol+(slotRow*5);
        return itemIndex;
    }
    public void drawSubWindow(int x, int y, int width, int height){
        
        Color c = new Color(0, 0, 0, 220);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public int getXforAllignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
    
}
