/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
/**
 *
 * @author hp
 */
public class Game {
    
    public static JFrame window;

    public static void main(String[] args) throws MalformedURLException, IOException {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Pixel Tiem");
        
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); 
        
        try {
            //test
            System.out.println(gamePanel.data.lastIndexNum());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        gamePanel.config.loadConfig();
        if (gamePanel.fullScreenOn == true) {
            window.setUndecorated(true);
        }
        
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
    public void setIcon(){
        //pls fix this
        String path = "./src/player/boy_down_1.png";
        File f1 = new File(path);
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(path));
        window.setIconImage(icon.getImage());
    }
    
}
