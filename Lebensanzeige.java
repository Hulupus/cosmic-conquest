import sas.*;
import java.util.ArrayList;
/**
 * Beschreiben Sie hier die Klasse Lebensanzeige.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Lebensanzeige
{
    ArrayList<Picture> herzen;
    int amountHearts;
    int maxLives;
    
    public Lebensanzeige(int lives, int mxLives)
    {
        herzen = new ArrayList<>();
        amountHearts = lives;
        maxLives = mxLives;
        for (int i = 0; i < mxLives; i++) {
            herzen.add(new Picture(25*i+10, 760, 37, 30, "Herz.png"));
            //Herz = neues Bild(x, y, breite, höhe , datei)
            if (i+1 > lives) {
                herzen.get(i).setHidden(true);
            }
        }
    }
    
    public void removeHeart() {
        //Sperre: Man kann nicht weniger Herzen als 0 haben
        if (amountHearts == 0) {return;}
        herzen.get(amountHearts-1).setHidden(true);
        amountHearts--;
    }
    
    public void addHeart() {
        //Sperre: Man kann nicht mehr Herzen hinzufügen als am Anfang erstellt
        if (maxLives == amountHearts) {return;}
        herzen.get(amountHearts).setHidden(false);
        amountHearts++;
    }
    
    public void resetHearts() {
        amountHearts = maxLives;
        for (int i = 0; i < maxLives; i++) {
            herzen.get(i).setHidden(false);
        }
    }
    
    public int getAmountHearts() {
        return amountHearts;
    }
    
    public void moveto(double x,double y){
        amountHearts = maxLives;
        for (int i = 0; i < maxLives; i++) {
            herzen.get(i).moveTo(x,y);
        }
    }
}
