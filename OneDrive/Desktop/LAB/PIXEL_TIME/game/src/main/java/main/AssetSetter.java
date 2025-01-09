package main;

import data.Progress;
import main.entity.NPC_BigRock;
import main.entity.NPC_Merchant;
import main.entity.NPC_Oldman;
import main.monster.MON_Bat;
import main.monster.MON_GreenSlime;
import main.monster.MON_Orc;
import main.monster.MON_RedSlime;
import main.monster.MON_SkeletonLord;
import object.OBJ_Axe;
import object.OBJ_BlueHeart;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door;
import object.OBJ_Door_Iron;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_ManaCrystal;
import object.OBJ_Pickaxe;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Tent;
import tile_interactive.IT_DestructibleWall;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_MetalPlate;

public class AssetSetter {
        GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*25;
        gp.obj[mapNum][i].worldY = gp.tileSize*19;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Key(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*28;
        gp.obj[mapNum][i].worldY = gp.tileSize*16;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Tent(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*17;
        gp.obj[mapNum][i].worldY = gp.tileSize*20;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*16;
        gp.obj[mapNum][i].worldY = gp.tileSize*20;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*14;
        gp.obj[mapNum][i].worldY = gp.tileSize*28;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*12;
        gp.obj[mapNum][i].worldY = gp.tileSize*12;
        i++;
        
        
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*22;
        gp.obj[mapNum][i].worldY = gp.tileSize*27;
        i++;
       
        gp.obj[mapNum][i] = new OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*22;
        gp.obj[mapNum][i].worldY = gp.tileSize*29;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*22;
        gp.obj[mapNum][i].worldY = gp.tileSize*30;
        i++;
        
        mapNum = 1;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*12;
        gp.obj[mapNum][i].worldY = gp.tileSize*13 + 10;
        i++;
        
        mapNum = 2;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Pickaxe(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*40;
        gp.obj[mapNum][i].worldY = gp.tileSize*41;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*13;
        gp.obj[mapNum][i].worldY = gp.tileSize*16;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*26;
        gp.obj[mapNum][i].worldY = gp.tileSize*34;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*27;
        gp.obj[mapNum][i].worldY = gp.tileSize*15;
        i++;
        
        if (gp.rockDone == false) {
            gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
            gp.obj[mapNum][i].worldX = gp.tileSize*18;
            gp.obj[mapNum][i].worldY = gp.tileSize*23;
            i++;
        }
        
        
        mapNum = 3;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Lantern(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*21;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*25;
        gp.obj[mapNum][i].worldY = gp.tileSize*15;
        i++;
        
        gp.obj[mapNum][i] = new OBJ_BlueHeart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*25;
        gp.obj[mapNum][i].worldY = gp.tileSize*8;
        i++;
        
        mapNum = 4; //rando dungeon 1
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*12;
        gp.obj[mapNum][i].worldY = gp.tileSize*30;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*30;
        gp.obj[mapNum][i].worldY = gp.tileSize*40;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*18;
        gp.obj[mapNum][i].worldY = gp.tileSize*21;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*38;
        gp.obj[mapNum][i].worldY = gp.tileSize*30;
        i++;
        
        mapNum = 5; //rando dungeon 2
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*14;
        gp.obj[mapNum][i].worldY = gp.tileSize*41;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*18;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*25;
        gp.obj[mapNum][i].worldY = gp.tileSize*19;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*9;
        gp.obj[mapNum][i].worldY = gp.tileSize*7;
        i++;
        
        mapNum = 6; //rando dungeon 3
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*32;
        gp.obj[mapNum][i].worldY = gp.tileSize*29;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*12;
        gp.obj[mapNum][i].worldY = gp.tileSize*22;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*9;
        gp.obj[mapNum][i].worldY = gp.tileSize*13;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*35;
        gp.obj[mapNum][i].worldY = gp.tileSize*15;
        i++;
        
        
        mapNum = 7; // random dungeon 4
        i = 0;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*14;
        gp.obj[mapNum][i].worldY = gp.tileSize*40;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*36;
        gp.obj[mapNum][i].worldY = gp.tileSize*41;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*8;
        gp.obj[mapNum][i].worldY = gp.tileSize*28;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot( new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.tileSize*36;
        gp.obj[mapNum][i].worldY = gp.tileSize*35;
        i++;
        
    }
    public void setNPC(){
        
        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_Oldman(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*21;
        gp.npc[mapNum][i].worldY = gp.tileSize*21;
        i++;
        
        //map 1
        
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*12;
        gp.npc[mapNum][i].worldY = gp.tileSize*7;
        i++;
        
//        gp.npc[0] = new NPC_Oldman(gp);
//        gp.npc[0].worldX = gp.tileSize*9;
//        gp.npc[0].worldY = gp.tileSize*11;

        mapNum = 2;
        i = 0;
        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*20;
        gp.npc[mapNum][i].worldY = gp.tileSize*25;
        i++;
        
        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*11;
        gp.npc[mapNum][i].worldY = gp.tileSize*18;
        i++;
        
        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*23;
        gp.npc[mapNum][i].worldY = gp.tileSize*14;
        i++;
    }
    
    public void setMonster(){
        
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*23;
        gp.monster[mapNum][i].worldY = gp.tileSize*36;
        i++;
        
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*23;
        gp.monster[mapNum][i].worldY = gp.tileSize*37;
        i++;
        
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*24;
        gp.monster[mapNum][i].worldY = gp.tileSize*37;
        i++;
        
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*34;
        gp.monster[mapNum][i].worldY = gp.tileSize*42;
        i++;
        
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*38;
        gp.monster[mapNum][i].worldY = gp.tileSize*42;
        i++;
        
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*38;
        gp.monster[mapNum][i].worldY = gp.tileSize*10;
        i++;
        
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*12;
        gp.monster[mapNum][i].worldY = gp.tileSize*33;
        i++;
        
        mapNum = 2; //first dungon (not random)
        i = 0;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*34;
        gp.monster[mapNum][i].worldY = gp.tileSize*39;
        i++;
        
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*36;
        gp.monster[mapNum][i].worldY = gp.tileSize*25;
        i++;
        
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*39;
        gp.monster[mapNum][i].worldY = gp.tileSize*26;
        i++;
        
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*28;
        gp.monster[mapNum][i].worldY = gp.tileSize*11;
        i++;
        
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*10;
        gp.monster[mapNum][i].worldY = gp.tileSize*19;
        i++;
        
        mapNum = 3; // boss rooom
        i = 0;
        if (Progress.skeletonLordDefeated ==false ) {
            gp.monster[mapNum][i] = new MON_SkeletonLord(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*23;
            gp.monster[mapNum][i].worldY = gp.tileSize*17;
        i++;
        }
        
        mapNum = 4; //rando dungeon #1
        i = 0;
        
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*11;
        gp.monster[mapNum][i].worldY = gp.tileSize*35;
        i++;
        
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*18;
        gp.monster[mapNum][i].worldY = gp.tileSize*10;
        i++;
        
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*17;
        gp.monster[mapNum][i].worldY = gp.tileSize*39;
        i++;
        
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*18;
        gp.monster[mapNum][i].worldY = gp.tileSize*24;
        i++;
        
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*11;
        gp.monster[mapNum][i].worldY = gp.tileSize*19;
        i++;
        
        if (gp.player.difficulty == 1 || gp.player.difficulty == 2) {
            gp.monster[mapNum][i] = new MON_RedSlime(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*38;
            gp.monster[mapNum][i].worldY = gp.tileSize*17;
            i++;
        }
        if (gp.player.difficulty == 2) {
            gp.monster[mapNum][i] = new MON_RedSlime(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*17;
            gp.monster[mapNum][i].worldY = gp.tileSize*12;
            i++;
            gp.monster[mapNum][i] = new MON_Orc(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*9;
            gp.monster[mapNum][i].worldY = gp.tileSize*8;
            i++;
        }
        
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*27;
        gp.monster[mapNum][i].worldY = gp.tileSize*35;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*27;
        gp.monster[mapNum][i].worldY = gp.tileSize*36;
        i++;
        
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*29;
        gp.monster[mapNum][i].worldY = gp.tileSize*18;
        i++;
        
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*17;
        gp.monster[mapNum][i].worldY = gp.tileSize*10;
        i++;
        
        mapNum = 5; //second rando dungeon
        i = 0;
        
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*8;
        gp.monster[mapNum][i].worldY = gp.tileSize*32;
        i++;
        
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*9;
        gp.monster[mapNum][i].worldY = gp.tileSize*32;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*10;
        gp.monster[mapNum][i].worldY = gp.tileSize*32;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*12;
        gp.monster[mapNum][i].worldY = gp.tileSize*32;
        i++;
        
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*12;
        gp.monster[mapNum][i].worldY = gp.tileSize*19;
        i++;
        
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*17;
        gp.monster[mapNum][i].worldY = gp.tileSize*10;
        i++;
        
        if (gp.player.difficulty == 1 || gp.player.difficulty == 2) {
            gp.monster[mapNum][i] = new MON_RedSlime(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*38;
            gp.monster[mapNum][i].worldY = gp.tileSize*15;
            i++;
        }
        if (gp.player.difficulty == 2) {
            gp.monster[mapNum][i] = new MON_RedSlime(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*32;
            gp.monster[mapNum][i].worldY = gp.tileSize*12;
            i++;
            gp.monster[mapNum][i] = new MON_Orc(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*37;
            gp.monster[mapNum][i].worldY = gp.tileSize*19;
            i++;
            gp.monster[mapNum][i] = new MON_Orc(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*40;
            gp.monster[mapNum][i].worldY = gp.tileSize*15;
            i++;
        }
        
        mapNum = 6; //rando dungeon #3
        i = 0;
        
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*15;
        gp.monster[mapNum][i].worldY = gp.tileSize*40;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*26;
        gp.monster[mapNum][i].worldY = gp.tileSize*38;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*28;
        gp.monster[mapNum][i].worldY = gp.tileSize*36;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*29;
        gp.monster[mapNum][i].worldY = gp.tileSize*30;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*18;
        gp.monster[mapNum][i].worldY = gp.tileSize*21;
        i++;
        
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*32;
        gp.monster[mapNum][i].worldY = gp.tileSize*21;
        i++;
        
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*32;
        gp.monster[mapNum][i].worldY = gp.tileSize*34;
        i++;
        
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*26;
        gp.monster[mapNum][i].worldY = gp.tileSize*16;
        i++;
        
        
        if (gp.player.difficulty == 1 || gp.player.difficulty == 2) {
            gp.monster[mapNum][i] = new MON_RedSlime(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*19;
            gp.monster[mapNum][i].worldY = gp.tileSize*18;
            i++;
        }
        if (gp.player.difficulty == 2) {
            gp.monster[mapNum][i] = new MON_RedSlime(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*15;
            gp.monster[mapNum][i].worldY = gp.tileSize*13;
            i++;
            gp.monster[mapNum][i] = new MON_Orc(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*22;
            gp.monster[mapNum][i].worldY = gp.tileSize*10;
            i++;
            gp.monster[mapNum][i] = new MON_RedSlime(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*12;
            gp.monster[mapNum][i].worldY = gp.tileSize*14;
            i++;
        }
        
        mapNum = 7; //rando dungeon #4
        i = 0;
        
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*20;
        gp.monster[mapNum][i].worldY = gp.tileSize*36;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*31;
        gp.monster[mapNum][i].worldY = gp.tileSize*34;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*37;
        gp.monster[mapNum][i].worldY = gp.tileSize*24;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*18;
        gp.monster[mapNum][i].worldY = gp.tileSize*19;
        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*10;
        gp.monster[mapNum][i].worldY = gp.tileSize*29;
        i++;
        
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*9;
        gp.monster[mapNum][i].worldY = gp.tileSize*32;
        i++;
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*31;
        gp.monster[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*29;
        gp.monster[mapNum][i].worldY = gp.tileSize*26;
        i++;
        
        if (gp.player.difficulty == 1 || gp.player.difficulty == 2) {
            gp.monster[mapNum][i] = new MON_RedSlime(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*18;
            gp.monster[mapNum][i].worldY = gp.tileSize*28;
            i++;
        }
        if (gp.player.difficulty == 2) {
            gp.monster[mapNum][i] = new MON_RedSlime(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*24;
            gp.monster[mapNum][i].worldY = gp.tileSize*30;
            i++;
            gp.monster[mapNum][i] = new MON_Orc(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*16;
            gp.monster[mapNum][i].worldY = gp.tileSize*32;
            i++;
            gp.monster[mapNum][i] = new MON_RedSlime(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize*20;
            gp.monster[mapNum][i].worldY = gp.tileSize*32;
            i++;
        }
        
    }
    public void setInteractiveTile(){
        
        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 32, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 20); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 21); i++;
        
        mapNum = 2; //dungeon 1
        i = 0;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 30); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 31); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 32); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 34); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 34); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 33); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 10, 22); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 10, 24); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 18); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 19); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 20); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 21); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 13); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 14); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 22, 28); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 30, 28); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 32, 28); i++;
        
        gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 20, 22); i++;
        gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 8, 17); i++;
        gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 14, 28); i++;
        
        mapNum = 4; //rando dungeon 1
        i = 0;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 13, 37); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 10, 35); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 33); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 33); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 34); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 34); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 19, 31); i++;
        
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 11, 26); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 11, 27); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 26, 27); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 33, 21); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 36, 20); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 18); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 36, 10); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 22, 10); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 22, 11); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 11, 10); i++;
        
        mapNum = 5; //rando dungeon 2
        i = 0;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 13, 37); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 11, 30); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 10, 23); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 12, 23); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 13, 23); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 14, 23); i++;
        
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 15, 15); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 16, 15); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 15); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 15); i++;
        
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 13, 10); i++;
        
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 23, 10); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 23, 11); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 22, 10); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 22, 11); i++;
        
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 36, 17); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 37, 17); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 17); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 34, 14); i++;
        
        mapNum = 6; //rando dungeon 3
        i = 0;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 12, 41); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 13, 41); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 20, 36); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 20, 37); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 24, 33); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 31, 31); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 37, 28); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 26, 16); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 15); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 20, 11); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 21, 11); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 22, 11); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 23, 11); i++;
        
        
        mapNum = 7; //rando dungeon 4
        i = 0;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 11, 41); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 12, 41); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 13, 41); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 20, 36); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 29, 37); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 12, 30); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 12, 29); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 12, 22); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 30); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 24); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 28, 31); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 33, 30); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 33, 29); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 33, 28); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 34, 29); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 37, 21); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 21); i++;
        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 39, 21); i++;
        

    }
}
