package framework.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class WelcomePage extends Form {

    private final ITextBox loginField = super.getElementFactory().getTextBox(By.id("index_email"), " Login field");
    private final ITextBox passwordField = super.getElementFactory().getTextBox(By.id("index_pass"), "Password field");
    private final IButton enterButton = super.getElementFactory().getButton(By.id("index_login_button"), "Enter button");

    public WelcomePage() {super(By.id("index_login"), "VK welcome page");}

    public void typeLogin(String login) {
        loginField.clearAndType(login);
    }

    public void typePassword(String password) {
        passwordField.clearAndType(password);
    }

    public void clickEnterButton() {
        enterButton.click();
    }

}
