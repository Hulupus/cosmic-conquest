package base;

import sas.*;
public class Building{
    private Picture building;
    private int building_degree;
    private int[] requiredParts;
    private boolean build;
    
    public Building(String pictureURL, int xPos, int yPos, int height, int width, int[] pRequiredParts){
        building = new Picture(xPos, yPos, height, width, pictureURL);
        requiredParts = pRequiredParts;
        build = false;
    }

    public Boolean clicked(){
        return building.mouseClicked();
    }
    
    public void move(double x){
        building.move(x);
    }
    
    public void moveTo(double x, double y){
        building.moveTo(x,y);
    }
    
    public boolean getBuild(){
        return build;
    }
    
    // public boolean collides(){
        
    // }
}
