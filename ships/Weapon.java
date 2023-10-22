package ships;

import util.Queue;
import util.List;

public class Weapon
{
    private Queue<Projectile> projectiles;
    private List<Projectile> firedProjectiles;
    private double[] firePositions;
    private int cooldown;

    private Ship attachedShip;

    //Attributes
    private int damage;
    private double cooldownTime;
    private double failureRate;

    public Weapon(Projectile projectile, int projectileCount, double cooldownTime, double failureRate)
    {
        this.attachedShip = attachedShip;
        this.cooldownTime = cooldownTime;
        this.damage = projectile.getDamage();
        this.cooldown = (int) cooldownTime;
        this.failureRate = failureRate;

        this.projectiles = new Queue<>();
        this.projectiles.enqueue(projectile);
        for (int i = 1; i < projectileCount; i++) {
            this.projectiles.enqueue(projectile.clone());
        }

        firedProjectiles = new List<>();

        firePositions = new double[] { (25/2) };
    }

    public void fire()
    {
        if (projectiles.isEmpty()) return;
        if (Math.round(Math.random() * failureRate) == 0) {
            cooldown = 0;
            return;
        }
        projectiles.front().moveTo(attachedShip.getX() + firePositions[0], attachedShip.getY());
        cooldown = 0;

        firedProjectiles.append(projectiles.front());
        projectiles.dequeue();
    }

    public void moveProjectiles()
    {
        firedProjectiles.toFirst();
        while (firedProjectiles.hasAccess()) {
            firedProjectiles.getContent().move();
            if (firedProjectiles.getContent().getY() < -20 || firedProjectiles.getContent().getY() > 820) {
                projectiles.enqueue(firedProjectiles.getContent());
                firedProjectiles.getContent().setHidden(true);
                firedProjectiles.remove();
            }
            firedProjectiles.next();
        }
    }

    public boolean canFire () {
        if (cooldown < cooldownTime) return false;
        else return true;
    }

    protected void addCooldownTime () {
        cooldown += 1;
    }

    public boolean firedProjectilesIntersects (Ship ship) {
        firedProjectiles.toFirst();
        while (firedProjectiles.hasAccess() && !firedProjectiles.getContent().intersects(ship)) {
            firedProjectiles.next();
        }
        if (firedProjectiles.hasAccess()) return true;
        else return false;
    }

    /* ****************** Getter and Setter ****************** */
    public void setAttachedShip(Ship attachedShip) {
        this.attachedShip = attachedShip;
    }
    
    public int getDamage() {
        return damage;
    }
}
