/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


import ai.PathFinder;
import data.Data;
import data.SaveLoad;
import environment.EnvironmentManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import main.entity.Entity;
import main.entity.Player;
import main.tile.Map;
import main.tile.TileManager;
import tile_interactive.InteractiveTile;
/**
 *
 * @author hp
 */
public class GamePanel extends JPanel implements Runnable{
    
    // screen settings
    final int originalTileSize = 16; //16x16 tiles
    final int scale = 3;
    
    public final int tileSize = originalTileSize *scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    
    //WORLD SETTING
    public  int maxWorldCol = 50;
    public  int maxWorldRow = 50;
    public final int maxMap = 10;
    //change to 3 for boss
    public int currentMap = 0;
    
//    public final int worldWidth = tileSize * maxWorldCol;
//    public final int worldHeight = tileSize * maxWorldRow;
//    public final int worldWidth = tileSize * maxWorldCol;
//    public final int worldHeight = tileSize * maxWorldRow;
    
    //fps
    int FPS = 60;
    //SYSTEM
    public TileManager tileM;
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    public PathFinder pFinder = new PathFinder(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    Map map = new Map(this);
    SaveLoad saveLoad = new SaveLoad(this);
    public Data data = new Data(this);
    public EntityGenerator eGenerator = new EntityGenerator(this);
    public CutsceneManager csManager = new CutsceneManager(this);
    Thread gameThread;
    
    //ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public Entity obj[][] = new Entity[maxMap][80];
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity monster[][] = new Entity[maxMap][80];
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][100];
    public Entity projectile[][] = new Entity[maxMap][20] ;
//    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();
    
    
    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    public final int sleepState = 9;
    public final int mapState = 10;
    public final int cutsceneState = 11;
    
    //OTHERS
    public boolean bossBattleOn = false;
    public boolean rockDone = false;
    
    //FOR FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;
    
    //AREA 
    public int currentArea;
    public int nextArea;
    public final int outside = 50;
    public final int indoor = 51;
    public final int dungeon = 52;
    
    public GamePanel() throws MalformedURLException, FileNotFoundException{
        this.tileM = new TileManager(this);
        this.music = new Sound();
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void setupGame(){
        
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        eManager.setup();

        playMusic(9);
//        stopMusic(); music was played in UI class
        gameState = titleState;
        currentArea = outside;
        
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();
        
        if (fullScreenOn == true) {
            setFullScreen();
        }
        
    }
    public void Error(){
        JOptionPane.showMessageDialog(null, "Name can't be null");
    }
    public String getName(){
        String name = JOptionPane.showInputDialog("Enter your name");
        return name;
    }
    public void resetGame(boolean restart){
        
        currentArea = outside;
        removeTempEntity();
        bossBattleOn = false;
        player.setDefaultPositions();
        player.restoreStatus();
        player.resetCounter();
        aSetter.setNPC();
        aSetter.setMonster();
        player.speed = player.defaultSpeed;
        stopMusic();
        
       
        
        if (restart == true) {
            player.setDefaultValue();
            aSetter.setObject();
            aSetter.setInteractiveTile();
            eManager.lighting.resetDay();
            player.speed = player.defaultSpeed;
            if (gameState == titleState && ui.titleScreenState == 0) {
                playMusic(9);
                keyH.nameInput = false;
            }
            
            //change back to 0 if nak check data
            currentMap = 0;
            
        }
        else{
            playMusic(0);
        }
        
    }
    
    public void setFullScreen(){
        //get local screen device
        GraphicsEnvironment ge  = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Game.window);
        
        //get fullscreen width and height
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		Game.window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		screenWidth2 = (int) width;
		screenHeight2 = (int) height;
                
		
    }
    public void startGameThread () {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
//    public void run (){
//    
//        double drawInterval = 1000000000/FPS; // 0.0166666 sec
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        
//        while(gameThread !=null) {
//            
//            update();
//            
//            repaint();
//            
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1000000;
//                
//                if (remainingTime < 0 ) {
//                    remainingTime = 0;
//                }
//                
//                Thread.sleep((long) remainingTime);
//                
//                nextDrawTime += drawInterval;
//                
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta > 1) {
                update();
                if (fullScreenOn == true) {
                    try {
                        drawToTempScreen(); //draw everything to buffered img
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    drawToScreen(); // draw the buffered img to the screen
                }
                else{
                    repaint();
                }
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println(drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        if (gameState == 1) {
            //PLAYER
            player.update();
            
            //NPC
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            //monster
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
                        monster[currentMap][i].update();
                    }
                    if (monster[currentMap][i].alive == false) {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }
            //projectile
            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    if (projectile[currentMap][i].alive == true ) {
                        projectile[currentMap][i].update();
                        
                    }
                    if (projectile[currentMap][i].alive == false) {
                        projectile[currentMap][i] = null;
                        
                    }
                }
            }
            //particle
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    if (particleList.get(i).alive == true ) {
                        particleList.get(i).update();
                        
                    }
                    if (particleList.get(i).alive == false) {
                        particleList.remove(i);
                        
                    }
                }
            }
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].update();
                }
            }
            eManager.update();
        }
        if (gameState == pauseState) {
            
        }
        
    }
    
        public void drawToTempScreen() throws FileNotFoundException{
        //DEBUG
        g2.clearRect(0, 0, screenWidth2, screenHeight2);
        long drawStart = 0;
        if (keyH.showDebugText == true) {
            drawStart = System.nanoTime();
        }
        
        // Title Screen
        if (gameState  == titleState) {
            ui.draw(g2);
        }
        // Map Screen
        else if(gameState == mapState){
            map.drawFullMapScreen(g2);
        }
        // OTHERS
        else{
            //TILE
            tileM.draw(g2);
            
            //INTERACTIVE TILE
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2); 
                }
            }
            
            //ADD ENTITY TO THE LIST
            entityList.add(player);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    entityList.add(projectile[currentMap][i]);
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
                }
            }
            
            //SORT 
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
//                    throw new UnsupportedOperationException("Not supported yet."); 
                    
                }
            });
            
            //DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            
            //EMPTY ENTITY LIST
            entityList.clear();
            
            //ENVIRONMENT
            eManager.draw(g2);
            
            //MINIMAP
            map.drawMiniMap(g2);
            
            //UI
            ui.draw(g2);
            
            //Cutscene
            csManager.draw(g2);
        }
        //debug
        if (keyH.showDebugText == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeigth = 20;
            
            g2.drawString("worldX " + player.worldX, x, y); y+= lineHeigth;
            g2.drawString("worldY " + player.worldY, x, y); y+= lineHeigth;
            g2.drawString("Col " + (player.worldX + player.solidArea.x) / tileSize, x, y); y+= lineHeigth;
            g2.drawString("Row " + (player.worldY + player.solidArea.y)/tileSize, x, y); y+= lineHeigth;
            
            g2.drawString("Draw time " + passed, x, y);
            
        }
    }
    public void drawToScreen(){
        Graphics g = getGraphics();
	    
        int fullScreenWidth = screenWidth2;
        int fullScreenHeight = screenHeight2;
	    // Calculate the aspect ratio
	    double screenRatio = (double) fullScreenWidth/fullScreenHeight;
	    double gameRatio = (double) screenWidth/screenHeight;

	    // Calculate the size of the black bars
	    int barWidth = 0;
	    if (gameRatio < screenRatio) {
	        barWidth = (fullScreenWidth - (int)(fullScreenHeight * gameRatio)) / 2;
	    }
	    // Draw the black bars
	    g.setColor(Color.BLACK);
	    g.fillRect(0, 0, barWidth, fullScreenHeight); // Left bar
	    g.fillRect(fullScreenWidth - barWidth, 0, barWidth, fullScreenHeight); // Right bar
	    // Draw the game screen
	    g.drawImage(tempScreen, barWidth, 0, fullScreenWidth - 2 * barWidth, fullScreenHeight, null);
	    g.dispose();
        
    }
    
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        //DEBUG
        long drawStart = 0;
        if (keyH.showDebugText == true) {
            drawStart = System.nanoTime();
        }
        
        // Title Screen
        if (gameState  == titleState) {
            try {
                ui.draw(g2);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Map Screen
        else if(gameState == mapState){
            map.drawFullMapScreen(g2);
        }
        // OTHERS
        else{
            //TILE
            tileM.draw(g2);
            
            //INTERACTIVE TILE
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2); 
                }
            }
            
            //ADD ENTITY TO THE LIST
            entityList.add(player);
            
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    entityList.add(projectile[currentMap][i]);
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
                }
            }
            
            //SORT 
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
//                    throw new UnsupportedOperationException("Not supported yet."); 
                    
                }
            });
            
            //DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            
            //EMPTY ENTITY LIST
            entityList.clear();
            
            //ENVIRONMENT
            eManager.draw(g2);
            
            //MINIMAP
            map.drawMiniMap(g2);
            
            try {
                //UI
                ui.draw(g2);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Cutscene
            csManager.draw(g2);
        }
        

        
        //debug
        if (keyH.showDebugText == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeigth = 20;
            
            g2.drawString("worldX " + player.worldX, x, y); y+= lineHeigth;
            g2.drawString("worldY " + player.worldY, x, y); y+= lineHeigth;
            g2.drawString("Col " + (player.worldX + player.solidArea.x) / tileSize, x, y); y+= lineHeigth;
            g2.drawString("Row " + (player.worldY + player.solidArea.y)/tileSize, x, y); y+= lineHeigth;
            
            g2.drawString("Draw time " + passed, x, y);y+= lineHeigth;
            g2.drawString("God Mode: " + keyH.godModeOn, x, y);
            
        }
        
        
        g2.dispose();
    }
    
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    
    public void stopMusic(){
        music.stop();
        
    }
    public void playSE(int i ){
        se.setFile(i);
        se.play();
    }
    public void changeArea(){
        
        if (nextArea != currentArea) {
            stopMusic();
            
            if (nextArea == outside) {
                playMusic(0);
            }
            if (nextArea == indoor) {
                playMusic(19);
            }
            if (nextArea == dungeon) {
                playMusic(20);
            }
            aSetter.setNPC();;
        }
        currentArea = nextArea;
        aSetter.setMonster();
        
    }
    public void removeTempEntity(){
        
        for (int mapNum = 0; mapNum < maxMap; mapNum++) {
            
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[mapNum][i] != null && obj[mapNum][i].temp == true) {
                    obj[mapNum][i] = null;  
                }
            }
        }
    }
}
