package user;

import Utils.Endpoints;
import Utils.Specifications;
import data.User;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.Matchers.is;

public class UserClient {
    @Step("Send post request to api/auth/register")
    public ValidatableResponse register(User user) {
        return given()
                .spec(Specifications.requestSpecification())
                .and()
                .body(user)
                .when()
                .post(Endpoints.REGISTER_USER_API)
                .then();
    }

    @Step("Send post request to api/auth/login")
    public ValidatableResponse login(User user) {
        return given()
                .spec(Specifications.requestSpecification())
                .and()
                .body(user)
                .when()
                .post(Endpoints.LOGIN_API)
                .then();
    }

    @Step("Send delete request to api/auth/user")
    public ValidatableResponse delete(String bearerToken) {
        return given()
                .spec(Specifications.requestSpecification())
                .headers("Authorization", bearerToken)
                .delete(Endpoints.DELETE_USER_API)
                .then()
                .statusCode(SC_ACCEPTED)
                .and()
                .body("message", is("User successfully removed"));
    }

    @Step("Send patch request to api/auth/user")
    public ValidatableResponse patch(User user, String bearerToken) {
        return given()
                .spec(Specifications.requestSpecification())
                .header("Authorization", bearerToken)
                .contentType(ContentType.JSON)
                .and()
                .body(user)
                .when()
                .patch(Endpoints.PATCH_USER_API)
                .then();
    }
}
