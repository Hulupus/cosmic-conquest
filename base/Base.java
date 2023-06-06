package base; 

public class Base{
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
        //Buldings
        labor = new Laboratory();
        shipBay = new ShipBay();

    }
    
    public void BaseRun(){
        while (true){
            if (ShipBay.clicked()){
                screenmanager.openscreen(screen2ShipBay);
            }
            if (Laboratory.clicked()){
                screenmanager.openscreen(screen3Labor);
            }
        }
    }
}
