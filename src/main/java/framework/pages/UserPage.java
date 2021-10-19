package framework.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.elements.Button;
import aquality.selenium.elements.TextBox;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.protocol.BasicHttpContext;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;


public class UserPage extends Form {

    private static final String POST_ID_FORMAT = "post%d_%d";
    private static final String POST_AUTHOR_LOCATOR = "h5[class*='post_author']";
    private static final String POST_TEXT_LOCATOR = "div[class*='wall_post_text']";
    private static final String POST_PHOTO_LOCATOR = "a[class*='image_cover']";
    private static final String NEXT_POST_COMMENTS_BUTTON_LOCATOR = "a[class*='replies_next']";
    private static final String FIRST_POST_REPLY_LOCATOR = "div[class*='reply']";
    private static final String COMMENT_TEXT_LOCATOR = "div[class*='reply_text']";
    private static final String COMMENT_AUTHOR_LOCATOR = "div[class*='reply_author']";
    private static final String POST_LIKE_BUTTON_LOCATOR = "a[class*='_like']";
    private static final String POST_LIKE_COUNT_LOCATOR = "div[@class*='like_button_count']";
    private static final String CLOSE_WALL_IMAGE_LOCATOR = "[class*='pv_close_btn']";
    private static final String POST_DOWNLOADED_IMG_SELECTOR = "#pv_photo > img";

    public UserPage() {
        super(By.id("page_info_wrap"), "VK user page");
    }

    public String getCreatedPost(int userId, int postId) {
        return getPost(userId, postId).findChildElement(By.cssSelector(POST_AUTHOR_LOCATOR), TextBox.class).getText();
    }

    private ITextBox getPost(int userId, int postId) {
        return super.getElementFactory().getTextBox(By.id(String.format(POST_ID_FORMAT, userId, postId)),
                "User post");
    }

    public String getPostText(int userId, int postId) {
        return getPost(userId, postId).findChildElement(By.cssSelector(POST_TEXT_LOCATOR), TextBox.class).getText();
    }

    public void isPostPhotoDisplayed(int userId, int postId) {
        getPost(userId, postId).findChildElement(By.cssSelector(POST_PHOTO_LOCATOR), TextBox.class).state()
                .waitForDisplayed();
    }

    public void clickNextPostCommentsButton(int userId, int postId) {
        IButton button = getPost(userId, postId).findChildElement(By.cssSelector(NEXT_POST_COMMENTS_BUTTON_LOCATOR),
                Button.class);
        button.getJsActions().scrollToTheCenter();
        button.click();
    }

    public void scrollToComment(int userId, int commentId) {
        getPost(userId, commentId).findChildElement(By.cssSelector(FIRST_POST_REPLY_LOCATOR), TextBox.class).getJsActions()
                .scrollToTheCenter();
    }

    public void waitForCommentTextDisplayed(int userId, int commentId, String text) {
        try {
            AqualityServices.getConditionalWait().waitForTrue(() -> getPost(userId, commentId)
                    .findChildElement(By.cssSelector(COMMENT_TEXT_LOCATOR), TextBox.class).getText().equals(text));
        } catch (TimeoutException e) {
            Logger.getInstance().debug("The comment text isn't displayed", e);
        }
    }

    public String getCommentAuthor(int userId, int commentId) {
        return getPost(userId, commentId).findChildElement(By.cssSelector(COMMENT_AUTHOR_LOCATOR), TextBox.class)
                .getText();
    }

    public String getCommentText(int userId, int commentId) {
        return getPost(userId, commentId).findChildElement(By.cssSelector(COMMENT_TEXT_LOCATOR), TextBox.class).getText();
    }

    public void clickPostLikeButton(int userId, int postId) {
        IButton button = getPost(userId, postId).findChildElement(By.cssSelector(POST_LIKE_BUTTON_LOCATOR), Button.class);
        button.getJsActions().scrollToTheCenter();
        button.click();
    }

    public void isPostLikeCountDisplayed(int userId, int postId) {
        getPost(userId, postId).findChildElement(By.cssSelector(POST_LIKE_COUNT_LOCATOR), Button.class).state()
                .waitForNotDisplayed();
    }

    public boolean isPostNotDisplayed(int userId, int postId) {
        return getPost(userId, postId).state().waitForNotDisplayed();
    }

    public void downloadImgFrom(int postId,String path,int userId) throws IOException {

        getPost(userId, postId).findChildElement(By.cssSelector(POST_PHOTO_LOCATOR), Button.class).click();
        String link = getElementFactory().getLink(By.cssSelector(POST_DOWNLOADED_IMG_SELECTOR),"link").getAttribute("src");
        File fileToSave = new File(path);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(link);
        CloseableHttpResponse response = httpClient.execute(httpGet, new BasicHttpContext());
        copyInputStreamToFile(response.getEntity().getContent(), fileToSave);
        getElementFactory().getButton(By.cssSelector(CLOSE_WALL_IMAGE_LOCATOR),"Close img btn").click();

    }

}
