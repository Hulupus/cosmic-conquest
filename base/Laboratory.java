package base;

import sas.*;
import java.awt.Color.*;

// Hier kann man neue Upgrades herstellen
public class Laboratory extends Building{
    Rectangle test;
    public Laboratory(){
        super("laboratory.png(WIP)", 400, 800, 20, 20,  new int[] {3,6,1,1,7,0});
        test = new Rectangle (375, 400, 50, 75);
    }
    
    
}
