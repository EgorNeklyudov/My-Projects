package framework.steps;

import framework.configuration.Configuration;
import framework.utils.VkApiUtils;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

import static framework.constants.JsonObjConstants.*;

public class EditPostOnWallStep {

    private static final VkApiUtils VK_API_UTILS = new VkApiUtils(Configuration.getVkApiUrl(),Configuration.getUserToken(),
            Configuration.getApiVersion());

    public static void editWallPost(int userId, int postId, int photoId, String newMessage) {
        VK_API_UTILS.editWallPost(userId, postId, newMessage, String.format("photo%d_%d", userId, photoId));
    }

    private static String getUploadUrl() {
        HttpResponse<JsonNode> photosResponse = VK_API_UTILS.getPhotosWallUploadServer();
        return photosResponse.getBody().getObject().getJSONObject(RESPONSE_KEY).getString(JSON_OBJ_KEY_URL);
    }

    private static String getResponseBodyAttribute(HttpResponse<JsonNode> uploadPhotoResponse, String attribute) {
        return uploadPhotoResponse.getBody().getObject().getString(attribute);
    }

    public static int getWallPhotoId(int userId, String attachmentPath) {
        String uploadUrl = getUploadUrl();
        HttpResponse<JsonNode> uploadPhotoResponse = VK_API_UTILS.uploadPhotoToServer(uploadUrl, attachmentPath);
        String server = getResponseBodyAttribute(uploadPhotoResponse, "server");
        String hash = getResponseBodyAttribute(uploadPhotoResponse, "hash");
        String photo = getResponseBodyAttribute(uploadPhotoResponse, "photo");
        HttpResponse<JsonNode> savePhotoResponse = VK_API_UTILS.saveWallPhoto(userId, server, hash, photo);
        return savePhotoResponse.getBody().getObject().getJSONArray(RESPONSE_KEY).getJSONObject(0).getInt(JSON_OBJ_KEY_WALL_ID);
    }
}
