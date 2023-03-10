package stellarBurgersTest.user;

import api.User;
import api.UserAPI;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import stellarBurgers.AuthPage;
import stellarBurgers.ConstructorPage;
import stellarBurgers.RegistrationPage;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class UserRegistrationTest {
    private final Faker faker = new Faker();
    private WebDriver driver;
    private final String BASEURL = "https://stellarburgers.nomoreparties.site/";

    private final String name = faker.name().firstName();
    private final String email = faker.name().username() + "@bom.com";
    private String password = faker.random().hex();

    private final UserAPI userAPI = new UserAPI();
    private final User user = new User(email, password, name);

    private final String path;

    public UserRegistrationTest(String browserName, String path) {
        this.path = path;
    }

    @Parameterized.Parameters(name = "Проверка в {0}")
    public static Object[][] getPath() {
        return new Object[][]{
                {"Yandex browser", "C:\\Users\\isupo\\Downloads\\yandexdriver-23.1.0.2864-win64\\yandexdriver.exe"},
                {"Chrome browser", "D:\\KateProject\\WebDriver\\bin\\chromedriver.exe"},
        };
    }

    @Test
    @DisplayName("Registration user with correct data")
    public void successfulRegistrationTest() {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASEURL);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnPersonalAccount();

        AuthPage authPage = new AuthPage(driver);
        authPage.clickOnRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(name, email, password);

        WebElement expected = authPage.findEnterHeader();
        Assert.assertTrue(expected.isDisplayed());

        userAPI.login(user);
        userAPI.remove(user);
    }

    @Test
    @DisplayName("Registration user with incorrect data")
    public void registrationWithIncorrectPasswordTest() {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASEURL);

        password = String.valueOf(faker.number().numberBetween(0, 5));

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnPersonalAccount();

        AuthPage authPage = new AuthPage(driver);
        authPage.clickOnRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registration(name, email, password);

        WebElement expected = registrationPage.findPasswordError();
        Assert.assertTrue(expected.isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
