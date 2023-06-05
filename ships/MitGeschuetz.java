package ships;

import sas.*;
import java.util.ArrayList;

public class MitGeschuetz extends Schiff
{
    private Picture[] lasers;
    private int cooldown;
    
    //Artribute:
    private double tSchuss;
    private double vSchuss;
    private double firePosition;
    
    public MitGeschuetz(String type, int laserCount, int x,int y, int width, int height, double[] properties)
    {
       super(type, x, y, width, height, properties);
       
       tSchuss = properties[2];
       vSchuss = properties[3];
       firePosition = properties[4];
       
       cooldown = 0;
       
       lasers = new Picture[laserCount];
       for (int i = 0; i < lasers.length; i++){
            if (type == "player") {
                lasers[i] = new Picture (55,700,7,25,"assets/ships/Laser_green.png");
                lasers[i].setDirection(0);
            } else {
                lasers[i] = new Picture (55,700,7,25,"assets/ships/Laser_red.png");
                lasers[i].setDirection(180);
            }
            lasers[i].setHidden(true);
        }
    }
    
    public void schießen() {
        for (int i = 0; i < lasers.length; i++) {
            if (!lasers[i].getHidden()) {continue;}
            lasers[i].moveTo(getX() + firePosition, getY());
            lasers[i].setHidden(false);
            cooldown = 0;
            return;
        }
    }
    
    public void bewegeLaser() {
        for (int i = 0; i < lasers.length; i++) {
            if (lasers[i].getHidden()) {continue;}
            if (lasers[i].getShapeY() < -20 || lasers[i].getShapeY() > 820) {
                lasers[i].setHidden(true);
            }
            lasers[i].move(vSchuss);
        }
    }
    
    public boolean collides(Schiff ship) {
        //collidiert
        if (super.collides(ship)) {
            return true;
        }
        //abgeschossen
        for (int i = 0; i < lasers.length; i++) {
            if (lasers[i].getHidden()) {continue;}
            if (lasers[i].intersects(ship.getSchiff())) {
                lasers[i].setHidden(true);
                return true;
            }
        }
        //nichts passiert
        return false;
    }
    
    public boolean canFire () {
        if (cooldown < tSchuss) {return false;}
        return true;
    }
    
    protected void addCooldownTime () {
        cooldown += 1;
    }
    
    public void setFirePosition(int newPos) {
        firePosition = newPos;
    }
}
