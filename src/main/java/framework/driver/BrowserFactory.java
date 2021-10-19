package framework.driver;

import framework.driver.exception.WrongWebDriverException;
import framework.utils.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

    public static WebDriver driver;

    public static WebDriver getBrowser(String browserName) {
        browserName = browserName.toLowerCase();
        switch (browserName) {
            case "firefox":
                Logger.writeLog("Tests are performed for firefox browser");
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                return new FirefoxDriver(firefoxOptions);
            case "chrome":
                Logger.writeLog("Tests are performed for chrome browser");
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new ChromeDriver(chromeOptions);
                return new ChromeDriver(chromeOptions);
            default:
                throw new WrongWebDriverException(" Didn't find driver " + browserName);
        }
    }
}


