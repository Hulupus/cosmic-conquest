import ships.*;
import sasio.*;

public class Level
{
    private Stage stage1;
    private int activeStage;
    private int levelNummer;
    
    public Level(int levelNummer) {//int pLevelNummer, int CruiserAnzahl, int BomberAnzahl, int pCruiserPos[][], int pBomberPos[][]){
        this.levelNummer = levelNummer;
        String fileName = "levels/level" + levelNummer + ".txt";
        
        if(!StringFileTools.fileExists(fileName)) {
            System.out.print("Level" + levelNummer + " konnte nicht geladen werden");
            return;
        }
        
        String[] levelConfig = StringFileTools.loadFileInStringArray(fileName);
        for (int i = 0; i < levelConfig.length; i++) {
            //jump to stage to begin reading
            while (!levelConfig[i].contains("//") && i < levelConfig.length) {
                continue;
            }
            i++;
            while (!levelConfig[i].contains("//") && i < levelConfig.length) {
                String[] shipTypes = levelConfig[i].split("::");
                
                String[] xPositions = levelConfig[1].split(",");
                
                //for (xPositions) {}
                Integer.parseInt();//pos x
                //pos y
                String a = shipTypes[0];//type 
                //arr Pos; -1
                i++;
            }
        }
        
        //for (int i = 0; i <
        //stage1 = new Stage();
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
