package ships;

public class Lander extends Ship
{
    public Lander(int x, int y)
    {
        super("bomber", x, y, 70, 70, new double[] {1, 0.7}, null);
    }
}
