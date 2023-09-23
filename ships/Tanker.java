package ships;

public class Tanker extends Ship
{
    
    public Tanker(int xPos, int yPos)
    {
        super("cruiser", xPos, yPos, 50, 50, new double[] {1, 0.2, 180, 3, 50/2}, null);
    }
}
