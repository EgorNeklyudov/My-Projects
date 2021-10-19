package framework.element;

import framework.driver.DriverUtils;
import framework.utils.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseElement {

    private final WebDriver driver = DriverUtils.getInstance();
    public By locator;
    public String name;

    public BaseElement(By locator) {
        this.locator = locator;
    }

    public WebElement getElement() {
        Logger.writeLog("Getting element: " + name);
        return driver.findElement(locator);
    }

    public int getSize(){
        return driver.findElements(locator).size();
    }

    public String getText() {
        Logger.writeLog("Getting text of element: " + name);
        return driver.findElement(locator).getText();
    }

    public void click() {
        Logger.writeLog("Click on " + name);
        getElement().click();
    }
}

