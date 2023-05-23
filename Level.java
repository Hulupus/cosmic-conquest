import ships.*;
import sasio.*;
import sas.Tools;

public class Level
{
    private Stage stage1;
    private int activeStage;
    
    public Level(int levelNummer) {
        String fileName = "levels/level" + levelNummer + ".txt";
        
        if(!StringFileTools.fileExists(fileName)) {
            System.out.print("Level" + levelNummer + " konnte nicht geladen werden");
            return;
        }
        
        String[] levelConfig = StringFileTools.loadFileInStringArray(fileName);
        for (int i = 0; i < levelConfig.length; i++) {
            //jump to stage to begin reading
            while (!levelConfig[i].contains("//") && i < levelConfig.length) {continue;}
            i++;
            while (!levelConfig[i].contains("//") && i < levelConfig.length) {
                String[] shipTypes = levelConfig[i].split(";");
                
                //Todo: own func ;; only written to hold on to the idea ;; needs to be 2x because x and y 
                if (levelConfig[2].contains(",")) {
                    String[] xPositions = levelConfig[1].split(",");
                    //pos.länge muss =Amount sein
                    int[] positions = new int[Integer.parseInt(levelConfig[1])];
                    for (int j = 0; j < Integer.parseInt(levelConfig[1]); j++) {
                        positions[j] = Integer.parseInt(xPositions[j]);
                    }
                } else if (levelConfig[2].contains("::")) {
                    String[] xPositions = levelConfig[1].split("::");
                    //Pos.länge muss 2 sein
                    int[] positions = new int[Integer.parseInt(levelConfig[1])];
                    for (int j = 0; j < Integer.parseInt(levelConfig[1]); j++) {
                        positions[j] = Tools.randomNumber(Integer.parseInt(xPositions[0]), Integer.parseInt(xPositions[1]));
                    }
                }
                
                
                /*
                 * Config:
                 * 1: Type
                 * 2: Amount
                 * 3: x
                 * 4: y
                 *  ;  Coordinates (as long as amount) => Integer.parseInt
                 *  :: Intervall (only two numbers)    => Tools.random
                 */
                
                
                //if includes , => cordinates
                //if includes ; => intervall => Tools.random();
                
                //for (xPositions) {}
                //Integer.parseInt();//pos x
                //pos y
                String a = shipTypes[0];//type 
                //arr Pos; -1
                i++;
            }
            //new Stage();
        }
    }
    
    // public void openNextStage(){
        
    // }
    
    public boolean levelzuende(){
        // if (activeStage == stage.length){
           // return true;
        // }
        return false;
    }
    
    public String[] getStageEnemies() {
        return stage1.getEnemyTypes();
    }
    // public boolean stagezuende(){
        // if (/*alle Schiffe hidden/nicht aktiv*/){
            // return true;
        // }
        // return false;
    // }
}
