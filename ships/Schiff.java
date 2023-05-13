package ships;

import sas.Picture;
import java.awt.Color;

public class Schiff
{
    private Picture schiff;
    
    //Artribute:
    private int leben;
    private double vFlug;
    
    public Schiff(String type, int x,int y, int width, int height)
    {
        schiff = new Picture(x, y, width, height, "assets/ships/Space_Ship_" + type + ".png");
    }
    
    public void move(double dir, double pV) {
        schiff.setDirection(dir);
        schiff.move(pV);
    }
    
    public void moveTo(int x, int y) {
        schiff.moveTo(x, y);
    }
    
    //getroffen bzw. collidiert
    
    public boolean collides(Schiff ship){
        if (schiff.intersects(ship.schiff)){
            return true;
        }
        return false;
    }
    
    /* ****** Getter und Setter ****** */
    protected Picture getSchiff() {
        return schiff;
    }
    
    public double getX() {
        return schiff.getShapeX();
    }
    
    public double getY() {
        return schiff.getShapeY();
    }
    
    public boolean getHidden() {
        return schiff.getHidden();    
    }
    
    public void toggleHidden(boolean hidden) {
        schiff.setHidden(hidden);
    }
}
