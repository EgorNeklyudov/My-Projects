package framework.userinyerface.pageObjects.forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AuthorizationForm extends Form {

    public AuthorizationForm() {
        super(By.xpath("//div//input[@placeholder='Choose Password']"),"Authorization Form");
    }

    private final ITextBox textBoxPassword = getElementFactory().getTextBox(By.xpath("//div//input[@placeholder='Choose Password']"), "password");
    private final ITextBox textBoxEmail = getElementFactory().getTextBox(By.xpath("//div//input[@placeholder='Your email']"), "email");
    private final ITextBox textBoxDomain = getElementFactory().getTextBox(By.xpath("//div//input[@placeholder='Domain']"), "domain");
    private final ICheckBox checkBox = getElementFactory().getCheckBox(By.xpath("//span[@class='checkbox__box']"), "accept");
    private final IButton nextButton = getElementFactory().getButton(By.xpath("//a[@class='button--secondary']"), "next");
    private final IButton helpButton = getElementFactory().getButton(By.xpath("//span[contains(@class,'highlight') and text()='Send']"), "button help");
    private final IButton cookieButton = getElementFactory().getButton(By.xpath("//button[contains(@class,'button button--solid button--transparent') and text() ='Not really, no']"), "button cookie");
    private final List<WebElement> cookies = AqualityServices.getBrowser().getDriver().findElements(By.xpath("//div[@class='cookies']"));
    private final ILabel timer = getElementFactory().getLabel(By.xpath("//*[contains(@class,'timer')]"), "timer");
    private final ITextBox numberOfCard = getElementFactory().getTextBox(By.xpath("//div[@class='page-indicator']"), "number");
    private final ILabel hiddenOfHelp = getElementFactory().getLabel(By.xpath("//div[@class='help-form']"), "hidden form");
    private final ILabel cookiesForm = getElementFactory().getLabel(By.xpath("//div[@class='cookies']"),"Cookies Form");


    public int getCookieFormSize() { return cookies.size();}

    public void isCookiesFormClosed(){ cookiesForm.state().waitForNotDisplayed();}

    public boolean isHelpWindowHidden() {
        return hiddenOfHelp.state().waitForNotDisplayed();
    }

    public String getNumberOfCard() { return numberOfCard.getText(); }

    public void enterPassword(String password,String randomString)  { textBoxPassword.clearAndType(randomString + password); }

    public void enterEmail(String email)  { textBoxEmail.clearAndType(email); }

    public void enterDomain(String domain) { textBoxDomain.clearAndType(domain); }

    public void clickAcceptCheckBox() { checkBox.click(); }

    public void clickNextButton() { nextButton.clickAndWait(); }

    public void clickHelpButton() { helpButton.click(); }

    public void clickCookieButton() { cookieButton.clickAndWait(); }

    public String getTimerText() { return timer.getText(); }

}
