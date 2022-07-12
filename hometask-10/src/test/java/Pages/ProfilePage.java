package Pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    @Step("Открыть страницу профиля.")
    public ProfilePage openProfilePage(String value) {
        Selenide.open(value);

        return this;
    }

    @Step("Найти репозиторий по названию.")
    public ProfilePage openRepo(String value) {
        $(".repo").shouldHave(text(value)).click();

        return this;
    }

    @Step("Проверить видимость раздела 'Issues'.")
    public ProfilePage checkIssue() {
        $("#repository-container-header").shouldHave(text("Issues")).shouldBe(visible);

        return this;
    }
}
