package base;

import sas.*;
/**
 * Beschreiben Sie hier die Klasse Bulding.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Building{
    
    private Picture building;
    
    public Building(){
        
    }

    public Boolean clicked(){
        return building.mouseClicked();
    }
}
