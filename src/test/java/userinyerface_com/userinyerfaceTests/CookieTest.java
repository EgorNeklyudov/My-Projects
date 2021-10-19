package userinyerface_com.userinyerfaceTests;

import framework.userinyerface.pageObjects.pages.GamePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import userinyerface_com.baseTest.BaseTest;

public class CookieTest extends BaseTest {

    GamePage gamePage = new GamePage();

    @Test
    public void cookieTestCase(){

        gamePage.clickOnLink();
        gamePage.getAuthorizationForm().clickCookieButton();
        gamePage.getAuthorizationForm().getCookieFormSize();
        Assert.assertEquals(gamePage.getAuthorizationForm().getCookieFormSize(), 0," The size of the cookie differs from the expected one !");
        gamePage.getAuthorizationForm().isCookiesFormClosed();

    }
}
