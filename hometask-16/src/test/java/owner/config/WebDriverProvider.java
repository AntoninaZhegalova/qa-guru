package owner.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;
import java.util.function.Supplier;


public class WebDriverProvider implements Supplier<WebDriver> {

    private final WebDriverConfig config;

    public WebDriverProvider() {
        this.config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    }

    @Override
    public WebDriver get() {
        WebDriver driver = createWebDriver();
        driver.get(config.getBaseUrl());
        return driver;
    }

    public WebDriver createWebDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (Objects.isNull(config.getRemoteUrl())) {
            if (config.getBrowser().equals(Browser.CHROME.toString())) {
                WebDriverManager.chromedriver().setup();
                capabilities.setVersion(config.getBrowserVersion());
                return new ChromeDriver();
            } else if (config.getBrowser().equals(Browser.FIREFOX.toString())) {
                WebDriverManager.firefoxdriver().setup();
                capabilities.setVersion(config.getBrowserVersion());
                return new FirefoxDriver();
            }
        } else {
            if (config.getBrowser().equals(Browser.CHROME.toString())) {
                capabilities.setVersion(config.getBrowserVersion());
                return new RemoteWebDriver(config.getRemoteUrl(), new ChromeOptions());
            } else if(config.getBrowser().equals(Browser.FIREFOX.toString())) {
                capabilities.setVersion(config.getBrowserVersion());
                return new RemoteWebDriver(config.getRemoteUrl(), new FirefoxOptions());
            }
        }
        throw new RuntimeException("No such browser");
    }
}