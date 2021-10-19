package framework.configuration;

public class Configuration {

    private Configuration() {
    }

    public static String getStartUrl() {
        return Environment.getCurrentEnvironment().getValue("/testUrl").toString();
    }

    public static String getVkApiUrl() {
        return Environment.getCurrentEnvironment().getValue("/vk_api_url").toString();
    }

    public static String getUserToken() {
        return Environment.getCurrentEnvironment().getValue("/user_token").toString();
    }

    public static String getApiVersion() {
        return Environment.getCurrentEnvironment().getValue("/apiVersion").toString();
    }

    public static String getUserId() {
        return Environment.getCurrentEnvironment().getValue("/test.userId").toString();
    }

    public static String getUserName() {
        return Environment.getCurrentEnvironment().getValue("/username").toString();
    }

    public static String getImagePath() {
        return Environment.getCurrentEnvironment().getValue("/test.image_path").toString();
    }

    public static String getDownloadedImgPath() {
        return Environment.getCurrentEnvironment().getValue("/test.downloaded_image_path").toString();
    }
}
