package framework.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SideBarForm extends Form {

    private final IButton myPageButton = super.getElementFactory().getButton(By.id("l_pr"), "My page button");

    public SideBarForm() {
        super(By.id("side_bar"), "Side Bar form");
    }

    public void clickMyPageButton() {
        myPageButton.click();
    }
}
