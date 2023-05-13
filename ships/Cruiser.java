package ships;

import sas.*;

public class Cruiser extends MitGeschuetz
{
    public Cruiser(int x, int y)
    {
        super("cruiser", 20, x, y, 50, 50);
        setFirePosition(50/2);
    }
    
    public void move() {
        move(180, 0.5);
    }
    
    public void schießen() {
        if (canFire()) {
            super.schießen();
        } else {
            addCooldownTime();
        }
    }
}
