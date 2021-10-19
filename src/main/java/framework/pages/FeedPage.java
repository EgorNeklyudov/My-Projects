package framework.pages;

import aquality.selenium.forms.Form;
import framework.forms.SideBarForm;
import org.openqa.selenium.By;

public class FeedPage extends Form {

    private final SideBarForm sideBarForm = new SideBarForm();

    public FeedPage() {
        super(By.id("main_feed"), "VK feed page");
    }

    public void clickMyPageButton() {
        sideBarForm.clickMyPageButton();
    }

}
