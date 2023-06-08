package base;
import sas.*;

public class screenmanager{
    private Picture screen0Loading;
    private Picture screen1Basemain;
    private Picture screen2ShipBay;
    private Picture screen3Labor;
    
    private Picture openedscreen;
    public screenmanager(){
        Picture openedscreen = screen0Loading;
    }
    
    public void openscreen(Picture screen){
        openedscreen.setHidden(true);
        openedscreen = screen;
        screen.setHidden(false);
    }
    
    // public void openFirst(){
        
    // }
    
    public void closeAll(){
        openedscreen.setHidden(true);
    }
}
