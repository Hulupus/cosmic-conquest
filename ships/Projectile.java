package ships;

import sas.Picture;

public class Projectile
{
    private Picture projectile;
    private int width;
    private int height;
    private String imageURL;
    
    // Attribute:
    private int damage;
    private double speed;
    private double direction;
    private int orginXPosition;
    
    public Projectile(int xPosition, int yPosition, int width, int height, String image, int damage, double speed, double direction)
    {
        this.projectile = new Picture(xPosition, yPosition, width, height, image);
        this.width = width;
        this.height = height;
        this.imageURL = image;
        this.projectile.setHidden(true);
        this.orginXPosition = xPosition;
        this.damage = damage;
        this.speed = speed;
        this.direction = direction;
        this.projectile.setDirection(direction);
    }
    
    public void move() {
        projectile.move(speed);
    }
    
    public void moveTo(double x, double y) {
        projectile.moveTo(x, y);
        projectile.setHidden(false);
        orginXPosition = (int) x;
    }
    
    public boolean intersects(Ship ship) {
        if (projectile.intersects(ship.getShip())) return true;
        else return false;
    }
    
    @Override
    public Projectile clone() {
        Projectile cloneProjectile = new Projectile(
            orginXPosition,
            this.getY(),
            width,
            height,
            imageURL,
            damage,
            speed,
            direction
        );
        return cloneProjectile;
    }
    
    /* ************* Getter And Setter ************* */
    public int getY() {
        return (int) projectile.getShapeY();
    }
    
    public void setHidden(boolean hidden) {
        projectile.setHidden(hidden);
    }
    
    public int getDamage() {
        return damage;
    }
}
