package ships;

import sas.*;

public class Cruiser extends MitGeschuetz
{
    private Picture lasers[];
    
    public Cruiser(int x, int y)
    {
        super("cruiser", 1, x, y, 50, 50);
        setFirePosition(50/2);
    }
    
    public void move() {
        move(180, 1);
    }
    
    public void schießen(View v) {
        if (canFire()) {
            super.schießen();
        } else {
            addCooldownTime();
        }
    }
}
