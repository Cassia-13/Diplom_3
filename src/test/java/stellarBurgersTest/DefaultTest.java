package stellarBurgersTest;

import com.github.javafaker.Faker;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.api.User;
import site.nomoreparties.stellarburgers.api.UserAPI;

public abstract class DefaultTest {
    protected final String BASEURL = "https://stellarburgers.nomoreparties.site/";

    protected final Faker faker = new Faker();

    protected final UserAPI userAPI = new UserAPI();
    protected final User user = new User(faker.name().username() + "@bom.com", faker.name().username() + "@bom.com", faker.name().firstName());

    protected WebDriver driver;

    @After
    public void tearDown() {
        driver.quit();

        if (userAPI.login(user)) {
            userAPI.remove(user);
        }
    }
}
