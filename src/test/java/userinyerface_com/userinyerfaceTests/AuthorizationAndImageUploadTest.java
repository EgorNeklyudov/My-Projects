package userinyerface_com.userinyerfaceTests;

import framework.configuration.Configuration;
import framework.userinyerface.pageObjects.pages.GamePage;
import framework.userinyerface.utils.FileUploader;
import framework.userinyerface.utils.RandomStringGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import userinyerface_com.baseTest.BaseTest;

import java.io.IOException;
import java.util.Properties;

public class AuthorizationAndImageUploadTest extends BaseTest {

    GamePage gamePage = new GamePage();
    private final Properties properties = new Properties();

    @Test
    public void authorizationAndImageUploadTestCase() throws IOException {

        properties.load(ClassLoader.getSystemResourceAsStream("userIn.properties"));
        String password = properties.getProperty("pass.path");
        String randomString = new RandomStringGenerator().randomString();
        String email = properties.getProperty("email.path");
        String domain = properties.getProperty("domain.path");

        Assert.assertEquals(getBrowser().getCurrentUrl(), Configuration.getStartUrl());
        gamePage.clickOnLink();
        getBrowser().waitForPageToLoad();
        Assert.assertTrue(gamePage.isAuthorizationFormDisplayed(),"Failed click on the game start link");
        Assert.assertEquals(gamePage.getAuthorizationForm().getNumberOfCard(), "1 / 4","The card number does not match the expected one !");

        gamePage.getAuthorizationForm().enterPassword(password,randomString);
        gamePage.getAuthorizationForm().enterEmail(email);
        gamePage.getAuthorizationForm().enterDomain(domain);
        gamePage.getDropDownForm().selectOption();
        gamePage.getAuthorizationForm().clickAcceptCheckBox();
        gamePage.getAuthorizationForm().clickNextButton();
        Assert.assertTrue(gamePage.isImageAndInterestsFormDisplayed(),"Authorization failed !");
        Assert.assertEquals(gamePage.getAuthorizationForm().getNumberOfCard(), "2 / 4","The card number does not match the expected one !");

        gamePage.getImageAndInterestsForm().clickUnselectAllCheckBox();
        gamePage.getImageAndInterestsForm().chooseInterests(3);
        gamePage.getImageAndInterestsForm().clickPhotoUploadBtn();
        FileUploader.uploadImage();
        gamePage.getImageAndInterestsForm().clickNextButton();
        Assert.assertTrue(gamePage.isPersonalDetailsFormDisplayed(),"Failed choose random interests or load random image");
        Assert.assertEquals(gamePage.getAuthorizationForm().getNumberOfCard(), "3 / 4","The card number does not match the expected one !");

    }

}
