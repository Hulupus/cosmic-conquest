import sas.*;
import sasio.*;

import java.util.ArrayList;
import java.awt.Color;

import ships.*;

public class Kontrollzentrum
{
    private View view;

    //Hintergründe
    private Picture loadingscreen;
    private Picture titlescreen;
    private Picture background;

    //Explosion
    private Picture explosion;

    //Schiffe
    private PlayerShip mainShip;

    private Cruiser[] cruiser;
    private Bomber[] bomber;

    //Sonstiges
    private Picture startbutton;
    private Lebensanzeige leben;
    //private int activeLevel;

    private Level level;
    
    private int clearedEnemies;

    public Kontrollzentrum() {
        view = new View(800, 800, "Cosmic Conquest");

        loadingscreen = new Picture(0, 0, 800, 800, "assets/views/Cosmic_Conquest_loadingscreen.png");

        background = new Picture(0, 0, 800, 800,  "assets/views/background.png");
        background.setHidden(true);

        explosion = new Picture(0, 0, 60, 60, "assets/views/Explosion.png");
        explosion.setHidden(true);

        level = new Level(1);
        cruiser = new Cruiser[6];
        bomber = new Bomber[6];

        for (int i = 0; i < cruiser.length; i++) {
            cruiser[i] = new Cruiser(75 + i*120, 20);//Tools.randomNumber(-150, -50));
            cruiser[i].toggleHidden(true);// activeEnemies.add(cruiser[i]);
        }
        for (int i = 0; i < bomber.length; i++) {
            bomber[i] = new Bomber(75 + i*120, 20);//Tools.randomNumber(-150, -50));
            bomber[i].toggleHidden(true);// activeEnemies.add(cruiser[i]);
        }       
        // activeBomber = new ArrayList<>();
        // for (int i = 0; i < 6; i++) {
        // activeBomber.add(new Bomber(30 + 130*i, -40));
        // }

        titlescreen = new Picture(0, 0, 800, 800, "assets/views/Cosmic_Conquest_titlescreen.png");
        startbutton = new Picture(250, 450 , 300, 100, "assets/views/Cosmic_Conquest_startbutton.png");
        mainShip = new PlayerShip();

        starteSpiel();

    }

    public void starteSpiel() {
        while (!startbutton.mouseClicked()) {
            view.wait(100);
        }
        
        titlescreen.setHidden(true);
        background.setHidden(false);
        startbutton.setHidden(true);
        leben = new Lebensanzeige(3);
        
        for (int i = 0; i < 100; i++) {
            mainShip.move(0);
            view.wait(3);
        }
        
        starteLevel();
    }

    public void starteLevel() {
        clearedEnemies = 0;
        openStage(level.getEnemies());
        while (leben.getAmountOfHearts() != 0) {//!level[0].) {
            letCruiserMove();
            letBomberMove();

            mainShip.move(view);
            mainShip.bewegeLaser();
            mainShip.schießen(view);
            
            if (level.isStageCleared(clearedEnemies)){
                level.setActiveStage(level.getActiveStage()+1);
                if (level.isCleared()) {break;}
                openStage(level.getEnemies());
            }

            view.wait(3);
        }
    }

    public void letCruiserMove() {
        for (int i = 0; i < cruiser.length; i++) {
            if (cruiser[i].collides(mainShip)) {
                leben.removeHeart();
            }
            if (mainShip.collides(cruiser[i])) {
                cruiser[i].toggleHidden(true);
                //add loot?
                clearedEnemies++;
            }
            
            cruiser[i].bewegeLaser();
            if (cruiser[i].getHidden()) {continue;}
            if (cruiser[i].getY() > 810) {
                cruiser[i].toggleHidden(true);
                clearedEnemies++;
            }
            
            cruiser[i].move();
            cruiser[i].schießen();
        }
    }

    public void letBomberMove() {
        for (int i = 0; i < bomber.length; i++) {
            if (bomber[i].collides(mainShip)) {
                leben.removeHeart();
            }
            if (mainShip.collides(bomber[i])) {
                bomber[i].toggleHidden(true);
                //add loot?
                clearedEnemies++;
            } 
            
            if (bomber[i].getHidden()) {continue;}
            if (bomber[i].getY() > 810) {
                bomber[i].toggleHidden(true);
                clearedEnemies++;
            }
            bomber[i].move();
        }
    }
            
            
    // public void zerstören (){
    // Zerstörung durch MainShip
    // for (int i = 0; i < enemies.length; i++) {
    // if (mainShip.trifft(enemies[i])) {
    // explosion.moveTo(enemies[i].getX()-5, enemies[i].getY()-5);
    // explosion.setHidden(false);
    // view.wait(5);
    // enemies[i].toggleHidden(true);
    // explosion.setHidden(true);
    // speed += 0.09;
    // hits++;
    // }
    // }
    // Zerstörung durch Gegner
    // for (int i = 0; i < enemies.length; i++) {
    // if (enemies[i].trifft(mainShip)) {
    // leben.removeHeart();
    // }
    // }
    // }

    // public void aendereStat(double[][] stats) {
    // //Anzahl der stats, die verändert werden
    // for (int i1 = 0; i1 < stats.length; i1++) {
    // if (stats[i1][0] == 0) {
    // for (int i2 = 0; i2 < stats[i1][1]; i2++) {
    // leben.addHeart();            
    // }
    // } else if (stats[i1][0] == 1) {

    // } else if (stats[i1][0] == 2) {

    // } else if (stats[i1][0] == 3) {

    // }
    // }
    // }

    // public void enemyMoveTo(int i, int xPos, int yPos){
    // enemies[i].moveTo(xPos, yPos);
    // }

    //Levelsystem
    public void openStage(ArrayList<Schiffposition> stageShips){
        System.out.println(stageShips);
        for (int i = 0; i < stageShips.size(); i++){
            if (stageShips.get(i).getType().equals("Cruiser")){
                for (int j = 0;j < cruiser.length; j++){
                    if (cruiser[j].getActive()){continue;}
                    cruiser[j].moveTo(stageShips.get(i).getX(), stageShips.get(i).getY());
                    cruiser[j].toggleActive(true);
                    cruiser[j].toggleHidden(false);
                    break;
                }
            } 
            // else if (stageShips.get(i).getType() == "Bomber"){
            // // for (int k = 0;k > bomber.length; k++){
            // // if (!bomber[k].getactive){
            // // //bewge zu ausgelesener Pos
            // // //(kopiere) in Array activeBomber
            // // }
            // // }
            // }
        }
    }
}

// public boolean stagezuende(){
// if (true){
// return true;
// }
// return false;
// }
// }
