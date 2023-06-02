import sas.*;
import sasio.*;
import ships.*;

/**
 * Beschreiben Sie hier die Klasse aba.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class aba
{
    private View view;
    private Blockaderunner b;

    /**
     * Konstruktor für Objekte der Klasse aba
     */
    public aba()
    {
        view = new View(800, 800);
        b = new Blockaderunner(0, 0);
        move();
        // String a = StringFileTools.loadFileInString("levels/level1.txt");
        // System.out.print("a" + "\n" + "as");
    }

    public void move() {
        for (;;) {
            b.move();
            view.wait(3);
        }
    }

}
