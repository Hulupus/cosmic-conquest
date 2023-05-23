import sas.View;
import sas.Text;
import ships.*;

import java.awt.Color;
import java.util.ArrayList;
import java.awt.MouseInfo;

public class Levelbuilder
{
    private View view;

    private Schiff createdShip;
    
    private ArrayList<Schiffposition> shipPositions;
    
    private ArrayList<Cruiser> cruiser;
    
    private Text modeStatus;

    public Levelbuilder()
    {
        view = new View(800, 800, "Levelbuilder Cosmic Conquest");
        
        cruiser = new ArrayList<>();
        
        shipPositions = new ArrayList <>();
        
        //Status Text
        Text modeText = new Text(10, 0, "Modus:");
        modeText.setFontSerif(true, 30);
        modeStatus = new Text(85, 0, "Editing");
        modeStatus.setFontColor(Color.BLUE);
        modeStatus.setFontSerif(true, 30);
        
        //Run
        startBuilding();
    }

    public void startBuilding() {
        while (true) {
            modeStatus.setText("Building");
            modeStatus.setFontColor(Color.RED);
            
            if (view.keyPressed('c')) {
                cruiser.add(new Cruiser(30, 30));//(int) MouseInfo.getPointerInfo().getLocation().getX() + 680, (int) MouseInfo.getPointerInfo().getLocation().getY() + 140));
                shipPositions.add(new Schiffposition("cruiser", cruiser.size()-1, 30, 30));
                moveShip(cruiser.get(cruiser.size()-1), shipPositions.size()-1);
                resetShips();
            }
            
            if (view.keyPressed('r')) {
                runShips();
            }
        }
    }

    public void moveShip(Schiff createdShip, int arrayIndex) {
        modeStatus.setText("Moving");
        modeStatus.setFontColor(Color.ORANGE);
        
        while (!view.keyEnterPressed()) {
            if (view.keyUpPressed()) {
                createdShip.move(0);
            }
            if (view.keyDownPressed()) {
                createdShip.move(180);
            }
            if (view.keyRightPressed()) {
                createdShip.move(90);
            }
            if (view.keyLeftPressed()) {
                createdShip.move(270);
            }
            view.wait(3);
        }
        
        shipPositions.get(arrayIndex).setPosition((int) createdShip.getX(), (int) createdShip.getY());
    }

    public void runShips() {
        modeStatus.setText("Running");
        modeStatus.setFontColor(Color.GREEN);
        
        view.wait(100);
        
        while (!view.keyPressed('r')) {
            for (int i = 0; i < cruiser.size(); i++) {
                cruiser.get(i).move();
            }
            
            view.wait(3);
        }
        
        view.wait(100);
        
        resetShips();
    }
    
    public void resetShips()
    {
        for (int i = 0; i < shipPositions.size(); i++) {
            if (shipPositions.get(i).getType() == "cruiser") {
                cruiser.get(shipPositions.get(i).getArrayIndex()).moveTo(shipPositions.get(i).getX(), shipPositions.get(i).getY());
            }
        }
    }
}
