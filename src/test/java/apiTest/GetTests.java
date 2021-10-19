package apiTest;

import entities.*;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.UserSteps;
import utils.JsonUtils;
import steps.PostSteps;
import utils.ProjectApiMethods;

import java.io.*;

public class GetTests {

    private static final User EXPECTED_USER = new User(1, "Chelsey Dietrich", "Kamren", "Lucio_Hettinger@annie.ca",
            new Address("Skiles Walks", "Suite 351", "Roscoeview", "33263", new Geo(-31.8129, 62.5342)),
            "(254)954-1289", "demarco.info",
            new Company("Keebler LLC", "User-centric fault-tolerant solution", "revolutionize end-to-end systems"));

    private static final Post EXPECTED_POST = new Post(10, 99,
            "temporibus sit alias delectus eligendi possimus magni",
            "quo deleniti praesentium dicta non quod\naut est molestias\nmolestias et officia quis nihil\nitaque dolorem quia");

    @Test
    public void testCaseGetAllPosts() throws IOException {
        String getAllPostsResponse = ProjectApiMethods.getAllPosts(HttpStatus.SC_OK);

        Assert.assertTrue(JsonUtils.isJson(getAllPostsResponse),
                "The response to the request was returned in a non-Json format ! ");
        Assert.assertTrue(PostSteps.isPostSortedAscendingOrder(getAllPostsResponse),
                "Array is not sorted in ascending order");
    }

    @Test
    public void testCaseGetPostWithPostId99() throws IOException {

        Assert.assertEquals(PostSteps.getPostByIdAsString(99, HttpStatus.SC_OK), EXPECTED_POST,
                "The expected result does not match the actual one !");
    }

    @Test
    public void testCaseGetPostWithPostId150() throws IOException {

        Assert.assertNull(ProjectApiMethods.getPostById(150, HttpStatus.SC_NOT_FOUND),
                "Actual response body isn't empty ! ");

    }

    @Test
    public void testCaseGetAllUsersAndCheckOne() throws IOException {

        Assert.assertEquals(UserSteps.getUsersAsList(HttpStatus.SC_OK).get(4), EXPECTED_USER,
                "The expected result does not match the actual one !");
    }

    @Test
    public void testCaseGetUserById() throws IOException{

        Assert.assertEquals(UserSteps.getUserByIdAsList(5,HttpStatus.SC_OK).get(0),
                UserSteps.getUsersAsList(HttpStatus.SC_OK).get(4),"data mismatch");
    }
}
