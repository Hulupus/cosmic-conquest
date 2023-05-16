public class Stage
{
    private String[] enemytyp;
    private int[] enemyXPos;
    private int[] enemyYPos;
    
    public Stage(String[] enemytyp, int[] enemyXPos, int[] enemyYPos){
        this.enemytyp = enemytyp;
        this.enemyXPos = enemyXPos;
        this.enemyYPos = enemyYPos;
    }

    public String[] getEnemyTypes(){
        return enemytyp;
    }
}
