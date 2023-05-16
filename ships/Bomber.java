package ships;

public class Bomber extends Schiff
{
    public Bomber(int x, int y)
    {
        super("cruiser", x, y, 50, 50, new double[] {1, 0.5});
    }

    public void move() {
        super.move(180);
    }
}
