package ships;

import sas.Picture;
import java.awt.Color;

public abstract class Ship
{
    private Picture ship;
    protected Weapon weapon;

    //Artribute:
    private int lives;
    private double vFlight; //Speed of Ship
    private int originXPosition; //xPos to allow centered left and right movement

    public Ship(String type, int x,int y, int width, int height, double[] properties, Weapon weapon)
    {
        ship = new Picture(x, y, width, height, "assets/ships/Space_Ship_" + type + ".png");
        lives = (int) properties[0];
        vFlight = properties[1];
        originXPosition = x;
        this.weapon = weapon;
        this.weapon.setAttachedShip(this);
    }

    public void move(double dir) {
        ship.setDirection(dir);
        ship.move(vFlight);
    }

    public void moveTo(int newXPos, int newYPos) {
        ship.moveTo(newXPos, newYPos);
        originXPosition = newXPos;
    }

    public boolean collides(Ship ship){
        if (this.ship.intersects(ship.getShip())){
            return true;
        }
        return false;
    }

    public void fire() {
        if (weapon == null) return;
        if (weapon.canFire()) weapon.fire();
        else weapon.addCooldownTime();
        weapon.moveLasers();
    }

    /* ****** Getter und Setter ****** */
    protected Picture getShip() {
        return ship;
    }

    public int getOriginX() {
        return originXPosition;
    }

    public int getX() {
        return (int) ship.getShapeX();
    }

    public int getY() {
        return (int) ship.getShapeY();
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

    // public boolean getActive() {
    // return isActive;
    // }

    // public void toggleActive(boolean active) {
    // isActive = active;
    // }

    public boolean getHidden() {
        return ship.getHidden();    
    }

    public void toggleHidden(boolean hidden) {
        ship.setHidden(hidden);
    }
}
