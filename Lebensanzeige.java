import sas.*;
import java.util.ArrayList;

public class Lebensanzeige
{
    private int maxHearts = 10; //const -> config file ??
    
    private ArrayList<Picture> hearts;
    private int currentAmountOfHearts;
    
    public Lebensanzeige(int startingHearts)
    {
        hearts = new ArrayList<>();
        currentAmountOfHearts = startingHearts;
        for (int i = 0; i < maxHearts; i++) {
            hearts.add(new Picture(25*i+10, 760, 37, 30, "assets/views/Herz.png"));
            if (i > startingHearts-1) {
                hearts.get(i).setHidden(true);
            }
        }
    }
    
    public void removeHeart() {
        if (currentAmountOfHearts == 0) {return;}
        hearts.get(currentAmountOfHearts-1).setHidden(true);
        currentAmountOfHearts--;
    }
    
    public void addHeart() {
        if (maxHearts == currentAmountOfHearts) {return;}
        hearts.get(currentAmountOfHearts).setHidden(false);
        currentAmountOfHearts++;
    }
    
    public void resetHeartsTo(int startingHearts) {
        startingHearts = maxHearts;
        for (int i = 0; i < maxHearts; i++) {
            hearts.get(i).setHidden(false);
        }
    }
    
    public int getAmountOfHearts() {
        return currentAmountOfHearts;
    }
}
