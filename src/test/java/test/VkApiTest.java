package test;

import aquality.selenium.core.logging.Logger;
import framework.configuration.Configuration;
import framework.pages.FeedPage;
import framework.pages.UserPage;
import framework.pages.WelcomePage;
import framework.steps.CreatePostCommentOnWallStep;
import framework.steps.CreatePostOnWallStep;
import framework.steps.EditPostOnWallStep;
import framework.utils.ImageUtils;
import framework.utils.VkApiUtils;
import kong.unirest.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;

public class VkApiTest extends BaseTest{

    WelcomePage welcomePage = new WelcomePage();
    FeedPage feedPage = new FeedPage();
    UserPage userPage = new UserPage();
    private static final Properties properties = new Properties();
    private static final int TEXT_SIZE = 10;
    private static final String TEST_IMG_FORMAT = "png";
    private static final String DOWNLOADED_IMG_FORMAT = "jpg";

    @Test
    public void vkApiTestCase() throws Exception {
        properties.load(ClassLoader.getSystemResourceAsStream("conf.properties"));
        String testLogin = properties.getProperty("test.login");
        String testPassword = properties.getProperty("test.password");
        int getVKUserId = Integer.parseInt(Configuration.getUserId());
        String getVKUsername = Configuration.getUserName();
        String postText = ("Random Post Text: " + RandomStringUtils.randomAlphabetic(TEXT_SIZE));
        String newPostText = ("Random Post Text: " + RandomStringUtils.randomAlphabetic(TEXT_SIZE));
        String commentText = ("Random Post Comment: " + RandomStringUtils.randomAlphabetic(TEXT_SIZE));
        String downloadedImgPath = Configuration.getDownloadedImgPath();
        String testImgPath = Configuration.getImagePath();

        Logger.getInstance().info("Go to 'Welcome Page' ");
        Assert.assertTrue(welcomePage.state().waitForDisplayed()," The welcome page isn't displayed ! ");

        Logger.getInstance().info(" Pass authorization ");
        welcomePage.typeLogin(testLogin);
        welcomePage.typePassword(testPassword);
        welcomePage.clickEnterButton();
        Assert.assertTrue(feedPage.state().waitForDisplayed()," The feed page isn't displayed ! ");

        Logger.getInstance().info(" Go to 'My Page' ");
        feedPage.clickMyPageButton();
        Assert.assertTrue(userPage.state().waitForDisplayed()," The user page isn't displayed ! ");

        Logger.getInstance().info(" Use an API request to create an entry with randomly generated text on the wall ");
        int createdPostId = CreatePostOnWallStep.getCreatedWallPostId(getVKUserId,postText);
        Assert.assertEquals(userPage.getCreatedPost(getVKUserId, createdPostId), getVKUsername,
                "Actual post creator isn't equals expected.");
        Assert.assertEquals(userPage.getPostText(getVKUserId, createdPostId), postText,
                "Actual post text isn't equals expected.");

        Logger.getInstance().info("Edit an entry via an API request - change the text and add (upload) any image.");
        int addedPhotoId = EditPostOnWallStep.getWallPhotoId(getVKUserId,testImgPath);
        EditPostOnWallStep.editWallPost(getVKUserId, createdPostId, addedPhotoId, newPostText);
        userPage.isPostPhotoDisplayed(getVKUserId, createdPostId);

        Assert.assertEquals(userPage.getPostText(getVKUserId, createdPostId), newPostText,
                "Actual post text isn't equals expected.");

        userPage.downloadImgFrom(createdPostId,downloadedImgPath,getVKUserId);
        Assert.assertEquals(ImageUtils.getImageByteArray(downloadedImgPath, DOWNLOADED_IMG_FORMAT),
                ImageUtils.getImageByteArray(testImgPath,TEST_IMG_FORMAT),"Actual photo id isn't equals expected");

       Logger.getInstance().info("Leave a comment on the post");
        int getCreatedCommentId = CreatePostCommentOnWallStep.getWallPostCommentId(getVKUserId, createdPostId, commentText);
        userPage.clickNextPostCommentsButton(getVKUserId, createdPostId);
        userPage.scrollToComment(getVKUserId, getCreatedCommentId);
        userPage.waitForCommentTextDisplayed(getVKUserId, getCreatedCommentId, commentText);
        Assert.assertEquals(userPage.getCommentAuthor(getVKUserId, getCreatedCommentId), getVKUsername,
                "Actual comment creator isn't equals expected.");
        Assert.assertEquals(userPage.getCommentText(getVKUserId, getCreatedCommentId), commentText,
                "Actual post text isn't equals expected.");

        Logger.getInstance().info("Put a like to the post");
        userPage.clickPostLikeButton(getVKUserId, createdPostId);
        userPage.isPostLikeCountDisplayed(getVKUserId, createdPostId);
        VkApiUtils vkApiUtils = new VkApiUtils(Configuration.getVkApiUrl(),Configuration.getUserToken(),
                Configuration.getApiVersion());
        JSONObject receivedPost = vkApiUtils.getWallPost(getVKUserId, createdPostId);
        Assert.assertTrue(vkApiUtils.isWallPostLikedByCurrentUser(receivedPost), "The post isn't liked by a current user.");

        Logger.getInstance().info("Delete wall post");
        vkApiUtils.deleteWallPost(getVKUserId, createdPostId);
        Assert.assertTrue(userPage.isPostNotDisplayed(getVKUserId, createdPostId), "The post isn't deleted.");
    }
}
