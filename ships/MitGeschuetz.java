package ships;

import sas.*;
import java.util.ArrayList;

public class MitGeschuetz extends Schiff
{
    private Picture[] lasers;
    private int firePosition;
    
    //Artribute:
    private double tSchuss;
    private int cooldown;
    private double vSchuss;
    
    public MitGeschuetz(String type, int laserCount, int x,int y, int width, int height)
    {
       super(type, x, y, width, height);
       
       firePosition = 0;
       
       tSchuss = 180;
       cooldown = 0;
       vSchuss = 3;
       
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
        boolean hasFired = false;
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
