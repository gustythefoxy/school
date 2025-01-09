/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile_interactive;

import main.GamePanel;



public class IT_MetalPlate extends InteractiveTile{
    
    public static final String itName = "Metal Plate";
    
    public IT_MetalPlate(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        
        this.worldX = gp.tileSize*col;
        this.worldY = gp.tileSize*row;
        
        name = itName;
        down1 = setup("interactives/metalplate", gp.tileSize, gp.tileSize);
        
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
    }
    
}
