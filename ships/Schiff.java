package ships;

import sas.Picture;
import java.awt.Color;

public class Schiff
{
    private Picture schiff;
    private boolean isActive = false;
    
    //Artribute:
    private int lives;
    private double vFlight; //Speed of Ship
    private int originXPosition; //xPos to allow centered left and right movement
    
    public Schiff(String type, int x,int y, int width, int height, double[] properties)
    {
        schiff = new Picture(x, y, width, height, "assets/ships/Space_Ship_" + type + ".png");
        lives = (int) properties[0];
        vFlight = properties[1];
        originXPosition = x;
    }
    
    public void move(double dir) {
        schiff.setDirection(dir);
        schiff.move(vFlight);
    }
    
    public void moveTo(int newXPos, int newYPos) {
        schiff.moveTo(newXPos, newYPos);
        originXPosition = newXPos;
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
    
    public int getOriginX() {
        return originXPosition;
    }
    
    public int getX() {
        return (int) schiff.getShapeX();
    }
    
    public int getY() {
        return (int) schiff.getShapeY();
    }
    
    public int getLives() {
        return lives;
    }
    
    public void setLives(int newAmountOfLives) {
        lives = newAmountOfLives;
    }
    
    public double getVeFlight() {
        return vFlight;
    }
    
    public void setVeFlight(double newVelocity) {
        vFlight = newVelocity;
    }
    
    public boolean getActive() {
        return isActive;
    }
    
    public void toggleActive(boolean active) {
        isActive = active;
    }
    
    public boolean getHidden() {
        return schiff.getHidden();    
    }
    
    public void toggleHidden(boolean hidden) {
        schiff.setHidden(hidden);
    }
}
