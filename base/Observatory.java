package base;
// Zeigt drei Level zur Auswahl (ein Story Level und zwei zufällig gennerierte Level)
public class Observatory extends Building{
    /**
     * Konstruktor für Objekte der Klasse Observatory
     */
    public Observatory()
    {
        super("Observatory.png(WIP)", 400, 800, 20, 20, new int[] {1,1,1,0,2,7});
    }
}
