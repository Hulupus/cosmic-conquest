package ships;

public class Bomber extends Ship
{
    public Bomber(int x, int y)
    {
        super(
            "bomber", 
            x, 
            y, 
            70, 
            70, 
            new double[] {1, 0.6}, 
            null
        );
    }
}
