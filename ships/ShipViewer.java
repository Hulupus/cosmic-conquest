package ships;

import sas.*;

public class ShipViewer
{
    private View v;
    
    private PlayerShip p;
    private Cruiser c;
    private Blockaderunner b;
    
    public ShipViewer()
    {
        v = new View(1000, 1000);
        p = new PlayerShip();
        c = new Cruiser(20, 20);
        b = new Blockaderunner(40, 40);
        
        start();
    }

    public void start() {
        while(!v.keyPressed('e')) {
            p.move(v);
            p.fire(v);
            c.move();
            b.move();
            
            if (c.collides(p)) c.loseLives(1);            
            v.wait(3);
        }
    }
}
