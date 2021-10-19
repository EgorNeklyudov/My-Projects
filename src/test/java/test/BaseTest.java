package test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import framework.configuration.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    protected void beforeClass() {
        getBrowser().goTo(Configuration.getStartUrl());
        getBrowser().maximize();

    }

    @AfterClass
    public void afterClass() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }

    protected Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}
