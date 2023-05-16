import ships.*;

public class Level
{
    //Aussehen
    //Gegner -> Anzahl, Typ, Position
    //Stages
    //Spawning
    
    
    //Weiteren Vorgehen:
    //Logik zum Spawning?
    //->Pixelgröße als Lösung?
    
    // private Stage[] stages;
    
    private Stage stage1;
    private int activeStage;
    private int LevelNummer;
    
    public void Level(int LevelNummer) {//int pLevelNummer, int CruiserAnzahl, int BomberAnzahl, int pCruiserPos[][], int pBomberPos[][]){
        this.LevelNummer = LevelNummer;
        
        //for (int i = 0; i <
        stage1 = new Stage();
    }
    
    // public void openNextStage(){
        
    // }
    
    public boolean levelzuende(){
        if (activeStage == stage.length){
           return true;
        }
        return false;
    }
    
    // public boolean stagezuende(){
        // if (/*alle Schiffe hidden/nicht aktiv*/){
            // return true;
        // }
        // return false;
    // }
}
