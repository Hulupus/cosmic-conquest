import sas.*;

public class Item{
    //Name des Items
    private String name;
    
    //Vorteil u. Nachteil
    private double[][] aenderung;

    //Bild
    private Picture icon;

    //Preis
    private int price;
    
    //Gekauft?
    private boolean gekauft;
    
    //Wann wird das Item freigeschaltet
    private int unlockLvl;
    //Null eingeben und in ArrayList avaItems einfügen/erzeugen falls von Anfang an freigeschaltet!

    public Item(String name, int aenderungsart1, double aenderung1, int aenderungsart2, double aenderung2, int pprice,int punlockLvl){
        /*
         * Erste Zeile gibt an was verädert werden soll, zweite Zeile gibt an um wie viel.
         * 0 = Lebensanzahl
         * 1 = Fluggeschwindigkeit
         * 2 = Schußhäufigkeit
         * 3 = Schußgeschwindigkeit
         */
        aenderung = new double[2][2];
        aenderung[0][0] = aenderungsart1;
        aenderung[0][1] = aenderung1;
        aenderung[1][0] = aenderungsart2;
        aenderung[1][1] = aenderung2;

        icon = new Picture (800, 100, 100, 100, "Cosmic_Conquest_upgrade_" + name + ".png");
        price = pprice;
        gekauft = false;
        unlockLvl = punlockLvl;
    }
    
    public int getUnlockLvl(){
        return unlockLvl;
    }
    
    public int getPrice(){
        return price;
    }
    
    public boolean clicked(){
        return icon.mouseClicked();
    }
    
    public void setgekauft(boolean x){
        gekauft = x;
    }
    
    public void move(int x, int y){
        icon.move(x,y);
    }
}
