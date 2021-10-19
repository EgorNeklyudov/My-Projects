package framework.pageobjects;

import framework.driver.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseForm {

    public String nameOfPage;
    public By locator;

    public BaseForm(By locator,String nameOfPage){
        this.locator = locator;
        this.nameOfPage = nameOfPage;
    }

    public boolean PageIsPresent(By locator){
        return !DriverUtils.getInstance().findElements(locator).isEmpty();
    }

    public boolean PageIsPresent(By locator, String nameOfPage){
        if(!DriverUtils.getInstance().findElements(locator).isEmpty())
            return DriverUtils.getInstance().findElement(locator).getText().equals(nameOfPage);
        else return false;
    }

    public void waitUntilOpen() {
        WebDriverWait downloadingWait = new WebDriverWait(DriverUtils.getInstance(), 5);
        downloadingWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public BaseForm(){}
}
