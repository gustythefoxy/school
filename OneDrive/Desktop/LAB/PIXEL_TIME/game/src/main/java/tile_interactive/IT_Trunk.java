/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile_interactive;

import main.GamePanel;


public class IT_Trunk extends InteractiveTile{
    
    GamePanel gp;
    
    public IT_Trunk(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        
        down1 = setup("interactives/trunk", gp.tileSize, gp.tileSize);
        
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
    }
}
