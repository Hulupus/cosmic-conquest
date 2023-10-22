import sas.*;

public class Explosion
{
    private Picture explosion;
    private int showTime;
    
    public Explosion()
    {
        this.explosion = new Picture(0, 0, 60, 60, "assets/views/Explosion.png");
        this.showTime = 0;
    }

    public boolean getHidden() {
        return explosion.getHidden();
    }
    
    public void setHidden(boolean hidden) {
        explosion.setHidden(hidden);
    }
}
