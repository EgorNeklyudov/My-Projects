package framework.steps;

import framework.configuration.Configuration;
import framework.utils.VkApiUtils;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

import static framework.constants.JsonObjConstants.*;


public class CreatePostOnWallStep {

    private static final VkApiUtils VK_API_UTILS = new VkApiUtils(Configuration.getVkApiUrl(),Configuration.getUserToken(),
            Configuration.getApiVersion());

    public static int getCreatedWallPostId(int userId, String postText) {
        HttpResponse<JsonNode> response = VK_API_UTILS.createWallPost(userId, postText);
        return response.getBody().getObject().getJSONObject(RESPONSE_KEY).getInt(JSON_OBJ_POST_ID);
    }
}
