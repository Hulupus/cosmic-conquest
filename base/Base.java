package base; 

import sas.*;
public class Base{
    private Picture base_background;
    
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
        base_background = new Picture (0, 0, 800 * 6, 800, "assets/views/base/Cosmic_Conquest_base_background.png");
        
        //Buldings
        labor = new Laboratory[2]; // unter umständen Arraylist oder Menge begrenzen
        labor[0] = new Laboratory();
        labor[1] = new Laboratory();
        shipBay = new ShipBay();
        observatory = new Observatory();
        
        BaseRun(view);
    }
    
    // Wie benutzt man den ScreenManager?
    public void BaseRun(View view /*, ScreenManager screenManager*/){
        while (true){
            if (shipBayClicked()){
                // openScreen(screenManager.baseShipBay, view);
            }
            if (shipBayClicked()){
                // openScreen(screenManager.baseShipBay, view);
            }
            moveBase(view);
        }
    }
    
    public void moveBase(View view){
        if (view.keyPressed('a')){
            if (base_background.getShapeX() <= 0){
                //Background
                base_background.move(0.0001);
                
                //Buildings
                shipBay.move(0.0001);
                observatory.move(0.0001);
                for (int i = 0; i < labor.length; i++){ //
                    labor[i].move(0.0001);
                }
            }
        } else if(view.keyPressed('d')){
            if (base_background.getShapeX() >= -4000){
                //Background
                base_background.move(-0.0001);
                
                //Buildings
                shipBay.move(-0.0001);
                observatory.move(-0.0001);
                for (int i = 0; i < labor.length; i++){
                    labor[i].move(-0.0001);
                }
            }
        }
    }
    
    public void build(String BuildingType, View view){
        
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
