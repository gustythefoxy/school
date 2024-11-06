/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
/**
 *
 * @author hp
 */
public class GamePanel extends JPanel{
    
    // screen settings
    final int originalTileSize = 16; //16x16 tiles
    final int scale = 3;
    
    final int tileSize = originalTileSize *scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    
    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
}
