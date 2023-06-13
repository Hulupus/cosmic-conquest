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
    private int amount_Pyrotheum; //explosive Flüssigkeit 
    private int amount_EngineParts;

    

    public Base(){
        base_planet = new Picture(-25, 475, 850, 850, "assets/views/base/Cosmic_Conquest_base_planet.png");
        
        //Buldings
        labor = new Laboratory();
        shipBay = new ShipBay();

    }
    
    public void BaseRun(View view){
        while (true){
            // if (ShipBay.clicked()){
                // screenmanager.openscreen(screen2ShipBay);
            // }
            // if (Laboratory.clicked()){
                // screenmanager.openscreen(screen3Labor);
            // }
            turnPlanet(view);
        }
    }
    
    public void turnPlanet(View view){
        if (view.keyPressed('a')){
            base_planet.turn(400, 900, -0.2);
            labor.turn(-0.2);
        } else if(view.keyPressed('d')){
            base_planet.turn(400, 900, 0.2);
            labor.turn(-0.2);
        }
    }
    
    public void build(String BuildingType, View view){
        
        
        // while(!view.keyPressed('b')){
            // if (view.keyPressed('q')){
                // .turn(400, 800, -1);
            // } else if(view.keyPressed('e')){
                // .turn(400, 800, 1);
            // }
        // }
    }
}
