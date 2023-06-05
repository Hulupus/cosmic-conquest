package ships;

import sas.*;

public class Cruiser extends MitGeschuetz
{
    private int maxMoveDistance = 50; //const??
    
    private int moveDirection = 120;
    
    public Cruiser(int xPos, int yPos)
    {
        super("cruiser", 20, xPos, yPos, 50, 50, new double[] {1, 0.2, 180, 3, 50/2});
    }
    
    public void move() {
        if (Math.abs(getOriginX()-getX()) > maxMoveDistance)  {
            moveDirection = -moveDirection;
        }
        super.move(moveDirection);
    }
    
    public void schießen() {
        if (canFire() && Tools.randomNumber(0, 600) == 9) {
            super.schießen();
        } else {
            addCooldownTime();
        }
    }
}
