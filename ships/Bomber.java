package ships;

public class Bomber extends Schiff
{
    public Bomber(int x, int y)
    {
        super("cruiser", x, y, 50, 50);
    }

    public void move() {
        move(180, 0.5);
    }
}
