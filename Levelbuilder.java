import sas.View;
import sas.Text;
import sas.Tools;
import sasio.StringFileTools;

import ships.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
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
                cruiser.get(cruiser.size()-1).setVeFlight(1);
                shipPositions.add(new Schiffposition("Cruiser", cruiser.size()-1, 30, 30));
                moveShip(cruiser.get(cruiser.size()-1), shipPositions.size()-1);
            }
            
            if (view.keyPressed('b')) {
                bomber.add(new Bomber(30, 30));
                bomber.get(bomber.size()-1).setVeFlight(1);
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
            
            if (view.keyPressed('s')) {
                saveStageToFile();
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
            /*
             * Delete Ship
             * -> return;
             */
            view.wait(3);
        }
        
        shipPositions.get(arrayIndex).setPosition(createdShip.getX(), createdShip.getY());
        createdShip.moveTo(createdShip.getX(), createdShip.getY());
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
    
    /**
     * Diese Methode bewegt die gespeicherten Schiffe zurück zu ihren Positionen.
     */
    public void resetShips()
    {
        for (int i = 0; i < shipPositions.size(); i++) {
            getShip(shipPositions.get(i)).moveTo(shipPositions.get(i).getX(), shipPositions.get(i).getY());
        }
    }
    
    public Schiff getShip(Schiffposition ship) { //->Optional ??
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
    
    /**
     * Diese Methode erstellt Schiffe 
     */
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
                int[] xPositions = turnStringToPositions(shipGroup[2], amountOfShips, 0);
                int[] yPositions = turnStringToPositions(shipGroup[3], amountOfShips, 800);
                
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
    
    private int[] turnStringToPositions(String strPositions, int amountOfPositions, int offset) {
        int[] positions = new int[amountOfPositions];
        if (strPositions.contains(",")) {
            String[] arrayPos = strPositions.split(",");
            for (int i = 0; i < amountOfPositions; i++) {
                positions[i] = Integer.parseInt(arrayPos[i]) + offset;
            }
        } else if (strPositions.contains("::")) {
            String[] intervallPos = strPositions.split("::");
            for (int i = 0; i < amountOfPositions; i++) {
                positions[i] = Tools.randomNumber(
                    Integer.parseInt(intervallPos[0]), 
                    Integer.parseInt(intervallPos[1])
                ) + offset;
            }
        }
        return positions;
    }
    
    public void saveStageToFile() {
        modeStatus.setText("Saving");
        modeStatus.setFontColor(Color.CYAN);
        view.keyBufferDelete();
        
        String levelNum = Tools.inputDialog("In welchem Level soll das Layout gespeichert werden?");
        if (levelNum == null) {return;} 
        String file = "levels/" + levelNum + ".txt";
        
        int stage = Integer.parseInt(Tools.inputDialog("Als welche Stage?"));
        
        String levelData = "";
        levelData += "//Stage " + stage + "\n";
        
        //Prep-ing
        ArrayList<String> usedTypes= new ArrayList<>();
        for(int j = 0; j < shipPositions.size(); j++) {
            if (usedTypes.contains(shipPositions.get(j).getType())) {continue;}
            
            String type = shipPositions.get(j).getType();
            int amountOfShips = 0;
            List<String> xPositions = new ArrayList<>();
            List<String> yPositions = new ArrayList<>();
            
            for(int i = 0; i < shipPositions.size(); i++) {
                if (!shipPositions.get(i).getType().equals(type)) {continue;}
                
                amountOfShips++;
                xPositions.add(shipPositions.get(i).getX() + "");
                yPositions.add(shipPositions.get(i).getY() - 800 + "");
            }
            
            levelData += type + ";" + amountOfShips + ";" + String.join(",", xPositions) + ";" + String.join(",", yPositions) + "\n";
            usedTypes.add(type);
        }
        levelData += "//Stage " + stage;
        
        //Writing
        if (!StringFileTools.fileExists(file)) {
            StringFileTools.writeInFile(file, levelData);
            return;
        }
        
        StringBuilder fileLevelData = new StringBuilder(StringFileTools.loadFileInString(file));
        
        if (fileLevelData.indexOf("//Stage " + stage) < 0) {
            int insertionIndex = 0;
            for(int i = stage-1; i > 0; i--) {
                if (fileLevelData.indexOf("Stage " + i) < 0) {continue;}
                insertionIndex = fileLevelData.lastIndexOf("//Stage " + i) + ("//Stage " + i).length();
                break;
            }
            fileLevelData.insert(
                insertionIndex,
                insertionIndex == 0 ? levelData+"\n\n" : "\n\n"+levelData
            );
        } else {
            fileLevelData.replace(
                fileLevelData.indexOf("//Stage " + stage),  //Start
                fileLevelData.lastIndexOf("//Stage " + stage) + ("//Stage " + stage).length(), //Stopp
                levelData //Content
            );
        }
        StringFileTools.writeInFile(file, fileLevelData.toString());
    }
}
