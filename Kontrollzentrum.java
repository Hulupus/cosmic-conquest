import sas.*;
import java.util.ArrayList;
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
    private ArrayList<Cruiser> activeEnemies;
    
    private Bomber[] bomber;
    private ArrayList<Bomber> activeBomber;
    //aktiveShips => alle Schiffe des momentanen Levels => ArrayList / Array
    
    //Sonstiges
    private Picture startbutton;
    private Lebensanzeige leben;
    private int activeLevel;
    
    private Level[] levels;
    
    public Kontrollzentrum() {
        view = new View(800, 800, "Cosmic Conquest");

        loadingscreen = new Picture(0, 0, 800, 800, "assets/views/Cosmic_Conquest_loadingscreen.png");

        background = new Picture(0, 0, 800, 800,  "assets/views/background.png");
        background.setHidden(true);

        explosion = new Picture(0, 0, 60, 60, "assets/views/Explosion.png");
        explosion.setHidden(true);
        
        levels = new Level[3];
        levels[0] = new Level(1); //Todo
        
        cruiser = new Cruiser[6];
        activeEnemies = new ArrayList<>();
        for (int i = 0; i < cruiser.length; i++) {
            cruiser[i] = new Cruiser(75 + i*120, 20);//Tools.randomNumber(-150, -50));
            activeEnemies.add(cruiser[i]);
        }
        
        activeBomber = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            activeBomber.add(new Bomber(30 + 130*i, -40));
        }
        
        titlescreen = new Picture(0, 0, 800, 800, "assets/views/Cosmic_Conquest_titlescreen.png");
        startbutton = new Picture(250, 450 , 300, 100, "assets/views/Cosmic_Conquest_startbutton.png");
        mainShip = new PlayerShip();
        
        starteSpiel();
        
    }

    public void starteSpiel() {
        boolean gamestarted = false;
        while (!gamestarted) {
            if (startbutton.mouseClicked()) {
                titlescreen.setHidden(true);
                background.setHidden(false);
                startbutton.setHidden(true);

                leben = new Lebensanzeige(3);

                for (int i = 0; i < 100; i++) {
                    mainShip.move(0);
                    view.wait(3);
                }
                gamestarted = true;
            }
            view.wait(100);
        }
        starteLevel();
    }
    
    public void starteLevel() {
        for (;;) {
            letCruiserMove();
            letBomberMove();
            
            mainShip.move(view);
            mainShip.bewegeLaser();
            mainShip.schießen(view);
            
            view.wait(3);
        }
    }
    
    public void letCruiserMove() {
        for (int i = 0; i < activeEnemies.size(); i++) {
            if (activeEnemies.get(i).collides(mainShip)) {
                leben.removeHeart();
            }
            if (mainShip.collides(activeEnemies.get(i))) {
                activeEnemies.get(i).toggleHidden(true);
            }
            
            activeEnemies.get(i).bewegeLaser();
            if (activeEnemies.get(i).getHidden()) {continue;}
            activeEnemies.get(i).move();
            activeEnemies.get(i).schießen();
        }
        
    }
    
    public void letBomberMove() {
        for (int i = 0; i < activeBomber.size(); i++) {
            if (activeBomber.get(i).collides(mainShip)) {
                leben.removeHeart();
            }
            if (mainShip.collides(activeBomber.get(i))) {
                activeBomber.get(i).toggleHidden(true);
            }
            if (activeBomber.get(i).getHidden()) {continue;}
            activeBomber.get(i).move();
        }
    }
    
    // for (;;) {
            // mainShip.move(view);
            // view.wait(3);
        // }
    
    
    // while(hits != 6) { // != 0 || 
            // if (leben.getAmountHearts() == 0) {return;}

            // //Bewegen der Schiffe
            // mainShip.move(view);
            // //bewegeGegner();

            // //Schießen
            // if (view.keyPressed(' ') && countdown <= 0) {
                // mainShip.schießen();
                // countdown = 180;
            // }
            // countdown--;
            // //lasseGegnerSchießen();

            // //Bewegen der Laser
            // // mainShip.bewegeLaser();
            // // for (int i = 0; i < enemies.length; i++) {
                // // enemies[i].bewegeLaser();
            // // }

            // //Zerstörung
            // //zerstören();
            // view.wait(3);

            // //Beendung
            // int outOfBounce = 0;
            // // for (int i = 0; i < enemies.length; i++) {
                // // if (enemies[i].getY() < 810) {continue;}
                // // outOfBounce++;
            // // }
            // if (outOfBounce == 6) {return;}
        // }
        // view.wait(100);
        // while (mainShip.getY() > -70) {
            // mainShip.move('w', 1);
            // view.wait(2);
        // }
    
    
    
    // public void lasseGegnerSchießen() {
        // if (Tools.randomNumber(0, 200) != 99) {return;}
        // for (int i = 0; i < enemies.length; i++) {
            // if (enemies[i].getHidden()) {continue;}
            // if (Tools.randomNumber(0, 6) == 3) {
                // enemies[i].schießen();
            // }
        // }
    // }

    // public void bewegeGegner(){
        // if (enemies[0].getX() <= 0){
            // dir = 'd';
        // } else if(enemies[0].getX() >= 150){
            // dir = 'a';
        // }
        // for (int i = 0; i < enemies.length; i++) {
            // enemies[i].move(dir, 0.2);
        // }
        // for (int i = 0; i < enemies.length; i++) {
            // enemies[i].move('s', speed);
        // }
    // }

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
    public void openNextStage(){
        String[] enemyTypes = levels[0].getStageEnemies();
        for (int i = 0; i < enemyTypes.length; i++){
            if (enemyTypes[i] == "Cruiser"){
                for (int j = 0;j > cruiser.length; j++){
                    if (!true){ //cruiser[j].getactive){
                        //bewge zu ausgelesener Pos
                        //(kopiere) in Array activeCruiser
                    }
                }
            } else if (enemyTypes[i] == "Bomber"){
                // for (int k = 0;k > bomber.length; k++){
                    // if (!bomber[k].getactive){
                        // //bewge zu ausgelesener Pos
                        // //(kopiere) in Array activeBomber
                    // }
                // }
            }
        }
    }
    
    public boolean stagezuende(){
        if (true){
            return true;
        }
        return false;
    }
}
