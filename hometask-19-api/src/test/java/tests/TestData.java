package tests;

import com.github.javafaker.Faker;
import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;

public class TestData {
    CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
    String password = config.password();
    String email = config.email();

    Faker faker = new Faker();
    String firstName = faker.funnyName().name(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().safeEmailAddress();
}
