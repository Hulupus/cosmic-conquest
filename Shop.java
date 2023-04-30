import sas.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Shop
{
    //gekaufte Items
    private ArrayList<Item> boughtItems;
    //aktuelle Items im Shop
    private ArrayList<Item> inShopItems;
    //alle verfügbaren Items
    private ArrayList<Item> avaItems;
    //alle Items
    private ArrayList<Item> allItems;
    private Picture shopBackground;

    public Shop()
    {
        allItems = new ArrayList<>(); // nötig? wenn ja für andere hinzufügen!

        allItems.add(new Item("triebwerk", 1, 0.5, 2, 40, 100, 0));
        allItems.add(new Item("rocket", 2, 1.5, 3, -0.5, 75, 0));
        allItems.add(new Item("verstärkung", 0, 1, 1, -0.2, 100, 0));

        shopBackground = new Picture(800, 50, 700, 700, "Cosmic_Conquest_shopbackground.png");
    }

    public void openShop () {
        for (int i = 0; i < 150; i++){
            shopBackground.move(-5,0);
            for (int j = 0; j < inShopItems.size(); j++){
                inShopItems.get(j).move(-5,0);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void closeShop() {
        for (int i = 0; i < 150; i++){
            shopBackground.move(5,0);
            for (int j = 0; j < inShopItems.size(); j++){
                inShopItems.get(j).move(5,0);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void buyItem() {
        for (int i = 0; i < inShopItems.size(); i++){
            if (inShopItems.get(i).clicked()){
                /*if (Kontrollzentrum.getMoney() >= inShopItems.get(i).getPrice()){
                    inShopItems.get(i).setgekauft(true);
                    boughtItems.add(inShopItems.get(i));
                    inShopItems.remove(i);
                    Kontrollzentrum.setMoney(Kontrollzentrum.getMoney() - inShopItems.get(i).getPrice());
                }*/
            }
        }
    }

    public void fillShop() { //beim Ende eines Levels ausführen 
        // Zu 4 ändern sobald mehr Items hinzugefügt
        for (int i = 0; i < 3; i++){
            int pos = Tools.randomNumber(0,avaItems.size() - 1);
            inShopItems.add(avaItems.get(pos));
            avaItems.remove(pos);
        }
    }

    public void emptyShop() { //beim Start eines Levels ausführen
        // Zu 4 ändern sobald mehr Items hinzugefügt
        for (int i = 0; i < 3;i++){
            avaItems.add(inShopItems.get(i));
            inShopItems.remove(i);
        }
    }

    public void unlockItems(int playedLvl) { //beim Ende eines Levels ausführen 
        for (int i = 0; i < allItems.size(); i++){
            if (allItems.get(i).getUnlockLvl() == playedLvl){
                avaItems.add(allItems.get(i));
                allItems.remove(i);
            }
        }
    }
}
