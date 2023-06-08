import ships.*;
import sasio.*;
import sas.Tools;
import java.util.ArrayList;

public class Level
{
    private ArrayList<ArrayList<Schiffposition>> stages;
    private int activeStage = 0;

    public Level(int levelNummer) {
        stages = new ArrayList<>();
        String fileName = "levels/level" + levelNummer + ".txt";

        if(!StringFileTools.fileExists(fileName)) {
            System.out.print("Level" + levelNummer + " konnte nicht geladen werden");
            return;
        }

        String[] levelConfig = StringFileTools.loadFileInStringArray(fileName);
        for (int i = 0; i < levelConfig.length; i++) {
            //jump to stage
            if (!levelConfig[i].contains("//")) {
                continue;
            }
            i++;
            
            //begin reading (all ships in line)
            ArrayList<Schiffposition> enemies = new ArrayList<>();
            while (!levelConfig[i].contains("//") && i < levelConfig.length) {
                String[] shipGroup = levelConfig[i].split(";");

                int amountOfShips = Integer.parseInt(shipGroup[1]);
                int[] xPositions = turnStringToPositions(shipGroup[2], amountOfShips);
                int[] yPositions = turnStringToPositions(shipGroup[3], amountOfShips);

                for (int j = 0; j < amountOfShips; j++) {
                    enemies.add(new Schiffposition(
                            shipGroup[0],
                            -1,
                            xPositions[j],  
                            yPositions[j]
                        ));
                }

                i++;
            }
            stages.add(enemies);
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
    
    public boolean isCleared() {
        System.out.println(activeStage + ": " + stages.size());
        return activeStage == stages.size();
    }
    
    public boolean isStageCleared(int clearedEnemies) {
        return stages.get(activeStage).size() == clearedEnemies;
    }
    
    public int getActiveStage() {
        return activeStage;
    }
    
    public void setActiveStage(int activeStage) {
        this.activeStage = activeStage;
    }
    
    public ArrayList<Schiffposition> getEnemies() {
        return stages.get(activeStage);
    }
    
    
    /*
     * Config of a level:
     * 1: Type
     * 2: Amount
     * 3: x
     * 4: y
     *  ;  Coordinates (as long as amount) => Integer.parseInt
     *  :: Intervall (only two numbers)    => Tools.randomNumber()
     */
    
}
