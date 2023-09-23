package ships;

public class Overseer extends Ship
{
    public Overseer(int xPos, int yPos)
    {
        super("cruiser", xPos, yPos, 50, 50, new double[] {1, 0.2, 180, 3, 50/2}, null);
    }
}
