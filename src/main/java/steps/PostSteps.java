package steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Post;
import junit.framework.Assert;
import org.json.JSONArray;
import utils.JsonUtils;
import utils.ProjectApiMethods;

import java.io.IOException;

import static utils.JsonUtils.isArraySorted;

public class PostSteps {

    public static Post getPostByIdAsString(int postId, int expStatusCode) throws IOException {

        String getPostById = ProjectApiMethods.getPostById(postId, expStatusCode);
        ObjectMapper mapper = new ObjectMapper();

        Assert.assertTrue("The response to the request was returned in a non-Json format ! ", JsonUtils.isJson(getPostById));

            return mapper.readValue(getPostById,Post.class);
    }

    public static Object getCreatedPostAsPostEntities (Post post, int expStatusCode) throws IOException {

        Object getCreatedPost = ProjectApiMethods.createPost(expStatusCode,post);

        Assert.assertTrue("The response to the request was returned in a non-Json format ! ",
                JsonUtils.isJson(getCreatedPost));

        return getCreatedPost;
    }

    public static boolean isPostSortedAscendingOrder(String postArray) {
        return isSortedAscendingOrder(new JSONArray(postArray));
    }

    public static boolean isSortedAscendingOrder(JSONArray jsonArray) {
        return isArraySorted(jsonArray, (e, t) -> e.getInt("id") < t.getInt("id"));
    }

}
