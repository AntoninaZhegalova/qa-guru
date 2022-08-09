package owner.config;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources("classpath:${host}.properties")

public interface WebDriverConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://github.com")
    String getBaseUrl();

    @Key("browser.name")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("browser.version")
    @DefaultValue("100.0")
    String getBrowserVersion();

    @Key("remote.url")
    URL getRemoteUrl();
}