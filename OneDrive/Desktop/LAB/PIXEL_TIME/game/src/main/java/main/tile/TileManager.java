
package main.tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UltilityTool;


public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    boolean drawPath = true;
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();
    
    
    public TileManager(GamePanel gp) throws FileNotFoundException{
        
        this.gp = gp;
        
        // READ TILE DATAFILE
         File file = new File("src/maps/tiledata.txt");
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        
        //GETTING TILE NAMES AND COLLISION INFO FROM FILES
        String line;
        
        try{
            while((line =br.readLine()) != null){
                fileNames.add(line);
                collisionStatus.add(br.readLine());
                
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
            
        }
        //INITIALIZE THE TILE ARRAY
        tile = new Tile[fileNames.size()];
        getTileImage();
        
        //GET THE maxWorldCol and Row
        file = new File("src/maps/worldmap.txt");
        fis = new FileInputStream(file);
        br = new BufferedReader(new InputStreamReader(fis));
        
        try{
            String line2 = br.readLine();
            String maxTile[] = line2.split(" ");
            gp.maxWorldCol = maxTile.length;
            gp.maxWorldRow = maxTile.length;
            mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
            
            br.close();
            
        }catch(IOException e){
            
        }
        
        loadMap("src/maps/worldmap.txt", 0);
        loadMap("src/maps/indoor01.txt", 1);
        loadMap("src/maps/dungeon01.txt", 2);
        loadMap("src/maps/dungeon02.txt", 3); //boss room
        loadMap("src/maps/dungeon03.txt", 4);
        loadMap("src/maps/dungeon04.txt", 5);
        loadMap("src/maps/dungeon05.txt", 6);
        loadMap("src/maps/dungeon06.txt", 7);
//        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
//        loadMap("src/maps/interior1.txt", 1);
//        loadMap("src/maps/worldV3.txt", 0);
        
    }
    //problem starts 
    
    public void getTileImage(){
        for (int i = 0; i < fileNames.size(); i++) {
            
            String fileName;
            boolean collision;
            
            //Get the file  name
            fileName = fileNames.get(i);
            
            //Get the collision status
            if (collisionStatus.get(i).equals("true")){
                collision = true;
            }
            else{
                collision = false;
            }
            setup(i, fileName, collision);
        }
            
    }   
    
    public void setup(int index, String imageName, boolean collision){
        
        UltilityTool uTool = new UltilityTool();
        
        try{
            tile[index] = new Tile();
            File file = new File("src/tiles/" + imageName );
            
            FileInputStream fis = new FileInputStream(file);
            tile[index].image = ImageIO.read(fis);
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision; 
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadMap(String filePath, int map){
        try{
            System.out.println("loading map " +  map + " and currently in map " + gp.currentMap);
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                
                while(col < gp.maxWorldCol ){
                    
                    String numbers[] = line.split("\\s+");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row] = num;
                    col++;   
//                    System.out.println(num);
                }
                if (col == gp.maxWorldCol ) {
                    
                    col = 0;
                    row += 1;
                    
                }
                
                
            }
            br.close();
        }catch(IOException e){
            
        }
        
    }
    
    
    public void draw(Graphics2D g2){
        
        int worldCol = 0;
        int worldRow = 0;
 
        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize> gp.player.worldY - gp.player.screenY && worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            
            
            worldCol++; 
      
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
        
        //to locate the location of the path
//        if (drawPath == true) {
//            g2.setColor(new Color(255, 0, 0, 70));
//            
//            for (int i = 0; i < gp.pFinder.pathList.size(); i++) {
//                int worldX = gp.pFinder.pathList.get(i).col *gp.tileSize;
//                int worldY = gp.pFinder.pathList.get(i).row *gp.tileSize;
//                int screenX = worldX - gp.player.worldX + gp.player.screenX;
//                int screenY = worldY - gp.player.worldY + gp.player.screenY;
//                
//                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
//            }
//        }
    }
}
