package userinyerface_com.userinyerfaceTests;

import framework.userinyerface.pageObjects.pages.GamePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import userinyerface_com.baseTest.BaseTest;

public class HelpButtonTest extends BaseTest {

    GamePage gamePage = new GamePage();

    @Test
    public void helpButtonTestCase(){

        gamePage.clickOnLink();
        gamePage.getAuthorizationForm().clickHelpButton();
        gamePage.getAuthorizationForm().isHelpWindowHidden();
        Assert.assertTrue(gamePage.getAuthorizationForm().isHelpWindowHidden(),"The form is still not hidden !");

    }
}
