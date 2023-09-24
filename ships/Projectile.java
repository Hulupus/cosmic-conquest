package ships;

import sas.Picture;

public class Projectile
{
    private Picture projectile;
    private double speed;
    private double direction;
    private int orginXPosition;
    
    public Projectile(int xPosition, int yPosition, int width, int height, String image, double speed, double direction)
    {
        this.projectile = new Picture(xPosition, yPosition, width, height, image);
        this.projectile.setHidden(true);
        this.orginXPosition = xPosition;
        this.speed = speed;
        this.direction = direction;
    }
    
    public void move() {
        projectile.move(speed, direction);
    }
    
    public void moveTo(int x, double y) {
        projectile.moveTo(x, y);
        this.projectile.setHidden(false);
        orginXPosition = x;
    }
    
    /* ************* Getter And Setter ************* */
    public void setHidden(boolean hidden) {
        projectile.setHidden(hidden);
    }
}
