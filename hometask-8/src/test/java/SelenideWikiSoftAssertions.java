import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


class SelenideWikiSoftAssertions {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    @DisplayName("Проверка наличия на странице Selenide в Github в разделе Wiki страницы" +
            " SoftAssertions и на ней есть пример кода для JUnit5")
    void searchJUnit5Test() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-filter").val("SoftAssertions").pressEnter();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions")).
                $(byText("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text("Using JUnit5 extend test class:"));
    }
}
