package ships;

public class Squasher extends Ship
{
    public Squasher(int x, int y)
    {
        super("bomber", x, y, 70, 70, new double[] {1, 0.5}, null);
    }
    
    public void move() {
        super.move(90);
        if (getX() > 810) { //810 Bildgröße -> const in config
            moveTo(getOriginX(), getY()+70);
        }
    }
}
