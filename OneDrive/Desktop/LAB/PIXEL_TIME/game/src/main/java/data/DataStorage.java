/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{
    //PLAYER STATS
    int level;
    int maxLife;
    int life;
    int maxMana;
    int mana;
    int strenght;
    int dexterity;
    int exp;
    int nextLevelExp;
    int coin;
    int score;
    String names;
    String className;
    //PLAYER INVENTORY
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();
    int currentWeaponSlot;
    int currentShieldSlot;
    // Object on map
    String mapObjectName[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];
    String mapObjectLootNames[][];
    boolean mapObjectOpened[][];
}
