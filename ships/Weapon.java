package ships;

import util.Queue;
import util.List;

public class Weapon
{
    private Queue<Projectile> lasers;
    private List<Projectile> firedLasers;
    private double[] firePositions;
    private int cooldown;
    
    private Ship attachedShip;
    
    //Attributes
    private double cooldownTime;
    private double failureRate;
    
    public Weapon(Projectile projectile, int projectileCount, double cooldownTime, double failureRate)
    {
        this.attachedShip = attachedShip;
        this.cooldownTime = cooldownTime;
        this.cooldown = (int) cooldownTime;
        this.failureRate = failureRate;
        
        this.lasers = new Queue<>();
        this.lasers.enqueue(projectile);
        for (int i = 1; i < projectileCount; i++) {
            this.lasers.enqueue(projectile.clone());
        }
        
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
        cooldown = 0;
        
        firedLasers.append(lasers.front());
        lasers.dequeue();
    }
    
    public void moveLasers()
    {
        firedLasers.toFirst();
        while (firedLasers.hasAccess()) {
            firedLasers.getContent().move();
            if (firedLasers.getContent().getY() < -20 || firedLasers.getContent().getY() > 820) {
                lasers.enqueue(firedLasers.getContent());
                firedLasers.getContent().setHidden(true);
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
    public void setAttachedShip(Ship attachedShip) {
        this.attachedShip = attachedShip;
    }
}
