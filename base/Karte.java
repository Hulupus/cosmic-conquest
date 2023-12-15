package base;

public class Karte{
    private String name;
    private int type; //1 = Building; 2 = Material; 3 = Worker
    private boolean highlighted; 
    
    public Karte(String pName, int pType){
        name = pName;
        type = pType;
    }
    
    
    
    public String getName(){return name;}
    
    public int getType(){return type;}
}
