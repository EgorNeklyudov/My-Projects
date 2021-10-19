package framework.element;

import framework.driver.DriverUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Script {

    private WebDriver driver = DriverUtils.getInstance();

    public void ScrollScript(){
        String script = "window.scrollTo(0,document.body.scrollHeight)";
        var jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(script);
    }
}
