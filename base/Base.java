package base; 

import sas.*;
public class Base{
    // Karte[] kartendeck = new Karte[200];
    // Karte[] aktivesKartendeck = new Karte[5];
    // Karte[] ablagestapel = new Karte[200];
    
    
    
    private Picture[] base_background;
    private Picture base_background_left;
    private Picture base_background_main;
    private Picture base_background_right;
    //Buildings
    private Laboratory[] labor;
    private ShipBay shipBay;
    private Observatory observatory;
    
    //Material
        // Overseer
    private int amount_Sternfragmente;
    private int amount_Feuerball;
        // Cruiser
    private int amount_Metalplatte;
    private int amount_Metalgerüßt;
        // Bomber
    private int amount_Pyrotheum; //explosive Flüssigkeit 
    private int amount_EngineParts;

    

    public Base(View view){
        //Background
        base_background = new Picture[3];
        base_background[0] = new Picture (-800 * 6, 0, 800 * 6, 800, "assets/views/base/Cosmic_Conquest_base_background0.png");
        base_background[1] = new Picture (0, 0, 800 * 6, 800, "assets/views/base/Cosmic_Conquest_base_background1.png");
        base_background[2] = new Picture (800 * 6, 0, 800 * 6, 800, "assets/views/base/Cosmic_Conquest_base_background2.png");
        
        //Buldings
        labor = new Laboratory[2]; // unter umständen Arraylist oder Menge begrenzen
        labor[0] = new Laboratory();
        labor[1] = new Laboratory();
        shipBay = new ShipBay();
        observatory = new Observatory();
    }
    
    public void moveBase(View view){
        if (view.keyPressed('a')){
            //Background
            for (int i = 0; i < base_background.length; i++){
                base_background[i].move(0.0001);
            }
                
            //Buildings
            shipBay.move(0.0001);
            observatory.move(0.0001);
            for (int i = 0; i < labor.length; i++){ //
                labor[i].move(0.0001);
            }
        } 
        if(view.keyPressed('d')){
            //Background
            for (int i = 0; i < base_background.length; i++){
                base_background[i].move(-0.0001);
            }
                
            //Buildings
            shipBay.move(-0.0001);
            observatory.move(-0.0001);
            for (int i = 0; i < labor.length; i++){
                labor[i].move(-0.0001);
            }
        }
        if (base_background[1].getShapeX() <= -800 * 6){
            for (int i = 0; i < base_background.length; i++){
                base_background[i].move(800 * 6);
            }
            
            //Buildings
            shipBay.move(800 * 6);
            observatory.move(800 * 6);
            for (int i = 0; i < labor.length; i++){ //
                labor[i].move(800 * 6);
            }
        }
        if (base_background[1].getShapeX() >= 800){
            for (int i = 0; i < base_background.length; i++){
                base_background[i].move(-800 * 6);
            }
            //Buildings
            shipBay.move(-800 * 6);
            observatory.move(-800 * 6);
            for (int i = 0; i < labor.length; i++){
                labor[i].move(-800 * 6);
            }
        }
    }
    
    public boolean shipBayClicked(){
        if (shipBay.clicked()){
                return true;
        }
        return false;
    }
    
    public int laborClicked(){
        for (int i = 0; i < labor.length; i++){
            if (labor[i].clicked()){
                return i;
            }
        }
        return 0;
    }
    
    public boolean observatoryClicked(){
        if (observatory.clicked()){
                return true;
        }
        return false;
    }

    
    
}
