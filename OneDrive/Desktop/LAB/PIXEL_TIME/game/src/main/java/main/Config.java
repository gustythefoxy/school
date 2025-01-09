
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Config {
    GamePanel gp;
    
    public Config(GamePanel gp){
        this.gp = gp;
    }
    
    public void saveConfig(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            //full screen setting
            if (gp.fullScreenOn == true) {
                bw.write("On");
            }
            if (gp.fullScreenOn == false) {
                bw.write("Off");
            }
            bw.newLine();
            
            //MUSIC VOLUME  
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();
            
            //SE VOLUME 
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();
            
            bw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadConfig() throws IOException{
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            
            String s = br.readLine();
            
            //FULL SCREEN
            if (s.equals("On")) {
                gp.fullScreenOn = true;
            }
            if (s.equals("Off")) {
                gp.fullScreenOn = false;
            }
            
            //MUSIC VOLUME
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);
            
            //SE VOLUME
            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);
            
            br.close();
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
