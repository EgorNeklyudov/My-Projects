package framework.userinyerface.pageObjects.forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Random;

public class ImageAndInterestsForm extends Form {

    private final ICheckBox unselectAll = getElementFactory().getCheckBox(By.xpath("//span[text()[contains(.,'Unselect all')]]"), "unselect all");
    private final ILink photoUpload = getElementFactory().getLink(By.xpath("//a[@class='avatar-and-interests__upload-button']"), "photo upload");
    private final IButton nextButton = getElementFactory().getButton(By.xpath("//button[contains(@class,'button button--stroked button--white button--fluid') and text() ='Next']"), "next button");
    private static final String INTEREST_LOCATOR = "//div[@Class='avatar-and-interests__interests-list__item']//label";

    public ImageAndInterestsForm() {
        super(By.xpath("//body/div[@id='app']"),"Image And Interests Form");
    }

    public void chooseInterests(int amount) {
        unselectAll.click();
        List<ICheckBox> interests = getInterests();
        for (int i = 0; i < amount; i++) {
            int rand = new Random().nextInt(interests.size());
            interests.get(rand).click();
            interests.remove(rand);
        }
    }

    private List<ICheckBox> getInterests() {
        List<ICheckBox> interest = getElementFactory().findElements(By.xpath(INTEREST_LOCATOR), ElementType.CHECKBOX);
        interest.removeIf((o) -> o.getAttribute("for").equals("interest_selectall") ||
                o.getAttribute("for").equals("interest_unselectall"));
        return interest;
        }

    public void clickUnselectAllCheckBox() { unselectAll.click(); }

    public void clickPhotoUploadBtn() {
        photoUpload.click();
    }

    public void clickNextButton() { nextButton.click();}
}
