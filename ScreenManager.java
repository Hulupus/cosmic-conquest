import sas.*;

public class ScreenManager{
    private Picture loading;
    private Picture titlescreen;
    private Picture baseMain;
    private Picture baseShipBay;
    private Picture baseLab;
    
    private String openedScreen;
    public ScreenManager(int height, int width){
        loading = new Picture(0, 0, height, width, "assets/views/Cosmic_Conquest_loadingscreen.png");
        openedScreen = "loading";
        titlescreen = new Picture(0, 0, height, width,  "assets/views/background.png");
        titlescreen.setHidden(true);
        baseMain = new Picture(0, 0, height, width, "assets/base/Cosmic_Conquest_base_background.png");
        baseMain.setHidden(true);
        // baseShipBay = new Picture(0, 0, height, width, "URL");
        // baseShipBay.setHidden(true);
        // baseLab = new Picture(0, 0, height, width, "URL");
        // baseLab.setHidden(true);
    }
    
    public void openScreen(String screen, View view){
        getScreen(openedScreen).setHidden(true);
        
        loading.setHidden(false);
        getScreen(screen).setHidden(false);
        view.wait(3000);
        
        loading.setHidden(true);
        openedScreen = screen;
    }
    
    public Picture getScreen(String screen) {
        switch(screen) {
            case "titlescreen" : 
                return titlescreen; 
            case "baseMain" : 
                return baseMain;
            default :
                return null; //Optinal??
        }
    }
    // public void openFirst(){
        
    // }
    
    public void closeAll(){
        //openedscreen.setHidden(true);
    }
}
