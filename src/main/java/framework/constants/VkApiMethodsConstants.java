package framework.constants;

public interface VkApiMethodsConstants {

    String CREATE_POST_URL = "%s/method/wall.post?owner_id=%s&message=%s&%s";

    String ACCESS_VERSION_URL_PART = "access_token=%s&v=%s";

    String GET_WALL_POSTS_URL = "%s/method/wall.getById?posts=%s&%s";

    String EDIT_POST_URL = "%s/method/wall.edit?owner_id=%s&post_id=%s&message=%s&attachments=%s&%s";

    String GET_PHOTOS_WALL_UPLOAD_SERVER_URL = "%s/method/photos.getWallUploadServer?%s";

    String SAVE_WALL_PHOTO_URL = "%s/method/photos.saveWallPhoto?user_id=%s&server=%s&hash=%s&%s";

    String CREATE_POST_COMMENT_URL = "%s/method/wall.createComment?owner_id=%s&post_id=%s&message=%s&%s";

    String DELETE_POST_URL = "%s/method/wall.delete?owner_id=%s&post_id=%s&%s";
}
