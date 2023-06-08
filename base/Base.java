package base; 

import sas.*;
public class Base{
    private Picture base_planet;
    
    //Buildings
    private Laboratory labor;
    private ShipBay shipBay;
    
    //Material
        // Overseer
    private int amount_Sternfragmente;
    private int amount_Feuerball;
        // Cruiser
    private int amount_Metalplatte;
    private int amount_Metalgerüßt;
        // Bomber
    private int Pyrotheum; //explosive Flüssigkeit 

    

    public Base(){
        base_planet = new Picture(-25, 475, 850, 800, "assets/base/Cosmic_Conquest_base_planet.png");
        //Buldings
        // labor = new Laboratory();
        // shipBay = new ShipBay();

    }
    
    public void BaseRun(){
        while (true){
            if (ShipBay.clicked()){
                screenmanager.openscreen(screen2ShipBay);
            }
            if (Laboratory.clicked()){
                screenmanager.openscreen(screen3Labor);
            }
            turnPlanet();
        }
    }
    
    public void turnPlanet(){
        if (Kontrollzentrum.keyPressed("q")){
            base_planet.turn(400, 800, -1);
        } else if(Kontrollzentrum.keyPressed("e")){
            base_planet.turn(400, 800, 1);
        }
    }
}
