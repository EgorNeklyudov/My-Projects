package framework.userinyerface.pageObjects.forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PersonalDetailsForm extends Form {

    public PersonalDetailsForm() {
        super(By.xpath("//div[@class='personal-details__form']"), "Personal details form");
    }
}
