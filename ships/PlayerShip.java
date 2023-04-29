package ships;

import sas.*;

public class PlayerShip extends MitGeschuetz
{
    
    public PlayerShip()
    {
        super("player", 20, 365, 800, 70, 70);
        setFirePosition(70/2);
    }
    
    public void move(View v) {
        if (v.keyPressed('a') && getX() >= 0) {
            super.move(270, 1);
        }
        if (v.keyPressed('d') && getX() <= v.getWidth() - 70) {
            super.move(90, 1);
        }
    }
    
    public void schießen(View v) {
        if (v.keyPressed(' ') && canFire()) {
            super.schießen();
        } else {
            addCooldownTime();
        }
    }
    
}
