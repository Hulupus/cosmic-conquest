import sas.*;
import sasio.*;
import util.List;
import util.Queue;

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
    private PlayerShip playerShip;
    private ShipCollection enemyShips;
    private List<Ship> activeEnemies;

    //Sonstiges
    private Picture startbutton;
    private Lebensanzeige leben;
    //private int activeLevel;

    private Level level;

    public Kontrollzentrum() {
        view = new View(800, 800, "Cosmic Conquest");
        screenManager = new ScreenManager(800, 800);

        // explosion = new Picture(0, 0, 60, 60, "assets/views/Explosion.png");
        // explosion.setHidden(true);

        level = new Level(1);
        
        playerShip = new PlayerShip();
        enemyShips = new ShipCollection();
        activeEnemies = new List<Ship>();
        
        // base = new Base(view);
        // screenManager.openScreen("baseMain", view);
        
        starteSpiel();
    }
    
    public View getView(){
        return view;
    }
    

    public void starteSpiel() {
        // startbutton = new Picture(250, 450 , 300, 100, "assets/views/Cosmic_Conquest_startbutton.png");
        // while (!startbutton.mouseClicked()) {
            // view.wait(100);
        // }
        // startbutton.setHidden(true);

        screenManager.openScreen("level", view);

        
        leben = new Lebensanzeige(3);

        for (int i = 0; i < 100; i++) {
            playerShip.move();
            view.wait(3);
        }

        starteLevel();
    }

    public void starteLevel() {
        activeEnemies = getStage(level.getEnemies());
        while (leben.getAmountOfHearts() != 0) {

            //Move and Shoot
            playerShip.move(view);
            playerShip.fire(view);
            
            activeEnemies.toFirst();
            while(activeEnemies.hasAccess()) {
                activeEnemies.getContent().move();
                activeEnemies.getContent().fire();
                if (playerShip.collides(activeEnemies.getContent())) {
                    leben.removeHearts(activeEnemies.getContent().getWeaponDamage());
                }
                if (activeEnemies.getContent().collides(playerShip)) {
                    // cruiser[i].toggleHidden(true);
                    // //add loot?
                }
                activeEnemies.next();
            }
            
            if (activeEnemies.isEmpty()){
                level.setActiveStage(level.getActiveStage()+1);
                // for (int i = 0; i < cruiser.length; i++) {
                    // cruiser[i].toggleActive(false);
                    // bomber[i].toggleActive(false);
                // }
                if (level.isCleared()) break;
                activeEnemies = getStage(level.getEnemies());
            }

            view.wait(3);
        }
    }

    //Levelladen
    public List<Ship> getStage(ArrayList<Schiffposition> stageShips){
        List<Ship> choosenShips = new List<>();
        for (int i = 0; i < stageShips.size(); i++){
            Ship currentShip = enemyShips.getShip(stageShips.get(i).getType());
            currentShip.moveTo(stageShips.get(i).getX(), stageShips.get(i).getY());
            choosenShips.append(currentShip);
            //cruiser[j].toggleActive(true);
            //cruiser[j].toggleHidden(false);
        }
        return choosenShips;
    }
    
    private class ShipCollection
    {
        Queue<Ship> cruiser;
        
        public ShipCollection() {
            cruiser = new Queue<>();
            for (int i = 0; i < 6; i++) {
                cruiser.enqueue(new Cruiser(0,0));
            }
        }
        
        public Ship getShip(String type) {
            switch(type) {
                case "Cruiser":
                    Ship ship = cruiser.front();
                    cruiser.dequeue();
                    cruiser.enqueue(ship);
                    return ship;
            }
            return null;
        }
    }
}

