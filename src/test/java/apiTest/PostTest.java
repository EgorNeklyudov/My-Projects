package apiTest;

import entities.Post;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.PostSteps;

import java.io.IOException;

public class PostTest {

    private static final int RANDOM_TEXT_SIZE = 10;
    private static final int TEST_USER_ID = 1;

    @Test
    public void testCaseCreatePost() throws IOException {

        String randomTitle = RandomStringUtils.randomAlphabetic(RANDOM_TEXT_SIZE);
        String randomBody = RandomStringUtils.randomAlphabetic(RANDOM_TEXT_SIZE);

        Post sendingPost = new Post(TEST_USER_ID, randomTitle,randomBody);

        Assert.assertNotNull(PostSteps.getCreatedPostAsPostEntities(sendingPost,HttpStatus.SC_CREATED),
                "The response to the request came empty ! ");

    }
}
