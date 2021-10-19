package framework.utils;

import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.Properties;

import static framework.constants.EndPointsConstants.GET_POST_BY_ID;
import static io.restassured.RestAssured.given;

public class ProjectApiMethods {

    private static final Properties properties = new Properties();

    public static String getPostById(int postId,int expStatusCode ) throws IOException {

        properties.load(ClassLoader.getSystemResourceAsStream("conf.properties"));
        String startURL = properties.getProperty("start.url");

        String result;
        result =
                given()
                        .pathParam("postId",postId)
                        .when()
                        .get(String.format(GET_POST_BY_ID,startURL))
                        .then()
                        .log().all()
                        .extract().body().asString();
        if (expStatusCode == HttpStatus.SC_OK){
            return result;
        }else if(expStatusCode == HttpStatus.SC_NOT_FOUND){
            System.out.println("The expected status code is not specified correctly or the response body is empty");
            return null;
        }
        else {
            System.out.print("The status code is different ! ");
            return null;}
    }
}
