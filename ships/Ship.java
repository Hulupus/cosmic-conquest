package ships;

import sas.Picture;
import java.awt.Color;

public abstract class Ship
{
    private Picture ship;
    protected Weapon weapon;

    //Artribute:
    private String type;
    private int lives;
    private int originXPosition; //xPos to allow centered left and right movement
    private double flightDirection;
    private int flightDeviation;
    private double flightVelocity;

    public Ship(String type, int x,int y, int width, int height, double[] properties, Weapon weapon)
    {
        this.ship = new Picture(x, y, width, height, "assets/ships/Space_Ship_" + type + ".png");
        this.lives = (int) properties[0];
        this.flightVelocity = properties[1];
        this.flightDirection = properties[2];
        this.flightDeviation = (int) properties[3];
        this.originXPosition = x;
        this.type = type;
        this.weapon = weapon;
        this.weapon.setAttachedShip(this);
    }

    public void move() {
        if (flightDeviation != 0 && Math.abs(getOriginX()-getX()) > flightDeviation)  {
            flightDirection = -flightDirection;
        }
        ship.setDirection(flightDirection);
        ship.move(flightVelocity);
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
    public String getType() {
        return type;
    }

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
        return flightVelocity;
    }

    public void setVeFlight(double newVelocity) {
        flightVelocity = newVelocity;
    }

    public void setFlightDirection(double newFlightDirection) {
        flightDirection = newFlightDirection;
    }
    
    public boolean getHidden() {
        return ship.getHidden();    
    }

    public void setHidden(boolean hidden) {
        ship.setHidden(hidden);
    }
}
