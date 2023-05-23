package base; 

public class Basis{
    //Buildings
    private Laboratory labor;
    private ShipBay shipBay;
    
    public Basis()
    {
        //Buldings
        labor = new Laboratory();
        shipBay = new ShipBay();
        
    }
}
