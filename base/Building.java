package base;

import sas.*;
public class Building{
    private Picture building;
    private int building_degree;
    private int[] requiredParts;
    
    public Building(String pictureURL, int xPos, int yPos, int height, int width, int[] pRequiredParts){
        building = new Picture(xPos, yPos, height, width, pictureURL);
        requiredParts = pRequiredParts;
    }

    public Boolean clicked(){
        return building.mouseClicked();
    }
    
    public void move(double x){
        building.move(x);
    }
}
