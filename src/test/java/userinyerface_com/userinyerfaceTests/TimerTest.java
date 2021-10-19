package userinyerface_com.userinyerfaceTests;

import framework.userinyerface.pageObjects.pages.GamePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import userinyerface_com.baseTest.BaseTest;

public class TimerTest extends BaseTest {

    private static final String TIMER_NULL = "00:00:00";
    GamePage gamePage = new GamePage();

    @Test
    public void timerTestCase(){

        gamePage.clickOnLink();
        gamePage.getAuthorizationForm().getTimerText();
        Assert.assertEquals(gamePage.getAuthorizationForm().getTimerText(), TIMER_NULL,"The timer does not start the report from 00:00:00 !");

    }
}
