/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import javax.swing.JFrame;
/**
 *
 * @author hp
 */
public class Game {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Pixel Tiem");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); 
        
        window.setLocation(null);
        window.setVisible(true);
        
    }
}
