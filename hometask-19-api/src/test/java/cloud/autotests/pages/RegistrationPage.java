package cloud.autotests.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    public RegistrationPage openPage() {
        open("/register");
        return this;
    }

    public RegistrationPage setGenderMale() {
        $("[for='gender-male']").click();
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        $("#FirstName").setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        $("#LastName").setValue(value);
        return this;
    }

    public RegistrationPage setMail(String value) {
        $("#Email").setValue(value);
        return this;
    }

    public RegistrationPage setPassword(String value) {
        $("#Password").setValue(value);
        return this;
    }

    public RegistrationPage setConfirmPassword(String value) {
        $("#ConfirmPassword").setValue(value);
        return this;
    }

    public RegistrationPage clickRegister() {
        $("#register-button").click();
        return this;
    }

    public RegistrationPage checkResult() {
        $(".page-body").shouldHave(Condition.text("Your registration completed"));
        return this;
    }

}


