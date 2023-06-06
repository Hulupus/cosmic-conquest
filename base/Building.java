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
    private int building_degree;
    
    public Building(String pictureURL, int xPos, int yPos, int height, int width){
        building = new Picture(xPos, yPos, height, width, pictureURL);
    }

    public Boolean clicked(){
        return building.mouseClicked();
    }
}
