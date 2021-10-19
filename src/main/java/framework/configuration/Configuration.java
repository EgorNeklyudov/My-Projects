package framework.configuration;

public class Configuration {

    private Configuration() {
    }

    public static String getStartUrl() {
        return Environment.getCurrentEnvironment().getValue("/userinyerfaceTestUrl").toString();
    }
}
