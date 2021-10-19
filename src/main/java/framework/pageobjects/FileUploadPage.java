package framework.pageobjects;

import framework.element.Button;
import framework.element.Loader;
import framework.element.TextField;
import framework.utils.Logger;
import org.openqa.selenium.By;

public class FileUploadPage extends BaseForm{

    private  Button BUTTON_FILE_UPLOAD = new Button(By.xpath("//a[contains(text(),'File Upload')]"),"file upload btn");
    private  Loader CHOOSE_FILE = new Loader(By.xpath("//*[@id=\"file-upload\"]")," choosing file for upload");
    private  Button BUTTON_UPLOAD = new Button(By.xpath("//*[@id=\"file-submit\"]")," upload btn");
    private  TextField GET_UPLOADED_FILE = new TextField(By.xpath("//*[@id=\"uploaded-files\"]")," uploaded file text");

    public FileUploadPage(By locator, String nameOfPage) {
        super(locator, nameOfPage);
    }

    public void clickFileUpload(){
        BUTTON_FILE_UPLOAD.click();
    }

    public void fileUploadPageIsOpen(){
        Logger.writeLog("File Upload page is open");
        PageIsPresent(By.xpath("*//div[@class='example']"),"File Upload Page");
    }

    public void selectAFile(){
        CHOOSE_FILE.uploadFile();
    }

    public void uploadClick(){
        BUTTON_UPLOAD.click();
    }

    public void fileUploadedTextIsPresent(){
        Logger.writeLog("File Uploaded!");
        PageIsPresent(By.xpath("//*[contains(text(),'File Uploaded!')]"));
    }

    public String getUploadedFiles(){
        return GET_UPLOADED_FILE.getText();
    }

    public FileUploadPage() {
    }
}
