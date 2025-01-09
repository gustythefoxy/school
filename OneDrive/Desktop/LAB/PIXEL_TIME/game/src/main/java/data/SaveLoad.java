
package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import main.GamePanel;
import main.entity.Entity;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;

public class SaveLoad {
    GamePanel gp;
    
    public SaveLoad(GamePanel gp){
        this.gp = gp;
    }
    
    public void save() throws IOException{
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            DataStorage ds = new DataStorage();
            
            ds.level = gp.player.level;
            ds.life = gp.player.life;
            ds.maxLife = gp.player.maxLife;
            ds.mana = gp.player.mana;
            ds.maxMana = gp.player.maxMana;
            ds.strenght = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;
            ds.score = gp.player.score;
            ds.names = gp.player.name;
            ds.className = gp.player.characterClass;
            //Player inventory
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amount);
            }
            //PLAYER EQUIPMENT
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldSlot();
            //OBJECT ON MAP
            ds.mapObjectName = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];
            
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                
                for (int i = 0; i < gp.obj[1].length; i++) {
                    if (gp.obj[mapNum][i] == null) {
                        ds.mapObjectName[mapNum][i] = "NA";
                    }
                    else{
                        ds.mapObjectName[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX; 
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY; 
                        if (gp.obj[mapNum][i].loot != null) {
                            ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name; 
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
                    }
                }
            }
            
            //Write the DataStorage object
            oos.writeObject(ds);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Save Exceeption");
        }
    }
    public void load(){
        
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
            
            //Read the datastorage
            DataStorage ds = (DataStorage) ois.readObject();
            
            gp.player.level = ds.level;
            gp.player.life = ds.life;
            gp.player.maxLife = ds.maxLife;
            gp.player.mana = ds.mana;
            gp.player.maxMana = ds.maxMana;
            gp.player.strength = ds.strenght;
            gp.player.dexterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.coin = ds.coin;
            gp.player.score = ds.score;
            gp.player.name = ds.names;
            gp.player.characterClass = ds.className;
            
            //Player inventory
            gp.player.inventory.clear();
            for (int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(gp.eGenerator.getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
            }
            
            //PLAYER EQUIPMENT
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getAttackImage();
            
            //OBJECT ON MAP
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.obj[1].length ; i++) {
                    if (ds.mapObjectName[mapNum][i].equals("NA")) {
                        gp.obj[mapNum][i] = null;
                    }
                    else{ 
                        gp.obj[mapNum][i] = gp.eGenerator.getObject(ds.mapObjectName[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        if (ds.mapObjectLootNames[mapNum][i] != null) {
                            gp.obj[mapNum][i].setLoot(gp.eGenerator.getObject(ds.mapObjectLootNames[mapNum][i]));
                        }
                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if (gp.obj[mapNum][i].opened == true) {
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                        }
                    }
                }
            }

        } catch(Exception e){
            System.out.println("Load Exception");
        }
    }
}
