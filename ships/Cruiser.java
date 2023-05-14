package ships;

import sas.*;

public class Cruiser extends MitGeschuetz
{
    private static final int MAX_X_MOVE_DISTANZ = 50;
    
    private int moveDirection = 120;
    
    public Cruiser(int xPos, int yPos)
    {
        super("cruiser", 20, xPos, yPos, 50, 50);
        setFirePosition(50/2);
    }
    
    public void move() {
        if (Math.abs(getOriginX()-getX()) > MAX_X_MOVE_DISTANZ)  {
            moveDirection = -moveDirection;
        }
        move(moveDirection, 0.2);
    }
    
    public void schießen() {
        if (canFire() && Tools.randomNumber(0, 600) == 9) {
            super.schießen();
        } else {
            addCooldownTime();
        }
    }
}
