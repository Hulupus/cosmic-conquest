package ships;

public class Laser extends Projectile
{
    public Laser(String color)
    {
        super(
            55, 
            700, 
            7, 
            25, 
            "assets/ships/Laser_" + color + ".png",
            color.equals("green") ? 2 : 3,
            color.equals("green") ? 0 : 180
        );
    }
}
