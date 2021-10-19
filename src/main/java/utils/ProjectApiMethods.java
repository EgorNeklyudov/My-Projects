package utils;

import java.io.IOException;
import java.util.Properties;
import constants.EndPointsConstants;
import entities.Post;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ProjectApiMethods implements EndPointsConstants {

    private static final Properties properties = new Properties();

    public static String getAllPosts(int expStatusCode) throws IOException {

        properties.load(ClassLoader.getSystemResourceAsStream("conf.properties"));
        String startURL = properties.getProperty("start.url");

        String result;
        result = given().when().get(String.format(GET_ALL_POSTS,startURL))
                        .then()
                        .log().all()
                        .extract().asString();
        if (expStatusCode == HttpStatus.SC_OK){
            return result;
        }
        else {
            System.out.print("The status code is different ! ");
            return null;}

    }

    public static String getPostById(int postId,int expStatusCode ) throws IOException{

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

    public static String getAllUser(int expStatusCode) throws IOException{

        properties.load(ClassLoader.getSystemResourceAsStream("conf.properties"));
        String startURL = properties.getProperty("start.url");

        String result = given()
                .when()
                .get(String.format(GET_ALL_USERS,startURL))
                .then().log().all()
                .extract().asString();
        if (expStatusCode == HttpStatus.SC_OK){
            return result;
        }
        else {
            System.out.print("The status code is different ! ");
            return null;}
    }

    public static String getUserById(int userId,int expStatusCode) throws IOException{

        properties.load(ClassLoader.getSystemResourceAsStream("conf.properties"));
        String startURL = properties.getProperty("start.url");

        String result = given()
                .queryParam("id",userId)
                .when().get(String.format(GET_ALL_USERS,startURL))
                .then().extract().asString();

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

    public static Object createPost(int expStatusCode, Post post) throws IOException{

        properties.load(ClassLoader.getSystemResourceAsStream("conf.properties"));
        String startURL = properties.getProperty("start.url");

        RequestSpecification request = RestAssured.given();
        request.body(post);
        request.then().statusCode(HttpStatus.SC_CREATED);
        request.contentType(ContentType.JSON).accept(ContentType.JSON);
        request.then().contentType(ContentType.JSON);
        Response response = request.post(String.format(GET_ALL_POSTS,startURL));
        JSONObject jsonObject = new JSONObject(response.body().print());

        if (expStatusCode == HttpStatus.SC_CREATED){
            return jsonObject.get("id");
        }
        else {
            System.out.print("The status code is different ! ");
            return null;}
    }
}
