package steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.User;
import junit.framework.Assert;
import utils.JsonUtils;
import utils.ProjectApiMethods;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UserSteps {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<User> getUsersAsList(int expStatusCode) throws IOException {
        String getAllUsers = ProjectApiMethods.getAllUser(expStatusCode);
        List<User> actualUser = Arrays.asList(mapper.readValue(getAllUsers, User[].class));

        Assert.assertTrue("The response to the request was returned in a non-Json format ! ", JsonUtils.isJson(getAllUsers));

        return actualUser;
    }

    public static List<User> getUserByIdAsList(int userId,int expStatusCode) throws IOException {
        String getUserById = ProjectApiMethods.getUserById(userId, expStatusCode);
        List<User> userById = Arrays.asList(mapper.readValue(getUserById, User[].class));

        Assert.assertTrue("The response to the request was returned in a non-Json format ! ", JsonUtils.isJson(getUserById));

        return userById;
    }
}
