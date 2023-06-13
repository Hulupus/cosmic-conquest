import sas.*;
import sasio.*;

import java.util.ArrayList;
import java.awt.Color;

import ships.*;
import base.*;

public class Kontrollzentrum
{
    private View view;

    private Base base;

    //Hintergründe
    private ScreenManager screenManager;    

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
        screenManager = new ScreenManager(800, 800);

        // explosion = new Picture(0, 0, 60, 60, "assets/views/Explosion.png");
        // explosion.setHidden(true);

        level = new Level(1);
        cruiser = new Cruiser[6];
        bomber = new Bomber[6];

        for (int i = 0; i < cruiser.length; i++) {
            cruiser[i] = new Cruiser(75 + i*120, 20);
            cruiser[i].toggleHidden(true);
        }
        for (int i = 0; i < bomber.length; i++) {
            bomber[i] = new Bomber(75 + i*120, 20);
            bomber[i].toggleHidden(true);
        }

        mainShip = new PlayerShip();
        // base = new Base();
        // screenManager.openScreen("baseMain", view);
        screenManager.openScreen("titlescreen", view);
        starteSpiel();
    }

    public void starteSpiel() {
        startbutton = new Picture(250, 450 , 300, 100, "assets/views/Cosmic_Conquest_startbutton.png");
        // while (!startbutton.mouseClicked()) {
            // view.wait(100);
        // }
        startbutton.setHidden(true);

        screenManager.openScreen("level", view);

        
        leben = new Lebensanzeige(3);

        for (int i = 0; i < 200; i++) {
            mainShip.move(0);
            view.wait(3);
        }

        starteLevel();
    }

    public void starteLevel() {
        clearedEnemies = 0;
        openStage(level.getEnemies());
        while (leben.getAmountOfHearts() != 0) {
            letCruiserMove();
            letBomberMove();

            mainShip.move(view);
            mainShip.bewegeLaser();
            mainShip.schießen(view);
            //base.turnPlanet(view);

            if (level.isStageCleared(clearedEnemies)){
                level.setActiveStage(level.getActiveStage()+1);
                for (int i = 0; i < cruiser.length; i++) {
                    cruiser[i].toggleActive(false);
                    bomber[i].toggleActive(false);
                }
                if (level.isCleared()) {break;}
                openStage(level.getEnemies());
                clearedEnemies = 0;
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

    //Levelladen
    public void openStage(ArrayList<Schiffposition> stageShips){
        for (int i = 0; i < stageShips.size(); i++){
            if (stageShips.get(i).getType().equals("Cruiser")){
                for (int j = 0;j < cruiser.length; j++){
                    if (cruiser[j].getActive()){continue;}
                    cruiser[j].moveTo(stageShips.get(i).getX(), stageShips.get(i).getY());
                    cruiser[j].toggleActive(true);
                    cruiser[j].toggleHidden(false);
                    break;
                }
            } else if (stageShips.get(i).getType().equals("Bomber")) {
                for (int j = 0;j < bomber.length; j++){
                    if (bomber[j].getActive()){continue;}
                    bomber[j].moveTo(stageShips.get(i).getX(), stageShips.get(i).getY());
                    bomber[j].toggleActive(true);
                    bomber[j].toggleHidden(false);
                    break;
                }
            }
        }
    }

    public boolean keyPressed(char key){
        return view.keyPressed(key);
    }
}

// public boolean stagezuende(){
// if (true){
// return true;
// }
// return false;
// }
// }
