import Pages.ProfilePage;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class ThreeOptionsTest extends BaseTest {

    @DisplayName("Проверка наличия раздела 'Issue' в репозитории - чистый selenide")
    @Test
    void checkIssueSelenide() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Selenide.open("AntoninaZhegalova");
        $(".repo").shouldHave(text("test")).click();
        $("#repository-container-header").shouldHave(text("Issues")).shouldBe(visible);
    }

    @DisplayName("Проверка наличия раздела 'Issue' в репозитории - лямбда steps")
    @Test
    void checkIssueLSteps() {
        step("Открыть страницу профиля.", () -> {
            Selenide.open("AntoninaZhegalova");
        });
        step("Найти репозиторий по названию.", () -> {
            $(".repo").shouldHave(text("test")).click();
        });
        step("Проверить видимость раздела 'Issues'.", () -> {
            $("#repository-container-header").shouldHave(text("Issues")).shouldBe(visible);
        });

    }

    @DisplayName("Проверка наличия раздела 'Issue' в репозитории - @Step")
    @Test
    void checkIssueSteps() {
        ProfilePage step = new ProfilePage();
        step.openProfilePage("AntoninaZhegalova");
        step.openRepo("test");
        step.checkIssue();
    }

}