
/**
 * Beschreiben Sie hier die Klasse Schiffposition.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Schiffposition
{
    private String shipType;
    
    private int arrayIndex;
    
    private int xPosition;
    private int yPosition;
    
    public Schiffposition(String shipType, int arrayIndex, int xPosition, int yPosition) 
    {
        this.shipType = shipType;
        
        this.arrayIndex = arrayIndex;
        
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    
    /* ****** Getter und Setter ****** */
    public String getType()
    {
        return shipType;
    }
    
    public int getArrayIndex () 
    {
        return arrayIndex;
    }
    
    public int getX() 
    {
        return xPosition;
    }
    
    public int getY() 
    {
        return yPosition;
    }
    
    public void setPosition(int newXPos, int newYPos) 
    {
        xPosition = newXPos;
        yPosition = newYPos;
    }
}
