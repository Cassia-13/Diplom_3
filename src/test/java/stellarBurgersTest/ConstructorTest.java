package stellarBurgersTest;

import api.User;
import api.UserAPI;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import stellarBurgers.AuthPage;
import stellarBurgers.ConstructorPage;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class ConstructorTest {

    private final Faker faker = new Faker();
    private WebDriver driver;

    private final String name = faker.name().firstName();
    private final String email = faker.name().username() + "@bom.com";
    private final String password = faker.random().hex();

    private final UserAPI userAPI = new UserAPI();
    private final User user = new User(email, password, name);

    private final String path;

    public ConstructorTest(String browserName, String path) {
        this.path = path;
    }

    @Parameterized.Parameters(name = "Проверка в {0}")
    public static Object[][] getPath() {
        return new Object[][]{
                {"Yandex browser", "C:\\Users\\isupo\\Downloads\\yandexdriver-23.1.0.2864-win64\\yandexdriver.exe"},
                {"Chrome browser", "D:\\KateProject\\WebDriver\\bin\\chromedriver.exe"},
        };
    }

    @Before
    public void createUser() {
        userAPI.create(user);
    }

    @Test
    @DisplayName("Switching to the 'Булки' tab in the constructor")
    public void clickOnBunTabTest() {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://stellarburgers.nomoreparties.site/");

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnLoginButton();

        AuthPage authPage = new AuthPage(driver);
        authPage.auth(email, password);

        constructorPage.clickOnIngredientsTab();
        constructorPage.clickOnBunTab();

        String expected = "Булки";
        Assert.assertEquals(expected, constructorPage.findSelectedButton().getText());
    }

    @Test
    @DisplayName("Switching to the 'Соусы' tab in the constructor")
    public void clickOnSouseTabTest() {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://stellarburgers.nomoreparties.site/");

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnLoginButton();

        AuthPage authPage = new AuthPage(driver);
        authPage.auth(email, password);

        constructorPage.clickOnSouseTab();

        String expected = "Соусы";
        Assert.assertEquals(expected, constructorPage.findSelectedButton().getText());
    }

    @Test
    @DisplayName("Switching to the 'Начинки' tab in the constructor")
    public void clickOnIngredientTabTest() {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://stellarburgers.nomoreparties.site/");

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickOnLoginButton();

        AuthPage authPage = new AuthPage(driver);
        authPage.auth(email, password);

        constructorPage.clickOnIngredientsTab();

        String expected = "Начинки";
        Assert.assertEquals(expected, constructorPage.findSelectedButton().getText());
    }

    @After
    public void tearDown() {
        driver.quit();
        userAPI.remove(user);
    }
}
