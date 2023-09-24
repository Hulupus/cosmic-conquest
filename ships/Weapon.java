package ships;

import util.Queue;
import util.List;
import sas.Picture;

public class Weapon
{
    private Queue<Picture> lasers;
    private List<Picture> firedLasers;
    private double[] firePositions;
    private int cooldown;
    
    private Ship attachedShip;
    
    //Attributes
    private double cooldownTime;
    private double failureRate;
    private double laserSpeed;
    
    public Weapon(double cooldownTime, double failureRate, double laserSpeed)
    {
        this.attachedShip = attachedShip;
        this.cooldownTime = cooldownTime;
        this.cooldown = (int) cooldownTime;
        this.laserSpeed = laserSpeed;
        this.failureRate = failureRate;
        lasers = new Queue<>();
        firedLasers = new List<>();
        firePositions = new double[] { (25/2) };
    }
    
    public void fire()
    {
        if (lasers.isEmpty()) return;
        if (Math.round(Math.random() * failureRate) == 0) {
            cooldown = 0;
            return;
        }
        lasers.front().moveTo(attachedShip.getX() + firePositions[0], attachedShip.getY());
        lasers.front().setHidden(false);
        cooldown = 0;
        
        firedLasers.append(lasers.front());
        lasers.dequeue();
    }
    
    public void moveLasers()
    {
        firedLasers.toFirst();
        while (firedLasers.hasAccess()) {
            firedLasers.getContent().move(laserSpeed);
            if (firedLasers.getContent().getShapeY() < -20 || firedLasers.getContent().getShapeY() > 820) {
                lasers.enqueue(firedLasers.getContent());
                firedLasers.remove();
            }
            firedLasers.next();
        }
    }
    
    public boolean canFire () {
        if (cooldown < cooldownTime) {return false;}
        return true;
    }
    
    protected void addCooldownTime () {
        cooldown += 1;
    }
    
    /* ****************** Getter and Setter ****************** */
    public void setAttachedShip(Ship attachedShip, int laserCount) {
        this.attachedShip = attachedShip;
        String image = "assets/ships/Laser_red.png";
        int direction = 180;
        if (this.attachedShip.getType() == "player") {
            image = "assets/ships/Laser_green.png";
            direction = 0;
        }
        for (int i = 0; i < laserCount; i++) {
            Picture laser = new Picture(55, 700, 7, 25, image);
            laser.setDirection(direction);
            lasers.enqueue(laser);
        }
    }
}
