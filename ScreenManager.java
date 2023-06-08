import sas.*;

public class ScreenManager{
    private Picture loading;
    private Picture titlescreen;
    private Picture level; //TEMP
    private Picture baseMain;
    private Picture baseShipBay;
    private Picture baseLab;
    
    private String openedScreen;
    public ScreenManager(int height, int width){
        titlescreen = new Picture(0, 0, height, width,  "assets/views/titlescreen.png");
        titlescreen.setHidden(true);
        level = new Picture(0, 0, height, width, "assets/views/background.png");
        level.setHidden(true);
        baseMain = new Picture(0, 0, height, width, "assets/views/base/base_background.png");
        baseMain.setHidden(true);
        // baseShipBay = new Picture(0, 0, height, width, "URL");
        // baseShipBay.setHidden(true);
        // baseLab = new Picture(0, 0, height, width, "URL");
        // baseLab.setHidden(true);
        loading = new Picture(0, 0, height, width, "assets/views/loadingscreen.png");
        openedScreen = "loading";
    }
    
    public void openScreen(String screen, View view){
        getScreen(openedScreen).setHidden(true);
        
        loading.setHidden(false);
        getScreen(screen).setHidden(false);
        view.wait(1500);
        
        loading.setHidden(true);
        openedScreen = screen;
    }
    
    public Picture getScreen(String screen) {
        switch(screen) {
            case "loading" : 
                return loading;
            case "titlescreen" : 
                return titlescreen;
            case "level" :
                return level;
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
