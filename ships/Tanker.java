package ships;

public class Tanker extends MitGeschuetz
{
    
    public Tanker(int xPos, int yPos)
    {
        super("cruiser", 20, xPos, yPos, 50, 50, new double[] {1, 0.2, 180, 3, 50/2});
    }
}
