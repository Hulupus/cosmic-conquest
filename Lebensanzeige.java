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
            hearts.add(new Picture(25*i+10, 750, 37, 30, "assets/views/Herz.png"));
            hearts.get(i).setHidden(true);
        }
    }
    
    public void removeHearts(int amount) {
        for (int i = 0; i < amount; i++) {
            if (currentAmountOfHearts == 0) return;
            hearts.get(currentAmountOfHearts-1).setHidden(true);
            currentAmountOfHearts--;
        }
    }
    
    public void showHearts() {
        for (int i = 0; i < currentAmountOfHearts; i++) {
            hearts.get(i).setHidden(false);
        }
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
