package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static helpers.CustomApiListener.withCustomTemplates;

public class DemoWebShopTest extends TestBase {

    @Test
    @Tag("demowebshop")
    @DisplayName("Registration user")
    void registrationTest() {
        this.registrationPage.openPage()
                .setGenderMale()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setMail(testData.userEmail)
                .setPassword(testData.password)
                .setConfirmPassword(testData.password)
                .clickRegister()
                .checkResult();
    }

    @Test
    @Tag("demowebshop")
    @DisplayName("User authentication")
    void authTest() {
        String cookieKey = "NOPCOMMERCE.AUTH";
        String cookieValue = given()
                .filter(withCustomTemplates())
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .log().all()
                .formParam("Email", testData.email)
                .formParam("Password", testData.password)
                .when()
                .post("https://demowebshop.tricentis.com/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie(cookieKey);

        open("/login");
        Cookie authCookie = new Cookie(cookieKey, cookieValue);
        getWebDriver().manage().addCookie(authCookie);
        open("");
        $(".header-links").shouldHave(text(testData.email));
    }

    @Test
    @Tag("demowebshop")
    @DisplayName("Update info user")
    void updateTest() {
        String cookieKey = "NOPCOMMERCE.AUTH";
        String cookieValue = given()
                .filter(withCustomTemplates())
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .log().all()
                .formParam("Email", testData.email)
                .formParam("Password", testData.password)
                .when()
                .post("http://demowebshop.tricentis.com/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie(cookieKey);
        open("");
        Cookie authCookie = new Cookie(cookieKey, cookieValue);
        getWebDriver().manage().addCookie(authCookie);
        open("/customer/info");
        $("#FirstName").setValue("ReFirst");
        $("#LastName").setValue("ReLast");
        $("[for='gender-female']").click();
        $("[name='save-info-button']").click();
        $("#FirstName").shouldHave(Condition.value("ReFirst"));
    }
}
