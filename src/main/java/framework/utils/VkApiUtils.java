package framework.utils;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import java.io.File;

import static framework.constants.VkApiMethodsConstants.*;

public class VkApiUtils  {

    private final String baseApiUrl;
    private final String accessToken;
    private final String apiVersion;

    private static final Integer NUMBER_OF_LIKES = 1;

    public VkApiUtils(String baseApiUrl, String accessToken, String apiVersion) {
        this.baseApiUrl = baseApiUrl;
        this.accessToken = accessToken;
        this.apiVersion = apiVersion;
    }

    public HttpResponse<JsonNode> createWallPost(int userId, String message) {
        return Unirest.post(String.format(CREATE_POST_URL, baseApiUrl, userId, message,
                String.format(ACCESS_VERSION_URL_PART, accessToken, apiVersion))).asJson();
    }

    public JSONObject getWallPost(int userId, int postId) {
        return Unirest.get(String.format(GET_WALL_POSTS_URL, baseApiUrl, String.format("%s_%s", userId, postId),
                        String.format(ACCESS_VERSION_URL_PART, accessToken, apiVersion)))
                .asJson().getBody().getObject().getJSONArray("response").getJSONObject(0);
    }

    public void editWallPost(int userId, int postId, String message, String attachments) {
        Unirest.post(String.format(EDIT_POST_URL, baseApiUrl, userId, postId, message, attachments,
                String.format(ACCESS_VERSION_URL_PART, accessToken, apiVersion))).asJson();
    }

    public HttpResponse<JsonNode> getPhotosWallUploadServer() {
        return Unirest.post(String.format(GET_PHOTOS_WALL_UPLOAD_SERVER_URL, baseApiUrl,
                String.format(ACCESS_VERSION_URL_PART, accessToken, apiVersion))).asJson();
    }

    public HttpResponse<JsonNode> uploadPhotoToServer(String uploadUrl, String photoPath) {
        return Unirest.post(uploadUrl).multiPartContent().field("photo", new File(photoPath)).asJson();
    }

    public HttpResponse<JsonNode> saveWallPhoto(int userId, String server, String hash, String photo) {
        return Unirest
                .post(String.format(SAVE_WALL_PHOTO_URL, baseApiUrl, userId, server, hash,
                        String.format(ACCESS_VERSION_URL_PART, accessToken, apiVersion)))
                .multiPartContent().field("photo", photo).asJson();
    }

    public HttpResponse<JsonNode> createPostComment(int userId, int postId, String message) {
        return Unirest.post(String.format(CREATE_POST_COMMENT_URL, baseApiUrl, userId, postId, message,
                String.format(ACCESS_VERSION_URL_PART, accessToken, apiVersion))).asJson();
    }

    public boolean isWallPostLikedByCurrentUser(JSONObject post) {
        int isLiked = post.getJSONObject("likes").getInt("user_likes");
        return isLiked == NUMBER_OF_LIKES;
    }

    public void deleteWallPost(int userId, int postId) {
        Unirest.post(String.format(DELETE_POST_URL, baseApiUrl, userId, postId,
                String.format(ACCESS_VERSION_URL_PART, accessToken, apiVersion))).asJson();
    }
}
