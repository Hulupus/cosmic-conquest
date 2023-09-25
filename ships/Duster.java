package ships;

public class Duster extends Ship
{
    private static final int MAX_X_MOVE_DISTANZ = 70;
    
    private int moveDirection = 170;
    
    public Duster(int x, int y)
    {
        super("cruiser", x, y, 40, 40, new double[] {1, 0.1}, null);
    }
    
    // public void move() {
        // if (Math.abs(getOriginX()-getX()) > MAX_X_MOVE_DISTANZ)  {
            // moveDirection = -moveDirection;
        // }
        // super.move(moveDirection);
    // }
}
