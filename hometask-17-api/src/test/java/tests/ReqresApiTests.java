package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.*;

public class ReqresApiTests {

    @BeforeAll
    static void init() {
        Specifications.installSpecifications();
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    void postSuccessfulUserRegistrationTest() {
        Map<String, String> data = new HashMap<>(Map.of(
                "email", "eve.holt@reqres.in",
                "password", "pistol"));
        given()
                .body(data)
                .when()
                .post("/api/register")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .and()
                .body("token", not(empty()));
    }

    @Test
    @DisplayName("Проверка неуспешной регистрации")
    void postUnsuccessfulUserRegistrationTest() {
        Map<String, String> data = new HashMap<>(Map.of(
                "email", "eve.holt@reqres"));
        given()
                .body(data)
                .when()
                .post("/api/register")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }

    @Test
    @DisplayName("Наличие пангинции")
    void getReturnEntitiesForPage() {
        given()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2));
    }

    @Test
    @DisplayName("Обновление данных пользователя")
    void patchUserData() {
        String name = "morpheus";
        String job = "leader";
        Map<String, String> data = new HashMap<>(Map.of(
                "name", name,
                "job", job));
        given()
                .body(data)
                .when()
                .put("/api/users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo(name))
                .and()
                .body("job", equalTo(job));
    }

    @Test
    @DisplayName("Удаление пользователя")
    void deleteUser() {
        responseSpecification.contentType("");
        given()
                .when()
                .delete("/api/users/2")
                .then()
                .statusCode(204);
    }

}
