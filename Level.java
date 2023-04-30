
public class Level
{
    //Aussehen
    //Gegner -> Anzahl, Typ, Position
    //Stages
    //Spawning
    
    
    //Weiteren Vorgehen:
    //Logik zum Spawning?
    //->Pixelgröße als Lösung?
    
    private int[][] CruiserPos;
    private int[][] BomberPos;
    
    int LevelNummer;
    
    public void Level(int pLevelNummer, int CruiserAnzahl, int BomberAnzahl, int pCruiserPos[][], int pBomberPos[][]){
        LevelNummer = pLevelNummer;
        
        CruiserPos = new int[CruiserAnzahl][2]; // Array mit der Länge der Anzahl Gegner des Types Cruiser
        for (int i = 0; i < CruiserPos.length; i++){
            CruiserPos[i][1] = pCruiserPos[i][1];
            CruiserPos[i][2] = pCruiserPos[i][2];
        }
        BomberPos = new int[BomberAnzahl][2]; // Array mit der Länge der Anzahl Gegner des Types Bomber
        for (int i = 0; i < BomberPos.length; i++){
            BomberPos[i][1] = pBomberPos[i][1];
            BomberPos[i][2] = pBomberPos[i][2];
        }
    }
    
    public void oeffneLevel(){
        for (int i = 0; i < CruiserPos.length; i++){
            //Kontrollzentrum.enemies[i].moveTo(CruiserPos[i][1], CruiserPos[i][2]);
            Kontrollzentrum.enemyMoveTo(i, CruiserPos[i][1], CruiserPos[i][2]);
        }
        /*for (int i = 0; i < BomberPos.length; i++){
            Bomber[i].moveTo(BomberPos[i][1], BomberPos[i][2]);
        }*/
    }
}
//(CruiserPos[i][1], CruiserPos[i][2])
