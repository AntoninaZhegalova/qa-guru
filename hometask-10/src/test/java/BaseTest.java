import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void openMainPage() {
        Configuration.baseUrl = ("https://github.com/");
        Configuration.browserSize = "1920x1080";
    }
}
