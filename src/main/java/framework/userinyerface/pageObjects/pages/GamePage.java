package framework.userinyerface.pageObjects.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import framework.userinyerface.pageObjects.forms.AuthorizationForm;
import framework.userinyerface.pageObjects.forms.ImageAndInterestsForm;
import framework.userinyerface.pageObjects.forms.PersonalDetailsForm;
import framework.userinyerface.utils.DropDown;
import org.openqa.selenium.By;

public class GamePage extends Form {

    public GamePage() {
        super(By.id("app"), "Main Page");
    }

    private final IButton link = getElementFactory().getButton(By.xpath("//a[@href='/game.html']"), "Button");

    private final AuthorizationForm authorizationForm = new AuthorizationForm();
    private final ImageAndInterestsForm imageAndInterestsForm = new ImageAndInterestsForm();
    private final PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();
    private final DropDown dropDown = new DropDown();

    public AuthorizationForm getAuthorizationForm() {
        return authorizationForm;
    }

    public ImageAndInterestsForm getImageAndInterestsForm() {
        return imageAndInterestsForm;
    }

    public PersonalDetailsForm getPersonalDetailsForm(){
        return personalDetailsForm;
    }

    public DropDown getDropDownForm() {
        return dropDown;
    }

    public void clickOnLink() {
        link.click();
    }

    public boolean isAuthorizationFormDisplayed() {
        return authorizationForm.state().waitForDisplayed();
    }

    public boolean isImageAndInterestsFormDisplayed() {
        return imageAndInterestsForm.state().waitForDisplayed();
    }

    public boolean isPersonalDetailsFormDisplayed() {
        return personalDetailsForm.state().waitForDisplayed();
    }

}
