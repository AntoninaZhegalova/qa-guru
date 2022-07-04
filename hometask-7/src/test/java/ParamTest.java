import com.codeborne.selenide.Selenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

class ParamTest {

    @ParameterizedTest(name = "Search item {0}, {1} and {2}")
    @ValueSource(strings = {"Маленький принц", "978-5-699-97439-9", "600040"})
    void labirintSearchTest(String searchItem) {
        Selenide.open("https://www.labirint.ru");
        $("#search-field").setValue(searchItem);
        $(".b-header-b-search-e-btn").pressEnter();
        $$(".product-title").find(text("Маленький принц")).shouldBe(visible);
    }

    @CsvSource(
            {
                    "Чарли, Самокат",
                    "Роальд Даль, Самокат",
                    "Роальд Даль, Puffin"
            }
    )
    @ParameterizedTest(name = "Search item {0}, {1}")
    void labirintSearchTestCSV(String searchItem, String expectedResult) {
        Selenide.open("https://www.labirint.ru");
        $("#search-field").setValue(searchItem);
        $(".b-header-b-search-e-btn").pressEnter();
        $$(".product-pubhouse__pubhouse").find(text(expectedResult)).shouldHave(visible);
    }

    @CsvFileSource(resources = "/testData.csv")
    @ParameterizedTest(name = "Search item {0}, {1} and {2}")
    void labirintSearchTestCSVFile(String searchItem, String expectedResult) {
        Selenide.open("https://www.labirint.ru");
        $("#search-field").setValue(searchItem);
        $(".b-header-b-search-e-btn").pressEnter();
        $$(".product-pubhouse__pubhouse").find(text(expectedResult)).shouldHave(visible);
    }


    @MethodSource
    @ParameterizedTest(name = "Search item {0}, {1}")
    void labirintItemSearchAdditionalSearch(String searchItem, String expectedResult) {
        Selenide.open("https://www.labirint.ru");
        $("#search-field").setValue(searchItem);
        $(".b-header-b-search-e-btn").pressEnter();
        $$(".product-author").find(text(expectedResult)).shouldHave(visible);
    }

    static Stream<Arguments> labirintItemSearchAdditionalSearch() {
        return Stream.of(
                Arguments.of("Чарли", "Даль Роальд"),
                Arguments.of("Мы ужаснее всех", "Donaldson Julia")
        );
    }

    @EnumSource(Authors.class)
    @ParameterizedTest
    void enumTest(Authors authors) {
        Selenide.open("https://www.labirint.ru");
        $("#search-field").setValue(authors.desc);
        $(".b-header-b-search-e-btn").click();
        $$(".product-author").find(text(authors.desc)).shouldBe(visible);
    }

}