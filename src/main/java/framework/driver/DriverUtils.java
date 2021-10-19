package framework.driver;

import framework.utils.ConfPropertiesManager;
import framework.utils.Logger;
import org.openqa.selenium.WebDriver;

public class DriverUtils {

    private static WebDriver instance;

    public DriverUtils() { }

    public static WebDriver getInstance() {
        Logger.writeLog("Initializing the driver");
        if (instance == null) {
            instance = BrowserFactory.getBrowser(ConfPropertiesManager.getProperty("browser.name"));
        }
        return instance;
    }

    public static void maximizeWindow() {
        Logger.writeLog("Maximize window");
        getInstance().manage().window().maximize();
    }

    public static void getMainUrl() {
        Logger.writeLog("Get the test url");
        getInstance().get(ConfPropertiesManager.getProperty("url"));
    }

    public static void quitDriver() {
        if (getInstance() != null) {
            Logger.writeLog("Quit driver and stop testing");
            getInstance().quit();
        }
    }

    public  static void goBack(){
        getInstance().navigate().back();
    }
}
