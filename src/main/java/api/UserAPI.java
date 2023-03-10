package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserAPI {
    private final String BASEURL = "https://stellarburgers.nomoreparties.site";

    @Step("User registration. POST '/api/auth/register'")
    public void create(User user) {
        Response response = given().baseUri(BASEURL)
                .contentType(ContentType.JSON)
                .when()
                .body(user)
                .when()
                .post("/api/auth/register");

        response.then().assertThat().statusCode(200);

        user.setAccessToken(response.jsonPath().getString("accessToken"));
    }

    @Step("Remove user. DELETE '/api/auth/user'")
    public void remove(User user) {
        given().baseUri(BASEURL)
                .contentType(ContentType.JSON)
                .header("Authorization", user.getAccessToken())
                .when()
                .delete("/api/auth/user")
                .then()
                .assertThat()
                .statusCode(202);
    }

    @Step("User authorization. POST '/api/auth/login'")
    public void login(User user) {
        Response response = given().baseUri(BASEURL)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/auth/login");

        response.then().assertThat().statusCode(200);

        user.setAccessToken(response.jsonPath().getString("accessToken"));
    }
}
