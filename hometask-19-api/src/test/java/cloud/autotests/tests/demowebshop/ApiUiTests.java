package cloud.autotests.tests.demowebshop;

import cloud.autotests.config.demowebshop.App;
import cloud.autotests.tests.TestBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static cloud.autotests.helpers.AllureRestAssuredFilter.withCustomTemplates;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class ApiUiTests extends TestBase {

    static String login,
            password;

    @BeforeAll
    static void configureBaseUrl() {
        RestAssured.baseURI = App.config.apiUrl();
        Configuration.baseUrl = App.config.webUrl();

        login = App.config.userLogin();
        password = App.config.userPassword();
    }

    @Test
    @Tag("demowebshop")
    @DisplayName("Registration user")
    void registrationTest() {
        Faker faker = new Faker();
        String firstName = faker.funnyName().name();
        String lastName = faker.name().lastName();
        String userEmail = faker.internet().safeEmailAddress();
        String userPassword = faker.internet().password();

        this.registrationPage.openPage()
                .setGenderMale()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setMail(userEmail)
                .setPassword(userPassword)
                .setConfirmPassword(userPassword)
                .clickRegister()
                .checkResult();
    }

    @Test
    @Tag("demowebshop")
    @DisplayName("User authentication")
    void authTest() {
        Cookie authCookie = getCookie();
        open("/login");
        getWebDriver().manage().addCookie(authCookie);
        open("");
        $(".header-links").shouldHave(text(login));
    }

    @Test
    @Tag("demowebshop")
    @DisplayName("Update info user")
    void updateTest() {
        Cookie authCookie = getCookie();
        open("");
        getWebDriver().manage().addCookie(authCookie);
        open("/customer/info");
        $("#FirstName").setValue("ReFirst");
        $("#LastName").setValue("ReLast");
        $("[for='gender-female']").click();
        $("[name='save-info-button']").click();
        $("#FirstName").shouldHave(Condition.value("ReFirst"));
    }

    private Cookie getCookie() {
        String cookieKey = "NOPCOMMERCE.AUTH";
        String cookieValue = given()
                .filter(withCustomTemplates())
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .log().all()
                .formParam("Email", login)
                .formParam("Password", password)
                .when()
                .post(App.config.apiUrl() + "/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie(cookieKey);
        return new Cookie(cookieKey, cookieValue);
    }

}

