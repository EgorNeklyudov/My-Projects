package framework.element;

import framework.driver.DriverUtils;
import framework.utils.ConfPropertiesManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Loader extends BaseElement{

    private WebDriver driver = DriverUtils.getInstance();

    public Loader(By locator, String name){
        super(locator);
    }

    public void uploadFile(){
        WebElement upload = driver.findElement(locator);
        upload.sendKeys(ConfPropertiesManager.getProperty("file_for_upload"));
    }
}
