package base;

import sas.*;
public class Building{
    private Picture building;
    private int building_degree;
    private int[] requiredParts;
    
    public Building(String pictureURL, int xPos, int yPos, int height, int width, int[] prequiredParts){
        building = new Picture(xPos, yPos, height, width, pictureURL);
        requiredParts = new int[6];
        for (int i = 0; i < requiredParts.length; i++){
            prequiredParts[i] = requiredParts[i];
        }
    }

    public Boolean clicked(){
        return building.mouseClicked();
    }
}
