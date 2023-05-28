import sas.View;
import sas.Text;
import sas.Tools;
import sasio.StringFileTools;

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
    private ArrayList<Bomber> bomber;
    
    private String status;
    private Text modeStatus;

    public Levelbuilder()
    {
        view = new View(800, 800, "Levelbuilder Cosmic Conquest");
        
        cruiser = new ArrayList<>();
        bomber = new ArrayList<>();
        
        shipPositions = new ArrayList <>();
        
        //Status Text
        Text modeText = new Text(10, 0, "Modus:");
        modeText.setFontSerif(true, 30);
        modeStatus = new Text(85, 0, "Building");
        modeStatus.setFontColor(Color.RED);
        modeStatus.setFontSerif(true, 30);
        
        //Run
        startBuilding();
    }

    public void startBuilding() {
        loadSavedStage();
        while (true) {
            modeStatus.setText("Building");
            modeStatus.setFontColor(Color.RED);
            
            if (view.keyPressed('c')) {
                cruiser.add(new Cruiser(30, 30));
                shipPositions.add(new Schiffposition("Cruiser", cruiser.size()-1, 30, 30));
                moveShip(cruiser.get(cruiser.size()-1), shipPositions.size()-1);
            }
            
            if (view.keyPressed('b')) {
                bomber.add(new Bomber(30, 30));
                shipPositions.add(new Schiffposition("Bomber", bomber.size()-1, 30, 30));
                moveShip(bomber.get(bomber.size()-1), shipPositions.size()-1);
            }
            
            if (shipPositions.size() == 0) {continue;}
            
            if (view.keyPressed('e')) {
                editShipPositions();
            }
            
            if (view.keyPressed('r')) {
                runShips();
            }
        }
    }

    public void moveShip(Schiff createdShip, int arrayIndex) {
        modeStatus.setText("Moving");
        modeStatus.setFontColor(Color.ORANGE);
        
        view.keyBufferDelete();
        
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
        createdShip.moveTo((int) createdShip.getX(), (int) createdShip.getY());
    }

    public void runShips() {
        modeStatus.setText("Running");
        modeStatus.setFontColor(Color.GREEN);
        
        view.keyBufferDelete();
        
        while (!view.keyPressed('r')) {
            for (int i = 0; i < cruiser.size(); i++) {
                cruiser.get(i).move();
            }
            for (int i = 0; i < bomber.size(); i++) {
                bomber.get(i).move();
            }
            view.wait(3);
        }
        
        view.keyBufferDelete();
        
        resetShips();
    }
    
    public void resetShips()
    {
        for (int i = 0; i < shipPositions.size(); i++) {
            getShip(shipPositions.get(i)).moveTo(shipPositions.get(i).getX(), shipPositions.get(i).getY());
        }
    }
    
    public Schiff getShip(Schiffposition ship) {
        if (ship.getType().equals("Cruiser")) {
            return cruiser.get(ship.getArrayIndex());
        }
        else if (ship.getType().equals("Bomber")) {
            return bomber.get(ship.getArrayIndex());
        }
        return null;
    }
    
    public void editShipPositions() {
        modeStatus.setText("Editing");
        modeStatus.setFontColor(Color.PINK);
        
        view.keyBufferDelete();
        int chosenShip = 0;
        int time = 0;

        while (!view.keyPressed('e')) {
            Schiff ship = getShip(shipPositions.get(chosenShip));
            
            //Auswählen
            if (view.keyRightPressed() && chosenShip < shipPositions.size()-1) {
                ship.toggleHidden(false);
                chosenShip++;
                view.keyBufferDelete();
            }
            if (view.keyLeftPressed() && chosenShip > 0) {
                ship.toggleHidden(false);
                chosenShip--;
                view.keyBufferDelete();
            }
            
            //Blinken
            if (time == 190 && !ship.getHidden()) {
                ship.toggleHidden(true);
                time = 0;
            } else if (time == 70 && ship.getHidden()) {
                ship.toggleHidden(false);
                time = 0;
            }
            
            //Auswahl Bestätigen
            if (view.keyEnterPressed()) {
                ship.toggleHidden(false);
                moveShip(ship, shipPositions.get(chosenShip).getArrayIndex());
                modeStatus.setText("Editing");
                modeStatus.setFontColor(Color.PINK);
                view.keyBufferDelete();
            }
            
            time++;
            view.wait(3);
        }
        
        getShip(shipPositions.get(chosenShip)).toggleHidden(false);
        view.keyBufferDelete();
    }
    
    public void loadSavedStage() {
        if (Tools.confirmDialog("Soll ein Level geladen werden?") == 1) {return;}
        
        String chosenFile;
        
        do {
            chosenFile = Tools.inputDialog("Welche Datei soll geladen werden?");
            if (chosenFile == null) {
                return;
            } else if (!StringFileTools.fileExists("levels/" + chosenFile + ".txt")) {
                Tools.message("Datei konnte nicht gefunde werden", "ERROR");
            }
        } while (!StringFileTools.fileExists("levels/" + chosenFile + ".txt"));
        
        //Stage Abfrage
        String stageNum = Tools.inputDialog("Welche Stage soll geladen werden?");
        //Laden
        String[] levelConfig = StringFileTools.loadFileInStringArray("levels/" + chosenFile + ".txt");
        
        for (int i = 0; i < levelConfig.length; i++) {
            if (!levelConfig[i].contains("//Stage " + stageNum)) { //chosenStage
                continue;
            }
            i++;
            
            //begin reading all ships in line
            while (!levelConfig[i].contains("//") && i < levelConfig.length) {
                String[] shipGroup = levelConfig[i].split(";");

                int amountOfShips = Integer.parseInt(shipGroup[1]);
                int[] xPositions = turnStringToPositions(shipGroup[2], amountOfShips);
                int[] yPositions = turnStringToPositions(shipGroup[3], amountOfShips);
                
                for (int j = 0; j < amountOfShips; j++) {
                    int arrIndex = 0;
                    if (shipGroup[0].equals("Cruiser")) {
                        cruiser.add(new Cruiser(xPositions[j], yPositions[j]));
                        arrIndex = cruiser.size()-1;
                    } else if(shipGroup[0].equals("Bomber")) {
                        bomber.add(new Bomber(xPositions[j], yPositions[j]));
                        arrIndex = bomber.size()-1;
                    }
                    shipPositions.add(new Schiffposition(
                            shipGroup[0],
                            arrIndex,
                            xPositions[j],  
                            yPositions[j]
                        ));
                }

                i++;
            }
            return;
        }
    }
    
    private int[] turnStringToPositions(String strPositions, int amountOfPositions) {
        int[] positions = new int[amountOfPositions];
        if (strPositions.contains(",")) {
            String[] arrayPos = strPositions.split(",");
            for (int i = 0; i < amountOfPositions; i++) {
                positions[i] = Integer.parseInt(arrayPos[i]);
            }
        } else if (strPositions.contains("::")) {
            String[] intervallPos = strPositions.split("::");
            for (int i = 0; i < amountOfPositions; i++) {
                positions[i] = Tools.randomNumber(
                    Integer.parseInt(intervallPos[0]), 
                    Integer.parseInt(intervallPos[1])
                );
            }
        }
        return positions;
    }
    
    public void saveStageToFile(String file) {
        //inputDialog
        for(int i = 0; i < shipPositions.size(); i++) {
            
        }
        
        //if (!StringFileTools.fileExists(file)) {
            StringFileTools.writeInFile(file, "");
        //}
        
        
        /*
         * if File does not exist -> new file -> write line
         * if File does exist -> 
         *  if Stage does not exist -> new //Stage -> write line
         *  if Stage does exist -> overwrite the file
         *      
         */
    }
}
