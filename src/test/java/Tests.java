import framework.driver.DriverUtils;
import framework.pageobjects.InfiniteScrollPage;
import framework.pageobjects.FileUploadPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests extends BaseTest {

    @Test
    public void InfiniteScrollTest() {

        DriverUtils.getMainUrl();
        InfiniteScrollPage infiniteScrollPage = new InfiniteScrollPage();
        infiniteScrollPage.clickScroll();
        infiniteScrollPage.infiniteScrollPageIsOpen();
        infiniteScrollPage.scrollToParagraph(23);
        Assert.assertEquals(infiniteScrollPage.getNumberPresentParagraphs(),23,"The number of paragraphs is not equal to the age of the engineer!!");
    }

    @Test
    public void UploadFileTest() {

        DriverUtils.getMainUrl();
        FileUploadPage fileUploadPage = new FileUploadPage();
        fileUploadPage.clickFileUpload();
        fileUploadPage.fileUploadPageIsOpen();
        fileUploadPage.selectAFile();
        fileUploadPage.uploadClick();
        fileUploadPage.fileUploadedTextIsPresent();
        Assert.assertEquals(fileUploadPage.getUploadedFiles(),"chromedriver","Uploaded is incorrect!!");
    }
}
