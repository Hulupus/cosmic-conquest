package ships;

import sas.Picture;
import sas.View;

public class PlayerShip extends Ship
{
    
    public PlayerShip()
    {
        super(
            "player", 
            365, 
            800, 
            70, 
            70, 
            new double[] {3, 1},
            new Weapon(
                new Laser("green"),
                5,
                120,
                60
            )
        );
    }
    
    public void move(View v) {
        if (v.keyPressed('a') && getX() >= 0) {
            super.move(270);
        }
        if (v.keyPressed('d') && getX() <= v.getWidth() - 70) {
            super.move(90);
        }
    }
    
    public void fire(View v) {
        if (v.keyPressed(' ')) {
            super.fire();
            return;
        }
        weapon.moveLasers();
    }
}
