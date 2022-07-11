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

public class ThreeTestOptions extends BaseTest {

    @DisplayName("Проверка наличия ссылки 'Issue' в репозитории - чистый selenide")
    @Test
    public void checkIssueSelenide() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Selenide.open("AntoninaZhegalova");
        $(".repo").shouldHave(text("test")).click();
        $("#repository-container-header").shouldHave(text("Issues")).shouldBe(visible);
    }

    @DisplayName("Проверка наличия ссылки 'Issue' в репозитории - лямбда steps")
    @Test
    public void checkIssueLSteps() {
        step("Открыть страниу профиля.", () -> {
            Selenide.open("AntoninaZhegalova");
        });
        step("Наити репозиторий по названию.", () -> {
            $(".repo").shouldHave(text("test")).click();
        });
        step("Проветь видимость раздела 'Issues'.", () -> {
            $("#repository-container-header").shouldHave(text("Issues")).shouldBe(visible);
        });

    }

    @DisplayName("Проверка наличия ссылки 'Issue' в репозитории - @Step")
    @Test
    public void checkIssueSteps() {
        ProfilePage step = new ProfilePage();
        step.openProfilePage("AntoninaZhegalova");
        step.openRepo("test");
        step.checkIssue();
    }

}