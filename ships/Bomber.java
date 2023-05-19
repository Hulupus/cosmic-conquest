package ships;

public class Bomber extends Schiff
{
    public Bomber(int x, int y)
    {
        super("bomber", x, y, 70, 70, new double[] {1, 0.7});
    }

    public void move() {
        super.move(180);
    }
}
