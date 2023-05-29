package ships;

public class Blockaderunner extends Schiff
{
    public Blockaderunner(int x, int y)
    {
        super("blockaderunner", x, y, 70, 70, new double[] {15, 0.04});
    }
    
    public void move() {
        super.move(180);
    }
}
