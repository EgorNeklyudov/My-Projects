package framework.userinyerface.utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import org.openqa.selenium.By;
import java.io.IOException;
import java.util.Properties;

import static aquality.selenium.browser.AqualityServices.getElementFactory;

public class DropDown {

    private final Properties properties = new Properties();

    private final ILabel trigger = getElementFactory().getLabel(By.xpath("//div[@class='dropdown__field']"), "dropdown list");

    public DropDown() {
    }

    public  void openDropDownList() {
        trigger.click();
    }

    public void selectOption() throws IOException {
        properties.load(ClassLoader.getSystemResourceAsStream("userIn.properties"));
        String option = properties.getProperty("option.path");
        openDropDownList();
        AqualityServices.getBrowser().getDriver().findElement(By.xpath(String.format("//div[@class='dropdown__list-item' and contains(text(),'%s') ]",option))).click();
    }
}
