package framework.steps;

import framework.configuration.Configuration;
import framework.utils.VkApiUtils;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

import static framework.constants.JsonObjConstants.*;

public class CreatePostCommentOnWallStep {

    private static final VkApiUtils VK_API_UTILS = new VkApiUtils(Configuration.getVkApiUrl(),Configuration.getUserToken(),
            Configuration.getApiVersion());

    public static int getWallPostCommentId(int userId, int postId, String commentText) {
        HttpResponse<JsonNode> createPostCommentResponse = VK_API_UTILS.createPostComment(userId, postId, commentText);
        return createPostCommentResponse.getBody().getObject().getJSONObject(RESPONSE_KEY).getInt(JSON_OBJ_COMMENT_ID);
    }
}
