
package main;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;
    
    public Sound() throws MalformedURLException {
        String test1 = "src/sound/BlueBoyAdventure.wav";
        soundURL[0]  = new File(test1).toURI().toURL();
        
        String test2 = "src/sound/coin.wav";
        soundURL[1]  = new File(test2).toURI().toURL();
        
        String test3 = "src/sound/powerup.wav";
        soundURL[2]  = new File(test3).toURI().toURL();
        
        String test4 = "src/sound/unlock.wav";
        soundURL[3]  = new File(test4).toURI().toURL();
        
        String test5 = "src/sound/fanfare.wav";
        soundURL[4]  = new File(test5).toURI().toURL();
        
        String test6 = "src/sound/hitmonster.wav";
        soundURL[5]  = new File(test6).toURI().toURL();
        
        String test7 = "src/sound/receivedamage.wav";
        soundURL[6]  = new File(test7).toURI().toURL();
        
        String test8 = "src/sound/swingweapon.wav";
        soundURL[7]  = new File(test8).toURI().toURL();
        
        String test9 = "src/sound/levelup1.wav";
        soundURL[8]  = new File(test9).toURI().toURL();
        
        String test10 = "src/sound/pixel_time.wav";
        soundURL[9]  = new File(test10).toURI().toURL();
        
        String test11 = "src/sound/cursor.wav";
        soundURL[10]  = new File(test11).toURI().toURL();
        
        String test12 = "src/sound/burning.wav";
        soundURL[11]  = new File(test12).toURI().toURL();
        
        String test13 = "src/sound/cuttree.wav";
        soundURL[12]  = new File(test13).toURI().toURL();
        
        String test14 = "src/sound/gameover.wav";
        soundURL[13]  = new File(test14).toURI().toURL();
        
        String test15 = "src/sound/stairs.wav";
        soundURL[14]  = new File(test15).toURI().toURL();
        
        String test16 = "src/sound/sleep.wav";
        soundURL[15]  = new File(test16).toURI().toURL();
        
        String test17 = "src/sound/blocked.wav";
        soundURL[16]  = new File(test17).toURI().toURL();
        
        String test18 = "src/sound/parry.wav";
        soundURL[17]  = new File(test18).toURI().toURL();
        
        String test19 = "src/sound/speak.wav";
        soundURL[18]  = new File(test19).toURI().toURL();
        
        String test20 = "src/sound/Merchant.wav";
        soundURL[19]  = new File(test20).toURI().toURL();
        
        String test21 = "src/sound/Dungeon.wav";
        soundURL[20]  = new File(test21).toURI().toURL();
        
        String test22 = "src/sound/chipwall.wav";
        soundURL[21]  = new File(test22).toURI().toURL();
        
        String test23 = "src/sound/dooropen.wav";
        soundURL[22]  = new File(test23).toURI().toURL();
        
        String test24 = "src/sound/kitsunebi.wav";
        soundURL[23]  = new File(test24).toURI().toURL();
    }
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
            
        }catch(Exception e){
        
        }
        
    }
    public void play(){
        clip.start();
        
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    
    }
    public void stop(){
        clip.stop();
    }
    public void checkVolume(){
        switch(volumeScale){
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
    }
}
