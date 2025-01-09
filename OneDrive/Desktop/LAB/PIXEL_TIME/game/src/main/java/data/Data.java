/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Game;
import main.GamePanel;
import main.UI;




public class Data {
    GamePanel gp;
    public String[] player = new String[5];
    public int[] score = new int[5];
    public String[] scoreText = new String[6];
    
    
    public Data(GamePanel gp) {
        this.gp = gp;
        boolean mf = true;
        
        for (int i = 0; i < 5; i++) {
            player[i] = "";
            scoreText[i] = "";
            score[i] = 0;
        }
        
        
        if (mf == true) {
            for (int i = 1; i < 6; i++) {  
                
                try {
                    if (topScoreName(i) != null && topScore(i) != 0) {

                            //name
                            player[i-1] = topScoreName(i);

                            //score
                            score[i-1] = topScore(i);
                            scoreText[i-1] = Integer.toString(score[i-1]);              

                    }
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
                }

                    
            }
            mf = false;
        }
    }
    
    public void sort() throws ClassNotFoundException{
        
        
        String url, username, password, sql ;
        sql = "ALTER TABLE Score ORDER BY Score ASC;";

        url = "jdbc:postgresql://localhost:5432/Score";
        username = "postgres";
        password = "darwisy05";
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            int rs = st.executeUpdate(sql);
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public int lastIndexNum() throws ClassNotFoundException{
        int n = 0;
        
        String url, username, password, sql ;
        sql = "SELECT id FROM Score\n" +
            "ORDER BY id DESC\n" +
            "LIMIT 1;";

        url = "jdbc:postgresql://localhost:5432/Score";
        username = "postgres";
        password = "darwisy05";
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);  
            if (rs.next() == true) {
                n = rs.getInt(1);
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    
    public void insertData(String name, int score, int n) throws ClassNotFoundException{
        
        
        String url, username, password, sql ;
        sql = "INSERT INTO Score (playerName, Score, id)\n" +
                "VALUES('" + name +"'," + score + "," + (n+1) +")";

        url = "jdbc:postgresql://localhost:5432/Score";
        username = "postgres";
        password = "darwisy05";
        
        try {
            
            
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            int rs = st.executeUpdate(sql);  

            
            
        } catch (SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public String topScoreName(int n) throws ClassNotFoundException{
        String url, username, password, sql, name ;
        sql = "SELECT playerName FROM Score  ORDER BY Score DESC LIMIT 1 OFFSET " + (n-1);

        url = "jdbc:postgresql://localhost:5432/Score";
        username = "postgres";
        password = "darwisy05";
        name = "";
        try {
            
            
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);  
            if (rs.next()) {
                name = rs.getString(1);
            }
            
            

            
            
        } catch (SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }
    public int topScore(int n) throws ClassNotFoundException{
        String url, username, password, sql ;
        sql = "SELECT Score FROM Score  ORDER BY Score DESC LIMIT 1 OFFSET " + (n-1);

        url = "jdbc:postgresql://localhost:5432/Score";
        username = "postgres";
        password = "darwisy05";
        int score = 0;
        try {
            
            
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);  
            if (rs.next()) {
                score = rs.getInt(1);
            }
            
            
            

            
            
        } catch (SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        return score;
    }
}
